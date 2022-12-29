package com.example.nhatro2.hop_dong;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;
import com.example.nhatro2.dich_vu.DichVu;
import com.example.nhatro2.dich_vu.DichVuAdapter;
import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.phong.PhongEdit;
import com.example.nhatro2.phong.PhongModel;
import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class HopDongAdd extends AppCompatActivity {

    ImageView thoat, logo, themNguoiThue;
    SharedPreferences shp;
    RecyclerView listThietBi, listKhachAdd;
    ThietBiAddAdapter dichVuAdapter;
    List<DichVuModel> dichVu;
    EditText tenDaiDienText, sdtDaiDienText;
    TextView textNameRoom, ngayKetThuc,themHopDong;
    private int mYear, mMonth, mDay;
    DatePickerDialog.OnDateSetListener setListener;
//    FloatingActionButton fab;
    List<ThanhVienModel> listKhachArr = new ArrayList<>();
    KhachAddAdapter addAdapterKhach;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hop_dong_add);

        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HopDongAdd.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HopDongAdd.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(HopDongAdd.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HopDongAdd.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(HopDongAdd.this, "Stay", Toast.LENGTH_SHORT).show();
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
        FrameLayout imageFrame = findViewById(R.id.imageServiceEdit);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.hop_dong);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        Bundle bundle = getIntent().getExtras();
        int idPhong = bundle.getInt("idPhong");
        String tenPhong = bundle.getString("tenPhong");
        int giaPhong = bundle.getInt("gia");
        String daiDien = bundle.getString("daidien");
        String dienThoai = bundle.getString("dienthoai");
        int tienCoc = bundle.getInt("datcoc");


        listThietBi = findViewById(R.id.thietbiCheck);
        listThietBi.setLayoutManager(new GridLayoutManager(HopDongAdd.this, 3));
        listThietBi.hasFixedSize();
        listThietBi.setNestedScrollingEnabled(false);

        tenDaiDienText = findViewById(R.id.tenDaiDienText);
        sdtDaiDienText = findViewById(R.id.sdtDaiDienText);
        textNameRoom = findViewById(R.id.textNameRoom);
        //
        Api.api.hopDongPhong(idPhong).enqueue(new Callback<PhongModel>() {
            @Override
            public void onResponse(Call<PhongModel> call, Response<PhongModel> response) {
                PhongModel phongHopDong = response.body();
                tenDaiDienText.setText(phongHopDong.getDaidien());
                sdtDaiDienText.setText(phongHopDong.getDienthoai());
                textNameRoom.setText("Phòng thuê " + phongHopDong.getTen());
            }

            @Override
            public void onFailure(Call<PhongModel> call, Throwable t) {
                Log.d("err", "" + t.toString());
            }
        });
        // Thiết bị
        Api.api.getDichVuList().enqueue(new Callback<List<DichVuModel>>() {
            @Override
            public void onResponse(Call<List<DichVuModel>> call, Response<List<DichVuModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(HopDongAdd.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                dichVu = response.body();
                dichVuAdapter = new ThietBiAddAdapter(HopDongAdd.this, dichVu);
                listThietBi.setAdapter(dichVuAdapter);
            }

            @Override
            public void onFailure(Call<List<DichVuModel>> call, Throwable t) {

            }
        });
        // Chon ngay ket thuc
        // Ánh xạ text date
        ngayKetThuc = findViewById(R.id.ngayKetThuc);
        // set data gửi lên

        // date input
        Calendar time = Calendar.getInstance();

        mYear = time.get(Calendar.YEAR);
        mMonth = time.get(Calendar.MONTH);
        mDay = time.get(Calendar.DAY_OF_MONTH);

        Locale locale = getResources().getConfiguration().locale;
        Locale.setDefault(Locale.forLanguageTag(locale.getCountry()));

        // Bắt sự kiện chọn ngày sinh
        ngayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(HopDongAdd.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, mYear, mMonth, mDay);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        // xet text ket qua ngay chon
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = day + "-" + month + "-" + year;
                ngayKetThuc.setText(date);
            }
        };
        //
//        fab = findViewById(R.id.floatButtonHopDongAdd);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        listKhachAdd = findViewById(R.id.listKhachAdd);
        listKhachAdd.setLayoutManager(new LinearLayoutManager(HopDongAdd.this));
        listKhachAdd.hasFixedSize();
        listKhachAdd.setNestedScrollingEnabled(false);
        addAdapterKhach = new KhachAddAdapter(HopDongAdd.this, listKhachArr);
        listKhachAdd.setAdapter(addAdapterKhach);

        // Thêm khách thuê trọ
        themNguoiThue = findViewById(R.id.themNguoiThue);
        themNguoiThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listKhachArr.add(new ThanhVienModel(0, "", "", "", 4, "", "", "", "", "", "", "", 0, 0));

                if (listKhachArr.size() == 4) {
                    themNguoiThue.setVisibility(View.GONE);
                }

                addAdapterKhach.notifyDataSetChanged();
            }
        });

        // Thêm hợp đồng
        themHopDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}