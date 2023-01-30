package com.example.nhatro2.dong_tien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.thanhvien.ThanhVien;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import java.util.List;

public class KhachPhongTienAdapter extends RecyclerView.Adapter<KhachPhongTienAdapter.KhachPhongTienViewHolder>{
    Context context;
    List<ThanhVienModel> listKhach;

    public KhachPhongTienAdapter(Context context, List<ThanhVienModel> listKhach) {
        this.context = context;
        this.listKhach = listKhach;
    }

    @NonNull
    @Override
    public KhachPhongTienAdapter.KhachPhongTienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_khach_phong_tien_item,parent,false);
        return new KhachPhongTienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachPhongTienAdapter.KhachPhongTienViewHolder holder, int position) {
        ThanhVienModel thanhVienItem = listKhach.get(position);
        holder.khachItem.setText(thanhVienItem.getFullname());
    }

    @Override
    public int getItemCount() {
        return listKhach.size();
    }

    public class KhachPhongTienViewHolder extends RecyclerView.ViewHolder {
        TextView khachItem;
        public KhachPhongTienViewHolder(@NonNull View itemView) {
            super(itemView);
            khachItem = itemView.findViewById(R.id.khachThuePhong);
        }
    }
}
