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

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.PhongViewHolder> {

    Context context;
    List<PhongModel> phong;

    public PhongAdapter(Context context, List<PhongModel> phong) {
        this.context = context;
        this.phong = phong;
    }

    @NonNull
    @Override
    public PhongAdapter.PhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_item,parent,false);
        return new PhongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongAdapter.PhongViewHolder holder, int position) {
        PhongModel data = phong.get(position);

        String ten = data.getTen();
        int vitri = data.getVitri();
        int trangthai = data.getTrangthai();
        String dichvu = data.getDichvu();
        int datcoc = data.getDatcoc();
        int khach = data.getKhach();

        holder.ten.setText(ten);
        holder.vitri.setText(vitri+"");
        holder.trangthai.setText(trangthai+"");
        holder.dichvu.setText(dichvu);
        holder.datcoc.setText(datcoc+"");
        holder.khach.setText(khach+"");
    }

    @Override
    public int getItemCount() {

        return phong.size();
    }

    public class PhongViewHolder extends RecyclerView.ViewHolder {
        TextView id,ten,vitri,trangthai,dichvu,datcoc,khach;
        public PhongViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            ten = itemView.findViewById(R.id.ten);
            vitri = itemView.findViewById(R.id.vitri);
            trangthai = itemView.findViewById(R.id.trangthai);
            dichvu = itemView.findViewById(R.id.dichvu);
            datcoc = itemView.findViewById(R.id.datcoc);
            khach = itemView.findViewById(R.id.khach);
        }

    }
}
