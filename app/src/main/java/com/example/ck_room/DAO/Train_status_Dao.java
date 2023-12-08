package com.example.ck_room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ck_room.Entity.Train_status;

import java.util.List;

@Dao
public interface Train_status_Dao {
    @Insert
    void insert(Train_status trainStatus);

    @Update
    void update(Train_status trainStatus);

    @Delete
    void delete(Train_status trainStatus);

    @Query("SELECT * FROM train_status")
    List<Train_status> getAllTrainStatus();

    @Query("SELECT * FROM train_status WHERE train_id = :trainId")
    Train_status getTrainStatusById(int trainId);
}
