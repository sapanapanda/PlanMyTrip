package com.sapana.androidapps.planmytrip.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MyExcursion.class}, version = 1,exportSchema = false)
public abstract class MyExcursionDatabase  extends RoomDatabase {
    public abstract MyExcursionDao myExcursionDao();
}
