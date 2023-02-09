package com.example.nhatro2.uu_dai;

import android.content.Context;
import android.media.Image;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;

import java.util.List;

public class UuDaiAdapter extends RecyclerView.Adapter<UuDaiAdapter.UuDaiViewHolder> {
    Context context;
    List<UuDaiModel> listUuDai;
    idUuDaiEditClick clickIdCouponsEdit;

    public UuDaiAdapter(Context context, List<UuDaiModel> listUuDai, idUuDaiEditClick clickIdCouponsEdit) {
        this.context = context;
        this.listUuDai = listUuDai;
        this.clickIdCouponsEdit = clickIdCouponsEdit;
    }

    @NonNull
    @Override
    public UuDaiAdapter.UuDaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.uu_dai_item, parent,false);
        return new UuDaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UuDaiAdapter.UuDaiViewHolder holder, int position) {
        UuDaiModel uuDaiItem = listUuDai.get(position);
        int idCoupons = uuDaiItem.getId();
        String tenUuDai = uuDaiItem.getTen();
        int soNgayUuDai = uuDaiItem.getSo();
        int apDung = uuDaiItem.getApdung();

        holder.tenUuDai.setText(uuDaiItem.getTen());
        if(uuDaiItem.getApdung() == 1){
            holder.statusUuDai.setText(Html.fromHtml("<font color='#71a6d5'>Áp dụng!</font>"));
        }else{
            holder.statusUuDai.setText(Html.fromHtml("<font color='#f03838'>Không áp dụng!</font>"));
        }

        holder.tenUuDai.setText(uuDaiItem.getTen());
        holder.tacVuUuDai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickIdCouponsEdit.editCouponsClick(idCoupons,tenUuDai,soNgayUuDai,apDung);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUuDai.size();
    }

    public class UuDaiViewHolder extends RecyclerView.ViewHolder {
        TextView tenUuDai, statusUuDai;
        ImageView tacVuUuDai;
        public UuDaiViewHolder(@NonNull View itemView) {
            super(itemView);
            tenUuDai = itemView.findViewById(R.id.tenUuDai);
            statusUuDai = itemView.findViewById(R.id.statusUuDai);
            tacVuUuDai = itemView.findViewById(R.id.tacVuUuDai);
        }
    }
}
