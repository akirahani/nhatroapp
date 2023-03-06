package com.example.nhatro2.thay_cong_to;

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

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dong_tien.ChonPhongIdClick;
import com.example.nhatro2.dong_tien.ChonPhongTienAdapter;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.phong.PhongModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetPhongCongToDien extends BottomSheetDialogFragment {
    RecyclerView listPhongCongTo;
    LinearLayoutManager layoutBottomSheetPhongCongTo;
    SharedPreferences shpPhongCongTo;
    SharedPreferences.Editor shpPhongCongToEdit;
    public BottomSheetPhongCongToDien() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_phong_cong_to, container, false);
        ImageView searchPhongCongToDien;
        EditText keySearchCongToDien;
        listPhongCongTo = view.findViewById(R.id.listPhongCongTo);
        listPhongCongTo.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        listPhongCongTo.hasFixedSize();
        listPhongCongTo.setNestedScrollingEnabled(false);

        ApiQH.apiQH.listPhongTien().enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                List<PhongModel> listPhongChon = response.body();
                listPhongCongTo.setAdapter(new PhongCongToDienAdapter(view.getContext(), listPhongChon, new ClickCongToDien() {
                    @Override
                    public void clickPhongID(int idPhong, String tenPhong, int chuPhongChon, int trangThaiPhong) {
                        shpPhongCongTo = view.getContext().getSharedPreferences("phongCongToDien", MODE_PRIVATE);
                        shpPhongCongToEdit = shpPhongCongTo.edit();
                        shpPhongCongToEdit.putInt("trangThai",trangThaiPhong);
                        shpPhongCongToEdit.putString("idPhongChon",tenPhong);
                        shpPhongCongToEdit.putInt("maPhongChon",idPhong);
                        shpPhongCongToEdit.putInt("chuPhongChon",chuPhongChon);
                        shpPhongCongToEdit.putString("tenPhong",tenPhong);
                        shpPhongCongToEdit.commit();
                        Intent intent = new Intent(view.getContext(), CongToDien.class);
                        startActivity(intent);
                        getDialog().dismiss();
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {

            }
        });

        searchPhongCongToDien = view.findViewById(R.id.searchPhongCongToDien);
        keySearchCongToDien = view.findViewById(R.id.keyPhongCongToDien);
        searchPhongCongToDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenPhong = keySearchCongToDien.getText().toString();
                ApiQH.apiQH.searchPhongTien(tenPhong).enqueue(new Callback<List<PhongModel>>() {
                    @Override
                    public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                        List<PhongModel> listPhongSearch = response.body();
                        listPhongCongTo.setAdapter(new ChonPhongTienAdapter(view.getContext(), listPhongSearch, new ChonPhongIdClick() {
                            @Override
                            public void clickPhongID(int idPhong, String tenPhong, int chuPhongChon, int trangThaiPhong) {
                                shpPhongCongTo = view.getContext().getSharedPreferences("phongCongToDien", MODE_PRIVATE);
                                shpPhongCongToEdit = shpPhongCongTo.edit();
                                shpPhongCongToEdit.putInt("trangThai",trangThaiPhong);
                                shpPhongCongToEdit.putString("idPhongChon",tenPhong);
                                shpPhongCongToEdit.putInt("maPhongChon",idPhong);
                                shpPhongCongToEdit.putInt("chuPhongChon",chuPhongChon);
                                shpPhongCongToEdit.putString("tenPhong",tenPhong);
                                shpPhongCongToEdit.commit();
                                Intent intent = new Intent(view.getContext(), CongToDien.class);
                                startActivity(intent);
                                getDialog().dismiss();
                            }
                        }));
                    }

                    @Override
                    public void onFailure(Call<List<PhongModel>> call, Throwable t) {
                        Log.d("err ca iquan que",""+t.toString());
                    }
                });
            }
        });
        return view;
    }
}
