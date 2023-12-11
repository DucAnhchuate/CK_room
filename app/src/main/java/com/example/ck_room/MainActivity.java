package com.example.ck_room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ck_room.DataConfig.DatabaseManager;
import com.example.ck_room.DataConfig.MyDatabase;
import com.example.ck_room.Entity.User;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 0;
    EditText username, pass;
    Button login;
    TextView register;

    boolean passwordVisible;
    private MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_in);
        username = findViewById(R.id.edtFirst);
        pass = findViewById(R.id.edtPassword);
        login = findViewById(R.id.btSignUp);
        register = findViewById(R.id.txtSignIn);

        myDatabase = DatabaseManager.getDatabase(getApplicationContext());

        //Register text
        SpannableString spannableString = new SpannableString("Don't have an account? Sign up here");

        int loginHereStart = "Don't have an account? ".length();

        int loginHereEnd = spannableString.length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Xử lý sự kiện, ví dụ: chuyển hướng đến trang đăng nhập
                Intent intent = new Intent(MainActivity.this, Sign_up.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        };
        spannableString.setSpan(clickableSpan, loginHereStart, loginHereEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), loginHereStart, loginHereEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        register.setText(spannableString);

        register.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());

        //Password toggle
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



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = myDatabase.userDao().getUserByMail(username.getText().toString());
                if(username.getText().toString().equals("admin") && pass.getText().toString().equals("admin"))
                {
                    Intent intent = new Intent(MainActivity.this,Admin_page.class);
                    startActivityForResult(intent,REQUEST_CODE);

                }
                else
                {
                   if(user != null && pass.getText().toString().equals(user.getPass()))
                    {
                        Intent intent = new Intent(MainActivity.this,User_page.class);
                        intent.putExtra("lastName",user.getLastName());
                        intent.putExtra("firstName",user.getFirstName());
                        intent.putExtra("dob",user.getDob() +"");
                        intent.putExtra("phone",user.getPhone());
                        intent.putExtra("gender",user.getGender());
                        intent.putExtra("username",user.getUserName());
                        intent.putExtra("pass",user.getPass());

                        startActivityForResult(intent,REQUEST_CODE);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Error, user not found", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Xử lý kết quả từ Activity đích
                // Dữ liệu kết quả được truyền trong Intent data
            } else if (resultCode == RESULT_CANCELED) {
                // Xử lý trường hợp người dùng hủy bỏ hoặc lỗi
            }
        }
    }


}