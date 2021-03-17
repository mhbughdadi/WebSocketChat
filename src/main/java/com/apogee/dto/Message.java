package com.apogee.dto;

import jakarta.json.bind.annotation.JsonbNillable;
import jakarta.json.bind.annotation.JsonbProperty;

import java.util.Date;
import java.util.Objects;

public class Message {
    private String userName, message,gender ;
    private long date ;
    public Message(){}

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Message(String userName, String message, String gender, long date) {
        this.userName = userName;
        this.message = message;
        this.gender = gender ;
        this.date = date ;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @JsonbProperty(nillable = true)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
