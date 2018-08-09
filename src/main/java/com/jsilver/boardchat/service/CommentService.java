package com.jsilver.boardchat.service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsilver.boardchat.dto.Comment;
import com.jsilver.boardchat.mapper.CommentMapper;

@Service
public class CommentService {
	@Autowired
	CommentMapper commentMapper;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void create(Comment comment) {
		Calendar calendar = Calendar.getInstance();
		String now = format.format(calendar.getTime());
		comment.setCtime(now);
		comment.setMtime(now);
		
		commentMapper.insert(comment);
	}
	
	public void remove(int id) {
		commentMapper.delete(id);
	}
	
	public void modify(Comment comment) {
		
	}
	
	public List<Comment> getComments(int page, int post_id) {
		Map<String, Object> param = new HashMap<>();
		param.put("start", 20 * (page - 1));
		param.put("post_id", post_id);
		
		return commentMapper.select(param);
	}
	
	public Comment getComment(int id) {
		return commentMapper.selectOne(id);
	}
	
	public int getCommentCount(int post_id) {
		return commentMapper.selectCount(post_id);
	}
}
