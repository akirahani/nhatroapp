package com.example.nhatro2.hop_dong;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.LongDef;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetThanhVienChon extends BottomSheetDialogFragment {
    RecyclerView listKhachClick;
    SharedPreferences shpKhach;
    SharedPreferences.Editor shpKhachEdit;
    String listKhachChooseString;
    EditText keySearchKach;
    ImageView searchCustomerChon;
    LinearLayoutManager layoutBottomSheetKhachChon;
    public BottomSheetThanhVienChon() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_khach_chon, container, false);
        listKhachClick = view.findViewById(R.id.listKhachClick);
        listKhachClick.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listKhachClick.hasFixedSize();
        listKhachClick.setNestedScrollingEnabled(false);

        keySearchKach = view.findViewById(R.id.keySearchKach);
        searchCustomerChon = view.findViewById(R.id.searchCustomerChon);

        int chuPhong = 0;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            chuPhong = bundle.getInt("daiDienPHong", 0);
        }

        int finalChuPhong = chuPhong;

        // Tìm kiếm khách
        searchCustomerChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyKhach = keySearchKach.getText().toString();
                ApiQH.apiQH.searchKhachChon(keyKhach, finalChuPhong).enqueue(new Callback<List<ThanhVienModel>>() {
                    @Override
                    public void onResponse(Call<List<ThanhVienModel>> call, Response<List<ThanhVienModel>> response) {
                        List<ThanhVienModel> listKhachSearchChon = response.body();
                        listKhachClick.setAdapter(new ListKhachChonAdapter(view.getContext(),listKhachSearchChon, new ClickKhachAddHopDong() {
                            @Override
                            public void clickKhachChon(int thanhvien) {

                                List<Integer> arrKhachChon = new ArrayList<>();

                                if (listKhachChooseString.equals("")) {
                                    arrKhachChon.add(thanhvien);
                                    String convertStringKhach = null;
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        convertStringKhach = arrKhachChon.stream().map(String::valueOf).collect(Collectors.joining(","));
                                    }
                                    shpKhachEdit.putString("idKhachChon", convertStringKhach);
                                    shpKhachEdit.apply();

                                } else {

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        arrKhachChon = Arrays.stream(listKhachChooseString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                                    }

                                    if (arrKhachChon.contains(thanhvien)) {
                                        arrKhachChon.remove(Integer.valueOf(thanhvien));
                                        String convertStringKhach = null;
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                            convertStringKhach = arrKhachChon.stream().map(String::valueOf).collect(Collectors.joining(","));
                                        }
                                        shpKhachEdit.putString("idKhachChon", convertStringKhach);
                                        shpKhachEdit.apply();

                                    } else {
                                        arrKhachChon.add(thanhvien);
                                        String convertStringKhach = null;
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                            convertStringKhach = arrKhachChon.stream().map(String::valueOf).collect(Collectors.joining(","));
                                        }
                                        shpKhachEdit.putString("idKhachChon", convertStringKhach);
                                        shpKhachEdit.apply();
                                    }
                                }
                                listKhachChooseString = shpKhach.getString("idKhachChon", "");

                            }
                        }));
                    }

                    @Override
                    public void onFailure(Call<List<ThanhVienModel>> call, Throwable t) {
                    }
                });
            }
        });

        shpKhach = view.getContext().getSharedPreferences("khachChonHopDongAdd", MODE_PRIVATE);
        shpKhachEdit = shpKhach.edit();
        listKhachChooseString = shpKhach.getString("idKhachChon", "");


        ApiQH.apiQH.getKhachListHopDongChon(chuPhong).enqueue(new Callback<List<ThanhVienModel>>() {
            @Override
            public void onResponse(Call<List<ThanhVienModel>> call, Response<List<ThanhVienModel>> response) {
                List<ThanhVienModel> infoListKhachChon = response.body();
                listKhachClick.setAdapter(new ListKhachChonAdapter(view.getContext(), infoListKhachChon, new ClickKhachAddHopDong() {
                    @Override
                    public void clickKhachChon(int thanhvien) {
                        List<Integer> arrKhachChon = new ArrayList<>();
                        if (listKhachChooseString.equals("")) {
                            arrKhachChon.add(thanhvien);
                            String convertStringKhach = null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                convertStringKhach = arrKhachChon.stream().map(String::valueOf).collect(Collectors.joining(","));
                            }
                            shpKhachEdit.putString("idKhachChon", convertStringKhach);
                            shpKhachEdit.commit();

                        } else {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                arrKhachChon = Arrays.stream(listKhachChooseString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                            }

                            if (arrKhachChon.contains(thanhvien)) {
                                arrKhachChon.remove(Integer.valueOf(thanhvien));
                                String convertStringKhach = null;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    convertStringKhach = arrKhachChon.stream().map(String::valueOf).collect(Collectors.joining(","));
                                }
                                shpKhachEdit.putString("idKhachChon", convertStringKhach);
                                shpKhachEdit.commit();

                            } else {
                                arrKhachChon.add(thanhvien);
                                String convertStringKhach = null;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    convertStringKhach = arrKhachChon.stream().map(String::valueOf).collect(Collectors.joining(","));
                                }
                                shpKhachEdit.putString("idKhachChon", convertStringKhach);
                                shpKhachEdit.commit();
                            }
                        }
                        listKhachChooseString = shpKhach.getString("idKhachChon", "");
//                        Intent intent = new Intent(getContext(),HopDongAdd.class);
//                        startActivity(intent);
                        // Nếu đủ 4 người thì sẽ clear add Customer button
                        List<String> thanhVienPhong = new ArrayList<>();
                        String[] items = listKhachChooseString.split(",");


//                        for(int i=0; i < items.length; i++){
//                            if(items.length <= 3){
//                                thanhVienPhong.add(items[i]);
//                            }else{
//                                thanhVienPhong.remove(items[i]);
//                            }
//                        }
//
//                        Log.d("arr",""+thanhVienPhong);
//
//                        if(thanhVienPhong.size() > 4){
//                            view.setVisibility(View.GONE);
//                        }


                    }
                }));
            }

            @Override
            public void onFailure(Call<List<ThanhVienModel>> call, Throwable t) {
                Log.d("err",""+t.toString());
            }
        });
        return view;
    }
}
