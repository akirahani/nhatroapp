package com.example.nhatro2.phong;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

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
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhongEdit extends AppCompatActivity {
    ImageView closeFormEdit, thoat, logo, imagePhongTro, menuDanhMuc;
    RecyclerView listRoom;
    SharedPreferences shp;
    ViewPager2 tabContentViewRoom;
    TabLayout tabRoom;
    RelativeLayout tieuDePhongTro;
    LinearLayout rowFirstEditRoom, quayLai;
    RadioButton trong, thue, bangiao;
    TextView tenPhongEdit, vitriPhongEdit, editRoomButton;
    EditText tienPhong, daidien, dienthoai,tiencoc;
    CardView rowDaiDien, rowDienThoai;
    int trangthai;
    DrawerLayout mDrawerLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phong_edit);
        closeFormEdit = findViewById(R.id.closeFormEditRoom);
        closeFormEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhongEdit.this, Phong.class);
                startActivity(intent);
            }
        });
        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhongEdit.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhongEdit.super.onBackPressed();
            }
        });

        // Xét ví trí tương đối
        FrameLayout imageFrame = findViewById(R.id.tieuDePhongTro);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.phongtro);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        // Ánh xạ thông tin phòng edit
        rowDaiDien = findViewById(R.id.rowDaiDien);
        rowDienThoai = findViewById(R.id.rowDienThoai);
        trong = findViewById(R.id.trong);
        bangiao = findViewById(R.id.bangiao);
        thue = findViewById(R.id.thue);
        tienPhong = findViewById(R.id.tienPhong);
        rowFirstEditRoom = findViewById(R.id.rowFirstEditRoom);
        daidien = findViewById(R.id.daiDien);
        dienthoai = findViewById(R.id.dienThoai);
        tenPhongEdit = findViewById(R.id.tenPhongEdit);
        vitriPhongEdit = findViewById(R.id.vitriPhongEdit);
        tienPhong = findViewById(R.id.tienPhong);
        tiencoc = findViewById(R.id.tienCoc);

        // Lấy thông tin gửi từ adapter
        Bundle bundle = getIntent().getExtras();
        int idPhong = bundle.getInt("idPhong");
        int tang = bundle.getInt("tang");
        trangthai = bundle.getInt("trangthai");
        int giaPhong = bundle.getInt("gia");
        String tenPhong = bundle.getString("tenPhong");
        String dayPhong = bundle.getString("day");
        String daiDien = bundle.getString("daidien");
        String dienThoai = bundle.getString("dienthoai");
        int datcoc = bundle.getInt("datcoc");

        if (bundle == null) {
            Toast.makeText(this, "Có lỗi !", Toast.LENGTH_SHORT).show();
        } else {
            tenPhongEdit.setText("Tên: " + tenPhong);
            vitriPhongEdit.setText("Vị trí: Dãy " + dayPhong + " - Tầng " + tang);
            tienPhong.setText("" + giaPhong);
            if (trangthai == 2 || trangthai == 3) {
                daidien.setText(daiDien);
                dienthoai.setText(dienThoai);
                tiencoc.setText(""+datcoc);
            } else {
                daidien.setText("");
                dienthoai.setText("");
                tiencoc.setText("");
            }

            switch (trangthai) {
                case 1:
                    trong.setChecked(true);
                    int color1 = Color.parseColor("#F4F7FF");
                    rowFirstEditRoom.setBackgroundColor(color1);
                    thue.setVisibility(View.GONE);
                    break;
                case 2:
                    thue.setChecked(true);
                    int color2 = Color.parseColor("#FFF7F7");
                    rowFirstEditRoom.setBackgroundColor(color2);
                    bangiao.setVisibility(View.GONE);
                    break;
                case 3:
                    bangiao.setChecked(true);
                    int color3 = Color.parseColor("#FFFDF5");
                    thue.setVisibility(View.GONE);
                    rowFirstEditRoom.setBackgroundColor(color3);
                    break;
            }
        }

        // Ánh xạ cho việc cập nhật phòng
        SharedPreferences sharedPhong = getSharedPreferences("idPhong", Context.MODE_PRIVATE);
        SharedPreferences.Editor roomEditor = sharedPhong.edit();
        String listRoom = sharedPhong.getString("items", "");
        editRoomButton = findViewById(R.id.editRoomButton);
        editRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int trangThaiPost = 0;
                String tenDaiDien = daidien.getText().toString();
                String dienThoai = dienthoai.getText().toString();

                if (trong.isChecked()) {
                    trangThaiPost = 1;
                } else if (bangiao.isChecked()) {
                    trangThaiPost = 3;
                } else if (thue.isChecked()) {
                    trangThaiPost = 2;
                }

                int finalTrangThaiPost = trangThaiPost;
                ApiQH.apiQH.editPhong(idPhong, trangthai, trangThaiPost, tenDaiDien, dienThoai).enqueue(new Callback<PhongModel>() {
                    @Override
                    public void onResponse(Call<PhongModel> call, Response<PhongModel> response) {
                        if( finalTrangThaiPost ==3 || finalTrangThaiPost == 2 ){
                            roomEditor.remove("items");
                            roomEditor.commit();
                        }
                        PhongModel phongEdit = response.body();
                        if (phongEdit.getId() == idPhong) {
                            Toast.makeText(PhongEdit.this, "Cập nhật phòng thành công !", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PhongEdit.this, Phong.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(PhongEdit.this, "Cập nhật phòng không thành công !", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<PhongModel> call, Throwable t) {
                        Toast.makeText(PhongEdit.this, "Cập nhật phòng không thành công !", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_phong_edit);

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
                        Intent khachTro = new Intent(PhongEdit.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(PhongEdit.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(PhongEdit.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(PhongEdit.this, HopDong.class);
                        startActivity(hopDong);
                        return true;


                }
                return true;
            }
        });
    }


}
