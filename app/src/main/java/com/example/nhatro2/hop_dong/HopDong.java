package com.example.nhatro2.hop_dong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

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

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.phong.Phong;
import com.example.nhatro2.phong.TabPhongAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class HopDong extends AppCompatActivity {
    ImageView thoat, logo, themNguoiThue;
    SharedPreferences shp;
    SharedPreferences.Editor shpKhachEdit;
    TabLayout tabRoomHopDong;
    ViewPager2 tabContentViewRoomHopDong;
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
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HopDong.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(HopDong.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HopDong.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(HopDong.this, "Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
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
    }
}