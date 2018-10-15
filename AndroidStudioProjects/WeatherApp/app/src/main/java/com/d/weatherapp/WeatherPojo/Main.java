package com.d.weatherapp.WeatherPojo;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
        private double temp;
        @SerializedName("pressure")
        private Integer pressure;
        @SerializedName("humidity")
        private Integer humidity;
        @SerializedName("temp_min")
        private double tempmin;
        @SerializedName("temp_max")
        private double tempmax;

        private String dummy;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public double getTempmin() {
        return tempmin;
    }

    public void setTempmin(double tempmin) {
        this.tempmin = tempmin;
    }

    public double getTempmax() {
        return tempmax;
    }

    public void setTempmax(double tempmax) {
        this.tempmax = tempmax;
    }

    public String getDummy() {
        return dummy;
    }

    public void setDummy(String dummy) {
        this.dummy = dummy;
    }
}
