package com.example.ck_room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Admin_page extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    Button butAddTrain,getButAddTrainStatus, butAddRoute,butAddStation, butProfile,butManageTrain;
    Button back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);
        butAddTrain = findViewById(R.id.add_train);
        getButAddTrainStatus = findViewById(R.id.add_train_status);
        butAddRoute = findViewById(R.id.add_route);
        butAddStation = findViewById(R.id.add_station);
        butManageTrain = findViewById(R.id.manage_train);
        butProfile = findViewById(R.id.manage_profile);
        back = findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        butAddTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_page.this,Add_Train.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        butAddStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_page.this,Add_station.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        butAddRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_page.this,Add_route.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });




    }
}
