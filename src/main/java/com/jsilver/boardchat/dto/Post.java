package com.jsilver.boardchat.dto;

import java.util.Date;

public class Post {
	private int id;
	private String title;
	private String content;
	private String writer;
	private String password;
	private String category;
	private Date ctime;
	private Date mtime;
	private int parent;
	private boolean isDeleted;
	
	private int commentCount;
	private boolean isNew = false;
	private String[] contents;
	
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
	
	public int getParent() {
		return parent;
	}
	
	public void setParent(int parent) {
		this.parent = parent;
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

	public String[] getContents() {
		return this.contents;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
}
