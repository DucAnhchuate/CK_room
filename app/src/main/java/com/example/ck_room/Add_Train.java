package com.example.ck_room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Add_Train extends AppCompatActivity {
    private static  final int REQUEST_CODE = 3;
    private Spinner spinner, spinner2;
    private ArrayAdapter<String> adapter;
    MyDatabase myDatabase;
    Button but_oke,but_update,but_cancel;
    EditText train_name,fare1,fare2,fare3,
            number_seat1,number_seat2,number_seat3, date_avail;
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2,radioButton3;
    String sourceId = "",desId = "";
    // Declare a member variable
    private Calendar selectedDate = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_train);
        myDatabase = DatabaseManager.getDatabase(getApplicationContext());
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        train_name = findViewById(R.id.train_name);
        final EditText source_station_id = findViewById(R.id.source_station_id);
        final EditText destination_station_id = findViewById(R.id.destination_station_id);
        fare1 = findViewById(R.id.fare1);
        fare2 = findViewById(R.id.fare2);
        fare3 = findViewById(R.id.fare3);
        number_seat1 = findViewById(R.id.number_seat1);
        number_seat2 = findViewById(R.id.number_seat2);
        number_seat3 = findViewById(R.id.number_seat3);
        radioGroup = findViewById(R.id.radio);
        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);


        but_oke = findViewById(R.id.ok);
        but_update = findViewById(R.id.update);
        but_cancel = findViewById(R.id.cancel);

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
                sourceId = ""+ selectedOption;
                for(int i = 0; i < list.size(); i++)
                {
                    if(sourceId.toString().equals(list.get(i).getName()))
                    {
                        source_station_id.setText(String.valueOf(list.get(i).getStationId()));
                    }
                }
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
                desId = "" +selectedOption;
                for(int i = 0; i < list.size(); i++)
                {
                    if(desId.toString().equals(list.get(i).getName()))
                    {
                        destination_station_id.setText(String.valueOf(list.get(i).getStationId()));
                    }
                }

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
                        Add_Train.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                selectedDate.set(Calendar.YEAR, year);
                                selectedDate.set(Calendar.MONTH, month);
                                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                date_avail = findViewById(R.id.date_avail);
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                String selectedDateString = dateFormat.format(selectedDate.getTime());
                                date_avail.setText(selectedDateString);
                            }
                        },
                        selectedDate.get(Calendar.YEAR),
                        selectedDate.get(Calendar.MONTH),
                        selectedDate.get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });

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

                Boolean flag = true;
                if(train_name.getText().toString().isEmpty() ||
                        source_station_id.getText().toString().isEmpty()||
                        destination_station_id.getText().toString().isEmpty()||
                        fare1.getText().toString().isEmpty()||
                        fare2.getText().toString().isEmpty()||
                        fare3.getText().toString().isEmpty() ||
                        number_seat1.getText().toString().isEmpty()||
                        number_seat2.getText().toString().isEmpty()||
                        number_seat3.getText().toString().isEmpty() ||
                        radioGroup.getCheckedRadioButtonId() == -1 ||
                        date_avail.getText().toString().isEmpty()

                )
                {
                    flag = false;
                    Toast.makeText(Add_Train.this, "Please filling all information", Toast.LENGTH_SHORT).show();
                }
                if(source_station_id.getText().toString().equals(destination_station_id.getText().toString()))
                {
                    flag = false;
                    Toast.makeText(Add_Train.this, "Duplicate source id and destination id", Toast.LENGTH_SHORT).show();

                }
                if(flag)
                {
                    String train_type_convert = "";
                    if(radioButton1.isChecked())
                    {
                        train_type_convert = radioButton1.getText().toString();
                    }
                    else if(radioButton2.isChecked())
                    {
                        train_type_convert = radioButton2.getText().toString();
                    }
                    else
                    {
                        train_type_convert = radioButton3.getText().toString();

                    }
                    Train train = new Train(train_name.getText().toString(),train_type_convert
                            ,sourceId,desId,Integer.parseInt(source_station_id.getText().toString())
                            ,Integer.parseInt(destination_station_id.getText().toString()));
                   long id = myDatabase.trainDao().insert(train);

                    Day_available dayAvailable = new Day_available((int)id,date_avail.getText().toString());
                    myDatabase.dateAvailableDao().insert(dayAvailable);
                    Train_class trainClass = new Train_class((int) id,
                            Double.parseDouble(fare1.getText().toString()),
                            Integer.parseInt(number_seat1.getText().toString()),
                            Double.parseDouble(fare2.getText().toString()),
                            Integer.parseInt(number_seat2.getText().toString()),
                            Double.parseDouble(fare3.getText().toString()),
                            Integer.parseInt(number_seat3.getText().toString()));
                    myDatabase.trainClassDao().insert(trainClass);
                    Train_status trainStatus = new Train_status((int)id,date_avail.getText().toString(),0,0,
                            Integer.parseInt(number_seat1.getText().toString()) +
                                    Integer.parseInt(number_seat2.getText().toString())+
                                    Integer.parseInt(number_seat3.getText().toString()));
                    myDatabase.trainStatusDao().insert(trainStatus);
                    Toast.makeText(Add_Train.this, "Success", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
