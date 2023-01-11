package com.example.nhatro2.tien_dien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.tien_nuoc.LichSuNuocModel;

import java.util.List;

public class LichSuDienAdapter extends RecyclerView.Adapter<LichSuDienAdapter.LichSuDienViewHolder> {
    Context context;
    List<LichSuDienModel> listPhongDien;

    public LichSuDienAdapter(Context context, List<LichSuDienModel> listPhongDien) {
        this.context = context;
        this.listPhongDien = listPhongDien;
    }

    @NonNull
    @Override
    public LichSuDienAdapter.LichSuDienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lich_su_dung_dien_item,parent,false);
        return new LichSuDienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuDienAdapter.LichSuDienViewHolder holder, int position) {
        LichSuDienModel phongDienItem = listPhongDien.get(position);
        String thoiGianHienThi = phongDienItem.getTime();
        int soDienSuDung = phongDienItem.getSodien();
        holder.numberElectricUseText.setText("" + soDienSuDung);
        holder.timeMonthElectricDisplayHistory.setText("Số điện " + thoiGianHienThi);
    }

    @Override
    public int getItemCount() {
        return listPhongDien.size();
    }

    public class LichSuDienViewHolder extends RecyclerView.ViewHolder {
        TextView timeMonthElectricDisplayHistory, numberElectricUseText;
        public LichSuDienViewHolder(@NonNull View itemView) {
            super(itemView);
            timeMonthElectricDisplayHistory = itemView.findViewById(R.id.timeMonthElectricDisplayHistory);
            numberElectricUseText = itemView.findViewById(R.id.numberElectricUseText);
        }
    }
}
