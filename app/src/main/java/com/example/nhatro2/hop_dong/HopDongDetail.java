package com.example.nhatro2.hop_dong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
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
import com.example.nhatro2.dich_vu.DichVu;
import com.example.nhatro2.dich_vu.DichVuEdit;
import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HopDongDetail extends AppCompatActivity {
    ImageView thoat, logo, themNguoiThue ,menuDanhMuc;
    SharedPreferences shp;
    LinearLayout quayLai;
    SharedPreferences.Editor shpKhachEdit;
    TextView maHopDongRoomDetail, ngayBatDauDetail, ngayKetThucDetail, titleChiTietHD;
    EditText tenDaiDienTextDetail;
    RecyclerView listKhachDetail, thietBiListDetail;
    LinearLayout khungThietBiHopDong, infoListNguoiThue, infoHieuLuc;
    DrawerLayout mDrawerLayout;

    @SuppressLint({"WrongViewCast", "MissingInflatedId", "ResourceAsColor"})
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
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HopDongDetail.super.onBackPressed();
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
        int trangthaiHD = bundle.getInt("trangthai");
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
        infoListNguoiThue = findViewById(R.id.infoListNguoiThue);
        infoHieuLuc = findViewById(R.id.infoHieuLuc);
        titleChiTietHD = findViewById(R.id.titleChiTietHD);
        if(trangthaiHD == 1){
            maHopDongRoomDetail.setTextColor(Color.parseColor("#1338BD"));
            titleChiTietHD.setBackgroundTintList(ContextCompat.getColorStateList(HopDongDetail.this, R.color.tabActive));
            infoListNguoiThue.setBackgroundColor(Color.parseColor("#E8EDFF"));
            infoHieuLuc.setBackgroundColor(Color.parseColor("#E8EDFF"));
            khungThietBiHopDong.setBackgroundColor(Color.parseColor("#E8EDFF"));
        }else{
//            titleChiTietHD.setBackgroundColor(Color.parseColor("#ff3232"));
            titleChiTietHD.setBackgroundTintList(ContextCompat.getColorStateList(HopDongDetail.this, R.color.tabThue));
            maHopDongRoomDetail.setTextColor(Color.parseColor("#B71616"));
            khungThietBiHopDong.setBackgroundColor(Color.parseColor("#FFEFEF"));
            infoListNguoiThue.setBackgroundColor(Color.parseColor("#FFEFEF"));
            infoHieuLuc.setBackgroundColor(Color.parseColor("#FFEFEF"));
        }

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

        // slide danh muc
        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_hop_dong_detail);

        menuDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.khach_tro:
                        Intent khachTro = new Intent(HopDongDetail.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(HopDongDetail.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(HopDongDetail.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(HopDongDetail.this, HopDong.class);
                        startActivity(hopDong);
                        return true;


                }
                return true;
            }
        });

    }
}