package com.d.gngry;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private ArrayList<ForecastModel> forecastList;

    private AdapterCallback mAdapterCallback;



    public WeatherAdapter(Wheatherforecast context, ArrayList<ForecastModel> forecastList) {
        this.forecastList = forecastList;

        this.mAdapterCallback=context;
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list, parent, false);

        return new ViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, final int position) {

//        holder.cloudMain.setText(forecastList.get(position).getWeatherMain());
//
//        holder.cloudDesr.setText(forecastList.get(position).getWeatherdescr());
        holder.date.setText(forecastList.get(position).getDate());


        holder.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapterCallback.onMethodCallback(position,forecastList);

            }
        });


    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cloudMain,cloudDesr,date;

        public ViewHolder(View itemView) {
            super(itemView);

//            cloudDesr=(TextView)itemView.findViewById(R.id.cloud_desr);
//            cloudMain=(TextView)itemView.findViewById(R.id.cloud_main);
            date=(TextView)itemView.findViewById(R.id.date);


        }
    }
}
