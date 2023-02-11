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

public class ThuKhacAdapter extends RecyclerView.Adapter<ThuKhacAdapter.ThuKhacViewHolder>{
    Context context;
    List<ThuKhacModel> listThuKhac;
    DetailThuKhac clickIdThuKhac;

    public ThuKhacAdapter(Context context, List<ThuKhacModel> listThuKhac, DetailThuKhac clickIdThuKhac) {
        this.context = context;
        this.listThuKhac = listThuKhac;
        this.clickIdThuKhac = clickIdThuKhac;
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
        int idPhongThuKhac = thuKhacItem.getId();
        String tenPhongThuKhac = thuKhacItem.getPhong();
        String lyDoThuKhac = thuKhacItem.getLydo();
        String tienThuKhacFormat = thuKhacItem.getTienformat();
        int tienThuKhac = thuKhacItem.getTien();
        String time = thuKhacItem.getNgay()+" - "+thuKhacItem.getGio();
        holder.tienThuKhac.setText(tienThuKhacFormat);
        holder.lyDoThuKhac.setText(lyDoThuKhac);
        holder.phongThuKhac.setText(tenPhongThuKhac);
        holder.detailThuKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickIdThuKhac.clickIdThuKhac(idPhongThuKhac, tenPhongThuKhac, lyDoThuKhac, tienThuKhacFormat, tienThuKhac, time);
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
