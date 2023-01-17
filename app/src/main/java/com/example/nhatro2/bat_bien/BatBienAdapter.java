package com.example.nhatro2.bat_bien;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.kha_bien.KhaBienModel;

import java.util.List;

public class BatBienAdapter extends RecyclerView.Adapter<BatBienAdapter.BatBienViewHolder> {
    Context context;
    List<BatBienModel> listBatBien;

    public BatBienAdapter(Context context, List<BatBienModel> listBatBien) {
        this.context = context;
        this.listBatBien = listBatBien;
    }

    @NonNull
    @Override
    public BatBienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bat_bien_item,parent,false);
        return new BatBienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BatBienViewHolder holder, int position) {
        BatBienModel detailBatBien = listBatBien.get(position);
        String lydo = detailBatBien.getLydo();
        int tien = detailBatBien.getTien();
        String ngay = detailBatBien.getNgay();
        String gio = detailBatBien.getGio();
        DecimalFormat formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formatter = new DecimalFormat("#,###,###");
            String tienChiFormat = formatter.format(tien);
            holder.tienBatBien.setText(""+tienChiFormat);
        }
        holder.lyDoBatBien.setText(lydo);
        holder.timeBatBien.setText(ngay);

    }

    @Override
    public int getItemCount() {
        return listBatBien.size();
    }

    public class BatBienViewHolder extends RecyclerView.ViewHolder {
        TextView lyDoBatBien, timeBatBien, tienBatBien;
        public BatBienViewHolder(@NonNull View itemView) {
            super(itemView);
            lyDoBatBien = itemView.findViewById(R.id.lyDoBatBien);
            timeBatBien = itemView.findViewById(R.id.timeBatBien);
            tienBatBien = itemView.findViewById(R.id.tienBatBien);
        }
    }
}
