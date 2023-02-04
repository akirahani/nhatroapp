package com.example.nhatro2.thong_ke;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;

import com.example.nhatro2.api.ApiQH;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongKe extends AppCompatActivity {
    ImageView thoat, logo;
    SharedPreferences shp;
    int soPhongTrong ,soPhongThue ,soKhachThue;
    AnyChartView anyChartTongQuan;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        //Ánh xạ
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongKe.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ThongKe.this);
                builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Yes</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ThongKe.this, "Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ThongKe.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>No</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(ThongKe.this, "Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // Biểu đồ tròn (pie chart)
        ApiQH.apiQH.getTongQuan().enqueue(new Callback<TongQuanChartModel>() {
            @Override
            public void onResponse(Call<TongQuanChartModel> call, Response<TongQuanChartModel> response) {
                TongQuanChartModel tongQuanDetail = response.body();
                soPhongTrong = tongQuanDetail.getPhongthue();
                soPhongThue = tongQuanDetail.getPhongthue();
                soKhachThue = tongQuanDetail.getPhongthue();
            }

            @Override
            public void onFailure(Call<TongQuanChartModel> call, Throwable t) {
                Log.d("err tong quan",""+t.toString());
            }
        });

        anyChartTongQuan = findViewById(R.id.tongQuanChart);
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("John", 10000));
        data.add(new ValueDataEntry("Jake", 12000));
        data.add(new ValueDataEntry("Peter", 18000));

        anyChartTongQuan.setChart(pie);

    }

}