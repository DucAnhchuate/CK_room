package com.example.ck_room.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "train_status", foreignKeys = {
        @ForeignKey(entity = Train.class, parentColumns = "train_id", childColumns = "train_id",
                onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE)
})
public class Train_status {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int train_id;
    private String available_date;
    private int blocked_seats;
    private int waiting_seats;
    private int available_seats;


    // Constructor
    public Train_status(int train_id, String available_date, int blocked_seats, int waiting_seats, int available_seats) {
        this.train_id = train_id;
        this.available_date = available_date;
        this.blocked_seats = blocked_seats;
        this.waiting_seats = waiting_seats;
        this.available_seats = available_seats;
    }

    // Getters and setters
    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public String getAvailable_date() {
        return available_date;
    }

    public void setAvailable_date(String available_date) {
        this.available_date = available_date;
    }

    public int getBlocked_seats() {
        return blocked_seats;
    }

    public void setBlocked_seats(int blocked_seats) {
        this.blocked_seats = blocked_seats;
    }

    public int getWaiting_seats() {
        return waiting_seats;
    }

    public void setWaiting_seats(int waiting_seats) {
        this.waiting_seats = waiting_seats;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}