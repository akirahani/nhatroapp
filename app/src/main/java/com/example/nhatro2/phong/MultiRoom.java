package com.example.nhatro2.phong;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class MultiRoom extends AppCompatActivity {
    ImageView thoat,logo;
    SharedPreferences shp;
    String listRoom, tenPhongchecked;
    List<PhongModel> phongCheckedList;
    TextView nameRoomChecked,capNhatMulti;
    RecyclerView phongList;
    EditText tenDaiDien, tiencoc, dienThoaiDaiDien;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_room);

        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiRoom.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MultiRoom.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MultiRoom.this,"Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MultiRoom.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MultiRoom.this,"Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // SharedPreferences sharedPhong = getSharedPreferences("idPhong", Context.MODE_PRIVATE);
        Bundle bundle = getIntent().getExtras();
        listRoom = bundle.getString("idRoom");
        nameRoomChecked = findViewById(R.id.nameRoomChecked);
        phongList = findViewById(R.id.roomChecked);
        phongList.setLayoutManager(new GridLayoutManager(MultiRoom.this, 3));
        phongList.hasFixedSize();
        phongList.setNestedScrollingEnabled(false);

        Api.api.phongChecked(listRoom).enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                List<PhongModel> phongCheck = response.body();
                phongCheckedList = response.body();
                int sizePhong = phongCheck.size();
                for (int i = 0; i < sizePhong; i++) {
                    tenPhongchecked = phongCheck.get(i).toString();
                    phongList.setAdapter(new MultiRoomAdapter(MultiRoom.this, phongCheckedList));
                }
            }
            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {
            }
        });

        capNhatMulti = findViewById(R.id.capNhatMulti);
        tenDaiDien = findViewById(R.id.tenDaiDienText);
        tiencoc = findViewById(R.id.tienCoc);
        dienThoaiDaiDien = findViewById(R.id.sdtDaiDienText);
        capNhatMulti.setOnClickListener(new View.OnClickListener() {
            SharedPreferences sharedPhong = getSharedPreferences("idPhong", Context.MODE_PRIVATE);
            SharedPreferences.Editor roomEditor = sharedPhong.edit();
            String listRoom = sharedPhong.getString("items", "");
            @Override
            public void onClick(View view) {
                String tenKhach = tenDaiDien.getText().toString();
                String tienCoc = tiencoc.getText().toString();
                String dienThoai  = dienThoaiDaiDien.getText().toString();
                Api.api.datCocPhong(tenKhach,dienThoai,tienCoc,listRoom).enqueue(new Callback<PhongMultiModel>() {
                    @Override
                    public void onResponse(Call<PhongMultiModel> call, Response<PhongMultiModel> response) {
                        PhongMultiModel phongChecked = response.body();
                        Intent intent = new Intent(MultiRoom.this,Phong.class);
                        startActivity(intent);
                        roomEditor.remove("items");
                        roomEditor.commit();
                        Toast.makeText(MultiRoom.this, "Đặt cọc cho nhiều phòng thành công",Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onFailure(Call<PhongMultiModel> call, Throwable t) {
                        Toast.makeText(MultiRoom.this, "Đặt cọc cho nhiều phòng thất bại",Toast.LENGTH_SHORT);
                    }
                });
            }
        });
    }

//    // Back button
//    public void onBackPressed()
//    {
//        super.onBackPressed();
//        SharedPreferences sharedPhong = getSharedPreferences("idPhong", Context.MODE_PRIVATE);
//        SharedPreferences.Editor roomEditor = sharedPhong.edit();
//        roomEditor.remove("items");
//        roomEditor.commit();
//    }
}