package com.d.weatherapp;

import android.content.res.Resources;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.d.weatherapp.ForecastPojo.ForecastModel;
import com.d.weatherapp.ForecastRoom.ForecastWeather;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<ForecastWeather> forecastWeathers = new ArrayList<>();

    AdapterCallback mAdapterCallback;


    public WeatherAdapter(Weatherforecast weatherforecast, List<ForecastWeather> forecastWeathers) {

        this.forecastWeathers = forecastWeathers;
        this.mAdapterCallback=weatherforecast;
    }

    public WeatherAdapter(Weatherforecast weatherforecast, ForecastModel forecastModel) {

    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list, parent, false);

        return new ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, final int position) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd h:mm:ss");

        String sDate = "";
        String sTime = "";

        try {
            Date date = simpleDateFormat.parse(forecastWeathers.get(position).getWdate());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            sDate = calendar.get(Calendar.DAY_OF_MONTH) + "/"
                    + calendar.get(Calendar.MONTH)
                    + "/" + calendar.get(Calendar.YEAR);


            simpleDateFormat = new SimpleDateFormat("hh:mm a");

            sTime = simpleDateFormat.format(calendar.getTime());




        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.mDate.setText(sDate);
        holder.mTime.setText(sTime);
        holder.mPlace.setText(forecastWeathers.get(position).getPlace());
        holder.mTemp.setText(String.valueOf(((int) forecastWeathers.get(position).getTemp())));
        holder.mTemp.append("\u2103");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapterCallback.CallBack(forecastWeathers.get(position));
            }
        });

        holder.mMain.setText(forecastWeathers.get(position).getMain());

    }

    @Override
    public int getItemCount() {
        return forecastWeathers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTime, mPlace, mTemp, mDate,mMain;

        public ViewHolder(View itemView) {
            super(itemView);
            mDate = (TextView) itemView.findViewById(R.id.mdate);
            mTime = (TextView) itemView.findViewById(R.id.mtime);
            mPlace = (TextView) itemView.findViewById(R.id.mplace);
            mTemp = (TextView) itemView.findViewById(R.id.mtemp);
            mMain = (TextView) itemView.findViewById(R.id.mMain);



        }
    }
}
//    String sDate = calendar.get(Calendar.YEAR) + "-"
//            + c.get(Calendar.MONTH)
//            + "-" + c.get(Calendar.DAY_OF_MONTH)
//            + " at " + c.get(Calendar.HOUR_OF_DAY)
//            + ":" + c.get(Calendar.MINUTE);
//
