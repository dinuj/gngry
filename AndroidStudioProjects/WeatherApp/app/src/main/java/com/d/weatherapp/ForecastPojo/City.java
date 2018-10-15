package com.d.weatherapp.ForecastPojo;

import com.d.weatherapp.ForecastPojo.CoordModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("coord")
    @Expose
    private CoordModel coordModel;

    @SerializedName("country")
    @Expose
    private String country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public CoordModel getCoordModel() {
        return coordModel;
    }

    public void setCoordModel(CoordModel coordModel) {
        this.coordModel = coordModel;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
