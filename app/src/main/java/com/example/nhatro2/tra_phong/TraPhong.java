package com.example.nhatro2.tra_phong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.dong_tien.LichSuDongTienModel;
import com.example.nhatro2.dong_tien.LichSuNopTienAdapter;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thu_khac.ThuKhacModel;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraPhong extends AppCompatActivity {
    ImageView logo, menuDanhMuc;
    SharedPreferences.Editor shpKhachEdit;
    LinearLayout quayLai;
    DrawerLayout mDrawerLayout;
    TextView textTitleTraPhong, tieuDeKhachTraPhong,tienThanhVienCacThang,tienPhongCacThang,
            tenChuPhongTra,phoneChuPhongTra,tienDienTongCacThang,tienNuocTongCacThang,tienTongDaDongCacThang,
            addressChuPhongTra, ngayBatDauHopDong,tieuDeThanhToanTienPhong, ngayTraPhongHopDong,
            soTienKhachNhanLai, tienThietBiCacThang, chiTraPhongButton;
    EditText tienThanhToanCocText, tienThanhToanText;
    RecyclerView lichSuDongTienThang, lichSuTienNuocThang, lichSuTienDienThang, lichSuTienThietBiThang, lichSuTienThanhVienThang
            , lichSuTienPhongThang;
    RadioButton tienMatKhoanChiCoc, chuyenKhoanChiCoc, tienMatDongTien, chuyenKhoanDongTien ;
    int tienCocChecked, tienPhongChecked;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_phong);

        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TraPhong.this, HomeActivity.class);
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
                TraPhong.super.onBackPressed();
            }
        });

        // Xét ví trí tương đối
        @SuppressLint({"WrongViewCast", "MissingInflatedId", "LocalSuppress"})
        FrameLayout imageFrame = findViewById(R.id.imageTraPhong);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.traphong);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        // Menu slide
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
                switch (item.getItemId()) {
                    case R.id.khach_tro:
                        Intent khachTro = new Intent(TraPhong.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(TraPhong.this, TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(TraPhong.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(TraPhong.this, HopDong.class);
                        startActivity(hopDong);
                        return true;
                }
                return true;
            }
        });

        Bundle bundle = getIntent().getExtras();
        int trangThai = bundle.getInt("trangThai",0);
        String tenPhong = bundle.getString("tenPhong", "");
        int idPhong = bundle.getInt("idPhong", 0);
        int chuPhong = bundle.getInt("chuPhongChon", 0);
        int hopDong = bundle.getInt("hopdong", 0);
        String time = bundle.getString("time", "");
        tieuDeKhachTraPhong = findViewById(R.id.tieuDeKhachTraPhong);
        textTitleTraPhong = findViewById(R.id.textTitleTraPhong);
        tieuDeKhachTraPhong.setText("Đóng hợp đồng số "+hopDong);
        textTitleTraPhong.setText("Trả phòng "+tenPhong);
        tieuDeThanhToanTienPhong = findViewById(R.id.tieuDeThanhToanTienPhong);
        chiTraPhongButton = findViewById(R.id.chiTraPhongButton);

        ApiQH.apiQH.getThongTinTraPhong(time, hopDong).enqueue(new Callback<TraPhongModel>() {
            @Override
            public void onResponse(Call<TraPhongModel> call, Response<TraPhongModel> response) {
                TraPhongModel thongTinTraPhong = response.body();
                tenChuPhongTra = findViewById(R.id.tenChuPhongTra);
                phoneChuPhongTra = findViewById(R.id.phoneChuPhongTra);
                addressChuPhongTra = findViewById(R.id.addressChuPhongTra);
                ngayBatDauHopDong = findViewById(R.id.ngayBatDauHopDong);

                // get thong tin ca nhan
                tenChuPhongTra.setText("Chủ phòng: " + thongTinTraPhong.getTenchuphong());
                phoneChuPhongTra.setText("Điện thoại: " + thongTinTraPhong.getDienthoai());
                addressChuPhongTra.setText("Địa chỉ: " + thongTinTraPhong.getDiachi());
                ngayBatDauHopDong.setText("Ngày vào thuê: " + thongTinTraPhong.getNgaybatdau());

                tienThanhToanCocText = findViewById(R.id.tienThanhToanCocText);
                tienThanhToanText = findViewById(R.id.tienThanhToanText);
                // get tien phai chi, thu
                int tienCocPhaiTra = thongTinTraPhong.getTiencoc();
                tienThanhToanCocText.setText("" + tienCocPhaiTra);

                int soTienDuThieu = Math.abs(thongTinTraPhong.getSoducuoi());
                tienThanhToanText.setText("" + soTienDuThieu);
                if (thongTinTraPhong.getSoducuoi() < 0) {
                    tieuDeThanhToanTienPhong.setText("Tạo phiếu thu tiền phòng");
                } else {
                    tieuDeThanhToanTienPhong.setText("Tạo phiếu chi tiền phòng");
                }

                ngayTraPhongHopDong = findViewById(R.id.ngayTraPhongHopDong);
                ngayTraPhongHopDong.setText("Ngày trả phòng: " + time);

                soTienKhachNhanLai = findViewById(R.id.soTienKhachNhanLai);
                soTienKhachNhanLai.setText("Số tiền khách nhận lại: " + thongTinTraPhong.getTongkhachnhanformat() + "đ");

                // Tiền điện phải đóng
                lichSuTienDienThang = findViewById(R.id.lichSuTienDienThang);
                tienDienTongCacThang = findViewById(R.id.tienDienTongCacThang);
                tienDienTongCacThang.setText("Tổng tiền: " + thongTinTraPhong.getTongtiendienformat() + "đ");
                lichSuTienDienThang.setLayoutManager(new LinearLayoutManager(TraPhong.this));
                lichSuTienDienThang.hasFixedSize();
                lichSuTienDienThang.setNestedScrollingEnabled(false);
                ApiQH.apiQH.getLichSuTienDienCanDong(thongTinTraPhong.getListidtiendien()).enqueue(new Callback<List<DienTraPhongModel>>() {
                    @Override
                    public void onResponse(Call<List<DienTraPhongModel>> call, Response<List<DienTraPhongModel>> response) {
                        List<DienTraPhongModel> lichSuTienDienPhaiThu = response.body();
                        lichSuTienDienThang.setAdapter(new DienTraPhongAdapter(TraPhong.this, lichSuTienDienPhaiThu));
                    }

                    @Override
                    public void onFailure(Call<List<DienTraPhongModel>> call, Throwable t) {

                    }
                });


                // Tiền nước phải đóng
                tienNuocTongCacThang = findViewById(R.id.tienNuocTongCacThang);
                tienNuocTongCacThang.setText("Tổng tiền: " + thongTinTraPhong.getTongtiennuocformat() + "đ");
                lichSuTienNuocThang = findViewById(R.id.lichSuTienNuocThang);
                lichSuTienNuocThang.setLayoutManager(new LinearLayoutManager(TraPhong.this));
                lichSuTienNuocThang.hasFixedSize();
                lichSuTienNuocThang.setNestedScrollingEnabled(false);
                ApiQH.apiQH.getLichSuTienNuocCanDong(thongTinTraPhong.getListidtiennuoc()).enqueue(new Callback<List<NuocTraPhongModel>>() {
                    @Override
                    public void onResponse(Call<List<NuocTraPhongModel>> call, Response<List<NuocTraPhongModel>> response) {
                        List<NuocTraPhongModel> lichSuTienNuocPhaiThu = response.body();
                        lichSuTienNuocThang.setAdapter(new NuocTraPhongAdapter(TraPhong.this, lichSuTienNuocPhaiThu));
                    }

                    @Override
                    public void onFailure(Call<List<NuocTraPhongModel>> call, Throwable t) {

                    }
                });

                //Tiền phòng phải đóng
                tienPhongCacThang = findViewById(R.id.tienPhongCacThang);
                tienPhongCacThang.setText("Tổng tiền: " + thongTinTraPhong.getTienphongcanthuformat() + "đ");
                lichSuTienPhongThang = findViewById(R.id.lichSuTienPhongThang);
                lichSuTienPhongThang.setLayoutManager(new LinearLayoutManager(TraPhong.this));
                lichSuTienPhongThang.hasFixedSize();
                lichSuTienPhongThang.setNestedScrollingEnabled(false);
                ApiQH.apiQH.getTienPhongCanDong(idPhong, time, thongTinTraPhong.getListidtienphongthang()).enqueue(new Callback<List<TienPhongTraModel>>() {
                    @Override
                    public void onResponse(Call<List<TienPhongTraModel>> call, Response<List<TienPhongTraModel>> response) {
                        List<TienPhongTraModel> lichSuTienPhong = response.body();
                        lichSuTienPhongThang.setAdapter(new TienPhongTraPhongAdapter(TraPhong.this, lichSuTienPhong));
                    }

                    @Override
                    public void onFailure(Call<List<TienPhongTraModel>> call, Throwable t) {
                        Log.d("err tien phong dong", "" + t.toString());
                    }
                });

                //Tiền thiết bị phải đóng
                tienThietBiCacThang = findViewById(R.id.tienThietBiCacThang);
                tienThietBiCacThang.setText("Tổng tiền: " + thongTinTraPhong.getTongtienthietbiformat() + "đ");
                lichSuTienThietBiThang = findViewById(R.id.lichSuTienThietBiThang);
                lichSuTienThietBiThang.setLayoutManager(new LinearLayoutManager(TraPhong.this));
                lichSuTienThietBiThang.hasFixedSize();
                lichSuTienThietBiThang.setNestedScrollingEnabled(false);
                ApiQH.apiQH.getThietBiCanDong(time, thongTinTraPhong.getListidtienthietbi()).enqueue(new Callback<List<ThietBiTraPhongModel>>() {
                    @Override
                    public void onResponse(Call<List<ThietBiTraPhongModel>> call, Response<List<ThietBiTraPhongModel>> response) {
                        List<ThietBiTraPhongModel> lichSuTienThietBi = response.body();
                        lichSuTienThietBiThang.setAdapter(new ThietBiTraPhongAdapter(TraPhong.this, lichSuTienThietBi));
                    }

                    @Override
                    public void onFailure(Call<List<ThietBiTraPhongModel>> call, Throwable t) {

                    }
                });

                //Tiền thành viên phải đóng
                tienThanhVienCacThang = findViewById(R.id.tienThanhVienCacThang);
                tienThanhVienCacThang.setText("Tổng tiền: " + thongTinTraPhong.getTongtienthanhvienformat() + "đ");
                lichSuTienThanhVienThang = findViewById(R.id.lichSuTienThanhVienThang);
                lichSuTienThanhVienThang.setLayoutManager(new LinearLayoutManager(TraPhong.this));
                lichSuTienThanhVienThang.hasFixedSize();
                lichSuTienThanhVienThang.setNestedScrollingEnabled(false);
                ApiQH.apiQH.getTienThanhVienCanDong(time, thongTinTraPhong.getListidtienthanhvien()).enqueue(new Callback<List<ThanhVienTraPhongModel>>() {
                    @Override
                    public void onResponse(Call<List<ThanhVienTraPhongModel>> call, Response<List<ThanhVienTraPhongModel>> response) {
                        List<ThanhVienTraPhongModel> lichSuTienThanhVien = response.body();
                        lichSuTienThanhVienThang.setAdapter(new ThanhVienTraPhongAdapter(TraPhong.this, lichSuTienThanhVien));
                    }

                    @Override
                    public void onFailure(Call<List<ThanhVienTraPhongModel>> call, Throwable t) {

                    }
                });

                //Lịch sử đã đóng tiền
                tienTongDaDongCacThang = findViewById(R.id.tienTongDaDongCacThang);
                tienTongDaDongCacThang.setText("Tổng tiền: " + thongTinTraPhong.getTongtienthanhtoan());
                lichSuDongTienThang = findViewById(R.id.lichSuDongTienThang);
                lichSuDongTienThang.setLayoutManager(new LinearLayoutManager(TraPhong.this));
                lichSuDongTienThang.hasFixedSize();
                lichSuDongTienThang.setNestedScrollingEnabled(false);
                ApiQH.apiQH.getHistoryPay(thongTinTraPhong.getListidtratien()).enqueue(new Callback<List<LichSuDongTienModel>>() {
                    @Override
                    public void onResponse(Call<List<LichSuDongTienModel>> call, Response<List<LichSuDongTienModel>> response) {
                        List<LichSuDongTienModel> lichSuDongTien = response.body();
                        lichSuDongTienThang.setAdapter(new LichSuNopTienAdapter(TraPhong.this, lichSuDongTien));
                    }

                    @Override
                    public void onFailure(Call<List<LichSuDongTienModel>> call, Throwable t) {

                    }
                });

                tienMatDongTien = findViewById(R.id.tienMatDongTien);
                chuyenKhoanDongTien = findViewById(R.id.chuyenKhoanDongTien);
                if (tienMatDongTien.isChecked()) {
                    tienPhongChecked = 1;
                } else if (chuyenKhoanDongTien.isChecked()) {
                    tienPhongChecked = 2;
                }

                tienMatKhoanChiCoc = findViewById(R.id.tienMatKhoanChiCoc);
                chuyenKhoanChiCoc = findViewById(R.id.chuyenKhoanChiCoc);
                if (tienMatKhoanChiCoc.isChecked()) {
                    tienCocChecked = 1;
                } else if (chuyenKhoanChiCoc.isChecked()) {
                    tienCocChecked = 2;
                }

                String status = thongTinTraPhong.getStatus();
                chiTraPhongButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ApiQH.apiQH.taoPhieuChiTraPhong(chuPhong, tenPhong, hopDong, time, status, soTienDuThieu, tienCocPhaiTra, tienPhongChecked, tienCocChecked).enqueue(new Callback<ChiTienTraPhongModel>() {
                            @Override
                            public void onResponse(Call<ChiTienTraPhongModel> call, Response<ChiTienTraPhongModel> response) {
                                ChiTienTraPhongModel chiTien = response.body();
                                Toast.makeText(TraPhong.this, chiTien.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(TraPhong.this, DongTien.class);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onFailure(Call<ChiTienTraPhongModel> call, Throwable t) {
                                Log.d("err lỗi tao phiếu chi", "" + t.toString());
                            }
                        });
                    }
                });


            }

            @Override
            public void onFailure(Call<TraPhongModel> call, Throwable t) {
                Log.d("err j the", "" + t.toString());
            }
        });
        // Ánh xạ
    }
}