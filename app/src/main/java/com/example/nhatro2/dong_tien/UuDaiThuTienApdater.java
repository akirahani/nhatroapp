package com.example.nhatro2.dong_tien;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.uu_dai.UuDaiModel;

import java.util.List;

public class UuDaiThuTienApdater extends RecyclerView.Adapter<UuDaiThuTienApdater.UuDaiThuTienViewHolder> {
    Context context;
    List<UuDaiModel> uuDaiList;
    ChonUuDaiClick uuDaiClickDongTien;

    public UuDaiThuTienApdater(Context context, List<UuDaiModel> uuDaiList, ChonUuDaiClick uuDaiClickDongTien) {
        this.context = context;
        this.uuDaiList = uuDaiList;
        this.uuDaiClickDongTien = uuDaiClickDongTien;
    }

    @NonNull
    @Override
    public UuDaiThuTienApdater.UuDaiThuTienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.uu_dai_thu_tien_item, parent,false);
        return new UuDaiThuTienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UuDaiThuTienApdater.UuDaiThuTienViewHolder holder, int position) {
        UuDaiModel itemUuDai = uuDaiList.get(position);
        int idUuDaiClick = itemUuDai.getId();
        String tenGiamGia = itemUuDai.getTen();
        holder.tenGiamGia.setText(itemUuDai.getTen());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uuDaiClickDongTien.chonUuDaiXuLy(idUuDaiClick, tenGiamGia);
            }
        });
    }

    @Override
    public int getItemCount() {
        return uuDaiList.size();
    }

    public class UuDaiThuTienViewHolder extends RecyclerView.ViewHolder {
        TextView tenGiamGia;
        public UuDaiThuTienViewHolder(@NonNull View itemView) {
            super(itemView);
            tenGiamGia = itemView.findViewById(R.id.tenGiamGia);
        }
    }
}
