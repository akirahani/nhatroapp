package com.example.nhatro2.tien_dien.tien_nuoc;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.thanhvien.KhachTro;
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

public class TienNuoc extends AppCompatActivity {
    ImageView thoat, logo, searchNuoc, menuDanhMuc;
    SharedPreferences shp;
    TextView chonThangNuoc, searchWaterClick, searchWaterClose;
    DatePickerDialog.OnDateSetListener setListener;
    RecyclerView danhSachPhongNuoc;
    private int mYear, mMonth;
    List<TienNuocModel> phongNuoc = new ArrayList<>();
    EditText keyWaterRoom;
    String timeSendAdapter;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_nuoc);
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TienNuoc.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TienNuoc.this);
                builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Yes</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TienNuoc.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TienNuoc.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>No</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(TienNuoc.this, "Stay", Toast.LENGTH_SHORT).show();
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
        FrameLayout imageKhachTro = findViewById(R.id.imageNuoc);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.nuoctitle);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageKhachTro.addView(iv, params);

        // Danh sách phòng nước
        danhSachPhongNuoc = findViewById(R.id.danhSachPhongNuoc);
        danhSachPhongNuoc.setLayoutManager(new LinearLayoutManager(TienNuoc.this));
        danhSachPhongNuoc.hasFixedSize();
        danhSachPhongNuoc.setNestedScrollingEnabled(false);

        // Ánh xạ chọn tháng nước
        chonThangNuoc = findViewById(R.id.chonThangNuoc);
        chonThangNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat monthFormat = new SimpleDateFormat("MM");
                Date date = new Date();
                RackMonthPicker rackMonthPicker = new RackMonthPicker(TienNuoc.this);
                rackMonthPicker.setLocale(Locale.ENGLISH)
                        .setNegativeText("Đóng")
                        .setPositiveText("Chọn")
                        .setMonthType(MonthType.NUMBER)
                        .setSelectedMonth(Integer.parseInt(monthFormat.format(date)))
                        .setColorTheme(R.color.tenPhongColor)
                        .setPositiveButton(new DateMonthDialogListener() {
                            @Override
                            public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {
                                chonThangNuoc.setText("Tháng "+month+" - năm "+year);
                                ApiQH.apiQH.chooseTime(month,year).enqueue(new Callback<List<TienNuocModel>>() {
                                    @Override
                                    public void onResponse(Call<List<TienNuocModel>> call, Response<List<TienNuocModel>> response) {
                                        phongNuoc = response.body();
                                        danhSachPhongNuoc.setAdapter(new TienNuocAdapter(TienNuoc.this, phongNuoc, new NuocItemClick() {
                                            @Override
                                            public void itemOnClick(String idPhong) {
                                                ApiQH.apiQH.detailWater(idPhong,month,year).enqueue(new Callback<TienNuocModel>() {
                                                    @Override
                                                    public void onResponse(Call<TienNuocModel> call, Response<TienNuocModel> response) {
                                                        TienNuocModel detailPhongNuoc = response.body();
                                                        Intent intent = new Intent(TienNuoc.this,TienNuocEdit.class);
                                                        intent.putExtra("tenKhach",detailPhongNuoc.getTenkhach());
                                                        intent.putExtra("idKhach",detailPhongNuoc.getKhach());
                                                        intent.putExtra("phongNuoc",detailPhongNuoc.getPhong());
                                                        intent.putExtra("tongTien",detailPhongNuoc.getTien());
                                                        intent.putExtra("soNuoc",detailPhongNuoc.getSonuoc());
                                                        intent.putExtra("soDau",detailPhongNuoc.getSodau());
                                                        intent.putExtra("soCuoi",detailPhongNuoc.getSocuoi());
                                                        intent.putExtra("donGia",detailPhongNuoc.getDongia());
                                                        intent.putExtra("ngayChot",detailPhongNuoc.getNgaychot());
                                                        intent.putExtra("thang",month);
                                                        intent.putExtra("nam",year);
                                                        startActivity(intent);
                                                    }

                                                    @Override
                                                    public void onFailure(Call<TienNuocModel> call, Throwable t) {
                                                        Log.d("err",""+t.toString());
                                                    }
                                                });
                                            }
                                        }));
                                    }

                                    @Override
                                    public void onFailure(Call<List<TienNuocModel>> call, Throwable t) {
                                        Log.d("err",""+t.toString());
                                    }
                                });

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

        // Lấy ra dữ liệu phòng nước theo tháng
        ApiQH.apiQH.getTienNuoc().enqueue(new Callback<List<TienNuocModel>>() {
            @Override
            public void onResponse(Call<List<TienNuocModel>> call, Response<List<TienNuocModel>> response) {
                phongNuoc = response.body();
                DateFormat monthFormat = new SimpleDateFormat("MM");
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int monthGet = Integer.parseInt(monthFormat.format(date));


                chonThangNuoc.setText("Tháng "+monthFormat.format(date)+" - năm "+year);
                danhSachPhongNuoc.setAdapter(new TienNuocAdapter(TienNuoc.this, phongNuoc, new NuocItemClick() {
                    @Override
                    public void itemOnClick(String idPhong) {
                        ApiQH.apiQH.detailWater(idPhong,monthGet,year).enqueue(new Callback<TienNuocModel>() {
                            @Override
                            public void onResponse(Call<TienNuocModel> call, Response<TienNuocModel> response) {
                                TienNuocModel detailPhongNuocChon = response.body();
                                Intent intent = new Intent(TienNuoc.this,TienNuocEdit.class);
                                intent.putExtra("tenKhach",detailPhongNuocChon.getTenkhach());
                                intent.putExtra("idKhach",detailPhongNuocChon.getKhach());
                                intent.putExtra("phongNuoc",detailPhongNuocChon.getPhong());
                                intent.putExtra("tongTien",detailPhongNuocChon.getTien());
                                intent.putExtra("soNuoc",detailPhongNuocChon.getSonuoc());
                                intent.putExtra("soDau",detailPhongNuocChon.getSodau());
                                intent.putExtra("soCuoi",detailPhongNuocChon.getSocuoi());
                                intent.putExtra("donGia",detailPhongNuocChon.getDongia());
                                intent.putExtra("ngayChot",detailPhongNuocChon.getNgaychot());
                                intent.putExtra("thang",monthGet);
                                intent.putExtra("nam",year);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<TienNuocModel> call, Throwable t) {
                                Log.d("err chon",""+t.toString());
                            }
                        });
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<TienNuocModel>> call, Throwable t) {
            }
        });

        // Tìm kiếm phòng trọ
        searchNuoc = findViewById(R.id.searchNuoc);
        searchNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialogSearch = new Dialog(TienNuoc.this);
                dialogSearch.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogSearch.setContentView(R.layout.layout_dialog_search_phong_nuoc);

                Window window = dialogSearch.getWindow();
                if (window == null) {
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAttr = window.getAttributes();
                windowAttr.gravity = Gravity.CENTER;
                window.setAttributes(windowAttr);

                searchWaterClick = dialogSearch.findViewById(R.id.searchWaterClick);
                searchWaterClose = dialogSearch.findViewById(R.id.searchWaterClose);

                searchWaterClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        keyWaterRoom = dialogSearch.findViewById(R.id.keyWaterRoom);
                        String keyTimKiem = keyWaterRoom.getText().toString();
                        String timeChoose = chonThangNuoc.getText().toString();
                        String[] elementTime = timeChoose.split(" ");
                        int thangSend =  Integer.parseInt(elementTime[1]);
                        int namSend = Integer.parseInt(elementTime[4]);
                        ApiQH.apiQH.searchWater(keyTimKiem,thangSend,namSend).enqueue(new Callback<List<TienNuocModel>>() {
                            @Override
                            public void onResponse(Call<List<TienNuocModel>> call, Response<List<TienNuocModel>> response) {
                                List<TienNuocModel> phongCanTim = response.body();
                                danhSachPhongNuoc.setAdapter(new TienNuocAdapter(TienNuoc.this, phongCanTim, new NuocItemClick() {
                                    @Override
                                    public void itemOnClick(String idPhong) {
                                        ApiQH.apiQH.detailWater(idPhong,thangSend,namSend).enqueue(new Callback<TienNuocModel>() {
                                            @Override
                                            public void onResponse(Call<TienNuocModel> call, Response<TienNuocModel> response) {
                                                TienNuocModel detailPhongNuocTimKiem = response.body();
//                                                Log.d("chi tiet tim kiem",""+detailPhongNuocTimKiem);
                                                Intent intent = new Intent(TienNuoc.this,TienNuocEdit.class);
                                                intent.putExtra("tenKhach",detailPhongNuocTimKiem.getTenkhach());
                                                intent.putExtra("idKhach",detailPhongNuocTimKiem.getKhach());
                                                intent.putExtra("phongNuoc",detailPhongNuocTimKiem.getPhong());
                                                intent.putExtra("tongTien",detailPhongNuocTimKiem.getTien());
                                                intent.putExtra("soNuoc",detailPhongNuocTimKiem.getSonuoc());
                                                intent.putExtra("soDau",detailPhongNuocTimKiem.getSodau());
                                                intent.putExtra("soCuoi",detailPhongNuocTimKiem.getSocuoi());
                                                intent.putExtra("donGia",detailPhongNuocTimKiem.getDongia());
                                                intent.putExtra("ngayChot",detailPhongNuocTimKiem.getNgaychot());
                                                intent.putExtra("thang",thangSend);
                                                intent.putExtra("nam",namSend);
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onFailure(Call<TienNuocModel> call, Throwable t) {
                                                Log.d("err search",""+t.toString());
                                            }
                                        });
                                    }
                                }));
                                dialogSearch.dismiss();
                            }

                            @Override
                            public void onFailure(Call<List<TienNuocModel>> call, Throwable t) {
                                Log.d("err",""+t.toString());
                            }
                        });
                    }
                });

                searchWaterClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogSearch.dismiss();
                    }
                });

                dialogSearch.show();
            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_nuoc);

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
                        Intent khachTro = new Intent(TienNuoc.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(TienNuoc.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(TienNuoc.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(TienNuoc.this, HopDong.class);
                        startActivity(hopDong);
                        return true;


                }
                return true;
            }
        });
    }

}