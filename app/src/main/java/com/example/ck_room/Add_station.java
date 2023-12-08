package com.example.ck_room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ck_room.DataConfig.DatabaseManager;
import com.example.ck_room.DataConfig.MyDatabase;
import com.example.ck_room.Entity.Station;

import java.util.List;

public class Add_station extends AppCompatActivity {
    private static final int REQUEST_CODE = 2;
    EditText edit_id,edit_name;
    Button but_oke, but_update,but_cancel;
    private MyDatabase myDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_station);
        edit_id = findViewById(R.id.id);
        edit_name = findViewById(R.id.name);
        but_oke = findViewById(R.id.ok);
        but_update = findViewById(R.id.update);
        but_cancel = findViewById(R.id.cancel);

        but_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        but_oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_name.getText().toString() != null && edit_id.getText().toString() != null)
                {
                    int id = Integer.parseInt(edit_id.getText().toString());
                    Station station = new Station(id,edit_name.getText().toString());
                    if(station != null)
                    {
                        myDatabase = DatabaseManager.getDatabase(getApplicationContext());
                        myDatabase.stationDao().insert(station);
                        Toast.makeText(Add_station.this, station.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        but_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase = DatabaseManager.getDatabase(getApplicationContext());
                List<Station> list = myDatabase.stationDao().getAllStations();
                String a = "";
                for(int i = 0; i < list.size();i++)
                {
                    a = "" + list.get(1).toString();
                }
                Toast.makeText(Add_station.this, a, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
