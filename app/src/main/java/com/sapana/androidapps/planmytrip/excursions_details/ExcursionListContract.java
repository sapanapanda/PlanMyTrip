package com.sapana.androidapps.planmytrip.excursions_details;

import com.sapana.androidapps.planmytrip.model.Excursion;
import com.sapana.androidapps.planmytrip.model.Forecastday;
import com.sapana.androidapps.planmytrip.weather_details.WeatherListContract;

import java.util.List;

public interface ExcursionListContract {
    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Excursion> excursionList);

            void onFailure(Throwable t);
        }

        void getExcursionList(ExcursionListContract.Model.OnFinishedListener onFinishedListener);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Excursion> excursionList);

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();
        void requestData();

    }
}
