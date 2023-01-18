package com.example.nhatro2.hop_dong;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HetHieuLucFragment extends Fragment {
    RecyclerView listHetHieuLuc;
    List<HopDongModel> hopDongHetHieuLuc = new ArrayList<>();
    public HetHieuLucFragment() {

    }

    public static HetHieuLucFragment newInstance(String param1, String param2) {
        HetHieuLucFragment fragment = new HetHieuLucFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_het_hieu_luc,container,false);
        listHetHieuLuc = view.findViewById(R.id.listHetHieuLuc);
        listHetHieuLuc.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listHetHieuLuc.hasFixedSize();
        listHetHieuLuc.setNestedScrollingEnabled(false);

        ApiQH.apiQH.getHetHieuLuc().enqueue(new Callback<List<HopDongModel>>() {
            @Override
            public void onResponse(Call<List<HopDongModel>> call, Response<List<HopDongModel>> response) {
                hopDongHetHieuLuc = response.body();
                listHetHieuLuc.setAdapter(new HetHieuLucAdapter(view.getContext(),hopDongHetHieuLuc));
            }

            @Override
            public void onFailure(Call<List<HopDongModel>> call, Throwable t) {
            }
        });

        return view;
    }
}
