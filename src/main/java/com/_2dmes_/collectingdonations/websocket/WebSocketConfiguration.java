package com._2dmes_.collectingdonations.websocket;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import com._2dmes_.collectingdonations.components.DataBaseComponent;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@Component
public class WebSocketConfiguration implements WebSocketConfigurer {
    @Autowired
    private DataBaseComponent db;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new EventHandler(db), "/plugin")
                .setAllowedOrigins("*");
    }

}