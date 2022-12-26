package com.example.nhatro2.thanhvien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhachTroAdd extends AppCompatActivity {
    ImageView thoat,logo,addCustomer;
    SharedPreferences shp;
    TextView backKhachList,themKhach;
    EditText tenKhachAdd,sdtKhachAddText,soCanCuocAddText,diaChiKhachAddText,quocTichText,ngayCapAddText,ngaySinhAddText;
    RadioButton nam,nu,khac,nguoiLon,treEm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_tro_add);

        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KhachTroAdd.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(KhachTroAdd.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(KhachTroAdd.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(KhachTroAdd.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(KhachTroAdd.this, "Stay", Toast.LENGTH_SHORT).show();
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
        FrameLayout imageKhachTro = findViewById(R.id.imageCustomerAdd);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.khachtro);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageKhachTro.addView(iv, params);
        //back lại trang danh sách
        backKhachList = findViewById(R.id.backKhachList);
        backKhachList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KhachTroAdd.this,KhachTro.class);
                startActivity(intent);
            }
        });

        tenKhachAdd = findViewById(R.id.tenKhachAdd);
        sdtKhachAddText = findViewById(R.id.sdtKhachAddText);
        soCanCuocAddText = findViewById(R.id.soCanCuocAddText);
        diaChiKhachAddText =findViewById(R.id.diaChiKhachAddText);
        quocTichText = findViewById(R.id.quocTichText);
        ngayCapAddText = findViewById(R.id.ngayCapAddText);
        ngaySinhAddText = findViewById(R.id.ngaySinhAddText);

        themKhach = findViewById(R.id.themKhach);
        themKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenKhachPost = tenKhachAdd.getText().toString();
                String sdtKhachPost = sdtKhachAddText.getText().toString();
                Api.api.themKhach(tenKhachPost,sdtKhachPost).enqueue(new Callback<ThanhVienModel>() {
                    @Override
                    public void onResponse(Call<ThanhVienModel> call, Response<ThanhVienModel> response) {
                        ThanhVienModel khachThue = response.body();
                        Intent intent = new Intent(KhachTroAdd.this,KhachTro.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ThanhVienModel> call, Throwable t) {
                    }
                });
            }
        });
    }
}