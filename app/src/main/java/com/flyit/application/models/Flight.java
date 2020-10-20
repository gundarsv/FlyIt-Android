package com.flyit.application.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Flight {
    private float id;
    private String date;
    private String flightNo;
    private String status;
    DepartureDestination departure;
    DepartureDestination destination;

    // Getter Methods

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

    // Setter Methods

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


    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}



