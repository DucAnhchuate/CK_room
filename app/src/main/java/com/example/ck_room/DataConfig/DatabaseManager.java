package com.example.ck_room.DataConfig;

import android.content.Context;

import androidx.room.Room;

import com.example.ck_room.DataConfig.MyDatabase;
import com.example.ck_room.Entity.Station;

import java.util.List;

public class DatabaseManager {
    private static MyDatabase database;
    private static final Object lock = new Object();

    public static MyDatabase getDatabase(Context context) {
        if (database == null) {
            synchronized (lock) {
                if (database == null) {
                    database = Room.databaseBuilder(context.getApplicationContext(),
                                    MyDatabase.class, "my-database").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return database;
    }


}
