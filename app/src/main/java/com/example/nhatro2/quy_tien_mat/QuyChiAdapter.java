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

public class QuyChiAdapter extends RecyclerView.Adapter<QuyChiAdapter.QuyChiViewHolder> {
    Context context;
    List<QuyChiModel> listQuyChi;

    public QuyChiAdapter(Context context, List<QuyChiModel> listQuyChi) {
        this.context = context;
        this.listQuyChi = listQuyChi;
    }

    @NonNull
    @Override
    public QuyChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quy_chi_item,parent,false);
        return new QuyChiAdapter.QuyChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuyChiViewHolder holder, int position) {
        QuyChiModel itemQuyChi = listQuyChi.get(position);
        String tienQuyChiItem = itemQuyChi.getTien();
        String timeQuyChiItem = itemQuyChi.getTime();

        holder.quyChiTien.setText(tienQuyChiItem+"đ");
        holder.quyChiTime.setText("Tháng "+timeQuyChiItem+": ");
    }

    @Override
    public int getItemCount() {
        return listQuyChi.size();
    }

    public class QuyChiViewHolder extends RecyclerView.ViewHolder {
        TextView quyChiTien,quyChiTime;
        public QuyChiViewHolder(@NonNull View itemView) {
            super(itemView);
            quyChiTime = itemView.findViewById(R.id.quyChiTime);
            quyChiTien = itemView.findViewById(R.id.quyChiTien);
        }
    }
}
