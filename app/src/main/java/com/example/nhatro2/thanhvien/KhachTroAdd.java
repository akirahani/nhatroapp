package com.example.nhatro2.thanhvien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
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

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhachTroAdd extends AppCompatActivity {
    ImageView thoat, logo, addCustomer;
    SharedPreferences shp;
    TextView backKhachList, themKhach, ngayCapAddText, ngaySinhAddText;
    EditText tenKhachAdd, sdtKhachAddText, soCanCuocAddText, diaChiKhachAddText, quocTichText;
    RadioButton nam, nu, khac, nguoiLon, treEm;
    DatePickerDialog.OnDateSetListener setListener, setNgayCap;
    private int mYear, mMonth, mDay, mYearCap, mMonthCap, mDayCap, gioiTinh, doiTuong;

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
                Intent intent = new Intent(KhachTroAdd.this, KhachTro.class);
                startActivity(intent);
            }
        });

        tenKhachAdd = findViewById(R.id.tenKhachAdd);
        sdtKhachAddText = findViewById(R.id.sdtKhachAddText);
        soCanCuocAddText = findViewById(R.id.soCanCuocAddText);
        diaChiKhachAddText = findViewById(R.id.diaChiKhachAddText);
        quocTichText = findViewById(R.id.quocTichText);
        ngayCapAddText = findViewById(R.id.ngayCapAddText);
        ngaySinhAddText = findViewById(R.id.ngaySinhAddText);

        // date input
        Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mYearCap = c.get(Calendar.YEAR);
        mMonthCap = c.get(Calendar.MONTH);
        mDayCap = c.get(Calendar.DAY_OF_MONTH);

        // Ánh xạ text date
        ngaySinhAddText = findViewById(R.id.ngaySinhAddText);
        ngayCapAddText = findViewById(R.id.ngayCapAddText);
        Locale locale = getResources().getConfiguration().locale;
        Locale.setDefault(Locale.forLanguageTag(locale.getCountry()));

        // Bắt sự kiện chọn ngày sinh
        ngaySinhAddText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(KhachTroAdd.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, mYear, mMonth, mDay);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        // Hiển thị ngày sinh lên textView
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = day + "-" + month + "-" + year;
                ngaySinhAddText.setText(date);
            }
        };

        // Bắt sự kiện chọn ngày cấp
        ngayCapAddText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(KhachTroAdd.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setNgayCap, mYearCap, mMonthCap, mDayCap);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        // Hiển thị ngày cấp lên textView
        setNgayCap = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yearCap, int monthCap, int dayCap) {
                monthCap += 1;
                String dateCap = dayCap + "-" + monthCap + "-" + yearCap;
                ngayCapAddText.setText(dateCap);
            }
        };



        themKhach = findViewById(R.id.themKhach);
        themKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenKhachPost = tenKhachAdd.getText().toString();
                String sdtKhachPost = sdtKhachAddText.getText().toString();
                // Chọn giới tính
                //Ánh xạ
                nam = findViewById(R.id.nam);
                nu = findViewById(R.id.nu);
                khac = findViewById(R.id.khac);
                // xét checked
                if(nam.isChecked()){
                    gioiTinh = 1;
                }else if(nu.isChecked()){
                    gioiTinh = 2;
                }else if(khac.isChecked()){
                    gioiTinh = 3;
                }


                // Chọn đối tượng
                // Ánh xạ
                nguoiLon = findViewById(R.id.nguoiLon);
                treEm = findViewById(R.id.treEm);
                //  xét checked
                if(nguoiLon.isChecked()){
                    doiTuong = 1;
                }else if(treEm.isChecked()){
                    doiTuong = 2;
                }
                String ngayCap = ngayCapAddText.getText().toString();
                String ngaySinh =  ngaySinhAddText.getText().toString();
                String canCuoc = soCanCuocAddText.getText().toString();
                String diaChi = diaChiKhachAddText.getText().toString();
                String quocTich = quocTichText.getText().toString();

                Api.api.themKhach(tenKhachPost, sdtKhachPost,canCuoc,diaChi,ngayCap,ngaySinh,quocTich,gioiTinh,doiTuong).enqueue(new Callback<ThanhVienModel>() {
                    @Override
                    public void onResponse(Call<ThanhVienModel> call, Response<ThanhVienModel> response) {
                        ThanhVienModel khachThue = response.body();
                        Intent intent = new Intent(KhachTroAdd.this, KhachTro.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ThanhVienModel> call, Throwable t) {
                        Log.d("err",""+t.toString());
                    }
                });

            }
        });

    }
}
