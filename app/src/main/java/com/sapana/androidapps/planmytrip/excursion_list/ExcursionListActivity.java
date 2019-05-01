package com.sapana.androidapps.planmytrip.excursion_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sapana.androidapps.planmytrip.R;
import com.sapana.androidapps.planmytrip.adapter.ExcursionAdapter;
import com.sapana.androidapps.planmytrip.excursions_details.ExcursionListContract;
import com.sapana.androidapps.planmytrip.model.Excursion;
import com.sapana.androidapps.planmytrip.weather_list.WeatherListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ExcursionListActivity extends AppCompatActivity implements ExcursionListContract.View {

    RecyclerView rvExcursionList;
    ExcursionAdapter adapter;
    List<Excursion> excursionList;
    ExcursionListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion_list);
        rvExcursionList = (RecyclerView)findViewById(R.id.rvExcursion);
        rvExcursionList.setLayoutManager(new GridLayoutManager(ExcursionListActivity.this,2));
        presenter = new ExcursionListPresenter(this);
        excursionList = new ArrayList<>();

        adapter = new ExcursionAdapter(this,excursionList);
        rvExcursionList.setItemAnimator(new DefaultItemAnimator());
        rvExcursionList.setAdapter(adapter);
        presenter.requestData();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToRecyclerView(List<Excursion> excursionList) {
        this.excursionList.addAll(excursionList);
       //
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}
