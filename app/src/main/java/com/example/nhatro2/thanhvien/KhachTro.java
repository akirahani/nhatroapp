package com.example.nhatro2.thanhvien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;
import com.example.nhatro2.dich_vu.DichVu;
import com.example.nhatro2.dich_vu.DichVuAdapter;
import com.example.nhatro2.dich_vu.DichVuAdd;
import com.example.nhatro2.dich_vu.DichVuModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhachTro extends AppCompatActivity {
    ImageView thoat,logo,addCustomer;
    SharedPreferences shp;
    RecyclerView listKhachThue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_tro);

        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KhachTro.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(KhachTro.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(KhachTro.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(KhachTro.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(KhachTro.this, "Stay", Toast.LENGTH_SHORT).show();
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
        FrameLayout imageKhachTro = findViewById(R.id.imageKhachTro);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.khachtro);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageKhachTro.addView(iv, params);

        //Danh sach khach thue phong
        listKhachThue = findViewById(R.id.listKhachTro);
        listKhachThue.setLayoutManager(new LinearLayoutManager(KhachTro.this));
        listKhachThue.hasFixedSize();
        listKhachThue.setNestedScrollingEnabled(false);
        Api.api.getKhachList().enqueue(new Callback<List<ThanhVienModel>>() {
            @Override
            public void onResponse(Call<List<ThanhVienModel>> call, Response<List<ThanhVienModel>> response) {
                List<ThanhVienModel> listKhachTro = response.body();
                listKhachThue.setAdapter(new KhachTroAdapter(KhachTro.this,listKhachTro));
            }

            @Override
            public void onFailure(Call<List<ThanhVienModel>> call, Throwable t) {

            }
        });

        // Them khach tro
        addCustomer = findViewById(R.id.addCustomer);
        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KhachTro.this,KhachTroAdd.class);
                startActivity(intent);
            }
        });

    }
}