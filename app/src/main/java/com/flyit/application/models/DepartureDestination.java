package com.flyit.application.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DepartureDestination {
    private String gate;
    private String delay = null;
    private String terminal;
    private String airportName;
    private String scheduled;
    private String estimated;
    private String actual = null;
    private String iata;
    private String icao;

    public String getIcao() {
        return icao;
    }

    public String getGate() {
        return gate;
    }

    public String getDelay() {
        return delay;
    }

    public String getTerminal() {
        return terminal;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getScheduled() {
        return scheduled;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public OffsetDateTime getDateTimeOffset() {
        return OffsetDateTime.parse(scheduled, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public String getEstimated() {
        return estimated;
    }

    public String getActual() {
        return actual;
    }

    public String getIata() {
        return iata;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public void setEstimated(String estimated) {
        this.estimated = estimated;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }
}
