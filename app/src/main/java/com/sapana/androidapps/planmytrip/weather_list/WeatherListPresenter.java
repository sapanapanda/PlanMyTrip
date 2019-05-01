package com.sapana.androidapps.planmytrip.weather_list;

import com.sapana.androidapps.planmytrip.model.Forecastday;
import com.sapana.androidapps.planmytrip.weather_details.WeatherListContract;

import java.util.List;

public class WeatherListPresenter implements WeatherListContract.Presenter, WeatherListContract.Model.OnFinishedListener {


    private WeatherListContract.View view;

    private WeatherListContract.Model model;

    public WeatherListPresenter(WeatherListContract.View view) {
        this.view = view;
        model = new WeatherListModel();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void requestWeatherData(String location, String date) {

    }


    @Override
    public void requestDataFromServer(String location,int day) {
        view.showProgress();
       model.getWeatherList(this,location,day);


    }

    @Override
    public void onFinished(List<Forecastday> forecastdayList) {
        view.hideProgress();
        view.setDataToRecyclerView(forecastdayList);

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
