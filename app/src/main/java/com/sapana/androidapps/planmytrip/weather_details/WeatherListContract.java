package com.sapana.androidapps.planmytrip.weather_details;

import com.sapana.androidapps.planmytrip.database.MyExcursion;
import com.sapana.androidapps.planmytrip.model.Excursion;
import com.sapana.androidapps.planmytrip.model.Forecastday;
import com.sapana.androidapps.planmytrip.model.Weather;

import java.util.List;

public interface WeatherListContract {

        interface Model {

            interface OnFinishedListener {
                void onFinished(List<Forecastday> weatherList);

                void onFailure(Throwable t);
            }

            void getWeatherList(OnFinishedListener onFinishedListener,String location,
                                int days);

            void getWeatherByDate(OnFinishedListener onFinishedListener,String location,
                                String date);


        }

        interface View {

            void showProgress();

            void hideProgress();

            void setDataToRecyclerView(List<Forecastday> forecastdayList);

            void setDataToWeather(List<Forecastday>forecastdayList);

            void onResponseFailure(Throwable throwable);

        }

        interface Presenter {

            void onDestroy();

            void requestWeatherData(String location,String date);

            void requestDataFromServer(String location, int day);



        }
    }