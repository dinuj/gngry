package com.d.weatherapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.util.Log;
import android.view.VelocityTracker;

import com.d.weatherapp.ForecastPojo.City;
import com.d.weatherapp.ForecastPojo.Forecast;
import com.d.weatherapp.ForecastPojo.ForecastModel;
import com.d.weatherapp.ForecastPojo.MainModel;
import com.d.weatherapp.ForecastPojo.WeatherMdl;
import com.d.weatherapp.ForecastRoom.ForecastRepository;
import com.d.weatherapp.ForecastRoom.ForecastWeather;
import com.d.weatherapp.Room.WeatherRepository;
import com.d.weatherapp.Room.Weathers;
import com.d.weatherapp.WeatherPojo.Main;
import com.d.weatherapp.WeatherPojo.Weather;
import com.d.weatherapp.WeatherPojo.WeatherModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherViewModel extends AndroidViewModel {

    private MutableLiveData<WeatherModel> WeatherList;
    private MutableLiveData<ForecastModel> forecastList;
    private WeatherRepository weatherRepository;
    private LiveData<List<Weathers>> alllWeathers;
    private LiveData<List<ForecastWeather>>allForecastWeather;

    final Handler handler = new Handler();


    private final MutableLiveData<ForecastWeather> selectedForecast = new MutableLiveData<ForecastWeather>();

    public WeatherViewModel(Application application) {
        super(application);
        weatherRepository = new WeatherRepository(application);
        alllWeathers = weatherRepository.getAllweathers();
        allForecastWeather=weatherRepository.getAllForecastweathers();

    }

    public LiveData<List<ForecastWeather>>getAllForecastWeather(){

        return allForecastWeather;
    }

    public LiveData<List<Weathers>> getAllWeathers() {
        return alllWeathers;
    }

    public LiveData<WeatherModel> getWeather() {

        if (WeatherList == null) {
            WeatherList = new MutableLiveData<WeatherModel>();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    loadWeather();

                    handler.postDelayed(this, 30000);
                }
            }, 0);
        }
        return WeatherList;
    }

    public LiveData<ForecastModel> getForecast() {

        if (forecastList == null) {
            forecastList = new MutableLiveData<ForecastModel>();
            loadForecast();
        }
        return forecastList;
    }

    private void loadForecast() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService api = retrofit.create(APIService.class);
        Call<ForecastModel> call = api.getForecast();

        call.enqueue(new Callback<ForecastModel>() {
            @Override
            public void onResponse(Call<ForecastModel> call, Response<ForecastModel> response) {
                forecastList.setValue(response.body());

                weatherRepository.deleteAllForecastWeathers();

                ArrayList<ForecastModel> forecastModels = new ArrayList<>();
                forecastModels.add(response.body());

                for (int i = 0; i < forecastModels.size(); i++) {

                    for (int i1 = 0; i1 < forecastModels.get(i).forecasts.size(); i1++) {

                        MainModel mainModel = forecastModels.get(i).forecasts.get(i1).mainModel;

                        List<WeatherMdl> weather = forecastModels.get(i).forecasts.get(i1).getWeather();


                        for (int i2 = 0; i2 < weather.size(); i2++) {

                            weatherRepository.insertForecastWeather(new ForecastWeather(mainModel.getTemp(), mainModel.getTempMin(), mainModel.getTempMax(), mainModel.getPressure(), mainModel.getHumidity(), weather.get(i2).getMain(), weather.get(i2).getDescription(),forecastModels.get(i).getCity().getName(),forecastModels.get(i).forecasts.get(i1).getDt_txt()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ForecastModel> call, Throwable t) {
            }
        });


    }

    private void loadWeather() {

        Log.e("loadWeather: ","running" );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService api = retrofit.create(APIService.class);
        Call<WeatherModel> call = api.getWeather();
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {

                WeatherModel model = response.body();
                ArrayList<WeatherModel> weatherModels = new ArrayList<>();
                weatherModels.add(model);

                WeatherList.setValue(response.body());

                for (int i = 0; i < weatherModels.size(); i++) {
                    Main main = weatherModels.get(i).main;
                    List<Weather> weatherList = weatherModels.get(i).weather;

                    weatherRepository.deleteAllWeathers();

                    weatherRepository.insert(new Weathers(main.getTemp(), main.getHumidity(), main.getTempmin(), main.getTempmax(), weatherModels.get(i).getName(), weatherList.get(i).getDescription()));

                }
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {

            }
        });
    }

    public void setSelectedForecast(ForecastWeather forecastWeather) {

        selectedForecast.setValue(forecastWeather);

    }
    public MutableLiveData<ForecastWeather> getSelectedForecast() {
        return selectedForecast;
    }
}
