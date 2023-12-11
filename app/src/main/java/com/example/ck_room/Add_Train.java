package com.example.ck_room;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ck_room.DataConfig.DatabaseManager;
import com.example.ck_room.DataConfig.MyDatabase;
import com.example.ck_room.Entity.Day_available;
import com.example.ck_room.Entity.Station;
import com.example.ck_room.Entity.Train;
import com.example.ck_room.Entity.Train_class;
import com.example.ck_room.Entity.Train_status;
import com.example.ck_room.Entity.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Add_Train extends AppCompatActivity {
    private static  final int REQUEST_CODE = 3;
    private Spinner edtDepart, edtArrive;
    private ArrayAdapter<String> adapter;
    MyDatabase myDatabase;
    Button btCreate;
    EditText edtName,edtEcoFare,edtFirstFare,edtBusFare,
            edtEcoPass,edtBusPass,edtFirstPass;

    TextView edtDate, edtStart, edtEnd;
    String sourceId = "",desId = "";
    // Declare a member variable
    private Calendar selectedDate = Calendar.getInstance();
    private int selectedHour, selectedMinute;
    private int source_station_id = 0 , destination_station_id = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_train1);
        myDatabase = DatabaseManager.getDatabase(getApplicationContext());

        edtDepart = findViewById(R.id.edtDepart);
        edtArrive = findViewById(R.id.edtArrive);

        edtName = findViewById(R.id.edtName);

        edtDate = findViewById(R.id.edtDate);

        edtEnd = findViewById(R.id.edtEnd);
        edtStart = findViewById(R.id.edtStart);

        edtEcoPass = findViewById(R.id.edtEcoPass);
        edtBusPass = findViewById(R.id.edtBusPass);
        edtFirstPass = findViewById(R.id.edtFirstPass);

        edtBusFare = findViewById(R.id.edtBusFare);
        edtFirstFare = findViewById(R.id.edtFirstFare);
        edtEcoFare = findViewById(R.id.edtEcoFare);
        btCreate =findViewById(R.id.btCreate);


        edtEcoPass.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        edtBusPass.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        edtFirstPass.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);


        List<Station> list = myDatabase.stationDao().getAllStations();
        List<String> nameStation = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            nameStation.add(list.get(i).getName());
        }

//        if (!list.isEmpty()) {
//            source_station_id = String.valueOf(list.get(0).getStationId());
//            destination_station_id = String.valueOf(list.get(0).getStationId());
//            Log.d("Initial Station ID", source_station_id);
//            Log.d("Initial Station ID", destination_station_id);
//
//        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nameStation);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtDepart.setAdapter(adapter);
        edtDepart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = (String) parent.getItemAtPosition(position);
                sourceId = ""+ selectedOption;
                for(int i = 0; i < list.size(); i++)
                {
                     if(sourceId.toString().equals(list.get(i).getName()))
                    {
                        source_station_id = list.get(i).getStationId();
                        Log.d("source ID", String.valueOf(source_station_id));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì khi không chọn gì
            }
        });


        edtArrive.setAdapter(adapter);
        edtArrive.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = (String) parent.getItemAtPosition(position);
                desId = "" +selectedOption;
                for(int i = 0; i < list.size(); i++)
                {
                    if(desId.toString().equals(list.get(i).getName()))
                    {
                        destination_station_id = list.get(i).getStationId();
                        Log.d("des ID", String.valueOf(source_station_id));
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Add_Train.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                selectedDate.set(Calendar.YEAR, year);
                                selectedDate.set(Calendar.MONTH, month);
                                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                String selectedDateString = dateFormat.format(selectedDate.getTime());
                                edtDate.setText(selectedDateString);
                            }
                        },
                        selectedDate.get(Calendar.YEAR),
                        selectedDate.get(Calendar.MONTH),
                        selectedDate.get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });
        edtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // Tạo TimePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Add_Train.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Xử lý khi thời gian được chọn
                                selectedHour = hourOfDay;
                                selectedMinute = minute;

                                // Hiển thị thời gian trên TextView
                                String formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                                edtStart.setText(formattedTime);
                            }
                        },
                        hour,
                        minute,
                        true
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.setTitle("Select a time");
                timePickerDialog.show();
            }
        });
        edtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // Tạo TimePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Add_Train.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Xử lý khi thời gian được chọn
                                selectedHour = hourOfDay;
                                selectedMinute = minute;

                                // Hiển thị thời gian trên TextView
                                String formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                                edtEnd.setText(formattedTime);
                            }
                        },
                        hour,
                        minute,
                        true
                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.setTitle("Select a time");
                timePickerDialog.show();
            }
        });

        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean flag = true;
                if(edtName.getText().toString().isEmpty() ||
                        source_station_id == 0||
                        destination_station_id == 0||
                        edtBusFare.getText().toString().isEmpty()||
                        edtFirstFare.getText().toString().isEmpty()||
                        edtEcoFare.getText().toString().isEmpty() ||
                        edtBusPass.getText().toString().isEmpty()||
                        edtFirstPass.getText().toString().isEmpty()||
                        edtEcoPass.getText().toString().isEmpty() ||
                        edtStart.getText().toString().isEmpty()||
                        edtEnd.getText().toString().isEmpty()

                )
                {
                    flag = false;
                    Toast.makeText(Add_Train.this, "Please fill all information", Toast.LENGTH_SHORT).show();
                }
                if(source_station_id == destination_station_id)
                {
                    flag = false;
                    Toast.makeText(Add_Train.this, "Departure and arrival locations must be different", Toast.LENGTH_SHORT).show();

                }
                if(flag)
                {

                    Train train = new Train(edtName.getText().toString(),sourceId,desId, source_station_id
                            ,destination_station_id);
                   long id = myDatabase.trainDao().insert(train);

                    Day_available dayAvailable = new Day_available((int)id, edtDate.getText().toString());
                    myDatabase.dateAvailableDao().insert(dayAvailable);
                    Train_class trainClass = new Train_class((int) id,
                            Double.parseDouble(edtBusFare.getText().toString()),
                            Integer.parseInt(edtBusPass.getText().toString()),
                            Double.parseDouble(edtFirstFare.getText().toString()),
                            Integer.parseInt(edtFirstPass.getText().toString()),
                            Double.parseDouble(edtEcoFare.getText().toString()),
                            Integer.parseInt(edtEcoPass.getText().toString()));
                    myDatabase.trainClassDao().insert(trainClass);
                    Train_status trainStatus = new Train_status((int)id, edtDate.getText().toString(),0,0,
                            Integer.parseInt(edtEcoPass.getText().toString()) +
                                    Integer.parseInt(edtBusPass.getText().toString())+
                                    Integer.parseInt(edtFirstPass.getText().toString()));
                    myDatabase.trainStatusDao().insert(trainStatus);
                    Toast.makeText(Add_Train.this, "Success", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
