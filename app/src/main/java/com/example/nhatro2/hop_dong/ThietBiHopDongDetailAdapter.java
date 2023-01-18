package com.example.nhatro2.hop_dong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.dich_vu.DichVuModel;

import java.util.List;

public class ThietBiHopDongDetailAdapter extends RecyclerView.Adapter<ThietBiHopDongDetailAdapter.ThietBiHopDongDetailViewHolder> {
    Context context;
    List<DichVuModel> listThietBiDetailHD;

    public ThietBiHopDongDetailAdapter(Context context, List<DichVuModel> listThietBiDetailHD) {
        this.context = context;
        this.listThietBiDetailHD = listThietBiDetailHD;
    }

    @NonNull
    @Override
    public ThietBiHopDongDetailAdapter.ThietBiHopDongDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thiet_bi_hop_dong_detail_item,parent,false);
        return new ThietBiHopDongDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThietBiHopDongDetailAdapter.ThietBiHopDongDetailViewHolder holder, int position) {
        DichVuModel thietBi = listThietBiDetailHD.get(position);
        String tenThietBi = thietBi.getTen();
        holder.textThietBiHopDongDetail.setText(tenThietBi);
    }

    @Override
    public int getItemCount() {
        return listThietBiDetailHD.size();
    }

    public class ThietBiHopDongDetailViewHolder extends RecyclerView.ViewHolder {
        TextView textThietBiHopDongDetail;
        public ThietBiHopDongDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            textThietBiHopDongDetail = itemView.findViewById(R.id.textThietBiHopDongDetail);
        }
    }
}
