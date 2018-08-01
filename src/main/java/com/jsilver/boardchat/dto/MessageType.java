package com.jsilver.boardchat.dto;

public enum MessageType {
	JOIN(1), EXIT(2), CHAT(3);
	
	final private int type;
	
	MessageType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
}
