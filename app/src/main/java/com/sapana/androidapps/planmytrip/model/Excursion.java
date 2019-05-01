package com.sapana.androidapps.planmytrip.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Excursion implements Parcelable {
    private String name;
    private int excursionImage;
    private int details;
    private String price;

    public Excursion(String name, int excursionImage, int details, String price) {
        this.name = name;
        this.excursionImage = excursionImage;
        this.details = details;
        this.price = price;
    }

    protected Excursion(Parcel in) {
        name = in.readString();
        excursionImage = in.readInt();
        details = in.readInt();
        price = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(excursionImage);
        dest.writeInt(details);
        dest.writeString(price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Excursion> CREATOR = new Creator<Excursion>() {
        @Override
        public Excursion createFromParcel(Parcel in) {
            return new Excursion(in);
        }

        @Override
        public Excursion[] newArray(int size) {
            return new Excursion[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getExcursionImage() {
        return excursionImage;
    }

    public int getDetails() {
        return details;
    }

    public String getPrice() {
        return price;
    }
}
