package com.example.nhatro2.hop_dong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.dich_vu.DichVu;
import com.example.nhatro2.dich_vu.DichVuEdit;
import com.example.nhatro2.doi_thiet_bi.DoiThietBi;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.phong.Phong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thay_cong_to.CongToDien;
import com.example.nhatro2.thay_cong_to.CongToNuoc;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HopDong extends AppCompatActivity {
    ImageView thoat, logo, themNguoiThue, menuDanhMuc;
    SharedPreferences shp;
    LinearLayout quayLai;
    SharedPreferences.Editor shpKhachEdit;
    TabLayout tabRoomHopDong;
    ViewPager2 tabContentViewRoomHopDong;
    DrawerLayout mDrawerLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hop_dong);

        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HopDong.this, HomeActivity.class);
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
                HopDong.super.onBackPressed();
            }
        });
        //tab room
        tabRoomHopDong = findViewById(R.id.tabRoomHopDong);
        tabContentViewRoomHopDong = findViewById(R.id.tabContentViewRoomHopDong);

        TabHopDongAdapter tabHopDong  = new TabHopDongAdapter(HopDong.this);
        tabContentViewRoomHopDong.setAdapter(tabHopDong);

        new TabLayoutMediator(tabRoomHopDong, tabContentViewRoomHopDong, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                    default:
                        tab.setCustomView(R.layout.tab_con_hieu_luc);
                        break;
                    case 1:
                        tab.setCustomView(R.layout.tab_het_hieu_luc);
                        break;
                }
            }
        }).attach();

        //Tạo khoảng trống tab items
        int betweenSpace = 10;
        ViewGroup slidingTabStrip = (ViewGroup) tabRoomHopDong.getChildAt(0);
        for (int i=0; i<slidingTabStrip.getChildCount(); i++) {
            View v = slidingTabStrip.getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            params.rightMargin = betweenSpace;
            params.height = 90;
        }

        // Xét ví trí tương đối
        @SuppressLint("WrongViewCast")
        FrameLayout imageFrame = findViewById(R.id.imageHopDongList);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.hop_dong);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_hop_dong);

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
                        Intent khachTro = new Intent(HopDong.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(HopDong.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(HopDong.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(HopDong.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                    case R.id.thay_cong_to_nuoc:
                        Intent thay_cong_to_nuoc = new Intent(HopDong.this, CongToNuoc.class);
                        startActivity(thay_cong_to_nuoc);
                        return true;
                    case R.id.thay_cong_to_dien:
                        Intent thay_cong_to_dien = new Intent(HopDong.this, CongToDien.class);
                        startActivity(thay_cong_to_dien);
                        return true;
                    case R.id.doi_thiet_bi:
                        Intent doi_thiet_bi = new Intent(HopDong.this, DoiThietBi.class);
                        startActivity(doi_thiet_bi);
                        return true;

                }
                return true;
            }
        });
    }
}