package com.jsilver.boardchat.service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsilver.boardchat.dto.Post;
import com.jsilver.boardchat.dto.ViewParams;
import com.jsilver.boardchat.mapper.PostMapper;

@Service
public class PostService {
	@Autowired
	PostMapper postMapper;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void create(Post post) {
		Calendar calendar = Calendar.getInstance();
		String now = format.format(calendar.getTime());
		post.setCtime(now);
		post.setMtime(now);
		
		postMapper.insert(post);
	}
	
	public void remove(int id) {
		postMapper.delete(id);
	}
	
	public void modify(Post post) {
		Calendar calendar = Calendar.getInstance();
		String now = format.format(calendar.getTime());
		post.setMtime(now);
		
		postMapper.update(post);
	}
	
	public List<Post> getPosts(ViewParams viewParams) {
		Map<String, Object> param = new HashMap<>();
		param.put("start", 20 * (viewParams.getPage() - 1));
		param.put("category", (viewParams.getCategory() == null || viewParams.getCategory().equals("전체")) ? null : viewParams.getCategory());
		param.put("option", viewParams.getOption());
		param.put("search", viewParams.getSearch());

		List<Post> posts = postMapper.select(param);
		
		// represent recently updated post
		Calendar calendar = Calendar.getInstance();
		for(Post post : posts) {
			long dif = calendar.getTimeInMillis() - post.getCdate().getTime();
			long difDays = dif / (24 * 60 * 60 * 1000);

			if (difDays > 0)
				break;

			post.setNew(true);
		}

		return posts;
	}
	
	public Post getPost(int id) {
		return postMapper.selectOne(id);
	}
	
	public int getPostCount() {
		return postMapper.selectCount();
	}
}
