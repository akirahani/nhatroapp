package com.example.nhatro2.phong;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.nhatro2.HomeFragment;
import com.example.nhatro2.MenuFragment;
import com.example.nhatro2.NotificationFragment;
import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;
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
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

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
        Api.api.getPhongList().enqueue(new Callback<List<PhongModel>>() {
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_room, menu);
        MenuItem searchItem = menu.findItem(R.id.searchRoom);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.searchRoom:
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }


}