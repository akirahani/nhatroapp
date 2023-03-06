package com.example.nhatro2.kha_bien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
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
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.bat_bien.BatBien;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhaBien extends AppCompatActivity {
    ImageView thoat, logo, addKhaBien ,menuDanhMuc;
    SharedPreferences shp;
    LinearLayout quayLai;
    RecyclerView listKhaBien;
    List<KhaBienModel> listKhaBienGet = new ArrayList<>();
    TextView chonThangKhaBien, khaBienAdd, khaBienClose;
    EditText lyDoKhaBien, tienKhaBien;
    RadioButton tienMatKhaBien, chuyenKhoanKhaBien;
    DrawerLayout mDrawerLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kha_bien);
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KhaBien.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KhaBien.super.onBackPressed();
            }
        });

        // Xét vị trí tương đối
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FrameLayout imageKhaBien = findViewById(R.id.imageKhaBien);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.ic_khabien);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageKhaBien.addView(iv, params);
        // Xet danh sach kha bien
        listKhaBien = findViewById(R.id.listKhaBien);
        listKhaBien.setLayoutManager(new LinearLayoutManager(KhaBien.this));
        listKhaBien.hasFixedSize();
        listKhaBien.setNestedScrollingEnabled(false);

        // Hien thi thang - nam hien tai cho chi phi kha bien
        DateFormat monthFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthGet = Integer.parseInt(monthFormat.format(date));

        // set Text hien thi thang - nam hien tai cho chi phi kha bien
        chonThangKhaBien = findViewById(R.id.chonThangKhaBien);
        chonThangKhaBien.setText("Tháng "+monthFormat.format(date)+" - năm "+year);

        ApiQH.apiQH.getKhaBien().enqueue(new Callback<List<KhaBienModel>>() {
            @Override
            public void onResponse(Call<List<KhaBienModel>> call, Response<List<KhaBienModel>> response) {
                listKhaBienGet = response.body();
                listKhaBien.setAdapter(new KhaBienAdapter(KhaBien.this,listKhaBienGet));
            }

            @Override
            public void onFailure(Call<List<KhaBienModel>> call, Throwable t) {

            }
        });

        chonThangKhaBien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RackMonthPicker rackMonthPicker = new RackMonthPicker(KhaBien.this);
                rackMonthPicker.setLocale(Locale.ENGLISH)
                        .setNegativeText("Đóng")
                        .setPositiveText("Chọn")
                        .setMonthType(MonthType.NUMBER)
                        .setColorTheme(R.color.tenPhongColor)
                        .setPositiveButton(new DateMonthDialogListener() {
                    @Override
                    public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {
                        chonThangKhaBien.setText("Tháng "+month+" - năm "+year);
                        ApiQH.apiQH.chooseTimeKhaBien(month,year).enqueue(new Callback<List<KhaBienModel>>() {
                            @Override
                            public void onResponse(Call<List<KhaBienModel>> call, Response<List<KhaBienModel>> response) {
                                listKhaBienGet = response.body();
                                listKhaBien.setAdapter(new KhaBienAdapter(KhaBien.this, listKhaBienGet));
                            }

                            @Override
                            public void onFailure(Call<List<KhaBienModel>> call, Throwable t) {
                                Log.d("err",""+t.toString());
                            }
                        });

                    }
                }).setNegativeButton(new OnCancelMonthDialogListener() {
                    @Override
                    public void onCancel(AlertDialog dialog) {
                        rackMonthPicker.dismiss();
                    }
                }).show();
            }
        });

        addKhaBien = findViewById(R.id.addKhaBien);

        addKhaBien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialogKhaBien = new Dialog(KhaBien.this);
                dialogKhaBien.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogKhaBien.setContentView(R.layout.layout_dialog_them_phi_kha_bien);

                Window window = dialogKhaBien.getWindow();
                if (window == null) {
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAttr = window.getAttributes();
                windowAttr.gravity = Gravity.CENTER;
                window.setAttributes(windowAttr);

                khaBienAdd = dialogKhaBien.findViewById(R.id.khaBienAdd);
                khaBienClose = dialogKhaBien.findViewById(R.id.khaBienClose);

                khaBienAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tienMatKhaBien = dialogKhaBien.findViewById(R.id.tienMatKhaBien);
                        chuyenKhoanKhaBien = dialogKhaBien.findViewById(R.id.chuyenKhoanKhaBien);
                        int hinhThucThanhToan = 0;
                        if(tienMatKhaBien.isChecked()){
                            hinhThucThanhToan = 1;
                        }else if(chuyenKhoanKhaBien.isChecked()){
                            hinhThucThanhToan = 2;
                        }

                        lyDoKhaBien = dialogKhaBien.findViewById(R.id.lyDoKhaBienText);
                        tienKhaBien = dialogKhaBien.findViewById(R.id.tienKhaBienText);
                        String lyDoKhaBienText = lyDoKhaBien.getText().toString();
                        String tienKhaBienText = tienKhaBien.getText().toString();
                        int giaTienKhaBienFinal = Integer.parseInt(tienKhaBienText);

                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        int idThanhVienQuanLy = shp.getInt("idThanhVien",0);
                        ApiQH.apiQH.addKhaBien(idThanhVienQuanLy,lyDoKhaBienText,giaTienKhaBienFinal,hinhThucThanhToan).enqueue(new Callback<KhaBienModel>() {
                            @Override
                            public void onResponse(Call<KhaBienModel> call, Response<KhaBienModel> response) {
                                KhaBienModel khaBienThem = response.body();
                                listKhaBien.setAdapter(new KhaBienAdapter(KhaBien.this, listKhaBienGet));
                                Intent intent = new Intent(KhaBien.this, KhaBien.class);
                                startActivity(intent);
                                finish();
                                dialogKhaBien.dismiss();
                            }

                            @Override
                            public void onFailure(Call<KhaBienModel> call, Throwable t) {
                                Log.d("err",""+t.toString());
                            }
                        });
                    }
                });

                khaBienClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogKhaBien.dismiss();
                    }
                });

                dialogKhaBien.show();
            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_kha_bien);

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
                        Intent khachTro = new Intent(KhaBien.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(KhaBien.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(KhaBien.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(KhaBien.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                    case R.id.thay_cong_to_nuoc:
                        Intent thay_cong_to_nuoc = new Intent(KhaBien.this, CongToNuoc.class);
                        startActivity(thay_cong_to_nuoc);
                        return true;
                    case R.id.thay_cong_to_dien:
                        Intent thay_cong_to_dien = new Intent(KhaBien.this, CongToDien.class);
                        startActivity(thay_cong_to_dien);
                        return true;
                    case R.id.doi_thiet_bi:
                        Intent doi_thiet_bi = new Intent(KhaBien.this, DoiThietBi.class);
                        startActivity(doi_thiet_bi);
                        return true;

                }
                return true;
            }
        });
    }
}