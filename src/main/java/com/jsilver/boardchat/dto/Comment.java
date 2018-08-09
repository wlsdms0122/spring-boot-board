package com.jsilver.boardchat.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
	// database
	private int id;
	private String content;
	private String writer;
	private String password;
	private String ctime;
	private String mtime;
	private int post_id;

	// view
	private String[] contents;
	private Date cdate;
	
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
	
	public String getCtime() {
		return ctime;
	}
	
	public void setCtime(String ctime) {
		this.ctime = ctime;

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.cdate = format.parse(this.ctime);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getMtime() {
		return mtime;
	}
	
	public void setMtime(String mtime) {
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

	public Date getCdate() { return this.cdate; }
}
