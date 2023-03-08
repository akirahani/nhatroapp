package com.example.nhatro2.doi_thiet_bi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.bat_bien.BatBien;
import com.example.nhatro2.bat_bien.BatBienAdapter;
import com.example.nhatro2.bat_bien.BatBienModel;
import com.example.nhatro2.chi_khac.ChiKhac;
import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.dong_tien.ChuyenPhongModel;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.dong_tien.ThietBiPhongTienAdapter;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.hop_dong.HopDongAdd;
import com.example.nhatro2.hop_dong.ThietBiAddAdapter;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thay_cong_to.CongToDien;
import com.example.nhatro2.thay_cong_to.CongToNuoc;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoiThietBi extends AppCompatActivity {
    ImageView thoat, logo, menuDanhMuc, searchPhongThietBi,timPhongDi;
    SharedPreferences shp;
    LinearLayout quayLai;
    SharedPreferences.Editor shpKhachEdit;
    DrawerLayout mDrawerLayout;
    TextView tenPhongThietBi, statusPhongThietBi, phongThietBiAdd, phongThietBiClose, tenPhongChuyen;
    RecyclerView listThietBiHienThi;
    AppCompatButton themThietBiPhong,chuyenThietBiPhongClick;
    RadioGroup chonThietBiCanThem;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_thiet_bi);
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoiThietBi.this, HomeActivity.class);
                startActivity(intent);
                SharedPreferences shpPhongThietBiChon = getSharedPreferences("phongThietBiChon", MODE_PRIVATE);
                shpPhongThietBiChon.edit().remove("maPhongChon");
                shpPhongThietBiChon.edit().commit();
                SharedPreferences thietBiChuyen = getSharedPreferences("thietBiChuyen", Context.MODE_PRIVATE);
                thietBiChuyen.edit().remove("idThietBi");
                thietBiChuyen.edit().commit();
            }
        });
        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoiThietBi.super.onBackPressed();
                SharedPreferences shpPhongThietBiChon = getSharedPreferences("phongThietBiChon", MODE_PRIVATE);
                shpPhongThietBiChon.edit().remove("maPhongChon");
                shpPhongThietBiChon.edit().commit();
                SharedPreferences thietBiChuyen = getSharedPreferences("thietBiChuyen", Context.MODE_PRIVATE);
                thietBiChuyen.edit().remove("idThietBi");
                thietBiChuyen.edit().commit();
            }
        });

        // Xét vị trí tương đối
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FrameLayout imageChiKhac = findViewById(R.id.imageQuanLiThietBi );
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.ic_quanlithietbi);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageChiKhac.addView(iv, params);

        // Tìm phòng
        searchPhongThietBi = findViewById(R.id.searchPhongThietBi);
        searchPhongThietBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetPhongThietBiChon bsPhongThietBi = new BottomSheetPhongThietBiChon();
                bsPhongThietBi.show(getSupportFragmentManager(),"phongThietBiList");
            }
        });

        //Tim phong den
        timPhongDi = findViewById(R.id.timPhongDi);
        timPhongDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetPhongChuyen bsPhongChuyenDi = new BottomSheetPhongChuyen();
                bsPhongChuyenDi.show(getSupportFragmentManager(),"phongChuyenDi");
            }
        });

        statusPhongThietBi = findViewById(R.id.statusPhongThietBi);

        tenPhongThietBi = findViewById(R.id.tenPhongThietBi);
        SharedPreferences shpPhongThietBiChon = getSharedPreferences("phongThietBiChon", MODE_PRIVATE);
        String tenPhong = shpPhongThietBiChon.getString("maPhongChon","");
        if(!tenPhong.equals("")){
            tenPhongThietBi.setText("Thiết bị phòng "+tenPhong+":");
        }else{
            tenPhongThietBi.setText("");
        }

        listThietBiHienThi = findViewById(R.id.listThietBiHienThi);
        listThietBiHienThi.setLayoutManager(new GridLayoutManager(DoiThietBi.this,2));
        listThietBiHienThi.hasFixedSize();
        listThietBiHienThi.setNestedScrollingEnabled(false);

        ApiQH.apiQH.getPhongThietBi(tenPhong).enqueue(new Callback<DoiThietBiModel>() {
            @Override
            public void onResponse(Call<DoiThietBiModel> call, Response<DoiThietBiModel> response) {
                DoiThietBiModel phongThietBi = response.body();
                // Thiết bị sử dụng
                if(phongThietBi.getThietbi().equals("")){
                    statusPhongThietBi.setVisibility(View.VISIBLE);
                }else{
                    statusPhongThietBi.setVisibility(View.GONE);
                }

                //Thêm thiết bị
                themThietBiPhong = findViewById(R.id.themThietBiPhong);
                themThietBiPhong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog dialogPhongThietBi = new Dialog(DoiThietBi.this);
                        dialogPhongThietBi.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogPhongThietBi.setContentView(R.layout.layout_dialog_them_thiet_bi_phong);

                        Window window = dialogPhongThietBi.getWindow();
                        if (window == null) {
                            return;
                        }

                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        WindowManager.LayoutParams windowAttr = window.getAttributes();
                        windowAttr.gravity = Gravity.CENTER;
                        window.setAttributes(windowAttr);

                        phongThietBiAdd = dialogPhongThietBi.findViewById(R.id.phongThietBiAdd);
                        phongThietBiClose = dialogPhongThietBi.findViewById(R.id.phongThietBiClose);
                        chonThietBiCanThem = dialogPhongThietBi.findViewById(R.id.chonThietBiCanThemRadioGroup);
                        // Hiển thị thiết bị không dùng
                        ApiQH.apiQH.getThietBiPhongTien(phongThietBi.getKhongdung()).enqueue(new Callback<List<DichVuModel>>() {
                            @SuppressLint("NewApi")
                            @Override
                            public void onResponse(Call<List<DichVuModel>> call, Response<List<DichVuModel>> response) {

                                List<DichVuModel> phongThietBiCheck = response.body();
                                List<Integer> idTBList = new ArrayList<>();  // here is list

                                phongThietBiCheck.forEach(item ->{
                                    idTBList.add(item.getId());
                                });

                                for(int itb=0;itb<idTBList.size();itb++){
                                    RadioButton rb = new RadioButton(DoiThietBi.this);
                                    if(idTBList.get(itb) == 1){
                                        rb.setText("Tủ lạnh");
                                    }else if(idTBList.get(itb) == 2){
                                        rb.setText("Máy giắt");
                                    }else if(idTBList.get(itb) == 3){
                                        rb.setText("Điều hòa");
                                    }else{
                                        rb.setText("Sạc xe điện");
                                    }
                                    rb.setId(idTBList.get(itb));
                                    chonThietBiCanThem.addView(rb);
                                }

                                chonThietBiCanThem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(RadioGroup radioGroup, int idcheck) {
                                        int childCount = radioGroup.getChildCount();
                                        for (int x = 0; x <childCount; x++) {
                                            RadioButton btn = (RadioButton) radioGroup.getChildAt(x);
                                            if (btn.getId() == idcheck) {
                                                phongThietBiAdd.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                                                        int idThanhVienQuanLy = shp.getInt("idThanhVien",0);
                                                        ApiQH.apiQH.themPhongThietBi(tenPhong,btn.getId()).enqueue(new Callback<DoiThietBiModel>() {
                                                            @Override
                                                            public void onResponse(Call<DoiThietBiModel> call, Response<DoiThietBiModel> response) {
                                                                Toast.makeText(DoiThietBi.this,"Thêm thiết bị thành công",Toast.LENGTH_SHORT).show();
                                                                Intent intent = new Intent(DoiThietBi.this,DoiThietBi.class);
                                                                startActivity(intent);
                                                            }

                                                            @Override
                                                            public void onFailure(Call<DoiThietBiModel> call, Throwable t) {
                                                                Toast.makeText(DoiThietBi.this,"Thêm thiết bị thất bại",Toast.LENGTH_SHORT).show();

                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        }

                                    }
                                });


                            }

                            @Override
                            public void onFailure(Call<List<DichVuModel>> call, Throwable t) {
                            }
                        });



                        phongThietBiClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogPhongThietBi.dismiss();
                            }
                        });

                        dialogPhongThietBi.show();
                    }
                });


                ApiQH.apiQH.getThietBiPhongTien(phongThietBi.getThietbi()).enqueue(new Callback<List<DichVuModel>>() {
                    @Override
                    public void onResponse(Call<List<DichVuModel>> call, Response<List<DichVuModel>> response) {
                        List<DichVuModel> listThietBi = response.body();
                        listThietBiHienThi.setAdapter(new PhongThietBiAdapter(DoiThietBi.this, listThietBi));
                    }

                    @Override
                    public void onFailure(Call<List<DichVuModel>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<DoiThietBiModel> call, Throwable t) {
                Log.d("err","phong thiet bi hien thi"+t.toString());
            }
        });

        SharedPreferences thietBiChuyen = getSharedPreferences("thietBiChuyen", Context.MODE_PRIVATE);
        int idThietBiChuyen = thietBiChuyen.getInt("idThietBi",0);
        tenPhongChuyen = findViewById(R.id.tenPhongChuyen);
        chuyenThietBiPhongClick = findViewById(R.id.chuyenThietBiPhongClick);
        if(idThietBiChuyen != 0){
            SharedPreferences shpPhongChuyenDi = getSharedPreferences("phongChuyenDi", MODE_PRIVATE);
            shpPhongChuyenDi.getInt("idPhongChon",0);
            String tenPhongDi = shpPhongChuyenDi.getString("maPhongChon","");
            tenPhongChuyen.setText("Chuyển đến phòng: "+tenPhongDi);
            chuyenThietBiPhongClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApiQH.apiQH.chuyenPhongThietBi(idThietBiChuyen,tenPhongDi,tenPhong).enqueue(new Callback<ChuyenPhongThietBiModel>() {
                        @Override
                        public void onResponse(Call<ChuyenPhongThietBiModel> call, Response<ChuyenPhongThietBiModel> response) {
                            ChuyenPhongThietBiModel chuyenPhongThietBiItem = response.body();
                            Toast.makeText(DoiThietBi.this,chuyenPhongThietBiItem.getMess(),Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DoiThietBi.this, DoiThietBi.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<ChuyenPhongThietBiModel> call, Throwable t) {
                            Log.d("err ","chuyển thiết bị phòng"+t.toString());
                        }
                    });
                }
            });

        }else{

        }

        // Menu slide
        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_quan_li_thiet_bi);

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
                switch (item.getItemId()) {
                    case R.id.khach_tro:
                        Intent khachTro = new Intent(DoiThietBi.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(DoiThietBi.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(DoiThietBi.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(DoiThietBi.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                    case R.id.thay_cong_to_nuoc:
                        Intent thay_cong_to_nuoc = new Intent(DoiThietBi.this, CongToNuoc.class);
                        startActivity(thay_cong_to_nuoc);
                        return true;
                    case R.id.thay_cong_to_dien:
                        Intent thay_cong_to_dien = new Intent(DoiThietBi.this, CongToDien.class);
                        startActivity(thay_cong_to_dien);
                        return true;
                    case R.id.doi_thiet_bi:
                        Intent doi_thiet_bi = new Intent(DoiThietBi.this, DoiThietBi.class);
                        startActivity(doi_thiet_bi);
                        return true;
                }
                return true;
            }
        });
    }
}