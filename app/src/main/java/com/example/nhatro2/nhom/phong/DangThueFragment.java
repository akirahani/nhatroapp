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

public class DangThueFragment extends Fragment {
    RecyclerView listRentRoom;
    List<PhongModel> phongThue = new ArrayList<>();
    public DangThueFragment() {
    }

    public static DangThueFragment newInstance(String param1, String param2) {
        DangThueFragment fragment = new DangThueFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_thue,container,false);
        listRentRoom = view.findViewById(R.id.listStayRoom);
        listRentRoom.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listRentRoom.hasFixedSize();
        listRentRoom.setNestedScrollingEnabled(false);

        ApiQH.apiQH.getRentRoomList().enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                phongThue = response.body();
                listRentRoom.setAdapter(new DangThueAdapter(view.getContext(),phongThue));
            }

            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {

            }
        });

        return view;
    }
}