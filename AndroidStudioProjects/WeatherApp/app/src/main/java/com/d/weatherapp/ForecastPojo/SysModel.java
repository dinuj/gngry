package com.d.weatherapp.ForecastPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class SysModel {

    @SerializedName(" pod")
    @Expose
    private String  pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }
}
