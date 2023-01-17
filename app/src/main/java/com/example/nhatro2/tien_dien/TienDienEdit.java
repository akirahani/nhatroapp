package com.example.nhatro2.tien_dien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;
import com.example.nhatro2.api.ApiQH;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TienDienEdit extends AppCompatActivity {
    ImageView thoat, logo, closePhongDienEdit;
    SharedPreferences shp;
    TextView tenPhongDienEdit, daiDienPhongDien, infoTimePhongDien, tieuDeLichSuDungDien, btnUpdateElectric;
    EditText soDauDien, soCuoiDien, soDienSuDung, tienDienSuDung, ngayDo;
    RecyclerView listElectricNumberUsed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_dien_edit);
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TienDienEdit.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TienDienEdit.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TienDienEdit.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TienDienEdit.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(TienDienEdit.this, "Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FrameLayout imageKhachTro = findViewById(R.id.imageDien);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.dientitle);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageKhachTro.addView(iv, params);

        Bundle bundle = getIntent().getExtras();
        String tenKhach = bundle.getString("tenKhach");
        int idKhach = bundle.getInt("idKhach");
        String phong = bundle.getString("phongDien");
        int tienDien = bundle.getInt("tongTien");
        int soDien = bundle.getInt("soDien");
        int soDau = bundle.getInt("soDau");
        int soCuoi = bundle.getInt("soCuoi");
        int donGia = bundle.getInt("donGia");
        int thang = bundle.getInt("thang");
        int nam = bundle.getInt("nam");
        String ngayChot =  bundle.getString("ngayChot");

        infoTimePhongDien = findViewById(R.id.infoTimePhongDien);
        tenPhongDienEdit = findViewById(R.id.tenPhongDienEdit);
        daiDienPhongDien = findViewById(R.id.daiDienPhongDien);
        soDauDien = findViewById(R.id.soDauDien);
        soCuoiDien = findViewById(R.id.soCuoiDien);
        soDienSuDung = findViewById(R.id.soDienSuDung);
        tienDienSuDung = findViewById(R.id.tienDienSuDung);
        ngayDo = findViewById(R.id.ngayDoDien);
        tieuDeLichSuDungDien = findViewById(R.id.tieuDeLichSuDungDien);
        listElectricNumberUsed = findViewById(R.id.listElectricNumberUsed);

        tieuDeLichSuDungDien.setText("Lịch sử dùng điện phòng "+phong);
        tenPhongDienEdit.setText("Phòng "+phong);
        daiDienPhongDien.setText("Đại diện: "+tenKhach);
        soDauDien.setText(""+soDau);
        soCuoiDien.setText(""+soCuoi);
        soDienSuDung.setText(""+soDien);
        DecimalFormat formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            formatter = new DecimalFormat("#,###,###");
            String tienDienFormat = formatter.format(tienDien);
            tienDienSuDung.setText(""+tienDienFormat);
        }
        ngayDo.setText(ngayChot);
        infoTimePhongDien.setText("Thông tin chi tiết tiền điện tháng "+thang+"/"+nam);
        tieuDeLichSuDungDien.setText("Lịch sử dùng điện phòng "+phong);

        listElectricNumberUsed.setLayoutManager(new LinearLayoutManager(TienDienEdit.this));
        listElectricNumberUsed.hasFixedSize();
        listElectricNumberUsed.setNestedScrollingEnabled(false);

        ApiQH.apiQH.historyElectric(phong,thang,nam).enqueue(new Callback<List<LichSuDienModel>>() {
            @Override
            public void onResponse(Call<List<LichSuDienModel>> call, Response<List<LichSuDienModel>> response) {
                List<LichSuDienModel> phongDienLichSu = response.body();
                listElectricNumberUsed.setAdapter(new LichSuDienAdapter(TienDienEdit.this,phongDienLichSu));
            }

            @Override
            public void onFailure(Call<List<LichSuDienModel>> call, Throwable t) {
                Log.d("err",""+t.toString());
            }
        });

        btnUpdateElectric = findViewById(R.id.btnUpdateElectric);
        btnUpdateElectric.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText soDauDienEdit = findViewById(R.id.soDauDien);
                EditText soCuoiDienEdit = findViewById(R.id.soCuoiDien);

                String soDauEdit = soDauDienEdit.getText().toString();
                String soCuoiEdit = soCuoiDienEdit.getText().toString();
                int soDauFormat = Integer.parseInt(soDauEdit);
                int soCuoiFormat = Integer.parseInt(soCuoiEdit);

                ApiQH.apiQH.updateElectric(phong,thang,nam,soDauFormat,soCuoiFormat).enqueue(new Callback<TienDienModel>() {
                    @Override
                    public void onResponse(Call<TienDienModel> call, Response<TienDienModel> response) {
                        TienDienModel phongDien = response.body();
                        Intent intent = new Intent(TienDienEdit.this, TienDien.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<TienDienModel> call, Throwable t) {
                        Log.d("err uodate nuoc",""+t.toString());
                    }
                });
            }
        });

        closePhongDienEdit = findViewById(R.id.closePhongDienEdit);
        closePhongDienEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}