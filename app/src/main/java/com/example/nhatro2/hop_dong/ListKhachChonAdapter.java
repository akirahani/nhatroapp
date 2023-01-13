package com.example.nhatro2.hop_dong;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListKhachChonAdapter extends RecyclerView.Adapter<ListKhachChonAdapter.ListKhachChonViewHolder> {
    Context context;
    List<ThanhVienModel> listKhach;
    ClickKhachAddHopDong clickKhach;
    String listKhachChooseString;

    public ListKhachChonAdapter(Context context, List<ThanhVienModel> listKhach, ClickKhachAddHopDong clickKhach) {
        this.context = context;
        this.listKhach = listKhach;
        this.clickKhach = clickKhach;
    }

    @NonNull
    @Override
    public ListKhachChonAdapter.ListKhachChonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.khach_chon_hop_dong_item, parent,false);
        return new ListKhachChonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListKhachChonAdapter.ListKhachChonViewHolder holder, int position) {
        ThanhVienModel thanhVien = listKhach.get(position);
        String tenKhach = thanhVien.getFullname();
        String sdt = thanhVien.getDienthoai();
        int id = thanhVien.getId();

        holder.tenKhachChon.setText(tenKhach+" - ");
        holder.sdtKhachChon.setText(sdt);

        SharedPreferences shpKhach = context.getSharedPreferences("khachChonHopDongAdd", MODE_PRIVATE);
        SharedPreferences.Editor shpKhachEdit = shpKhach.edit();
        listKhachChooseString = shpKhach.getString("idKhachChon", "");

        List<Integer> arrKhachChon = new ArrayList<>();

        if (listKhachChooseString.equals("")) {
            if (arrKhachChon.contains(id)) {
                holder.tenKhachChon.setTextColor(Color.rgb(46, 184, 75));
                holder.sdtKhachChon.setTextColor(Color.rgb(46, 184, 75));
            } else {
                holder.tenKhachChon.setTextColor(Color.rgb(0, 0, 0));
                holder.sdtKhachChon.setTextColor(Color.rgb(0, 0, 0));
            }
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                arrKhachChon = Arrays.stream(listKhachChooseString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            }
            if (arrKhachChon.contains(id)) {
                holder.tenKhachChon.setTextColor(Color.rgb(46, 184, 75));
                holder.sdtKhachChon.setTextColor(Color.rgb(46, 184, 75));
            } else {
                holder.tenKhachChon.setTextColor(Color.rgb(0, 0, 0));
                holder.sdtKhachChon.setTextColor(Color.rgb(0, 0, 0));
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickKhach.clickKhachChon(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKhach.size();
    }

    public class ListKhachChonViewHolder extends RecyclerView.ViewHolder {
        TextView tenKhachChon, sdtKhachChon;
        public ListKhachChonViewHolder(@NonNull View itemView) {
            super(itemView);
            tenKhachChon = itemView.findViewById(R.id.tenKhachChon);
            sdtKhachChon = itemView.findViewById(R.id.sdtKhachChon);
        }
    }
}
