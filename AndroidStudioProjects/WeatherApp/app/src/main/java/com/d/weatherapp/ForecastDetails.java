package com.d.weatherapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.d.weatherapp.ForecastRoom.ForecastWeather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ForecastDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ForecastDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForecastDetails extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView mDate, mTime, mPlace, mTemp, mMain, mDesc, mHumidity, mPressure, mTempMax, mTempMin;

    private OnFragmentInteractionListener mListener;

    public ForecastDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForecastDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static ForecastDetails newInstance(String param1, String param2) {
        ForecastDetails fragment = new ForecastDetails();
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
        View rootView = inflater.inflate(R.layout.fragment_forecast_details, container, false);


        mDate = (TextView) rootView.findViewById(R.id.m_date);
        mTime = (TextView) rootView.findViewById(R.id.m_time);
        mPlace = (TextView) rootView.findViewById(R.id.m_place);
        mTemp = (TextView) rootView.findViewById(R.id.m_temp);
        mMain = (TextView) rootView.findViewById(R.id.m_main);
        mDesc = (TextView) rootView.findViewById(R.id.m_desc);
        mHumidity = (TextView) rootView.findViewById(R.id.m_humidity);
        mPressure = (TextView) rootView.findViewById(R.id.m_pressure);
        mTempMax = (TextView) rootView.findViewById(R.id.m_tempmax);
        mTempMin = (TextView) rootView.findViewById(R.id.m_tempmin);

        WeatherViewModel model = ViewModelProviders.of(getActivity()).get(WeatherViewModel.class);

        model.getSelectedForecast().observe(this, new Observer<ForecastWeather>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(@Nullable ForecastWeather forecastWeather) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd h:mm:ss");

                String sDate = "";
                String sTime = "";

                try {
                    Date date = simpleDateFormat.parse(forecastWeather.getWdate());
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



                mDate.setText(sDate);
                mTime.setText(sTime);
                mPlace.setText(forecastWeather.getPlace());
                mTemp.setText(String.valueOf(((int) forecastWeather.getTemp())));
                mTemp.append(getString(R.string.temp));
                mMain.setText(String.valueOf(forecastWeather.getMain()));
                mDesc.setText(forecastWeather.getDescription());
                mHumidity.setText("Humidity: "+String.valueOf(forecastWeather.getHumidity()));
                mPressure.setText("Pressure "+String.valueOf(forecastWeather.getPressure()));
                mTempMax.setText("Temp max: "+String.valueOf(forecastWeather.getTempMax()));
                mTempMin.setText("Temp min: "+String.valueOf(forecastWeather.getTempMin()));

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
