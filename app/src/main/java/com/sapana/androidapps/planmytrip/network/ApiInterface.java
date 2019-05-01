package com.sapana.androidapps.planmytrip.network;

import com.sapana.androidapps.planmytrip.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("forecast.json")
    Call<Weather>getWeather(@Query("key") String key, @Query("q") String location,
                              @Query("days") Integer days
                              );
    @GET("forecast.json")
    Call<Weather>getWeatherByDate(@Query("key") String key, @Query("q") String location,
                            @Query("date") String date
    );
}