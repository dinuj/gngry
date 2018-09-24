package com.d.gngry;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Currentweather.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Currentweather#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Currentweather extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView city,weather,description,temp,humidity;

    final Handler handler = new Handler();

    boolean firstTime=true;

    public Currentweather() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Currentweather.
     */
    // TODO: Rename and change types and number of parameters
    public static Currentweather newInstance(String param1, String param2) {
        Currentweather fragment = new Currentweather();
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
        View rootView= inflater.inflate(R.layout.fragment_currentweather, container, false);

        city=(TextView)rootView.findViewById(R.id.city);
        weather=(TextView)rootView.findViewById(R.id.cloud);
        description=(TextView)rootView.findViewById(R.id.desr);
        temp=(TextView)rootView.findViewById(R.id.temp);
        humidity=(TextView)rootView.findViewById(R.id.humidity);


        if (firstTime){

        }


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                getCurrentWeatherData();

                handler.postDelayed(this, 30000);
            }
        }, 0);




        return rootView;
    }

    private void getCurrentWeatherData() {


//
        String url = "https://api.openweathermap.org/data/2.5/weather?id=1277333&appid=1b6b5d707e2ce2c6124e0ad44456d8c6";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("onResponse: ", response);

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray jsonArray=jsonObject.getJSONArray("weather");


                    city.setText(jsonObject.getString("name"));
                    weather.setText("City : "+jsonArray.getJSONObject(0).getString("main"));
                    description.setText(jsonArray.getJSONObject(0).getString("description"));
                    temp.setText(jsonObject.getJSONObject("main").getString("temp"));
                    humidity.setText(jsonObject.getJSONObject("main").getString("humidity"));




                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e( "onErrorResponse: ", "ddddd");

            }
        });

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "searchscreen");



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
