package com.example.ck_room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ck_room.Entity.Passenger;
import com.example.ck_room.Entity.Train;

import java.util.List;
@Dao
public interface PassengerDao {
    @Insert
    void insert(Passenger passenger);

    @Update
    void update(Passenger passenger);

    @Delete
    void delete(Passenger passenger);

    @Query("SELECT * FROM passenger")
    List<Passenger> getAllPassengers();

    @Query("SELECT * FROM passenger WHERE pnr = :pnr")
    Passenger getPassengerByPnr(String pnr);

    @Query("SELECT * FROM passenger WHERE train_id = :trainId")
    List<Passenger> getPassengersByTrainId(int trainId);
    @Query("SELECT * FROM Train WHERE train_id = :trainId")
    Train getTrainClassById(int trainId);
}