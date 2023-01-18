package com.example.nhatro2.hop_dong;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabHopDongAdapter extends FragmentStateAdapter {
    public TabHopDongAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
            default:
                return new ConHieuLucFragment();
            case 1:
                return new HetHieuLucFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
