package com.d.weatherapp.Room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.d.weatherapp.ForecastRoom.ForecastDao;
import com.d.weatherapp.ForecastRoom.ForecastDatabase;
import com.d.weatherapp.ForecastRoom.ForecastRepository;
import com.d.weatherapp.ForecastRoom.ForecastWeather;

import java.util.List;

public class WeatherRepository {

    private WeatherDao weatherDao;
    private LiveData<List<Weathers>> allweathers;

    private ForecastDao forecastDao;
    private LiveData<List<ForecastWeather>> allForecastweathers;



    public WeatherRepository(Application application) {
        WeatherDatabase weatherDatabase=WeatherDatabase.getInstance(application);
        weatherDao=weatherDatabase.weatherDao();
        allweathers=weatherDao.getAllWeathers();

        ForecastDatabase forecastDatabase=ForecastDatabase.getInstance(application);
        forecastDao=forecastDatabase.forecastDao();
        allForecastweathers=forecastDao.getAllForecastWeather();

    }

    public void insertForecastWeather(ForecastWeather forecastWeather){
        new InsertForecastWeathersAsyncTask(forecastDao).execute(forecastWeather);

    }

    public LiveData<List<ForecastWeather>> getAllForecastweathers() {
        return allForecastweathers;
    }

    public void deleteAllForecastWeathers(){
        new DeleteAllforecastWeatherAsyncTask(forecastDao).execute();
    }


    private class InsertForecastWeathersAsyncTask extends AsyncTask<ForecastWeather,Void,Void> {

        private ForecastDao forecastDao;

        public InsertForecastWeathersAsyncTask(ForecastDao forecastDao) {
            this.forecastDao=forecastDao;
        }

        @Override
        protected Void doInBackground(ForecastWeather... forecastWeathers) {

            forecastDao.insertForecastWeather(forecastWeathers[0]);


            return null;
        }
    }

    private class DeleteAllforecastWeatherAsyncTask extends AsyncTask<ForecastWeather,Void,Void> {
        private ForecastDao forecastDao;


        public DeleteAllforecastWeatherAsyncTask(ForecastDao forecastDao) {
            this.forecastDao=forecastDao;
        }

        @Override
        protected Void doInBackground(ForecastWeather... forecastWeathers) {
            forecastDao.deleteAllForecastWeather();
            return null;
        }
    }



    public void insert(Weathers weathers){
        new InsertWeathersAsyncTask(weatherDao).execute(weathers);

    }

    public LiveData<List<Weathers>> getAllweathers() {
        return allweathers;
    }

    public void deleteAllWeathers(){
        new DeleteAllWeatherAsyncTask(weatherDao).execute();

    }



    private class InsertWeathersAsyncTask extends AsyncTask<Weathers,Void,Void> {

        private WeatherDao weatherDao;

        public InsertWeathersAsyncTask(WeatherDao weatherDao) {
            this.weatherDao=weatherDao;
        }

        @Override
        protected Void doInBackground(Weathers... weathers) {

            weatherDao.insertCurrentWeather(weathers[0]);
            return null;
        }
    }

    private class DeleteAllWeatherAsyncTask extends AsyncTask<Void,Void,Void> {
        private WeatherDao weatherDao;

        public DeleteAllWeatherAsyncTask(WeatherDao weatherDao) {
            this.weatherDao=weatherDao;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            weatherDao.deleteAllWeather();
            return null;
        }
    }
}
