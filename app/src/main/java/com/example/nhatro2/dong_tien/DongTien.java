package com.example.nhatro2.dong_tien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.hop_dong.BottomSheetThanhVienChon;
import com.example.nhatro2.hop_dong.HopDongAdd;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DongTien extends AppCompatActivity {
    ImageView logo, thoat, timInfoPhongChiTiet;
    SharedPreferences shp;
    TextView nameRoomSearch, tienPhongCanTra, tenNopPhong;
    SharedPreferences.Editor shpKhachEdit;
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

        ApiQH.apiQH.getTienDongList(maPhongChon).enqueue(new Callback<ChonPhongModel>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<ChonPhongModel> call, Response<ChonPhongModel> response) {
                ChonPhongModel thongTinDongTienPhong = response.body();
                if(thongTinDongTienPhong.getDutienphong() < 0){
                    tenNopPhong.setTextColor(R.color.tabThue);
                    tienPhongCanTra.setTextColor(R.color.tabThue);
                }else{
                    tenNopPhong.setTextColor(R.color.tenPhongColor);
                    tienPhongCanTra.setTextColor(R.color.tenPhongColor);
                }

                tenNopPhong.setText(thongTinDongTienPhong.getTennopphong()+": ");
                tienPhongCanTra.setText(thongTinDongTienPhong.getDutienphongformat()+" VNĐ");

                Log.d("infoRoom","thong tin dong tien phong"+thongTinDongTienPhong);
            }

            @Override
            public void onFailure(Call<ChonPhongModel> call, Throwable t) {
                Log.d("errRoom","Loi info phong"+t.toString());
            }
        });


    }
}