package com.example.nhatro2.quy_tien_mat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class QuyThuAdapter extends RecyclerView.Adapter<QuyThuAdapter.QuyThuViewHolder> {
    Context context;
    List<QuyThuModel> listQuyThu;

    public QuyThuAdapter(Context context, List<QuyThuModel> listQuyThu) {
        this.context = context;
        this.listQuyThu = listQuyThu;
    }

    @NonNull
    @Override
    public QuyThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quy_thu_item,parent,false);
        return new QuyThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuyThuViewHolder holder, int position) {
        QuyThuModel itemQuyThu = listQuyThu.get(position);
        String tienQuyThuItem = itemQuyThu.getTien();
        String timeQuyThuItem = itemQuyThu.getTime();

        holder.quyThuTien.setText(tienQuyThuItem+"đ");
        holder.quyThuTime.setText("Tháng "+timeQuyThuItem+": ");
    }

    @Override
    public int getItemCount() {
        return listQuyThu.size();
    }

    public class QuyThuViewHolder extends RecyclerView.ViewHolder {
        TextView quyThuTien,quyThuTime;
        public QuyThuViewHolder(@NonNull View itemView) {
            super(itemView);
            quyThuTime = itemView.findViewById(R.id.quyThuTime);
            quyThuTien = itemView.findViewById(R.id.quyThuTien);
        }
    }
}
