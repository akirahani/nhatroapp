package com.example.nhatro2.thu_khac;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
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
import com.example.nhatro2.dong_tien.DongTien;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThuKhacAdd extends AppCompatActivity {
    ImageView thoat, logo, menuDanhMuc, chonPhongThuKhac;
    SharedPreferences shp;
    DrawerLayout mDrawerLayout;
    TextView thuKhacAddButton, thuKhacAddClose;
    int idPhongThuKhac, checkTien;
    String phongThuKhac;
    EditText phongThuKhacDialog, lyDoThuKhacText, tienThuKhacText;
    RadioButton thuaTienThuKhac, thieuTienThuKhac;
    LinearLayout quayLai;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_khac_add);

        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThuKhacAdd.this, HomeActivity.class);
                startActivity(intent);
                SharedPreferences shpKhach = view.getContext().getSharedPreferences("khachChonHopDongAdd", MODE_PRIVATE);
                SharedPreferences.Editor shpKhachEdit = shpKhach.edit();
                shpKhachEdit.remove("idKhachChon");
                shpKhachEdit.apply();
            }
        });
        // quay loại
        quayLai = findViewById(R.id.quayLai);
        quayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThuKhacAdd.super.onBackPressed();
            }
        });

        // Xét ví trí tương đối
        @SuppressLint("WrongViewCast")
        FrameLayout imageFrame = findViewById(R.id.imageThuKhacAdd);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.thu_khac);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        chonPhongThuKhac = findViewById(R.id.chonPhongThuKhac);
        chonPhongThuKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetChonPhongThuKhac chonPhongThuKhacTien = new BottomSheetChonPhongThuKhac();
                chonPhongThuKhacTien.show(getSupportFragmentManager(), "ChonPhongThuKhac");
            }
        });

        thuKhacAddButton = findViewById(R.id.thuKhacAddButton);
        thuKhacAddClose = findViewById(R.id.thuKhacAddClose);

        SharedPreferences shpPhongThuKhacChon = getApplicationContext().getSharedPreferences("phongThuKhacChon", MODE_PRIVATE);
        phongThuKhac = shpPhongThuKhacChon.getString("idPhongThuKhacChon","");
        idPhongThuKhac = shpPhongThuKhacChon.getInt("maPhongThuKhacChon",0);
        phongThuKhacDialog = findViewById(R.id.phongThuKhacDialog);
        phongThuKhacDialog.setText(phongThuKhac);


        thuKhacAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lyDoThuKhacText = findViewById(R.id.lyDoThuKhacText);
                tienThuKhacText = findViewById(R.id.tienThuKhacText);
                String lyDoThuKhacTextFinal = lyDoThuKhacText.getText().toString();

                String tienThuKhacTextProcess = tienThuKhacText.getText().toString();

                int tienThuKhacTextFinal = Integer.parseInt(tienThuKhacTextProcess);

                thuaTienThuKhac = findViewById(R.id.thuaTienThuKhac);
                thieuTienThuKhac = findViewById(R.id.thieuTienThuKhac);

                if (thuaTienThuKhac.isChecked()){
                    checkTien = 1;
                }else if(thieuTienThuKhac.isChecked()){
                    checkTien = 0;
                }
                shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                int idThanhVienQuanLy = shp.getInt("idThanhVien",0);

                ApiQH.apiQH.addThuKhac(idThanhVienQuanLy,lyDoThuKhacTextFinal,tienThuKhacTextFinal,checkTien, idPhongThuKhac ).enqueue(new Callback<ThuKhacModel>() {
                    @Override
                    public void onResponse(Call<ThuKhacModel> call, Response<ThuKhacModel> response) {
                        ThuKhacModel thKhacItemAdd = response.body();
                        Intent intent = new Intent(ThuKhacAdd.this, ThuKhac.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ThuKhacModel> call, Throwable t) {
                        Log.d("loi me roi",""+t.toString());
                    }
                });
            }
        });

        thuKhacAddClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThuKhacAdd.this, ThuKhac.class);
                startActivity(intent);
                finish();
            }
        });

    }
}