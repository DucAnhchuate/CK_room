package com.example.ck_room.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import androidx.room.ForeignKey;


@Entity(tableName = "Train",
        foreignKeys = {
                @ForeignKey(entity = Station.class,
                        parentColumns = "station_id",
                        childColumns = "Source_ID"),
                @ForeignKey(entity = Station.class,
                        parentColumns = "station_id",
                        childColumns = "Destination_ID")
        })
public class Train {
    @PrimaryKey(autoGenerate = true)
    private int train_id;
    private String Train_name;
    private String Source_stn;
    private String Destination_stn;
    private int Source_ID;
    private int Destination_ID;

    // Constructor
    public Train(String Train_name, String Source_stn, String Destination_stn, int Source_ID, int Destination_ID) {
        this.Train_name = Train_name;
        this.Source_stn = Source_stn;
        this.Destination_stn = Destination_stn;
        this.Source_ID = Source_ID;
        this.Destination_ID = Destination_ID;
    }

    // Getters and setters
    public int getTrain_ID() {
        return train_id;
    }

    public void setTrain_ID(int Train_ID) {
        this.train_id = Train_ID;
    }

    public String getTrain_name() {
        return Train_name;
    }

    public void setTrain_name(String Train_name) {
        this.Train_name = Train_name;
    }


    public String getSource_stn() {
        return Source_stn;
    }

    public void setSource_stn(String Source_stn) {
        this.Source_stn = Source_stn;
    }

    public String getDestination_stn() {
        return Destination_stn;
    }

    public void setDestination_stn(String Destination_stn) {
        this.Destination_stn = Destination_stn;
    }

    public int getSource_ID() {
        return Source_ID;
    }

    public void setSource_ID(int Source_ID) {
        this.Source_ID = Source_ID;
    }

    public int getDestination_ID() {
        return Destination_ID;
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public void setDestination_ID(int Destination_ID) {
        this.Destination_ID = Destination_ID;
    }

    @Override
    public String toString() {
        return
              Train_name  +
                "         " + Source_stn +
                "         " + Destination_stn +"/" +train_id;


    }
}