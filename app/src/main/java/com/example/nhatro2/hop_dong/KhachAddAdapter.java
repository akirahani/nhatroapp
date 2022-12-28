package com.example.nhatro2.hop_dong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import java.util.List;

public class KhachAddAdapter extends RecyclerView.Adapter<KhachAddAdapter.KhachTroAddViewHolder> {
    Context context;
    List<ThanhVienModel> khachTro;
    @NonNull
    @Override
    public KhachAddAdapter.KhachTroAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.khach_hop_dong_add_item,parent,false);
        return new KhachTroAddViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachAddAdapter.KhachTroAddViewHolder holder, int position) {
            
    }

    @Override
    public int getItemCount() {
        return khachTro.size();
    }

    public class KhachTroAddViewHolder extends RecyclerView.ViewHolder {
        public KhachTroAddViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
