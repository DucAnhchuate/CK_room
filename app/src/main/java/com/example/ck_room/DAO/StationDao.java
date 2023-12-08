package com.example.ck_room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ck_room.Entity.Station;

import java.util.List;

@Dao
public interface StationDao {
    @Insert
    void insert(Station station);

    @Update
    void update(Station station);

    @Delete
    void delete(Station station);

    @Query("SELECT * FROM station")
    List<Station> getAllStations();
    @Query("SELECT * FROM station where station_id = :id")
    Station getStationById(int id);

    // Các truy vấn (queries) khác
}