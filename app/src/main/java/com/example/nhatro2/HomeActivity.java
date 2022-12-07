package com.example.nhatro2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.nhatro2.api.Api;

import java.util.List;

import retrofit2.Callback;

import com.example.nhatro2.phong.PhongAdapter;
import com.example.nhatro2.phong.PhongModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    RecyclerView listRoom;
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Phong
        listRoom = findViewById(R.id.listRoom);
        listRoom.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        listRoom.hasFixedSize();
        listRoom.setNestedScrollingEnabled(false);

        Api.api.getPhongList().enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(HomeActivity.this,response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<PhongModel> phongList = response.body();
                listRoom.setAdapter(new PhongAdapter(HomeActivity.this,phongList));
            }
            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {
                Toast.makeText(HomeActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //Menu
        navigation = (BottomNavigationView) findViewById(R.id.menu);

//        toolbar.setTitle("Shop");


    }

}