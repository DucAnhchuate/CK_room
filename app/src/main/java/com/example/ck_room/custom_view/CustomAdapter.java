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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<String> itemList;
    private Context context;

    public CustomAdapter(List<String> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_search_train_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = itemList.get(position);
        String[] get = item.split("/");
        holder.itemTextView.setText(get[0]);
        holder.itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startUserChooseSeatActivity(get[1]);
            }
        });
    }
    private void startUserChooseSeatActivity(String id) {
        Intent intent = new Intent(context, Schedule.class);
            intent.putExtra("id",id);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTextView;
        public Button itemButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.itemTextView);
            itemButton = itemView.findViewById(R.id.itemButton);
        }
    }
}