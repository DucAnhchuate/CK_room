package com.example.ck_room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ck_room.Entity.Reservation;
import com.example.ck_room.Entity.Train;
import com.example.ck_room.Entity.Train_class;

import java.util.List;

@Dao
public interface ReservationDao {

    @Insert
    void insert(Reservation reservation);

    @Update
    void update(Reservation reservation);

    @Delete
    void delete(Reservation reservation);

    @Query("SELECT * FROM Reservation")
    List<Reservation> getAllReservations();

    @Query("SELECT * FROM Reservation WHERE id = :id")
    Reservation getReservationById(int id);
    @Query("SELECT * FROM Reservation WHERE train_id_class = :trainId")
    List<Reservation> getReservationsByTrainId(int trainId);

    @Query("SELECT * FROM Reservation WHERE EmailID = :emailId")
    List<Reservation> getReservationsByEmailId(String emailId);

    @Query("SELECT * FROM Reservation WHERE Reservation_Status = :status")
    List<Reservation> getReservationsByStatus(String status);

    @Query("SELECT * FROM Reservation WHERE Available_Date = :date")
    List<Reservation> getReservationsByDate(String date);
    @Query("SELECT * FROM Train_class WHERE train_id = :trainId")
    Train_class getTrainClassById(int trainId);
}