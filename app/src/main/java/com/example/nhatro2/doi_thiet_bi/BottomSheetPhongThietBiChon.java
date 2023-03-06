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
import com.example.nhatro2.dong_tien.ChonPhongIdClick;
import com.example.nhatro2.dong_tien.ChonPhongTienAdapter;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.phong.PhongModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetPhongThietBiChon extends BottomSheetDialogFragment {
    RecyclerView listPhongThietBiChon;
    LinearLayoutManager layoutBottomSheetPhongThietBiChon;
    SharedPreferences shpPhongThietBiChon;
    SharedPreferences.Editor shpPhongThietBiChonEdit;
    public BottomSheetPhongThietBiChon(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.bottom_sheet_phong_thiet_bi_chon, container, false);
        ImageView searchPhongThietBiChon;
        EditText keySearchPhongThietBiChon;
        listPhongThietBiChon = view.findViewById(R.id.listPhongThietBiChon);
        listPhongThietBiChon.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        listPhongThietBiChon.hasFixedSize();
        listPhongThietBiChon.setNestedScrollingEnabled(false);

        ApiQH.apiQH.listPhongTien().enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                List<PhongModel> listPhongChon = response.body();
                listPhongThietBiChon.setAdapter(new ChonPhongThietBiAdapter(view.getContext(), listPhongChon, new ClickPhongThietBi() {
                    @Override
                    public void clickPhongID(int idPhong, String tenPhong, int chuPhongChon, int trangThaiPhong) {
                        shpPhongThietBiChon = view.getContext().getSharedPreferences("phongThietBiChon", MODE_PRIVATE);
                        shpPhongThietBiChonEdit = shpPhongThietBiChon.edit();
                        shpPhongThietBiChonEdit.putInt("trangThai",trangThaiPhong);
                        shpPhongThietBiChonEdit.putInt("idPhongChon",idPhong);
                        shpPhongThietBiChonEdit.putString("maPhongChon",tenPhong);
                        shpPhongThietBiChonEdit.putInt("chuPhongChon",chuPhongChon);
                        shpPhongThietBiChonEdit.commit();
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

        searchPhongThietBiChon = view.findViewById(R.id.searchPhongThietBiChon);
        keySearchPhongThietBiChon = view.findViewById(R.id.keySearchPhongThietBiChon);
        searchPhongThietBiChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenPhong = keySearchPhongThietBiChon.getText().toString();
                ApiQH.apiQH.searchPhongTien(tenPhong).enqueue(new Callback<List<PhongModel>>() {
                    @Override
                    public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                        List<PhongModel> listPhongSearch = response.body();
                        listPhongThietBiChon.setAdapter(new ChonPhongThietBiAdapter(view.getContext(), listPhongSearch, new ClickPhongThietBi() {
                            @Override
                            public void clickPhongID(int idPhong, String tenPhong, int chuPhongChon, int trangThaiPhong) {
                                shpPhongThietBiChon = view.getContext().getSharedPreferences("phongThietBiChon", MODE_PRIVATE);
                                shpPhongThietBiChonEdit = shpPhongThietBiChon.edit();
                                shpPhongThietBiChonEdit.putInt("trangThai",trangThaiPhong);
                                shpPhongThietBiChonEdit.putInt("idPhongChon",idPhong);
                                shpPhongThietBiChonEdit.putString("maPhongChon",tenPhong);
                                shpPhongThietBiChonEdit.putInt("chuPhongChon",chuPhongChon);
                                shpPhongThietBiChonEdit.commit();
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
