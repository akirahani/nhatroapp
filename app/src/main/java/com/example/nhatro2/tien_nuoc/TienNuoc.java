package com.example.nhatro2.tien_nuoc;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.thanhvien.KhachTro;

import java.util.Calendar;

public class TienNuoc extends AppCompatActivity {
    ImageView thoat, logo;
    SharedPreferences shp;
    TextView chonThangNuoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_nuoc);
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TienNuoc.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TienNuoc.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TienNuoc.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TienNuoc.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(TienNuoc.this, "Stay", Toast.LENGTH_SHORT).show();
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

        chonThangNuoc = findViewById(R.id.chonThangNuoc);
        chonThangNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(MainActivity.this,
                        new MonthPickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(int selectedMonth, int selectedYear) { // on date set }
                            }, today.get(Calendar.YEAR), today.get(Calendar.MONTH));

                builder.setActivatedMonth(Calendar.JULY).setMinYear(1990)
                       .setActivatedYear(2017)
                       .setMaxYear(2030)
                       .setMinMonth(Calendar.FEBRUARY)
                       .setTitle("Select trading month")
                       .setMonthRange(Calendar.FEBRUARY, Calendar.NOVEMBER)
                                            // .setMaxMonth(Calendar.OCTOBER)
                                            // .setYearRange(1890, 1890)
                                            // .setMonthAndYearRange(Calendar.FEBRUARY, Calendar.OCTOBER, 1890, 1890)
                                            //.showMonthOnly()
                                            // .showYearOnly()
                       .setOnMonthChangedListener(new MonthPickerDialog.OnMonthChangedListener() {
                                                @Override
                                                public void onMonthChanged(int selectedMonth) { // on month selected } })
                       .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
                                                        @Override
                                                        public void onYearChanged(int selectedYear) { // on year selected } })
                        .build()
                                                                    .show();
                //
            }
        });
    }
}