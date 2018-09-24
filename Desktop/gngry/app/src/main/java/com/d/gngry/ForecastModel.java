package com.d.gngry;

public class ForecastModel {

    private  String weatherMain;
    private  String weatherdescr;
    private  String date;


    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherdescr() {
        return weatherdescr;
    }

    public void setWeatherdescr(String weatherdescr) {
        this.weatherdescr = weatherdescr;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
