package com.jsilver.boardchat.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsilver.boardchat.dto.Comment;
import com.jsilver.boardchat.mapper.CommentMapper;

@Service
public class CommentService {
	@Autowired
	CommentMapper commentMapper;
	
	public void create(Comment comment) throws SQLException {
		Date now = new Date();
		comment.setCtime(now);
		comment.setMtime(now);
		
		commentMapper.insert(comment);
	}
	
	public void remove(int id) throws SQLException {
		commentMapper.delete(id);
	}
	
	public void modify(Comment comment) throws SQLException {
		
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
