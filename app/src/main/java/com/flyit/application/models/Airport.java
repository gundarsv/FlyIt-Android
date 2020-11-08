package com.flyit.application.models;

public class Airport {
    private String iata;
    private String name;
    private String mapUrl;
    private String mapName;
    private String rentingCompanyUrl;
    private String rentingCompanyName;
    private String rentingCompanyPhoneNo;

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getRentingCompanyUrl() {
        return rentingCompanyUrl;
    }

    public void setRentingCompanyUrl(String rentingCompanyUrl) {
        this.rentingCompanyUrl = rentingCompanyUrl;
    }

    public String getRentingCompanyName() {
        return rentingCompanyName;
    }

    public void setRentingCompanyName(String rentingCompanyName) {
        this.rentingCompanyName = rentingCompanyName;
    }

    public String getRentingCompanyPhoneNo() {
        return rentingCompanyPhoneNo;
    }

    public void setRentingCompanyPhoneNo(String rentingCompanyPhoneNo) {
        this.rentingCompanyPhoneNo = rentingCompanyPhoneNo;
    }
}
