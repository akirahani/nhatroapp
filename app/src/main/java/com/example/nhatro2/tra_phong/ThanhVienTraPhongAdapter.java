package com.example.nhatro2.tra_phong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.dong_tien.LichSuDongTienModel;
import com.example.nhatro2.dong_tien.LichSuNopTienAdapter;

import java.util.List;

public class ThanhVienTraPhongAdapter extends RecyclerView.Adapter<ThanhVienTraPhongAdapter.ThanhVienTraPhongViewHolder> {
    Context context;
    List<ThanhVienTraPhongModel> listTienTraTienThanhVien;

    public ThanhVienTraPhongAdapter(Context context, List<ThanhVienTraPhongModel> listTienTraTienThanhVien) {
        this.context = context;
        this.listTienTraTienThanhVien = listTienTraTienThanhVien;
    }

    @NonNull
    @Override
    public ThanhVienTraPhongAdapter.ThanhVienTraPhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tien_thanh_vien_item,parent,false);
        return new ThanhVienTraPhongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienTraPhongAdapter.ThanhVienTraPhongViewHolder holder, int position) {
        ThanhVienTraPhongModel tienThanhVienItem = listTienTraTienThanhVien.get(position);
        holder.thoiGianTienThanhVien.setText(tienThanhVienItem.getThoigian()+": ");
        holder.tienThanhVienCanTra.setText(tienThanhVienItem.getTien()+"đ");
    }

    @Override
    public int getItemCount() {
        return listTienTraTienThanhVien.size();
    }

    public class ThanhVienTraPhongViewHolder extends RecyclerView.ViewHolder {
        TextView thoiGianTienThanhVien, tienThanhVienCanTra;
        public ThanhVienTraPhongViewHolder(@NonNull View itemView) {
            super(itemView);
            thoiGianTienThanhVien = itemView.findViewById(R.id.thoiGianTienThanhVien);
            tienThanhVienCanTra = itemView.findViewById(R.id.tienThanhVienCanTra);
        }
    }
}
