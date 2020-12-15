package com.flyit.application.models;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Flight {
    private int id;
    private String date;
    private String lastUpdated;
    private String flightNo;
    private String status;
    private int chatroomId;
    private DepartureDestination departure;
    private DepartureDestination destination;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public OffsetDateTime getDateTimeOffset() {
        return OffsetDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME).withOffsetSameInstant(ZoneOffset.UTC);
    }

    public String getFlightNo() {
        return flightNo;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime getLastUpdatedDateTime() {
        if (lastUpdated.contains("+"))
        {
            return LocalDateTime.parse(lastUpdated, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }

        return LocalDateTime.parse(lastUpdated);
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
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



