package com.example.ck_room.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "route_has_station",
        foreignKeys = {
                @ForeignKey(entity = Station.class,
                        parentColumns = "station_id",
                        childColumns = "station_id",
                        onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Train.class,
                        parentColumns = "train_id",
                        childColumns = "train_id",
                        onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE)
        })
public class Route_has_station {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int train_id;
    private int station_id;
    private String stop_number;


    public Route_has_station(int train_id, int station_id, String stop_number) {
        this.train_id = train_id;
        this.station_id = station_id;
        this.stop_number = stop_number;
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStop_number() {
        return stop_number;
    }

    public void setStop_number(String stop_number) {
        this.stop_number = stop_number;
    }
}
