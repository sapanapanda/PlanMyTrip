package com.sapana.androidapps.planmytrip.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sapana.androidapps.planmytrip.R;
import com.sapana.androidapps.planmytrip.excursion_list.ExcursionDetailsActivity;
import com.sapana.androidapps.planmytrip.excursion_list.ExcursionListActivity;
import com.sapana.androidapps.planmytrip.model.Excursion;
import com.sapana.androidapps.planmytrip.model.Forecastday;
import com.sapana.androidapps.planmytrip.weather_list.WeatherListActivity;

import java.util.List;

public class ExcursionAdapter extends RecyclerView.Adapter<ExcursionAdapter.MyViewHolder> {



    private ExcursionListActivity excursionListActivity;
    private List<Excursion> excursionList;



    public ExcursionAdapter(ExcursionListActivity excursionListActivity, List<Excursion> excursionList) {
        this.excursionListActivity = excursionListActivity;
        this.excursionList = excursionList;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_excursion, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Excursion excursion = excursionList.get(position);

        holder.tvName.setText(excursion.getName());


        // loading album cover using Glide library
        Glide.with(excursionListActivity)
                .load(excursion.getExcursionImage())
                .into(holder.ivImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ExcursionDetailsActivity.class);
                intent.putExtra("Excursion",excursion);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return excursionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivImage;

        public TextView tvName;




        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvName = itemView.findViewById(R.id.tvName);

        }

        @Override
        public void onClick(View view) {

        }
    }
}