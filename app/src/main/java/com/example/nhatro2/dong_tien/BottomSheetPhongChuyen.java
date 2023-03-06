package com.example.nhatro2.dong_tien;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.MainActivity;
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

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int hopdong = bundle.getInt("hopDong", 0);
            ApiQH.apiQH.listPhongTrong().enqueue(new Callback<List<PhongModel>>() {
                @Override
                public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                    List<PhongModel> listPhongChon = response.body();
                    listPhongChuyen.setAdapter(new ChonPhongChuyenAdapter(view.getContext(), listPhongChon, new ClickPhongChuyen() {
                        @Override
                        public void chuyenPhong(int idPhong, String tenPhong, int trangThaiPhong) {
                            shpPhongChuyen = view.getContext().getSharedPreferences("phongChuyen", MODE_PRIVATE);
                            shpPhongChuyenEdit = shpPhongChuyen.edit();
                            shpPhongChuyenEdit.putInt("trangThai",trangThaiPhong);
                            shpPhongChuyenEdit.putString("idPhongChon",tenPhong);
                            shpPhongChuyenEdit.putInt("maPhongChon",idPhong);
                            shpPhongChuyenEdit.commit();


                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn chắc chắn muốn chuyển phòng ?</font>"));
                            builder.setCancelable(true);
                            builder.setIcon(R.drawable.alert_bottom);
                            //check
                            builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Có</font>"), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getContext(),"Chuyển phòng", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(view.getContext(), DongTien.class);
                                    startActivity(intent);
                                    ApiQH.apiQH.chuyenPhong(tenPhong,hopdong).enqueue(new Callback<ChuyenPhongModel>() {
                                        @Override
                                        public void onResponse(Call<ChuyenPhongModel> call, Response<ChuyenPhongModel> response) {
                                            Log.d("Chuyen phong","thanh cong");

                                        }

                                        @Override
                                        public void onFailure(Call<ChuyenPhongModel> call, Throwable t) {
                                            Log.d("Chuyen phong","that bai"+t.toString());

                                        }
                                    });
                                }
                            });
                            // NO
                            builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>Không</font>"), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(getContext(),"Giữ nguyên phòng", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            });
                            // show alert
                            AlertDialog alert = builder.create();
                            alert.show();
                        }}));
                }

                @Override
                public void onFailure(Call<List<PhongModel>> call, Throwable t) {

                }
            });

            searchPhongChon = view.findViewById(R.id.searchPhongChuyen);
            keySearchPhong = view.findViewById(R.id.keyPhongChuyen);
            searchPhongChon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tenPhong = keySearchPhong.getText().toString();
                    ApiQH.apiQH.searchPhongTien(tenPhong).enqueue(new Callback<List<PhongModel>>() {
                        @Override
                        public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                            List<PhongModel> listPhongSearch = response.body();
                            listPhongChuyen.setAdapter(new ChonPhongChuyenAdapter(view.getContext(), listPhongSearch, new ClickPhongChuyen() {
                                @Override
                                public void chuyenPhong(int idPhong, String tenPhong, int trangThaiPhong) {
                                    shpPhongChuyen = view.getContext().getSharedPreferences("phongChuyen", MODE_PRIVATE);
                                    shpPhongChuyenEdit = shpPhongChuyen.edit();
                                    shpPhongChuyenEdit.putInt("trangThai",trangThaiPhong);
                                    shpPhongChuyenEdit.putString("idPhongChon",tenPhong);
                                    shpPhongChuyenEdit.putInt("maPhongChon",idPhong);
                                    shpPhongChuyenEdit.commit();


                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                                    builder.setCancelable(true);
                                    builder.setIcon(R.drawable.alert_bottom);
                                    //check
                                    builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Có</font>"), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Toast.makeText(getContext(),"Chuyển phòng", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(view.getContext(), DongTien.class);
                                            startActivity(intent);
                                            ApiQH.apiQH.chuyenPhong(tenPhong,hopdong).enqueue(new Callback<ChuyenPhongModel>() {
                                                @Override
                                                public void onResponse(Call<ChuyenPhongModel> call, Response<ChuyenPhongModel> response) {
                                                    Log.d("Chuyen phong","thanh cong");

                                                }

                                                @Override
                                                public void onFailure(Call<ChuyenPhongModel> call, Throwable t) {
                                                    Log.d("Chuyen phong","that bai"+t.toString());

                                                }
                                            });
                                        }
                                    });
                                    // NO
                                    builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>Không</font>"), new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Toast.makeText(getContext(),"Giữ nguyên phòng", Toast.LENGTH_SHORT).show();
                                            dialog.cancel();
                                        }
                                    });
                                    // show alert
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }}));

                        }

                        @Override
                        public void onFailure(Call<List<PhongModel>> call, Throwable t) {
                            Log.d("err ca iquan que",""+t.toString());
                        }
                    });
                }
            });
        }
        return view;
    }
}
