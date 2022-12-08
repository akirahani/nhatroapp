package com.example.nhatro2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.nhatro2.api.Api;

import java.util.List;

import retrofit2.Callback;

import com.example.nhatro2.phong.PhongAdapter;
import com.example.nhatro2.phong.PhongModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import retrofit2.Call;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView navigation;
    private int mMenuId;
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
                    case R.id.menumenu:
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
//        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = null;
//
////                switch (item.getItemId()){
////                    case R.id.item1:
////                        fragment = new HomeFragment();
////                        break;
////                    case R.id.item2:
////                        fragment = new MenuFragment();
////                        break;
////                    case R.id.item3:
////                        fragment = new NotificationFragment();
////                        break;
////                }
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer,fragment).commit();
////                return true;
//            //}
//        });
        //Menu
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        // uncheck the other items.
//        mMenuId = item.getItemId();
//        for (int i = 0; i < mBtmView.getMenu().size(); i++) {
//            MenuItem menuItem = mBtmView.getMenu().getItem(i);
//            boolean isChecked = menuItem.getItemId() == item.getItemId();
//            menuItem.setChecked(isChecked);
//        }

//        return true;
//    }
//

}