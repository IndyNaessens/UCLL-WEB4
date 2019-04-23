package org.ucll.web4.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/blog")
public class BlogWebSocket {

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    //constructor
    public BlogWebSocket(){}

    //actions
    @OnOpen
    public void onOpen(Session session){
        if(session != null) sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message){
        int score = Integer.parseInt(message.split(",")[3]);

        if(score < 1 || score > 10) return;

        //broadcast message
        sessions.forEach(s -> {
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @OnClose
    public void onClose(Session session){
        if(session != null) sessions.remove(session);
    }

    @OnError
    public void onError(Throwable throwable){
        throwable.printStackTrace();
    }



}
