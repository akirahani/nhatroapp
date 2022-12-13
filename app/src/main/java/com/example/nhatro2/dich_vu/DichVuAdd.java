package com.example.nhatro2.dich_vu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhatro2.MainActivity;
import com.example.nhatro2.R;
import com.example.nhatro2.api.Api;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class DichVuAdd extends AppCompatActivity {
    ImageView thoat,them;
    EditText ten,gia;
    TextView themDV, backDV;
    SharedPreferences shp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dich_vu_add);

        // Nút thoát
        thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DichVuAdd.this);
                builder.setTitle("Confirm").setMessage("Bạn có thực sự muốn thoát ?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DichVuAdd.this,"Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DichVuAdd.this, MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(DichVuAdd.this,"Stay", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        //Thêm mới
            // Input
            ten = findViewById(R.id.tenThietBiAdd);
            gia = findViewById(R.id.giaThietBiAdd);
            // Click button
            themDV = findViewById(R.id.themDichVu);
            backDV = findViewById(R.id.backDichVu);
            // onClick Back
            backDV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DichVuAdd.this,DichVu.class);
                    startActivity(intent);
                }
            });

            // onClick Add
            themDV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tenThietBi = ten.getText().toString();
                    String giaText  = gia.getText().toString();
                    int giaThietBi =Integer.parseInt(giaText);
                    if(!tenThietBi.equals("") && !giaText.equals("")){
                        Api.api.addThietBi(tenThietBi,giaThietBi).enqueue(new Callback<DichVuModel>() {
                            @Override
                            public void onResponse(Call<DichVuModel> call, Response<DichVuModel> response) {
                                DichVuModel dichvu = response.body();
                                Intent intent = new Intent(DichVuAdd.this,DichVu.class);
                                Toast.makeText(DichVuAdd.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }
                            @Override
                            public void onFailure(Call<DichVuModel> call, Throwable t) {
                                Toast.makeText(DichVuAdd.this,"Thêm không thành công!",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
    }
}
