package com.d.weatherapp.Room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.d.weatherapp.WeatherPojo.Weather;

import java.util.List;

@Dao
public interface WeatherDao {

    @Insert
     void insertCurrentWeather(Weathers weathers);

    @Query("SELECT * FROM weather_table")
    LiveData<List<Weathers>>getAllWeathers();

    @Query("DELETE FROM weather_table ")
    void deleteAllWeather();


}