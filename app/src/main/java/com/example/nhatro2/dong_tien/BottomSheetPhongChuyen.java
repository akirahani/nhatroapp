package com.example.nhatro2.dong_tien;

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

public class BottomSheetPhongChuyen extends BottomSheetDialogFragment {
    RecyclerView listPhongChuyen;
    LinearLayoutManager layoutBottomSheetPhongChuyen;
    SharedPreferences shpPhongChuyen;
    SharedPreferences.Editor shpPhongChuyenEdit;
    public BottomSheetPhongChuyen() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_phong_chuyen, container, false);
        ImageView searchPhongChon;
        EditText keySearchPhong;
        listPhongChuyen = view.findViewById(R.id.listPhongChuyen);
        listPhongChuyen.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        listPhongChuyen.hasFixedSize();
        listPhongChuyen.setNestedScrollingEnabled(false);

        ApiQH.apiQH.listPhongTrong().enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                List<PhongModel> listPhongChon = response.body();
                listPhongChuyen.setAdapter(new ChonPhongChuyenAdapter(view.getContext(), listPhongChon, new ClickPhongChuyen() {
                    @Override
                    public void chuyenPhong(int idPhong,String tenPhong, int chuPhongChon, int trangThaiPhong) {
                        shpPhongChuyen = view.getContext().getSharedPreferences("phongChuyen", MODE_PRIVATE);
                        shpPhongChuyenEdit = shpPhongChuyen.edit();
                        shpPhongChuyenEdit.putInt("trangThai",trangThaiPhong);
                        shpPhongChuyenEdit.putString("idPhongChon",tenPhong);
                        shpPhongChuyenEdit.putInt("maPhongChon",idPhong);
                        shpPhongChuyenEdit.putInt("chuPhongChon",chuPhongChon);
                        shpPhongChuyenEdit.commit();
//                        Intent intent = new Intent(view.getContext(), DongTien.class);
//                        startActivity(intent);
//                        getDialog().dismiss();
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {

            }
        });

        searchPhongChon = view.findViewById(R.id.searchPhongChon);
        keySearchPhong = view.findViewById(R.id.keySearchPhong);
        searchPhongChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenPhong = keySearchPhong.getText().toString();
                ApiQH.apiQH.searchPhongTien(tenPhong).enqueue(new Callback<List<PhongModel>>() {
                    @Override
                    public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                        List<PhongModel> listPhongSearch = response.body();
                        listPhongClick.setAdapter(new ChonPhongTienAdapter(view.getContext(), listPhongSearch, new ChonPhongIdClick() {
                            @Override
                            public void clickPhongID(int idPhong, String tenPhong, int chuPhongChon, int trangThaiPhong) {
                                shpPhongChon = view.getContext().getSharedPreferences("phongChon", MODE_PRIVATE);
                                shpPhongChonEdit = shpPhongChon.edit();
                                shpPhongChonEdit.putInt("trangThai",trangThaiPhong);
                                shpPhongChonEdit.putString("idPhongChon",tenPhong);
                                shpPhongChonEdit.putInt("maPhongChon",idPhong);
                                shpPhongChonEdit.putInt("chuPhongChon",chuPhongChon);
                                shpPhongChonEdit.commit();
                                Intent intent = new Intent(view.getContext(), DongTien.class);
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
