package com.example.nhatro2.dong_tien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.hop_dong.BottomSheetThanhVienChon;
import com.example.nhatro2.hop_dong.HopDongAdd;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DongTien extends AppCompatActivity {
    ImageView logo, thoat, timInfoPhongChiTiet;
    SharedPreferences shp;
    TextView nameRoomSearch, tienPhongCanTra, tenNopPhong, tienCocDaTra, tienCocCanTra, tenchuphong,dienthoaichuphong, soDienSuDungText, soNuocSuDungText, tienNuocPhaiThu, tienNuocDaThu, tienDienPhaiThu, tienDienDaThu, phaiTraTien ;
    RecyclerView listThietBiSuDung, listThanhVienPhong, lichSuThuTienPhong;
    SharedPreferences.Editor shpKhachEdit;
    LinearLayout thongTinChungDongTien, dongTienPhongText, khungLichSuDongTien;
    RadioGroup hinhThucDongTien;
    EditText tienThanhToanText;
    ImageView thanhToanTienButton;
    RadioButton tienMatDongTien,chuyenKhoanDongTien;
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
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DongTien.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DongTien.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DongTien.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(DongTien.this, "Stay", Toast.LENGTH_SHORT).show();
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
                        tenNopPhong.setTextColor(R.color.tabThue);
                        tienPhongCanTra.setTextColor(R.color.tabThue);
                    }else{
                        tenNopPhong.setTextColor(R.color.tenPhongColor);
                        tienPhongCanTra.setTextColor(R.color.tenPhongColor);
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
                                    }
                                });
                            }
                        }
                    });

                    tenNopPhong.setText(thongTinDongTienPhong.getTennopphong()+": ");
                    tienPhongCanTra.setText(thongTinDongTienPhong.getDutienphongformat()+"đ");

                    tienCocCanTra.setText(""+thongTinDongTienPhong.getTencoc()+":"+thongTinDongTienPhong.getTiencocthuthemformat());
                    tienCocDaTra.setText("Đã cọc: "+thongTinDongTienPhong.getDacoc()+"đ");

                    tenchuphong.setText(thongTinDongTienPhong.getTenchuphong());
                    dienthoaichuphong.setText(thongTinDongTienPhong.getDienthoaichuphong());

                    soDienSuDungText.setText("Số điện: "+thongTinDongTienPhong.getSodiensudung());
                    tienDienPhaiThu.setText("Phải thu: "+thongTinDongTienPhong.getTiendiensudung()+"đ");
                    tienDienDaThu.setText("Đã thu: "+thongTinDongTienPhong.getSodienphaithu()+"đ");

                    soNuocSuDungText.setText("Số nước: "+thongTinDongTienPhong.getSonuocsudung());
                    tienNuocPhaiThu.setText("Phải thu: "+thongTinDongTienPhong.getTiennuocsudung()+"đ");
                    tienNuocDaThu.setText("Đã thu: "+thongTinDongTienPhong.getSonuocphaithu()+"đ");

                    phaiTraTien.setText("Tiền phải thu: "+thongTinDongTienPhong.getTongthuformat()+"đ");

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
                    khungLichSuDongTien.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ChonPhongModel> call, Throwable t) {
                Log.d("errRoom","Loi info phong"+t.toString());
            }
        });

    }
}