package com.example.nhatro2.doi_thiet_bi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.chi_khac.ChiKhac;
import com.google.android.material.tabs.TabLayout;

public class DoiThietBi extends AppCompatActivity {
    ImageView thoat, logo, themNguoiThue, menuDanhMuc;
    SharedPreferences shp;
    LinearLayout quayLai;
    SharedPreferences.Editor shpKhachEdit;
    DrawerLayout mDrawerLayout;
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
            }
        });
        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoiThietBi.super.onBackPressed();
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
    }
}