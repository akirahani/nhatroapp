package com.example.nhatro2.tra_phong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class ThietBiTraPhongAdapter extends RecyclerView.Adapter<ThietBiTraPhongAdapter.ThietBiTraPhongViewHolder> {
    Context context;
    List<ThietBiTraPhongModel> listTienThietBiTra;

    public ThietBiTraPhongAdapter(Context context, List<ThietBiTraPhongModel> listTienThietBiTra) {
        this.context = context;
        this.listTienThietBiTra = listTienThietBiTra;
    }

    @NonNull
    @Override
    public ThietBiTraPhongAdapter.ThietBiTraPhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tien_thiet_bi_can_tra_item,parent,false);
        return new ThietBiTraPhongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThietBiTraPhongAdapter.ThietBiTraPhongViewHolder holder, int position) {
        ThietBiTraPhongModel itemThietBiTraPhong = listTienThietBiTra.get(position);
        String ngay = itemThietBiTraPhong.getThoigian();
        String tien = itemThietBiTraPhong.getTien();
        holder.thoiGianTienThietBi.setText(ngay+": ");
        holder.tienThietBiCanTra.setText(tien+"đ");
    }

    @Override
    public int getItemCount() {
        return listTienThietBiTra.size();
    }

    public class ThietBiTraPhongViewHolder extends RecyclerView.ViewHolder {
        TextView tienThietBiCanTra, thoiGianTienThietBi;
        public ThietBiTraPhongViewHolder(@NonNull View itemView) {
            super(itemView);
            thoiGianTienThietBi = itemView.findViewById(R.id.thoiGianTienThietBi);
            tienThietBiCanTra = itemView.findViewById(R.id.tienThietBiCanTra);
        }
    }
}
