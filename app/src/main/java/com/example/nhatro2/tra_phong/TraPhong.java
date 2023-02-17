package com.example.nhatro2.tra_phong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thu_khac.ThuKhacModel;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraPhong extends AppCompatActivity {
    ImageView logo, menuDanhMuc;
    SharedPreferences.Editor shpKhachEdit;
    LinearLayout quayLai;
    DrawerLayout mDrawerLayout;
    TextView textTitleTraPhong, tieuDeKhachTraPhong;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_phong);

        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TraPhong.this, HomeActivity.class);
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
                TraPhong.super.onBackPressed();
            }
        });

        // Xét ví trí tương đối
        @SuppressLint({"WrongViewCast", "MissingInflatedId", "LocalSuppress"})
        FrameLayout imageFrame = findViewById(R.id.imageTraPhong);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.traphong);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        // Menu slide
        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_dong_tien);

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
                        Intent khachTro = new Intent(TraPhong.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(TraPhong.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(TraPhong.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(TraPhong.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                }
                return true;
            }
        });

        Bundle bundle = getIntent().getExtras();
        String tenPhong = bundle.getString("tenPhong","");
        int idPhong = bundle.getInt("idPhong",0);
        int chuPhong = bundle.getInt("chuPhongChon",0);
        int hopDong = bundle.getInt("hopdong",0);
        String time = bundle.getString("time","");

        // Ánh xạ
        tieuDeKhachTraPhong = findViewById(R.id.tieuDeKhachTraPhong);
        textTitleTraPhong = findViewById(R.id.textTitleTraPhong);
        tieuDeKhachTraPhong.setText("Đóng hợp đồng số "+hopDong);
        textTitleTraPhong.setText("Trả phòng "+tenPhong);
        ApiQH.apiQH.getThongTinTraPhong(time,hopDong).enqueue(new Callback<TraPhongModel>() {
            @Override
            public void onResponse(Call<TraPhongModel> call, Response<TraPhongModel> response) {

            }

            @Override
            public void onFailure(Call<TraPhongModel> call, Throwable t) {

            }
        });

    }
}