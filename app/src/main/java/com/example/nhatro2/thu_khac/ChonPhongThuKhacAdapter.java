package com.example.nhatro2.thu_khac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.dong_tien.ChonPhongIdClick;
import com.example.nhatro2.phong.Phong;
import com.example.nhatro2.phong.PhongModel;

import java.util.List;

public class ChonPhongThuKhacAdapter extends RecyclerView.Adapter<ChonPhongThuKhacAdapter.ChonPhongThuKhacViewHolder> {
    Context context;
    List<PhongModel> listPhong;
    ChonPhongThuKhacClick clickPhongId;

    public ChonPhongThuKhacAdapter(Context context, List<PhongModel> listPhong, ChonPhongThuKhacClick clickPhongId) {
        this.context = context;
        this.listPhong = listPhong;
        this.clickPhongId = clickPhongId;
    }

    @NonNull
    @Override
    public ChonPhongThuKhacAdapter.ChonPhongThuKhacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_thu_khac_item, parent,false);
        return new ChonPhongThuKhacViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChonPhongThuKhacAdapter.ChonPhongThuKhacViewHolder holder, int position) {
        PhongModel phongThuKhacItem = listPhong.get(position);
        int idPhong = phongThuKhacItem.getId();
        String tenPhong = phongThuKhacItem.getTen();
        holder.chonPhongThuKhacClick.setText(tenPhong);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickPhongId.clickPhongThuKhac(idPhong, tenPhong);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listPhong.size();
    }

    public class ChonPhongThuKhacViewHolder extends RecyclerView.ViewHolder {
        TextView chonPhongThuKhacClick;
        public ChonPhongThuKhacViewHolder(@NonNull View itemView) {
            super(itemView);
            chonPhongThuKhacClick = itemView.findViewById(R.id.chonPhongThuKhacClick);
        }
    }
}
