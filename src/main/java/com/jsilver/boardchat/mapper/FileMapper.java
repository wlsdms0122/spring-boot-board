package com.jsilver.boardchat.mapper;

import com.jsilver.boardchat.dto.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
	public List<File> select(int post_id);
	public File selectOne(int id);
	public void insert(File file);
	public void delete(int id);
	public void update(File file);
}
