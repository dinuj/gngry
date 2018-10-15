package com.d.weatherapp;


import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.d.weatherapp.ForecastRoom.ForecastWeather;


public class MainActivity extends AppCompatActivity implements
        CurrentWeather.OnFragmentInteractionListener,
        Weatherforecast.OnFragmentInteractionListener,
        ForecastDetails.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CurrentWeather(), "Currentweather");
        adapter.addFragment(new Weatherforecast(), "Wheatherforecast");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(String fragmentName) {
        if (fragmentName.equals("ForecastDetails")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ForecastDetails()).addToBackStack("ForecastDetails").commit();

        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

