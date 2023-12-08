package com.example.ck_room.DataConfig;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteDatabase extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Xóa cơ sở dữ liệu
        Context context = this;
        context.deleteDatabase("my-database");

        // Tiếp tục xử lý logic của ứng dụng
    }
}
