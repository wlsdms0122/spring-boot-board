package com.jsilver.boardchat.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
	// database
	private int id;
	private String title;
	private String content;
	private String writer;
	private String password;
	private String category;
	private String ctime;
	private String mtime;
	private boolean isDeleted;

	// view
	private int commentCount;
	private boolean isNew = false;
	private String[] contents;
	private Date cdate;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
		contents = this.content.split("\\r?\\n");
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
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
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

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public String[] getContents() {
		return this.contents;
	}

	public Date getCdate() {
		return this.cdate;
	}
}
