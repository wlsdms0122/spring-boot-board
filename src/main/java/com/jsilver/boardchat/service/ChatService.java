package com.jsilver.boardchat.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.jsilver.boardchat.dto.ChatMessage;
import com.jsilver.boardchat.dto.MessageType;

@Service
public class ChatService {
	@Autowired
	private SimpMessagingTemplate template;
	
	private Map<String, String> users;
	
	public ChatService() {
		this.users = new HashMap<>();
	}
	
	public void addUser(String sessionId, String nickname) {
		users.put(sessionId, nickname);
	}
	
	public void removeUser(String sessionId) {
		String nickname = users.get(sessionId);
    	this.template.convertAndSend("/topic/chat", new ChatMessage(MessageType.EXIT, nickname));
		users.remove(sessionId);
	}
}
