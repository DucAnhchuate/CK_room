package com.example.ck_room.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "day_available", foreignKeys = {
        @ForeignKey(entity = Train.class,
                parentColumns = "train_id",
                childColumns = "train_id", onDelete = ForeignKey.CASCADE)})
public class Day_available
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int train_id;
    private String day_available;

    public Day_available(int train_id, String day_available) {
        this.train_id = train_id;
        this.day_available = day_available;
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public String getDay_available() {
        return day_available;
    }

    public void setDay_available(String day_available) {
        this.day_available = day_available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Day_available{" +
                "id=" + id +
                ", train_id=" + train_id +
                ", day_available='" + day_available + '\'' +
                '}';
    }
}
