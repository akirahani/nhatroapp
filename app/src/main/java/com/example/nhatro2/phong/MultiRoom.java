package com.example.nhatro2.phong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    SharedPreferences sharedPhong;
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
                        Log.d("ok ok","");
                    }

                    @Override
                    public void onFailure(Call<PhongMultiModel> call, Throwable t) {
                        Log.d("err",""+t.toString());
                    }
                });
            }
        });
    }
}