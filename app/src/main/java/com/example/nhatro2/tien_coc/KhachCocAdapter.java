package com.example.nhatro2.tien_coc;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KhachCocAdapter extends RecyclerView.Adapter<KhachCocAdapter.KhachCocViewHolder> {
    Context context;
    List<ThanhVienModel> listKhach;
    ChonKhachCocIdClick chonKhachCocIdClick;

    public KhachCocAdapter(Context context, List<ThanhVienModel> listKhach, ChonKhachCocIdClick chonKhachCocIdClick) {
        this.context = context;
        this.listKhach = listKhach;
        this.chonKhachCocIdClick = chonKhachCocIdClick;
    }

    @NonNull
    @Override
    public KhachCocAdapter.KhachCocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.khach_chon_coc_item,parent,false);
        return new KhachCocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachCocAdapter.KhachCocViewHolder holder, int position) {
        ThanhVienModel thanhVien = listKhach.get(position);
        String tenKhach = thanhVien.getFullname();
        String sdt = thanhVien.getDienthoai();
        int id = thanhVien.getId();

        holder.tenKhachChonCoc.setText(tenKhach+" - ");
        holder.sdtKhachChonCoc.setText(sdt);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonKhachCocIdClick.clickKhachCocChon(id,tenKhach,sdt);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKhach.size();
    }

    public class KhachCocViewHolder extends RecyclerView.ViewHolder {
        TextView tenKhachChonCoc, sdtKhachChonCoc;
        public KhachCocViewHolder(@NonNull View itemView) {
            super(itemView);
            tenKhachChonCoc = itemView.findViewById(R.id.tenKhachChonCoc);
            sdtKhachChonCoc = itemView.findViewById(R.id.sdtKhachChonCoc);
        }
    }
}
