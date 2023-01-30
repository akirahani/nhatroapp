package com.example.nhatro2.dong_tien;

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

public class ThietBiPhongTienAdapter extends RecyclerView.Adapter<ThietBiPhongTienAdapter.ThietBiPhongTienViewHolder> {
    Context context;
    List<DichVuModel> listThietBi;

    public ThietBiPhongTienAdapter(Context context, List<DichVuModel> listThietBi) {
        this.context = context;
        this.listThietBi = listThietBi;
    }

    @NonNull
    @Override
    public ThietBiPhongTienAdapter.ThietBiPhongTienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thiet_bi_phong_tien_item, parent, false);
        return new ThietBiPhongTienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThietBiPhongTienAdapter.ThietBiPhongTienViewHolder holder, int position) {
        DichVuModel dichVuItem = listThietBi.get(position);
        holder.thietBiPhongTienItem.setText(dichVuItem.getTen());
    }

    @Override
    public int getItemCount() {
        return listThietBi.size();
    }

    public class ThietBiPhongTienViewHolder extends RecyclerView.ViewHolder {
        TextView thietBiPhongTienItem;
        public ThietBiPhongTienViewHolder(@NonNull View itemView) {
            super(itemView);
            thietBiPhongTienItem = itemView.findViewById(R.id.thietBiPhongTienItem);
        }
    }
}
