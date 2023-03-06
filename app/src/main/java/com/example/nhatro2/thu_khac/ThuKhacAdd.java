package com.example.nhatro2.thu_khac;

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
import android.util.Log;
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
import com.example.nhatro2.doi_thiet_bi.DoiThietBi;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thay_cong_to.CongToDien;
import com.example.nhatro2.thay_cong_to.CongToNuoc;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThuKhacAdd extends AppCompatActivity {
    ImageView thoat, logo, menuDanhMuc, chonPhongThuKhac;
    SharedPreferences shp;
    DrawerLayout mDrawerLayout;
    TextView thuKhacAddButton, thuKhacAddClose;
    int idPhongThuKhac, checkTien;
    String phongThuKhac;
    EditText phongThuKhacDialog, lyDoThuKhacText, tienThuKhacText;
    RadioButton thuaTienThuKhac, thieuTienThuKhac;
    LinearLayout quayLai;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_khac_add);

        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThuKhacAdd.this, HomeActivity.class);
                startActivity(intent);
                SharedPreferences shpKhach = view.getContext().getSharedPreferences("khachChonHopDongAdd", MODE_PRIVATE);
                SharedPreferences.Editor shpKhachEdit = shpKhach.edit();
                shpKhachEdit.remove("idKhachChon");
                shpKhachEdit.apply();
            }
        });
        // quay loại
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThuKhacAdd.super.onBackPressed();
            }
        });

        // Xét ví trí tương đối
        @SuppressLint("WrongViewCast")
        FrameLayout imageFrame = findViewById(R.id.imageThuKhacAdd);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.thu_khac);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        chonPhongThuKhac = findViewById(R.id.chonPhongThuKhac);
        chonPhongThuKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetChonPhongThuKhac chonPhongThuKhacTien = new BottomSheetChonPhongThuKhac();
                chonPhongThuKhacTien.show(getSupportFragmentManager(), "ChonPhongThuKhac");
            }
        });

        thuKhacAddButton = findViewById(R.id.thuKhacAddButton);
        thuKhacAddClose = findViewById(R.id.thuKhacAddClose);

        SharedPreferences shpPhongThuKhacChon = getApplicationContext().getSharedPreferences("phongThuKhacChon", MODE_PRIVATE);
        phongThuKhac = shpPhongThuKhacChon.getString("idPhongThuKhacChon","");
        idPhongThuKhac = shpPhongThuKhacChon.getInt("maPhongThuKhacChon",0);
        phongThuKhacDialog = findViewById(R.id.phongThuKhacDialog);
        phongThuKhacDialog.setText(phongThuKhac);


        thuKhacAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lyDoThuKhacText = findViewById(R.id.lyDoThuKhacText);
                tienThuKhacText = findViewById(R.id.tienThuKhacText);
                String lyDoThuKhacTextFinal = lyDoThuKhacText.getText().toString();

                String tienThuKhacTextProcess = tienThuKhacText.getText().toString();

                thuaTienThuKhac = findViewById(R.id.thuaTienThuKhac);
                thieuTienThuKhac = findViewById(R.id.thieuTienThuKhac);

                if (thuaTienThuKhac.isChecked()){
                    checkTien = 1;
                }else if(thieuTienThuKhac.isChecked()){
                    checkTien = 0;
                }
                shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                int idThanhVienQuanLy = shp.getInt("idThanhVien",0);


                if(lyDoThuKhacTextFinal.equals("") ){
                    Toast.makeText(ThuKhacAdd.this,"Vui lòng nhập lý do!",Toast.LENGTH_SHORT).show();
                }else{
                    if(tienThuKhacTextProcess.equals("")){
                        Toast.makeText(ThuKhacAdd.this,"Vui lòng nhập khoản tiền thu khác!",Toast.LENGTH_SHORT).show();
                    }else{
                        int tienThuKhacTextFinal = Integer.parseInt(tienThuKhacTextProcess);
                        ApiQH.apiQH.addThuKhac(idThanhVienQuanLy,lyDoThuKhacTextFinal,tienThuKhacTextFinal,checkTien, idPhongThuKhac ).enqueue(new Callback<ThuKhacModel>() {
                            @Override
                            public void onResponse(Call<ThuKhacModel> call, Response<ThuKhacModel> response) {
                                ThuKhacModel thKhacItemAdd = response.body();
                                Intent intent = new Intent(ThuKhacAdd.this, ThuKhac.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(ThuKhacAdd.this,"Thêm khoản thu khác thành công",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<ThuKhacModel> call, Throwable t) {
                            }
                        });
                    }

                }

            }
        });

        thuKhacAddClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThuKhacAdd.this, ThuKhac.class);
                startActivity(intent);
                finish();
            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_thu_khac);

        menuDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.khach_tro:
                        Intent khachTro = new Intent(ThuKhacAdd.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(ThuKhacAdd.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(ThuKhacAdd.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(ThuKhacAdd.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                    case R.id.thay_cong_to_nuoc:
                        Intent thay_cong_to_nuoc = new Intent(ThuKhacAdd.this, CongToNuoc.class);
                        startActivity(thay_cong_to_nuoc);
                        return true;
                    case R.id.thay_cong_to_dien:
                        Intent thay_cong_to_dien = new Intent(ThuKhacAdd.this, CongToDien.class);
                        startActivity(thay_cong_to_dien);
                        return true;
                    case R.id.doi_thiet_bi:
                        Intent doi_thiet_bi = new Intent(ThuKhacAdd.this, DoiThietBi.class);
                        startActivity(doi_thiet_bi);
                        return true;
                }
                return true;
            }
        });


    }
}