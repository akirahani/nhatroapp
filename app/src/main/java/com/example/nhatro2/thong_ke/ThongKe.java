package com.example.nhatro2.thong_ke;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.quy_tien_mat.QuyTienModel;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;
import com.highsoft.highcharts.common.hichartsclasses.HIChart;
import com.highsoft.highcharts.common.hichartsclasses.HIColumn;
import com.highsoft.highcharts.common.hichartsclasses.HIOptions;
import com.highsoft.highcharts.common.hichartsclasses.HITitle;
import com.highsoft.highcharts.common.hichartsclasses.HIYAxis;
import com.highsoft.highcharts.core.*;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongKe extends AppCompatActivity {
    ImageView thoat, logo, menuDanhMuc;
    SharedPreferences shp;
    DrawerLayout mDrawerLayout;
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
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ThongKe.this);
                builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Yes</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ThongKe.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ThongKe.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>No</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(ThongKe.this, "Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


        // Biểu đồ cột: tổng quan(bar chart)
        HIChartView chartView = (HIChartView) findViewById(R.id.tongQuanChart);
        HIOptions options = new HIOptions();

        HIChart chart = new HIChart();
        chart.setType("column");
        options.setChart(chart);

        HITitle title = new HITitle();
        title.setText("Tổng quan");
        options.setTitle(title);

        final HIYAxis hiyAxis = new HIYAxis();
        hiyAxis.setMin(0);
        hiyAxis.setTitle(new HITitle());
        hiyAxis.getTitle().setText("");
        options.setYAxis(new ArrayList() {{
            add(hiyAxis);
        }});
//
//        final HIXAxis hixAxis = new HIXAxis();
//        ArrayList categories = new ArrayList<>();
//        categories.add("Goals");
//        categories.add("Assists");
//        categories.add("Shots On Goal");
//
//        hixAxis.setCategories(categories);
//        options.setXAxis(new ArrayList() {{
//            add(hixAxis);
//        }});

        HIColumn phongTrongColumn = new HIColumn();
        HIColumn phongThueColumn = new HIColumn();
        HIColumn khachThueColumn = new HIColumn();

        phongTrongColumn.setName("Phòng trống");
        ArrayList phongTrongColumnData = new ArrayList<>();

        phongThueColumn.setName("Phòng đang thuê");
        ArrayList phongThueColumnData = new ArrayList<>();

        khachThueColumn.setName("Khách trọ");
        ArrayList khachThueColumnData = new ArrayList<>();


//        ApiQH.apiQH.getTongQuan().enqueue(new Callback<TongQuanChartModel>() {
//            @Override
//            public void onResponse(Call<TongQuanChartModel> call, Response<TongQuanChartModel> response) {
//                TongQuanChartModel tongQuanDetail = response.body();
//                int soPhongTrong = tongQuanDetail.getPhongtrong();
//                int soPhongThue = tongQuanDetail.getPhongthue();
//                int soKhachThue = tongQuanDetail.getThanhvien();
//
//                Log.d("trong day",""+soPhongTrong);
//                Log.d("thue day",""+soPhongThue);
//                Log.d("khach day",""+soKhachThue);
//            }
//            @Override
//            public void onFailure(Call<TongQuanChartModel> call, Throwable t) {
//            }
//        });

        phongThueColumnData.add(100);
        phongThueColumnData.add(15);
        khachThueColumnData.add(11);

        phongTrongColumn.setData(phongTrongColumnData);
        phongThueColumn.setData(phongThueColumnData);
        khachThueColumn.setData(khachThueColumnData);


        ArrayList series = new ArrayList<>();

        series.add(phongThueColumn);
        series.add(phongTrongColumn);
        series.add(khachThueColumn);

        options.setSeries(series);
        chartView.setOptions(options);

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