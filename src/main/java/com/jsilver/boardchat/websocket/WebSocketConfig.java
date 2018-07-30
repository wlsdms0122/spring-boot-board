package com.jsilver.boardchat.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Autowired
	StompHandler stompHandler;
	
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
    	// set topic prefix (like url, address) server to client
    	// server boradcast using @SendTo annotaion.
    	// topic must start "/topic"
        config.enableSimpleBroker("/topic");
        // set destination prefix client to server
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	// set server socket end-point. client connect this url
        registry.addEndpoint("/websocket").withSockJS();
    }

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(stompHandler);
	}
}