package com.flyit.application.models;

public class Airport {
    private String iata;
    private String name;
    private String mapUrl;
    private String mapName;
    private String rentingCompanyUrl;
    private String rentingCompanyName;
    private String rentingCompanyPhoneNo;

    public Airport(String iata, String name, String mapUrl, String mapName, String rentingCompanyUrl, String rentingCompanyName, String rentingCompanyPhoneNo) {
        this.iata = iata;
        this.name = name;
        this.mapUrl = mapUrl;
        this.mapName = mapName;
        this.rentingCompanyUrl = rentingCompanyUrl;
        this.rentingCompanyName = rentingCompanyName;
        this.rentingCompanyPhoneNo = rentingCompanyPhoneNo;
    }

    public String getIata() {
        return iata;
    }

    public String getName() {
        return name;
    }

    public String getMapName() {
        return mapName;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public String getRentingCompanyUrl() {
        return rentingCompanyUrl;
    }

    public String getRentingCompanyName() {
        return rentingCompanyName;
    }

    public String getRentingCompanyPhoneNo() {
        return rentingCompanyPhoneNo;
    }
}
