package com.example.nhatro2.chi_khac;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.bat_bien.BatBienAdapter;
import com.example.nhatro2.bat_bien.BatBienModel;
import com.example.nhatro2.doi_thiet_bi.DoiThietBi;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thay_cong_to.CongToDien;
import com.example.nhatro2.thay_cong_to.CongToNuoc;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;
import com.kal.rackmonthpicker.MonthType;
import com.kal.rackmonthpicker.RackMonthPicker;
import com.kal.rackmonthpicker.listener.DateMonthDialogListener;
import com.kal.rackmonthpicker.listener.OnCancelMonthDialogListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiKhac extends AppCompatActivity {
    ImageView logo, addChiKhac, menuDanhMuc;
    LinearLayout quayLai;
    SharedPreferences shp;
    RecyclerView listChiKhac;
    List<ChiKhacModel> listChiKhacGet = new ArrayList<>();
    TextView chonThangChiKhac, ChiKhacAdd, ChiKhacClose;
    RadioButton tienMatChiKhac, chuyenKhoanChiKhac;
    EditText lyDoChiKhac, tienChiKhac;
    ChiKhacAdapter ChiKhacAdapter;
    DrawerLayout mDrawerLayout;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_khac);
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiKhac.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChiKhac.super.onBackPressed();
            }
        });

        // Xét vị trí tương đối
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FrameLayout imageChiKhac = findViewById(R.id.imageChiKhac);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.ic_chikhactitle);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageChiKhac.addView(iv, params);
        // Xet danh sach bat bien
        listChiKhac = findViewById(R.id.listChiKhac);
        listChiKhac.setLayoutManager(new LinearLayoutManager(ChiKhac.this));
        listChiKhac.hasFixedSize();
        listChiKhac.setNestedScrollingEnabled(false);

        // Hien thi thang - nam hien tai cho chi phi kha bien
        DateFormat monthFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthGet = Integer.parseInt(monthFormat.format(date));
        // set Text hien thi thang - nam hien tai cho chi phi kha bien
        chonThangChiKhac = findViewById(R.id.chonThangChiKhac);
        chonThangChiKhac.setText("Tháng "+monthFormat.format(date)+" - năm "+year);

        ApiQH.apiQH.getChiKhac().enqueue(new Callback<List<ChiKhacModel>>() {
            @Override
            public void onResponse(Call<List<ChiKhacModel>> call, Response<List<ChiKhacModel>> response) {
                listChiKhacGet = response.body();
                listChiKhac.setAdapter(new ChiKhacAdapter(ChiKhac.this,listChiKhacGet));
            }

            @Override
            public void onFailure(Call<List<ChiKhacModel>> call, Throwable t) {
                Log.d("Loi hien thi chi khac","");
            }
        });

        chonThangChiKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RackMonthPicker rackMonthPicker = new RackMonthPicker(ChiKhac.this);
                rackMonthPicker.setLocale(Locale.ENGLISH)
                        .setNegativeText("Đóng")
                        .setPositiveText("Chọn")
                        .setMonthType(MonthType.NUMBER)
                        .setColorTheme(R.color.tenPhongColor)
                        .setPositiveButton(new DateMonthDialogListener() {
                            @Override
                            public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {
                                chonThangChiKhac.setText("Tháng "+month+" - năm "+year);
                                ApiQH.apiQH.chooseTimeChiKhac(month,year).enqueue(new Callback<List<ChiKhacModel>>() {
                                    @Override
                                    public void onResponse(Call<List<ChiKhacModel>> call, Response<List<ChiKhacModel>> response) {
                                        listChiKhacGet = response.body();
                                        ChiKhacAdapter = new ChiKhacAdapter(ChiKhac.this, listChiKhacGet);
                                        listChiKhac.setAdapter(ChiKhacAdapter);
                                    }

                                    @Override
                                    public void onFailure(Call<List<ChiKhacModel>> call, Throwable t) {
                                        Log.d("err",""+t.toString());
                                    }
                                });

                            }
                        }).setNegativeButton(new OnCancelMonthDialogListener() {
                            @Override
                            public void onCancel(AlertDialog dialog) {
                                rackMonthPicker.dismiss();
                            }
                        });
                rackMonthPicker.show();
            }
        });

        addChiKhac = findViewById(R.id.addChiKhac);

        addChiKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialogChiKhac = new Dialog(ChiKhac.this);
                dialogChiKhac.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogChiKhac.setContentView(R.layout.layout_dialog_them_phi_chi_khac);
                Window window = dialogChiKhac.getWindow();
                if (window == null) {
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAttr = window.getAttributes();
                windowAttr.gravity = Gravity.CENTER;
                window.setAttributes(windowAttr);

                ChiKhacAdd = dialogChiKhac.findViewById(R.id.chiKhacAdd);
                ChiKhacClose = dialogChiKhac.findViewById(R.id.chiKhacClose);

                ChiKhacAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tienMatChiKhac = dialogChiKhac.findViewById(R.id.tienMatChiKhac);
                        chuyenKhoanChiKhac = dialogChiKhac.findViewById(R.id.chuyenKhoanChiKhac);
                        int hinhThucThanhToan = 0;
                        if(tienMatChiKhac.isChecked()){
                            hinhThucThanhToan = 1;
                        }else if(chuyenKhoanChiKhac.isChecked()){
                            hinhThucThanhToan = 2;
                        }

                        lyDoChiKhac = dialogChiKhac.findViewById(R.id.lyDoChiKhacText);
                        tienChiKhac = dialogChiKhac.findViewById(R.id.tienChiKhacText);
                        String lyDoChiKhacText = lyDoChiKhac.getText().toString();
                        String tienChiKhacText = tienChiKhac.getText().toString();
                        int giaTienChiKhacFinal = Integer.parseInt(tienChiKhacText);

                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        int idThanhVienQuanLy = shp.getInt("idThanhVien",0);
                        ApiQH.apiQH.addChiKhac(idThanhVienQuanLy,lyDoChiKhacText,giaTienChiKhacFinal,hinhThucThanhToan).enqueue(new Callback<ChiKhacModel>() {
                            @Override
                            public void onResponse(Call<ChiKhacModel> call, Response<ChiKhacModel> response) {
                                ChiKhacModel khaBatThem = response.body();
                                Intent intent = new Intent(ChiKhac.this, ChiKhac.class);
                                startActivity(intent);
                                finish();
                                ChiKhacAdapter = new ChiKhacAdapter(ChiKhac.this, listChiKhacGet);
                                dialogChiKhac.dismiss();
                            }

                            @Override
                            public void onFailure(Call<ChiKhacModel> call, Throwable t) {
                                Log.d("err",""+t.toString());
                            }
                        });
                    }
                });

                ChiKhacClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogChiKhac.dismiss();
                    }
                });

                dialogChiKhac.show();
            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_chi_khac);

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
                        Intent khachTro = new Intent(ChiKhac.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(ChiKhac.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(ChiKhac.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(ChiKhac.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                    case R.id.thay_cong_to_nuoc:
                        Intent thay_cong_to_nuoc = new Intent(ChiKhac.this, CongToNuoc.class);
                        startActivity(thay_cong_to_nuoc);
                        return true;
                    case R.id.thay_cong_to_dien:
                        Intent thay_cong_to_dien = new Intent(ChiKhac.this, CongToDien.class);
                        startActivity(thay_cong_to_dien);
                        return true;
                    case R.id.doi_thiet_bi:
                        Intent doi_thiet_bi = new Intent(ChiKhac.this, DoiThietBi.class);
                        startActivity(doi_thiet_bi);
                        return true;

                }
                return true;
            }
        });
    }
}