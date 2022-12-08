package com.example.nhatro2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nhatro2.api.Api;
import com.example.nhatro2.phong.PhongAdapter;
import com.example.nhatro2.phong.PhongModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Tag;

public class HomeFragment extends Fragment {
    RecyclerView listRoom;
    PhongAdapter phongAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.d("ok","home");
        // Phong
        listRoom = view.findViewById(R.id.listRoom);
        listRoom.setLayoutManager(new LinearLayoutManager(getContext()));
        listRoom.hasFixedSize();
        listRoom.setNestedScrollingEnabled(false);

        Api.api.getPhongList().enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(),response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<PhongModel> phongList = response.body();
                phongAdapter = new PhongAdapter(getContext(),phongList);
                listRoom.setAdapter(phongAdapter);
            }
            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}