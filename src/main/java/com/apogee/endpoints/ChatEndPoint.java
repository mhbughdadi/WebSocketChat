package com.apogee.endpoints;

import com.apogee.dto.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.ArrayList;

@ApplicationScoped
@ServerEndpoint("/chat")
public class ChatEndPoint {
    public static ArrayList<Session> sessions = new ArrayList<>();
    public static ArrayList<Message> messages = new ArrayList<>();

    @OnOpen
    public void open(Session session) {
        sessions.add(session);
        System.out.println(sessions);

    }

    @OnMessage
    public void onMessage(String message, Session session) {
        Gson json = new GsonBuilder().create();
        Message messageObj = json.fromJson(message, Message.class);
        if (messageObj.getMessage().equals("logout1010")) {
            /*if(messages.removeIf(m->  m.getUserName().equals(messageObj.getUserName()))){
                sessions.remove(session);
                publishMessage(json.toJson(messages));
            }*/
           for(int i= 0 ; i<messages.size(); i++){
               if(messages.get(i).getMessage().equals("")&&messages.get(i).getUserName().equals(messageObj.getUserName())){
                  messages.remove(i);
               }

           }

        } else {
            messages.add(messageObj);
        }

        publishMessage(json.toJson(messages));
    }

    public void publishMessage(String message) {
        sessions.forEach((sess) -> {
            try {
                sess.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @OnClose
    public void close(Session session) {
        sessions.remove(session);
    }
}
