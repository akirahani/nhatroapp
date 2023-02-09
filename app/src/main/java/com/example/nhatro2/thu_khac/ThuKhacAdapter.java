package com.example.nhatro2.thu_khac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class ThuKhacAdapter extends RecyclerView.Adapter<ThuKhacAdapter.ThuKhacViewHolder> {
    Context context;
    List<ThuKhacModel> listThuKhac;

    public ThuKhacAdapter(Context context, List<ThuKhacModel> listThuKhac) {
        this.context = context;
        this.listThuKhac = listThuKhac;
    }

    @NonNull
    @Override
    public ThuKhacAdapter.ThuKhacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.thu_khac_item, parent, false);
        return new ThuKhacViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThuKhacAdapter.ThuKhacViewHolder holder, int position) {
        ThuKhacModel thuKhacItem = listThuKhac.get(position);

    }

    @Override
    public int getItemCount() {
        return listThuKhac.size();
    }

    public class ThuKhacViewHolder extends RecyclerView.ViewHolder {
        public ThuKhacViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
