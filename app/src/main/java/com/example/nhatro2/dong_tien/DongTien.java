package com.example.nhatro2.dong_tien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dich_vu.DichVu;
import com.example.nhatro2.dich_vu.DichVuEdit;
import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DongTien extends AppCompatActivity {
    ImageView logo,timInfoPhongChiTiet,menuDanhMuc;
    SharedPreferences shp;
    TextView nameRoomSearch, tienPhongCanTra, tenNopPhong, tienCocDaTra, tienCocCanTra, tenchuphong,dienthoaichuphong, soDienSuDungText, soNuocSuDungText, tienNuocPhaiThu, tienNuocDaThu, tienDienPhaiThu, tienDienDaThu, phaiTraTien ;
    RecyclerView listThietBiSuDung, listThanhVienPhong, lichSuThuTienPhong;
    SharedPreferences.Editor shpKhachEdit;
    LinearLayout thongTinChungDongTien, dongTienPhongText, khungLichSuDongTien, quayLai;
    RadioGroup hinhThucDongTien;
    EditText tienThanhToanText;
    ImageView thanhToanTienButton;
    RadioButton tienMatDongTien,chuyenKhoanDongTien;
    DrawerLayout mDrawerLayout;
    int phuongThucThanhToan;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_tien);

        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DongTien.this, HomeActivity.class);
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
                DongTien.super.onBackPressed();
            }
        });

        // Xét ví trí tương đối
        @SuppressLint("WrongViewCast")
        FrameLayout imageFrame = findViewById(R.id.imageDongTien);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.khoanthuchi);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        timInfoPhongChiTiet = findViewById(R.id.timInfoPhongChiTiet);
        timInfoPhongChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetChonPhongTien chonPhongTien= new BottomSheetChonPhongTien();
                chonPhongTien.show(getSupportFragmentManager(), "ChonPhongTien");
            }
        });

        SharedPreferences shpPhongChon = getApplicationContext().getSharedPreferences("phongChon", MODE_PRIVATE);
        String tenPhong = shpPhongChon.getString("idPhongChon","");
        int maPhongChon = shpPhongChon.getInt("maPhongChon",0);

        //Tên phòng tìm
        nameRoomSearch = findViewById(R.id.nameRoomSearch);
        nameRoomSearch.setText("Phòng "+tenPhong);

        // Ánh xạ tiền phòng
        tienPhongCanTra = findViewById(R.id.tienPhongCanTra);
        tenNopPhong = findViewById(R.id.tenNopPhong);

        tienCocCanTra =  findViewById(R.id.tienCocCanTra);
        tienCocDaTra = findViewById(R.id.tienCocDaTra);

        tenchuphong = findViewById(R.id.tenchuphong);
        dienthoaichuphong = findViewById(R.id.dienthoaichuphong);

        soDienSuDungText = findViewById(R.id.soDienSuDungText);
        soNuocSuDungText = findViewById(R.id.soNuocSuDungText);
        tienNuocPhaiThu  = findViewById(R.id.tienNuocPhaiThu);
        tienNuocDaThu = findViewById(R.id.tienNuocDaThu);
        tienDienPhaiThu  = findViewById(R.id.tienDienPhaiThu);
        tienDienDaThu  = findViewById(R.id.tienDienDaThu);

        listThietBiSuDung = findViewById(R.id.listThietBiSuDung);
        listThanhVienPhong = findViewById(R.id.listThanhVienPhong);

        thongTinChungDongTien = findViewById(R.id.thongTinChungDongTien);
        hinhThucDongTien = findViewById(R.id.hinhThucDongTien);
        dongTienPhongText = findViewById(R.id.dongTienPhongText);

        phaiTraTien = findViewById(R.id.soTienPhaiTra);
        lichSuThuTienPhong = findViewById(R.id.lichSuThuTienPhong);
        khungLichSuDongTien = findViewById(R.id.khungLichSuDongTien);

        thanhToanTienButton = findViewById(R.id.thanhToanTienButton);
        tienThanhToanText = findViewById(R.id.tienThanhToanText);

        ApiQH.apiQH.getTienDongList(maPhongChon).enqueue(new Callback<ChonPhongModel>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<ChonPhongModel> call, Response<ChonPhongModel> response) {
                ChonPhongModel thongTinDongTienPhong = response.body();
                int idChuPhong = thongTinDongTienPhong.getIdchuphong();
                if(idChuPhong != 0){
                    if(thongTinDongTienPhong.getDutienphong() < 0){
                        tenNopPhong.setTextColor(Color.RED);
                        tienPhongCanTra.setTextColor(Color.RED);
                    }else{
                        tenNopPhong.setTextColor(Color.rgb(0,128,0));
                        tienPhongCanTra.setTextColor(Color.rgb(0,128,0));
                    }

                    thanhToanTienButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String tienThanhToan =  tienThanhToanText.getText().toString();
                            shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                            int khuTroId = shp.getInt("idThanhVien",0);
                            tienMatDongTien = findViewById(R.id.tienMatDongTien);
                            chuyenKhoanDongTien = findViewById(R.id.chuyenKhoanDongTien);
                            if(tienMatDongTien.isChecked()){
                                phuongThucThanhToan = 1;
                            }else if(chuyenKhoanDongTien.isChecked()){
                                phuongThucThanhToan = 2;
                            }

                            if(tienThanhToan.equals("")){
                                Toast.makeText(getApplicationContext(),"Vui lòng nhập số tiền thanh toán !",Toast.LENGTH_SHORT).show();
                            }else{
                                ApiQH.apiQH.postMoney(khuTroId,idChuPhong,tenPhong,phuongThucThanhToan,tienThanhToan).enqueue(new Callback<ThanhToanModel>() {
                                    @Override
                                    public void onResponse(Call<ThanhToanModel> call, Response<ThanhToanModel> response) {
                                        ThanhToanModel tienSauDong = response.body();
                                        Intent intent = new Intent(DongTien.this,DongTien.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<ThanhToanModel> call, Throwable t) {
                                        Log.d("err","dong tien"+t.toString());
                                    }
                                });
                            }
                        }
                    });

                    tenNopPhong.setText("Tiền phòng tháng: ");
                    tienPhongCanTra.setText(thongTinDongTienPhong.getDutienphongformat()+"đ");

                    tienCocCanTra.setText(""+thongTinDongTienPhong.getTencoc()+":"+thongTinDongTienPhong.getTiencocthuthemformat());
                    tienCocDaTra.setText("Đã cọc: "+thongTinDongTienPhong.getDacoc()+"đ");

                    tenchuphong.setText(thongTinDongTienPhong.getTenchuphong());
                    dienthoaichuphong.setText(thongTinDongTienPhong.getDienthoaichuphong());

                    soDienSuDungText.setText("Số điện: "+thongTinDongTienPhong.getSodiensudung());
                    tienDienPhaiThu.setText("Thành tiền: "+thongTinDongTienPhong.getTiendiensudung()+"đ");

                    soNuocSuDungText.setText("Số nước: "+thongTinDongTienPhong.getSonuocsudung());
                    tienNuocPhaiThu.setText("Thành tiền: "+thongTinDongTienPhong.getTiennuocsudung()+"đ");

                    phaiTraTien.setText(thongTinDongTienPhong.getTennopphong()+": "+thongTinDongTienPhong.getTongthuformat()+"đ");

                    if(thongTinDongTienPhong.getTongthu() < 0){
                        phaiTraTien.setTextColor(Color.RED);
                    }
                    else{
                        phaiTraTien.setTextColor(Color.rgb(0,128,0));
                    }


                    listThanhVienPhong.setLayoutManager(new LinearLayoutManager(DongTien.this));
                    listThanhVienPhong.hasFixedSize();
                    listThanhVienPhong.setNestedScrollingEnabled(false);

                    listThietBiSuDung.setLayoutManager(new LinearLayoutManager(DongTien.this));
                    listThietBiSuDung.hasFixedSize();
                    listThietBiSuDung.setNestedScrollingEnabled(false);

                    lichSuThuTienPhong.setLayoutManager(new LinearLayoutManager(DongTien.this));
                    lichSuThuTienPhong.hasFixedSize();
                    lichSuThuTienPhong.setNestedScrollingEnabled(false);
                    // Khách hiện đang thuê phòng
                    ApiQH.apiQH.getKhachPhongTien(thongTinDongTienPhong.getDanhsachthanhvien()).enqueue(new Callback<List<ThanhVienModel>>() {
                        @Override
                        public void onResponse(Call<List<ThanhVienModel>> call, Response<List<ThanhVienModel>> response) {
                            List<ThanhVienModel> listKhach = response.body();
                            listThanhVienPhong.setAdapter(new KhachPhongTienAdapter (DongTien.this,listKhach) );
                        }

                        @Override
                        public void onFailure(Call<List<ThanhVienModel>> call, Throwable t) {

                        }
                    });
                    // Thiết bị sử dụng
                    ApiQH.apiQH.getThietBiPhongTien(thongTinDongTienPhong.getTenthietbisudung()).enqueue(new Callback<List<DichVuModel>>() {
                        @Override
                        public void onResponse(Call<List<DichVuModel>> call, Response<List<DichVuModel>> response) {
                            List<DichVuModel> listThietBi = response.body();
                            listThietBiSuDung.setAdapter(new ThietBiPhongTienAdapter(DongTien.this,listThietBi) );
                        }

                        @Override
                        public void onFailure(Call<List<DichVuModel>> call, Throwable t) {

                        }
                    });
                    // Lịch sử đóng tiền phòng
                    ApiQH.apiQH.getHistoryPay(thongTinDongTienPhong.getLichsunopphong()).enqueue(new Callback<List<LichSuDongTienModel>>() {
                        @Override
                        public void onResponse(Call<List<LichSuDongTienModel>> call, Response<List<LichSuDongTienModel>> response) {
                            List<LichSuDongTienModel> lichSuDongTien = response.body();
                            lichSuThuTienPhong.setAdapter(new LichSuNopTienAdapter(DongTien.this,lichSuDongTien) );
                        }

                        @Override
                        public void onFailure(Call<List<LichSuDongTienModel>> call, Throwable t) {

                        }
                    });


                }else{
                    thongTinChungDongTien.setVisibility(View.GONE);
                    dongTienPhongText.setVisibility(View.GONE);
                    hinhThucDongTien.setVisibility(View.GONE);
                    phaiTraTien.setText("Phòng trống");
                    phaiTraTien.setTextColor(Color.RED);
                    khungLichSuDongTien.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ChonPhongModel> call, Throwable t) {
                Log.d("errRoom","Loi info phong"+t.toString());
            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_dong_tien);

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
                        Intent khachTro = new Intent(DongTien.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(DongTien.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(DongTien.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(DongTien.this, HopDong.class);
                        startActivity(hopDong);
                        return true;


                }
                return true;
            }
        });
    }
}