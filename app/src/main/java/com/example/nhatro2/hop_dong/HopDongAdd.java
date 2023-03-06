package com.example.nhatro2.hop_dong;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
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
import com.example.nhatro2.dich_vu.DichVu;
import com.example.nhatro2.dich_vu.DichVuEdit;
import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.doi_thiet_bi.DoiThietBi;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.kha_bien.KhaBien;
import com.example.nhatro2.phong.Phong;
import com.example.nhatro2.phong.PhongModel;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.example.nhatro2.thay_cong_to.CongToDien;
import com.example.nhatro2.thay_cong_to.CongToNuoc;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HopDongAdd extends AppCompatActivity {

    ImageView thoat, logo, themNguoiThue ,menuDanhMuc;
    SharedPreferences shp;
    LinearLayout quayLai;
    SharedPreferences.Editor shpKhachEdit;
    RecyclerView listThietBi, listKhachAdd;
    ThietBiAddAdapter dichVuAdapter;
    List<DichVuModel> dichVu;

    EditText tienPhongHopDongAdd, tienCocHopDongAdd, tenDaiDienText, sdtDaiDienText, tenKhachText, sdtKhachText,tenKhachAdd, canCuocKhachAdd, noiCapKhachAdd, sdtKhachAdd;
    TextView textNameRoom, ngayKetThuc,themHopDong, ghiChu;
    private int mYear, mMonth, mDay, tienCocChecked, tienPhongChecked, idDaiDien, coOTaiPhongChecked;
    RadioButton tienMatPhong, chuyenKhoanPhong,tienMatCoc, chuyenKhoanCoc, coTaiPhong, khongCoTaiPhong;

    DatePickerDialog.OnDateSetListener setListener;
//    FloatingActionButton fab;
    List<ThanhVienModel> listKhachArr = new ArrayList<>();
    TextView ngayCapKhachAdd,ngaySinhHopDongAddText,thuTuKhachTro;
    KhachAddAdapter addAdapterKhach;
    DrawerLayout mDrawerLayout;

    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hop_dong_add);

        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HopDongAdd.this, HomeActivity.class);
                startActivity(intent);
                SharedPreferences shpKhach = view.getContext().getSharedPreferences("khachChonHopDongAdd", MODE_PRIVATE);
                shpKhachEdit = shpKhach.edit();
                shpKhachEdit.remove("idKhachChon");
                shpKhachEdit.apply();
            }
        });
        // Nút thoát
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HopDongAdd.super.onBackPressed();
                SharedPreferences shpKhach = getApplicationContext().getSharedPreferences("khachChonHopDongAdd", MODE_PRIVATE);
                SharedPreferences.Editor shpKhachEdit = shpKhach.edit();
                shpKhachEdit.remove("idKhachChon");
                shpKhachEdit.apply();
                finish();
            }
        });
        // Xét ví trí tương đối
        @SuppressLint("WrongViewCast")
        FrameLayout imageFrame = findViewById(R.id.imageServiceEdit);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.hop_dong);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        SharedPreferences shpInfoRoom = getApplicationContext().getSharedPreferences("thongTinPhongHD", Context.MODE_PRIVATE);
        int idPhong = shpInfoRoom.getInt("idPhong",0);
        String tenPhong = shpInfoRoom.getString("tenPhong","");
        int giaPhong = shpInfoRoom.getInt("gia",0);
        int daiDien = shpInfoRoom.getInt("daidien",0);
        String dienThoai = shpInfoRoom.getString("dienthoai","");

        listThietBi = findViewById(R.id.thietbiCheck);
        listThietBi.setLayoutManager(new GridLayoutManager(HopDongAdd.this, 3));
        listThietBi.hasFixedSize();
        listThietBi.setNestedScrollingEnabled(false);

        tenDaiDienText = findViewById(R.id.tenDaiDienText);
        sdtDaiDienText = findViewById(R.id.sdtDaiDienText);
        textNameRoom = findViewById(R.id.textNameRoom);


        // Lấy ra thông tin phòng thuê
        ApiQH.apiQH.hopDongPhong(idPhong).enqueue(new Callback<PhongModel>() {
            @Override
            public void onResponse(Call<PhongModel> call, Response<PhongModel> response) {
                PhongModel phongHopDong = response.body();
                tenDaiDienText.setText(phongHopDong.getTenkhach());
                sdtDaiDienText.setText(phongHopDong.getDienthoai());
                textNameRoom.setText("Phòng thuê " + phongHopDong.getTen());
                idDaiDien = phongHopDong.getChuphong();
            }

            @Override
            public void onFailure(Call<PhongModel> call, Throwable t) {

            }
        });

        // Thiết bị
        ApiQH.apiQH.getDichVuList().enqueue(new Callback<List<DichVuModel>>() {
            @Override
            public void onResponse(Call<List<DichVuModel>> call, Response<List<DichVuModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(HopDongAdd.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                dichVu = response.body();
                dichVuAdapter = new ThietBiAddAdapter(HopDongAdd.this, dichVu);
                listThietBi.setAdapter(dichVuAdapter);
            }

            @Override
            public void onFailure(Call<List<DichVuModel>> call, Throwable t) {

            }
        });
        // Chon ngay ket thuc
        // Ánh xạ text date
        ngayKetThuc = findViewById(R.id.ngayKetThuc);
        // set data gửi lên

        // date input
        Calendar time = Calendar.getInstance();

        mYear = time.get(Calendar.YEAR);
        mMonth = time.get(Calendar.MONTH);
        mDay = time.get(Calendar.DAY_OF_MONTH);

        Locale locale = getResources().getConfiguration().locale;
        Locale.setDefault(Locale.forLanguageTag(locale.getCountry()));

        // Bắt sự kiện chọn ngày sinh
        ngayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(HopDongAdd.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, mYear, mMonth, mDay);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        // xet text ket qua ngay chon
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = day + "-" + month + "-" + year;
                ngayKetThuc.setText(date);
            }
        };

        // Hiển thị list khách được chọn lên hợp đồng được thêm
        listKhachAdd = findViewById(R.id.listKhachAdd);
        listKhachAdd.setLayoutManager(new LinearLayoutManager(HopDongAdd.this));
        listKhachAdd.hasFixedSize();
        listKhachAdd.setNestedScrollingEnabled(false);

        // Mảng lưu id khách trọ
        SharedPreferences shpKhach = getApplicationContext().getSharedPreferences("khachChonHopDongAdd", MODE_PRIVATE);
        String listKhachChooseString = shpKhach.getString("idKhachChon", "");

        //
        ApiQH.apiQH.addKhachHopDong(listKhachChooseString).enqueue(new Callback<List<ListKhachChonModel>>() {
            @Override
            public void onResponse(Call<List<ListKhachChonModel>> call, Response<List<ListKhachChonModel>> response) {
                List<ListKhachChonModel> listKhachArr = response.body();
                addAdapterKhach = new KhachAddAdapter(HopDongAdd.this, listKhachArr);
                listKhachAdd.setAdapter(addAdapterKhach);
                addAdapterKhach.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ListKhachChonModel>> call, Throwable t) {
            }
        });

        // Thêm khách thuê trọ
        themNguoiThue = findViewById(R.id.themNguoiThue);
        themNguoiThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetThanhVienChon tonKho = new BottomSheetThanhVienChon();
                Bundle bundle = new Bundle();
                bundle.putInt("daiDienPHong",daiDien);
                tonKho.setArguments(bundle);
                tonKho.show(getSupportFragmentManager(), "ChonThanhVien");
            }
        });

        // Thêm hợp đồng
        themHopDong = findViewById(R.id.themHopDong);
        themHopDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Thiết bị thêm trong hợp đồng
                SharedPreferences shpThietBi = getSharedPreferences("idThietBiHopDong", Context.MODE_PRIVATE);
                SharedPreferences.Editor thietBiEdit = shpThietBi.edit();
                String listThietBiString = shpThietBi.getString("itemThietBi", "");
                List<String> thietBiPhong = new ArrayList<>();
                String[] thietBiArr = listThietBiString.split(",");
                for (int i =0; i<thietBiArr.length; i++){
                    thietBiPhong.add(thietBiArr[i]);
                }

                // Ngày kết thúc hợp đồng
                String ngayKetThucHopDong = ngayKetThuc.getText().toString();

                //  Xét xem người đại diện có ở trong phòng hay không
                // Ánh xạ
                coTaiPhong = findViewById(R.id.coTaiPhong);
                khongCoTaiPhong = findViewById(R.id.khongCoTaiPhong);
                List<String> thanhVienPhong = new ArrayList<>();
                String[] khachArr = listKhachChooseString.split(",");
                for (int i =0; i<khachArr.length; i++){
                    thanhVienPhong.add(khachArr[i]);
                }


                if(coTaiPhong.isChecked()){
                    coOTaiPhongChecked = 1;
                    if(thanhVienPhong.contains(idDaiDien)){
                        thanhVienPhong.remove(String.valueOf(idDaiDien));
                    }else{
                        thanhVienPhong.add(String.valueOf(idDaiDien));
                    }
                }else if(khongCoTaiPhong.isChecked()){
                    coOTaiPhongChecked = 0;
                    if(thanhVienPhong.contains(idDaiDien)){
                        thanhVienPhong.remove(idDaiDien);
                    }
                }

                String idThanhVienConvert = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    idThanhVienConvert = thanhVienPhong.stream().map(String::valueOf).collect(Collectors.joining(","));
                }
                shpKhachEdit = shpKhach.edit();
                shpKhachEdit.putString("idKhachChon",idThanhVienConvert);
                shpKhachEdit.commit();

                String thietBiSentFinal = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    thietBiSentFinal = thietBiPhong.stream().map(String::valueOf).collect(Collectors.joining(","));
                }


                // Khách ở được thêm trong hợp đồng
                String listKhachChooseString = shpKhach.getString("idKhachChon", "");
                Log.d("","String Khach them: "+listKhachChooseString);

                // Tiền phòng
                tienPhongHopDongAdd = findViewById(R.id.tienPhongHopDongAdd);
                String tienPhongDong = tienPhongHopDongAdd.getText().toString();
//                int tienPhongDongFinal = 0;
                String tienPhongDongFinal = "";
                if(tienPhongDong.equals("")){
//                    tienPhongDongFinal = 0;
                    tienPhongDongFinal = "";
                }else{
//                    tienPhongDongFinal = Integer.parseInt(tienPhongDong);
                    tienPhongDongFinal = tienPhongDong;
                }

                // Tiền cọc
                tienCocHopDongAdd = findViewById(R.id.tienCocHopDongAdd);
                String tienCocDong = tienCocHopDongAdd.getText().toString();
//                int tienCocDongFinal = 0;
                String tienCocDongFinal = "";
                if(tienCocDong.equals("")){
//                    tienCocDongFinal = 0;
                    tienCocDongFinal = "";

                }else{
//                    tienCocDongFinal = Integer.parseInt(tienCocDong);
                    tienCocDongFinal = tienCocDong;
                };

                // Phương thức tiền phòng hợp đồng
                tienMatPhong = findViewById(R.id.tienMatTienPhong);
                chuyenKhoanPhong = findViewById(R.id.chuyenKhoanTienPhong);

                // xét checked tiền phòng hợp đồng
                if(tienMatPhong.isChecked()){
                    tienPhongChecked = 1;
                }else if(chuyenKhoanPhong.isChecked()){
                    tienPhongChecked = 2;
                }

                // Phương thức tiền cọc hợp đồng
                tienMatCoc = findViewById(R.id.tienMatTienCoc);
                chuyenKhoanCoc = findViewById(R.id.chuyenKhoanTienCoc);

                // xét checked tiền cọc hợp đồng
                if(tienMatCoc.isChecked()){
                    tienCocChecked = 1;
                }else if(chuyenKhoanCoc.isChecked()){
                    tienCocChecked = 2;
                }

                ghiChu = findViewById(R.id.ghiChu);
                String ghiChuText = ghiChu.getText().toString();
                ApiQH.apiQH.addContract(thietBiSentFinal,idDaiDien,coOTaiPhongChecked,ngayKetThucHopDong,ghiChuText,tienCocDongFinal,tienCocChecked,tienPhongDongFinal,tienPhongChecked,listKhachChooseString,tenPhong).enqueue(new Callback<HopDongModel>() {
                    @Override
                    public void onResponse(Call<HopDongModel> call, Response<HopDongModel> response) {
                        HopDongModel detailHopDong = response.body();
                        Intent intent = new Intent(HopDongAdd.this, Phong.class);
                        startActivity(intent);
                        Toast.makeText(HopDongAdd.this, "Tạo hợp đồng thành công", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<HopDongModel> call, Throwable t) {
                        Toast.makeText(HopDongAdd.this, "Tạo hợp đồng không thành công!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_hop_dong_add);

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
                        Intent khachTro = new Intent(HopDongAdd.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(HopDongAdd.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(HopDongAdd.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(HopDongAdd.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                    case R.id.thay_cong_to_nuoc:
                        Intent thay_cong_to_nuoc = new Intent(HopDongAdd.this, CongToNuoc.class);
                        startActivity(thay_cong_to_nuoc);
                        return true;
                    case R.id.thay_cong_to_dien:
                        Intent thay_cong_to_dien = new Intent(HopDongAdd.this, CongToDien.class);
                        startActivity(thay_cong_to_dien);
                        return true;
                    case R.id.doi_thiet_bi:
                        Intent doi_thiet_bi = new Intent(HopDongAdd.this, DoiThietBi.class);
                        startActivity(doi_thiet_bi);
                        return true;

                }
                return true;
            }
        });

    }

    // Back button
    public void onBackPressed()
    {
        super.onBackPressed();
        SharedPreferences shpKhach = getApplicationContext().getSharedPreferences("khachChonHopDongAdd", MODE_PRIVATE);
        SharedPreferences.Editor shpKhachEdit = shpKhach.edit();
        shpKhachEdit.remove("idKhachChon");
        shpKhachEdit.apply();
        finish();
    }

}