package com.jsilver.boardchat.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jsilver.boardchat.dto.Comment;

@Mapper
public interface CommentMapper {
	public List<Comment> select(Map<String, Object> param);
	public Comment selectOne(int id);
	public void insert(Comment post);
	public void delete(int id);
	public void update(Comment post);
	public int selectCount(int post_id);
}
