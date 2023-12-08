package com.example.ck_room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ck_room.Entity.Ticket;
import com.example.ck_room.Entity.Train;
import com.example.ck_room.Entity.Train_class;

import java.util.List;

@Dao
public interface TicketDao {
    @Insert
    void insert(Ticket ticket);

    @Update
    void update(Ticket ticket);

    @Delete
    void delete(Ticket ticket);

    @Query("SELECT * FROM Ticket")
    List<Ticket> getAllTickets();

    @Query("SELECT * FROM Ticket WHERE Ticket_No = :ticketNo")
    Ticket getTicketByTicketNo(int ticketNo);

    @Query("SELECT * FROM Ticket WHERE Passenger_Name = :passengerName")
    List<Ticket> getTicketsByPassengerName(String passengerName);
    @Query("SELECT * FROM Train WHERE train_id = :trainId")
    Train getTrainClassById(int trainId);
}