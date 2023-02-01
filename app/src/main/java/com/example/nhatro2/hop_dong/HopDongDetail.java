package com.example.nhatro2.hop_dong;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HopDongDetail extends AppCompatActivity {
    ImageView thoat, logo, themNguoiThue;
    SharedPreferences shp;
    SharedPreferences.Editor shpKhachEdit;
    TextView maHopDongRoomDetail, ngayBatDauDetail, ngayKetThucDetail;
    EditText tenDaiDienTextDetail;
    RecyclerView listKhachDetail, thietBiListDetail;
    LinearLayout khungThietBiHopDong;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hop_dong_detail);
        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HopDongDetail.this, HomeActivity.class);
                startActivity(intent);
                SharedPreferences shpKhach = view.getContext().getSharedPreferences("khachChonHopDongAdd", MODE_PRIVATE);
                shpKhachEdit = shpKhach.edit();
                shpKhachEdit.remove("idKhachChon");
                shpKhachEdit.apply();
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HopDongDetail.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(HopDongDetail.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HopDongDetail.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(HopDongDetail.this, "Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // Xét ví trí tương đối
        @SuppressLint("WrongViewCast")
        FrameLayout imageFrame = findViewById(R.id.imageHopDongDetail);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.hop_dong);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        // Thông tin gửi lên
        Bundle bundle = getIntent().getExtras();
        String thietbi = bundle.getString("thietbi");
        String khachThue = bundle.getString("khachthue");
        String tenPhong = bundle.getString("phong");
        int idHopDong = bundle.getInt("idHopDong");
        String ketthuc = bundle.getString("ketthuc");
        String batdau = bundle.getString("batdau");
        String chuphong = bundle.getString("chuphong");

        maHopDongRoomDetail = findViewById(R.id.maHopDongRoomDetail);
        maHopDongRoomDetail.setText("Hợp đồng số " + idHopDong + " - Phòng " + tenPhong);

        tenDaiDienTextDetail = findViewById(R.id.tenDaiDienTextDetail);
        tenDaiDienTextDetail.setText(chuphong);

        ngayBatDauDetail = findViewById(R.id.ngayBatDauDetail);
        ngayKetThucDetail = findViewById(R.id.ngayKetThucDetail);
        ngayBatDauDetail.setText(batdau);
        ngayKetThucDetail.setText(ketthuc);


        listKhachDetail = findViewById(R.id.listKhachDetail);
        listKhachDetail.setLayoutManager(new LinearLayoutManager(HopDongDetail.this));
        listKhachDetail.setNestedScrollingEnabled(false);
        listKhachDetail.hasFixedSize();

        thietBiListDetail = findViewById(R.id.thietBiListDetail);
        thietBiListDetail.setLayoutManager(new GridLayoutManager(HopDongDetail.this, 3));
        thietBiListDetail.setNestedScrollingEnabled(false);
        thietBiListDetail.hasFixedSize();

        ApiQH.apiQH.getListKhachDetail(khachThue).enqueue(new Callback<List<ThanhVienModel>>() {
            @Override
            public void onResponse(Call<List<ThanhVienModel>> call, Response<List<ThanhVienModel>> response) {
                List<ThanhVienModel> listKhachThueHopDongDetail = response.body();
                listKhachDetail.setAdapter(new KhachHopDongDetailAdapter(HopDongDetail.this, listKhachThueHopDongDetail));
            }

            @Override
            public void onFailure(Call<List<ThanhVienModel>> call, Throwable t) {

            }
        });
        khungThietBiHopDong = findViewById(R.id.khungThietBiHopDong);

        if(thietbi == null){
            khungThietBiHopDong.setVisibility(View.GONE);
        }

        ApiQH.apiQH.getListEquipmentDetail(thietbi).enqueue(new Callback<List<DichVuModel>>() {
            @Override
            public void onResponse(Call<List<DichVuModel>> call, Response<List<DichVuModel>> response) {
                List<DichVuModel> listDichVuThueHopDongDetail = response.body();
                thietBiListDetail.setAdapter(new ThietBiHopDongDetailAdapter(HopDongDetail.this, listDichVuThueHopDongDetail));
            }

            @Override
            public void onFailure(Call<List<DichVuModel>> call, Throwable t) {

            }
        });

    }
}