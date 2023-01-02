package com.example.nhatro2.tien_dien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class TienDienAdapter extends RecyclerView.Adapter<TienDienAdapter.TienDienViewHolder> {
    Context context;
    List<TienDienModel> phongDien;

    public TienDienAdapter(Context context, List<TienDienModel> phongDien) {
        this.context = context;
        this.phongDien = phongDien;
    }

    @NonNull
    @Override
    public TienDienAdapter.TienDienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_dien_item,parent,false);
        return new TienDienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TienDienAdapter.TienDienViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return phongDien.size();
    }

    public class TienDienViewHolder extends RecyclerView.ViewHolder {
        public TienDienViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
