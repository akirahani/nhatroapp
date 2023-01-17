package com.example.nhatro2.kha_bien;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class KhaBienAdapter extends RecyclerView.Adapter<KhaBienAdapter.KhaBienViewHolder> {
    Context context;
    List<KhaBienModel> listKhaBien;

    public KhaBienAdapter(Context context, List<KhaBienModel> listKhaBien) {
        this.context = context;
        this.listKhaBien = listKhaBien;
    }

    @NonNull
    @Override
    public KhaBienAdapter.KhaBienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kha_bien_item, parent, false);
        return new KhaBienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhaBienAdapter.KhaBienViewHolder holder, int position) {
        KhaBienModel detailKhaBien = listKhaBien.get(position);
        String lydo = detailKhaBien.getLydo();
        int tien = detailKhaBien.getTien();
        String ngay = detailKhaBien.getNgay();
        String gio = detailKhaBien.getGio();
        DecimalFormat formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formatter = new DecimalFormat("#,###,###");
            String tienChiFormat = formatter.format(tien);
            holder.tienKhaBien.setText(""+tienChiFormat);
        }
        holder.lyDoKhaBien.setText(lydo);
        holder.timeKhaBien.setText(ngay);
    }

    @Override
    public int getItemCount() {
        return listKhaBien.size();
    }

    public class KhaBienViewHolder extends RecyclerView.ViewHolder {
        TextView tienKhaBien,lyDoKhaBien, timeKhaBien;
        public KhaBienViewHolder(@NonNull View itemView) {
            super(itemView);
            tienKhaBien = itemView.findViewById(R.id.tienKhaBien);
            lyDoKhaBien = itemView.findViewById(R.id.lyDoKhaBien);
            timeKhaBien = itemView.findViewById(R.id.timeKhaBien);
        }
    }
}
