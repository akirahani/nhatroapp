package com.example.nhatro2.doi_thiet_bi;

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

public class ChonPhongChuyenDiAdapter extends RecyclerView.Adapter<ChonPhongChuyenDiAdapter.ChonPhongChuyenDiViewHolder> {
    Context context;
    List<PhongModel> listPhongChuyenDi;
    ClickPhongChuyenDi clickPhongChuyenDi;

    public ChonPhongChuyenDiAdapter(Context context, List<PhongModel> listPhongChuyenDi, ClickPhongChuyenDi clickPhongChuyenDi) {
        this.context = context;
        this.listPhongChuyenDi = listPhongChuyenDi;
        this.clickPhongChuyenDi = clickPhongChuyenDi;
    }

    @NonNull
    @Override
    public ChonPhongChuyenDiAdapter.ChonPhongChuyenDiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_chuyen_di_item,parent,false);
        return new ChonPhongChuyenDiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChonPhongChuyenDiAdapter.ChonPhongChuyenDiViewHolder holder, int position) {
        PhongModel phongItem = listPhongChuyenDi.get(position);
        String tenPhong = phongItem.getTen();
        int idPhong = phongItem.getId();
        int chuPhongChon = phongItem.getChuphong();
        int trangthaiphong = phongItem.getTrangthai();
        holder.chonPhongChuyenDiClick.setText(tenPhong);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickPhongChuyenDi.clickPhongID(idPhong,tenPhong,chuPhongChon,trangthaiphong);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPhongChuyenDi.size();
    }

    public class ChonPhongChuyenDiViewHolder extends RecyclerView.ViewHolder {
        TextView chonPhongChuyenDiClick;
        public ChonPhongChuyenDiViewHolder(@NonNull View itemView) {
            super(itemView);
            chonPhongChuyenDiClick = itemView.findViewById(R.id.chonPhongChuyenDiClick);
        }
    }
}
