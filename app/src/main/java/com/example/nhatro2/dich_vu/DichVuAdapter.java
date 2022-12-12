package com.example.nhatro2.dich_vu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.text.DecimalFormat;
import java.util.List;

public class DichVuAdapter extends RecyclerView.Adapter<DichVuAdapter.DichVuViewHolder> {
    Context context;
    List<DichVuModel> dichVu;

    public DichVuAdapter(Context context, List<DichVuModel> dichVu) {
        this.context = context;
        this.dichVu = dichVu;
    }

    @NonNull
    @Override
    public DichVuAdapter.DichVuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dich_vu_item,parent,false);
        return new DichVuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DichVuAdapter.DichVuViewHolder holder, int position) {
        DichVuModel data = dichVu.get(position);
        int id = data.getId();
        String ten = data.getTen();
        int loai = data.getLoai();
        int gia = data.getGia();

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String giaFormat = formatter.format(gia);
//        holder.idDichVu.setText(id+"");
        holder.tenDichVu.setText(ten);
//        holder.loaiDichVu.setText(loai+"");
        holder.giaDichVu.setText(giaFormat+"đ");
    }

    @Override
    public int getItemCount() {
        return dichVu.size();
    }

    public class DichVuViewHolder extends RecyclerView.ViewHolder {
        TextView idDichVu, tenDichVu, loaiDichVu, giaDichVu;
        ImageView suaDichVu, xoaDichVu;
        public DichVuViewHolder(@NonNull View itemView) {
            super(itemView);
//            idDichVu = itemView.findViewById(R.id.idDichVu);
            tenDichVu = itemView.findViewById(R.id.tenDichVu);
//            loaiDichVu = itemView.findViewById(R.id.loaiDichVu);
            giaDichVu = itemView.findViewById(R.id.giaDichVu);
            suaDichVu = itemView.findViewById(R.id.suaDichVu);
            xoaDichVu = itemView.findViewById(R.id.xoaDichVu);
        }
    }
}
