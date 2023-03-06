package com.example.nhatro2.thay_cong_to;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.phong.PhongModel;

import java.util.List;

public class PhongCongToNuocAdapter extends RecyclerView.Adapter<PhongCongToNuocAdapter.PhongCongToNuocViewHolder> {
    Context context;
    List<PhongModel> listPhong;
    ClickCongToNuoc clickCongToNuoc;

    public PhongCongToNuocAdapter(Context context, List<PhongModel> listPhong, ClickCongToNuoc clickCongToNuoc) {
        this.context = context;
        this.listPhong = listPhong;
        this.clickCongToNuoc = clickCongToNuoc;
    }

    @NonNull
    @Override
    public PhongCongToNuocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_cong_to_nuoc_item,parent,false);
        return new PhongCongToNuocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongCongToNuocViewHolder holder, int position) {
        PhongModel phongItem = listPhong.get(position);
        String tenPhong = phongItem.getTen();
        int idPhong = phongItem.getId();
        int trangthai = phongItem.getTrangthai();
        int chuPhong = phongItem.getChuphong();
        holder.chonPhongCongToNuocClick.setText(tenPhong);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCongToNuoc.clickPhongID(idPhong,tenPhong,chuPhong,trangthai);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPhong.size();
    }

    public class PhongCongToNuocViewHolder extends RecyclerView.ViewHolder {
        TextView chonPhongCongToNuocClick;
        public PhongCongToNuocViewHolder(@NonNull View itemView) {
            super(itemView);
            chonPhongCongToNuocClick = itemView.findViewById(R.id.chonPhongCongToNuocClick);

        }
    }
}
