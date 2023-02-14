package com.example.nhatro2.phong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.kha_bien.KhaBien;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MultiRoom extends AppCompatActivity {
    ImageView thoat,logo,menuDanhMuc;
    SharedPreferences shp;
    String listRoom, tenPhongchecked;
    List<PhongModel> phongCheckedList;
    TextView nameRoomChecked,capNhatMulti;
    RecyclerView phongList;
    EditText tenDaiDien, tiencoc, dienThoaiDaiDien;
    DrawerLayout mDrawerLayout;
    LinearLayout quayLai;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_room);

        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiRoom.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultiRoom.super.onBackPressed();
            }
        });

        // SharedPreferences sharedPhong = getSharedPreferences("idPhong", Context.MODE_PRIVATE);
        Bundle bundle = getIntent().getExtras();
        listRoom = bundle.getString("idRoom");
        nameRoomChecked = findViewById(R.id.nameRoomChecked);
        phongList = findViewById(R.id.roomChecked);
        phongList.setLayoutManager(new GridLayoutManager(MultiRoom.this, 3));
        phongList.hasFixedSize();
        phongList.setNestedScrollingEnabled(false);

        ApiQH.apiQH.phongChecked(listRoom).enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                List<PhongModel> phongCheck = response.body();
                phongCheckedList = response.body();
                int sizePhong = phongCheck.size();
                for (int i = 0; i < sizePhong; i++) {
                    tenPhongchecked = phongCheck.get(i).toString();
                    phongList.setAdapter(new MultiRoomAdapter(MultiRoom.this, phongCheckedList));
                }
            }
            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {
            }
        });

        capNhatMulti = findViewById(R.id.capNhatMulti);
        tenDaiDien = findViewById(R.id.tenDaiDienText);
        tiencoc = findViewById(R.id.tienCoc);
        dienThoaiDaiDien = findViewById(R.id.sdtDaiDienText);
        capNhatMulti.setOnClickListener(new View.OnClickListener() {
            SharedPreferences sharedPhong = getSharedPreferences("idPhong", Context.MODE_PRIVATE);
            SharedPreferences.Editor roomEditor = sharedPhong.edit();
            String listRoom = sharedPhong.getString("items", "");
            @Override
            public void onClick(View view) {
                String tenKhach = tenDaiDien.getText().toString();
                String tienCoc = tiencoc.getText().toString();
                String dienThoai  = dienThoaiDaiDien.getText().toString();
                ApiQH.apiQH.datCocPhong(tenKhach,dienThoai,tienCoc,listRoom).enqueue(new Callback<PhongMultiModel>() {
                    @Override
                    public void onResponse(Call<PhongMultiModel> call, Response<PhongMultiModel> response) {
                        PhongMultiModel phongChecked = response.body();
                        Intent intent = new Intent(MultiRoom.this,Phong.class);
                        startActivity(intent);
                        roomEditor.remove("items");
                        roomEditor.commit();
                        Toast.makeText(MultiRoom.this, "Đặt cọc cho nhiều phòng thành công",Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onFailure(Call<PhongMultiModel> call, Throwable t) {
                        Toast.makeText(MultiRoom.this, "Đặt cọc cho nhiều phòng thất bại",Toast.LENGTH_SHORT);
                    }
                });
            }
        });
        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_nhieu_phong);

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
                        Intent khachTro = new Intent(MultiRoom.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(MultiRoom.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(MultiRoom.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(MultiRoom.this, HopDong.class);
                        startActivity(hopDong);
                        return true;


                }
                return true;
            }
        });
    }

//    // Back button
//    public void onBackPressed()
//    {
//        super.onBackPressed();
//        SharedPreferences sharedPhong = getSharedPreferences("idPhong", Context.MODE_PRIVATE);
//        SharedPreferences.Editor roomEditor = sharedPhong.edit();
//        roomEditor.remove("items");
//        roomEditor.commit();
//    }
}