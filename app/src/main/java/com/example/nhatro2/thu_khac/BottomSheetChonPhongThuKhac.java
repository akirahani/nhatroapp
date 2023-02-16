package com.example.nhatro2.thu_khac;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

public class BottomSheetChonPhongThuKhac extends BottomSheetDialogFragment {
    RecyclerView listPhongClick;
    SharedPreferences shpPhongThuKhacChon;
    SharedPreferences.Editor shpPhongThuKhacChonEdit;
    public BottomSheetChonPhongThuKhac() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_phong_thu_khac_chon, container, false);
        EditText keySearchPhongThuKhac;
        ImageView searchPhongThuKhacChon;
        listPhongClick = view.findViewById(R.id.listKhachThuKhacClick);
        listPhongClick.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        listPhongClick.hasFixedSize();
        listPhongClick.setNestedScrollingEnabled(false);

        ApiQH.apiQH.listPhongTien().enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                List<PhongModel> listPhongChon = response.body();
                listPhongClick.setAdapter(new ChonPhongThuKhacAdapter(view.getContext(), listPhongChon, new ChonPhongThuKhacClick() {
                    @Override
                    public void clickPhongThuKhac(int idPhong, String tenPhong) {
                        shpPhongThuKhacChon = view.getContext().getSharedPreferences("phongThuKhacChon", MODE_PRIVATE);
                        shpPhongThuKhacChonEdit = shpPhongThuKhacChon.edit();
                        shpPhongThuKhacChonEdit.putString("idPhongThuKhacChon",tenPhong);
                        shpPhongThuKhacChonEdit.putInt("maPhongThuKhacChon",idPhong);
                        shpPhongThuKhacChonEdit.commit();
                        Intent intent = new Intent(view.getContext(),ThuKhacAdd.class);
                        startActivity(intent);
                        getDialog().dismiss();
                    }
                }));

            }

            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {

            }
        });

        searchPhongThuKhacChon = view.findViewById(R.id.searchPhongThuKhacChon);
        keySearchPhongThuKhac = view.findViewById(R.id.keySearchPhongThuKhac);
        searchPhongThuKhacChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenPhong = keySearchPhongThuKhac.getText().toString();
                ApiQH.apiQH.searchPhongTien(tenPhong).enqueue(new Callback<List<PhongModel>>() {
                    @Override
                    public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                        List<PhongModel> listPhongChon = response.body();
                        listPhongClick.setAdapter(new ChonPhongThuKhacAdapter(view.getContext(), listPhongChon, new ChonPhongThuKhacClick() {
                            @Override
                            public void clickPhongThuKhac(int idPhong, String tenPhong) {
                                shpPhongThuKhacChon = view.getContext().getSharedPreferences("phongThuKhacChon", MODE_PRIVATE);
                                shpPhongThuKhacChonEdit = shpPhongThuKhacChon.edit();
                                shpPhongThuKhacChonEdit.putString("idPhongThuKhacChon",tenPhong);
                                shpPhongThuKhacChonEdit.putInt("maPhongThuKhacChon",idPhong);
                                shpPhongThuKhacChonEdit.commit();
                                Intent intent = new Intent(view.getContext(),ThuKhacAdd.class);
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
