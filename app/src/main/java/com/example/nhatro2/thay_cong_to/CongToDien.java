package com.example.nhatro2.thay_cong_to;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.doi_thiet_bi.DoiThietBi;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.phong.Phong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thu_khac.ThuKhac;
import com.example.nhatro2.thu_khac.ThuKhacModel;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CongToDien extends AppCompatActivity {
    ImageView thoat, logo, menuDanhMuc;
    SharedPreferences shpPhongCongTo;
    SharedPreferences.Editor shpPhongCongToEdit;
    DrawerLayout mDrawerLayout;
    EditText phongCongToDien,chiSoCongToDien ;
    Dialog dialogThuKhacDetail;
    LinearLayout quayLai;
    TextView thayCongToDien;
    FloatingActionButton chonPhongCongToDien;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cong_to_dien);

        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CongToDien.this, HomeActivity.class);
                startActivity(intent);
                shpPhongCongToEdit = shpPhongCongTo.edit();
                shpPhongCongToEdit.remove("tenPhong");
                shpPhongCongToEdit.commit();
            }
        });

        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CongToDien.super.onBackPressed();
                shpPhongCongToEdit = shpPhongCongTo.edit();
                shpPhongCongToEdit.remove("tenPhong");
                shpPhongCongToEdit.commit();
            }
        });

        // Xét vị trí tương đối
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FrameLayout imageKhaBien = findViewById(R.id.imageCongToDien);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.ic_ctdien);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageKhaBien.addView(iv, params);

        chonPhongCongToDien= findViewById(R.id.chonPhongCongToDien);
        chonPhongCongToDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetPhongCongToDien bsCTDien = new BottomSheetPhongCongToDien();
                bsCTDien.show(getSupportFragmentManager(),"CongToDien");
            }
        });

        shpPhongCongTo = getSharedPreferences("phongCongToDien", MODE_PRIVATE);
        String tenPhongCTD = shpPhongCongTo.getString("tenPhong","");
        phongCongToDien = findViewById(R.id.phongCongToDien);
        chiSoCongToDien = findViewById(R.id.chiSoCongToDien);
        thayCongToDien= findViewById(R.id.thayCongToDien);


        thayCongToDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phongCongToDien.setText(tenPhongCTD);
                String chiSoDienThay = chiSoCongToDien.getText().toString();
                ApiQH.apiQH.congToDien(tenPhongCTD,chiSoDienThay).enqueue(new Callback<CongToDienModel>() {
                    @Override
                    public void onResponse(Call<CongToDienModel> call, Response<CongToDienModel> response) {
                        Toast.makeText(CongToDien.this,"Thay công tơ điện thành công",Toast.LENGTH_SHORT).show();
                        shpPhongCongToEdit = shpPhongCongTo.edit();
                        shpPhongCongToEdit.remove("tenPhong");
                        shpPhongCongToEdit.commit();
                        Intent intent = new Intent(CongToDien.this,CongToDien.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<CongToDienModel> call, Throwable t) {
                        Toast.makeText(CongToDien.this,"Thay công tơ điện thất bại",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_cong_to_dien);

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
                        Intent khachTro = new Intent(CongToDien.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(CongToDien.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(CongToDien.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(CongToDien.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                    case R.id.thay_cong_to_nuoc:
                        Intent thay_cong_to_nuoc = new Intent(CongToDien.this, CongToNuoc.class);
                        startActivity(thay_cong_to_nuoc);
                        return true;
                    case R.id.thay_cong_to_dien:
                        Intent thay_cong_to_dien = new Intent(CongToDien.this, CongToDien.class);
                        startActivity(thay_cong_to_dien);
                        return true;
                    case R.id.doi_thiet_bi:
                        Intent doi_thiet_bi = new Intent(CongToDien.this, DoiThietBi.class);
                        startActivity(doi_thiet_bi);
                        return true;

                }
                return true;
            }
        });
    }
}