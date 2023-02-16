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
import com.example.nhatro2.phong.PhongModel;
import com.example.nhatro2.phong.PhongModel;

import java.util.List;

public class ChonPhongTienAdapter extends RecyclerView.Adapter<ChonPhongTienAdapter.ChonPhongTienViewHolder> {
    
    Context context;
    List<PhongModel> listPhong;
    ChonPhongIdClick clickPhongId;

    public ChonPhongTienAdapter(Context context, List<PhongModel> listPhong, ChonPhongIdClick clickPhongId) {
        this.context = context;
        this.listPhong = listPhong;
        this.clickPhongId = clickPhongId;
    }


    @NonNull
    @Override
    public ChonPhongTienAdapter.ChonPhongTienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_chon_dong_tien_item,parent,false);
        return new ChonPhongTienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChonPhongTienAdapter.ChonPhongTienViewHolder holder, int position) {
        PhongModel getPhong = listPhong.get(position);
        String tenPhong = getPhong.getTen();
        int idPhong = getPhong.getId();
        int chuPhongChon = getPhong.getChuphong();
        holder.chonPhongTienClick.setText(tenPhong);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickPhongId.clickPhongID(idPhong,tenPhong,chuPhongChon);
                Log.d("chu phhong dau",""+chuPhongChon);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPhong.size();
    }

    public class ChonPhongTienViewHolder extends RecyclerView.ViewHolder {
        TextView chonPhongTienClick;
        public ChonPhongTienViewHolder(@NonNull View itemView) {
            super(itemView);
            chonPhongTienClick = itemView.findViewById(R.id.chonPhongTienClick);
        }
    }
}
