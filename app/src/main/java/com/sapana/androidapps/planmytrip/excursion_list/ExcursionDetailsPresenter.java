package com.sapana.androidapps.planmytrip.excursion_list;

import com.sapana.androidapps.planmytrip.model.Forecastday;
import com.sapana.androidapps.planmytrip.weather_details.WeatherListContract;
import com.sapana.androidapps.planmytrip.weather_list.WeatherListModel;

import java.util.List;

public class ExcursionDetailsPresenter implements WeatherListContract.Presenter, WeatherListContract.Model.OnFinishedListener {

    private WeatherListContract.View view;

    private WeatherListContract.Model model;

    public ExcursionDetailsPresenter(WeatherListContract.View view) {
        this.view = view;
        model = new WeatherListModel();
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public void requestWeatherData(String location, String date) {
        model.getWeatherByDate(this,location,date);
    }


    @Override
    public void requestDataFromServer(String location, int day) {

    }

    @Override
    public void onFinished(List<Forecastday> weatherList) {
         view.setDataToWeather(weatherList);
    }

    @Override
    public void onFailure(Throwable t) {

    }

}
