package com.example.nhatro2.doi_thiet_bi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.dich_vu.DichVuModel;

import java.util.List;

public class PhongThietBiAdapter extends RecyclerView.Adapter<PhongThietBiAdapter.PhongThietBiViewHolder> {
    Context context;
    List<DichVuModel> listThietBi;
    int mCheckedPosition = -1;

    public PhongThietBiAdapter(Context context, List<DichVuModel> listThietBi) {
        this.context = context;
        this.listThietBi = listThietBi;
    }

    @NonNull
    @Override
    public PhongThietBiAdapter.PhongThietBiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_thiet_bi_click_item,parent,false);
        return new PhongThietBiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongThietBiAdapter.PhongThietBiViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DichVuModel dichVuItem = listThietBi.get(position);
        holder.thietBiPhongThietBiItem.setText(dichVuItem.getTen());
        holder.thietBiChecked.setId(dichVuItem.getId());
        if(dichVuItem.getId() == 1){
            holder.hinhThietBi.setBackgroundResource(R.drawable.tu_lanh);
        }else if(dichVuItem.getId() == 2){
            holder.hinhThietBi.setBackgroundResource(R.drawable.may_giat);
        }else if(dichVuItem.getId() == 3){
            holder.hinhThietBi.setBackgroundResource(R.drawable.dieu_hoa);
        }else{
            holder.hinhThietBi.setBackgroundResource(R.drawable.xac_xe_dien);
        }

        holder.thietBiChecked.setOnCheckedChangeListener(null);
        holder.thietBiChecked.setChecked(position == mCheckedPosition);
        holder.thietBiChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCheckedPosition = position;
                notifyDataSetChanged();
                SharedPreferences thietBiChuyen = context.getSharedPreferences("thietBiChuyen",Context.MODE_PRIVATE);
                SharedPreferences.Editor thietBiChuyenEdit;
                thietBiChuyenEdit = thietBiChuyen.edit();
                thietBiChuyenEdit.putInt("idThietBi",dichVuItem.getId());
                thietBiChuyenEdit.commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return listThietBi.size();
    }

    public class PhongThietBiViewHolder extends RecyclerView.ViewHolder {
        TextView thietBiPhongThietBiItem;
        ImageView hinhThietBi;
        RadioButton thietBiChecked;
        public PhongThietBiViewHolder(@NonNull View itemView) {
            super(itemView);
            thietBiPhongThietBiItem = itemView.findViewById(R.id.thietBiPhongThietBiItem);
            hinhThietBi = itemView.findViewById(R.id.hinhThietBi);
            thietBiChecked = itemView.findViewById(R.id.thietBiChecked);
        }
    }
}
