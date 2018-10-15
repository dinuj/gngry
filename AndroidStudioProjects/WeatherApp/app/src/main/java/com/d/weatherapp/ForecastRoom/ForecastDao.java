package com.d.weatherapp.ForecastRoom;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ForecastDao {

    @Insert
    void insertForecastWeather(ForecastWeather forecastWeather);

    @Query("SELECT * FROM forecast_table")
    LiveData<List<ForecastWeather>> getAllForecastWeather();

    @Query("DELETE FROM forecast_table ")
    void deleteAllForecastWeather();
}

