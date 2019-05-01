package com.sapana.androidapps.planmytrip.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface MyExcursionDao {

    @Query("SELECT * FROM MyExcursion")
    MyExcursion getAll();

    @Insert
    void insert(MyExcursion myExcursion);
}
