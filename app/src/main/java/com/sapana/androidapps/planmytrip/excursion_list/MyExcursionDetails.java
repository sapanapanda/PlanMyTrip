package com.sapana.androidapps.planmytrip.excursion_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sapana.androidapps.planmytrip.R;
import com.sapana.androidapps.planmytrip.database.DatabaseClient;
import com.sapana.androidapps.planmytrip.database.MyExcursion;

public class MyExcursionDetails extends AppCompatActivity {
    TextView tvDate;


    TextView tvCondition;

    TextView tvMin;

    TextView tvMax;
    TextView tvDetails,tvPrice;


    ImageView ivCondition,ivLocation;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_excursion_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvDate = findViewById(R.id.tvDate);
        tvCondition = findViewById(R.id.tvCondition);
        tvMin = findViewById(R.id.tvMin);
        tvMax = findViewById(R.id.tvMax);
        ivCondition = findViewById(R.id.ivConditionIcon);
        ivLocation = findViewById(R.id.ivImage);
        tvDetails = findViewById(R.id.tvDetails);
        tvPrice = findViewById(R.id.tvPrice);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final MyExcursion myExcursion = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .myExcursionDao()
                        .getAll();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(myExcursion != null){

                        tvDate.setText(myExcursion.getWdate());
                        tvCondition.setText(myExcursion.getCondition());
                        tvDetails.setText(myExcursion.getDetails());
                        tvMin.setText(myExcursion.getMin());
                        tvMax.setText(myExcursion.getMax());
                        tvPrice.setText(myExcursion.getPrice());
                        getSupportActionBar().setTitle(myExcursion.getName());
                            Glide.with(MyExcursionDetails.this)
                                    .load(myExcursion.getIcon())
                                    .into(ivCondition);
                            Glide.with(MyExcursionDetails.this)
                                    .load(myExcursion.getExcursionImage())
                                    .into(ivLocation);

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"You  have not saved any excursions",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();


    }

}
