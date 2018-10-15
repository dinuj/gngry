package com.d.weatherapp.Room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "weather_table")
public class Weathers {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private double temp;
    private Integer humidity;
    private double tempmin;
    private double tempmax;
    private String name;
    private String description;


    public Weathers(double temp, Integer humidity, double tempmin, double tempmax, String name, String description) {
        this.temp = temp;
        this.humidity = humidity;
        this.tempmin = tempmin;
        this.tempmax = tempmax;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
