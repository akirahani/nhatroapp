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

public class ConHieuLucAdapter  extends RecyclerView.Adapter<ConHieuLucAdapter.ConHieuLucViewHolder> {
    Context context;
    List<HopDongModel> listHopDongHieuLuc;

    public ConHieuLucAdapter(Context context, List<HopDongModel> listHopDongHieuLuc) {
        this.context = context;
        this.listHopDongHieuLuc = listHopDongHieuLuc;
    }

    @NonNull
    @Override
    public ConHieuLucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.con_hieu_luc_item,parent,false);
        return new ConHieuLucViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConHieuLucViewHolder holder, int position) {
        HopDongModel hopDongSingle = listHopDongHieuLuc.get(position);
        String chuphong = hopDongSingle.getChuphong();
        int idHD = hopDongSingle.getId();
        String phongTen = hopDongSingle.getPhong();
        holder.tenKhachConHieuLuc.setText(chuphong);
        holder.soHopDong.setText(""+idHD);

        holder.tacVuHopDongConHieuLuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ngayBatDauText = hopDongSingle.getNgaybatdau();
                String ngayKetThucText  = hopDongSingle.getNgayketthuc();
                String strThietBi = hopDongSingle.getThietbi();
                String strKhach = hopDongSingle.getKhach();

                Intent intent = new Intent(context.getApplicationContext(),HopDongDetail.class);
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
        return listHopDongHieuLuc.size();
    }

    public class ConHieuLucViewHolder extends RecyclerView.ViewHolder {
        TextView tenKhachConHieuLuc, soHopDong;
        ImageView tacVuHopDongConHieuLuc;
        public ConHieuLucViewHolder(@NonNull View itemView) {
            super(itemView);
            tenKhachConHieuLuc = itemView.findViewById(R.id.tenKhachConHieuLuc);
            soHopDong = itemView.findViewById(R.id.soHopDong);
            tacVuHopDongConHieuLuc = itemView.findViewById(R.id.tacVuHopDongConHieuLuc);
        }
    }
}
