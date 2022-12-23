package com.example.nhatro2.phong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class MultiRoomAdapter extends RecyclerView.Adapter<MultiRoomAdapter.MultiRoomViewHolder> {
    public MultiRoomAdapter(Context context, List<PhongModel> phongCheck) {
        this.context = context;
        this.phongCheck = phongCheck;
    }

    Context context;
    List<PhongModel> phongCheck;


    @NonNull
    @Override
    public MultiRoomAdapter.MultiRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.room_checked_item,parent,false);
        return new MultiRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiRoomAdapter.MultiRoomViewHolder holder, int position) {
        PhongModel phongListCheck = phongCheck.get(position);
        String tenPhong = phongListCheck.getTen();
        holder.tenPhongCheck.setText(tenPhong);
    }

    @Override
    public int getItemCount() {
        return phongCheck.size();
    }

    public class MultiRoomViewHolder extends RecyclerView.ViewHolder {
        TextView tenPhongCheck;
        public MultiRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            tenPhongCheck = itemView.findViewById(R.id.nameRoomChecked);
        }
    }
}
