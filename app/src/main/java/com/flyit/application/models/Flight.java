package com.flyit.application.models;

public class Flight {
    private float id;
    private String date;
    private String flightNo;
    private String status;
    private int chatroomId;
    private DepartureDestination departure;
    private DepartureDestination destination;

    public float getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getStatus() {
        return status;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DepartureDestination getDeparture() {
        return departure;
    }

    public void setDeparture(DepartureDestination departure) {
        this.departure = departure;
    }

    public DepartureDestination getDestination() {
        return destination;
    }

    public void setDestination(DepartureDestination destination) {
        this.destination = destination;
    }

    public int getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(int chatroomId) {
        this.chatroomId = chatroomId;
    }
}



