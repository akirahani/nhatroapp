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
import com.example.nhatro2.ThongTin;
import com.example.nhatro2.dich_vu.DichVu;

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
                    case "thongtin":
                        Intent thongtin = new Intent(context, ThongTin.class);
                        context.startActivity(thongtin);
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
