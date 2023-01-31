package com.example.nhatro2.nhom.phong;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPhongAdapter extends FragmentStateAdapter {
    public TabPhongAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
            default:
                return new PhongTrongFragment();
            case 1:
                return new BanGiaoFragment();
            case 2:
                return new DangThueFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
