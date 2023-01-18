package com.example.nhatro2.dong_tien;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.R;
import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.hop_dong.ClickKhachAddHopDong;
import com.example.nhatro2.hop_dong.ListKhachChonAdapter;
import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetChonPhongTien extends BottomSheetDialogFragment {
    RecyclerView listPhongClick;
    LinearLayoutManager layoutBottomSheetPhongChon;
    public BottomSheetChonPhongTien() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_phong_chon, container, false);
        listPhongClick = view.findViewById(R.id.listKhachClick);
        listPhongClick.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listPhongClick.hasFixedSize();
        listPhongClick.setNestedScrollingEnabled(false);

        return view;
    }
}
