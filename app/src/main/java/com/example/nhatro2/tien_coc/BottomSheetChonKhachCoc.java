package com.example.nhatro2.tien_coc;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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
import com.example.nhatro2.hop_dong.ClickKhachAddHopDong;
import com.example.nhatro2.hop_dong.ListKhachChonAdapter;
import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetChonKhachCoc extends BottomSheetDialogFragment {
    RecyclerView listKhachCocClick;
    EditText keySearchKhachCoc;
    ImageView searchCustomerCoc;
    LinearLayoutManager layoutBottomSheetKhachChon;

    public BottomSheetChonKhachCoc(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_chon_khach_coc, container, false);
        SharedPreferences shpKhachCoc;
        SharedPreferences.Editor shpKhachCocEdit;
        shpKhachCoc = view.getContext().getSharedPreferences("khachCocChon", MODE_PRIVATE);
        shpKhachCocEdit = shpKhachCoc.edit();

        listKhachCocClick = view.findViewById(R.id.listKhachCocClick);
        listKhachCocClick.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listKhachCocClick.hasFixedSize();
        listKhachCocClick.setNestedScrollingEnabled(false);

        keySearchKhachCoc = view.findViewById(R.id.keySearchKhachCoc);
        searchCustomerCoc = view.findViewById(R.id.searchCustomerCoc);

        int chuPhong = 0;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            chuPhong = bundle.getInt("daiDienPHong", 0);
        }


        // Tìm kiếm khách
        searchCustomerCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyKhach = keySearchKhachCoc.getText().toString();
                ApiQH.apiQH.searchKhachCocChon(keyKhach).enqueue(new Callback<List<ThanhVienModel>>() {
                    @Override
                    public void onResponse(Call<List<ThanhVienModel>> call, Response<List<ThanhVienModel>> response) {
                        List<ThanhVienModel> listKhachSearchChon = response.body();
                        listKhachCocClick.setAdapter(new KhachCocAdapter(view.getContext(),listKhachSearchChon, new ChonKhachCocIdClick() {
                            @Override
                            public void clickKhachCocChon(int thanhvien, String tenKhach, String phoneKhach) {
                                Intent intent = new Intent(view.getContext(), TienCoc.class);

                                shpKhachCocEdit.putString("sdtKhach",phoneKhach);
                                shpKhachCocEdit.putString("tenKhach",tenKhach);
                                shpKhachCocEdit.putInt("idKhach",thanhvien);
                                shpKhachCocEdit.commit();

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
                listKhachCocClick.setAdapter(new KhachCocAdapter(view.getContext(),listKhachSearchChon, new ChonKhachCocIdClick() {
                    @Override
                    public void clickKhachCocChon(int thanhvien, String tenKhach, String phoneKhach) {
                        Intent intent = new Intent(view.getContext(), TienCoc.class);

                        shpKhachCocEdit.putString("sdtKhach",phoneKhach);
                        shpKhachCocEdit.putString("tenKhach",tenKhach);
                        shpKhachCocEdit.putInt("idKhach",thanhvien);
                        shpKhachCocEdit.commit();

                        startActivity(intent);
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<ThanhVienModel>> call, Throwable t) {
            }
        });

        return view;
    }
}
