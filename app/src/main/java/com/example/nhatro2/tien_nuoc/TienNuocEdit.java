package com.example.nhatro2.tien_nuoc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TienNuocEdit extends AppCompatActivity {
    ImageView thoat, logo;
    SharedPreferences shp;
    TextView tenPhongNuocEdit, daiDienPhongNuoc, infoTimePhongNuoc, tieuDeLichSuDungNuoc, btnUpdateWater;
    EditText soDauNuoc, soCuoiNuoc, soNuocSuDung, tienNuocSuDung, ngayDo;
    RecyclerView listWaterNumberUsed;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_nuoc_edit);

        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TienNuocEdit.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TienNuocEdit.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TienNuocEdit.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TienNuocEdit.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(TienNuocEdit.this, "Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // Xét vị trí tương đối
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FrameLayout imageKhachTro = findViewById(R.id.imageNuoc);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.nuoctitle);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageKhachTro.addView(iv, params);
        
        Bundle bundle = getIntent().getExtras();
        String tenKhach = bundle.getString("tenKhach");
        int idKhach = bundle.getInt("idKhach");
        String phong = bundle.getString("phongNuoc");
        int tienNuoc = bundle.getInt("tongTien");
        int soNuoc = bundle.getInt("soNuoc");
        int soDau = bundle.getInt("soDau");
        int soCuoi = bundle.getInt("soCuoi");
        int donGia = bundle.getInt("donGia");
        int thang = bundle.getInt("thang");
        int nam = bundle.getInt("nam");
        String ngayChot =  bundle.getString("ngayChot");

        infoTimePhongNuoc = findViewById(R.id.infoTimePhongNuoc);
        tenPhongNuocEdit = findViewById(R.id.tenPhongNuocEdit);
        daiDienPhongNuoc = findViewById(R.id.daiDienPhongNuoc);
        soDauNuoc = findViewById(R.id.soDauNuoc);
        soCuoiNuoc = findViewById(R.id.soCuoiNuoc);
        soNuocSuDung = findViewById(R.id.soNuocSuDung);
        tienNuocSuDung = findViewById(R.id.tienNuocSuDung);
        ngayDo = findViewById(R.id.ngayDo);
        tieuDeLichSuDungNuoc = findViewById(R.id.tieuDeLichSuDungNuoc);
        listWaterNumberUsed = findViewById(R.id.listWaterNumberUsed);

        tieuDeLichSuDungNuoc.setText("Lịch sử dùng nước phòng "+phong);
        tenPhongNuocEdit.setText("Phòng "+phong);
        daiDienPhongNuoc.setText("Đại diện: "+tenKhach);
        soDauNuoc.setText(""+soDau);
        soCuoiNuoc.setText(""+soCuoi);
        soNuocSuDung.setText(""+soNuoc);
        tienNuocSuDung.setText(""+tienNuoc);
        ngayDo.setText(ngayChot);
        infoTimePhongNuoc.setText("Thông tin chi tiết tiền nước tháng "+thang+"/"+nam);
        tieuDeLichSuDungNuoc.setText("Lịch sử dùng nước phòng "+phong);

        listWaterNumberUsed.setLayoutManager(new LinearLayoutManager(TienNuocEdit.this));
        listWaterNumberUsed.hasFixedSize();
        listWaterNumberUsed.setNestedScrollingEnabled(false);

        Api.api.historyWater(phong,thang,nam).enqueue(new Callback<List<LichSuNuocModel>>() {
            @Override
            public void onResponse(Call<List<LichSuNuocModel>> call, Response<List<LichSuNuocModel>> response) {

                List<LichSuNuocModel> phongNuocLichSu = response.body();
                listWaterNumberUsed.setAdapter(new LichSuAdapter(TienNuocEdit.this,phongNuocLichSu));
            }

            @Override
            public void onFailure(Call<List<LichSuNuocModel>> call, Throwable t) {
                Log.d("err",""+t.toString());
            }
        });





        btnUpdateWater = findViewById(R.id.btnUpdateWater);
        btnUpdateWater.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText soDauNuocEdit = findViewById(R.id.soDauNuoc);
                EditText soCuoiNuocEdit = findViewById(R.id.soCuoiNuoc);

                String soDauEdit = soDauNuocEdit.getText().toString();
                String soCuoiEdit = soCuoiNuocEdit.getText().toString();
                int soDauFormat = Integer.parseInt(soDauEdit);
                int soCuoiFormat = Integer.parseInt(soCuoiEdit);

                Api.api.updateWater(phong,thang,nam,soDauFormat,soCuoiFormat).enqueue(new Callback<TienNuocModel>() {
                    @Override
                    public void onResponse(Call<TienNuocModel> call, Response<TienNuocModel> response) {
                        TienNuocModel phongNuoc = response.body();
                        Intent intent = new Intent(TienNuocEdit.this,TienNuoc.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<TienNuocModel> call, Throwable t) {
                        Log.d("err update nuoc",""+t.toString());
                    }
                });
            }
        });
    }
}