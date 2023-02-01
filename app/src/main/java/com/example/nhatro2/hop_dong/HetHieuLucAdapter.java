package com.example.nhatro2.hop_dong;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class HetHieuLucAdapter extends RecyclerView.Adapter<HetHieuLucAdapter.HetHieuLucViewHolder>
{
    Context context;
    List<HopDongModel> listHopDongHetHieuLuc;

    public HetHieuLucAdapter(Context context, List<HopDongModel> listHopDongHetHieuLuc) {
        this.context = context;
        this.listHopDongHetHieuLuc = listHopDongHetHieuLuc;
    }

    @NonNull
    @Override
    public HetHieuLucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.het_hieu_luc_item,parent,false);
        return new HetHieuLucAdapter.HetHieuLucViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HetHieuLucViewHolder holder, int position) {
        HopDongModel hetHieuLucSingle = listHopDongHetHieuLuc.get(position);
        String chuphong = hetHieuLucSingle.getChuphong();
        String phongTen = hetHieuLucSingle.getPhong();
        int idHD = hetHieuLucSingle.getId();
        holder.tenKhachHetHieuLuc.setText(chuphong);
        holder.soHopDongHHL.setText(""+idHD);

        holder.tacVuHopDongHetHieuLuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(),HopDongDetail.class);
                String ngayBatDauText = hetHieuLucSingle.getNgaybatdau();
                String ngayKetThucText  = hetHieuLucSingle.getNgayketthuc();
                String strThietBi = hetHieuLucSingle.getThietbi();
                String strKhach =  hetHieuLucSingle.getKhach();
                intent.putExtra("thietbi",strThietBi);
                intent.putExtra("khachthue",strKhach);
                intent.putExtra("ketthuc",ngayKetThucText);
                intent.putExtra("batdau",ngayBatDauText);
                intent.putExtra("phong",phongTen);
                intent.putExtra("idHopDong",idHD);
                intent.putExtra("phong",phongTen);
                intent.putExtra("chuphong", chuphong);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHopDongHetHieuLuc.size();
    }

    public class HetHieuLucViewHolder extends RecyclerView.ViewHolder {
        TextView tenKhachHetHieuLuc, soHopDongHHL;
        ImageView tacVuHopDongHetHieuLuc;
        public HetHieuLucViewHolder(@NonNull View itemView) {
            super(itemView);
            tenKhachHetHieuLuc = itemView.findViewById(R.id.tenKhachHetHieuLuc);
            soHopDongHHL = itemView.findViewById(R.id.soHopDongHHL);
            tacVuHopDongHetHieuLuc = itemView.findViewById(R.id.tacVuHopDongHetHieuLuc);
        }
    }
}
