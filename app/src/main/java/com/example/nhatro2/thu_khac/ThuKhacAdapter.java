package com.example.nhatro2.thu_khac;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThuKhacAdapter extends RecyclerView.Adapter<ThuKhacAdapter.ThuKhacViewHolder> {
    Context context;
    List<ThuKhacModel> listThuKhac;
    @NonNull
    @Override
    public ThuKhacAdapter.ThuKhacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ThuKhacAdapter.ThuKhacViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ThuKhacViewHolder extends RecyclerView.ViewHolder {
        public ThuKhacViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
