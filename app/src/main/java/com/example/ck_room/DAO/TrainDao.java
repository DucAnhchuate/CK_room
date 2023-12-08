package com.example.ck_room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ck_room.Entity.Train;

import java.util.List;

@Dao
public interface TrainDao {
    @Insert
    long insert(Train train);

    @Update
    void update(Train train);

    @Delete
    void delete(Train train);

    @Query("SELECT * FROM train")
    List<Train> getAllTrains();

    @Query("SELECT * FROM Train " +
            "JOIN Day_available ON Train.train_id = Day_available.train_id " +
            "WHERE Train.Source_stn = :sourceStn " +
            "AND Train.Destination_stn = :destinationStn " +
            "AND Day_available.day_available = :dayAvailable")
    List<Train> findTrainsByStationsAndDay(String sourceStn, String destinationStn, String dayAvailable);}