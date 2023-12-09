package com.example.ck_room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ck_room.Entity.User;

public class User_page extends AppCompatActivity {
    int REQUEST_CODE = 4;
    Button edit,check,buy,back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.user_page);
        edit = findViewById(R.id.edit_profile);
        check = findViewById(R.id.check_pnr);
        buy = findViewById(R.id.buy_ticket);
        back = findViewById(R.id.back);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String phone = intent.getStringExtra("phone");
        String gender = intent.getStringExtra("gender");
        String username = intent.getStringExtra("username");
        String pass = intent.getStringExtra("pass");
        Log.d("=====",name);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_page.this,User_edit_profile.class);
                intent.putExtra("name",name);
                intent.putExtra("age",age);
                intent.putExtra("phone",phone);
                intent.putExtra("gender",gender);
                intent.putExtra("username",username);
                intent.putExtra("pass",pass);

                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_page.this,Search.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }



}
