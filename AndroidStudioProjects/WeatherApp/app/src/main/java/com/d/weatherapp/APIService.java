package com.d.weatherapp;

import com.d.weatherapp.ForecastPojo.ForecastModel;
import com.d.weatherapp.WeatherPojo.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather?id=1277333&appid=1b6b5d707e2ce2c6124e0ad44456d8c6&units=metric")
    Call<WeatherModel> getWeather();

    @GET("forecast?id=1277333&APPID=1b6b5d707e2ce2c6124e0ad44456d8c6&units=metric")
    Call<ForecastModel> getForecast();

}
