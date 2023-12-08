package com.example.ck_room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ck_room.Entity.Route_has_station;
import com.example.ck_room.Entity.Train;

import java.util.List;

@Dao
public interface Route_has_station_Dao {
    @Insert
    void insert(Route_has_station routeHasStation);

    @Update
    void update(Route_has_station routeHasStation);

    @Delete
    void delete(Route_has_station routeHasStation);

    @Query("SELECT * FROM route_has_station")
    List<Route_has_station> getAllRouteHasStations();

    @Query("SELECT * FROM route_has_station WHERE train_id = :trainId")
    List<Route_has_station> getRouteHasStationsByTrainId(int trainId);

    @Query("SELECT * FROM route_has_station WHERE station_id = :stationId")
    List<Route_has_station> getRouteHasStationsByStationId(int stationId);

    @Query("SELECT * FROM route_has_station WHERE train_id = :trainId AND station_id = :stationId")
    Route_has_station getRouteHasStation(int trainId, int stationId);

    @Query("SELECT * FROM route_has_station WHERE station_id IN (:stationIds)")
    List<Route_has_station> getRouteHasStationsByStationIds(List<Integer> stationIds);
    @Query("SELECT * FROM Train WHERE train_id = :trainId")
    Train getTrainClassById(int trainId);

}