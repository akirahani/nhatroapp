package com.example.nhatro2.tai_khoan;

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
import com.example.nhatro2.nhom.Nhom;
import com.example.nhatro2.phan_quyen.PhanQuyen;
import com.example.nhatro2.thanhvien.ThanhVien;

import java.util.List;

public class TaiKhoanAdapter extends RecyclerView.Adapter<TaiKhoanAdapter.TaiKhoanViewHolder> {
    Context context;
    List<TaiKhoanModel> taiKhoan;

    public TaiKhoanAdapter(Context context, List<TaiKhoanModel> taiKhoan) {
        this.context = context;
        this.taiKhoan = taiKhoan;
    }

    @NonNull
    @Override
    public TaiKhoanAdapter.TaiKhoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tai_khoan_item, parent, false);
        return new TaiKhoanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaiKhoanAdapter.TaiKhoanViewHolder holder, int position) {
        TaiKhoanModel data = taiKhoan.get(position);
        String ten = data.getTen();
        int anh = data.getAnh();
        holder.ten.setText(ten);
        holder.anh.setBackgroundResource(anh);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String page = data.getPage();
                switch (page){
                    case "nhom":
                        Intent nhom = new Intent(context, Nhom.class);
                        context.startActivity(nhom);
                        break;
                    case "thanhvien":
                        Intent thanhvien = new Intent(context, ThanhVien.class);
                        context.startActivity(thanhvien);
                        break;
                    case "phanquyen":
                        Intent phanquyen = new Intent(context, PhanQuyen.class);
                        context.startActivity(phanquyen);
                        break;
//                    case "trang":
//                        Intent trang = new Intent(context, Trang.class);
//                        context.startActivity(trang);
//                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return taiKhoan.size();
    }

    public class TaiKhoanViewHolder extends RecyclerView.ViewHolder {
        ImageView anh;
        TextView ten;
        public TaiKhoanViewHolder(@NonNull View itemView) {
            super(itemView);
            anh = itemView.findViewById(R.id.anhTaiKhoan);
            ten = itemView.findViewById(R.id.tenTaiKhoan);
        }
    }
}
