package com.jsilver.boardchat.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jsilver.boardchat.dto.Post;

@Mapper
public interface PostMapper {
	public List<Post> select(Map<String, Object> param);
	public Post selectOne(int id);
	public void insert(Post post);
	public void delete(int id);
	public void update(Post post);
	public int selectCount();
}
