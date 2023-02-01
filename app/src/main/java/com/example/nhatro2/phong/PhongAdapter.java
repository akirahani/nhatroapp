package com.example.nhatro2.phong;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.PhongViewHolder> {

    Context context;
    List<PhongModel> phong;
    PhongTrongItemClick phongClick;
    ImageView tacVuTrong;
    View slideUp;
    ViewGroup khungNgoai;
    ArrayList<Integer> listRoomChoose;
    String listRoom;
    boolean isUp;
    public PhongAdapter(Context context, List<PhongModel> phong, PhongTrongItemClick phongClick) {
        this.context = context;
        this.phong = phong;
        this.phongClick = phongClick;
    }

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public PhongAdapter.PhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phong_trong_item, parent, false);
        tacVuTrong = view.findViewById(R.id.tacVuTrong);
        return new PhongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongAdapter.PhongViewHolder holder, int position) {
        PhongModel data = phong.get(position);
        int idPhong = data.getId();
        String ten = data.getTen();
        String khu = data.getKhu();
        int tang = data.getTang();
        int trangthai = data.getTrangthai();
//        String dichvu = data.getDichvu();
        int datcoc = data.getDatcoc();
        int chuphong = data.getChuphong();
        int gia = data.getGiaphong();

        holder.ten.setText(ten);
        holder.day.setText(khu);
        holder.tang.setText("-" + tang);

        SharedPreferences sharedPhong = context.getSharedPreferences("idPhong", Context.MODE_PRIVATE);
        SharedPreferences.Editor roomEditor = sharedPhong.edit();
        listRoom = sharedPhong.getString("items", "");
        if (listRoom.equals("")) {
            List<Integer> lst = new ArrayList<>();
            //Set color model
            if (lst.contains(idPhong)) {
                holder.ten.setTextColor(Color.rgb(46, 184, 75));
                holder.checkMulti.setChecked(true);
            } else {
                holder.ten.setTextColor(Color.rgb(0, 0, 0));
                holder.checkMulti.setChecked(false);
            }
        } else {
            List<Integer> lst = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                lst = Arrays.stream(listRoom.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                //Set color model
                if (lst.contains(idPhong)) {
                    holder.ten.setTextColor(Color.rgb(46, 184, 75));
                    holder.checkMulti.setChecked(true);
                } else {
                    holder.ten.setTextColor(Color.rgb(0, 0, 0));
                    holder.checkMulti.setChecked(false);
                }
            }
        }

        tacVuTrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), PhongEdit.class);
                intent.putExtra("idPhong", idPhong);
                intent.putExtra("tenPhong", ten);
                intent.putExtra("day", khu);
                intent.putExtra("tang", tang);
                intent.putExtra("trangthai", trangthai);
                intent.putExtra("gia", gia);
                context.startActivity(intent);
            }
        });

        holder.checkMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> arrInt = new ArrayList<>();
                //Xử lý lưu mảng số nguyên sản phẩm
                if (listRoom.equals("")) {
                    //Tạo mảng
                    arrInt.add(idPhong);
                    //Convert list to string
                    String convertString = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        convertString = arrInt.stream().map(String::valueOf).collect(Collectors.joining(","));
                    }
                    roomEditor.putString("items", convertString);
                    roomEditor.apply();
                    notifyDataSetChanged();
                } else {
                    //Convert string to List
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        arrInt = Arrays.stream(listRoom.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                    }

                    if (arrInt.contains(idPhong)) {
                        //Xóa khỏi list
                        arrInt.remove(Integer.valueOf(idPhong));
                        //Convert list to string
                        String convertString = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            convertString = arrInt.stream().map(String::valueOf).collect(Collectors.joining(","));
                        }
                        roomEditor.putString("items", convertString);
                        roomEditor.apply();
                        notifyDataSetChanged();
                    } else {
                        //Xử lý lưu thêm
                        arrInt.add(idPhong);
                        //Convert list to string
                        String convertString = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            convertString = arrInt.stream().map(String::valueOf).collect(Collectors.joining(","));
                        }
                        roomEditor.putString("items", convertString);
                        roomEditor.apply();
                        notifyDataSetChanged();
                    }

                }
                listRoom = sharedPhong.getString("items", "");
                phongClick.itemOnClick(arrInt.size());

                ApiQH.apiQH.phongChecked(listRoom).enqueue(new Callback<List<PhongModel>>() {
                    @Override
                    public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                        Log.d("", "" + response.body());
                    }

                    @Override
                    public void onFailure(Call<List<PhongModel>> call, Throwable t) {
                        Log.d("err","loi phong hien thi");
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {

        return phong.size();
    }

    public static class PhongViewHolder extends RecyclerView.ViewHolder {
        TextView id, ten, vitri, trangthai, dichvu, datcoc, khach, day, tang;
        CheckBox checkMulti;

        public PhongViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenPhongTrong);
            day = itemView.findViewById(R.id.dayTrong);
            tang = itemView.findViewById(R.id.tangTrong);
            checkMulti = itemView.findViewById(R.id.checkMulti);
        }

    }


}