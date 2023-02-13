package com.example.nhatro2.tien_coc;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class TienCocAdapter extends RecyclerView.Adapter<TienCocAdapter.TienCocViewHolder> {
    Context context;
    List<TienCocModel> tienCocList;

    public TienCocAdapter(Context context, List<TienCocModel> tienCocList) {
        this.context = context;
        this.tienCocList = tienCocList;
    }

    @NonNull
    @Override
    public TienCocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tien_coc_item, parent,false);
        return new TienCocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TienCocViewHolder holder, int position) {
        TienCocModel itemTienCoc = tienCocList.get(position);
        holder.tenKhachCoc.setText(itemTienCoc.getTenchuphong());
        holder.tienCocDong.setText(itemTienCoc.getTienformat());
        if(itemTienCoc.getDaxuly() == 1){
            holder.checkCocItem.setBackgroundResource(R.color.cardThietBi);
            holder.xuLyCoc.setVisibility(View.GONE);
        }else{
            holder.checkCocItem.setBackgroundResource(R.color.phongThue);
            holder.xuLyCoc.setVisibility(View.VISIBLE);
            holder.xuLyCoc.setBackgroundResource(R.drawable.xoa);

        }
    }

    @Override
    public int getItemCount() {
        return tienCocList.size();
    }

    public class TienCocViewHolder extends RecyclerView.ViewHolder {
        TextView tenKhachCoc ,tienCocDong;
        ImageView xuLyCoc;
        LinearLayout checkCocItem;
        public TienCocViewHolder(@NonNull View itemView) {
            super(itemView);
            tenKhachCoc = itemView.findViewById(R.id.tenKhachCoc);
            tienCocDong = itemView.findViewById(R.id.tienCocDong);
            xuLyCoc = itemView.findViewById(R.id.xuLyCoc);
            checkCocItem = itemView.findViewById(R.id.checkCocItem);
        }
    }
}
