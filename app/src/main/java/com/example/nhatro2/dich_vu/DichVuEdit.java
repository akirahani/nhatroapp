package com.example.nhatro2.dich_vu;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DichVuEdit extends AppCompatActivity {
    TextView tacVuThietBi,tieuDeTenThietBi,tieuDeGiaThietBi,backDV, capNhat;
    ImageView them,logo,menuDanhMuc;
    LinearLayout quayLai;
    EditText ten,gia,tenTB, giaTB;
    SharedPreferences shp;
    DrawerLayout mDrawerLayout;
    int id_update = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dich_vu_edit);
        // logo
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DichVuEdit.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DichVuEdit.super.onBackPressed();
            }
        });
        // onClick Back
        backDV = findViewById(R.id.backDichVu);
        backDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DichVuEdit.super.onBackPressed();
            }
        });
        // get ID
        tenTB = findViewById(R.id.tenThietBiEdit);
        giaTB = findViewById(R.id.giaThietBiEdit);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
        }
        else
        {
            int idDichVu = bundle.getInt("idDichVu");
            ApiQH.apiQH.detailDichVu(idDichVu).enqueue(new Callback<DichVuModel>() {
                @Override
                public void onResponse(Call<DichVuModel> call, Response<DichVuModel> response) {
                    DichVuModel dichVuModel = response.body();
                    if(response.isSuccessful()){
                        id_update = idDichVu;
                        if(id_update > 0 ){
                            tenTB.setText(dichVuModel.getTen());
                            giaTB.setText(""+dichVuModel.getGia());
                        }
                    }
                }

                @Override
                public void onFailure(Call<DichVuModel> call, Throwable t) {
                    Toast.makeText(DichVuEdit.this,"Có lỗi hiển thị dịch vụ",Toast.LENGTH_SHORT).show();
                    Log.d("err thiet bi",""+t.toString());
                }
            });
        }
        // Cap nhat
        capNhat = findViewById(R.id.capNhat);
        capNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_send = id_update;
                String giaThietBiText = giaTB.getText().toString();
                int giaCapNhat = 0;
                if(giaThietBiText.equals("")){
                    giaCapNhat = 0;
                }else{
                    giaCapNhat = Integer.parseInt(giaThietBiText);
                }
                String tenCapNhat = tenTB.getText().toString();
                ApiQH.apiQH.editDichVu(id_update,tenCapNhat,giaCapNhat).enqueue(new Callback<DichVuModel>() {
                    @Override
                    public void onResponse(Call<DichVuModel> call, Response<DichVuModel> response) {
                        DichVuModel dichVuModel = response.body();
                        if(dichVuModel.getId() == id_send) {
                            Intent intent = new Intent(DichVuEdit.this, DichVu.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(DichVuEdit.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(DichVuEdit.this, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<DichVuModel> call, Throwable t) {
                        Toast.makeText(DichVuEdit.this,"Lỗi cập nhật",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        float alpha = (float) 0.7;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((TextView)findViewById(R.id.tacVuThietBi)).setTextColor(Color.argb(alpha, 0, 0, 0));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((TextView)findViewById(R.id.tieuDeTenThietBi)).setTextColor(Color.argb(alpha, 0, 0, 0));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((TextView)findViewById(R.id.tieuDeGiaThietBi)).setTextColor(Color.argb(alpha, 0, 0, 0));
        }
        // Vị trí tương đối
        FrameLayout imageServiceAdd = findViewById(R.id.imageServiceEdit);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.dichvu);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134,134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageServiceAdd.addView(iv, params);

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_dich_vu_edit);

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
                        Intent khachTro = new Intent(DichVuEdit.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(DichVuEdit.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(DichVuEdit.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(DichVuEdit.this, HopDong.class);
                        startActivity(hopDong);
                        return true;


                }
                return true;
            }
        });
    }
}
