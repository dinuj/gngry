package com.d.gngry;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Wheatherforecast.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Wheatherforecast#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Wheatherforecast extends Fragment implements AdapterCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<ForecastModel> forecastList = new ArrayList<>();

    private RecyclerView recyclerView;

    private WeatherAdapter mAdapter;


    public Wheatherforecast() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Wheatherforecast.
     */
    // TODO: Rename and change types and number of parameters
    public static Wheatherforecast newInstance(String param1, String param2) {
        Wheatherforecast fragment = new Wheatherforecast();
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
        View rootView = inflater.inflate(R.layout.fragment_wheatherforecast, container, false);


        getWeatherForecast();


        recyclerView = (RecyclerView)rootView.findViewById(R.id.my_recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return rootView;
    }

    private void getWeatherForecast() {

        forecastList.clear();


        String url = "http://api.openweathermap.org/data/2.5/forecast?id=1277333&APPID=1b6b5d707e2ce2c6124e0ad44456d8c6";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("onResponse: ", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
//                    System.out.println("dddd" + jsonObject);

                    System.out.println("array : " + jsonObject.getJSONArray("list").length());




                    for (int i = 0; i < jsonObject.getJSONArray("list").length(); i++) {

                        System.out.println("hhghghghgh"+jsonObject.getJSONArray("list").getJSONObject(i).getString("dt_txt"));


                        for (int i1 = 0; i1 < jsonObject.getJSONArray("list").getJSONObject(i).getJSONArray("weather").length(); i1++) {
                            ForecastModel forecastModel = new ForecastModel();
                            forecastModel.setWeatherMain(jsonObject.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(i1).getString("main"));
                            forecastModel.setWeatherdescr(jsonObject.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(i1).getString("description"));
                            forecastModel.setDate(jsonObject.getJSONArray("list").getJSONObject(i).getString("dt_txt"));

                            forecastList.add(forecastModel);
                        }

                    }


                    mAdapter = new WeatherAdapter(Wheatherforecast.this,forecastList);

                    recyclerView.setAdapter(mAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("onErrorResponse: ", "ddddd");

            }
        });

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "searchscreen");

    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
////            mListener.onFragmentInteraction(uri);
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
    public void onMethodCallback(int position, ArrayList<ForecastModel> forecastList) {
        mListener.onFragmentInteraction(position,forecastList);

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
        void onFragmentInteraction(int action, ArrayList<ForecastModel> selectedItemCode);
    }
}
