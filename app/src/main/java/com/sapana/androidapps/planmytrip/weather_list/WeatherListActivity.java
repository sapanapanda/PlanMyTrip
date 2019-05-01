package com.sapana.androidapps.planmytrip.weather_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sapana.androidapps.planmytrip.R;
import com.sapana.androidapps.planmytrip.adapter.WeatherAdapter;
import com.sapana.androidapps.planmytrip.excursion_list.ExcursionListActivity;
import com.sapana.androidapps.planmytrip.excursion_list.MyExcursionDetails;
import com.sapana.androidapps.planmytrip.model.Forecastday;
import com.sapana.androidapps.planmytrip.model.Weather;
import com.sapana.androidapps.planmytrip.weather_details.WeatherListContract;

import java.util.ArrayList;
import java.util.List;



public class WeatherListActivity extends AppCompatActivity implements WeatherListContract.View {


    FloatingActionButton fab;

    WeatherListPresenter presenter;

    List<Forecastday> forecastdayList;
    RecyclerView rvWeatherList;

    WeatherAdapter adapter;
    LinearLayoutManager mLayoutManager;

    LinearLayout llCheck,llExcursions,llMyExcursions;
    TextInputLayout til;
    TextInputEditText etDestination;
    boolean flag;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new WeatherListPresenter(this);
      //  FloatingActionButton fab = findViewById(R.id.fab);
progressBar = findViewById(R.id.progress);
        rvWeatherList = findViewById(R.id.rvWeatherList);
        llCheck = findViewById(R.id.llWeather);
        llExcursions = findViewById(R.id.llExcursions);
        llMyExcursions = findViewById(R.id.llMyExcursions);

      til = findViewById(R.id.tilDestination);
        etDestination = findViewById(R.id.etDestination);

        forecastdayList = new ArrayList<>();
         adapter = new WeatherAdapter(this, forecastdayList);

         mLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvWeatherList.setLayoutManager(mLayoutManager);

        rvWeatherList.setItemAnimator(new DefaultItemAnimator());
        rvWeatherList.setAdapter(adapter);

        llCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forecastdayList.clear();
                adapter.notifyDataSetChanged();
                try {
                    flag = !flag;
                    if (flag) {
                        til.setVisibility(View.VISIBLE);
                        rvWeatherList.setVisibility(View.VISIBLE);
                    } else
                    {   til.setVisibility(View.GONE);
                    rvWeatherList.setVisibility(View.GONE);}
                }catch (Exception e){
                    Log.d("Exc",e.toString());
                }
            }
        });

        llExcursions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ExcursionListActivity.class);
                startActivity(intent);
            }
        });

        llMyExcursions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyExcursionDetails.class);
                startActivity(intent);
            }
        });

        etDestination.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if  ((actionId == EditorInfo.IME_ACTION_GO)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etDestination.getWindowToken(), 0);
                    forecastdayList.clear();
                    adapter.notifyDataSetChanged();
                    if(TextUtils.isEmpty(etDestination.getText()))
                        Toast.makeText(getApplicationContext(),"Please enter destination to search",Toast.LENGTH_SHORT).show();
                    else {

                        presenter.requestDataFromServer(etDestination.getText().toString(), 7);
                    }
                    return true;

                }
                return false;
            }
        });
    }

    @Override
    public void showProgress() {
     progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Forecastday> weatherList) {
        forecastdayList.addAll(weatherList);
        adapter.notifyDataSetChanged();
        rvWeatherList.setVisibility(View.VISIBLE);
    }

    @Override
    public void setDataToWeather(List<Forecastday> forecastdayList) {

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}
