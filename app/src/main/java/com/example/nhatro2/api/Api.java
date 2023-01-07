package com.example.nhatro2.api;

import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.phong.PhongMultiModel;
import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.example.nhatro2.phong.PhongModel;
import com.example.nhatro2.tien_dien.TienDienModel;
import com.example.nhatro2.tien_nuoc.LichSuNuocModel;
import com.example.nhatro2.tien_nuoc.TienNuocModel;
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
//    String url = "http://172.16.1.71";
    String url = "http://192.168.1.190";
//    String url = "http://192.168.1.9";
    //Init
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-DD HH:mm:ss").create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    Api api = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(Api.class);

    @POST ("/nhatro/admin/api/auth/login.php")
    @FormUrlEncoded
    Call <ThanhVienModel> postLogin(@Field("username") String username,
                                    @Field("password") String password);

    // Phòng trống
    @GET ("/nhatro/admin/api/phong/list.php")
    Call<List<PhongModel>> getPhongList();
    // Phòng đặt
    @GET ("/nhatro/admin/api/phong/book.php")
    Call<List<PhongModel>> getBookRoomList();
    // Phòng thuê
    @GET ("/nhatro/admin/api/phong/rent.php")
    Call<List<PhongModel>> getRentRoomList();
    // Phòng chọn multi
    @POST ("/nhatro/admin/api/phong/multi_check.php")
    @FormUrlEncoded
    Call<List<PhongModel>> phongChecked(@Field("idPhong") String idPhong);
    // Cọc phòng multi
    @POST ("/nhatro/admin/api/phong/coc_multi.php")
    @FormUrlEncoded
    Call<PhongMultiModel> datCocPhong(@Field("ten") String ten, @Field("dienthoai") String dienthoai, @Field("tiencoc") String  tiencoc, @Field("phong") String phong);

    // Khách thuê
    @GET ("/nhatro/admin/api/khach/list.php")
    Call<List<ThanhVienModel>> getKhachList();

    //Thêm khách
    @POST ("/nhatro/admin/api/khach/add.php")
    @FormUrlEncoded
    Call <ThanhVienModel> themKhach(@Field("fullname") String tenKhach,
                                    @Field("dienthoai") String dienthoai,
                                    @Field("cancuoc") String cancuoc,
                                    @Field("diachi") String diachi,
                                    @Field("ngaycap") String ngaycap,
                                    @Field("ngaysinh") String ngaysinh,
                                    @Field("quoctich") String quoctich,
                                    @Field("gioitinh") int gioitinh,
                                    @Field("nhomtuoi") int nhomtuoi);

    //Cập nhật khách
    @POST ("/nhatro/admin/api/khach/edit.php")
    @FormUrlEncoded
    Call <ThanhVienModel> updateKhach( @Field("id") int id,
                                       @Field("fullname") String tenKhach,
                                       @Field("dienthoai") String dienthoai,
                                       @Field("cancuoc") String cancuoc,
                                       @Field("diachi") String diachi,
                                       @Field("ngaycap") String ngaycap,
                                       @Field("ngaysinh") String ngaysinh,
                                       @Field("quoctich") String quoctich,
                                       @Field("gioitinh") int gioitinh,
                                       @Field("nhomtuoi") int nhomtuoi);

    //Tìm kiếm khách
    @POST ("/nhatro/admin/api/khach/search.php")
    @FormUrlEncoded
    Call <List<ThanhVienModel>> searchKhach( @Field("key") String key);

//    @POST ("/nhatro/admin/api/phong/add.php")
//    @FormUrlEncoded
//    Call <POST> addPhong(@Field("tenphong") String tenphong,
//                         @Field("trangthai") int trangthai,
//                         @Field("vitri") int vitri);

    @POST ("/nhatro/admin/api/phong/edit.php")
    @FormUrlEncoded
    Call <PhongModel> editPhong(@Field("id") int id,
                          @Field("trangthai") int trangthai, @Field("trangthaipost") int trangthaipost ,
                          @Field("daidien") String daidien,
                          @Field("dienthoai") String dienthoai);
    // Thêm hợp đồng
    @POST ("/nhatro/admin/api/hop-dong/phong.php")
        @FormUrlEncoded
        Call <PhongModel> hopDongPhong(@Field("id") int id);

    //Dịch vụ
    @GET ("/nhatro/admin/api/dichvu/list.php")
    Call<List<DichVuModel>> getDichVuList();
    //Thêm Dịch vụ
    @POST("/nhatro/admin/api/dichvu/add.php")
    @FormUrlEncoded
    Call<DichVuModel> addThietBi(@Field("ten") String tenthietbi, @Field("gia") Integer giathietbi);
    // Get Thông tin dịch vụ
    @POST ("/nhatro/admin/api/dichvu/detail.php")
    @FormUrlEncoded
    Call <DichVuModel> detailDichVu (@Field("id") int id);
    // Cập nhật Thông tin dịch vụ
    @POST ("/nhatro/admin/api/dichvu/edit.php")
    @FormUrlEncoded
    Call <DichVuModel> editDichVu (@Field("id") int id,
                                   @Field("ten") String ten,
                                   @Field("gia") int gia);
    // Xóa dịch vụ
    @POST ("/nhatro/admin/api/dichvu/del.php")
    @FormUrlEncoded
    Call <DichVuModel> delDichVu (@Field("id") int id);

    // Tiền nước lựa chọn
    @POST ("/nhatro/admin/api/tien-nuoc/choose_month.php")
    @FormUrlEncoded
    Call <List<TienNuocModel>> chooseTime (@Field("month") int thang, @Field("year") int nam);

    // Tiền nước tháng hiện tại
    @GET ("/nhatro/admin/api/tien-nuoc/list.php")
    Call<List<TienNuocModel>> getTienNuoc();

    // Tìm kiếm phòng nước
    @POST ("/nhatro/admin/api/tien-nuoc/search.php")
    @FormUrlEncoded
    Call <List<TienNuocModel>> searchWater (@Field("key") String keyWater,@Field("month") int thang, @Field("year") int nam);

    // Tiền nước chi tiết
    @POST ("/nhatro/admin/api/tien-nuoc/detail.php")
    @FormUrlEncoded
    Call <TienNuocModel> detailWater (@Field("ten") String ten, @Field("month") int thang, @Field("year") int nam);

    // Danh sách lịch sử số nước dùng
    @POST("/nhatro/admin/api/tien-nuoc/hisory.php")
    @FormUrlEncoded
    Call <List<LichSuNuocModel>> historyWater(@Field("phong") String ten, @Field("month") int month, @Field("year") int year);

    // Tiền điện lựa chọn
    @POST ("/nhatro/admin/api/tien-dien/choose_month.php")
    @FormUrlEncoded
    Call <List<TienDienModel>> chooseTimeElectric (@Field("month") int thang, @Field("year") int nam);

    // Tiền điện tháng hiện tại
    @GET ("/nhatro/admin/api/tien-dien/list.php")
    Call<List<TienDienModel>> getTienDien();

    // Tìm kiếm phòng điện
    @POST ("/nhatro/admin/api/tien-dien/search.php")
    @FormUrlEncoded
    Call <List<TienDienModel>> searchElectric (@Field("key") String keyElectric,@Field("month") int thang, @Field("year") int nam);

    // Tiền điện chi tiết
    @POST ("/nhatro/admin/api/tien-dien/detail.php")
    @FormUrlEncoded
    Call <TienDienModel> detailElectric (@Field("ten") int ten, @Field("month") int thang, @Field("year") int nam);


}
