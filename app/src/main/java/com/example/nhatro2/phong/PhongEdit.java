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
import android.widget.EditText;
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
import com.example.nhatro2.api.Api;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class PhongEdit extends AppCompatActivity {
    ImageView closeFormEdit,thoat,logo,imagePhongTro;
    RecyclerView listRoom;
    SharedPreferences shp;
    ViewPager2 tabContentViewRoom;
    TabLayout tabRoom;
    RelativeLayout tieuDePhongTro;
    LinearLayout rowFirstEditRoom;
    RadioButton trong,thue,bangiao;
    TextView tenPhongEdit,vitriPhongEdit,editRoomButton;
    EditText tienPhong,daidien,dienthoai;
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

        // Ánh xạ thông tin phòng edit
        trong = findViewById(R.id.trong);
        bangiao = findViewById(R.id.bangiao);
        thue = findViewById(R.id.thue);
        tienPhong = findViewById(R.id.tienPhong);
        rowFirstEditRoom = findViewById(R.id.rowFirstEditRoom);
        daidien = findViewById(R.id.daiDien);
        dienthoai = findViewById(R.id.dienThoai);
        tenPhongEdit = findViewById(R.id.tenPhongEdit);
        vitriPhongEdit = findViewById(R.id.vitriPhongEdit);
        tienPhong = findViewById(R.id.tienPhong);

        // Lấy thông tin gửi từ adapter
        Bundle bundle = getIntent().getExtras();
        int idPhong = bundle.getInt("idPhong");
        int tang = bundle.getInt("tang");
        trangthai = bundle.getInt("trangthai");
        int giaPhong = bundle.getInt("gia");
        String tenPhong = bundle.getString("tenPhong");
        String dayPhong = bundle.getString("day");
        String daiDien = bundle.getString("daidien");
        String dienThoai = bundle.getString("dienthoai");
        if(bundle == null){
            Toast.makeText(this,"Có lỗi !",Toast.LENGTH_SHORT).show();
        }else{
            tenPhongEdit.setText("Tên: "+tenPhong);
            vitriPhongEdit.setText("Vị trí: Dãy "+dayPhong+" - Tầng "+tang);
            tienPhong.setText(""+giaPhong);
            if(trangthai == 2 || trangthai == 3){
                daidien.setText(daiDien);
                dienthoai.setText(dienThoai);
            }else{
                daidien.setText("");
                dienthoai.setText("");
            }

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
        // Ánh xạ cho việc cập nhật phòng
        editRoomButton = findViewById(R.id.editRoomButton);
        editRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenDaiDien = daidien.getText().toString();
                String dienThoai = dienthoai.getText().toString();
                Api.api.editPhong(idPhong,trangthai,tenDaiDien,dienThoai).enqueue(new Callback<PhongModel>() {
                    @Override
                    public void onResponse(Call<PhongModel> call, Response<PhongModel> response) {
                        PhongModel phongEdit = response.body();
                        if(phongEdit.getId() == idPhong){
                            Toast.makeText(PhongEdit.this,"Cập nhật phòng thành công !", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PhongEdit.this, Phong.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(PhongEdit.this,"Cập nhật phòng không thành công !", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<PhongModel> call, Throwable t) {
                        Toast.makeText(PhongEdit.this,"Cập nhật phòng không thành công !", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}
