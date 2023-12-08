package com.example.ck_room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ck_room.DataConfig.DatabaseManager;
import com.example.ck_room.DataConfig.MyDatabase;
import com.example.ck_room.Entity.User;

public class User_edit_profile extends AppCompatActivity {

    EditText name,age,phone,username,pass;
    Button back,edit;
    RadioGroup radio;
    RadioButton radioMale, radioFemale;
    MyDatabase myDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit_profile);
        myDatabase = DatabaseManager.getDatabase(getApplicationContext());
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        back = findViewById(R.id.back);
        edit = findViewById(R.id.edit);

        radio = findViewById(R.id.radioGroup);
        radioMale = findViewById(R.id.radioFemale);
        radioFemale = findViewById(R.id.radioMale);

        Intent intent = getIntent();
        String name1 = intent.getStringExtra("name");
        String age2 = intent.getStringExtra("age");
        String phone3 = intent.getStringExtra("phone");
        String gender4 = intent.getStringExtra("gender");
        String username5 = intent.getStringExtra("username");
        String pass6 = intent.getStringExtra("pass");
        Log.d("=====",name1);

        name.setText(name1);
        age.setText(age2);
        phone.setText(phone3);
        username.setText(username5);
        pass.setText(pass6);
        if(gender4.equals("Female"))
        {
            radioFemale.setChecked(true);
        }
        else
        {
            radioMale.setChecked(true);
        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender_convert = "" ;
                if(radioMale.isChecked())
                {
                    gender_convert = "" + radioFemale.getText().toString();
                }
                else
                {
                    gender_convert = "" + radioMale.getText().toString();

                }


                User user = myDatabase.userDao().getUserByMail(username5);
                user.setUserName(username.getText().toString());
                user.setPass(pass.getText().toString());
                user.setName(name.getText().toString());
                user.setPhone(phone.getText().toString());
                user.setGender(gender_convert);
                user.setAge(Integer.parseInt(age.getText().toString()));

                myDatabase.userDao().update(user);
                Intent intent = new Intent();

                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}
