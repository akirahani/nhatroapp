package com.example.nhatro2.hop_dong;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.dich_vu.DichVuModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThietBiAddAdapter extends RecyclerView.Adapter<ThietBiAddAdapter.ThietBiAddAdapterViewHolder> {
    Context context;
    List<DichVuModel> listThietBi;
    String listThietBiString;

    public ThietBiAddAdapter(Context context, List<DichVuModel> listThietBi) {
        this.context = context;
        this.listThietBi = listThietBi;
    }

    @NonNull
    @Override
    public ThietBiAddAdapter.ThietBiAddAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thiet_bi_hop_dong_item, parent, false);
        return new ThietBiAddAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThietBiAddAdapter.ThietBiAddAdapterViewHolder holder, int position) {
        DichVuModel thietbi = listThietBi.get(position);
        int idThietBi = thietbi.getId();
        String tenThietBi = thietbi.getTen();
        holder.tenThietBi.setText(tenThietBi);

        // Khai báo mảng dữ liệu thiết bị
        SharedPreferences shpThietBi = context.getSharedPreferences("idThietBiHopDong",Context.MODE_PRIVATE);
        SharedPreferences.Editor thietBiEdit = shpThietBi.edit();
        listThietBiString = shpThietBi.getString("itemThietBi","");

        // check thiet bi hien thi
        if (listThietBiString.equals("")) {
            List<Integer> listThietBiChoose = new ArrayList<>();
            //Set color model
            if (listThietBiChoose.contains(idThietBi)) {
                holder.imgThietBi.setImageResource(R.drawable.checked);
            } else {
                holder.imgThietBi.setImageResource(R.drawable.uncheck);
            }
        } else {
            List<Integer> listThietBiChoose = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                listThietBiChoose = Arrays.stream(listThietBiString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            }
            //Set color model
            if (listThietBiChoose.contains(idThietBi)) {
                holder.imgThietBi.setImageResource(R.drawable.checked);
            } else {
                holder.imgThietBi.setImageResource(R.drawable.uncheck);
            }
        }



        holder.imgThietBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Integer> listThietBiChoose = new ArrayList<>();
                if(listThietBiString.equals("")){
                    listThietBiChoose.add(idThietBi);
                    String idThietBiConvert = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        idThietBiConvert = listThietBiChoose.stream().map(String::valueOf).collect(Collectors.joining(","));
                    }
                    thietBiEdit.putString("itemThietBi",idThietBiConvert);
                    thietBiEdit.apply();
                    notifyDataSetChanged();

                }else{

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        listThietBiChoose = Arrays.stream(listThietBiString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                    }
                    if(listThietBiChoose.contains(idThietBi)){
                        listThietBiChoose.remove(Integer.valueOf(idThietBi));
                        String idThietBiConvert = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            idThietBiConvert = listThietBiChoose.stream().map(String::valueOf).collect(Collectors.joining(","));
                        }
                        thietBiEdit.putString("itemThietBi",idThietBiConvert);
                        thietBiEdit.apply();
                        notifyDataSetChanged();

                    }else{
                        listThietBiChoose.add(idThietBi);
                        String idThietBiConvert = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            idThietBiConvert = listThietBiChoose.stream().map(String::valueOf).collect(Collectors.joining(","));
                        }
                        thietBiEdit.putString("itemThietBi",idThietBiConvert);
                        thietBiEdit.apply();
                        notifyDataSetChanged();
                    }
                }
                listThietBiString = shpThietBi.getString("itemThietBi","");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listThietBi.size();
    }

    public class ThietBiAddAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThietBi;
        TextView tenThietBi;

        public ThietBiAddAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThietBi = itemView.findViewById(R.id.imageServiceAdd);
            tenThietBi = itemView.findViewById(R.id.textThietBiAdd);
        }
    }
}
