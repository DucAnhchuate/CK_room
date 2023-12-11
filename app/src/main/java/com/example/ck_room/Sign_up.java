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

import java.time.LocalDate;
import java.util.Calendar;

public class Sign_up extends AppCompatActivity {
    EditText first,last,phone,username,pass;
    RadioGroup radio;
    RadioButton radioMale, radioFemale;
    Button signUp;
    TextView signIn, dateDialog;

    boolean passwordVisible;

    int age= 0;
    DatePickerDialog datePickerDialog;
    private MyDatabase myDatabase;

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String date = makeDateString(dayOfMonth, month, year);
                dateDialog.setText(date);
                age = calculateAge(Integer.parseInt(dateDialog.getText().toString().substring(dateDialog.getText().toString().length() - 4))
                        ,LocalDate.now());
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    public void openDatePicker(View view){
        datePickerDialog.show();
    }
    private String makeDateString(int day, int month, int year){
        return getMonthFormat(month) + " " + day + " " + year;
    }
    private String getMonthFormat(int month){
        if (month == 1){
            return "JAN";
        }
        if (month == 2){
            return "FEB";
        }
        if (month == 3){
            return "MAR";
        }
        if (month == 4){
            return "APR";
        }
        if (month == 5){
            return "MAY";
        }
        if (month == 6){
            return "JUN";
        }
        if (month == 7){
            return "JUL";
        }
        if (month == 8){
            return "AUG";
        }
        if (month == 9){
            return "SEP";
        }
        if (month == 10){
            return "OCT";
        }
        if (month == 11){
            return "NOV";
        }
        if (month == 12){
            return "DEC";
        }
        return "JAN";
    }

    private String getTodaysDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month ++;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

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
        dateDialog = findViewById(R.id.dob2);
        phone = findViewById(R.id.edtPhone);
        username = findViewById(R.id.edtEmail);
        pass = findViewById(R.id.edtPassword);
        radio = findViewById(R.id.radioGr);
        radioMale = findViewById(R.id.radioM);
        radioFemale = findViewById(R.id.radioF);
        signIn = findViewById(R.id.txtSignIn);
        signUp = findViewById(R.id.btSignUp);

        initDatePicker();
        dateDialog.setText(getTodaysDate());

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
                        dateDialog.getText().toString().isEmpty() ||
                        phone.getText().toString().isEmpty() ||
                        username.getText().toString().isEmpty() ||
                        pass.getText().toString().isEmpty() ||
                        radio.getCheckedRadioButtonId() == -1

                )
                {
                    aler("Please fill in all information to complete the registration. Thank you!");
                    flag = false;

                }

                else if(age < 18)
                {
                    aler("Age must be >= 18");
                    flag = false;
                }
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

                    User user = new User(username.getText().toString(),pass.getText().toString(),name,
                                                    phone.getText().toString(), age, gender);

                    myDatabase.userDao().insert(user);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}
