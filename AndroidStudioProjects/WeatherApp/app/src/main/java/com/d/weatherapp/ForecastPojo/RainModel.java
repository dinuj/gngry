package com.d.weatherapp.ForecastPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class RainModel {

    @SerializedName(" 3h")
    @Expose
    private double  hours;

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }
}
