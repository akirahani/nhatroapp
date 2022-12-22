package com.example.nhatro2.hop_dong;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.dich_vu.DichVuModel;

import java.util.List;

public class ThietBiAddAdapter extends RecyclerView.Adapter<ThietBiAddAdapter.ThietBiAddAdapterViewHolder> {
    Context context;
    List<DichVuModel> listThietBi;

    public ThietBiAddAdapter(Context context, List<DichVuModel> listThietBi) {
        this.context = context;
        this.listThietBi = listThietBi;
    }

    @NonNull
    @Override
    public ThietBiAddAdapter.ThietBiAddAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thiet_bi_hop_dong_item, parent, false);
        return new ThietBiAddAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThietBiAddAdapter.ThietBiAddAdapterViewHolder holder, int position) {
        DichVuModel thietbi = listThietBi.get(position);
        int idThietBi = thietbi.getId();
        String tenThietBi = thietbi.getTen();
        holder.tenThietBi.setText(tenThietBi);
        holder.imgThietBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int imgCurrent = 0;
                holder.imgThietBi.setImageResource(imgCurrent);
                if (imgCurrent == R.drawable.uncheck) {
                    holder.imgThietBi.setImageResource(R.drawable.checked);
                } else {
                    holder.imgThietBi.setImageResource(R.drawable.uncheck);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listThietBi.size();
    }

    public class ThietBiAddAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThietBi;
        TextView tenThietBi;

        public ThietBiAddAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThietBi = itemView.findViewById(R.id.imageServiceAdd);
            tenThietBi = itemView.findViewById(R.id.textThietBiAdd);
        }
    }
}
