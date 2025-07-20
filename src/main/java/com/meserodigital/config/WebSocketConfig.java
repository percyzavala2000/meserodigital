package com.meserodigital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
      // Prefijos de destino para cliente
      registry.setApplicationDestinationPrefixes("/app");
      registry.enableSimpleBroker("/topic"); // Aquí van tus mensajes
  }
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Solo WebSocket puro, sin SockJS
         registry.addEndpoint("/ws/websocket")
         .setAllowedOriginPatterns("*");
               // .setAllowedOrigins("http://192.168.18.9:19006"); 
            // registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS(); // ✅
    }

}