package com.example.nhatro2.dong_tien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.phong.PhongModel;

import java.util.List;

public class ChonPhongChuyenAdapter extends RecyclerView.Adapter<ChonPhongChuyenAdapter.ChonPhongChuyenViewHolder> {
    Context context;
    List<PhongModel> listPhongChuyen;
    ClickPhongChuyen phongChuyenItemClick;

    public ChonPhongChuyenAdapter(Context context, List<PhongModel> listPhongChuyen, ClickPhongChuyen phongChuyenItemClick) {
        this.context = context;
        this.listPhongChuyen = listPhongChuyen;
        this.phongChuyenItemClick = phongChuyenItemClick;
    }

    @NonNull
    @Override
    public ChonPhongChuyenAdapter.ChonPhongChuyenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_chuyen_item,parent,false);
        return new ChonPhongChuyenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChonPhongChuyenAdapter.ChonPhongChuyenViewHolder holder, int position) {
        PhongModel phongChuyenItem = listPhongChuyen.get(position);
        String tenPhong = phongChuyenItem.getTen();
        int idPhong = phongChuyenItem.getId();
        int trangthai = phongChuyenItem.getTrangthai();
        holder.chonPhongChuyenClick.setText(tenPhong);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phongChuyenItemClick.chuyenPhong(idPhong,tenPhong,trangthai);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPhongChuyen.size();
    }

    public class ChonPhongChuyenViewHolder extends RecyclerView.ViewHolder {
        TextView chonPhongChuyenClick;
        public ChonPhongChuyenViewHolder(@NonNull View itemView) {
            super(itemView);
            chonPhongChuyenClick = itemView.findViewById(R.id.chonPhongChuyenClick);
        }
    }
}
