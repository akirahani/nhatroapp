package com.example.nhatro2.thu_khac;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import org.w3c.dom.Text;

import java.util.List;

public class ThuKhacAdapter extends RecyclerView.Adapter<ThuKhacAdapter.ThuKhacViewHolder> {
    Context context;
    List<ThuKhacModel> listThuKhac;

    public ThuKhacAdapter(Context context, List<ThuKhacModel> listThuKhac) {
        this.context = context;
        this.listThuKhac = listThuKhac;
    }

    @NonNull
    @Override
    public ThuKhacAdapter.ThuKhacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.thu_khac_item, parent, false);
        return new ThuKhacViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThuKhacAdapter.ThuKhacViewHolder holder, int position) {
        ThuKhacModel thuKhacItem = listThuKhac.get(position);
        holder.tienThuKhac.setText(thuKhacItem.getTien());
        holder.lyDoThuKhac.setText(thuKhacItem.getLydo());
        holder.phongThuKhac.setText(thuKhacItem.getPhong());
        holder.detailThuKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listThuKhac.size();
    }

    public class ThuKhacViewHolder extends RecyclerView.ViewHolder {
        TextView tienThuKhac, lyDoThuKhac, phongThuKhac;
        ImageView detailThuKhac;
        public ThuKhacViewHolder(@NonNull View itemView) {
            super(itemView);
            tienThuKhac = itemView.findViewById(R.id.tienThuKhac);
            lyDoThuKhac = itemView.findViewById(R.id.lyDoThuKhac);
            detailThuKhac = itemView.findViewById(R.id.detailThuKhac);
            phongThuKhac = itemView.findViewById(R.id.phongThuKhac);
        }
    }
}
