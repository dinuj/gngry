package com.d.weatherapp.ForecastPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class CloudModel {

    @SerializedName("all")
    @Expose
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }
}
