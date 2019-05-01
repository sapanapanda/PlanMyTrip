package com.sapana.androidapps.planmytrip.excursion_list;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sapana.androidapps.planmytrip.R;
import com.sapana.androidapps.planmytrip.database.DatabaseClient;
import com.sapana.androidapps.planmytrip.database.MyExcursion;
import com.sapana.androidapps.planmytrip.model.Excursion;
import com.sapana.androidapps.planmytrip.model.Forecast;
import com.sapana.androidapps.planmytrip.model.Forecastday;
import com.sapana.androidapps.planmytrip.weather_details.WeatherListContract;

import java.util.Calendar;
import java.util.List;

public class ExcursionDetailsActivity extends AppCompatActivity implements WeatherListContract.View {

    private int pYear;
    private int pMonth;
    private int pDay;
    static final int DATE_DIALOG_ID = 1;

    Calendar calendar = Calendar.getInstance();

    TextView tvDate;


     TextView tvCondition;

     TextView tvMin;

      TextView tvMax;
      TextView tvDetails,tvPrice;


     ImageView ivCondition,ivLocation;
     String location;

Excursion excursion;

    ExcursionDetailsPresenter presenter;

    FloatingActionButton fabSave;

String strWUrl;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion_details);
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
        presenter = new ExcursionDetailsPresenter(this);
        excursion = getIntent().getParcelableExtra("Excursion");

        fabSave = findViewById(R.id.fabSave);
        location = excursion.getName();

        tvDetails.setText(getResources().getString(excursion.getDetails()));

        tvPrice.setText("Total Cost : "+excursion.getPrice().concat("Pounds"));


        // loading album cover using Glide library
        Glide.with(this)
                .load(excursion.getExcursionImage())
                .into(ivLocation);

       final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(calendar.MONTH);
        pDay = cal.get(calendar.DAY_OF_MONTH);
        updateDateDisplay();
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ExcursionDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                tvDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                presenter.requestWeatherData(location,tvDate.getText().toString());
                            }
                        }, pYear, pMonth, pDay);
                datePickerDialog.show();


                datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());




            }
        });

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyExcursion myExcursion = new MyExcursion(location,excursion.getExcursionImage(),excursion.getDetails(),
                        excursion.getPrice(),tvCondition.getText().toString(),strWUrl,tvDate.toString(),
                        tvMin.getText().toString(),tvMax.getText().toString());
                saveData(myExcursion);
            }
        });

    }

    private void updateDateDisplay() {
        tvDate.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(pDay).append("/").append(pMonth + 1).append("/")
                .append(pYear));
    }
public  void saveData(final MyExcursion myExcursion)
{
new Thread(new Runnable() {
    @Override
    public void run() {
        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                .myExcursionDao()
                .insert(myExcursion);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"Excursion saved",Toast.LENGTH_SHORT).show();
            }
        });
    }
}).start();

}

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToRecyclerView(List<Forecastday> forecastdayList) {

    }

    @Override
    public void setDataToWeather(List<Forecastday> forecastdayList) {
        Forecastday forecastday = forecastdayList.get(0);
        tvCondition.setText(forecastday.getDay().getCondition().getText());
        tvMin.setText("min"+ forecastday.getDay().getMintempC().toString() + " \u2103");
        tvMax.setText("max"+forecastday.getDay().getMaxtempC().toString() + " \u2103");

        // loading album cover using Glide library
        Glide.with(this)
                .load("https://"+forecastday.getDay().getCondition().getIcon())
                .into(ivCondition);

        strWUrl = "https://"+forecastday.getDay().getCondition().getIcon();

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.share) {

            String strDetails = "";
                            strDetails = "Location Name : " + getSupportActionBar().getTitle() + "\n" +
                                    "Details : " + tvDetails.getText().toString() + "\n"
                        + " Price : " + tvPrice.getText().toString() ;


            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/html");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, strDetails);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));

            }
        return super.onOptionsItemSelected(item);
    }
}
