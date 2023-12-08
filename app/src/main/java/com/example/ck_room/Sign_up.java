package com.example.ck_room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ck_room.DataConfig.DatabaseManager;
import com.example.ck_room.DataConfig.MyDatabase;
import com.example.ck_room.Entity.User;

public class Sign_up extends AppCompatActivity {
    EditText name,age,phone,username,pass;
    RadioGroup radio;
    RadioButton radioMale, radioFemale;
    Button back, register;
    private MyDatabase myDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        radio = findViewById(R.id.radioGroup);
        radioMale = findViewById(R.id.radioFemale);
        radioFemale = findViewById(R.id.radioMale);
        back = findViewById(R.id.back);
        register = findViewById(R.id.register);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean flag = true;
                if (name.getText().toString().isEmpty() ||
                        age.getText().toString().isEmpty() ||
                        phone.getText().toString().isEmpty() ||
                        username.getText().toString().isEmpty() ||
                        pass.getText().toString().isEmpty() ||
                        radio.getCheckedRadioButtonId() == -1

                )
                {
                    Toast.makeText(Sign_up.this, "Please filling full information", Toast.LENGTH_SHORT).show();
                    flag = false;
                }

                if( Integer.parseInt(age.getText().toString()) < 0 || Integer.parseInt(age.getText().toString()) > 200 )
                {

                    Toast.makeText(Sign_up.this, "Age must be > 0 and < 200", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
                int age_convert = Integer.parseInt(age.getText().toString());
                String gender_convert = "";

                if(flag)
                {
                    if(radioFemale.isChecked())
                    {
                        gender_convert = radioFemale.getText().toString();
                    }
                    else
                    {
                        gender_convert = radioMale.getText().toString();
                    }
                    Intent resultIntent = new Intent();
                    myDatabase = DatabaseManager.getDatabase(getApplicationContext());
                    User user = new User(username.getText().toString(),pass.getText().toString(),name.getText().toString(),
                    phone.getText().toString(),age_convert,gender_convert);
                    myDatabase.userDao().insert(user);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });





    }
}
