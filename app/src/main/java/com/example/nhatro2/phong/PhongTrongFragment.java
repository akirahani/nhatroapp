package com.example.nhatro2.phong;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhongTrongFragment extends Fragment {
    RecyclerView listEmptyRoom ;
    List<PhongModel> roomEmpty ;
    public PhongTrongFragment() {

    }

    public static PhongTrongFragment newInstance(String param1, String param2) {
        PhongTrongFragment fragment = new PhongTrongFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_phong_trong, container, false);
        listEmptyRoom = view.findViewById(R.id.listEmptyRoom);
        listEmptyRoom.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listEmptyRoom.hasFixedSize();
        listEmptyRoom.setNestedScrollingEnabled(false);

        Api.api.getPhongList().enqueue(new Callback<List<PhongModel>>() {
            @Override
            public void onResponse(Call<List<PhongModel>> call, Response<List<PhongModel>> response) {
                roomEmpty = response.body();
                Log.d("phong","phong"+roomEmpty);

            }

            @Override
            public void onFailure(Call<List<PhongModel>> call, Throwable t) {

            }
        });
//        listEmptyRoom.setAdapter(view.getContext());
        return view;
    }
}