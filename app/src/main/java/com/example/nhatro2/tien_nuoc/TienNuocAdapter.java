package com.example.nhatro2.tien_nuoc;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.tien_dien.TienDienAdapter;

import java.util.List;

public class TienNuocAdapter extends RecyclerView.Adapter<TienNuocAdapter.TienNuocViewHolder> {
    Context context;
    List<TienNuocModel> phongNuoc;

    public TienNuocAdapter(Context context, List<TienNuocModel> phongNuoc) {
        this.context = context;
        this.phongNuoc = phongNuoc;
    }

    @NonNull
    @Override
    public TienNuocAdapter.TienNuocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_nuoc_item, parent, false);
        return new TienNuocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TienNuocAdapter.TienNuocViewHolder holder, int position) {

        TienNuocModel listPhongNuoc = phongNuoc.get(position);

        String tenPhong = listPhongNuoc.getPhong();
        int soNuocText = listPhongNuoc.getSocuoi();
        int tienNuocText = listPhongNuoc.getTien();

        holder.tenPhongNuoc.setText(tenPhong);
        holder.soNuoc.setText("" + soNuocText);
        holder.tienNuoc.setText("" + tienNuocText);
    }

    @Override
    public int getItemCount() {
        return phongNuoc.size();
    }

    public class TienNuocViewHolder extends RecyclerView.ViewHolder {
        TextView tenPhongNuoc, soNuoc, tienNuoc;
        ImageView tacVuPhongNuoc;

        public TienNuocViewHolder(@NonNull View itemView) {
            super(itemView);
            tenPhongNuoc = itemView.findViewById(R.id.tenPhongNuoc);
            soNuoc = itemView.findViewById(R.id.soNuoc);
            tienNuoc = itemView.findViewById(R.id.tienNuoc);
            tacVuPhongNuoc = itemView.findViewById(R.id.tacVuPhongNuoc);
        }
    }
}
