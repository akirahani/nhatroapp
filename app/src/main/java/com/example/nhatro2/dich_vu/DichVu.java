package com.example.nhatro2.dich_vu;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.bat_bien.BatBien;
import com.example.nhatro2.doi_thiet_bi.DoiThietBi;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thay_cong_to.CongToDien;
import com.example.nhatro2.thay_cong_to.CongToNuoc;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DichVu extends AppCompatActivity {
    ImageView thoat, them, logo, imageDichVu, menuDanhMuc;
    SharedPreferences shp;
    LinearLayout quayLai;
    RelativeLayout tieuDeDichVu;
    RecyclerView listThietBi;
    TextView tenThietBi, giaThietBi, tacVu;
    List<DichVuModel> dichVu;
    DichVuAdapter dichVuAdapter;
    DrawerLayout mDrawerLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dich_vu);
        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DichVu.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DichVu.super.onBackPressed();
            }
        });

        // Thiết bị
        listThietBi = findViewById(R.id.listThietBi);
        listThietBi.setLayoutManager(new LinearLayoutManager(DichVu.this));
        listThietBi.hasFixedSize();
        listThietBi.setNestedScrollingEnabled(false);

        ApiQH.apiQH.getDichVuList().enqueue(new Callback<List<DichVuModel>>() {
            @Override
            public void onResponse(Call<List<DichVuModel>> call, Response<List<DichVuModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DichVu.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                dichVu = response.body();
                dichVuAdapter = new DichVuAdapter(DichVu.this, dichVu);
                listThietBi.setAdapter(dichVuAdapter);
            }

            @Override
            public void onFailure(Call<List<DichVuModel>> call, Throwable t) {

            }
        });

        //Thêm mới
        them = findViewById(R.id.themDichVu);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DichVu.this, DichVuAdd.class);
                startActivity(intent);
                finish();
            }
        });
        // Xét vị trí tương đối
        FrameLayout imageFrame = findViewById(R.id.imageDichVu);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.dichvu);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_dich_vu);

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
                        Intent khachTro = new Intent(DichVu.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(DichVu.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(DichVu.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(DichVu.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                    case R.id.thay_cong_to_nuoc:
                        Intent thay_cong_to_nuoc = new Intent(DichVu.this, CongToNuoc.class);
                        startActivity(thay_cong_to_nuoc);
                        return true;
                    case R.id.thay_cong_to_dien:
                        Intent thay_cong_to_dien = new Intent(DichVu.this, CongToDien.class);
                        startActivity(thay_cong_to_dien);
                        return true;
                    case R.id.doi_thiet_bi:
                        Intent doi_thiet_bi = new Intent(DichVu.this, DoiThietBi.class);
                        startActivity(doi_thiet_bi);
                        return true;

                }
                return true;
            }
        });
    }
}