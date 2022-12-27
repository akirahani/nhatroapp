package com.example.nhatro2.thanhvien;

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

import java.util.Date;
import java.util.List;

public class KhachTroAdapter extends RecyclerView.Adapter<KhachTroAdapter.KhachTroViewHolder> {
    Context context;
    List<ThanhVienModel> khachTro;

    public KhachTroAdapter(Context context, List<ThanhVienModel> khachTro) {
        this.context = context;
        this.khachTro = khachTro;
    }

    @NonNull
    @Override
    public KhachTroAdapter.KhachTroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.khach_tro_item, parent, false);
        return new KhachTroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachTroAdapter.KhachTroViewHolder holder, int position) {
        ThanhVienModel khach = khachTro.get(position);
        String tenKhachThue = khach.getFullname();
        String sdtKhachThue = khach.getDienthoai();
        int idKhachThue = khach.getId();
        String diachi = khach.getDiachi();
        String cancuoc = khach.getCancuoc();
        String ngaycap = khach.getNgaycap();
        String ngaysinh = khach.getNgaysinh();
        String quoctich = khach.getQuoctich();
        int gioitinh = khach.getGioitinh();
        int nhomtuoi = khach.getNhomtuoi();
        holder.tenKhach.setText(tenKhachThue);
        holder.sdtKhach.setText(sdtKhachThue);

        holder.suaKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(),KhachTroEdit.class);
                intent.putExtra("idKhach",idKhachThue);
                intent.putExtra("tenKhach",tenKhachThue);
                intent.putExtra("sdtKhach",sdtKhachThue);
                intent.putExtra("diachi",diachi);
                intent.putExtra("cancuoc",cancuoc);
                intent.putExtra("ngaycap",ngaycap);
                intent.putExtra("ngaysinh",ngaysinh);
                intent.putExtra("quoctich",quoctich);
                intent.putExtra("gioitinh",gioitinh);
                intent.putExtra("nhomtuoi",nhomtuoi);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return khachTro.size();
    }

    public class KhachTroViewHolder extends RecyclerView.ViewHolder {
        TextView tenKhach, sdtKhach, phongThue;
        ImageView suaKhach, xoaKhach;
        public KhachTroViewHolder(@NonNull View itemView) {
            super(itemView);
            tenKhach = itemView.findViewById(R.id.tenKhach);
            sdtKhach = itemView.findViewById(R.id.sdtKhach);
//            phongThue = itemView.findViewById(R.id.phongKhachThue);
            suaKhach = itemView.findViewById(R.id.suaKhachThue);
            xoaKhach = itemView.findViewById(R.id.xoaKhachThue);
        }
    }
}
