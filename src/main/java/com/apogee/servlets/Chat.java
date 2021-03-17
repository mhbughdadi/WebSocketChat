package com.apogee.servlets;
import com.apogee.dto.Message;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;


public class Chat extends HttpServlet {
    public static List<Message> messages ;

    @Override
    public void init() throws ServletException {
        super.init();
        messages = new ArrayList<Message>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String mess = req.getParameter("message") ;

        // tramsform the message obj to json string
        Message messageObj = new Message (userName, mess, null ,    0 );
        messages.add(messageObj);
        Jsonb json = JsonbBuilder.create();
        String messJson = json.toJson(messages);

        System.out.println(messages);
        resp.getWriter().println(messJson);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb json = JsonbBuilder.create();
        String messJson = json.toJson(messages);

        System.out.println(messages);
        resp.getWriter().println(messJson);

    }
}
