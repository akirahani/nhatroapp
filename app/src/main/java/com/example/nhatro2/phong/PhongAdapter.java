package com.example.nhatro2.phong;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.PhongViewHolder> {

    Context context;
    List<PhongModel> phong;
    ImageView tacVuTrong ;
    CheckBox checkMulti;
    boolean isUp ;
    LinearLayout slideUp;

    public PhongAdapter(Context context, List<PhongModel> phong) {
        this.context = context;
        this.phong = phong;
    }

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public PhongAdapter.PhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_trong_item,parent,false);
        tacVuTrong = view.findViewById(R.id.tacVuTrong);
        checkMulti = view.findViewById(R.id.checkMulti);
        slideUp = view.findViewById(R.id.slideUp);

        isUp = false;
        return new PhongViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PhongAdapter.PhongViewHolder holder, int position) {
        PhongModel data = phong.get(position);
        int idPhong = data.getId();
        String ten = data.getTen();
        String day = data.getDay();
        int tang = data.getTang();
        int trangthai = data.getTrangthai();
        String dichvu = data.getDichvu();
        int datcoc = data.getDatcoc();
        int khach = data.getKhach();
        int gia = data.getGia();

        holder.ten.setText(ten);
        holder.day.setText(day);
        holder.tang.setText("-"+tang);

        tacVuTrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(),PhongEdit.class);
                intent.putExtra("idPhong",idPhong);
                intent.putExtra("tenPhong",ten);
                intent.putExtra("day",day);
                intent.putExtra("tang",tang);
                intent.putExtra("trangthai",trangthai);
                intent.putExtra("gia",gia);
                context.startActivity(intent);
            }
        });
//        slideUp.setVisibility(View.GONE);
        checkMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    @Override
    public int getItemCount() {

        return phong.size();
    }

    public static class PhongViewHolder extends RecyclerView.ViewHolder {
        TextView id,ten,vitri,trangthai,dichvu,datcoc,khach,day,tang;
        public PhongViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenPhongTrong);
            day = itemView.findViewById(R.id.dayTrong);
            tang = itemView.findViewById(R.id.tangTrong);
        }

    }



}
