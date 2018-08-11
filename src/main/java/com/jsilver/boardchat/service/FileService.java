package com.jsilver.boardchat.service;

import com.jsilver.boardchat.dto.File;
import com.jsilver.boardchat.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class FileService {

	@Autowired
	private FileMapper fileMapper;

	private Path rootLocation;

	public FileService() {
		this.rootLocation = Paths.get("D:\\Uploads");
	}

	public List<File> insert(MultipartFile[] files) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");

		List<File> fl = new ArrayList<>();
		for (MultipartFile file : files) {
			String[] filename = file.getOriginalFilename().split("\\.");

			File f = new File();
			f.setFilename(format.format(calendar.getTime()) + filename[0] + "." + filename[1]);
			f.setOrigin(filename[0]);
			f.setExt(filename[1]);

			fileMapper.insert(f);
			store(file, f.getFilename());
			fl.add(f);
		}

		return fl;
	}

	public void remove(int[] file_id) {
		for (int id : file_id) {
			File file = fileMapper.selectOne(id);
			fileMapper.delete(id);
			delete(file.getFilename());
		}
	}

	public void linkWithPost(int[] file_id, int post_id) {
		for (int id : file_id) {
			File file = fileMapper.selectOne(id);
			file.setPost_id(post_id);
			fileMapper.update(file);
		}
	}

	private void store(MultipartFile file, String filename) {
		// automatically call close() if object need to call close() like stream
		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void delete(String filename) {
		Path file = rootLocation.resolve(filename);
		try {
			FileSystemUtils.deleteRecursively(file);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Resource load(String filename) {
		Path file = rootLocation.resolve(filename);
		Resource resource = null;

		try {
			resource = new UrlResource(file.toUri());
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return resource;
	}

	public List<File> getFiles(int post_id) {
		return fileMapper.select(post_id);
	}

	public File getFile(int id) {
		return fileMapper.selectOne(id);
	}
}
