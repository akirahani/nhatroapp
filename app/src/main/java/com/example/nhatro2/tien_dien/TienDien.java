package com.example.nhatro2.tien_dien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.phong.Phong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.tien_coc.TienCoc;
import com.example.nhatro2.tien_nuoc.TienNuoc;
import com.example.nhatro2.tien_nuoc.TienNuocAdapter;
import com.example.nhatro2.tien_nuoc.TienNuocEdit;
import com.example.nhatro2.tien_nuoc.TienNuocModel;
import com.google.android.material.navigation.NavigationView;
import com.kal.rackmonthpicker.MonthType;
import com.kal.rackmonthpicker.RackMonthPicker;
import com.kal.rackmonthpicker.listener.DateMonthDialogListener;
import com.kal.rackmonthpicker.listener.OnCancelMonthDialogListener;

import java.lang.reflect.Field;
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

public class TienDien extends AppCompatActivity {
    ImageView thoat, logo, searchDien, menuDanhMuc;
    SharedPreferences shp;
    TextView chonThangDien, searchElectricClick, searchElectricClose;
    List<TienDienModel> phongDien = new ArrayList<>();
    RecyclerView danhSachPhongDien;
    EditText keyElectricRoom;
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_dien);
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TienDien.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TienDien.this);
                builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Yes</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TienDien.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TienDien.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>No</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(TienDien.this, "Stay", Toast.LENGTH_SHORT).show();
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
        FrameLayout imageKhachTro = findViewById(R.id.imageDien);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.dientitle);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageKhachTro.addView(iv, params);

        danhSachPhongDien = findViewById(R.id.danhSachPhongDien);
        danhSachPhongDien.setLayoutManager(new LinearLayoutManager(TienDien.this));
        danhSachPhongDien.hasFixedSize();
        danhSachPhongDien.setNestedScrollingEnabled(false);

        chonThangDien = findViewById(R.id.chonThangDien);
        chonThangDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat dateFormat = new SimpleDateFormat("MM");
                Date date = new Date();
                RackMonthPicker rackMonthPicker = new RackMonthPicker(TienDien.this);
                rackMonthPicker.setLocale(Locale.ENGLISH)
                        .setNegativeText("Đóng")
                        .setPositiveText("Chọn")
                        .setMonthType(MonthType.NUMBER)
                        .setSelectedMonth(Integer.parseInt(dateFormat.format(date)))
                        .setColorTheme(R.color.tenPhongColor)
                        .setPositiveButton(new DateMonthDialogListener() {
                            @Override
                            public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {
                                chonThangDien.setText("Tháng "+month+" - năm "+year);
                                ApiQH.apiQH.chooseTimeElectric(month,year).enqueue(new Callback<List<TienDienModel>>() {
                                    @Override
                                    public void onResponse(Call<List<TienDienModel>> call, Response<List<TienDienModel>> response) {
                                        phongDien = response.body();
                                        danhSachPhongDien.setAdapter(new TienDienAdapter(TienDien.this, phongDien, new DienItemClick() {
                                            @Override
                                            public void itemOnClick(String idPhong) {
                                                ApiQH.apiQH.detailElectric(idPhong,month,year).enqueue(new Callback<TienDienModel>() {
                                                    @Override
                                                    public void onResponse(Call<TienDienModel> call, Response<TienDienModel> response) {
                                                        TienDienModel detailPhongDien = response.body();
                                                        Intent intent = new Intent(TienDien.this, TienDienEdit.class);
                                                        intent.putExtra("tenKhach",detailPhongDien.getTenkhach());
                                                        intent.putExtra("idKhach",detailPhongDien.getKhach());
                                                        intent.putExtra("phongDien",detailPhongDien.getPhong());
                                                        intent.putExtra("tongTien",detailPhongDien.getTien());
                                                        intent.putExtra("soDien",detailPhongDien.getSodien());
                                                        intent.putExtra("soDau",detailPhongDien.getSodau());
                                                        intent.putExtra("soCuoi",detailPhongDien.getSocuoi());
                                                        intent.putExtra("donGia",detailPhongDien.getDongia());
                                                        intent.putExtra("ngayChot",detailPhongDien.getNgaychot());
                                                        intent.putExtra("thang",month);
                                                        intent.putExtra("nam",year);
                                                        startActivity(intent);
                                                    }

                                                    @Override
                                                    public void onFailure(Call<TienDienModel> call, Throwable t) {
                                                        Log.d("err",""+t.toString());
                                                    }
                                                });
                                            }
                                        }));
                                    }

                                    @Override
                                    public void onFailure(Call<List<TienDienModel>> call, Throwable t) {
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

        ApiQH.apiQH.getTienDien().enqueue(new Callback<List<TienDienModel>>() {
            @Override
            public void onResponse(Call<List<TienDienModel>> call, Response<List<TienDienModel>> response) {
                phongDien = response.body();
                DateFormat monthFormat = new SimpleDateFormat("MM");
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int monthGet = Integer.parseInt(monthFormat.format(date));
                chonThangDien.setText("Tháng "+monthFormat.format(date)+" - năm "+year);
                danhSachPhongDien.setAdapter(new TienDienAdapter(TienDien.this, phongDien, new DienItemClick() {
                    @Override
                    public void itemOnClick(String idPhong) {

                        ApiQH.apiQH.detailElectric(idPhong,monthGet,year).enqueue(new Callback<TienDienModel>() {
                            @Override
                            public void onResponse(Call<TienDienModel> call, Response<TienDienModel> response) {
                                TienDienModel detailPhongDien = response.body();
                                Intent intent = new Intent(TienDien.this, TienDienEdit.class);
                                intent.putExtra("tenKhach",detailPhongDien.getTenkhach());
                                intent.putExtra("idKhach",detailPhongDien.getKhach());
                                intent.putExtra("phongDien",detailPhongDien.getPhong());
                                intent.putExtra("tongTien",detailPhongDien.getTien());
                                intent.putExtra("soDien",detailPhongDien.getSodien());
                                intent.putExtra("soDau",detailPhongDien.getSodau());
                                intent.putExtra("soCuoi",detailPhongDien.getSocuoi());
                                intent.putExtra("donGia",detailPhongDien.getDongia());
                                intent.putExtra("ngayChot",detailPhongDien.getNgaychot());
                                intent.putExtra("thang",monthGet);
                                intent.putExtra("nam",year);
                                startActivity(intent);

                            }

                            @Override
                            public void onFailure(Call<TienDienModel> call, Throwable t) {
                                Log.d("err",""+t.toString());
                            }
                        });
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<TienDienModel>> call, Throwable t) {

            }
        });

        // Tìm kiếm phòng trọ
        searchDien = findViewById(R.id.searchDien);
        searchDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialogSearch = new Dialog(TienDien.this);
                dialogSearch.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogSearch.setContentView(R.layout.layout_dialog_search_phong_dien);

                Window window = dialogSearch.getWindow();
                if (window == null) {
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAttr = window.getAttributes();
                windowAttr.gravity = Gravity.CENTER;
                window.setAttributes(windowAttr);

                searchElectricClick = dialogSearch.findViewById(R.id.searchElectricClick);
                searchElectricClose = dialogSearch.findViewById(R.id.searchElectricClose);

                searchElectricClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        keyElectricRoom = dialogSearch.findViewById(R.id.keyElectricRoom);
                        String keyTimKiem = keyElectricRoom.getText().toString();
                        String timeChoose = chonThangDien.getText().toString();
                        String[] elementTime = timeChoose.split(" ");
                        int thangSend =  Integer.parseInt(elementTime[1]);
                        int namSend = Integer.parseInt(elementTime[4]);
                        ApiQH.apiQH.searchElectric(keyTimKiem,thangSend,namSend).enqueue(new Callback<List<TienDienModel>>() {
                            @Override
                            public void onResponse(Call<List<TienDienModel>> call, Response<List<TienDienModel>> response) {
                                List<TienDienModel> phongCanTim = response.body();
                                danhSachPhongDien.setAdapter(new TienDienAdapter(TienDien.this, phongCanTim, new DienItemClick() {
                                    @Override
                                    public void itemOnClick(String idPhong) {
                                        ApiQH.apiQH.detailElectric(idPhong,thangSend,namSend).enqueue(new Callback<TienDienModel>() {
                                            @Override
                                            public void onResponse(Call<TienDienModel> call, Response<TienDienModel> response) {
                                                TienDienModel detailPhongDien = response.body();
                                                Intent intent = new Intent(TienDien.this, TienDienEdit.class);
                                                intent.putExtra("tenKhach",detailPhongDien.getTenkhach());
                                                intent.putExtra("idKhach",detailPhongDien.getKhach());
                                                intent.putExtra("phongDien",detailPhongDien.getPhong());
                                                intent.putExtra("tongTien",detailPhongDien.getTien());
                                                intent.putExtra("soDien",detailPhongDien.getSodien());
                                                intent.putExtra("soDau",detailPhongDien.getSodau());
                                                intent.putExtra("soCuoi",detailPhongDien.getSocuoi());
                                                intent.putExtra("donGia",detailPhongDien.getDongia());
                                                intent.putExtra("ngayChot",detailPhongDien.getNgaychot());
                                                intent.putExtra("thang",thangSend);
                                                intent.putExtra("nam",namSend);
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onFailure(Call<TienDienModel> call, Throwable t) {
                                                Log.d("err",""+t.toString());
                                            }
                                        });
                                    }
                                }));
                                dialogSearch.dismiss();
                            }

                            @Override
                            public void onFailure(Call<List<TienDienModel>> call, Throwable t) {
                                Log.d("err",""+t.toString());
                            }
                        });
                    }
                });

                searchElectricClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogSearch.dismiss();
                    }
                });

                dialogSearch.show();
            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_dien);

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
                        Intent khachTro = new Intent(TienDien.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(TienDien.this, TienCoc.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(TienDien.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(TienDien.this, HopDong.class);
                        startActivity(hopDong);
                        return true;


                }
                return true;
            }
        });
    }
}