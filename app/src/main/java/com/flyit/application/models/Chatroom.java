package com.flyit.application.models;

import java.util.ArrayList;
import java.util.List;

public class Chatroom {
    private int id;
    private String flightNo;
    private String date;
    private boolean hasUserJoined;
    private ArrayList<ChatroomMessage> chatroomMessages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isHasUserJoined() {
        return hasUserJoined;
    }

    public void setHasUserJoined(boolean hasUserJoined) {
        this.hasUserJoined = hasUserJoined;
    }

    public List<ChatroomMessage> getChatroomMessages() {
        return chatroomMessages;
    }

    public void setChatroomMessage(ArrayList<ChatroomMessage> chatroomMessage) {
        this.chatroomMessages = chatroomMessage;
    }
}
