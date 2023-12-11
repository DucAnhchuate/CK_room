package com.example.ck_room;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ck_room.DataConfig.DatabaseManager;
import com.example.ck_room.DataConfig.MyDatabase;
import com.example.ck_room.Entity.User;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

public class Sign_up extends AppCompatActivity {
    EditText first,last,phone,username,pass;
    RadioGroup radio;
    RadioButton radioMale, radioFemale;
    Button signUp;
    TextView signIn, dob;

    boolean passwordVisible;

    private Calendar selectedDate = Calendar.getInstance();

    private MyDatabase myDatabase;


    private void aler(String string){
        AlertDialog.Builder builder = new AlertDialog.Builder(Sign_up.this);
        builder.setTitle("Warning");
        builder.setMessage(string);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle positive button click
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public int calculateAge(int birthYear, LocalDate currentDate) {
        return currentDate.getYear()  - birthYear;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        first = findViewById(R.id.edtFirst);
        last = findViewById(R.id.edtLast);
        dob = findViewById(R.id.edtDob);
        phone = findViewById(R.id.edtPhone);
        username = findViewById(R.id.edtEmail);
        pass = findViewById(R.id.edtPassword);
        radio = findViewById(R.id.radioGr);
        radioMale = findViewById(R.id.radioM);
        radioFemale = findViewById(R.id.radioF);
        signIn = findViewById(R.id.txtSignIn);
        signUp = findViewById(R.id.btSignUp);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Sign_up.this,
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
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int Right = 2;

                if (event.getAction() == MotionEvent.ACTION_UP){

                    if (event.getRawX() >= pass.getRight() - pass.getCompoundDrawables()[Right].getBounds().width()){

                        int selection = pass.getSelectionEnd();

                        if (passwordVisible){

                            pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off_24, 0);

                            pass.setTransformationMethod(PasswordTransformationMethod.getInstance());

                            passwordVisible = false;
                        }
                        else{

                            pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_24, 0);

                            pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());


                            passwordVisible = true;
                        }
                        pass.setSelection(selection);

                        return true;
                    }
                }
                return  false;
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean flag = true;
                if (last.getText().toString().isEmpty() ||
                        first.getText().toString().isEmpty()||
                        dob.getText().toString().isEmpty() ||
                        phone.getText().toString().isEmpty() ||
                        username.getText().toString().isEmpty() ||
                        pass.getText().toString().isEmpty() ||
                        radio.getCheckedRadioButtonId() == -1

                )
                {
                    aler("Please fill in all information to complete the registration. Thank you!");
                    flag = false;

                }

//                else if(age < 18)
//                {
//                    aler("Age must be >= 18");
//                    flag = false;
//                }
                String gender = "";
                //if ()


                if(flag)
                {
                    String name = last.getText().toString() + " " + first.getText().toString();
                    if(radioFemale.isChecked())
                    {
                        gender = radioFemale.getText().toString();
                    }
                    else
                    {
                        gender = radioMale.getText().toString();
                    }
                    Intent resultIntent = new Intent();
                    myDatabase = DatabaseManager.getDatabase(getApplicationContext());

                    User user = new User(username.getText().toString(),pass.getText().toString(),
                                                    phone.getText().toString(), dob.getText().toString(), gender, first.getText().toString(), last.getText().toString());

                    myDatabase.userDao().insert(user);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}
