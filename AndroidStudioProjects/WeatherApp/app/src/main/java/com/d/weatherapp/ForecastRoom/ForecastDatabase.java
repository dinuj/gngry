package com.d.weatherapp.ForecastRoom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = ForecastWeather.class,version = 3)
public abstract class ForecastDatabase extends RoomDatabase {
    private static ForecastDatabase instance;

    public abstract ForecastDao forecastDao();

    public static synchronized ForecastDatabase getInstance(Context context){

        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    ForecastDatabase.class,"forecast_database").
                    fallbackToDestructiveMigration().
                    build();
        }
        return instance;
    }
}
