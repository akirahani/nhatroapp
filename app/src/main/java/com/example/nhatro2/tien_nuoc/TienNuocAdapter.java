package com.example.nhatro2.tien_nuoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        View view = LayoutInflater.from(context).inflate(R.layout.phong_nuoc_item,parent,false);
        return new TienNuocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TienNuocAdapter.TienNuocViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return phongNuoc.size();
    }

    public class TienNuocViewHolder extends RecyclerView.ViewHolder {
        public TienNuocViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
