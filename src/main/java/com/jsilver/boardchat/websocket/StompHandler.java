package com.jsilver.boardchat.websocket;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class StompHandler implements ChannelInterceptor {

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
                break;
            default:
                break;
        }
	}
}
