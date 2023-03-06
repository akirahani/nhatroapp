package com.example.nhatro2.chi_khac;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.bat_bien.BatBienAdapter;
import com.example.nhatro2.bat_bien.BatBienModel;

import java.util.List;

public class ChiKhacAdapter extends RecyclerView.Adapter<ChiKhacAdapter.ChiKhacViewHolder> {
   Context context;
   List<ChiKhacModel> listChiKhac;

    public ChiKhacAdapter(Context context, List<ChiKhacModel> listChiKhac) {
        this.context = context;
        this.listChiKhac = listChiKhac;
    }

    @NonNull
    @Override
    public ChiKhacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chi_khac_item,parent,false);
        return new ChiKhacViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiKhacViewHolder holder, int position) {
        ChiKhacModel detailChiKhac = listChiKhac.get(position);
        String lydo = detailChiKhac.getLydo();
        int tien = detailChiKhac.getTien();
        String ngay = detailChiKhac.getNgay();
        String gio = detailChiKhac.getGio();
        DecimalFormat formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formatter = new DecimalFormat("#,###,###");
            String tienChiFormat = formatter.format(tien);
            holder.tienChiKhac.setText(""+tienChiFormat);
        }
        holder.lyDoChiKhac.setText(lydo);
        holder.timeChiKhac.setText(ngay);
    }

    @Override
    public int getItemCount() {
        return listChiKhac.size();
    }

    public class ChiKhacViewHolder extends RecyclerView.ViewHolder {
        TextView lyDoChiKhac, timeChiKhac, tienChiKhac;
        public ChiKhacViewHolder(@NonNull View itemView) {
            super(itemView);
            lyDoChiKhac = itemView.findViewById(R.id.lyDoChiKhac);
            timeChiKhac = itemView.findViewById(R.id.timeChiKhac);
            tienChiKhac = itemView.findViewById(R.id.tienChiKhac);
        }
    }
}
