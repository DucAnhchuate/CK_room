package com.example.ck_room.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ck_room.Entity.Train_class;
import com.example.ck_room.Entity.User;

import java.util.List;

@Dao
public interface Train_class_Dao{
    @Insert
    void insert(Train_class trainClass);

    @Update
    void update(Train_class trainClass);

    @Delete
    void delete(Train_class trainClass);
    @Query("SELECT * FROM Train_class")
    List<Train_class> getAllTrainClasses();

    @Query("SELECT * FROM Train_class WHERE train_id = :trainId")
    Train_class getTrainClassById(int trainId);
}
