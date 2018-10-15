package com.d.weatherapp.ForecastPojo;

import com.d.weatherapp.ForecastPojo.CloudModel;
import com.d.weatherapp.ForecastPojo.MainModel;
import com.d.weatherapp.ForecastPojo.RainModel;
import com.d.weatherapp.ForecastPojo.SysModel;
import com.d.weatherapp.ForecastPojo.WeatherMdl;
import com.d.weatherapp.ForecastPojo.WindModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {

    @SerializedName("dt")
    @Expose
    private Integer dt;

    @SerializedName("main")
    @Expose
    public MainModel mainModel;

    @SerializedName("weather")
    @Expose
    public List<WeatherMdl> weather = null;


    @SerializedName("clouds")
    @Expose
    private CloudModel cloudModel;

    @SerializedName("wind")
    @Expose
    private WindModel windModel;

    @SerializedName("rain")
    @Expose
    private RainModel rainModel;

    @SerializedName("sys")
    @Expose
    private SysModel sysModel;

    @SerializedName("dt_txt")
    @Expose
    private String dt_txt;



    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public MainModel getMainModel() {
        return mainModel;
    }

    public void setMainModel(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public List<WeatherMdl> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherMdl> weather) {
        this.weather = weather;
    }

    public CloudModel getCloudModel() {
        return cloudModel;
    }

    public void setCloudModel(CloudModel cloudModel) {
        this.cloudModel = cloudModel;
    }

    public WindModel getWindModel() {
        return windModel;
    }

    public void setWindModel(WindModel windModel) {
        this.windModel = windModel;
    }

    public RainModel getRainModel() {
        return rainModel;
    }

    public void setRainModel(RainModel rainModel) {
        this.rainModel = rainModel;
    }

    public SysModel getSysModel() {
        return sysModel;
    }

    public void setSysModel(SysModel sysModel) {
        this.sysModel = sysModel;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
