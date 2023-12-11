package com.example.ck_room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ck_room.DataConfig.DatabaseManager;
import com.example.ck_room.DataConfig.MyDatabase;
import com.example.ck_room.Entity.Route;
import com.example.ck_room.Entity.Route_has_station;
import com.example.ck_room.Entity.Station;
import com.example.ck_room.Entity.Train;

import java.util.ArrayList;
import java.util.List;

public class Add_route extends AppCompatActivity {
    Button but_oke,but_update,but_cancel;
    private Spinner spinner, spinner2;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter2;
    EditText train_id,station_id,source,source_distance,stop,destination,arrival,departure;

    String trainName = "";
    String stop_station_name = "";
    String trainId = "";
    String stationId = "";
    MyDatabase myDatabase;
    String s = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_route);
        but_oke = findViewById(R.id.ok);
        but_update = findViewById(R.id.update);
        but_cancel = findViewById(R.id.cancel);
        spinner = findViewById(R.id.sourceSpinner);
        spinner2 = findViewById(R.id.desSpinner);

        train_id = findViewById(R.id.train_id);
        station_id = findViewById(R.id.station_id);
        source = findViewById(R.id.source_station);
        source_distance = findViewById(R.id.source_distance);
        stop = findViewById(R.id.stop_number);
        destination = findViewById(R.id.destination);
        arrival = findViewById(R.id.arrival_time);
        departure = findViewById(R.id.departure_time);



        but_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });


        but_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase = DatabaseManager.getDatabase(getApplicationContext());

            }
        });
        myDatabase = DatabaseManager.getDatabase(getApplicationContext());


        List<Train> list = myDatabase.trainDao().getAllTrains();
        List<String> nameTrain = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            nameTrain.add(list.get(i).getTrain_name());
        }


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nameTrain);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = (String) parent.getItemAtPosition(position);
                trainName = ""+ selectedOption;
                for(int i = 0; i < list.size(); i++)
                {
                    if(trainName.toString().equals(list.get(i).getTrain_name()))
                    {
                        Train t = list.get(i);

                        train_id.setText(String.valueOf(list.get(i).getTrain_id()));
                        source.setText(t.getSource_stn());
                        destination.setText(t.getDestination_stn());

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        List<Station> list2 = myDatabase.stationDao().getAllStations();
        List<String> nameStation = new ArrayList<>();

        for (int i = 0; i < list2.size(); i++) {
            nameStation.add(list2.get(i).getName());
        }




        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nameStation);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = (String) parent.getItemAtPosition(position);
                s = "" + selectedOption;
                for(int i = 0; i < list.size(); i++)
                {
                    if(selectedOption.equals(list2.get(i).getName()))
                    {
                        station_id.setText(String.valueOf(list2.get(i).getStationId()));
                        break; // Exit the loop once the station is found

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        but_oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Boolean flag = true;
                if(train_id.getText().toString().isEmpty()||
                        source.getText().toString().isEmpty() ||
                        destination.getText().toString().isEmpty() ||
                        station_id.getText().toString().isEmpty() ||
                        stop.getText().toString().isEmpty() ||
                        source_distance.getText().toString().isEmpty() ||
                        arrival.getText().toString().isEmpty() ||
                        departure.getText().toString().isEmpty()
                )
                {
                    flag = false;
                    Toast.makeText(Add_route.this, "Please filling all information", Toast.LENGTH_SHORT).show();
                }
                if(flag)
                {
                    Route route = new Route(Integer.parseInt(train_id.getText().toString()),
                            s,stop.getText().toString(),
                            arrival.getText().toString(),
                            departure.getText().toString(),
                            source_distance.getText().toString());



                    myDatabase.routeDao().insert(route);
                    Route_has_station routeHasStation = new Route_has_station(Integer.parseInt(train_id.getText().toString()),
                            Integer.parseInt(station_id.getText().toString()),stop.getText().toString());
                    myDatabase.routeHasStationDao().insert(routeHasStation);
                    Toast.makeText(Add_route.this, "success", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
