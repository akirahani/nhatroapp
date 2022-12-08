package com.example.nhatro2.api;

import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.example.nhatro2.phong.PhongModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import retrofit2.Call;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Api {
    String url = "http://172.16.1.71";
    //Init
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-DD HH:mm:ss").create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    Api api = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(Api.class);

    @GET ("/nhatro/admin/api/phong/list.php")
    Call<List<PhongModel>> getPhongList();

    @GET ("/nhatro/admin/api/khach/list.php")
    Call<List<ThanhVienModel>> getKhachList();

    @POST ("/nhatro/admin/api/phong/add.php")
    @FormUrlEncoded
    Call <POST> addPhong(@Field("tenphong") String tenphong,
                         @Field("trangthai") int trangthai,
                         @Field("vitri") int vitri);

    @FormUrlEncoded
    @POST ("/nhatro/admin/api/auth/login.php")
    Call <ThanhVienModel> postLogin(@Field("username") String username,
                         @Field("password") String password);
}
