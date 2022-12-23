package com.example.nhatro2.phong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    TextView nameRoomChecked;
    RecyclerView phongList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_room);

//        SharedPreferences sharedPhong = getSharedPreferences("idPhong", Context.MODE_PRIVATE);
//        listRoom = sharedPhong.getString("items", "");
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
                Log.d("", "" + t.toString());
            }
        });
    }
}