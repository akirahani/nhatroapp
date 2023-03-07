package com.example.nhatro2.thong_ke;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;

import com.example.nhatro2.api.ApiQH;

import com.example.nhatro2.doi_thiet_bi.DoiThietBi;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.quy_tien_mat.QuyTienModel;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thay_cong_to.CongToDien;
import com.example.nhatro2.thay_cong_to.CongToNuoc;
import com.example.nhatro2.thu_khac.ThuKhacAdd;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;
import com.highsoft.highcharts.common.hichartsclasses.HIChart;
import com.highsoft.highcharts.common.hichartsclasses.HIColumn;
import com.highsoft.highcharts.common.hichartsclasses.HIOptions;
import com.highsoft.highcharts.common.hichartsclasses.HITitle;
import com.highsoft.highcharts.common.hichartsclasses.HIXAxis;
import com.highsoft.highcharts.common.hichartsclasses.HIYAxis;
import com.highsoft.highcharts.core.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongKe extends AppCompatActivity {
    ImageView logo, menuDanhMuc;
    SharedPreferences shp;
    LinearLayout quayLai;
    DrawerLayout mDrawerLayout;
    WebView tongQuan, doanhThuThangHienTai, thietBiCacThang, doanhThuThangTruoc, tienThietBiCacThang
            ,gioiTinh,tinhThanh,doTuoiKhach;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_thong_ke);

            //Ánh xạ
            logo = findViewById(R.id.logo);
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ThongKe.this, HomeActivity.class);
                    startActivity(intent);
                }
            });

            // Nút thoát
            quayLai = findViewById(R.id.quayLai);
            quayLai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ThongKe.super.onBackPressed();
                }
            });

//            String url = "http://192.168.1.190/quanghieu";
            String url = "http://172.16.1.71/quanghieu";
        // tong quan
            tongQuan = findViewById(R.id.tongQuan);
            tongQuan.loadUrl(url+"/admin/api/thong-ke/tong-quan.php");
            WebSettings webSettings = tongQuan.getSettings();
            webSettings.setJavaScriptEnabled(true);

            doanhThuThangHienTai = findViewById(R.id.doanhThuThangHienTai);
            doanhThuThangHienTai.loadUrl(url+"/admin/api/thong-ke/doanh-thu.php");
            WebSettings webSettingDTCurrent = doanhThuThangHienTai.getSettings();
            webSettingDTCurrent.setJavaScriptEnabled(true);

            doanhThuThangTruoc = findViewById(R.id.doanhThuThangTruoc);
            doanhThuThangTruoc.loadUrl(url+"/admin/api/thong-ke/doanh-thu-truoc.php");
            WebSettings webSettingDTBefore = doanhThuThangTruoc.getSettings();
            webSettingDTBefore.setJavaScriptEnabled(true);

            thietBiCacThang = findViewById(R.id.thietBiCacThang);
            thietBiCacThang.loadUrl(url+"/admin/api/thong-ke/thiet-bi.php");
            WebSettings webSettingTB = thietBiCacThang.getSettings();
            webSettingTB.setJavaScriptEnabled(true);

            tienThietBiCacThang = findViewById(R.id.tienThietBiCacThang);
            tienThietBiCacThang.loadUrl(url+"/admin/api/thong-ke/tien-thiet-bi.php");
            WebSettings webSettingTTB = tienThietBiCacThang.getSettings();
            webSettingTTB.setJavaScriptEnabled(true);

            gioiTinh = findViewById(R.id.gioiTinh);
            gioiTinh.loadUrl(url+"/admin/api/thong-ke/gioi-tinh.php");
            WebSettings webSettingGT = gioiTinh.getSettings();
            webSettingGT.setJavaScriptEnabled(true);

            tinhThanh = findViewById(R.id.tinhThanh);
            tinhThanh.loadUrl(url+"/admin/api/thong-ke/tinh-thanh.php");
            WebSettings webSettingTinhThanh = tinhThanh.getSettings();
            webSettingTinhThanh.setJavaScriptEnabled(true);

            doTuoiKhach = findViewById(R.id.doTuoiKhach);
            doTuoiKhach.loadUrl(url+"/admin/api/thong-ke/do-tuoi.php");
            WebSettings webSettingDoTuoi = doTuoiKhach.getSettings();
            webSettingDoTuoi.setJavaScriptEnabled(true);

            menuDanhMuc = findViewById(R.id.menuDanhMuc);
            mDrawerLayout = findViewById(R.id.drawer_layout_thong_ke);

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
                            Intent khachTro = new Intent(ThongKe.this, KhachTro.class);
                            startActivity(khachTro);
                            return true;
                        case R.id.dat_coc:
                            Intent datCoc = new Intent(ThongKe.this, TienCocAdd.class);
                            startActivity(datCoc);
                            return true;
                        case R.id.thanh_toan:
                            Intent thanhToan = new Intent(ThongKe.this, DongTien.class);
                            startActivity(thanhToan);
                            return true;
                        case R.id.hop_dong:
                            Intent hopDong = new Intent(ThongKe.this, HopDong.class);
                            startActivity(hopDong);
                            return true;
                        case R.id.thay_cong_to_nuoc:
                            Intent thay_cong_to_nuoc = new Intent(ThongKe.this, CongToNuoc.class);
                            startActivity(thay_cong_to_nuoc);
                            return true;
                        case R.id.thay_cong_to_dien:
                            Intent thay_cong_to_dien = new Intent(ThongKe.this, CongToDien.class);
                            startActivity(thay_cong_to_dien);
                            return true;
                        case R.id.doi_thiet_bi:
                            Intent doi_thiet_bi = new Intent(ThongKe.this, DoiThietBi.class);
                            startActivity(doi_thiet_bi);
                            return true;

                    }
                    return true;
                }
            });

            TextView doanhThuThangThongKe = findViewById(R.id.doanhThuThangThongKe);
            ApiQH.apiQH.getQuyTien().enqueue(new Callback<QuyTienModel>() {
                @Override
                public void onResponse(Call<QuyTienModel> call, Response<QuyTienModel> response) {
                    QuyTienModel listQuyTien = response.body();
                    String doanhThuHienTaiText = listQuyTien.getDoanhthu();
                    doanhThuThangThongKe.setText(doanhThuHienTaiText+"đ");
                }

                @Override
                public void onFailure(Call<QuyTienModel> call, Throwable t) {

                }
            });
    }

}