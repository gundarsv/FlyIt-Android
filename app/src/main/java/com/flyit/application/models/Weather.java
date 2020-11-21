package com.flyit.application.models;

public class Weather {
    private int temperature;
    private int realFeel;
    private int humidity;
    private int windSpeed;
    private String city;

    public Weather(int temperature, int realFeel, int humidity, int windSpeed, String city) {
        this.temperature = temperature;
        this.realFeel = realFeel;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.city = city;
    }

    public String getTemperature() {
        return "" + temperature;
    }

    public String getRealFeel() {
        return "" + realFeel;
    }

    public String getHumidity() {
        return "" + humidity;
    }

    public String getWindSpeed() {
        return "" + windSpeed;
    }

    public String getCity() {
        return city;
    }
}
