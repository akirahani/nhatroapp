package com.example.nhatro2.phong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

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
    String listRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_room);
        SharedPreferences sharedPhong = getSharedPreferences("idPhong", Context.MODE_PRIVATE);
        listRoom = sharedPhong.getString("items", "");
        Log.d("",""+listRoom);
        Api.api.phongChecked(listRoom).enqueue(new Callback<PhongModel>() {
            @Override
            public void onResponse(Call<PhongModel> call, Response<PhongModel> response) {
                PhongModel phongCheck = response.body();
                Log.d("",""+phongCheck.getTen());
            }

            @Override
            public void onFailure(Call<PhongModel> call, Throwable t) {

            }
        });
    }
}