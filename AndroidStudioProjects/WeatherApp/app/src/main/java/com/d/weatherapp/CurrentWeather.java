package com.d.weatherapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.d.weatherapp.Room.Weathers;
import com.d.weatherapp.WeatherPojo.Main;
import com.d.weatherapp.WeatherPojo.Weather;
import com.d.weatherapp.WeatherPojo.WeatherModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentWeather.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentWeather#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentWeather extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView temp, weather, humidity, temp_max, temp_min, place, desc;


    public CurrentWeather() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentWeather.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentWeather newInstance(String param1, String param2) {
        CurrentWeather fragment = new CurrentWeather();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_current_weather, container, false);

        temp = (TextView) rootView.findViewById(R.id.temp);
        weather = (TextView) rootView.findViewById(R.id.weather);
        humidity = (TextView) rootView.findViewById(R.id.humidity);
        temp_max = (TextView) rootView.findViewById(R.id.temp_max);
        temp_min = (TextView) rootView.findViewById(R.id.temp_min);
        place = (TextView) rootView.findViewById(R.id.place);
        desc = (TextView) rootView.findViewById(R.id.desc);

        final WeatherViewModel model = ViewModelProviders.of(this).get(WeatherViewModel.class);
        model.getWeather().observe(this, new Observer<WeatherModel>() {
            @Override
            public void onChanged(@Nullable WeatherModel weatherModel) {

            }
        });

        model.getAllWeathers().observe(this, new Observer<List<Weathers>>() {
            @Override
            public void onChanged(@Nullable List<Weathers> weathers) {

                System.out.println(" currentweathers database size : "+weathers.size());

                for (int i = 0; i < weathers.size(); i++) {
                    System.out.println("Room database : "+weathers.get(i).getName());

                    place.setText(weathers.get(i).getName());
                    desc.setText(weathers.get(i).getDescription());
                    humidity.setText(String.valueOf(weathers.get(i).getHumidity()));
                    temp_max.setText(String.valueOf(weathers.get(i).getTempmax()));
                    temp_min.setText(String.valueOf(weathers.get(i).getTempmin()));
                    temp.setText(String.valueOf(((int) weathers.get(i).getTemp())));
                    temp.append(getString(R.string.temp));

                }

            }
        });


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
