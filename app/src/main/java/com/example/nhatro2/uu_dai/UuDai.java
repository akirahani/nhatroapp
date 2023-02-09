package com.example.nhatro2.uu_dai;

import static com.example.nhatro2.R.id.listUuDai;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.kha_bien.KhaBien;
import com.example.nhatro2.kha_bien.KhaBienAdapter;
import com.example.nhatro2.kha_bien.KhaBienModel;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.tien_coc.TienCoc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UuDai extends AppCompatActivity {
    ImageView thoat, logo, menuDanhMuc, addUuDai;
    SharedPreferences shp;
    DrawerLayout mDrawerLayout;
    RecyclerView listUuDai;
    TextView uuDaiAddButton,uuDaiAddClose ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uu_dai);

        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UuDai.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UuDai.this);
                builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Yes</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(UuDai.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UuDai.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>No</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(UuDai.this, "Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // Xét ví trí tương đối
        @SuppressLint("WrongViewCast")
        FrameLayout imageFrame = findViewById(R.id.imageUuDai);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.coupons);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_uu_dai);

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
                        Intent khachTro = new Intent(UuDai.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(UuDai.this, TienCoc.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(UuDai.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(UuDai.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                }
                return true;
            }
        });

        listUuDai = findViewById(R.id.listUuDai);
        listUuDai.setLayoutManager(new LinearLayoutManager(UuDai.this));
        listUuDai.hasFixedSize();
        listUuDai.setNestedScrollingEnabled(false);

        ApiQH.apiQH.getUuDai().enqueue(new Callback<List<UuDaiModel>>() {
            @Override
            public void onResponse(Call<List<UuDaiModel>> call, Response<List<UuDaiModel>> response) {
                List<UuDaiModel> listUuDaiData = response.body();
                listUuDai.setAdapter(new UuDaiAdapter(UuDai.this,listUuDaiData));

            }

            @Override
            public void onFailure(Call<List<UuDaiModel>> call, Throwable t) {

            }
        });

        addUuDai = findViewById(R.id.addUuDai);
        addUuDai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialogUuDaiAdd = new Dialog(UuDai.this);
                dialogUuDaiAdd.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogUuDaiAdd.setContentView(R.layout.layout_dialog_uu_dai_them);

                Window window = dialogUuDaiAdd.getWindow();
                if (window == null) {
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAttr = window.getAttributes();
                windowAttr.gravity = Gravity.CENTER;
                window.setAttributes(windowAttr);

                uuDaiAddButton = dialogUuDaiAdd.findViewById(R.id.uuDaiAddButton);
                uuDaiAddClose = dialogUuDaiAdd.findViewById(R.id.uuDaiAddClose);

                uuDaiAddButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        tenUuDai = dialogKhaBien.findViewById(R.id.lyDoKhaBienText);
//                        apDungUuDai = dialogKhaBien.findViewById(R.id.tienKhaBienText);
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
    }
}