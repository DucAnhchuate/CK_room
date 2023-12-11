package com.example.ck_room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ck_room.DataConfig.DatabaseManager;
import com.example.ck_room.DataConfig.MyDatabase;
import com.example.ck_room.Entity.Day_available;
import com.example.ck_room.Entity.Station;
import com.example.ck_room.Entity.Train;
import com.example.ck_room.custom_view.CustomAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Search extends AppCompatActivity {
    int REQUEST_CODE = 5;

    private Spinner spinner, spinner2;
     EditText date;
    private ArrayAdapter<String> adapter;
    private Calendar selectedDate = Calendar.getInstance();

    MyDatabase myDatabase;
    private String selec1 = "",selec2 = "";
    Button search,back;
    private RecyclerView recyclerView;
    private CustomAdapter adapter3;
    private List<String> itemList;
    private String send;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        spinner = findViewById(R.id.sourceSpinner);
        spinner2 = findViewById(R.id.desSpinner);
        date = findViewById(R.id.date_avail);
        search = findViewById(R.id.find);
        back = findViewById(R.id.back);

        myDatabase = DatabaseManager.getDatabase(getApplicationContext());


        List<Station> list = myDatabase.stationDao().getAllStations();
        List<String> nameStation = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            nameStation.add(list.get(i).getName());
        }


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nameStation);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = (String) parent.getItemAtPosition(position);
                selec1 = "" +selectedOption;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì khi không chọn gì
            }
        });



        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = (String) parent.getItemAtPosition(position);
                selec2 = ""+selectedOption;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button datePickerButton = findViewById(R.id.datePickerButton);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Search.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                selectedDate.set(Calendar.YEAR, year);
                                selectedDate.set(Calendar.MONTH, month);
                                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                String selectedDateString = dateFormat.format(selectedDate.getTime());
                                date.setText(selectedDateString);
                            }
                        },
                        selectedDate.get(Calendar.YEAR),
                        selectedDate.get(Calendar.MONTH),
                        selectedDate.get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList = new ArrayList<>();
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(Search.this));
                List<Train> list = myDatabase.trainDao().findTrainsByStationsAndDay(selec1,selec2,date.getText().toString());
                List<Day_available> d = myDatabase.dateAvailableDao().getAllDayAvailable();

                for(int i = 0; i < list.size();i++)
                {
                    itemList.add(list.get(i).toString());
                }
                if(itemList.size() == 0)
                {
                    Toast.makeText(Search.this, d.size() +"", Toast.LENGTH_SHORT).show();

                }

                adapter3 = new CustomAdapter(itemList, Search.this);
                recyclerView.setAdapter(adapter3);

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }

   
}
