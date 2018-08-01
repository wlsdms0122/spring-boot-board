package com.jsilver.boardchat.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsilver.boardchat.dto.ChatMessage;
import com.jsilver.boardchat.dto.MessageType;
import com.jsilver.boardchat.service.ChatService;

@Controller
public class ChatController {
	@Autowired
	private ChatService chatService;
	
	@RequestMapping("/chat/index")
	public String index(Model model) {
		model.addAttribute("chat", "active");
		
		return "chat/index";
	}
	
	@RequestMapping("/chat/join")
	public String join(Model model) {
		model.addAttribute("chat", "active");
		
		return "chat/join";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/chat/room")
	public String room(@RequestParam String nickname, Model model) {
		model.addAttribute("chat", "active");
		model.addAttribute("nickname", nickname);
		
		return "chat/room";
	}
	
    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public ChatMessage chat(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
    	switch (message.getType()) {
    	case JOIN:
            String sessionId = headerAccessor.getSessionId();
    		chatService.addUser(sessionId, message.getNickname());
    		break;
    	case CHAT:
        	Date time = new Date();
        	message.setTime(time.toGMTString());
        	message.setType(MessageType.CHAT);
    		break;
    	}
    	
    	return message;
    }
}
