package com.example.nhatro2.nhom.phong;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BanGiaoFragment extends Fragment {
    RecyclerView listBookRoom;
    List<PhongModel> phongBanGiao = new ArrayList<>();
    public BanGiaoFragment() {

    }

    public static BanGiaoFragment newInstance(String param1, String param2) {
        BanGiaoFragment fragment = new BanGiaoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ban_giao,container,false);
        listBookRoom = view.findViewById(R.id.listBookRoom);
        listBookRoom.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listBookRoom.hasFixedSize();
        listBookRoom.setNestedScrollingEnabled(false);

        ApiQH.apiQH.getBookRoomList().enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                phongBanGiao = response.body();
                listBookRoom.setAdapter(new BanGiaoAdapter(view.getContext(),phongBanGiao));
            }

            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {

            }
        });

        return view;
    }
}