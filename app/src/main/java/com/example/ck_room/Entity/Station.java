package com.example.ck_room.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "station")
public class Station {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "station_id")
    private int stationId;

    @ColumnInfo(name = "name")
    private String name;

    public Station(int stationId, String name) {
        this.stationId = stationId;
        this.name = name;
    }
    public Station()
    {

    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationId=" + stationId +
                ", name='" + name + '\'' +
                '}';
    }
}