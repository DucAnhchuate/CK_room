package com.example.ck_room;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ck_room.DataConfig.DatabaseManager;
import com.example.ck_room.DataConfig.MyDatabase;
import com.example.ck_room.DataConfig.StationAdapter;
import com.example.ck_room.Entity.Station;

import java.util.List;

public class ListStationView extends AppCompatActivity {
    private MyDatabase myDatabase;
    private StationAdapter stationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Access the database through the DatabaseManager
        myDatabase = DatabaseManager.getDatabase(getApplicationContext());

        // Initialize the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve all stations from the database
        List<Station> stationList = myDatabase.stationDao().getAllStations();

        // Update the UI with the retrieved data
        stationAdapter = new StationAdapter(stationList);
        recyclerView.setAdapter(stationAdapter);
    }
}
