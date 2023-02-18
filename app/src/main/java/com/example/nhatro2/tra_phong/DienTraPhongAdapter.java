package com.example.nhatro2.tra_phong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class DienTraPhongAdapter extends RecyclerView.Adapter<DienTraPhongAdapter.DienTraPhongViewHolder> {
    Context context;
    List<DienTraPhongModel> listDienTraPhong;

    public DienTraPhongAdapter(Context context, List<DienTraPhongModel> listDienTraPhong) {
        this.context = context;
        this.listDienTraPhong = listDienTraPhong;
    }

    @NonNull
    @Override
    public DienTraPhongAdapter.DienTraPhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dien_tra_phong_item,parent,false);
        return new DienTraPhongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DienTraPhongAdapter.DienTraPhongViewHolder holder, int position) {
        DienTraPhongModel itemDienTraPhong = listDienTraPhong.get(position);
        String tienDienCanTraHangThang = itemDienTraPhong.getTien();
        String thoiGianDienCanTraHangThang = itemDienTraPhong.getThoigian();
        int soDau = itemDienTraPhong.getSodau();
        int soCuoi = itemDienTraPhong.getSocuoi();
        holder.thoiGianDienTraPhong.setText(thoiGianDienCanTraHangThang);
        holder.tienDienTraPhong.setText(tienDienCanTraHangThang+"đ");
        holder.soCuoiDienTraPhong.setText(""+soCuoi);
        holder.soDauDienTraPhong.setText(""+soDau);
    }

    @Override
    public int getItemCount() {
        return listDienTraPhong.size();
    }

    public class DienTraPhongViewHolder extends RecyclerView.ViewHolder {
        TextView tienDienTraPhong, thoiGianDienTraPhong, soCuoiDienTraPhong, soDauDienTraPhong;
        public DienTraPhongViewHolder(@NonNull View itemView) {
            super(itemView);
            tienDienTraPhong = itemView.findViewById(R.id.tienDienTraPhong);
            thoiGianDienTraPhong = itemView.findViewById(R.id.thoiGianDienTraPhong);
            soCuoiDienTraPhong = itemView.findViewById(R.id.soCuoiDienTraPhong);
            soDauDienTraPhong = itemView.findViewById(R.id.soDauDienTraPhong);
        }
    }
}
