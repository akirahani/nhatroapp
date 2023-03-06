package com.example.nhatro2.tien_dien;

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
import android.icu.text.DecimalFormat;
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

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.doi_thiet_bi.DoiThietBi;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thay_cong_to.CongToDien;
import com.example.nhatro2.thay_cong_to.CongToNuoc;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.example.nhatro2.tien_nuoc.TienNuoc;
import com.example.nhatro2.tien_nuoc.TienNuocEdit;
import com.google.android.material.navigation.NavigationView;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TienDienEdit extends AppCompatActivity {
    ImageView logo, closePhongDienEdit, menuDanhMuc;
    SharedPreferences shp;
    TextView tenPhongDienEdit, daiDienPhongDien, infoTimePhongDien, tieuDeLichSuDungDien, btnUpdateElectric;
    EditText soDauDien, soCuoiDien, soDienSuDung, tienDienSuDung, ngayDo;
    RecyclerView listElectricNumberUsed;
    DrawerLayout mDrawerLayout;
    LinearLayout quayLai;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_dien_edit);
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TienDienEdit.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TienDienEdit.super.onBackPressed();
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FrameLayout imageKhachTro = findViewById(R.id.imageDien);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.dientitle);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageKhachTro.addView(iv, params);

        Bundle bundle = getIntent().getExtras();
        String tenKhach = bundle.getString("tenKhach");
        int idKhach = bundle.getInt("idKhach");
        String phong = bundle.getString("phongDien");
        int tienDien = bundle.getInt("tongTien");
        int soDien = bundle.getInt("soDien");
        int soDau = bundle.getInt("soDau");
        int soCuoi = bundle.getInt("soCuoi");
        int donGia = bundle.getInt("donGia");
        int thang = bundle.getInt("thang");
        int nam = bundle.getInt("nam");
        String ngayChot =  bundle.getString("ngayChot");

        infoTimePhongDien = findViewById(R.id.infoTimePhongDien);
        tenPhongDienEdit = findViewById(R.id.tenPhongDienEdit);
        daiDienPhongDien = findViewById(R.id.daiDienPhongDien);
        soDauDien = findViewById(R.id.soDauDien);
        soCuoiDien = findViewById(R.id.soCuoiDien);
        soDienSuDung = findViewById(R.id.soDienSuDung);
        tienDienSuDung = findViewById(R.id.tienDienSuDung);
        ngayDo = findViewById(R.id.ngayDoDien);
        tieuDeLichSuDungDien = findViewById(R.id.tieuDeLichSuDungDien);
        listElectricNumberUsed = findViewById(R.id.listElectricNumberUsed);

        tieuDeLichSuDungDien.setText("Lịch sử dùng điện phòng "+phong);
        tenPhongDienEdit.setText("Phòng "+phong);
        daiDienPhongDien.setText("Đại diện: "+tenKhach);
        soDauDien.setText(""+soDau);
        soCuoiDien.setText(""+soCuoi);
        soDienSuDung.setText(""+soDien);
        DecimalFormat formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formatter = new DecimalFormat("#,###,###");
            String tienDienFormat = formatter.format(tienDien);
            tienDienSuDung.setText(""+tienDienFormat);
        }
        ngayDo.setText(ngayChot);
        infoTimePhongDien.setText("Thông tin chi tiết tiền điện tháng "+thang+"/"+nam);
        tieuDeLichSuDungDien.setText("Lịch sử dùng điện phòng "+phong);

        listElectricNumberUsed.setLayoutManager(new LinearLayoutManager(TienDienEdit.this));
        listElectricNumberUsed.hasFixedSize();
        listElectricNumberUsed.setNestedScrollingEnabled(false);

        ApiQH.apiQH.historyElectric(phong,thang,nam).enqueue(new Callback<List<LichSuDienModel>>() {
            @Override
            public void onResponse(Call<List<LichSuDienModel>> call, Response<List<LichSuDienModel>> response) {
                List<LichSuDienModel> phongDienLichSu = response.body();
                listElectricNumberUsed.setAdapter(new LichSuDienAdapter(TienDienEdit.this,phongDienLichSu));
            }

            @Override
            public void onFailure(Call<List<LichSuDienModel>> call, Throwable t) {
                Log.d("err",""+t.toString());
            }
        });

        btnUpdateElectric = findViewById(R.id.btnUpdateElectric);
        btnUpdateElectric.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText soDauDienEdit = findViewById(R.id.soDauDien);
                EditText soCuoiDienEdit = findViewById(R.id.soCuoiDien);

                String soDauEdit = soDauDienEdit.getText().toString();
                String soCuoiEdit = soCuoiDienEdit.getText().toString();
                int soDauFormat = Integer.parseInt(soDauEdit);
                int soCuoiFormat = Integer.parseInt(soCuoiEdit);

                shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                int idThanhVienQuanLy = shp.getInt("idThanhVien",0);
                ApiQH.apiQH.updateElectric(idThanhVienQuanLy,phong,thang,nam,soDauFormat,soCuoiFormat).enqueue(new Callback<TienDienModel>() {
                    @Override
                    public void onResponse(Call<TienDienModel> call, Response<TienDienModel> response) {
                        TienDienModel phongDien = response.body();
                        Toast.makeText(TienDienEdit.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TienDienEdit.this, TienDien.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<TienDienModel> call, Throwable t) {
                        Toast.makeText(TienDienEdit.this,"Không thể cập nhật ở tháng này!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        closePhongDienEdit = findViewById(R.id.closePhongDienEdit);
        closePhongDienEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_dien_edit);

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
                        Intent khachTro = new Intent(TienDienEdit.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(TienDienEdit.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(TienDienEdit.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(TienDienEdit.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                    case R.id.thay_cong_to_nuoc:
                        Intent thay_cong_to_nuoc = new Intent(TienDienEdit.this, CongToNuoc.class);
                        startActivity(thay_cong_to_nuoc);
                        return true;
                    case R.id.thay_cong_to_dien:
                        Intent thay_cong_to_dien = new Intent(TienDienEdit.this, CongToDien.class);
                        startActivity(thay_cong_to_dien);
                        return true;
                    case R.id.doi_thiet_bi:
                        Intent doi_thiet_bi = new Intent(TienDienEdit.this, DoiThietBi.class);
                        startActivity(doi_thiet_bi);
                        return true;

                }
                return true;
            }
        });

    }
}