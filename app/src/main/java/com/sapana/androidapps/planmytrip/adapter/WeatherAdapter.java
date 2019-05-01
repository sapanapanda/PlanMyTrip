package com.sapana.androidapps.planmytrip.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sapana.androidapps.planmytrip.R;
import com.sapana.androidapps.planmytrip.model.Forecast;
import com.sapana.androidapps.planmytrip.model.Forecastday;
import com.sapana.androidapps.planmytrip.weather_list.WeatherListActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {



    private WeatherListActivity weatherListActivity;
    private List<Forecastday> foreCastList;



    public WeatherAdapter(WeatherListActivity weatherListActivity, List<Forecastday> foreCastList) {
        this.weatherListActivity = weatherListActivity;
        this.foreCastList = foreCastList;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_weather_list, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Forecastday forecastday = foreCastList.get(position);
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        String inputDateStr=forecastday.getDate();
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);
        holder.tvDate.setText(outputDateStr);
        holder.tvCondition.setText(forecastday.getDay().getCondition().getText());
        holder.tvMin.setText("min"+ forecastday.getDay().getMintempC().toString() + " \u2103");
        holder.tvMax.setText("max"+forecastday.getDay().getMaxtempC().toString() + " \u2103");

        // loading album cover using Glide library
        Glide.with(weatherListActivity)
                .load("https://"+forecastday.getDay().getCondition().getIcon())
                 .into(holder.ivCondition);

    }

    @Override
    public int getItemCount() {
        return foreCastList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDate;

        public TextView tvCondition;

        public TextView tvMin;

        public  TextView tvMax;


        public ImageView ivCondition;



        public MyViewHolder(View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvCondition = itemView.findViewById(R.id.tvCondition);
            tvMin = itemView.findViewById(R.id.tvMin);
            ivCondition = itemView.findViewById(R.id.ivConditionIcon);
            tvMax = itemView.findViewById(R.id.tvMax);
        }
    }
}
