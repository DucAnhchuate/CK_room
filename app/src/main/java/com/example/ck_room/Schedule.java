package com.example.ck_room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ck_room.DataConfig.DatabaseManager;
import com.example.ck_room.DataConfig.MyDatabase;
import com.example.ck_room.Entity.Route;
import com.example.ck_room.Entity.Train;
import com.example.ck_room.custom_view.CustomAdapter;
import com.example.ck_room.custom_view.CustomAdapter2;

import java.util.ArrayList;
import java.util.List;

public class Schedule extends AppCompatActivity {
    final int REQUEST_CODE = 7;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;

    private CustomAdapter2 adapter3;
    private List<String> itemList;
    private List<String> itemList2;

    MyDatabase myDatabase;

    private CustomAdapter2 adapter;
    Button  choose_seat, back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);
        back = findViewById(R.id.back);
        choose_seat = findViewById(R.id.choose_seat);
        myDatabase = DatabaseManager.getDatabase(getApplicationContext());
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        Train t = myDatabase.ticketDao().getTrainClassById(Integer.parseInt(id));
        String s = t.toString();
        itemList = new ArrayList<>();
        itemList.add(s);
        List<Route> routeList= myDatabase.routeDao().getRoutesByTrainId(Integer.parseInt(id));
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(Schedule.this));
        adapter3 = new CustomAdapter2(itemList, Schedule.this);
        recyclerView.setAdapter(adapter3);



        recyclerView2 = findViewById(R.id.recyclerView2);
        itemList2 = new ArrayList<>();
        for (int i = 0; i < routeList.size(); i++) {
            itemList2.add(routeList.get(i).toString());
        }
        recyclerView2.setLayoutManager(new LinearLayoutManager(Schedule.this));

        adapter = new CustomAdapter2(itemList2, Schedule.this);
        recyclerView2.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        choose_seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Schedule.this, User_choose_seat.class);
                in.putExtra("id", id);
                startActivityForResult(in,REQUEST_CODE);

            }
        });
    }

}
