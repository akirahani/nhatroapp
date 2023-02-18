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

public class NuocTraPhongAdapter extends RecyclerView.Adapter<NuocTraPhongAdapter.NuocTraPhongViewHolder> {
    Context context;
    List<NuocTraPhongModel> listNuocTraPhong;

    public NuocTraPhongAdapter(Context context, List<NuocTraPhongModel> listNuocTraPhong) {
        this.context = context;
        this.listNuocTraPhong = listNuocTraPhong;
    }

    @NonNull
    @Override
    public NuocTraPhongAdapter.NuocTraPhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nuoc_tra_phong_item,parent,false);
        return new NuocTraPhongAdapter.NuocTraPhongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NuocTraPhongAdapter.NuocTraPhongViewHolder holder, int position) {
        NuocTraPhongModel itemNuocTraPhong = listNuocTraPhong.get(position);
        String tienNuocCanTraHangThang = itemNuocTraPhong.getTien();
        String thoiGianDienCanTraHangThang = itemNuocTraPhong.getThoigian();
        int soDau = itemNuocTraPhong.getSodau();
        int soCuoi = itemNuocTraPhong.getSocuoi();
        holder.thoiGianNuocTraPhong.setText(thoiGianDienCanTraHangThang);
        holder.tienNuocTraPhong.setText(tienNuocCanTraHangThang+"đ");
        holder.soCuoiNuocTraPhong.setText(""+soCuoi);
        holder.soDauNuocTraPhong.setText(""+soDau);

    }

    @Override
    public int getItemCount() {
        return listNuocTraPhong.size();
    }

    public class NuocTraPhongViewHolder extends RecyclerView.ViewHolder {
        TextView tienNuocTraPhong, thoiGianNuocTraPhong, soCuoiNuocTraPhong, soDauNuocTraPhong;
        public NuocTraPhongViewHolder(@NonNull View itemView) {
            super(itemView);
            tienNuocTraPhong = itemView.findViewById(R.id.tienNuocTraPhong);
            thoiGianNuocTraPhong = itemView.findViewById(R.id.thoiGianNuocTraPhong);
            soCuoiNuocTraPhong = itemView.findViewById(R.id.soCuoiNuocTraPhong);
            soDauNuocTraPhong = itemView.findViewById(R.id.soDauNuocTraPhong);
        }
    }
}
