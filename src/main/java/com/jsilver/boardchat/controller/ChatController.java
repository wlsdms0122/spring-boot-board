package com.jsilver.boardchat.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsilver.boardchat.dto.ChatMessage;

@Controller
public class ChatController {
	
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
    
    @MessageMapping("/in")
    @SendTo("/topic/in")
    public ChatMessage in(ChatMessage message) throws Exception {
    	System.out.println("[SYSTEM] " + message.getNickname() + " joined.");
    	
    	return message;
    }
    
    @MessageMapping("/out")
    @SendTo("/topic/out")
    public ChatMessage out(ChatMessage message) throws Exception {
    	System.out.println("[SYSTEM] " + message.getNickname() + " leaved.");
    	
    	return message;
    }
	
    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public ChatMessage chat(ChatMessage message) throws Exception {
    	Date time = new Date();
    	message.setTime(time);
    	
    	System.out.println("[SYSTEM] " + message.getNickname() + " : " + message.getContent() + " - " + time.toString());
    	
    	return message;
    }
}
