package com.example.nhatro2.tien_dien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.tien_nuoc.TienNuocModel;

import java.text.DecimalFormat;
import java.util.List;

public class TienDienAdapter extends RecyclerView.Adapter<TienDienAdapter.TienDienViewHolder> {
    Context context;
    List<TienDienModel> phongDien;
    DienItemClick onlickPhongDien;

    public TienDienAdapter(Context context, List<TienDienModel> phongDien, DienItemClick onlickPhongDien) {
        this.context = context;
        this.phongDien = phongDien;
        this.onlickPhongDien = onlickPhongDien;
    }

    @NonNull
    @Override
    public TienDienAdapter.TienDienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_dien_item,parent,false);
        return new TienDienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TienDienAdapter.TienDienViewHolder holder, int position) {

        TienDienModel listPhongDien = phongDien.get(position);

        String tenPhong = listPhongDien.getPhong();
        int soDauDienText = listPhongDien.getSodau();
        int soCuoiDienText = listPhongDien.getSocuoi();

        holder.tenPhongDien.setText(tenPhong);
        holder.soDauDien.setText("" + soDauDienText);
        holder.soCuoiDien.setText("" + soCuoiDienText);

        holder.tacVuPhongDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onlickPhongDien.itemOnClick(tenPhong);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phongDien.size();
    }

    public class TienDienViewHolder extends RecyclerView.ViewHolder {
        TextView tenPhongDien, soDauDien, soCuoiDien;
        ImageView tacVuPhongDien;
        public TienDienViewHolder(@NonNull View itemView) {
            super(itemView);
            tenPhongDien = itemView.findViewById(R.id.tenPhongDien);
            soDauDien = itemView.findViewById(R.id.soDauDien);
            soCuoiDien = itemView.findViewById(R.id.soCuoiDien);
            tacVuPhongDien = itemView.findViewById(R.id.tacVuPhongDien);
        }
    }
}
