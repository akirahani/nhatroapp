package com.example.nhatro2.dich_vu;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DichVuEdit extends AppCompatActivity {
    TextView tacVuThietBi,tieuDeTenThietBi,tieuDeGiaThietBi,backDV, capNhat;
    ImageView thoat,them,logo;
    EditText ten,gia,tenTB, giaTB;
    SharedPreferences shp;
    int id_update = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dich_vu_edit);
        // logo
        logo = findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DichVuEdit.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DichVuEdit.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DichVuEdit.this,"Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DichVuEdit.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(DichVuEdit.this,"Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        // onClick Back
        backDV = findViewById(R.id.backDichVu);
        backDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DichVuEdit.this,DichVu.class);
                startActivity(intent);
            }
        });
        // get ID
        tenTB = findViewById(R.id.tenThietBiEdit);
        giaTB = findViewById(R.id.giaThietBiEdit);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            Toast.makeText(this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
        }
        else
        {
            int idDichVu = bundle.getInt("idDichVu");
            Api.api.detailDichVu(idDichVu).enqueue(new Callback<DichVuModel>() {
                @Override
                public void onResponse(Call<DichVuModel> call, Response<DichVuModel> response) {
                    DichVuModel dichVuModel = response.body();
                    if(response.isSuccessful()){
                        id_update = idDichVu;
                        if(id_update > 0 ){
                            tenTB.setText(dichVuModel.getTen());
                            giaTB.setText(""+dichVuModel.getGia());
                        }
                    }
                }

                @Override
                public void onFailure(Call<DichVuModel> call, Throwable t) {
                    Toast.makeText(DichVuEdit.this,"Có lỗi hiển thị dịch vụ",Toast.LENGTH_SHORT).show();
                }
            });
        }
        // Cap nhat
        capNhat = findViewById(R.id.capNhat);
        capNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_send = id_update;
                int giaCapNhat = Integer.parseInt(giaTB.getText().toString());
                String tenCapNhat = tenTB.getText().toString();
                Api.api.editDichVu(id_update,tenCapNhat,giaCapNhat).enqueue(new Callback<DichVuModel>() {
                    @Override
                    public void onResponse(Call<DichVuModel> call, Response<DichVuModel> response) {
                        DichVuModel dichVuModel = response.body();
                        Log.d("tahg0","giatri"+dichVuModel);
                        if(dichVuModel.getId() == id_send) {
                            Intent intent = new Intent(DichVuEdit.this, DichVu.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(DichVuEdit.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(DichVuEdit.this, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<DichVuModel> call, Throwable t) {
                        Toast.makeText(DichVuEdit.this,"Lỗi cập nhật",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        float alpha = (float) 0.7;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((TextView)findViewById(R.id.tacVuThietBi)).setTextColor(Color.argb(alpha, 0, 0, 0));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((TextView)findViewById(R.id.tieuDeTenThietBi)).setTextColor(Color.argb(alpha, 0, 0, 0));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((TextView)findViewById(R.id.tieuDeGiaThietBi)).setTextColor(Color.argb(alpha, 0, 0, 0));
        }
        // Vị trí tương đối
        FrameLayout imageServiceAdd = findViewById(R.id.imageServiceEdit);
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(R.drawable.dichvu);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(134,134);
        params.leftMargin = 46;
        params.topMargin = 18;
        imageServiceAdd.addView(iv, params);
    }
}
