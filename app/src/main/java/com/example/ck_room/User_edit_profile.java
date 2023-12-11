package com.example.ck_room;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ck_room.DataConfig.DatabaseManager;
import com.example.ck_room.DataConfig.MyDatabase;
import com.example.ck_room.Entity.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class User_edit_profile extends AppCompatActivity {
    int REQUEST_CODE = 20;

    EditText phone, first, last;
    Button back,save, logout;
    String[] genderOptions = {"Female", "Male"};

    TextView name, gender, dob;
    private Calendar selectedDate = Calendar.getInstance();

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
        first = findViewById(R.id.edtFirst);
        last = findViewById(R.id.edtLast);
        phone = findViewById(R.id.edtPhone);
        back = findViewById(R.id.btBack);
        save = findViewById(R.id.btSave);
        dob = findViewById(R.id.edtDob);
        gender = findViewById(R.id.edtGe);
        name = findViewById(R.id.txtName);
        logout = findViewById(R.id.btLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_edit_profile.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivityForResult(intent,REQUEST_CODE);
                // Finish all activities in the stack
                finishAffinity();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        Intent intent = getIntent();
        String userTemp = intent.getStringExtra("username");

        User user = myDatabase.userDao().getUserByMail(userTemp);

        name.setText(user.getLastName() + " " + user.getFirstName());
        phone.setText(user.getPhone());
        dob.setText(user.getDob());
        last.setText(user.getLastName());
        first.setText(user.getFirstName());

        if(user.getGender().equals("Female"))
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
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        User_edit_profile.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                selectedDate.set(Calendar.YEAR, year);
                                selectedDate.set(Calendar.MONTH, month);
                                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                String selectedDateString = dateFormat.format(selectedDate.getTime());
                                dob.setText(selectedDateString);
                            }
                        },
                        selectedDate.get(Calendar.YEAR),
                        selectedDate.get(Calendar.MONTH),
                        selectedDate.get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = myDatabase.userDao().getUserByMail(userTemp);

                user.setLastName(last.getText().toString());
                user.setFirstName(first.getText().toString());
                user.setPhone(phone.getText().toString());
                user.setGender(gender.getText().toString());
                user.setDob(dob.getText().toString());

                myDatabase.userDao().update(user);

                Toast.makeText(User_edit_profile.this,"Update Succesfully", Toast.LENGTH_SHORT).show();

                Intent updatedIntent = new Intent(User_edit_profile.this, User_edit_profile.class);
                updatedIntent.putExtra("username", userTemp);

                // Finish the current activity
                finish();

                // Start the activity with the new intent
                startActivity(updatedIntent);
          }
        });

    }
}
