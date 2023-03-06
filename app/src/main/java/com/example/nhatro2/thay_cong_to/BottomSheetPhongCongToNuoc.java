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
import com.example.nhatro2.phong.PhongModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetPhongCongToNuoc extends BottomSheetDialogFragment {
    RecyclerView listPhongCongToNuoc;
    LinearLayoutManager layoutBottomSheetPhongCongTo;
    SharedPreferences shpPhongCongToNuoc;
    SharedPreferences.Editor shpPhongCongToNuocEdit;
    public BottomSheetPhongCongToNuoc() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_phong_cong_to_nuoc, container, false);
        ImageView searchPhongCongToNuoc;
        EditText keySearchCongToNuoc;
        listPhongCongToNuoc = view.findViewById(R.id.listPhongCongToNuoc);
        listPhongCongToNuoc.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        listPhongCongToNuoc.hasFixedSize();
        listPhongCongToNuoc.setNestedScrollingEnabled(false);

        ApiQH.apiQH.listPhongTien().enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                List<PhongModel> listPhongChon = response.body();
                listPhongCongToNuoc.setAdapter(new PhongCongToNuocAdapter(view.getContext(), listPhongChon, new ClickCongToNuoc() {
                    @Override
                    public void clickPhongID(int idPhong, String tenPhong, int chuPhongChon, int trangThaiPhong) {
                        shpPhongCongToNuoc = view.getContext().getSharedPreferences("phongCongToNuoc", MODE_PRIVATE);
                        shpPhongCongToNuocEdit = shpPhongCongToNuoc.edit();
                        shpPhongCongToNuocEdit.putInt("trangThai",trangThaiPhong);
                        shpPhongCongToNuocEdit.putString("idPhongChon",tenPhong);
                        shpPhongCongToNuocEdit.putInt("maPhongChon",idPhong);
                        shpPhongCongToNuocEdit.putInt("chuPhongChon",chuPhongChon);
                        shpPhongCongToNuocEdit.putString("tenPhong",tenPhong);
                        shpPhongCongToNuocEdit.commit();
                        Intent intent = new Intent(view.getContext(), CongToNuoc.class);
                        startActivity(intent);
                        getDialog().dismiss();
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {

            }
        });

        searchPhongCongToNuoc = view.findViewById(R.id.searchPhongCongToNuoc);
        keySearchCongToNuoc = view.findViewById(R.id.keyPhongCongToNuoc);
        searchPhongCongToNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenPhong = keySearchCongToNuoc.getText().toString();
                ApiQH.apiQH.searchPhongTien(tenPhong).enqueue(new Callback<List<PhongModel>>() {
                    @Override
                    public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                        List<PhongModel> listPhongSearch = response.body();
                        listPhongCongToNuoc.setAdapter(new PhongCongToNuocAdapter(view.getContext(), listPhongSearch, new ClickCongToNuoc() {
                            @Override
                            public void clickPhongID(int idPhong, String tenPhong, int chuPhongChon, int trangThaiPhong) {
                                shpPhongCongToNuoc = view.getContext().getSharedPreferences("phongCongToNuoc", MODE_PRIVATE);
                                shpPhongCongToNuocEdit = shpPhongCongToNuoc.edit();
                                shpPhongCongToNuocEdit.putInt("trangThai",trangThaiPhong);
                                shpPhongCongToNuocEdit.putString("idPhongChon",tenPhong);
                                shpPhongCongToNuocEdit.putInt("maPhongChon",idPhong);
                                shpPhongCongToNuocEdit.putInt("chuPhongChon",chuPhongChon);
                                shpPhongCongToNuocEdit.putString("tenPhong",tenPhong);
                                shpPhongCongToNuocEdit.commit();
                                Intent intent = new Intent(view.getContext(), CongToNuoc.class);
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
