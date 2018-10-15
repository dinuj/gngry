package com.d.weatherapp.ForecastRoom;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class ForecastRepository {

//    private ForecastDao forecastDao;
//    private LiveData<List<ForecastWeather>> allForecastweathers;
//
//    public ForecastRepository(Application application) {
//
//        ForecastDatabase forecastDatabase=ForecastDatabase.getInstance(application);
//        forecastDao=forecastDatabase.forecastDao();
//        allForecastweathers=forecastDao.getAllForecastWeather();
//    }
//
//    public void insert(ForecastWeather forecastWeather){
//        new InsertWeathersAsyncTask(forecastDao).execute(forecastWeather);
//
//    }
//
//    public LiveData<List<ForecastWeather>> getAllForecastweathers() {
//        return allForecastweathers;
//    }
//
//    public void deleteAllForecastWeathers(){
//        new DeleteAllforecastWeatherAsyncTask(forecastDao).execute();
//    }
//
//
//    private class InsertWeathersAsyncTask extends AsyncTask<ForecastWeather,Void,Void> {
//
//        private ForecastDao forecastDao;
//
//        public InsertWeathersAsyncTask(ForecastDao forecastDao) {
//            this.forecastDao=forecastDao;
//        }
//
//        @Override
//        protected Void doInBackground(ForecastWeather... forecastWeathers) {
//
//            forecastDao.insertForecastWeather(forecastWeathers[0]);
//
//
//            return null;
//        }
//    }
//
//    private class DeleteAllforecastWeatherAsyncTask extends AsyncTask<ForecastWeather,Void,Void> {
//        private ForecastDao forecastDao;
//
//
//        public DeleteAllforecastWeatherAsyncTask(ForecastDao forecastDao) {
//            this.forecastDao=forecastDao;
//        }
//
//        @Override
//        protected Void doInBackground(ForecastWeather... forecastWeathers) {
//            forecastDao.deleteAllForecastWeather();
//            return null;
//        }
//    }
}
