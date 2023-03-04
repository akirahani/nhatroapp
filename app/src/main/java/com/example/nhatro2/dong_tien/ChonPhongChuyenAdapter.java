package com.example.nhatro2.dong_tien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.phong.PhongModel;

import java.util.List;

public class ChonPhongChuyenAdapter extends RecyclerView.Adapter<ChonPhongChuyenAdapter.ChonPhongChuyenViewHolder> {
    Context context;
    List<PhongModel> listPhongChuyen;
    ClickPhongChuyen phongChuyenItem;

    public ChonPhongChuyenAdapter(Context context, List<PhongModel> listPhongChuyen, ClickPhongChuyen phongChuyenItem) {
        this.context = context;
        this.listPhongChuyen = listPhongChuyen;
        this.phongChuyenItem = phongChuyenItem;
    }

    @NonNull
    @Override
    public ChonPhongChuyenAdapter.ChonPhongChuyenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_chuyen_item,parent,false);
        return new ChonPhongChuyenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChonPhongChuyenAdapter.ChonPhongChuyenViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listPhongChuyen.size();
    }

    public class ChonPhongChuyenViewHolder extends RecyclerView.ViewHolder {
        public ChonPhongChuyenViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
