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

public class KhachAddAdapter extends RecyclerView.Adapter<KhachAddAdapter.KhachTroAddViewHolder> {
    Context context;
    List<ThanhVienModel> khachTro;

    public KhachAddAdapter(Context context, List<ThanhVienModel> khachTro) {
        this.context = context;
        this.khachTro = khachTro;
    }

    @NonNull
    @Override
    public KhachAddAdapter.KhachTroAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.khach_hop_dong_add_item,parent,false);
        return new KhachTroAddViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachAddAdapter.KhachTroAddViewHolder holder, int position) {
        ThanhVienModel khachThueItem = khachTro.get(position);
        String tenKhach = khachThueItem.getFullname();
        String canCuoc = khachThueItem.getCancuoc();
        String ngayCap = khachThueItem.getNgaycap();
        String ngaySinh = khachThueItem.getNgaysinh();
        String dienThoai = khachThueItem.getDienthoai();
        String noicap = khachThueItem.getNoicap();

        holder.tenKhachAdd.setText(tenKhach);
        holder.canCuocKhachAdd.setText(canCuoc);
        holder.ngayCapKhachAdd.setText(ngayCap);
        holder.ngaySinhHopDongAddText.setText(ngaySinh);
        holder.sdtKhachAdd.setText(dienThoai);
        holder.noiCapKhachAdd.setText(noicap);
        position++;
        holder.thuTuKhachTro.setText("Khách trọ "+position);
    }

    @Override
    public int getItemCount() {
        return khachTro.size();
    }

    public class KhachTroAddViewHolder extends RecyclerView.ViewHolder {
        EditText tenKhachAdd, canCuocKhachAdd, noiCapKhachAdd, sdtKhachAdd;
        TextView ngayCapKhachAdd,ngaySinhHopDongAddText,thuTuKhachTro;
        public KhachTroAddViewHolder(@NonNull View itemView) {
            super(itemView);
            tenKhachAdd = itemView.findViewById(R.id.tenKhachAdd);
            canCuocKhachAdd = itemView.findViewById(R.id.canCuocKhachAdd);
            noiCapKhachAdd = itemView.findViewById(R.id.noiCapKhachAdd);
            sdtKhachAdd = itemView.findViewById(R.id.sdtKhachAdd);
            ngayCapKhachAdd = itemView.findViewById(R.id.ngayCapKhachAdd);
            ngaySinhHopDongAddText = itemView.findViewById(R.id.ngaySinhHopDongAddText);
            thuTuKhachTro = itemView.findViewById(R.id.thuTuKhachTro);
        }
    }
}
