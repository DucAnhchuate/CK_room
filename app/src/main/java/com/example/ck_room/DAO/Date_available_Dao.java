package com.example.ck_room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ck_room.Entity.Day_available;
import com.example.ck_room.Entity.Train;

import java.util.List;

@Dao

public interface Date_available_Dao {
    @Insert
     void insert(Day_available dayAvailable);

    @Update
    void update(Day_available dayAvailable);

    @Delete
    void delete(Day_available dayAvailable);

    @Query("SELECT * FROM day_available")
    List<Day_available> getAllDayAvailable();

    @Query("SELECT * FROM day_available WHERE train_id = :trainId")
    List<Day_available> getDayAvailableByTrainId(int trainId);
    @Query("SELECT * FROM Train WHERE train_id = :trainId")
    Train getTrainClassById(int trainId);
}