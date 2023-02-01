package com.example.nhatro2.phong;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.hop_dong.HopDongAdd;
import com.example.nhatro2.phong.PhongEdit;
import com.example.nhatro2.phong.PhongModel;

import java.util.List;

public class BanGiaoAdapter extends RecyclerView.Adapter<BanGiaoAdapter.BanGiaoViewHolder> {
    List<PhongModel> phongBanGiao;
    Context context;
    ImageView tacVuBanGiao,taoHopDong;

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
        taoHopDong = view.findViewById(R.id.taoHopDong);
        return new BanGiaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BanGiaoAdapter.BanGiaoViewHolder holder, int position) {
        PhongModel data = phongBanGiao.get(position);
        int idPhong = data.getId();
        String ten = data.getTen();
        String khu = data.getKhu();
        int tang = data.getTang();
        int trangthai = data.getTrangthai();
//        String dichvu = data.getDichvu();
        int datcoc = data.getDatcoc();
        int gia = data.getGiaphong();
        int khach = data.getChuphong();
//        String daidien = data.getDaidien();
        String dienthoai = data.getDienthoai();

        holder.ten.setText(ten);
        holder.day.setText(khu);
        holder.tang.setText("-"+tang);

        tacVuBanGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), PhongEdit.class);
                intent.putExtra("idPhong",idPhong);
                intent.putExtra("tenPhong",ten);
                intent.putExtra("day",khu);
                intent.putExtra("tang",tang);
                intent.putExtra("gia",gia);
                intent.putExtra("trangthai",trangthai);
                intent.putExtra("daidien",khach);
                intent.putExtra("dienthoai",dienthoai);
                intent.putExtra("datcoc",datcoc);
                context.startActivity(intent);
            }
        });

        taoHopDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), HopDongAdd.class);
                intent.putExtra("idPhong",idPhong);
                intent.putExtra("tenPhong",ten);
                intent.putExtra("gia",gia);
                intent.putExtra("daidien",khach);
                intent.putExtra("dienthoai",dienthoai);
                intent.putExtra("datcoc",datcoc);
                // Khai báo mảng dữ liệu thiết bị
                SharedPreferences shpThietBi = context.getSharedPreferences("idThietBiHopDong",Context.MODE_PRIVATE);
                SharedPreferences.Editor thietBiEdit = shpThietBi.edit();
                String listThietBiString = shpThietBi.getString("itemThietBi","");
                thietBiEdit.remove("itemThietBi");
                thietBiEdit.commit();
                context.startActivity(intent);
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
