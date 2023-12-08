package com.example.ck_room.custom_view;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ck_room.R;
import com.example.ck_room.Schedule;
import com.example.ck_room.Search;
import com.example.ck_room.User_choose_seat;

import java.util.List;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.ViewHolder2> {

    private List<String> itemList;
    private Context context;

    public CustomAdapter2(List<String> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.just_view_train, parent, false);
        return new ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        String item = itemList.get(position);
        String[] get = item.split("/");
        holder.itemTextView.setText(get[0]);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {
        public TextView itemTextView;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.itemTextView);
        }
    }
}