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
import com.example.nhatro2.phong.BanGiaoAdapter;
import com.example.nhatro2.phong.BanGiaoFragment;
import com.example.nhatro2.phong.PhongModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConHieuLucFragment extends Fragment {
    RecyclerView listConHieuLuc;
    List<HopDongModel> hopDongConHieuLuc = new ArrayList<>();
    public ConHieuLucFragment() {

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
        View view = inflater.inflate(R.layout.fragment_con_hieu_luc,container,false);
        listConHieuLuc = view.findViewById(R.id.listConHieuLuc);
        listConHieuLuc.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listConHieuLuc.hasFixedSize();
        listConHieuLuc.setNestedScrollingEnabled(false);

        ApiQH.apiQH.getConHieuLuc().enqueue(new Callback<List<HopDongModel>>() {
            @Override
            public void onResponse(Call<List<HopDongModel>> call, Response<List<HopDongModel>> response) {
                hopDongConHieuLuc = response.body();
                listConHieuLuc.setAdapter(new ConHieuLucAdapter(view.getContext(),hopDongConHieuLuc));
            }

            @Override
            public void onFailure(Call<List<HopDongModel>> call, Throwable t) {

            }
        });

        return view;
    }
}
