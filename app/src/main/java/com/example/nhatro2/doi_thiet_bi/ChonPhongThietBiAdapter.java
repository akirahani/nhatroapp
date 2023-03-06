package com.example.nhatro2.doi_thiet_bi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.phong.PhongModel;

import org.w3c.dom.Text;

import java.util.List;

public class ChonPhongThietBiAdapter extends RecyclerView.Adapter<ChonPhongThietBiAdapter.ChonPhongThietBiViewHolder> {
    Context context;
    List<PhongModel> listPhongThietBi;
    ClickPhongThietBi clickPhongThietBi;

    public ChonPhongThietBiAdapter(Context context, List<PhongModel> listPhongThietBi, ClickPhongThietBi clickPhongThietBi) {
        this.context = context;
        this.listPhongThietBi = listPhongThietBi;
        this.clickPhongThietBi = clickPhongThietBi;
    }

    @NonNull
    @Override
    public ChonPhongThietBiAdapter.ChonPhongThietBiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_thiet_bi_item,parent,false);
        return new ChonPhongThietBiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChonPhongThietBiAdapter.ChonPhongThietBiViewHolder holder, int position) {
        PhongModel phongItem = listPhongThietBi.get(position);
        String tenPhong = phongItem.getTen();
        int idPhong = phongItem.getId();
        int chuPhongChon = phongItem.getChuphong();
        int trangthaiphong = phongItem.getTrangthai();
        holder.chonPhongThietBiClick.setText(tenPhong);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickPhongThietBi.clickPhongID(idPhong,tenPhong,chuPhongChon,trangthaiphong);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPhongThietBi.size();
    }

    public class ChonPhongThietBiViewHolder extends RecyclerView.ViewHolder {
        TextView chonPhongThietBiClick;
        public ChonPhongThietBiViewHolder(@NonNull View itemView) {
            super(itemView);
            chonPhongThietBiClick = itemView.findViewById(R.id.chonPhongThietBiClick);
        }
    }
}
