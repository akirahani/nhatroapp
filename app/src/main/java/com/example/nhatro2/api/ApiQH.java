package com.example.nhatro2.api;

import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.hop_dong.HopDongModel;
import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiQH {
//    String url = "http://172.16.1.71";
    String url = "http://192.168.1.190";;
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

    // Hợp đồng
        //Thêm
        @POST("/quanghieu/admin/api/hop-dong/add.php")
        @FormUrlEncoded
        Call<HopDongModel> addContract(@Field("thietbi") List<String> thietbi,
                                       @Field("khach") int khach,
                                       @Field("daidienophong") int idDaiDien,
                                       @Field("ketthuc") String ketthuc,
                                       @Field("ghichu") String ghichu,
                                       @Field("coc") String coc,
                                       @Field("phuongthuccoc") int phuongThuCcoc,
                                       @Field("phong") String phong,
                                       @Field("phuongthucphong") int phuongThuPhong,
                                       @Field("person") List<String> person,
                                       @Field("roomhd") String roomhd);
    // Thiết bị
        // Danh sách
        @GET("/quanghieu/admin/api/thiet-bi/list.php")
        Call<List<DichVuModel>> getDichVuList();
        //Thêm Dịch vụ
        @POST("/quanghieu/admin/api/thiet-bi/add.php")
        @FormUrlEncoded
        Call<DichVuModel> addThietBi(@Field("ten") String tenthietbi, @Field("gia") Integer giathietbi);
        // Get Thông tin dịch vụ
        @POST ("/quanghieu/admin/api/thiet-bi/detail.php")
        @FormUrlEncoded
        Call <DichVuModel> detailDichVu (@Field("id") int id);
        // Cập nhật Thông tin dịch vụ
        @POST ("/quanghieu/admin/api/thiet-bi/edit.php")
        @FormUrlEncoded
        Call <DichVuModel> editDichVu (@Field("id") int id,
                                       @Field("ten") String ten,
                                       @Field("gia") int gia);
        // Xóa dịch vụ
        @POST ("/quanghieu/admin/api/thiet-bi/del.php")
        @FormUrlEncoded
        Call <DichVuModel> delDichVu (@Field("id") int id);
    // Quản lí khách thuê
        // Khách thuê
        @GET ("/quanghieu/admin/api/khach/list.php")
        Call<List<ThanhVienModel>> getKhachList();

        //Thêm khách
        @POST ("/quanghieu/admin/api/khach/add.php")
        @FormUrlEncoded
        Call <ThanhVienModel> themKhach(@Field("fullname") String tenKhach,
                                        @Field("dienthoai") String dienthoai,
                                        @Field("cancuoc") String cancuoc,
                                        @Field("diachi") String diachi,
                                        @Field("ngaycap") String ngaycap,
                                        @Field("ngaysinh") String ngaysinh,
                                        @Field("quoctich") String quoctich,
                                        @Field("gioitinh") int gioitinh);
        //    @Field("nhomtuoi") int nhomtuoi
        //Cập nhật khách
        @POST ("/quanghieu/admin/api/khach/edit.php")
        @FormUrlEncoded
        Call <ThanhVienModel> updateKhach( @Field("id") int id,
                                           @Field("fullname") String tenKhach,
                                           @Field("dienthoai") String dienthoai,
                                           @Field("cancuoc") String cancuoc,
                                           @Field("diachi") String diachi,
                                           @Field("ngaycap") String ngaycap,
                                           @Field("ngaysinh") String ngaysinh,
                                           @Field("quoctich") String quoctich,
                                           @Field("gioitinh") int gioitinh);
//    @Field("nhomtuoi") int nhomtuoi
        //Tìm kiếm khách
        @POST ("/quanghieu/admin/api/khach/search.php")
        @FormUrlEncoded
        Call <List<ThanhVienModel>> searchKhach( @Field("key") String key);
    // Quản lí phòng

}
