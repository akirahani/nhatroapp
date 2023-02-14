package com.example.nhatro2.dich_vu;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DichVuAdd extends AppCompatActivity {
    ImageView them,logo,menuDanhMuc;
    EditText ten,gia;
    LinearLayout quayLai;
    TextView themDV, backDV;
    SharedPreferences shp;
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dich_vu_add);
        // logo
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DichVuAdd.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DichVuAdd.super.onBackPressed();
            }
        });


        //Thêm mới
            // Input
            ten = findViewById(R.id.tenThietBiAdd);
            gia = findViewById(R.id.giaThietBiAdd);
            // Click button
            themDV = findViewById(R.id.themDichVu);
            backDV = findViewById(R.id.backDichVu);
            // onClick Back
            backDV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DichVuAdd.this,DichVu.class);
                    startActivity(intent);
                }
            });

            // onClick Add
            themDV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tenThietBi = ten.getText().toString();
                    String giaText  = gia.getText().toString();
                    int giaThietBi =Integer.parseInt(giaText);
                    if(!tenThietBi.equals("") && !giaText.equals("")){
                        ApiQH.apiQH.addThietBi(tenThietBi,giaThietBi).enqueue(new Callback<DichVuModel>() {
                            @Override
                            public void onResponse(Call<DichVuModel> call, Response<DichVuModel> response) {
                                DichVuModel dichvu = response.body();
                                Intent intent = new Intent(DichVuAdd.this,DichVu.class);
                                Toast.makeText(DichVuAdd.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                            @Override
                            public void onFailure(Call<DichVuModel> call, Throwable t) {
                                Toast.makeText(DichVuAdd.this,"Thêm không thành công!",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });

            @SuppressLint({"MissingInflatedId", "LocalSuppress"})
            FrameLayout imageServiceAdd = findViewById(R.id.imageServiceAdd);
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(R.drawable.dichvu);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134,134);
            params.leftMargin = 46;
            params.topMargin = 18;
            imageServiceAdd.addView(iv, params);

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_dich_vu_add);

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
                        Intent khachTro = new Intent(DichVuAdd.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(DichVuAdd.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(DichVuAdd.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(DichVuAdd.this, HopDong.class);
                        startActivity(hopDong);
                        return true;


                }
                return true;
            }
        });
    }
}
