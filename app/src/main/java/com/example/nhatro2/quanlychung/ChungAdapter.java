package com.example.nhatro2.quanlychung;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.dich_vu.DichVu;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.phong.Phong;
import com.example.nhatro2.quy_tien_mat.QuyTienMat;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.tien_coc.TienCoc;

import java.util.List;

public class ChungAdapter extends RecyclerView.Adapter<ChungAdapter.ChungViewHolder> {

    Context context;
    List<ChungModel> chung;

    public ChungAdapter(Context context, List<ChungModel> chung) {
        this.context = context;
        this.chung = chung;
    }

    @NonNull
    @Override
    public ChungAdapter.ChungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quan_li_chung_item,parent,false);
        return new ChungViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChungAdapter.ChungViewHolder holder, int position) {
        ChungModel data = chung.get(position);
        String ten = data.getTen();
        int hinh = data.getHinh();
        holder.ten.setText(ten);
        holder.hinh.setBackgroundResource(hinh);

        //Click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String page = data.getPage();
                switch (page){
                    case "dichvu":
                        Intent dichvu = new Intent(context, DichVu.class);
                        context.startActivity(dichvu);
                        break;
                    case "phong":
                        Intent phong = new Intent(context, Phong.class);
                        context.startActivity(phong);
                        break;
                    case "khachtro" :
                        Intent khachtro = new Intent(context, KhachTro.class);
                        context.startActivity(khachtro);
                        break;
                    case "hopdong" :
                        Intent hopdong = new Intent(context, HopDong.class);
                        context.startActivity(hopdong);
                        break;
                    case "khoanthuchi" :
                        Intent khoanthuchi = new Intent(context, DongTien.class);
                        context.startActivity(khoanthuchi);
                        break;
                    case "tiencoc" :
                        Intent tiencoc = new Intent(context, TienCoc.class);
                        context.startActivity(tiencoc);
                        break;
                    case "quytien" :
                        Intent quytien = new Intent(context, QuyTienMat.class);
                        context.startActivity(quytien);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return chung.size();
    }

    public class ChungViewHolder extends RecyclerView.ViewHolder {
        ImageView hinh;
        TextView ten;
        public ChungViewHolder(@NonNull View itemView) {
            super(itemView);
            hinh = itemView.findViewById(R.id.dichvu);
            ten = itemView.findViewById(R.id.tenNoiDungQL);
        }
    }
}
