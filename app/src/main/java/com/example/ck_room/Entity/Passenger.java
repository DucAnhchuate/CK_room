package com.example.ck_room.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "passenger",    foreignKeys = {
        @ForeignKey(entity = Train.class,
                parentColumns = "train_id",
                childColumns = "train_id", onDelete = ForeignKey.CASCADE)})
public class Passenger {
    @PrimaryKey
    @NonNull
    private String pnr;
    private int seatNumber;
    private String passengerName;
    private int age;
    private String gender;
    private int train_id;
    private String origin;

    // Constructor


    public Passenger(String pnr, int seatNumber, String passengerName, int age, String gender, int train_id, String origin) {
        this.pnr = pnr;
        this.seatNumber = seatNumber;
        this.passengerName = passengerName;
        this.age = age;
        this.gender = gender;
        this.train_id = train_id;
        this.origin = origin;
    }

    // Getters and setters
    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getTrainID() {
        return train_id;
    }

    public void setTrainID(int trainID) {
        this.train_id = trainID;
    }

    public String getOrigin() {
        return origin;
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}