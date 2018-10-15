package com.d.weatherapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.d.weatherapp.ForecastPojo.ForecastModel;
import com.d.weatherapp.ForecastRoom.ForecastWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Weatherforecast.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Weatherforecast#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Weatherforecast extends Fragment implements AdapterCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    WeatherAdapter mAdapter;

    public Weatherforecast() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Weatherforecast.
     */
    // TODO: Rename and change types and number of parameters
    public static Weatherforecast newInstance(String param1, String param2) {
        Weatherforecast fragment = new Weatherforecast();
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
        View rootView= inflater.inflate(R.layout.fragment_weatherforecast, container, false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        final WeatherViewModel model = ViewModelProviders.of(this).get(WeatherViewModel.class);


        model.getForecast().observe(this, new Observer<ForecastModel>() {
            @Override
            public void onChanged(@Nullable ForecastModel forecastModel) {


            }
        });

        WeatherViewModel viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        viewModel.getAllForecastWeather().observe(this, new Observer<List<ForecastWeather>>() {
            @Override
            public void onChanged(@Nullable List<ForecastWeather> forecastWeathers) {

                System.out.println(" forecastWeathers database size : "+forecastWeathers.size());

                mAdapter = new WeatherAdapter(Weatherforecast.this,forecastWeathers);
                recyclerView.setAdapter(mAdapter);
            }
        });



        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

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

    @Override
    public void CallBack(ForecastWeather forecastWeather) {

        WeatherViewModel viewModel=ViewModelProviders.of(getActivity()).get(WeatherViewModel.class);

        viewModel.setSelectedForecast(forecastWeather);

        mListener.onFragmentInteraction("ForecastDetails");
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
        void onFragmentInteraction(String fragmentName);
    }
}
