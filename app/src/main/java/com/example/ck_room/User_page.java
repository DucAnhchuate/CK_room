package com.example.ck_room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.example.ck_room.Entity.User;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class User_page extends AppCompatActivity {
    int REQUEST_CODE = 4;
    ImageButton edit,check,buy,back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.user_page1);
        edit = findViewById(R.id.btUser);
        check = findViewById(R.id.btHistory);
        buy = findViewById(R.id.btBooking);
        //back = findViewById(R.id.back);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String dob = intent.getStringExtra("dob");
        String phone = intent.getStringExtra("phone");
        String gender = intent.getStringExtra("gender");
        String username = intent.getStringExtra("username");
        String pass = intent.getStringExtra("pass");
        Log.d("=====",username);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_page.this,User_edit_profile.class);
                intent.putExtra("lastName", lastName);
                intent.putExtra("firstName", firstName);
                intent.putExtra("dob",dob);
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
