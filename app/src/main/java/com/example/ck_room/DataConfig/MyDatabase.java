package com.example.ck_room.DataConfig;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ck_room.DAO.Date_available_Dao;
import com.example.ck_room.DAO.PassengerDao;
import com.example.ck_room.DAO.ReservationDao;
import com.example.ck_room.DAO.RouteDao;
import com.example.ck_room.DAO.Route_has_station_Dao;
import com.example.ck_room.DAO.StationDao;
import com.example.ck_room.DAO.TicketDao;
import com.example.ck_room.DAO.TrainDao;
import com.example.ck_room.DAO.Train_class_Dao;
import com.example.ck_room.DAO.Train_status_Dao;
import com.example.ck_room.DAO.UserDao;
import com.example.ck_room.Entity.Day_available;
import com.example.ck_room.Entity.Passenger;
import com.example.ck_room.Entity.Reservation;
import com.example.ck_room.Entity.Route;
import com.example.ck_room.Entity.Route_has_station;
import com.example.ck_room.Entity.Station;
import com.example.ck_room.Entity.Ticket;
import com.example.ck_room.Entity.Train;
import com.example.ck_room.Entity.Train_class;
import com.example.ck_room.Entity.Train_status;
import com.example.ck_room.Entity.User;

@Database(entities = {User.class, Train.class, Station.class, Passenger.class, Train_class.class
                        , Train_status.class, Ticket.class, Route.class, Route_has_station.class
                        , Day_available.class, Reservation.class}, version = 4)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract TrainDao trainDao();
    public abstract StationDao stationDao();
    public abstract PassengerDao passengerDao();
    public abstract Train_class_Dao trainClassDao();
    public abstract Train_status_Dao trainStatusDao();
    public abstract TicketDao  ticketDao();
    public abstract RouteDao routeDao();
    public abstract Route_has_station_Dao routeHasStationDao();
    public abstract Date_available_Dao dateAvailableDao();
    public abstract ReservationDao reservationDao();









    public static final Migration MIGRATION_1_2 = new Migration(1, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Thực hiện các thay đổi cơ sở dữ liệu nếu cần
        }
    };
}