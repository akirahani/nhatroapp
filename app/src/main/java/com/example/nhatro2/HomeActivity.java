package com.example.nhatro2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView navigation;
    private int mMenuId;
    Context context;
    SharedPreferences shp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Menu
        getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer,new HomeFragment()).commit();
        navigation = findViewById(R.id.menu);
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.menuhome:
                        Fragment fragment = HomeFragment.newInstance();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragContainer, fragment);
                        transaction.commit();
                        return true;
                    case R.id.menualert:
                        Fragment fragmenta = MenuFragment.newInstance();
                        FragmentTransaction transactiona = getSupportFragmentManager().beginTransaction();
                        transactiona.replace(R.id.fragContainer, fragmenta);
                        transactiona.commit();
                        return true;
                    case R.id.menunoti:
                        Fragment fragmentab = NotificationFragment.newInstance();
                        FragmentTransaction transactionab = getSupportFragmentManager().beginTransaction();
                        transactionab.replace(R.id.fragContainer, fragmentab);
                        transactionab.commit();
                        return true;
                }
                return false;
            }
        });
    }
}