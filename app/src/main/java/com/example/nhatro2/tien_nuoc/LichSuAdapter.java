package com.example.nhatro2.tien_nuoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.LichSuTienNuocViewHolder> {
    Context context;
    List<LichSuNuocModel> listPhongNuoc;

    public LichSuAdapter(Context context, List<LichSuNuocModel> listPhongNuoc) {
        this.context = context;
        this.listPhongNuoc = listPhongNuoc;
    }

    @NonNull
    @Override
    public LichSuAdapter.LichSuTienNuocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lich_su_su_dung_nuoc_item, parent, false);
        return new LichSuTienNuocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuAdapter.LichSuTienNuocViewHolder holder, int position) {
        LichSuNuocModel phongNuocItem = listPhongNuoc.get(position);
        String thoiGianHienThi = phongNuocItem.getTime();
        int soNuocSuDung = phongNuocItem.getSonuoc();
        holder.numberWaterUseText.setText("" + soNuocSuDung);
        holder.timeMonthDisplayHistory.setText("Số điện " + thoiGianHienThi);
    }

    @Override
    public int getItemCount() {
        return listPhongNuoc.size();
    }

    public class LichSuTienNuocViewHolder extends RecyclerView.ViewHolder {
        TextView timeMonthDisplayHistory, numberWaterUseText;

        public LichSuTienNuocViewHolder(@NonNull View itemView) {
            super(itemView);
            timeMonthDisplayHistory = itemView.findViewById(R.id.timeMonthDisplayHistory);
            numberWaterUseText = itemView.findViewById(R.id.numberWaterUseText);
        }
    }
}