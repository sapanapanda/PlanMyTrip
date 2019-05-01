package com.sapana.androidapps.planmytrip.weather_list;

import android.util.Log;

import com.sapana.androidapps.planmytrip.model.Weather;
import com.sapana.androidapps.planmytrip.network.ApiClient;
import com.sapana.androidapps.planmytrip.network.ApiInterface;
import com.sapana.androidapps.planmytrip.weather_details.WeatherListContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sapana.androidapps.planmytrip.network.ApiClient.API_KEY;

public class WeatherListModel implements WeatherListContract.Model {


        private final String TAG = "WeatherListModel";


        /**
         * This function will fetch movies data
         *

         */
        @Override
        public void getWeatherList(final OnFinishedListener onFinishedListener, String location,
                                   int days) {

            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<Weather> call = apiService.getWeather(API_KEY, location,days);
            call.enqueue(new Callback<Weather>() {
                @Override
                public void onResponse(Call<Weather> call, Response<Weather> response) {
                    Weather weather = response.body();
                  //  Log.d(TAG, "Data received: " + response.body().toString());
                    onFinishedListener.onFinished(weather.getForecast().getForecastday());
                }

                @Override
                public void onFailure(Call<Weather> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                    onFinishedListener.onFailure(t);
                }
            });
        }

    @Override
    public void getWeatherByDate(final OnFinishedListener onFinishedListener, String location, String date) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Weather> call = apiService.getWeatherByDate(API_KEY, location,date);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather = response.body();
                //  Log.d(TAG, "Data received: " + response.body().toString());
                if(weather != null)
                onFinishedListener.onFinished(weather.getForecast().getForecastday());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }


}