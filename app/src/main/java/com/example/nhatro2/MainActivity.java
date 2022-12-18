package com.example.nhatro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatro2.HomeActivity;
import com.example.nhatro2.api.Api;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Tag;

public class MainActivity extends AppCompatActivity {
    TextView btnLogin;
    EditText username,password;
    SharedPreferences shp ;
    SharedPreferences.Editor shpEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.dangnhap);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        shp = getSharedPreferences("user",MODE_PRIVATE);

        int idUser = shp.getInt("idThanhVien",0);
        if(idUser != 0){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        // Api Khach
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String passd = password.getText().toString();

                if(!user.equals("") && !passd.equals("")) {
                    Api.api.postLogin(user, passd).enqueue(new Callback<ThanhVienModel>() {
                        @Override
                        public void onResponse(Call<ThanhVienModel> call, Response<ThanhVienModel> response) {
                            ThanhVienModel thanhvien = response.body();
                            if(thanhvien.getId() > 0)
                            {
                                shpEdit = shp.edit();
                                shpEdit.putInt("idThanhVien", thanhvien.getId());
                                shpEdit.putString("tenThanhVien", thanhvien.getFullname());
                                shpEdit.putString("dienthoaiThanhVien", thanhvien.getDienthoai());
                                shpEdit.putString("usernameThanhVien", thanhvien.getUsername());
                                shpEdit.apply();
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(MainActivity.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ThanhVienModel> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                        }


                    });
                }
            }
        });
    }


}