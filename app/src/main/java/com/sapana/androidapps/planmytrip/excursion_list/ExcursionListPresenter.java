package com.sapana.androidapps.planmytrip.excursion_list;

import com.sapana.androidapps.planmytrip.excursions_details.ExcursionListContract;
import com.sapana.androidapps.planmytrip.model.Excursion;
import com.sapana.androidapps.planmytrip.weather_details.WeatherListContract;
import com.sapana.androidapps.planmytrip.weather_list.WeatherListModel;

import java.util.List;

public class ExcursionListPresenter implements ExcursionListContract.Presenter,ExcursionListContract.Model.OnFinishedListener {

        private ExcursionListContract.View view;

        private ExcursionListContract.Model model;

    public ExcursionListPresenter(ExcursionListContract.View view) {
            this.view = view;
            model = new ExcursionListModel();
    }


    @Override
    public void onDestroy() {
        
    }

    @Override
    public void requestData() {
     model.getExcursionList(this);
    }

    @Override
    public void onFinished(List<Excursion> excursionList) {
        view.setDataToRecyclerView(excursionList);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
