package com.example.nhatro2.phong;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PhongEdit extends AppCompatActivity {
    ImageView closeFormEdit,thoat,logo,imagePhongTro;
    RecyclerView listRoom;
    SharedPreferences shp;
    ViewPager2 tabContentViewRoom;
    TabLayout tabRoom;
    RelativeLayout tieuDePhongTro;
    LinearLayout rowFirstEditRoom;
    RadioButton trong,thue,bangiao;
    TextView tenPhongEdit,vitriPhongEdit;
    int trangthai;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phong_edit);
        closeFormEdit = findViewById(R.id.closeFormEditRoom);
        closeFormEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhongEdit.this,Phong.class);
                startActivity(intent);
            }
        });
        // home
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhongEdit.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PhongEdit.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(PhongEdit.this,"Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PhongEdit.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(PhongEdit.this,"Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        // Xét ví trí tương đối
        FrameLayout imageFrame = findViewById(R.id.tieuDePhongTro);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.phongtro);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134, 134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageFrame.addView(iv, params);

        //
        trong = findViewById(R.id.trong);
        bangiao = findViewById(R.id.bangiao);
        thue = findViewById(R.id.thue);
        rowFirstEditRoom = findViewById(R.id.rowFirstEditRoom);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            Toast.makeText(this,"Có lỗi !",Toast.LENGTH_SHORT).show();
        }else{
            int idPhong = bundle.getInt("idPhong");
            int tang = bundle.getInt("tang");
            trangthai = bundle.getInt("trangthai");
            String tenPhong = bundle.getString("tenPhong");
            String dayPhong = bundle.getString("day");
            tenPhongEdit = findViewById(R.id.tenPhongEdit);
            vitriPhongEdit = findViewById(R.id.vitriPhongEdit);
            tenPhongEdit.setText("Tên: "+tenPhong);
            vitriPhongEdit.setText("Vị trí: Dãy "+dayPhong+" - Tầng "+tang);

            switch(trangthai) {
                case 1:
                    trong.setChecked(true);
                    int color1 = Color.parseColor("#F4F7FF");
                    rowFirstEditRoom.setBackgroundColor(color1);
                    break;
                case 2:
                    thue.setChecked(true);
                    int color2 = Color.parseColor("#FFF7F7");
                    rowFirstEditRoom.setBackgroundColor(color2);
                    break;
                case 3:
                    bangiao.setChecked(true);
                    int color3 = Color.parseColor("#FFFDF5");
                    rowFirstEditRoom.setBackgroundColor(color3);
                    break;
            }
        }
    }


}
