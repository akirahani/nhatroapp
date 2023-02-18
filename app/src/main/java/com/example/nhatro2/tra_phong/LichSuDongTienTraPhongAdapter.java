package com.example.nhatro2.tra_phong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.dong_tien.LichSuDongTienModel;
import com.example.nhatro2.dong_tien.LichSuNopTienAdapter;

import java.util.List;

public class LichSuDongTienTraPhongAdapter extends RecyclerView.Adapter<LichSuDongTienTraPhongAdapter.LichSuDongTienTraPhongViewHolder> {
    Context context;
    List<LichSuDongTienModel> listLichSuDongtien;

    public LichSuDongTienTraPhongAdapter(Context context, List<LichSuDongTienModel> listLichSuDongtien) {
        this.context = context;
        this.listLichSuDongtien = listLichSuDongtien;
    }

    @NonNull
    @Override
    public LichSuDongTienTraPhongAdapter.LichSuDongTienTraPhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lich_su_dong_tien_item,parent,false);
        return new LichSuDongTienTraPhongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuDongTienTraPhongAdapter.LichSuDongTienTraPhongViewHolder holder, int position) {
        LichSuDongTienModel dongTienItem = listLichSuDongtien.get(position);
        holder.thoiGianTraTien.setText(dongTienItem.getNgay()+" - "+dongTienItem.getGio());
        holder.tienKhachTra.setText(dongTienItem.getTien()+"đ");
    }

    @Override
    public int getItemCount() {
        return listLichSuDongtien.size();
    }

    public class LichSuDongTienTraPhongViewHolder extends RecyclerView.ViewHolder {
        TextView thoiGianTraTien, tienKhachTra;
        public LichSuDongTienTraPhongViewHolder(@NonNull View itemView) {
            super(itemView);
            thoiGianTraTien = itemView.findViewById(R.id.thoiGianTraTien);
            tienKhachTra = itemView.findViewById(R.id.tienKhachTra);
        }
    }
}
