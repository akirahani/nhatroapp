package com.example.nhatro2.tra_phong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class TienPhongTraPhongAdapter extends RecyclerView.Adapter<TienPhongTraPhongAdapter.TienPhongTraPhongViewHolder> {
    Context context;
    List<TienPhongTraModel> listPhongCanTra;

    public TienPhongTraPhongAdapter(Context context, List<TienPhongTraModel> listPhongCanTra) {
        this.context = context;
        this.listPhongCanTra = listPhongCanTra;
    }

    @NonNull
    @Override
    public TienPhongTraPhongAdapter.TienPhongTraPhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tien_phong_can_tra,parent,false);
        return new TienPhongTraPhongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TienPhongTraPhongAdapter.TienPhongTraPhongViewHolder holder, int position) {
        TienPhongTraModel itemTienPhongTra = listPhongCanTra.get(position);
        holder.thoiGianTienPhongTra.setText(itemTienPhongTra.getThoigian());
        holder.tienPhongCanTra.setText(itemTienPhongTra.getTien());
    }

    @Override
    public int getItemCount() {
        return listPhongCanTra.size();
    }

    public class TienPhongTraPhongViewHolder extends RecyclerView.ViewHolder {
        TextView tienPhongCanTra, thoiGianTienPhongTra;
        public TienPhongTraPhongViewHolder(@NonNull View itemView) {
            super(itemView);
            tienPhongCanTra = itemView.findViewById(R.id.tienPhongCanTra);
            thoiGianTienPhongTra = itemView.findViewById(R.id.thoiGianTienPhongTra);
        }
    }
}
