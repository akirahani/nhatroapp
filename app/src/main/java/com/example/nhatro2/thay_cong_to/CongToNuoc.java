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
import android.util.Log;
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
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thu_khac.ThuKhacModel;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CongToNuoc extends AppCompatActivity {
    ImageView thoat, logo, menuDanhMuc;
    SharedPreferences shpPhongCongToNuoc;
    SharedPreferences.Editor shpPhongCongToNuocEdit;
    DrawerLayout mDrawerLayout;
    EditText phongCongToNuoc,chiSoCongToNuoc;
    Dialog dialogThuKhacDetail;
    LinearLayout quayLai;
    TextView thayCongToNuoc;
    FloatingActionButton chonPhongCongToNuoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cong_to_nuoc);

        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CongToNuoc.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CongToNuoc.super.onBackPressed();
            }
        });

        // Xét vị trí tương đối
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FrameLayout imageKhaBien = findViewById(R.id.imageCongToNuoc);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.ic_ctnuoc);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageKhaBien.addView(iv, params);

        chonPhongCongToNuoc= findViewById(R.id.chonPhongCongToNuoc);
        chonPhongCongToNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetPhongCongToNuoc bsCTNuoc = new BottomSheetPhongCongToNuoc();
                bsCTNuoc.show(getSupportFragmentManager(),"CongToNuoc");
            }
        });

        shpPhongCongToNuoc = getSharedPreferences("phongCongToNuoc", MODE_PRIVATE);
        String tenPhongCTN = shpPhongCongToNuoc.getString("tenPhong","");
        phongCongToNuoc = findViewById(R.id.phongCongToNuoc);
        phongCongToNuoc.setText(tenPhongCTN);
        thayCongToNuoc = findViewById(R.id.thayCongToNuoc);

        thayCongToNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chiSoCongToNuoc = findViewById(R.id.chiSoCongToNuoc);
                String chiSoNuocThay = chiSoCongToNuoc.getText().toString();

                Log.d("chi so nuoc",""+chiSoNuocThay);
                Log.d("phong thay cs nuoc",""+tenPhongCTN);
                ApiQH.apiQH.congToNuoc(tenPhongCTN,chiSoNuocThay).enqueue(new Callback<CongToNuocModel>() {
                    @Override
                    public void onResponse(Call<CongToNuocModel> call, Response<CongToNuocModel> response) {
                        Toast.makeText(CongToNuoc.this,"Thay công tơ nước thành công",Toast.LENGTH_SHORT).show();
                        shpPhongCongToNuocEdit = shpPhongCongToNuoc.edit();
                        shpPhongCongToNuocEdit.remove("tenPhong");
                        shpPhongCongToNuocEdit.commit();
                        Intent intent = new Intent(CongToNuoc.this,CongToNuoc.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<CongToNuocModel> call, Throwable t) {
                        Toast.makeText(CongToNuoc.this,"Thay công tơ điện thất bại",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_phong);

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
                        Intent khachTro = new Intent(CongToNuoc.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(CongToNuoc.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(CongToNuoc.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(CongToNuoc.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                    case R.id.thay_cong_to_nuoc:
                        Intent thay_cong_to_nuoc = new Intent(CongToNuoc.this, CongToNuoc.class);
                        startActivity(thay_cong_to_nuoc);
                        return true;
                    case R.id.thay_cong_to_dien:
                        Intent thay_cong_to_dien = new Intent(CongToNuoc.this, CongToDien.class);
                        startActivity(thay_cong_to_dien);
                        return true;
                    case R.id.doi_thiet_bi:
                        Intent doi_thiet_bi = new Intent(CongToNuoc.this, DoiThietBi.class);
                        startActivity(doi_thiet_bi);
                        return true;

                }
                return true;
            }
        });
    }
}