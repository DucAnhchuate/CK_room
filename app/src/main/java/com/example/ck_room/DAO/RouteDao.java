package com.example.ck_room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ck_room.Entity.Route;
import com.example.ck_room.Entity.Train;

import java.util.List;

@Dao
public interface RouteDao {
    @Insert
    void insert(Route route);

    @Update
    void update(Route route);

    @Delete
    void delete(Route route);

    @Query("SELECT * FROM route")
    List<Route> getAllRoutes();

    @Query("SELECT * FROM route WHERE train_id = :trainId")
    List<Route> getRoutesByTrainId(int trainId);
    @Query("SELECT * FROM Train WHERE train_id = :trainId")
    Train getTrainClassById(int trainId);


    @Query("SELECT * FROM route WHERE id = :id")
    Route getRouteById(int id);


    @Query("SELECT * FROM route WHERE source = :source")
    List<Route> getRoutesBySource(String source);

}