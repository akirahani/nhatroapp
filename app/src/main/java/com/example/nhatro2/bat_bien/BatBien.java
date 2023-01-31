package com.example.nhatro2.bat_bien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.example.nhatro2.kha_bien.KhaBien;
import com.example.nhatro2.kha_bien.KhaBienModel;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.tien_nuoc.NuocItemClick;
import com.example.nhatro2.tien_nuoc.TienNuoc;
import com.example.nhatro2.tien_nuoc.TienNuocAdapter;
import com.example.nhatro2.tien_nuoc.TienNuocEdit;
import com.example.nhatro2.tien_nuoc.TienNuocModel;
import com.kal.rackmonthpicker.RackMonthPicker;
import com.kal.rackmonthpicker.listener.DateMonthDialogListener;
import com.kal.rackmonthpicker.listener.OnCancelMonthDialogListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BatBien extends AppCompatActivity {
    ImageView thoat, logo, addBatBien;
    SharedPreferences shp;
    RecyclerView listBatBien;
    List<BatBienModel> listBatBienGet = new ArrayList<>();
    TextView chonThangBatBien, batBienAdd, batBienClose;
    RadioButton tienMatBatBien, chuyenKhoanBatBien;
    EditText lyDoBatBien, tienBatBien;
    BatBienAdapter batBienAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bat_bien);
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BatBien.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BatBien.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(BatBien.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BatBien.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(BatBien.this, "Stay", Toast.LENGTH_SHORT).show();
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
        FrameLayout imageBatBien = findViewById(R.id.imageBatBien);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.ic_batbien);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageBatBien.addView(iv, params);
        // Xet danh sach bat bien
        listBatBien = findViewById(R.id.listBatBien);
        listBatBien.setLayoutManager(new LinearLayoutManager(BatBien.this));
        listBatBien.hasFixedSize();
        listBatBien.setNestedScrollingEnabled(false);

        // Hien thi thang - nam hien tai cho chi phi kha bien
        DateFormat monthFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthGet = Integer.parseInt(monthFormat.format(date));
        // set Text hien thi thang - nam hien tai cho chi phi kha bien
        chonThangBatBien = findViewById(R.id.chonThangBatBien);
        chonThangBatBien.setText("Tháng "+monthFormat.format(date)+" - năm "+year);

        ApiQH.apiQH.getBatBien().enqueue(new Callback<List<BatBienModel>>() {
            @Override
            public void onResponse(Call<List<BatBienModel>> call, Response<List<BatBienModel>> response) {
                listBatBienGet = response.body();
                listBatBien.setAdapter(new BatBienAdapter(BatBien.this,listBatBienGet));
            }

            @Override
            public void onFailure(Call<List<BatBienModel>> call, Throwable t) {

            }
        });

        chonThangBatBien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RackMonthPicker rackMonthPicker = new RackMonthPicker(BatBien.this);
                rackMonthPicker.setLocale(Locale.ENGLISH).setPositiveButton(new DateMonthDialogListener() {
                    @Override
                    public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {
                        chonThangBatBien.setText("Tháng "+month+" - năm "+year);
                        ApiQH.apiQH.chooseTimeBatBien(month,year).enqueue(new Callback<List<BatBienModel>>() {
                            @Override
                            public void onResponse(Call<List<BatBienModel>> call, Response<List<BatBienModel>> response) {
                                listBatBienGet = response.body();
                                batBienAdapter = new BatBienAdapter(BatBien.this, listBatBienGet);
                                listBatBien.setAdapter(batBienAdapter);
                            }

                            @Override
                            public void onFailure(Call<List<BatBienModel>> call, Throwable t) {
                                Log.d("err",""+t.toString());
                            }
                        });

                    }
                }).setNegativeButton(new OnCancelMonthDialogListener() {
                    @Override
                    public void onCancel(AlertDialog dialog) {
                        rackMonthPicker.dismiss();
                    }
                }).show();
            }
        });

        addBatBien = findViewById(R.id.addBatBien);

        addBatBien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialogBatBien = new Dialog(BatBien.this);
                dialogBatBien.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogBatBien.setContentView(R.layout.layout_dialog_them_phi_bat_bien);

                Window window = dialogBatBien.getWindow();
                if (window == null) {
                    return;
                }

                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAttr = window.getAttributes();
                windowAttr.gravity = Gravity.CENTER;
                window.setAttributes(windowAttr);

                batBienAdd = dialogBatBien.findViewById(R.id.batBienAdd);
                batBienClose = dialogBatBien.findViewById(R.id.batBienClose);

                batBienAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tienMatBatBien = dialogBatBien.findViewById(R.id.tienMatBatBien);
                        chuyenKhoanBatBien = dialogBatBien.findViewById(R.id.chuyenKhoanBatBien);
                        int hinhThucThanhToan = 0;
                        if(tienMatBatBien.isChecked()){
                            hinhThucThanhToan = 1;
                        }else if(chuyenKhoanBatBien.isChecked()){
                            hinhThucThanhToan = 2;
                        }

                        lyDoBatBien = dialogBatBien.findViewById(R.id.lyDoBatBienText);
                        tienBatBien = dialogBatBien.findViewById(R.id.tienBatBienText);
                        String lyDoBatBienText = lyDoBatBien.getText().toString();
                        String tienBatBienText = tienBatBien.getText().toString();
                        int giaTienBatBienFinal = Integer.parseInt(tienBatBienText);

                        ApiQH.apiQH.addBatBien(lyDoBatBienText,giaTienBatBienFinal,hinhThucThanhToan).enqueue(new Callback<BatBienModel>() {
                            @Override
                            public void onResponse(Call<BatBienModel> call, Response<BatBienModel> response) {
                                BatBienModel khaBatThem = response.body();
                                batBienAdapter = new BatBienAdapter(BatBien.this, listBatBienGet);
                                dialogBatBien.dismiss();
                            }

                            @Override
                            public void onFailure(Call<BatBienModel> call, Throwable t) {
                                Log.d("err",""+t.toString());
                            }
                        });
                    }
                });

                batBienClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBatBien.dismiss();
                    }
                });

                dialogBatBien.show();
            }
        });

    }
}