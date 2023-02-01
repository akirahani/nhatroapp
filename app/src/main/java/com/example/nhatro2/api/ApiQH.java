package com.example.nhatro2.api;

import com.example.nhatro2.bat_bien.BatBienModel;
import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.dong_tien.ChonPhongModel;
import com.example.nhatro2.dong_tien.LichSuDongTienModel;
import com.example.nhatro2.dong_tien.ThanhToanModel;
import com.example.nhatro2.hop_dong.HopDongModel;
import com.example.nhatro2.hop_dong.ListKhachChonModel;
import com.example.nhatro2.kha_bien.KhaBienModel;
import com.example.nhatro2.nhom.phong.PhongModel;
import com.example.nhatro2.nhom.phong.PhongMultiModel;
import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.example.nhatro2.tien_dien.LichSuDienModel;
import com.example.nhatro2.tien_dien.TienDienModel;
import com.example.nhatro2.tien_nuoc.LichSuNuocModel;
import com.example.nhatro2.tien_nuoc.TienNuocModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
//    String url = "http://192.168.1.190";
    String url = "http://172.16.1.71";
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
    // Tìm kiếm khách
    @POST ("/quanghieu/admin/api/hop-dong/search_khach.php")
    @FormUrlEncoded
    Call <List<ThanhVienModel>> searchKhachChon( @Field("key") String key,@Field("daidien") int daidien );
    //
    @POST("/quanghieu/admin/api/hop-dong/khach_list.php")
    @FormUrlEncoded
    Call<List<ThanhVienModel>> getKhachListHopDongChon(@Field("daidien") int idDaiDien);
    // List còn hiệu lực
    @GET("/quanghieu/admin/api/hop-dong/con-hieu-luc.php")
    Call<List<HopDongModel>> getConHieuLuc();
    // List hết hiệu lực
    @GET("/quanghieu/admin/api/hop-dong/het-hieu-luc.php")
    Call<List<HopDongModel>> getHetHieuLuc();
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
    // Chi tiết
    // thiết bị
    @POST("/quanghieu/admin/api/hop-dong/thiet-bi-detail.php")
    @FormUrlEncoded
    Call <List<DichVuModel>> getListEquipmentDetail(@Field("thietbi") String thietbi);
    // người thuê
    @POST("/quanghieu/admin/api/hop-dong/khach-detail.php")
    @FormUrlEncoded
    Call <List<ThanhVienModel>> getListKhachDetail(@Field("khach") String khach);
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
    // Quản lý phòng
    // Phòng trống
    @GET ("/quanghieu/admin/api/phong/list.php")
    Call<List<PhongModel>> getPhongList();
    // Phòng đặt
    @GET ("/quanghieu/admin/api/phong/book.php")
    Call<List<PhongModel>> getBookRoomList();
    // Phòng thuê
    @GET ("/quanghieu/admin/api/phong/rent.php")
    Call<List<PhongModel>> getRentRoomList();
    // Phòng chọn multi
    @POST ("/quanghieu/admin/api/phong/multi_check.php")
    @FormUrlEncoded
    Call<List<PhongModel>> phongChecked(@Field("idPhong") String idPhong);
    // Cọc phòng multi
    @POST ("/quanghieu/admin/api/phong/coc_multi.php")
    @FormUrlEncoded
    Call<PhongMultiModel> datCocPhong(@Field("ten") String ten,
                                      @Field("dienthoai") String dienthoai,
                                      @Field("tiencoc") String  tiencoc,
                                      @Field("phong") String phong);
    // Sua phong
    @POST ("/quanghieu/admin/api/phong/edit.php")
    @FormUrlEncoded
    Call <PhongModel> editPhong(@Field("id") int id,
                                @Field("trangthai") int trangthai, @Field("trangthaipost") int trangthaipost ,
                                @Field("daidien") String daidien,
                                @Field("dienthoai") String dienthoai);
    // Quản lý hợp đồng
    // Thêm khách vào hợp đồng
    @POST ("/quanghieu/admin/api/hop-dong/them_khach.php")
    @FormUrlEncoded
    Call <List<ListKhachChonModel>> addKhachHopDong(@Field("id") String id);


    // Thêm hợp đồng
    @POST ("/quanghieu/admin/api/hop-dong/phong.php")
    @FormUrlEncoded
    Call <PhongModel> hopDongPhong(@Field("id") int id);
    // Chi phí
    // Bất biến
    // Danh sách tháng hiện tại
    @GET ("/quanghieu/admin/api/chi-phi/bat-bien/list.php")
    Call <List<BatBienModel>> getBatBien();
    // Chọn tháng hiển thị danh sách
    @POST ("quanghieu/admin/api/chi-phi/bat-bien/choose-month.php")
    @FormUrlEncoded
    Call <List<BatBienModel>> chooseTimeBatBien(@Field("month") int month, @Field("year") int year);
    // Thêm chi phí bất biến
    @POST ("quanghieu/admin/api/chi-phi/bat-bien/add.php")
    @FormUrlEncoded
    Call <BatBienModel> addBatBien(@Field("lydo") String lydo, @Field("tien") int tien, @Field("hinhthuc") int hinhthuc);
    // Khả biến
    // Danh sách tháng hiện tại
    @GET ("/quanghieu/admin/api/chi-phi/kha-bien/list.php")
    Call <List<KhaBienModel>> getKhaBien();
    // Chọn tháng hiển thị danh sách
    @POST ("quanghieu/admin/api/chi-phi/kha-bien/choose-month.php")
    @FormUrlEncoded
    Call <List<KhaBienModel>> chooseTimeKhaBien(@Field("month") int month, @Field("year") int year);
    // Thêm chi phí khả biến
    @POST ("quanghieu/admin/api/chi-phi/kha-bien/add.php")
    @FormUrlEncoded
    Call <KhaBienModel> addKhaBien(@Field("lydo") String lydo, @Field("tien") int tien, @Field("hinhthuc") int hinhthuc);
    // Tiền điện
    // Tiền điện lựa chọn
    @POST ("/quanghieu/admin/api/tien-dien/choose_month.php")
    @FormUrlEncoded
    Call <List<TienDienModel>> chooseTimeElectric (@Field("month") int thang, @Field("year") int nam);

    // Tiền điện tháng hiện tại
    @GET ("/quanghieu/admin/api/tien-dien/list.php")
    Call<List<TienDienModel>> getTienDien();

    // Tìm kiếm phòng điện
    @POST ("/quanghieu/admin/api/tien-dien/search.php")
    @FormUrlEncoded
    Call <List<TienDienModel>> searchElectric (@Field("key") String keyElectric,@Field("month") int thang, @Field("year") int nam);

    // Tiền điện chi tiết
    @POST ("/quanghieu/admin/api/tien-dien/detail.php")
    @FormUrlEncoded
    Call <TienDienModel> detailElectric (@Field("ten") String ten, @Field("month") int thang, @Field("year") int nam);


    // Danh sách lịch sử số nước dùng
    @POST("/quanghieu/admin/api/tien-dien/history.php")
    @FormUrlEncoded
    Call <List<LichSuDienModel>> historyElectric(@Field("phong") String ten, @Field("month") int month, @Field("year") int year);

    //Cập nhật số nước
    @POST("/quanghieu/admin/api/tien-dien/update.php")
    @FormUrlEncoded
    Call <TienDienModel> updateElectric(@Field("phong") String ten, @Field("month") int month, @Field("year") int year,@Field("sodau") int sodau, @Field("socuoi") int socuoi);
    // Tiền nước
    // Tiền nước lựa chọn
    @POST ("/quanghieu/admin/api/tien-nuoc/choose_month.php")
    @FormUrlEncoded
    Call <List<TienNuocModel>> chooseTime (@Field("month") int thang, @Field("year") int nam);

    // Tiền nước tháng hiện tại
    @GET ("/quanghieu/admin/api/tien-nuoc/list.php")
    Call<List<TienNuocModel>> getTienNuoc();

    // Tìm kiếm phòng nước
    @POST ("/quanghieu/admin/api/tien-nuoc/search.php")
    @FormUrlEncoded
    Call <List<TienNuocModel>> searchWater (@Field("key") String keyWater,@Field("month") int thang, @Field("year") int nam);

    // Tiền nước chi tiết
    @POST ("/quanghieu/admin/api/tien-nuoc/detail.php")
    @FormUrlEncoded
    Call <TienNuocModel> detailWater (@Field("ten") String ten, @Field("month") int thang, @Field("year") int nam);

    // Danh sách lịch sử số nước dùng
    @POST("/quanghieu/admin/api/tien-nuoc/history.php")
    @FormUrlEncoded
    Call <List<LichSuNuocModel>> historyWater(@Field("phong") String ten, @Field("month") int month, @Field("year") int year);

    //Cập nhật số nước
    @POST("/quanghieu/admin/api/tien-nuoc/update.php")
    @FormUrlEncoded
    Call <TienNuocModel> updateWater(@Field("phong") String ten, @Field("month") int month, @Field("year") int year,@Field("sodau") int sodau, @Field("socuoi") int socuoi);

    // Thu tien phong
        @GET("/quanghieu/admin/api/thu-tien/list_phong.php")
        Call <List<PhongModel>> listPhongTien();
        // Chọn phòng lấy thông tin đóng tiền
        @POST("/quanghieu/admin/api/thu-tien/choose_room.php")
        @FormUrlEncoded
        Call <ChonPhongModel> getTienDongList(@Field("phong") int phong);
        // Lấy thông tin khách thuê phòng
        @POST("/quanghieu/admin/api/thu-tien/list_khach.php")
        @FormUrlEncoded
        Call <List<ThanhVienModel>> getKhachPhongTien(@Field("idKhach") String idKhach);
        // Lấy thông tin thiết bị được thuê
        @POST("/quanghieu/admin/api/thu-tien/list_thiet_bi.php")
        @FormUrlEncoded
        Call <List<DichVuModel>> getThietBiPhongTien(@Field("idThietBi") String idThietBi);
        // Lịch sử nộp tiền phòng
        @POST("/quanghieu/admin/api/thu-tien/lich_su.php")
        @FormUrlEncoded
        Call <List<LichSuDongTienModel>> getHistoryPay(@Field("idHistory") String idHistory);
        // Thanh toán
        @POST("/quanghieu/admin/api/thu-tien/thanh_toan.php")
        @FormUrlEncoded
        Call <ThanhToanModel> postMoney(@Field("khutroid") int khuTroId,
                                        @Field("chuphong") int chuPhong,
                                        @Field("phong") String tenPhong,
                                        @Field("phuongthuc") int phuongThuc,
                                        @Field("thanhtoan") String thanhToan);
}
