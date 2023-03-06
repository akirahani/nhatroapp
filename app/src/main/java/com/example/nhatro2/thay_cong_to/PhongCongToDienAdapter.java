package com.example.nhatro2.thay_cong_to;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.phong.Phong;
import com.example.nhatro2.phong.PhongModel;

import java.util.List;

public class PhongCongToDienAdapter extends RecyclerView.Adapter<PhongCongToDienAdapter.PhongCongToDienViewHolder> {
    Context context;
    List<PhongModel> phongList;
    ClickCongToDien congToDienClick;

    public PhongCongToDienAdapter(Context context, List<PhongModel> phongList, ClickCongToDien congToDienClick) {
        this.context = context;
        this.phongList = phongList;
        this.congToDienClick = congToDienClick;
    }

    @NonNull
    @Override
    public PhongCongToDienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_cong_to_dien_item,parent,false);
        return new PhongCongToDienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongCongToDienViewHolder holder, int position) {
        PhongModel phongItem = phongList.get(position);
        String tenPhong = phongItem.getTen();
        int idPhong = phongItem.getId();
        int trangthai = phongItem.getTrangthai();
        int chuPhong = phongItem.getChuphong();
        holder.chonPhongCongToDienClick.setText(tenPhong);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                congToDienClick.clickPhongID(idPhong,tenPhong,chuPhong,trangthai);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phongList.size();
    }

    public class PhongCongToDienViewHolder extends RecyclerView.ViewHolder {
        TextView chonPhongCongToDienClick;
        public PhongCongToDienViewHolder(@NonNull View itemView) {
            super(itemView);
            chonPhongCongToDienClick = itemView.findViewById(R.id.chonPhongCongToDienClick);
        }
    }
}
