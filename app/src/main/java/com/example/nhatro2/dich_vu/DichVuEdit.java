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
import com.example.nhatro2.api.Api;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.phong.Phong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.tien_coc.TienCoc;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DichVuEdit extends AppCompatActivity {
    TextView tacVuThietBi,tieuDeTenThietBi,tieuDeGiaThietBi,backDV, capNhat;
    ImageView thoat,them,logo,menuDanhMuc;
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
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DichVuEdit.this);
                builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Yes</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DichVuEdit.this,"Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DichVuEdit.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
                    }
                });
                // NO
                builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>No</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(DichVuEdit.this,"Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        // onClick Back
        backDV = findViewById(R.id.backDichVu);
        backDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DichVuEdit.this,DichVu.class);
                startActivity(intent);
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
                        Intent datCoc = new Intent(DichVuEdit.this, TienCoc.class);
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
