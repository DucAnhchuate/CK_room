package com.example.ck_room.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "route",foreignKeys = {
        @ForeignKey(entity = Train.class,
                parentColumns = "train_id",
                childColumns = "train_id",
                onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE)
})
public class Route {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "train_id")
    private int train_id;

    @ColumnInfo(name = "stop_number")
    private String stop_number;
    private String station;
    private String arrival_time;
    private String departure_time;

    @ColumnInfo(name = "source")
    private String source;

    public Route()
    {

    }

    public Route( int train_id, String stop_number, String station, String arrival_time, String departure_time, String source) {
        this.train_id = train_id;
        this.stop_number = stop_number;
        this.station = station;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public String getStop_number() {
        return stop_number;
    }

    public void setStop_number(String stop_number) {
        this.stop_number = stop_number;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return
                 stop_number +"         "
                + getStation() + "              "
               + arrival_time +"             "+ departure_time +"                        "+ source ;
    }
}
