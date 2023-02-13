package com.example.nhatro2.tien_coc;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class TienCocAdapter extends RecyclerView.Adapter<TienCocAdapter.TienCocViewHolder> {
    Context context;
    List<TienCocModel> tienCocList;
    DetailClickCoc clickIdCoc;
    XuLiCoc xoaCoc;
    int idCocItem;

    public TienCocAdapter(Context context, List<TienCocModel> tienCocList, DetailClickCoc clickIdCoc, XuLiCoc xoaCoc) {
        this.context = context;
        this.tienCocList = tienCocList;
        this.clickIdCoc = clickIdCoc;
        this.xoaCoc = xoaCoc;
    }

    @NonNull
    @Override
    public TienCocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tien_coc_item, parent,false);
        return new TienCocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TienCocViewHolder holder, int position) {
        TienCocModel itemTienCoc = tienCocList.get(position);
        idCocItem = itemTienCoc.getId();
        String tenChuPhong = itemTienCoc.getTenchuphong();
        String tienFormat = itemTienCoc.getTienformat();
        int trangThaiXyLy = itemTienCoc.getDaxuly();
        String ngayCoc = itemTienCoc.getNgay();
        String gioCoc = itemTienCoc.getGio();

        holder.tenKhachCoc.setText(tenChuPhong);
        holder.tienCocDong.setText(tienFormat);
        if(trangThaiXyLy == 1){
            holder.checkCocItem.setBackgroundResource(R.color.cardThietBi);
            holder.xuLyCoc.setVisibility(View.GONE);
        }else{
            holder.checkCocItem.setBackgroundResource(R.color.phongThue);
            holder.xuLyCoc.setVisibility(View.VISIBLE);
            holder.xuLyCoc.setBackgroundResource(R.drawable.xoa);
        }
        int idCocItemXoa = itemTienCoc.getId();
        holder.xuLyCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoaCoc.clickXoaCoc(idCocItemXoa);
            }
        });
        holder.chiTietCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickIdCoc.clickCocDetail(idCocItem, tenChuPhong, tienFormat, ngayCoc, gioCoc, trangThaiXyLy);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tienCocList.size();
    }

    public class TienCocViewHolder extends RecyclerView.ViewHolder {
        TextView tenKhachCoc ,tienCocDong;
        ImageView xuLyCoc, chiTietCoc;
        LinearLayout checkCocItem;
        public TienCocViewHolder(@NonNull View itemView) {
            super(itemView);
            chiTietCoc = itemView.findViewById(R.id.chiTietCoc);
            tenKhachCoc = itemView.findViewById(R.id.tenKhachCoc);
            tienCocDong = itemView.findViewById(R.id.tienCocDong);
            xuLyCoc = itemView.findViewById(R.id.xuLyCoc);
            checkCocItem = itemView.findViewById(R.id.checkCocItem);
        }
    }
}
