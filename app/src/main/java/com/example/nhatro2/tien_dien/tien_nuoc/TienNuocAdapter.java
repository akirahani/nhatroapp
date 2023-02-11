package com.example.nhatro2.tien_dien.tien_nuoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class TienNuocAdapter extends RecyclerView.Adapter<TienNuocAdapter.TienNuocViewHolder> {
    Context context;
    List<TienNuocModel> phongNuoc;
    NuocItemClick nuocItemClick;

    public TienNuocAdapter(Context context, List<TienNuocModel> phongNuoc, NuocItemClick nuocItemClick) {
        this.context = context;
        this.phongNuoc = phongNuoc;
        this.nuocItemClick = nuocItemClick;
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
        int soDauNuocText = listPhongNuoc.getSodau();
        int soCuoiNuocText = listPhongNuoc.getSocuoi();
        holder.tenPhongNuoc.setText(tenPhong);
        holder.soDauNuoc.setText("" + soDauNuocText);
        holder.soCuoiNuoc.setText("" +soCuoiNuocText );

        holder.tacVuPhongNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuocItemClick.itemOnClick(tenPhong);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phongNuoc.size();
    }
    public class TienNuocViewHolder extends RecyclerView.ViewHolder {
        TextView tenPhongNuoc;
        EditText soDauNuoc, soCuoiNuoc;
        ImageView tacVuPhongNuoc;

        public TienNuocViewHolder(@NonNull View itemView) {
            super(itemView);
            tenPhongNuoc = itemView.findViewById(R.id.tenPhongNuoc);
            soDauNuoc = itemView.findViewById(R.id.soDauNuoc);
            soCuoiNuoc = itemView.findViewById(R.id.soCuoiNuoc);
            tacVuPhongNuoc = itemView.findViewById(R.id.tacVuPhongNuoc);
        }
    }
}
