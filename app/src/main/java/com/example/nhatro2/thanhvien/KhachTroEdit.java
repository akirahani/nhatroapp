package com.example.nhatro2.thanhvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
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
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.phong.Phong;
import com.example.nhatro2.tien_coc.TienCoc;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhachTroEdit extends AppCompatActivity {
    ImageView thoat, logo, addCustomer, menuDanhMuc;
    SharedPreferences shp;
    TextView backKhachEditList, capNhatKhach, ngaySinhEditText, ngayCapEditText;
    EditText tenKhachEditText, sdtKhachEditText, soCanCuocEditText, diaChiKhachEditText, quocTichEditText;
    RadioButton namEdit, nuEdit, khacEdit, nguoiLonEdit, treEmEdit;
    private int mYear, mMonth, mDay, mYearCap, mMonthCap, mDayCap, gioiTinhSent, doiTuong;
    DatePickerDialog.OnDateSetListener setListener, setNgayCap;
    DrawerLayout mDrawerLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_tro_edit);
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KhachTroEdit.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(KhachTroEdit.this);
                builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Yes</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(KhachTroEdit.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(KhachTroEdit.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>No</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(KhachTroEdit.this, "Stay", Toast.LENGTH_SHORT).show();
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
        backKhachEditList = findViewById(R.id.backKhachEditList);
        backKhachEditList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KhachTroEdit.this, KhachTro.class);
                startActivity(intent);
            }
        });

        //data (edit) send to update
        Bundle bundle = getIntent().getExtras();
        int idKhach = bundle.getInt("idKhach");
        String tenKhach = bundle.getString("tenKhach");
        String sdtKhach = bundle.getString("sdtKhach");
        String diaChi = bundle.getString("diachi");
        String canCuoc = bundle.getString("cancuoc");
        String ngayCap = bundle.getString("ngaycap");
        String ngaySinh = bundle.getString("ngaysinh");
        String quocTich = bundle.getString("quoctich");
        int gioiTinh = bundle.getInt("gioitinh");
        int nhomTuoi = bundle.getInt("nhomtuoi");

        // Ánh xạ text date
        ngaySinhEditText = findViewById(R.id.ngaySinhEditText);
        ngayCapEditText = findViewById(R.id.ngayCapEditText);
        // set data gửi lên

        // date input
        Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mYearCap = c.get(Calendar.YEAR);
        mMonthCap = c.get(Calendar.MONTH);
        mDayCap = c.get(Calendar.DAY_OF_MONTH);


        Locale locale = getResources().getConfiguration().locale;
        Locale.setDefault(Locale.forLanguageTag(locale.getCountry()));

        // Bắt sự kiện chọn ngày sinh
        ngaySinhEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(KhachTroEdit.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, mYear, mMonth, mDay);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        // Hiển thị ngày sinh lên textView
        if(ngaySinh == null){
            ngaySinhEditText.setText("dd-mm-yyyy");
        }else{
            ngaySinhEditText.setText(ngaySinh);
        }

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = day + "-" + month + "-" + year;
                ngaySinhEditText.setText(date);
            }
        };


        // Bắt sự kiện chọn ngày cấp
        ngayCapEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(KhachTroEdit.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setNgayCap, mYearCap, mMonthCap, mDayCap);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        // Hiển thị ngày cấp lên textView
        if(ngayCap == null){
            ngayCapEditText.setText("dd-mm-yyyy");
        }else{
            ngayCapEditText.setText(ngayCap);
        }
        setNgayCap = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yearCap, int monthCap, int dayCap) {
                monthCap += 1;
                String dateCap = dayCap + "-" + monthCap + "-" + yearCap;
                ngayCapEditText.setText(dateCap);
            }
        };


        if (bundle == null) {
            Toast.makeText(this, "Có lỗi !", Toast.LENGTH_SHORT).show();
        } else {
            tenKhachEditText = findViewById(R.id.tenKhachEditText);
            sdtKhachEditText = findViewById(R.id.sdtKhachEditText);
            soCanCuocEditText = findViewById(R.id.canCuocKhachEditText);
            diaChiKhachEditText = findViewById(R.id.diaChiKhachEditText);
            quocTichEditText = findViewById(R.id.quocTichEditText);
            // set Text cho edit text
            tenKhachEditText.setText(tenKhach);
            sdtKhachEditText.setText(sdtKhach);
            soCanCuocEditText.setText(canCuoc);
            diaChiKhachEditText.setText(diaChi);
            quocTichEditText.setText(quocTich);
            //set Text cho textview
            namEdit = findViewById(R.id.namEdit);
            nuEdit = findViewById(R.id.nuEdit);
            khacEdit = findViewById(R.id.khacEdit);

            switch (gioiTinh) {
                case 1:
                    namEdit.setChecked(true);
                    break;
                case 2:
                    nuEdit.setChecked(true);
                    break;
                case 3:
                    khacEdit.setChecked(true);
                    break;
            }


            nguoiLonEdit = findViewById(R.id.nguoiLonEdit);
            treEmEdit = findViewById(R.id.treEmEdit);

            switch (nhomTuoi) {
                case 1:
                    nguoiLonEdit.setChecked(true);
                    break;
                case 2:
                    treEmEdit.setChecked(true);
                    break;
            }
        }

        // Cap nhat khach
        capNhatKhach = findViewById(R.id.capNhatKhach);
        capNhatKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tenKhachPost = tenKhachEditText.getText().toString();
                String sdtKhachPost = sdtKhachEditText.getText().toString();
                String ngayCap = ngayCapEditText.getText().toString();
                String ngaySinh =  ngaySinhEditText.getText().toString();
                String canCuoc = soCanCuocEditText.getText().toString();
                String diaChi = diaChiKhachEditText.getText().toString();
                String quocTich = quocTichEditText.getText().toString();
                // xét checked
                namEdit = findViewById(R.id.namEdit);
                nuEdit = findViewById(R.id.nuEdit);
                khacEdit = findViewById(R.id.khacEdit);
                if(namEdit.isChecked()){
                    gioiTinhSent = 1;
                }else if(nuEdit.isChecked()){
                    gioiTinhSent = 2;
                }else if(khacEdit.isChecked()){
                    gioiTinhSent = 3;
                }

                // Chọn đối tượng
                // Ánh xạ
                nguoiLonEdit = findViewById(R.id.nguoiLonEdit);
                treEmEdit = findViewById(R.id.treEmEdit);
                //  xét checked
                if(nguoiLonEdit.isChecked()){
                    doiTuong = 1;
                }else if(treEmEdit.isChecked()){
                    doiTuong = 2;
                }
                int idKhachUpdate = idKhach;
                Log.d("idK",""+idKhachUpdate);
                Log.d("tenK",""+tenKhachPost);
                Log.d("sdtK",""+sdtKhachPost);
                Log.d("cancuocK",""+canCuoc);
                Log.d("diachiK",""+diaChi);
                Log.d("ngaycapK",""+ngayCap);
                Log.d("ngaysinhK",""+ngaySinh);
                Log.d("quoctichK",""+quocTich);
                Log.d("gioitinhK",""+gioiTinhSent);
                ApiQH.apiQH.updateKhach(idKhachUpdate,tenKhachPost, sdtKhachPost,canCuoc,diaChi,ngayCap,ngaySinh,quocTich,gioiTinhSent).enqueue(new Callback<ThanhVienModel>() {
                    @Override
                    public void onResponse(Call<ThanhVienModel> call, Response<ThanhVienModel> response) {
                        ThanhVienModel khachUpdate = response.body();
                        Intent intent = new Intent(KhachTroEdit.this,KhachTro.class);
                        startActivity(intent);
                        Toast.makeText(KhachTroEdit.this,"Cập nhật khách thành công", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ThanhVienModel> call, Throwable t) {
                        Toast.makeText(KhachTroEdit.this,"Lỗi cập nhật khách", Toast.LENGTH_SHORT).show();
                        Log.d("err","lỗi khách cập nhật"+t.toString());
                    }
                });


            }
        });

        menuDanhMuc = findViewById(R.id.menuDanhMuc);
        mDrawerLayout = findViewById(R.id.drawer_layout_phong_khach_edit);

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
                switch (item.getItemId()){
                    case R.id.khach_tro:
                        Intent khachTro = new Intent(KhachTroEdit.this, KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(KhachTroEdit.this, TienCoc.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(KhachTroEdit.this, DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(KhachTroEdit.this, HopDong.class);
                        startActivity(hopDong);
                        return true;


                }
                return true;
            }
        });
    }
}