package com.example.nhatro2.hop_dong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import java.util.List;

public class KhachHopDongDetailAdapter extends RecyclerView.Adapter<KhachHopDongDetailAdapter.KhachHopDongDetailViewHolder> {
    Context context;
    List<ThanhVienModel> khachThueList;

    public KhachHopDongDetailAdapter(Context context, List<ThanhVienModel> khachThueList) {
        this.context = context;
        this.khachThueList = khachThueList;
    }

    @NonNull
    @Override
    public KhachHopDongDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.khach_hop_dong_detail_item, parent, false);
        return new KhachHopDongDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHopDongDetailViewHolder holder, int position) {
        ThanhVienModel khachThuePhong = khachThueList.get(position);
        String sdtKhach = khachThuePhong.getDienthoai();
        String tenKhach = khachThuePhong.getFullname();
        holder.sdtKhachHopDongDetail.setText(sdtKhach);
        holder.tenKhachHopDongDetail.setText(tenKhach);
        position++;
        holder.thuTuKhachTroDetail.setText("Thành viên "+position);
    }

    @Override
    public int getItemCount() {
        return khachThueList.size();
    }

    public class KhachHopDongDetailViewHolder extends RecyclerView.ViewHolder {
        TextView thuTuKhachTroDetail;
        EditText tenKhachHopDongDetail, sdtKhachHopDongDetail;
        public KhachHopDongDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            tenKhachHopDongDetail = itemView.findViewById(R.id.tenKhachHopDongDetail);
            sdtKhachHopDongDetail = itemView.findViewById(R.id.sdtKhachHopDongDetail);
            thuTuKhachTroDetail = itemView.findViewById(R.id.thuTuKhachTroDetail);
        }
    }
}
