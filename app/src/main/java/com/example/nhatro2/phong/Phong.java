package com.example.nhatro2.phong;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;
import com.example.nhatro2.dich_vu.DichVu;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Phong extends AppCompatActivity {
    RecyclerView listRoom;
    SharedPreferences shp;
    PhongAdapter phongAdapter;
    ImageView thoat,logo,imageDichVu;
    ViewPager2 tabContentViewRoom;
    TabLayout tabRoom;
    FrameLayout rowTitleRoom;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phong_tro);
        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Phong.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Phong.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Phong.this,"Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Phong.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(Phong.this,"Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        // Phong
        listRoom = findViewById(R.id.listRoom);
        listRoom.setLayoutManager(new LinearLayoutManager(Phong.this));
        listRoom.hasFixedSize();
        listRoom.setNestedScrollingEnabled(false);
        // list room
        Api.api.getPhongList().enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Phong.this,response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<PhongModel> phongList = response.body();
                phongAdapter = new PhongAdapter(Phong.this,phongList);
                listRoom.setAdapter(phongAdapter);
            }
            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {
                Toast.makeText(Phong.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //tab room
        tabRoom = findViewById(R.id.tabRoom);
        tabContentViewRoom = findViewById(R.id.tabContentViewRoom);

        TabPhongAdapter tabPhong  = new TabPhongAdapter(Phong.this);
        tabContentViewRoom.setAdapter(tabPhong);

        new TabLayoutMediator(tabRoom, tabContentViewRoom, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                    default:
                        tab.setText("Phòng trống");
                        break;
                    case 1:
                        tab.setText("Bàn giao");
                        break;
                    case 2:
                        tab.setText("Đang thuê");
                        break;
                }
            }
        }).attach();

        //Tạo khoảng trống tab items
        int betweenSpace = 10;
        ViewGroup slidingTabStrip = (ViewGroup) tabRoom.getChildAt(0);
        for (int i=0; i<slidingTabStrip.getChildCount(); i++) {
            View v = slidingTabStrip.getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            params.rightMargin = betweenSpace;
            params.height = 90;
        }
        // Xét ví trí tương đối
        rowTitleRoom = findViewById(R.id.rowTitleRoom);
        ImageView iv = new ImageView(this);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(100, 100);
        params.leftMargin = 50;
        params.topMargin = 160;
        rowTitleRoom.addView(iv, params);
    }
}
