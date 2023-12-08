package com.example.ck_room.custom_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ck_room.R;
import com.example.ck_room.custom_view.CustomAdapter;
import com.example.ck_room.custom_view.Seat;

import java.util.List;

public class CustomAdapter3 extends RecyclerView.Adapter<CustomAdapter3.ViewHolder3> {
    private List<Seat> itemList;
    private Context context;


    public CustomAdapter3(List<Seat> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat, parent, false);
        return new ViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder3 holder, int position) {
        Seat item = itemList.get(position);

        holder.text1.setText(item.getText1());
        holder.text2.setText(item.getText2());
        holder.text3.setText(item.getText3());
        holder.text4.setText(item.getText4());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }



    public static class ViewHolder3 extends RecyclerView.ViewHolder {
        // ...
        TextView text1;
        TextView text2;
        TextView text3;
        TextView text4;

        public ViewHolder3(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
            text3 = itemView.findViewById(R.id.text3);
            text4 = itemView.findViewById(R.id.text4);
        }
    }
}