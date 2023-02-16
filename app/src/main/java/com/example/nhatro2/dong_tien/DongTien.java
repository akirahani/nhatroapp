package com.example.nhatro2.dong_tien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
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
import com.example.nhatro2.thu_khac.ThuKhac;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.example.nhatro2.tien_coc.TienCocModel;
import com.example.nhatro2.tra_phong.TraPhong;
import com.example.nhatro2.uu_dai.UuDaiAdapter;
import com.example.nhatro2.uu_dai.UuDaiModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DongTien extends AppCompatActivity {
    ImageView logo, timInfoPhongChiTiet, menuDanhMuc, thanhToanCocButton;
    SharedPreferences shp;
    TextView nameRoomSearch, tienPhongCanTra, tienCocDaTra, tenchuphong,
            dienthoaichuphong, soDienSuDungText, soNuocSuDungText,
            tienNuocPhaiThu, tienDienPhaiThu, phaiTraTien, thanhTienThanhVien,
            thanhTienThietBi, dongCapNhatUuDai, capNhatUuDai, tenUuDaiChon;
    RecyclerView listThietBiSuDung, listThanhVienPhong, lichSuThuTienPhong, listChonUuDai;
    SharedPreferences.Editor shpKhachEdit;
    LinearLayout thongTinChungDongTien,
            dongTienPhongText, khungLichSuDongTien,
            quayLai, phanDongCocClick,traPhongThuong, traPhongNgay;
    RadioGroup hinhThucDongTien;
    EditText tienThanhToanText, tienCocThanhToanText;
    ImageView thanhToanTienButton;
    RadioButton tienMatDongTien, chuyenKhoanDongTien, chuyenKhoanDongTienCoc, tienMatDongTienCoc, huyApDungPhong, coApDungPhong;
    DrawerLayout mDrawerLayout;
    View lineCoc;
    int phuongThucThanhToan, phuongThucCoc;

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

        // Danh sách phòng cần chọn
        timInfoPhongChiTiet = findViewById(R.id.timInfoPhongChiTiet);
        timInfoPhongChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetChonPhongTien chonPhongTien = new BottomSheetChonPhongTien();
                chonPhongTien.show(getSupportFragmentManager(), "ChonPhongTien");
            }
        });

        SharedPreferences shpPhongChon = getApplicationContext().getSharedPreferences("phongChon", MODE_PRIVATE);
        String tenPhong = shpPhongChon.getString("idPhongChon", "");
        int maPhongChon = shpPhongChon.getInt("maPhongChon", 0);
        int chuPhongChon = shpPhongChon.getInt("chuPhongChon", 0);

        //Tên phòng tìm
        nameRoomSearch = findViewById(R.id.nameRoomSearch);
        nameRoomSearch.setText("Phòng " + tenPhong);

        // Ánh xạ tiền phòng
        tienPhongCanTra = findViewById(R.id.tienPhongCanTra);

        tienCocDaTra = findViewById(R.id.tienCocDaTra);

        tenchuphong = findViewById(R.id.tenchuphong);
        dienthoaichuphong = findViewById(R.id.dienthoaichuphong);

        soDienSuDungText = findViewById(R.id.soDienSuDungText);
        soNuocSuDungText = findViewById(R.id.soNuocSuDungText);
        tienNuocPhaiThu = findViewById(R.id.tienNuocPhaiThu);
        tienDienPhaiThu = findViewById(R.id.tienDienPhaiThu);

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
        phanDongCocClick = findViewById(R.id.phanDongCocClick);
        lineCoc = findViewById(R.id.lineCoc);
        thanhToanCocButton = findViewById(R.id.thanhToanCocButton);

        thanhTienThanhVien = findViewById(R.id.thanhTienThanhVien);
        thanhTienThietBi = findViewById(R.id.thanhTienThietBi);
        tienCocThanhToanText = findViewById(R.id.tienCocThanhToanText);
        chuyenKhoanDongTienCoc = findViewById(R.id.chuyenKhoanDongTienCoc);
        tienMatDongTienCoc = findViewById(R.id.tienMatDongTienCoc);

        listChonUuDai = findViewById(R.id.listChonUuDai);

        // Kiểm tra tiền phòng
        ApiQH.apiQH.getTienDongList(maPhongChon).enqueue(new Callback<ChonPhongModel>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<ChonPhongModel> call, Response<ChonPhongModel> response) {
                ChonPhongModel thongTinDongTienPhong = response.body();
                int idChuPhong = thongTinDongTienPhong.getIdchuphong();
                if (idChuPhong != 0) {

                    tienPhongCanTra.setTextColor(Color.rgb(0, 0, 0));

                    thanhToanTienButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String tienThanhToan = tienThanhToanText.getText().toString();
                            shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                            int khuTroId = shp.getInt("idThanhVien", 0);
                            tienMatDongTien = findViewById(R.id.tienMatDongTien);
                            chuyenKhoanDongTien = findViewById(R.id.chuyenKhoanDongTien);
                            if (tienMatDongTien.isChecked()) {
                                phuongThucThanhToan = 1;
                            } else if (chuyenKhoanDongTien.isChecked()) {
                                phuongThucThanhToan = 2;
                            }

                            if (tienThanhToan.equals("")) {
                                Toast.makeText(getApplicationContext(), "Vui lòng nhập số tiền thanh toán !", Toast.LENGTH_SHORT).show();
                            } else {
                                ApiQH.apiQH.postMoney(khuTroId, idChuPhong, tenPhong, phuongThucThanhToan, tienThanhToan).enqueue(new Callback<ThanhToanModel>() {
                                    @Override
                                    public void onResponse(Call<ThanhToanModel> call, Response<ThanhToanModel> response) {
                                        ThanhToanModel tienSauDong = response.body();
                                        Intent intent = new Intent(DongTien.this, DongTien.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(), "Đóng tiền phòng thành công", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<ThanhToanModel> call, Throwable t) {
                                    }
                                });
                            }
                        }
                    });

                    thanhToanCocButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String tienCocThanhToan = tienCocThanhToanText.getText().toString();
                            shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                            int khuTroId = shp.getInt("idThanhVien", 0);

                            if (tienMatDongTienCoc.isChecked()) {
                                phuongThucCoc = 1;
                            } else if (chuyenKhoanDongTienCoc.isChecked()) {
                                phuongThucCoc = 2;
                            }

                            if (tienCocThanhToan.equals("")) {
                                Toast.makeText(getApplicationContext(), "Vui lòng nhập số tiền thanh toán !", Toast.LENGTH_SHORT).show();
                            } else {
                                int tienCocFinal = Integer.parseInt(tienCocThanhToan);
                                if(thongTinDongTienPhong.getDacoc() + tienCocFinal <= 1500000){
                                    ApiQH.apiQH.postCoc(khuTroId, idChuPhong, tenPhong, phuongThucCoc, tienCocThanhToan).enqueue(new Callback<TienCocModel>() {
                                        @Override
                                        public void onResponse(Call<TienCocModel> call, Response<TienCocModel> response) {
                                            TienCocModel tienCocSauDong = response.body();
                                            Intent intent = new Intent(DongTien.this, DongTien.class);
                                            startActivity(intent);
                                            finish();
                                            Toast.makeText(getApplicationContext(), "Đóng cọc thành công", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(Call<TienCocModel> call, Throwable t) {
                                        }
                                    });
                                } else{
                                    Toast.makeText(getApplicationContext(), "Số tiền nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    });

                    tienPhongCanTra.setText("Cần thu:  " + thongTinDongTienPhong.getDutienphongformat() + "đ");

                    tienCocDaTra.setText("Đã cọc: " + thongTinDongTienPhong.getDacocformat() + "đ");
                    if(thongTinDongTienPhong.getDacoc() < 1500000){
                        phanDongCocClick.setVisibility(View.VISIBLE);
                        lineCoc.setVisibility(View.GONE);
                    }else{
                        phanDongCocClick.setVisibility(View.GONE);
                        lineCoc.setVisibility(View.VISIBLE);
                    }

                    tenchuphong.setText(thongTinDongTienPhong.getTenchuphong());
                    dienthoaichuphong.setText(thongTinDongTienPhong.getDienthoaichuphong());

                    soDienSuDungText.setText("Số điện: " + thongTinDongTienPhong.getSodiensudung());
                    tienDienPhaiThu.setText("Cần thu: " + thongTinDongTienPhong.getTiendiensudung() + "đ");

                    soNuocSuDungText.setText("Số nước: " + thongTinDongTienPhong.getSonuocsudung());
                    tienNuocPhaiThu.setText("Cần thu: " + thongTinDongTienPhong.getTiennuocsudung() + "đ");

                    phaiTraTien.setText(thongTinDongTienPhong.getTennopphong() + ": " + thongTinDongTienPhong.getTongthuformat() + "đ");

                    if (thongTinDongTienPhong.getTongthu() < 0) {
                        phaiTraTien.setTextColor(Color.RED);
                    } else {
                        phaiTraTien.setTextColor(Color.rgb(0, 128, 0));
                    }

                    // Tiền thành viên cần trả
                    thanhTienThanhVien.setText("Cần thu: "+thongTinDongTienPhong.getThuthemthanhvien()+"đ");
                    //
                    thanhTienThietBi.setText("Cần thu: "+thongTinDongTienPhong.getTienthietbithang()+"đ");

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
                            listThanhVienPhong.setAdapter(new KhachPhongTienAdapter(DongTien.this, listKhach));
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
                            listThietBiSuDung.setAdapter(new ThietBiPhongTienAdapter(DongTien.this, listThietBi));
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
                            lichSuThuTienPhong.setAdapter(new LichSuNopTienAdapter(DongTien.this, lichSuDongTien));
                        }

                        @Override
                        public void onFailure(Call<List<LichSuDongTienModel>> call, Throwable t) {

                        }
                    });


                } else {
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
                Log.d("errRoom", "Loi info phong" + t.toString());
            }
        });

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

        // set up list ưu đãi
        listChonUuDai.setLayoutManager(new GridLayoutManager(DongTien.this, 2));
        listChonUuDai.setNestedScrollingEnabled(false);
        listChonUuDai.hasFixedSize();

        // Kiểm tra ưu đãi
        ApiQH.apiQH.getUuDaiActive().enqueue(new Callback<List<UuDaiModel>>() {
            @Override
            public void onResponse(Call<List<UuDaiModel>> call, Response<List<UuDaiModel>> response) {
                List<UuDaiModel> listUuDaiActive = response.body();
                listChonUuDai.setAdapter(new UuDaiThuTienApdater(DongTien.this, listUuDaiActive, new ChonUuDaiClick() {
                    @Override
                    public void chonUuDaiXuLy(int idUuDai, String tenGiamGia) {
                        Dialog dialogUuDaiThuTien = new Dialog(DongTien.this);
                        dialogUuDaiThuTien.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialogUuDaiThuTien.setContentView(R.layout.layout_dialog_uu_dai_thu_tien);

                        Window window = dialogUuDaiThuTien.getWindow();
                        if (window == null) {
                            return;
                        }

                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        WindowManager.LayoutParams windowAttr = window.getAttributes();
                        windowAttr.gravity = Gravity.CENTER;
                        window.setAttributes(windowAttr);

                        tenUuDaiChon = dialogUuDaiThuTien.findViewById(R.id.tenUuDaiChon);
                        tenUuDaiChon.setText(tenGiamGia);

                        dongCapNhatUuDai = dialogUuDaiThuTien.findViewById(R.id.dongCapNhatUuDai);
                        capNhatUuDai =  dialogUuDaiThuTien.findViewById(R.id.capNhatUuDai);

                        coApDungPhong = dialogUuDaiThuTien.findViewById(R.id.coApDungPhong);
                        huyApDungPhong = dialogUuDaiThuTien.findViewById(R.id.huyApDungPhong);

                        capNhatUuDai.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int apDungUuDaiPhong = 0;
                                if(coApDungPhong.isChecked()){
                                    apDungUuDaiPhong = 1;
                                }else if(huyApDungPhong.isChecked()){
                                    apDungUuDaiPhong = 0;
                                }
                                shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                                int khuTroId = shp.getInt("idThanhVien", 0);

                                ApiQH.apiQH.updateUuDaiPhong(khuTroId,chuPhongChon,tenPhong,idUuDai,apDungUuDaiPhong).enqueue(new Callback<ApDungUuDaiModel>() {
                                    @Override
                                    public void onResponse(Call<ApDungUuDaiModel> call, Response<ApDungUuDaiModel> response) {
                                        ApDungUuDaiModel phongSauUuDai = response.body();
                                        Intent intent = new Intent(DongTien.this,DongTien.class);
                                        startActivity(intent);
                                        finish();
                                        String mess = phongSauUuDai.getMess();
                                        dialogUuDaiThuTien.dismiss();
                                        Toast.makeText(DongTien.this, mess , Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<ApDungUuDaiModel> call, Throwable t) {

                                    }
                                });
                            }
                        });

                        dongCapNhatUuDai.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogUuDaiThuTien.dismiss();
                            }
                        });


                        dialogUuDaiThuTien.show();
                    }
                }));
            }

            @Override
            public void onFailure(Call<List<UuDaiModel>> call, Throwable t) {

            }
        });

        // Trả phòng
        traPhongThuong = findViewById(R.id.traPhongThuong);
        traPhongNgay = findViewById(R.id.traPhongNgay);

        traPhongThuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DongTien.this, TraPhong.class);
                intent.putExtra("tenPhong",tenPhong);
                intent.putExtra("idPhong",maPhongChon);
                intent.putExtra("chuPhongChon",chuPhongChon);
                startActivity(intent);
            }
        });

        traPhongNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DongTien.this);
                builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);

//                view = LayoutInflater.from(DongTien.this).inflate(R.layout.layout_chon_ngay, null);
//                final AppCompatEditText input = (AppCompatEditText) view.findViewById(R.id.ngayChotTraPhong);
//                builder.setView(view);

                builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Có</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(DongTien.this, TraPhong.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>Không</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(DongTien.this,"Ở lại", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }
}