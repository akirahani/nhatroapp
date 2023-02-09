package com.example.nhatro2.thu_khac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import com.example.nhatro2.tien_coc.TienCoc;
import com.example.nhatro2.tien_dien.DienItemClick;
import com.example.nhatro2.tien_dien.TienDien;
import com.example.nhatro2.tien_dien.TienDienAdapter;
import com.example.nhatro2.tien_dien.TienDienEdit;
import com.example.nhatro2.tien_dien.TienDienModel;
import com.example.nhatro2.uu_dai.UuDai;
import com.google.android.material.navigation.NavigationView;
import com.kal.rackmonthpicker.MonthAdapter;
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

public class ThuKhac extends AppCompatActivity {
    ImageView thoat, logo, menuDanhMuc;
    SharedPreferences shp;
    DrawerLayout mDrawerLayout;
    TextView chonThangThuKhac;
    RecyclerView danhSachThuKhac;
    List<ThuKhacModel> thuKhacList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_khac);

        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThuKhac.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThuKhac.this);
                builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Yes</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ThuKhac.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ThuKhac.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>No</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(ThuKhac.this, "Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // Xét vị trí tương đối
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FrameLayout imageKhaBien = findViewById(R.id.imageThuKhac);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.thu_khac);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageKhaBien.addView(iv, params);

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
                switch (item.getItemId()){
                    case R.id.khach_tro:
                        Intent khachTro = new Intent(ThuKhac.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(ThuKhac.this, TienCoc.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(ThuKhac.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(ThuKhac.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                }
                return true;
            }
        });

        danhSachThuKhac = findViewById(R.id.danhSachThuKhac);
        danhSachThuKhac.setLayoutManager(new LinearLayoutManager(ThuKhac.this));
        danhSachThuKhac.hasFixedSize();
        danhSachThuKhac.setNestedScrollingEnabled(false);

        chonThangThuKhac = findViewById(R.id.chonThangThuKhac);
        chonThangThuKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RackMonthPicker rackMonthPicker = new RackMonthPicker(ThuKhac.this);

                DateFormat dateFormat = new SimpleDateFormat("MM");
                Date date = new Date();
                rackMonthPicker.setLocale(Locale.ENGLISH)
                        .setNegativeText("Đóng")
                        .setPositiveText("Chọn")
                        .setMonthType(MonthType.NUMBER)
                        .setSelectedMonth(Integer.parseInt(dateFormat.format(date)))
                        .setColorTheme(R.color.tenPhongColor)
                        .setPositiveButton(new DateMonthDialogListener() {

                            @Override
                            public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {
                                chonThangThuKhac.setText("Tháng "+month+" - năm "+year);
                            }
                        })
                        .setNegativeButton(new OnCancelMonthDialogListener() {
                            @Override
                            public void onCancel(AlertDialog dialog) {
                                rackMonthPicker.dismiss();
                            }
                        }).show();
            }
        });

        ApiQH.apiQH.getThuKhac().enqueue(new Callback<List<ThuKhacModel>>() {
            @Override
            public void onResponse(Call<List<ThuKhacModel>> call, Response<List<ThuKhacModel>> response) {
                thuKhacList = response.body();
                DateFormat monthFormat = new SimpleDateFormat("MM");
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int monthGet = Integer.parseInt(monthFormat.format(date));
                chonThangThuKhac.setText("Tháng "+monthFormat.format(date)+" - năm "+year);
                danhSachThuKhac.setAdapter(new ThuKhacAdapter(ThuKhac.this, thuKhacList));
            }

            @Override
            public void onFailure(Call<List<ThuKhacModel>> call, Throwable t) {

            }
        });
    }
}