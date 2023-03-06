package com.example.nhatro2.quy_tien_mat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.doi_thiet_bi.DoiThietBi;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.phong.PhongEdit;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thay_cong_to.CongToDien;
import com.example.nhatro2.thay_cong_to.CongToNuoc;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuyTienMat extends AppCompatActivity {
    ImageView logo, menuDanhMuc;
    SharedPreferences shp;
    DrawerLayout mDrawerLayout;
    TextView tienQuyConLai, tienTongChi, tienTongThu;
    RecyclerView listThangTienThu, listThangTienChi;
    LinearLayout quayLai;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quy_tien_mat);
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuyTienMat.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuyTienMat.super.onBackPressed();
            }
        });

        // Xét ví trí tương đối
        @SuppressLint("WrongViewCast")
        FrameLayout imageFrame = findViewById(R.id.imageQuyTien);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.quytien);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout);

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
                        Intent khachTro = new Intent(QuyTienMat.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(QuyTienMat.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(QuyTienMat.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(QuyTienMat.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                    case R.id.thay_cong_to_nuoc:
                        Intent thay_cong_to_nuoc = new Intent(QuyTienMat.this, CongToNuoc.class);
                        startActivity(thay_cong_to_nuoc);
                        return true;
                    case R.id.thay_cong_to_dien:
                        Intent thay_cong_to_dien = new Intent(QuyTienMat.this, CongToDien.class);
                        startActivity(thay_cong_to_dien);
                        return true;
                    case R.id.doi_thiet_bi:
                        Intent doi_thiet_bi = new Intent(QuyTienMat.this, DoiThietBi.class);
                        startActivity(doi_thiet_bi);
                        return true;

                }
                return true;
            }
        });

        // Content
        ApiQH.apiQH.getQuyTien().enqueue(new Callback<QuyTienModel>() {
            @Override
            public void onResponse(Call<QuyTienModel> call, Response<QuyTienModel> response) {
                QuyTienModel quyTienGet = response.body();
                String tienChiTong = quyTienGet.getTienchi();
                String tienThuTong = quyTienGet.getTienthu();
                String quyTienTong = quyTienGet.getQuy();

                tienQuyConLai = findViewById(R.id.tienQuyConLai);
                tienTongChi = findViewById(R.id.tienTongChi);
                tienTongThu = findViewById(R.id.tienTongThu);

                tienQuyConLai.setText(quyTienTong+"đ");
                tienTongChi.setText(tienChiTong+"đ");
                tienTongThu.setText(tienThuTong+"đ");

            }

            @Override
            public void onFailure(Call<QuyTienModel> call, Throwable t) {
            }
        });

        listThangTienThu = findViewById(R.id.listThangTienThu);
        listThangTienThu.setNestedScrollingEnabled(false);
        listThangTienThu.hasFixedSize();
        listThangTienThu.setLayoutManager(new LinearLayoutManager(QuyTienMat.this));

        listThangTienChi = findViewById(R.id.listThangTienChi);
        listThangTienChi.setNestedScrollingEnabled(false);
        listThangTienChi.hasFixedSize();
        listThangTienChi.setLayoutManager(new LinearLayoutManager(QuyTienMat.this));


        ApiQH.apiQH.getQuyThu().enqueue(new Callback<List<QuyThuModel>>() {
            @Override
            public void onResponse(Call<List<QuyThuModel>> call, Response<List<QuyThuModel>> response) {
                List<QuyThuModel> listQuyThuFinal = response.body();
                listThangTienThu.setAdapter(new QuyThuAdapter(QuyTienMat.this,listQuyThuFinal));

            }

            @Override
            public void onFailure(Call<List<QuyThuModel>> call, Throwable t) {

            }
        });

        ApiQH.apiQH.getQuyChi().enqueue(new Callback<List<QuyChiModel>>() {
            @Override
            public void onResponse(Call<List<QuyChiModel>> call, Response<List<QuyChiModel>> response) {
                List<QuyChiModel> listQuyChiFinal = response.body();
                listThangTienChi.setAdapter(new QuyChiAdapter(QuyTienMat.this,listQuyChiFinal));
            }

            @Override
            public void onFailure(Call<List<QuyChiModel>> call, Throwable t) {

            }
        });
    }
}