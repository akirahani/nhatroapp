package com.example.nhatro2.doi_thiet_bi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.chi_khac.ChiKhac;
import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.dong_tien.ThietBiPhongTienAdapter;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.hop_dong.HopDongAdd;
import com.example.nhatro2.hop_dong.ThietBiAddAdapter;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thay_cong_to.CongToDien;
import com.example.nhatro2.thay_cong_to.CongToNuoc;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoiThietBi extends AppCompatActivity {
    ImageView thoat, logo, menuDanhMuc, searchPhongThietBi;
    SharedPreferences shp;
    LinearLayout quayLai;
    SharedPreferences.Editor shpKhachEdit;
    DrawerLayout mDrawerLayout;
    TextView tenPhongThietBi;
    RecyclerView listThietBiHienThi;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_thiet_bi);
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoiThietBi.this, HomeActivity.class);
                startActivity(intent);
                SharedPreferences shpPhongThietBiChon = getSharedPreferences("phongThietBiChon", MODE_PRIVATE);
                shpPhongThietBiChon.edit().remove("maPhongChon");
                shpPhongThietBiChon.edit().commit();
            }
        });
        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoiThietBi.super.onBackPressed();
                SharedPreferences shpPhongThietBiChon = getSharedPreferences("phongThietBiChon", MODE_PRIVATE);
                shpPhongThietBiChon.edit().remove("maPhongChon");
                shpPhongThietBiChon.edit().commit();
            }
        });

        // Xét vị trí tương đối
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FrameLayout imageChiKhac = findViewById(R.id.imageQuanLiThietBi);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.ic_quanlithietbi);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageChiKhac.addView(iv, params);

        // Tìm phòng
        searchPhongThietBi = findViewById(R.id.searchPhongThietBi);
        searchPhongThietBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetPhongThietBiChon bsPhongThietBi = new BottomSheetPhongThietBiChon();
                bsPhongThietBi.show(getSupportFragmentManager(),"phongThietBiList");
            }
        });

        tenPhongThietBi = findViewById(R.id.tenPhongThietBi);
        SharedPreferences shpPhongThietBiChon = getSharedPreferences("phongThietBiChon", MODE_PRIVATE);
        String tenPhong = shpPhongThietBiChon.getString("maPhongChon","");
        if(!tenPhong.equals("")){
            tenPhongThietBi.setText("Thiết bị phòng "+tenPhong+":");
        }else{
            tenPhongThietBi.setText("");
        }

        listThietBiHienThi = findViewById(R.id.listThietBiHienThi);
        listThietBiHienThi.setLayoutManager(new LinearLayoutManager(DoiThietBi.this));
        listThietBiHienThi.hasFixedSize();
        listThietBiHienThi.setNestedScrollingEnabled(false);

        ApiQH.apiQH.getPhongThietBi(tenPhong).enqueue(new Callback<DoiThietBiModel>() {
            @Override
            public void onResponse(Call<DoiThietBiModel> call, Response<DoiThietBiModel> response) {
                DoiThietBiModel phongThietBi = response.body();
                Log.d("thiet bi phong",""+phongThietBi.getThietbi());
                // Thiết bị sử dụng
                ApiQH.apiQH.getThietBiPhongTien(phongThietBi.getThietbi()).enqueue(new Callback<List<DichVuModel>>() {
                    @Override
                    public void onResponse(Call<List<DichVuModel>> call, Response<List<DichVuModel>> response) {
                        List<DichVuModel> listThietBi = response.body();
                        listThietBiHienThi.setAdapter(new PhongThietBiAdapter(DoiThietBi.this, listThietBi));
                    }

                    @Override
                    public void onFailure(Call<List<DichVuModel>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<DoiThietBiModel> call, Throwable t) {
                Log.d("err","phong thiet bi hien thi"+t.toString());
            }
        });

        // Menu slide
        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_quan_li_thiet_bi);

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
                switch (item.getItemId()) {
                    case R.id.khach_tro:
                        Intent khachTro = new Intent(DoiThietBi.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(DoiThietBi.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(DoiThietBi.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(DoiThietBi.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                    case R.id.thay_cong_to_nuoc:
                        Intent thay_cong_to_nuoc = new Intent(DoiThietBi.this, CongToNuoc.class);
                        startActivity(thay_cong_to_nuoc);
                        return true;
                    case R.id.thay_cong_to_dien:
                        Intent thay_cong_to_dien = new Intent(DoiThietBi.this, CongToDien.class);
                        startActivity(thay_cong_to_dien);
                        return true;
                    case R.id.doi_thiet_bi:
                        Intent doi_thiet_bi = new Intent(DoiThietBi.this, DoiThietBi.class);
                        startActivity(doi_thiet_bi);
                        return true;
                }
                return true;
            }
        });
    }
}