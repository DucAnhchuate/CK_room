package com.example.ck_room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ck_room.DataConfig.DatabaseManager;
import com.example.ck_room.DataConfig.MyDatabase;

public class User_edit_profile extends AppCompatActivity {

    EditText phone, first, last;
    Button back,save;
    String[] genderOptions = {"Female", "Male"};

    TextView name, gender;

    MyDatabase myDatabase;
    String selectedGender;
    private void showGenderDialog() {

        int defaultSelectedIndex = getGenderIndex(gender.getText().toString());

        AlertDialog.Builder builder = new AlertDialog.Builder(User_edit_profile.this);
        builder.setTitle("Select Gender")
                .setSingleChoiceItems(genderOptions, defaultSelectedIndex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedGender= genderOptions[which];
                    }
                });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gender.setText(selectedGender);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private int getGenderIndex(String gender) {
        for (int i = 0; i < genderOptions.length; i++) {
            if (genderOptions[i].equalsIgnoreCase(gender)) {
                return i;
            }
        }
        return -1; // Trả về
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit_profile);
        myDatabase = DatabaseManager.getDatabase(getApplicationContext());
        name = findViewById(R.id.txtName);
        phone = findViewById(R.id.edtPhone);
        back = findViewById(R.id.btBack);
        save = findViewById(R.id.btSave);
        gender = findViewById(R.id.edtGe);


        Intent intent = getIntent();
        String nameTemp = intent.getStringExtra("name");
        String phoneTemp = intent.getStringExtra("phone");
        String genderTemp = intent.getStringExtra("gender");
        Log.d("=====",nameTemp);

        name.setText(nameTemp);
        phone.setText(phoneTemp);

//        String[] temp = nameTemp.split("\\s+");
//        String lastTemp = null;
//        for (int i = 0; i <= temp.length - 2; i++){
//            if (i == temp.length - 2){
//                lastTemp = lastTemp + temp[temp.length - 2];
//            }
//            else{
//                lastTemp = lastTemp + temp[i] + " ";
//            }
//        }
//        last.setText(lastTemp);
//
//        first.setText(temp[temp.length - 1]);

        if(genderTemp.equals("Female"))
        {
            gender.setText("Female");
        }
        else
        {
            gender.setText("Male");
        }

        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGenderDialog();
            }
        });
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                User user = myDatabase.userDao().getUserByMail(emailTemp);
//                //user.setUserName(username.getText().toString());
//                //user.setPass(pass.getText().toString());
//                user.setName(last.getText().toString() + " " + first.getText().toString());
//                user.setPhone(phone.getText().toString());
//                user.setGender(gender.getText().toString());
//                //user.setAge(Integer.parseInt(age.getText().toString()));
//
//                myDatabase.userDao().update(user);
//                Intent intent = new Intent();
//
//                setResult(RESULT_OK,intent);
//                finish();
//            }
//        });

    }
}
