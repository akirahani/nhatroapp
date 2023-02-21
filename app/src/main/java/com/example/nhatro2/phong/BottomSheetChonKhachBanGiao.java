package com.example.nhatro2.phong;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.example.nhatro2.tien_coc.ChonKhachCocIdClick;
import com.example.nhatro2.tien_coc.KhachCocAdapter;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetChonKhachBanGiao extends BottomSheetDialogFragment {
    RecyclerView listKhachBanGiaoClick;
    EditText keySearchKhachBanGiao;
    ImageView searchCustomerBanGiao;
    LinearLayoutManager layoutBottomSheetChonKhachBanGiao;

    public BottomSheetChonKhachBanGiao(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_chon_khach_ban_giao, container, false);
        SharedPreferences shpKhachBanGiao;
        SharedPreferences.Editor shpKhachBanGiaoEdit;
        shpKhachBanGiao = view.getContext().getSharedPreferences("khachBanGiao", MODE_PRIVATE);
        shpKhachBanGiaoEdit = shpKhachBanGiao.edit();

        listKhachBanGiaoClick = view.findViewById(R.id.listKhachBanGiaoClick);
        listKhachBanGiaoClick.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listKhachBanGiaoClick.hasFixedSize();
        listKhachBanGiaoClick.setNestedScrollingEnabled(false);

        keySearchKhachBanGiao = view.findViewById(R.id.keySearchKhachBanGiao);
        searchCustomerBanGiao = view.findViewById(R.id.searchCustomerBanGiao);

        int chuPhong,idPhong, tang, trangthai, giaPhong, datcoc;
        String tenPhong, dayPhong, daiDien, dienThoai;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            chuPhong = bundle.getInt("daiDienPHong");
            idPhong = bundle.getInt("idPhong");
            tang = bundle.getInt("tang");
            trangthai = bundle.getInt("trangthai");
            giaPhong = bundle.getInt("gia");
            tenPhong = bundle.getString("tenPhong");
            dayPhong = bundle.getString("day");
            daiDien = bundle.getString("daidien");
            dienThoai = bundle.getString("dienthoai");
            datcoc = bundle.getInt("datcoc");

            // Tìm kiếm khách
            searchCustomerBanGiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String keyKhach = keySearchKhachBanGiao.getText().toString();
                    ApiQH.apiQH.searchKhachCocChon(keyKhach).enqueue(new Callback<List<ThanhVienModel>>() {
                        @Override
                        public void onResponse(Call<List<ThanhVienModel>> call, Response<List<ThanhVienModel>> response) {
                            List<ThanhVienModel> listKhachSearchChon = response.body();
                            listKhachBanGiaoClick.setAdapter(new KhachCocAdapter(view.getContext(),listKhachSearchChon, new ChonKhachCocIdClick() {
                                @Override
                                public void clickKhachCocChon(int thanhvien, String tenKhach, String phoneKhach) {
                                    Intent intent = new Intent(view.getContext(), PhongEdit.class);
                                    shpKhachBanGiaoEdit.putString("sdtKhach",phoneKhach);
                                    shpKhachBanGiaoEdit.putString("tenKhach",tenKhach);
                                    shpKhachBanGiaoEdit.putInt("idKhach",thanhvien);
                                    shpKhachBanGiaoEdit.commit();
                                    intent.putExtra("idPhong", idPhong);
                                    intent.putExtra("tang", tang);
                                    intent.putExtra("trangthai", trangthai);
                                    intent.putExtra("gia", giaPhong);
                                    intent.putExtra("tenPhong", tenPhong);
                                    intent.putExtra("day", dayPhong);
                                    intent.putExtra("daidien", daiDien);
                                    intent.putExtra("dienthoai", dienThoai);
                                    intent.putExtra("datcoc", datcoc);
                                    startActivity(intent);
                                }
                            }));
                        }

                        @Override
                        public void onFailure(Call<List<ThanhVienModel>> call, Throwable t) {
                        }
                    });
                }
            });

            ApiQH.apiQH.getKhachCocList().enqueue(new Callback<List<ThanhVienModel>>() {
                @Override
                public void onResponse(Call<List<ThanhVienModel>> call, Response<List<ThanhVienModel>> response) {
                    List<ThanhVienModel> listKhachSearchChon = response.body();
                    listKhachBanGiaoClick.setAdapter(new KhachCocAdapter(view.getContext(),listKhachSearchChon, new ChonKhachCocIdClick() {
                        @Override
                        public void clickKhachCocChon(int thanhvien, String tenKhach, String phoneKhach) {
                            Intent intent = new Intent(view.getContext(), PhongEdit.class);
                            shpKhachBanGiaoEdit.putString("sdtKhach",phoneKhach);
                            shpKhachBanGiaoEdit.putString("tenKhach",tenKhach);
                            shpKhachBanGiaoEdit.putInt("idKhach",thanhvien);
                            shpKhachBanGiaoEdit.commit();
                            intent.putExtra("idPhong", idPhong);
                            intent.putExtra("tang", tang);
                            intent.putExtra("trangthai", trangthai);
                            intent.putExtra("gia", giaPhong);
                            intent.putExtra("tenPhong", tenPhong);
                            intent.putExtra("day", dayPhong);
                            intent.putExtra("daidien", daiDien);
                            intent.putExtra("dienthoai", dienThoai);
                            intent.putExtra("datcoc", datcoc);
                            startActivity(intent);
                        }
                    }));
                }

                @Override
                public void onFailure(Call<List<ThanhVienModel>> call, Throwable t) {
                }
            });
        }
        return view;
    }
}
