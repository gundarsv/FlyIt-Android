package com.flyit.application.models;

public class Airport {
    private String iata;
    private String name;
    private String mapUrl;
    private String mapName;
    private String rentingCompanyUrl;
    private String rentingCompanyName;
    private String rentingCompanyPhoneNo;
    private String taxiPhoneNo;
    private String emergencyPhoneNo;
    private String icao;



    public Airport(String iata, String name, String mapUrl, String mapName, String rentingCompanyUrl, String rentingCompanyName, String rentingCompanyPhoneNo, String taxiPhoneNo, String emergencyPhoneNo, String icao) {
        this.iata = iata;
        this.name = name;
        this.mapUrl = mapUrl;
        this.mapName = mapName;
        this.rentingCompanyUrl = rentingCompanyUrl;
        this.rentingCompanyName = rentingCompanyName;
        this.rentingCompanyPhoneNo = rentingCompanyPhoneNo;
        this.taxiPhoneNo = taxiPhoneNo;
        this.emergencyPhoneNo = emergencyPhoneNo;
        this.icao = icao;
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

    public String getTaxiPhoneNo() {
        return taxiPhoneNo;
    }

    public String getEmergencyPhoneNo() {
        return emergencyPhoneNo;
    }

    public String getIcao() {
        return icao;
    }

}
