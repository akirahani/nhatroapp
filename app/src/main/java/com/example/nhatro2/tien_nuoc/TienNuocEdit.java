package com.example.nhatro2.tien_nuoc;

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
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TienNuocEdit extends AppCompatActivity {
    ImageView logo, closePhongNuocEdit, menuDanhMuc;
    SharedPreferences shp;
    TextView tenPhongNuocEdit, daiDienPhongNuoc, infoTimePhongNuoc, tieuDeLichSuDungNuoc, btnUpdateWater;
    EditText soDauNuoc, soCuoiNuoc, soNuocSuDung, tienNuocSuDung, ngayDo;
    RecyclerView listWaterNumberUsed;
    DrawerLayout mDrawerLayout;
    LinearLayout quayLai;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_nuoc_edit);

        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TienNuocEdit.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TienNuocEdit.super.onBackPressed();
            }
        });

        // Xét vị trí tương đối
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FrameLayout imageKhachTro = findViewById(R.id.imageNuoc);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.nuoctitle);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageKhachTro.addView(iv, params);
        
        Bundle bundle = getIntent().getExtras();
        String tenKhach = bundle.getString("tenKhach");
        int idKhach = bundle.getInt("idKhach");
        String phong = bundle.getString("phongNuoc");
        int tienNuoc = bundle.getInt("tongTien");
        int soNuoc = bundle.getInt("soNuoc");
        int soDau = bundle.getInt("soDau");
        int soCuoi = bundle.getInt("soCuoi");
        int donGia = bundle.getInt("donGia");
        int thang = bundle.getInt("thang");
        int nam = bundle.getInt("nam");
        String ngayChot =  bundle.getString("ngayChot");

        infoTimePhongNuoc = findViewById(R.id.infoTimePhongNuoc);
        tenPhongNuocEdit = findViewById(R.id.tenPhongNuocEdit);
        daiDienPhongNuoc = findViewById(R.id.daiDienPhongNuoc);
        soDauNuoc = findViewById(R.id.soDauNuoc);
        soCuoiNuoc = findViewById(R.id.soCuoiNuoc);
        soNuocSuDung = findViewById(R.id.soNuocSuDung);
        tienNuocSuDung = findViewById(R.id.tienNuocSuDung);
        ngayDo = findViewById(R.id.ngayDoDien);
        tieuDeLichSuDungNuoc = findViewById(R.id.tieuDeLichSuDungNuoc);
        listWaterNumberUsed = findViewById(R.id.listWaterNumberUsed);

        tieuDeLichSuDungNuoc.setText("Lịch sử dùng nước phòng "+phong);
        tenPhongNuocEdit.setText("Phòng "+phong);
        daiDienPhongNuoc.setText("Đại diện: "+tenKhach);
        soDauNuoc.setText(""+soDau);
        soCuoiNuoc.setText(""+soCuoi);
        soNuocSuDung.setText(""+soNuoc);
        DecimalFormat formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formatter = new DecimalFormat("#,###,###");
            String tienNuocFormat = formatter.format(tienNuoc);
            tienNuocSuDung.setText(""+tienNuocFormat);
        }
        ngayDo.setText(ngayChot);
        infoTimePhongNuoc.setText("Thông tin chi tiết tiền nước tháng "+thang+"/"+nam);
        tieuDeLichSuDungNuoc.setText("Lịch sử dùng nước phòng "+phong);

        listWaterNumberUsed.setLayoutManager(new LinearLayoutManager(TienNuocEdit.this));
        listWaterNumberUsed.hasFixedSize();
        listWaterNumberUsed.setNestedScrollingEnabled(false);

        ApiQH.apiQH.historyWater(phong,thang,nam).enqueue(new Callback<List<LichSuNuocModel>>() {
            @Override
            public void onResponse(Call<List<LichSuNuocModel>> call, Response<List<LichSuNuocModel>> response) {
                List<LichSuNuocModel> phongNuocLichSu = response.body();
                listWaterNumberUsed.setAdapter(new LichSuAdapter(TienNuocEdit.this,phongNuocLichSu));
            }

            @Override
            public void onFailure(Call<List<LichSuNuocModel>> call, Throwable t) {
                Log.d("err",""+t.toString());
            }
        });

        btnUpdateWater = findViewById(R.id.btnUpdateWater);
        btnUpdateWater.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText soDauNuocEdit = findViewById(R.id.soDauNuoc);
                EditText soCuoiNuocEdit = findViewById(R.id.soCuoiNuoc);

                String soDauEdit = soDauNuocEdit.getText().toString();
                String soCuoiEdit = soCuoiNuocEdit.getText().toString();
                int soDauFormat = Integer.parseInt(soDauEdit);
                int soCuoiFormat = Integer.parseInt(soCuoiEdit);

                shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                int idThanhVienQuanLy = shp.getInt("idThanhVien",0);

//                Log.d("thanh vien",""+idThanhVienQuanLy);
//                Log.d("phong",""+phong);
//                Log.d("thang",""+thang);
//                Log.d("nam",""+nam);
//                Log.d("so dau",""+soDauFormat);
//                Log.d("so cuoi",""+soCuoiFormat);
                ApiQH.apiQH.updateWater(idThanhVienQuanLy,phong,thang,nam,soDauFormat,soCuoiFormat).enqueue(new Callback<TienNuocModel>() {
                    @Override
                    public void onResponse(Call<TienNuocModel> call, Response<TienNuocModel> response) {
                        TienNuocModel phongNuoc = response.body();
                        Toast.makeText(TienNuocEdit.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TienNuocEdit.this,TienNuoc.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<TienNuocModel> call, Throwable t) {
                        Toast.makeText(TienNuocEdit.this,"Không thể cập nhật ở tháng này!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        closePhongNuocEdit = findViewById(R.id.closePhongNuocEdit);
        closePhongNuocEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_nuoc_edit);

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
                        Intent khachTro = new Intent(TienNuocEdit.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(TienNuocEdit.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(TienNuocEdit.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(TienNuocEdit.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                }
                return true;
            }
        });
    }
}