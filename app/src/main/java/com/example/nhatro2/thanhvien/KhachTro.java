package com.example.nhatro2.thanhvien;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dich_vu.DichVu;
import com.example.nhatro2.dich_vu.DichVuAdapter;
import com.example.nhatro2.dich_vu.DichVuAdd;
import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.phong.Phong;
import com.example.nhatro2.tien_coc.TienCoc;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhachTro extends AppCompatActivity {
    ImageView thoat, logo, addCustomer, searchCustomer, menuDanhMuc;
    SharedPreferences shp;
    RecyclerView listKhachThue;
    TextView searchClose, searchClick;
    EditText keySearch;
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_tro);

        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KhachTro.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(KhachTro.this);
                builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Yes</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(KhachTro.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(KhachTro.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>No</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(KhachTro.this, "Stay", Toast.LENGTH_SHORT).show();
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
        FrameLayout imageKhachTro = findViewById(R.id.imageKhachTro);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.khachtro);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageKhachTro.addView(iv, params);

        //Danh sach khach thue phong
        listKhachThue = findViewById(R.id.listKhachTro);
        listKhachThue.setLayoutManager(new LinearLayoutManager(KhachTro.this));
        listKhachThue.hasFixedSize();
        listKhachThue.setNestedScrollingEnabled(false);
        ApiQH.apiQH.getKhachList().enqueue(new Callback<List<ThanhVienModel>>() {
            @Override
            public void onResponse(Call<List<ThanhVienModel>> call, Response<List<ThanhVienModel>> response) {
                List<ThanhVienModel> listKhachTro = response.body();
                listKhachThue.setAdapter(new KhachTroAdapter(KhachTro.this, listKhachTro));
            }

            @Override
            public void onFailure(Call<List<ThanhVienModel>> call, Throwable t) {

            }
        });

        // Them khach tro
        addCustomer = findViewById(R.id.addCustomer);
        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KhachTro.this, KhachTroAdd.class);
                startActivity(intent);
            }
        });


        // Tìm kiếm khách
        searchCustomer = findViewById(R.id.searchCustomer);
        searchCustomer.setOnClickListener(new View.OnClickListener() {
            // Ánh xạ
            @Override
            public void onClick(View view) {
                Dialog dialogSearch = new Dialog(KhachTro.this);
                dialogSearch.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogSearch.setContentView(R.layout.layout_dialog_search_khach);

                Window window = dialogSearch.getWindow();
                if (window == null) {
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAttr = window.getAttributes();
                windowAttr.gravity = Gravity.CENTER;
                window.setAttributes(windowAttr);

                searchClick = dialogSearch.findViewById(R.id.searchClick);
                searchClose = dialogSearch.findViewById(R.id.searchClose);


                searchClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        keySearch = dialogSearch.findViewById(R.id.keySearch);
                        String keyTimKiem = keySearch.getText().toString();
                        ApiQH.apiQH.searchKhach(keyTimKiem).enqueue(new Callback<List<ThanhVienModel>>() {
                            @Override
                            public void onResponse(Call<List<ThanhVienModel>> call, Response<List<ThanhVienModel>> response) {
                                List<ThanhVienModel> khachCanTim = response.body();
                                listKhachThue.setAdapter(new KhachTroAdapter(KhachTro.this, khachCanTim));
                                dialogSearch.dismiss();
                            }

                            @Override
                            public void onFailure(Call<List<ThanhVienModel>> call, Throwable t) {
                            }
                        });
                    }
                });

                searchClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogSearch.dismiss();
                    }
                });

                dialogSearch.show();
            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_phong_khach_tro);

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
                        Intent khachTro = new Intent(KhachTro.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(KhachTro.this, TienCoc.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(KhachTro.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(KhachTro.this, HopDong.class);
                        startActivity(hopDong);
                        return true;


                }
                return true;
            }
        });
    }
}