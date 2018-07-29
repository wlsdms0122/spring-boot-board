package com.jsilver.boardchat.dto;

import java.util.Date;

public class Comment {
	private int id;
	private String content;
	private String writer;
	private String password;
	private Date ctime;
	private Date mtime;
	private int post_id;
	
	private String[] contents;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
		this.contents = this.content.split("\\r\\n");
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getCtime() {
		return ctime;
	}
	
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	public Date getMtime() {
		return mtime;
	}
	
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	
	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	
	public String[] getContents() {
		return this.contents;
	}
}
