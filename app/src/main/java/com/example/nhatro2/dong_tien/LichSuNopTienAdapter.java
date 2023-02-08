package com.example.nhatro2.dong_tien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class LichSuNopTienAdapter extends RecyclerView.Adapter<LichSuNopTienAdapter.LichSuNopTienViewHolder> {
    Context context;
    List<LichSuDongTienModel> lichSuKhachDongTien;

    public LichSuNopTienAdapter(Context context, List<LichSuDongTienModel> lichSuKhachDongTien) {
        this.context = context;
        this.lichSuKhachDongTien = lichSuKhachDongTien;
    }

    @NonNull
    @Override
    public LichSuNopTienAdapter.LichSuNopTienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lich_su_dong_tien_item,parent,false);
        return new LichSuNopTienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuNopTienAdapter.LichSuNopTienViewHolder holder, int position) {
        LichSuDongTienModel dongTienItem = lichSuKhachDongTien.get(position);
        holder.thoiGianTraTien.setText(dongTienItem.getNgay()+" - "+dongTienItem.getGio());
        holder.tienKhachTra.setText(dongTienItem.getTien()+"đ");
    }

    @Override
    public int getItemCount() {
        return lichSuKhachDongTien.size();
    }

    public class LichSuNopTienViewHolder extends RecyclerView.ViewHolder {
        TextView thoiGianTraTien, tienKhachTra;
        public LichSuNopTienViewHolder(@NonNull View itemView) {
            super(itemView);
            thoiGianTraTien = itemView.findViewById(R.id.thoiGianTraTien);
            tienKhachTra = itemView.findViewById(R.id.tienKhachTra);
        }
    }
}
