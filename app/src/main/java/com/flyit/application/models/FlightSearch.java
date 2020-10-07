package com.flyit.application.models;

public class FlightSearch {
    private String flightNo;
    private String date;

    public FlightSearch(String flightNo, String date) {
        this.flightNo = flightNo;
        this.date = date;
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
}
