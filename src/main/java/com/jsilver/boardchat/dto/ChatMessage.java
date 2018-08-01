package com.jsilver.boardchat.dto;

public class ChatMessage {
	private MessageType type;
	private String nickname;
	private String content;
	private String time;

	public ChatMessage() {
		
	}
	
	public ChatMessage(MessageType type, String nickname) {
		this.type = type;
		this.nickname = nickname;
	}
	
	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}	
}