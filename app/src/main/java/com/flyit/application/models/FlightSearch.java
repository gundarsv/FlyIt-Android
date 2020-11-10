package com.flyit.application.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

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

    public String getDate() {
        return date;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public OffsetDateTime getDateTimeOffset() {
        return OffsetDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
