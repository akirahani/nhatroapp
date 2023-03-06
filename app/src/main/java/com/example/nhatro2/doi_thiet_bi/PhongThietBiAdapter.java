package com.example.nhatro2.doi_thiet_bi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.dich_vu.DichVuModel;

import java.util.List;

public class PhongThietBiAdapter extends RecyclerView.Adapter<PhongThietBiAdapter.PhongThietBiViewHolder> {
    Context context;
    List<DichVuModel> listThietBi;

    public PhongThietBiAdapter(Context context, List<DichVuModel> listThietBi) {
        this.context = context;
        this.listThietBi = listThietBi;
    }

    @NonNull
    @Override
    public PhongThietBiAdapter.PhongThietBiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_thiet_bi_click_item,parent,false);
        return new PhongThietBiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongThietBiAdapter.PhongThietBiViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listThietBi.size();
    }

    public class PhongThietBiViewHolder extends RecyclerView.ViewHolder {
        public PhongThietBiViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
