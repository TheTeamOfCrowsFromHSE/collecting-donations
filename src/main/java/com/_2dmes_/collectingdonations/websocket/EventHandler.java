package com._2dmes_.collectingdonations.websocket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com._2dmes_.collectingdonations.components.DataBaseComponent;

import javax.annotation.Priority;

@Priority(-10)
@Service("WebSocketHandler")
public class EventHandler extends TextWebSocketHandler implements WebSocketHandler {
    @Autowired
    private DataBaseComponent db;

    @Autowired
    public EventHandler(DataBaseComponent db) {
        this.db = db;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println("Socket Connected: " + session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        Long id = Long.valueOf(message.getPayload());
//        TextMessage text = db.getNotDisplayedTransactions(id);
//        session.sendMessage(text);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("Socket Closed: [" + closeStatus.getCode() + "] " + closeStatus.getReason());
        super.afterConnectionClosed(session, closeStatus);
    }
}