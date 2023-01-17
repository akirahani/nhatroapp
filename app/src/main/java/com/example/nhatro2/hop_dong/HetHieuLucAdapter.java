package com.example.nhatro2.hop_dong;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HetHieuLucAdapter extends RecyclerView.Adapter<HetHieuLucAdapter.HetHieuLucViewHolder>
{
    Context context;

    @NonNull
    @Override
    public HetHieuLucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HetHieuLucViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HetHieuLucViewHolder extends RecyclerView.ViewHolder {
        public HetHieuLucViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
