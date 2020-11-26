package com.flyit.application.models;

public class ChatroomMessage {
    private String userName;
    private String message;
    private String dateTime;
    private int messageType;

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public ChatroomMessage(String userName, String message, String dateTime, int messageType) {
        this.userName = userName;
        this.message = message;
        this.dateTime = dateTime;
        this.messageType = messageType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
