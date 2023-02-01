package com.example.nhatro2.phong;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhongTrongFragment extends Fragment {
    RecyclerView listEmptyRoom;
    List<PhongModel> roomEmpty = new ArrayList<>();
    BottomNavigationView slideUp;
    String listRoom;

    public PhongTrongFragment() {

    }

    public static PhongTrongFragment newInstance(String param1, String param2) {
        PhongTrongFragment fragment = new PhongTrongFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phong_trong, container, false);
        listEmptyRoom = view.findViewById(R.id.listEmptyRoom);
        listEmptyRoom.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listEmptyRoom.hasFixedSize();
        listEmptyRoom.setNestedScrollingEnabled(false);
        ApiQH.apiQH.getPhongList().enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                roomEmpty = response.body();
                listEmptyRoom.setAdapter(new PhongAdapter(view.getContext(), roomEmpty, new PhongTrongItemClick() {
                    @Override
                    public void itemOnClick(int count) {
                        //Click show hide slide
                        View redLayout = view.findViewById(R.id.slideUp);
                        ViewGroup parent = view.findViewById(R.id.parent);

                        Transition transition = new Slide(Gravity.BOTTOM);
                        transition.setDuration(600);
                        transition.addTarget(R.id.slideUp);

                        TransitionManager.beginDelayedTransition(parent, transition);
                        redLayout.setVisibility((count == 0) ? View.GONE : View.VISIBLE);
                    }
                }));

            }

            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {
                Log.d("err","loi phong trong"+t.toString());
            }
        });

        slideUp = view.findViewById(R.id.slideUp);
        slideUp.setVisibility(View.INVISIBLE);
        slideUp.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.multiroom:
                        SharedPreferences sharedPhong = getContext().getSharedPreferences("idPhong", Context.MODE_PRIVATE);
                        listRoom = sharedPhong.getString("items", "");
                        Intent intent = new Intent(getContext(), MultiRoom.class);
                        intent.putExtra("idRoom",""+listRoom);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
        return view;
    }


}