package com.example.nhatro2.phong;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class BanGiaoAdapter extends RecyclerView.Adapter<BanGiaoAdapter.BanGiaoViewHolder> {
    List<PhongModel> phongBanGiao;
    Context context;
    ImageView tacVuBanGiao;

    public BanGiaoAdapter(Context context, List<PhongModel> phongBanGiao) {
        this.phongBanGiao = phongBanGiao;
        this.context = context;
    }

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public BanGiaoAdapter.BanGiaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_ban_giao_item,parent,false);
        tacVuBanGiao = view.findViewById(R.id.tacVuBanGiao);
        return new BanGiaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BanGiaoAdapter.BanGiaoViewHolder holder, int position) {
        PhongModel data = phongBanGiao.get(position);
        Log.d("ten",""+data.getTen());
        int idPhong = data.getId();
        String ten = data.getTen();
        String day = data.getDay();
        int tang = data.getTang();
        int trangthai = data.getTrangthai();
        String dichvu = data.getDichvu();
        int datcoc = data.getDatcoc();
        int khach = data.getKhach();

        holder.ten.setText(ten);
        holder.day.setText(day);
        holder.tang.setText("-"+tang);

        tacVuBanGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return phongBanGiao.size();
    }

    public class BanGiaoViewHolder extends RecyclerView.ViewHolder {
        TextView id,ten,vitri,trangthai,dichvu,datcoc,khach,day,tang;
        public BanGiaoViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenPhongBanGiao);
            day = itemView.findViewById(R.id.dayBanGiao);
            tang = itemView.findViewById(R.id.tangBanGiao);
        }
    }
}
