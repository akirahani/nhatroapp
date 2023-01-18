package com.example.nhatro2.phong;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
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

public class DangThueAdapter extends RecyclerView.Adapter<DangThueAdapter.DangThueViewHolder> {

    List<PhongModel> phongThue;
    Context context;
    ImageView tacVuPhongThue;

    public DangThueAdapter(Context context, List<PhongModel> phongThue) {
        this.phongThue = phongThue;
        this.context = context;
    }


    @NonNull
    @Override
    public DangThueAdapter.DangThueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_thue_item,parent,false);
        tacVuPhongThue = view.findViewById(R.id.tacVuPhongThue);
        return new DangThueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DangThueAdapter.DangThueViewHolder holder, int position) {
        PhongModel data = phongThue.get(position);
        int idPhong = data.getId();
        String ten = data.getTen();
        String day = data.getKhu();
        int tang = data.getTang();
        int trangthai = data.getTrangthai();
//        String dichvu = data.getDichvu();
        int datcoc = data.getDatcoc();
        int gia = data.getGiaphong();
        int khach = data.getChuphong();
//        String daidien = data.getDaidien();
        String dienthoai = data.getDienthoai();

        holder.ten.setText(ten);
        holder.day.setText(day);
        holder.tang.setText("-"+tang);
        tacVuPhongThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(),PhongEdit.class);
                intent.putExtra("idPhong",idPhong);
                intent.putExtra("tenPhong",ten);
                intent.putExtra("day",day);
                intent.putExtra("tang",tang);
                intent.putExtra("trangthai",trangthai);
                intent.putExtra("gia",gia);
                intent.putExtra("daidien",khach);
                intent.putExtra("dienthoai",dienthoai);
                intent.putExtra("datcoc",datcoc);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phongThue.size();
    }

    public class DangThueViewHolder extends RecyclerView.ViewHolder {
        TextView id,ten,vitri,trangthai,dichvu,datcoc,khach,day,tang;
        public DangThueViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenPhongThue);
            day = itemView.findViewById(R.id.dayPhongThue);
            tang = itemView.findViewById(R.id.tangPhongThue);
        }
    }
}
