package com.example.nhatro2.api;

import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiQH {
    String url = "http://172.16.1.71";
    //    String url = "http://192.168.0.105";
//      String url = "http://192.168.1.191";
//    String url = "http://192.168.1.9";
    //Init
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-DD HH:mm:ss").create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    ApiQH apiQH = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ApiQH.class);

    @POST("/quanghieu/admin/api/auth/login.php")
    @FormUrlEncoded
    Call<ThanhVienModel> postLogin(@Field("username") String username,
                                   @Field("password") String password);
}
