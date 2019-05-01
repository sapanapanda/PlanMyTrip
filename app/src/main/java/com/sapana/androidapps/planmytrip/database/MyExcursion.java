package com.sapana.androidapps.planmytrip.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class MyExcursion implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int excursionImage;
    private int details;
    private String price;
    private String condition;
    private String Icon;
    private String Wdate;
    private String Min;
    private String Max;

    public MyExcursion(String name, int excursionImage, int details, String price, String condition, String Icon, String Wdate, String Min, String Max) {
        this.name = name;
        this.excursionImage = excursionImage;
        this.details = details;
        this.price = price;
        this.condition = condition;
        Icon = Icon;
        Wdate = Wdate;
        Min = Min;
        Max = Max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExcursionImage() {
        return excursionImage;
    }

    public void setExcursionImage(int excursionImage) {
        this.excursionImage = excursionImage;
    }

    public int getDetails() {
        return details;
    }

    public void setDetails(int details) {
        this.details = details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String wIcon) {
        this.Icon = wIcon;
    }

    public String getWdate() {
        return Wdate;
    }

    public void setWdate(String wDate) {
        this.Wdate = wDate;
    }

    public String getMin() {
        return Min;
    }

    public void setMin(String wMin) {
        this.Min = wMin;
    }

    public String getMax() {
        return Max;
    }

    public void setMax(String wMax) {
        this.Max = wMax;
    }
}
