package com.jsilver.boardchat.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import com.jsilver.boardchat.dto.ChatMessage;
import com.jsilver.boardchat.dto.MessageType;
import com.jsilver.boardchat.service.ChatService;

@Component
public class StompHandler implements ChannelInterceptor {
	@Autowired
	private ChatService chatService;
	
    @Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = accessor.getSessionId();
        
        switch (accessor.getCommand()) {
            case CONNECT:
                System.out.println("CONNECT : " + message);
                break;
            case DISCONNECT:
                System.out.println("DISCONNECT : " + message);
                chatService.removeUser(sessionId);
                break;
            default:
                break;
        }
	}
}
