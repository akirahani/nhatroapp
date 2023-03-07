package com.example.nhatro2.doi_thiet_bi;

import static android.content.Context.MODE_PRIVATE;

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

public class BottomSheetPhongChuyen extends BottomSheetDialogFragment {
    RecyclerView listPhongChuyenDi;
    LinearLayoutManager layoutBottomSheetPhongChuyenDi;
    SharedPreferences shpPhongChuyenDi;
    SharedPreferences.Editor shpPhongChuyenDiEdit;
    public BottomSheetPhongChuyen(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.bottom_sheet_phong_chuyen_di, container, false);
        ImageView searchPhongChuyenDi;
        EditText keySearchPhongChuyenDi;
        listPhongChuyenDi = view.findViewById(R.id.listPhongChuyenDi);
        listPhongChuyenDi.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        listPhongChuyenDi.hasFixedSize();
        listPhongChuyenDi.setNestedScrollingEnabled(false);

        ApiQH.apiQH.listPhongTien().enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                List<PhongModel> listPhongChon = response.body();
                listPhongChuyenDi.setAdapter(new ChonPhongChuyenDiAdapter(view.getContext(), listPhongChon, new ClickPhongChuyenDi() {
                    @Override
                    public void clickPhongID(int idPhong, String tenPhong, int chuPhongChon, int trangThaiPhong) {
                        shpPhongChuyenDi = view.getContext().getSharedPreferences("phongChuyenDi", MODE_PRIVATE);
                        shpPhongChuyenDiEdit = shpPhongChuyenDi.edit();
                        shpPhongChuyenDiEdit.putInt("trangThai",trangThaiPhong);
                        shpPhongChuyenDiEdit.putInt("idPhongChon",idPhong);
                        shpPhongChuyenDiEdit.putString("maPhongChon",tenPhong);
                        shpPhongChuyenDiEdit.putInt("chuPhongChon",chuPhongChon);
                        shpPhongChuyenDiEdit.commit();
                        Intent intent = new Intent(view.getContext(), DoiThietBi.class);
                        startActivity(intent);
                        getDialog().dismiss();
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {

            }
        });


        searchPhongChuyenDi = view.findViewById(R.id.searchPhongChuyenDi);
        keySearchPhongChuyenDi = view.findViewById(R.id.keySearchPhongChuyenDi);
        searchPhongChuyenDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenPhong = keySearchPhongChuyenDi.getText().toString();
                ApiQH.apiQH.searchPhongTien(tenPhong).enqueue(new Callback<List<PhongModel>>() {
                    @Override
                    public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                        List<PhongModel> listPhongSearch = response.body();
                        listPhongChuyenDi.setAdapter(new ChonPhongChuyenDiAdapter(view.getContext(), listPhongSearch, new ClickPhongChuyenDi() {
                            @Override
                            public void clickPhongID(int idPhong, String tenPhong, int chuPhongChon, int trangThaiPhong) {
                                shpPhongChuyenDi = view.getContext().getSharedPreferences("phongChuyenDi", MODE_PRIVATE);
                                shpPhongChuyenDiEdit = shpPhongChuyenDi.edit();
                                shpPhongChuyenDiEdit.putInt("trangThai",trangThaiPhong);
                                shpPhongChuyenDiEdit.putInt("idPhongChon",idPhong);
                                shpPhongChuyenDiEdit.putString("maPhongChon",tenPhong);
                                shpPhongChuyenDiEdit.putInt("chuPhongChon",chuPhongChon);
                                shpPhongChuyenDiEdit.commit();
                                Intent intent = new Intent(view.getContext(), DoiThietBi.class);
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
