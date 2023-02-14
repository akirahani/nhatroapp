package com.example.nhatro2.tien_coc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
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
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thu_khac.ThuKhac;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TienCocAdd extends AppCompatActivity {
    LinearLayout idNguoiCoc, quayLai;
    ImageView thoat, logo, menuDanhMuc ;
    SharedPreferences shp;
    FloatingActionButton fab;
    DrawerLayout mDrawerLayout;
    TextView datCocButton;
    EditText nguoiDongCocText, soDienThoaiNguoiCocText, idNguoiCocText, tienThanhToanText, ghiChuPhongCocText;
    RadioButton tienMatDongCoc, chuyenKhoanDongCoc;
    int checkTienCoc;
    String tenKhachCocFinal, sdtKhachCocFinal, ghiChuCocFinal, tienThanhCocToanText;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_coc_add);
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
                Intent intent = new Intent(TienCocAdd.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TienCocAdd.super.onBackPressed();
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
                        Intent khachTro = new Intent(TienCocAdd.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(TienCocAdd.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(TienCocAdd.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(TienCocAdd.this, HopDong.class);
                        startActivity(hopDong);
                        return true;


                }
                return true;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetChonKhachCoc chonKhachCoc = new BottomSheetChonKhachCoc();
                chonKhachCoc.show(getSupportFragmentManager(), "ChonKhachCoc");
            }
        });

        SharedPreferences shpKhachCoc = getApplicationContext().getSharedPreferences("khachCocChon", MODE_PRIVATE);

        String tenKhachCoc = shpKhachCoc.getString("tenKhach","");
        String sdtKhachCoc = shpKhachCoc.getString("sdtKhach","");
        int idKhachCoc = shpKhachCoc.getInt("idKhach",0);

        idNguoiCocText = findViewById(R.id.idNguoiCocText);
        nguoiDongCocText = findViewById(R.id.nguoiDongCocText);
        soDienThoaiNguoiCocText = findViewById(R.id.soDienThoaiNguoiCocText);


        tienThanhToanText = findViewById(R.id.tienThanhToanText);
        ghiChuPhongCocText = findViewById(R.id.ghiChuPhongCocText);
        tienMatDongCoc = findViewById(R.id.tienMatDongCoc);
        chuyenKhoanDongCoc = findViewById(R.id.chuyenKhoanDongCoc);

        nguoiDongCocText.setText(tenKhachCoc);
        soDienThoaiNguoiCocText.setText(sdtKhachCoc);
        idNguoiCocText.setText(""+idKhachCoc);

        idNguoiCoc = findViewById(R.id.idNguoiCoc);
        idNguoiCoc.setVisibility(View.GONE);

        datCocButton = findViewById(R.id.datCocButton);
        datCocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shp = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
                int idThanhVien = shp.getInt("idThanhVien", 0);
                tenKhachCocFinal =  nguoiDongCocText.getText().toString();
                sdtKhachCocFinal =  soDienThoaiNguoiCocText.getText().toString();
                ghiChuCocFinal =  ghiChuPhongCocText.getText().toString();
                tienThanhCocToanText =  tienThanhToanText.getText().toString();
                int tienThanhCocToanFinal = Integer.parseInt(tienThanhCocToanText);
                if(tienMatDongCoc.isChecked()){
                    checkTienCoc = 1;
                }else if (chuyenKhoanDongCoc.isChecked()){
                    checkTienCoc = 2;
                }


                ApiQH.apiQH.themCoc(idThanhVien,idKhachCoc,tenKhachCocFinal,sdtKhachCocFinal,tienThanhCocToanFinal,ghiChuCocFinal,checkTienCoc).enqueue(new Callback<TienCocModel>() {
                    @Override
                    public void onResponse(Call<TienCocModel> call, Response<TienCocModel> response) {
                        TienCocModel tienCocAdd = response.body();
                        Intent intent = new Intent(TienCocAdd.this, TienCoc.class);
                        startActivity(intent);
                        SharedPreferences shpKhachCoc = getApplicationContext().getSharedPreferences("khachCocChon", MODE_PRIVATE);
                        SharedPreferences.Editor shpKhachCocEdit = shpKhachCoc.edit();
                        shpKhachCocEdit.remove("tenKhach");
                        shpKhachCocEdit.remove("sdtKhach");
                        shpKhachCocEdit.remove("idKhach");
                        shpKhachCocEdit.apply();
                    }

                    @Override
                    public void onFailure(Call<TienCocModel> call, Throwable t) {
                    }
                });
            }
        });
    }

    // Back button
    public void onBackPressed()
    {
        super.onBackPressed();
        SharedPreferences shpKhachCoc = getApplicationContext().getSharedPreferences("khachCocChon", MODE_PRIVATE);
        SharedPreferences.Editor shpKhachCocEdit = shpKhachCoc.edit();
        shpKhachCocEdit.remove("tenKhach");
        shpKhachCocEdit.remove("sdtKhach");
        shpKhachCocEdit.remove("idKhach");
        shpKhachCocEdit.apply();
        Intent intent = new Intent(TienCocAdd.this, HomeActivity.class);
        startActivity(intent);
//        finish();
    }

}