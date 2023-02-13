package com.example.nhatro2.tien_coc;

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
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TienCoc extends AppCompatActivity {
    LinearLayout idNguoiCoc;
    ImageView thoat, logo, menuDanhMuc, addTienCoc;
    SharedPreferences shp;
    DrawerLayout mDrawerLayout;
    RecyclerView listKhachCoc;
    List<TienCocModel> listTienCoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_coc);
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences shpKhachCoc = getApplicationContext().getSharedPreferences("khachCocChon", MODE_PRIVATE);
                SharedPreferences.Editor shpKhachCocEdit = shpKhachCoc.edit();
                shpKhachCocEdit.remove("tenKhach");
                shpKhachCocEdit.remove("sdtKhach");
                shpKhachCocEdit.remove("idKhach");
                shpKhachCocEdit.apply();
                Intent intent = new Intent(TienCoc.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TienCoc.this);
                builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Yes</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TienCoc.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TienCoc.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>No</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(TienCoc.this, "Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // Xét ví trí tương đối
        @SuppressLint("WrongViewCast")
        FrameLayout imageFrame = findViewById(R.id.imageTienCoc);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.tiencoc);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);


        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_coc);

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
                        Intent khachTro = new Intent(TienCoc.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(TienCoc.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(TienCoc.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(TienCoc.this, HopDong.class);
                        startActivity(hopDong);
                        return true;


                }
                return true;
            }
        });

        listKhachCoc = findViewById(R.id.listKhachCoc);
        listKhachCoc.setLayoutManager(new LinearLayoutManager(TienCoc.this));
        listKhachCoc.hasFixedSize();
        listKhachCoc.setNestedScrollingEnabled(false);

        ApiQH.apiQH.listCoc().enqueue(new Callback<List<TienCocModel>>() {
            @Override
            public void onResponse(Call<List<TienCocModel>> call, Response<List<TienCocModel>> response) {
                listTienCoc = response.body();
                listKhachCoc.setAdapter(new TienCocAdapter(TienCoc.this,listTienCoc));
            }

            @Override
            public void onFailure(Call<List<TienCocModel>> call, Throwable t) {

            }
        });

        addTienCoc = findViewById(R.id.addTienCoc);
        addTienCoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TienCoc.this,TienCocAdd.class);
                startActivity(intent);
            }
        });
    }
}