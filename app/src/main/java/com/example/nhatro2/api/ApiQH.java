package com.example.nhatro2.api;

import com.example.nhatro2.bat_bien.BatBienModel;
import com.example.nhatro2.chi_khac.ChiKhacModel;
import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.doi_thiet_bi.ChuyenPhongThietBiModel;
import com.example.nhatro2.doi_thiet_bi.DoiThietBi;
import com.example.nhatro2.doi_thiet_bi.DoiThietBiModel;
import com.example.nhatro2.dong_tien.ApDungUuDaiModel;
import com.example.nhatro2.dong_tien.ChonPhongModel;
import com.example.nhatro2.dong_tien.ChuyenPhongModel;
import com.example.nhatro2.dong_tien.LichSuDongTienModel;
import com.example.nhatro2.dong_tien.ThanhToanModel;
import com.example.nhatro2.hop_dong.HopDongModel;
import com.example.nhatro2.hop_dong.ListKhachChonModel;
import com.example.nhatro2.kha_bien.KhaBienModel;
import com.example.nhatro2.phong.PhongModel;
import com.example.nhatro2.phong.PhongMultiModel;
import com.example.nhatro2.quy_tien_mat.QuyChiModel;
import com.example.nhatro2.quy_tien_mat.QuyThuModel;
import com.example.nhatro2.quy_tien_mat.QuyTienModel;
import com.example.nhatro2.thanhvien.ThanhVienModel;
import com.example.nhatro2.thay_cong_to.CongToDienModel;
import com.example.nhatro2.thay_cong_to.CongToNuocModel;
import com.example.nhatro2.thong_ke.TongQuanChartModel;
import com.example.nhatro2.thu_khac.ThuKhacModel;
import com.example.nhatro2.tien_coc.TienCocModel;
import com.example.nhatro2.tien_dien.LichSuDienModel;
import com.example.nhatro2.tien_dien.TienDienModel;
import com.example.nhatro2.tien_nuoc.LichSuNuocModel;
import com.example.nhatro2.tien_nuoc.TienNuocModel;
import com.example.nhatro2.tra_phong.ChiTienTraPhongModel;
import com.example.nhatro2.tra_phong.DienTraPhongModel;
import com.example.nhatro2.tra_phong.NuocTraPhongModel;
import com.example.nhatro2.tra_phong.ThanhVienTraPhongModel;
import com.example.nhatro2.tra_phong.ThietBiTraPhongModel;
import com.example.nhatro2.tra_phong.TienPhongTraModel;
import com.example.nhatro2.tra_phong.TraPhongModel;
import com.example.nhatro2.uu_dai.UuDaiModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiQH {


//    String url = "http://192.168.1.190/quanghieu/";
    String url = "http://172.16.1.71/quanghieu/";
    //    String url = "https://nhatroquanghieu.com";
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-DD HH:mm:ss").create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    ApiQH apiQH = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient).build()
            .create(ApiQH.class);

    @POST("admin/api/auth/login.php")
    @FormUrlEncoded
    Call<ThanhVienModel> postLogin(@Field("username") String username,
                                   @Field("password") String password);

    // Hợp đồng
    // Tìm kiếm khách
    @POST ("admin/api/hop-dong/search_khach.php")
    @FormUrlEncoded
    Call <List<ThanhVienModel>> searchKhachChon( @Field("key") String key,@Field("daidien") int daidien );
    //
    @POST("admin/api/hop-dong/khach_list.php")
    @FormUrlEncoded
    Call<List<ThanhVienModel>> getKhachListHopDongChon(@Field("daidien") int idDaiDien);
    // List còn hiệu lực
    @GET("admin/api/hop-dong/con-hieu-luc.php")
    Call<List<HopDongModel>> getConHieuLuc();
    // List hết hiệu lực
    @GET("admin/api/hop-dong/het-hieu-luc.php")
    Call<List<HopDongModel>> getHetHieuLuc();
    //Thêm
    @POST("admin/api/hop-dong/add.php")
    @FormUrlEncoded
    Call<HopDongModel> addContract(@Field("thietbi") String thietbi,
                                   @Field("khach") int khach,
                                   @Field("daidienophong") int idDaiDien,
                                   @Field("ketthuc") String ketthuc,
                                   @Field("ghichu") String ghichu,
                                   @Field("coc") String coc,
                                   @Field("phuongthuccoc") int phuongThuCcoc,
                                   @Field("phong") String phong,
                                   @Field("phuongthucphong") int phuongThuPhong,
                                   @Field("person") String person,
                                   @Field("roomhd") String roomhd);
    // Chi tiết
    // thiết bị
    @POST("admin/api/hop-dong/thiet-bi-detail.php")
    @FormUrlEncoded
    Call <List<DichVuModel>> getListEquipmentDetail(@Field("thietbi") String thietbi);
    // người thuê
    @POST("admin/api/hop-dong/khach-detail.php")
    @FormUrlEncoded
    Call <List<ThanhVienModel>> getListKhachDetail(@Field("khach") String khach);
    // Thiết bị
    // Danh sách
    @GET("admin/api/thiet-bi/list.php")
    Call<List<DichVuModel>> getDichVuList();
    //Thêm Dịch vụ
    @POST("admin/api/thiet-bi/add.php")
    @FormUrlEncoded
    Call<DichVuModel> addThietBi(@Field("ten") String tenthietbi, @Field("gia") Integer giathietbi);
    // Get Thông tin dịch vụ
    @POST ("admin/api/thiet-bi/detail.php")
    @FormUrlEncoded
    Call <DichVuModel> detailDichVu (@Field("id") int id);
    // Cập nhật Thông tin dịch vụ
    @POST ("admin/api/thiet-bi/edit.php")
    @FormUrlEncoded
    Call <DichVuModel> editDichVu (@Field("id") int id,
                                   @Field("ten") String ten,
                                   @Field("gia") int gia);
    // Xóa dịch vụ
    @POST ("admin/api/thiet-bi/del.php")
    @FormUrlEncoded
    Call <DichVuModel> delDichVu (@Field("id") int id);
    // Quản lí khách thuê
    // Khách thuê
    @GET ("admin/api/khach/list.php")
    Call<List<ThanhVienModel>> getKhachList();

    //Thêm khách
    @POST ("admin/api/khach/add.php")
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
    @POST ("admin/api/khach/edit.php")
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
    @POST ("admin/api/khach/search.php")
    @FormUrlEncoded
    Call <List<ThanhVienModel>> searchKhach( @Field("key") String key);
    // Quản lý phòng
    // Phòng trống
    @GET ("admin/api/phong/list.php")
    Call<List<PhongModel>> getPhongList();
    // Phòng đặt
    @GET ("admin/api/phong/book.php")
    Call<List<PhongModel>> getBookRoomList();
    // Phòng thuê
    @GET ("admin/api/phong/rent.php")
    Call<List<PhongModel>> getRentRoomList();
    // Phòng chọn multi
    @POST ("admin/api/phong/multi_check.php")
    @FormUrlEncoded
    Call<List<PhongModel>> phongChecked(@Field("idPhong") String idPhong);
    // Cọc phòng multi
    @POST ("admin/api/phong/coc_multi.php")
    @FormUrlEncoded
    Call<PhongMultiModel> datCocPhong(@Field("ten") String ten,
                                      @Field("dienthoai") String dienthoai,
                                      @Field("tiencoc") String  tiencoc,
                                      @Field("phong") String phong);
    // Sua phong
    @POST ("admin/api/phong/edit.php")
    @FormUrlEncoded
    Call <PhongModel> editPhong(@Field("id") int id,
                                @Field("trangthai") int trangthai, @Field("trangthaipost") int trangthaipost ,
                                @Field("daidien") String daidien,
                                @Field("dienthoai") String dienthoai);
    // Quản lý hợp đồng
    // Thêm khách vào hợp đồng
    @POST ("admin/api/hop-dong/them_khach.php")
    @FormUrlEncoded
    Call <List<ListKhachChonModel>> addKhachHopDong(@Field("id") String id);


    // Thêm hợp đồng
    @POST ("admin/api/hop-dong/phong.php")
    @FormUrlEncoded
    Call <PhongModel> hopDongPhong(@Field("id") int id);
    // Chi phí
    // Bất biến
    // Danh sách tháng hiện tại
    @GET ("admin/api/chi-phi/bat-bien/list.php")
    Call <List<BatBienModel>> getBatBien();
    // Chọn tháng hiển thị danh sách
    @POST ("admin/api/chi-phi/bat-bien/choose-month.php")
    @FormUrlEncoded
    Call <List<BatBienModel>> chooseTimeBatBien(@Field("month") int month, @Field("year") int year);
    // Thêm chi phí bất biến
    @POST ("admin/api/chi-phi/bat-bien/add.php")
    @FormUrlEncoded
    Call <BatBienModel> addBatBien(@Field("idThanhVien") int idThanhVien,@Field("lydo") String lydo, @Field("tien") int tien, @Field("hinhthuc") int hinhthuc);
    // Khả biến
    // Danh sách tháng hiện tại
    @GET ("admin/api/chi-phi/kha-bien/list.php")
    Call <List<KhaBienModel>> getKhaBien();
    // Chọn tháng hiển thị danh sách
    @POST ("admin/api/chi-phi/kha-bien/choose-month.php")
    @FormUrlEncoded
    Call <List<KhaBienModel>> chooseTimeKhaBien(@Field("month") int month, @Field("year") int year);
    // Thêm chi phí khả biến
    @POST ("admin/api/chi-phi/kha-bien/add.php")
    @FormUrlEncoded
    Call <KhaBienModel> addKhaBien(@Field("idThanhVien") int idThanhVien,@Field("lydo") String lydo, @Field("tien") int tien, @Field("hinhthuc") int hinhthuc);
    // Chi khác
    @GET ("admin/api/chi-phi/chi-khac/list.php")
    Call <List<ChiKhacModel>> getChiKhac();
    // Chọn tháng hiển thị danh sách
    @POST ("admin/api/chi-phi/chi-khac/choose-month.php")
    @FormUrlEncoded
    Call <List<ChiKhacModel>> chooseTimeChiKhac(@Field("month") int month, @Field("year") int year);
    // Thêm chi phí khác
    @POST ("admin/api/chi-phi/chi-khac/add.php")
    @FormUrlEncoded
    Call <ChiKhacModel> addChiKhac(@Field("idThanhVien") int idThanhVien,@Field("lydo") String lydo, @Field("tien") int tien, @Field("hinhthuc") int hinhthuc);

    // Tiền điện
    // Tiền điện lựa chọn
    @POST ("admin/api/tien-dien/choose_month.php")
    @FormUrlEncoded
    Call <List<TienDienModel>> chooseTimeElectric (@Field("month") int thang, @Field("year") int nam);

    // Tiền điện tháng hiện tại
    @GET ("admin/api/tien-dien/list.php")
    Call<List<TienDienModel>> getTienDien();

    // Tìm kiếm phòng điện
    @POST ("admin/api/tien-dien/search.php")
    @FormUrlEncoded
    Call <List<TienDienModel>> searchElectric (@Field("key") String keyElectric,@Field("month") int thang, @Field("year") int nam);

    // Tiền điện chi tiết
    @POST ("admin/api/tien-dien/detail.php")
    @FormUrlEncoded
    Call <TienDienModel> detailElectric (@Field("ten") String ten, @Field("month") int thang, @Field("year") int nam);


    // Danh sách lịch sử số nước dùng
    @POST("admin/api/tien-dien/history.php")
    @FormUrlEncoded
    Call <List<LichSuDienModel>> historyElectric(@Field("phong") String ten, @Field("month") int month, @Field("year") int year);

    //Cập nhật số nước
    @POST("admin/api/tien-dien/update.php")
    @FormUrlEncoded
    Call <TienDienModel> updateElectric(@Field("idThanhVien") int idThanhVien,@Field("phong") String ten, @Field("month") int month, @Field("year") int year,@Field("sodau") int sodau, @Field("socuoi") int socuoi);
    // Tiền nước
    // Tiền nước lựa chọn
    @POST ("admin/api/tien-nuoc/choose_month.php")
    @FormUrlEncoded
    Call <List<TienNuocModel>> chooseTime (@Field("month") int thang, @Field("year") int nam);

    // Tiền nước tháng hiện tại
    @GET ("admin/api/tien-nuoc/list.php")
    Call<List<TienNuocModel>> getTienNuoc();

    // Tìm kiếm phòng nước
    @POST ("admin/api/tien-nuoc/search.php")
    @FormUrlEncoded
    Call <List<TienNuocModel>> searchWater (@Field("key") String keyWater,@Field("month") int thang, @Field("year") int nam);

    // Tiền nước chi tiết
    @POST ("admin/api/tien-nuoc/detail.php")
    @FormUrlEncoded
    Call <TienNuocModel> detailWater (@Field("ten") String ten, @Field("month") int thang, @Field("year") int nam);

    // Danh sách lịch sử số nước dùng
    @POST("admin/api/tien-nuoc/history.php")
    @FormUrlEncoded
    Call <List<LichSuNuocModel>> historyWater(@Field("phong") String ten, @Field("month") int month, @Field("year") int year);

    //Cập nhật số nước
    @POST("admin/api/tien-nuoc/update.php")
    @FormUrlEncoded
    Call <TienNuocModel> updateWater(@Field("idThanhVien") int idThanhVien,@Field("phong") String ten, @Field("month") int month, @Field("year") int year,@Field("sodau") int sodau, @Field("socuoi") int socuoi);

    // Thu tien phong
    @GET("admin/api/thu-tien/list_phong.php")
    Call <List<PhongModel>> listPhongTien();
    // Chọn phòng lấy thông tin đóng tiền
    @POST("admin/api/thu-tien/choose_room.php")
    @FormUrlEncoded
    Call <ChonPhongModel> getTienDongList(@Field("phong") int phong);
    // Lấy thông tin khách thuê phòng
    @POST("admin/api/thu-tien/list_khach.php")
    @FormUrlEncoded
    Call <List<ThanhVienModel>> getKhachPhongTien(@Field("idKhach") String idKhach);
    // Lấy thông tin thiết bị được thuê
    @POST("admin/api/thu-tien/list_thiet_bi.php")
    @FormUrlEncoded
    Call <List<DichVuModel>> getThietBiPhongTien(@Field("idThietBi") String idThietBi);
    // Lịch sử nộp tiền phòng
    @POST("admin/api/thu-tien/lich_su.php")
    @FormUrlEncoded
    Call <List<LichSuDongTienModel>> getHistoryPay(@Field("idHistory") String idHistory);
    // Thanh toán
    @POST("admin/api/thu-tien/thanh_toan.php")
    @FormUrlEncoded
    Call <ThanhToanModel> postMoney(@Field("khutroid") int khuTroId,
                                    @Field("chuphong") int chuPhong,
                                    @Field("phong") String tenPhong,
                                    @Field("phuongthuc") int phuongThuc,
                                    @Field("thanhtoan") String thanhToan);
    // Đóng cọc phòng tiền
    @POST("admin/api/thu-tien/thu_coc.php")
    @FormUrlEncoded
    Call <TienCocModel> postCoc(@Field("khutroid") int khuTroId,
                                @Field("chuphong") int chuPhong,
                                @Field("phong") String tenPhong,
                                @Field("phuongthuc") int phuongThuc,
                                @Field("thanhtoan") String thanhToan);
    @POST ("admin/api/thu-tien/tim_phong.php")
    @FormUrlEncoded
    Call <List<PhongModel>> searchPhongTien( @Field("key") String key);

    //ưu đãi update
    @POST ("admin/api/thu-tien/update_uu_dai_phong.php")
    @FormUrlEncoded
    Call <ApDungUuDaiModel> updateUuDaiPhong(@Field("khutroid") int khuTroId,
                                             @Field("chuphong") int chuPhong,
                                             @Field("phong") String tenPhong,
                                             @Field("uudai") int idUuDai,
                                             @Field("phuongthuc") int phuongThuc);

    @GET ("admin/api/thu-tien/uu_dai_active.php")
    Call <List<UuDaiModel>> getUuDaiActive();

    // Quỹ tiền
    @GET("admin/api/quy-tien/list.php")
    Call <QuyTienModel> getQuyTien();
    @GET("admin/api/quy-tien/quy_chi.php")
    Call <List<QuyChiModel>> getQuyChi();
    @GET("admin/api/quy-tien/quy_thu.php")
    Call <List<QuyThuModel>> getQuyThu();

    // Tiền cọc
    //
    @GET ("admin/api/tien-coc/list.php")
    Call <List<TienCocModel>> listCoc();
    //
    @POST ("admin/api/tien-coc/coc.php")
    @FormUrlEncoded
    Call <TienCocModel> themCoc(@Field("thanhvienid") int idThanhVien,
                                @Field("khach") int idKhach,
                                @Field("tenkhach") String tenKhach,
                                @Field("dienthoai") String dienthoai,
                                @Field("tiencoc") int tiencoc,
                                @Field("ghichu") String ghichu,
                                @Field("phuongthuccoc") int phuongthuc);
    // Chi tiết cọc
    @POST ("admin/api/tien-coc/xoa_coc.php")
    @FormUrlEncoded
    Call <TienCocModel> xoaCoc(@Field("id") int id);
    //
    @POST("admin/api/tien-coc/search_khach.php")
    @FormUrlEncoded
    Call <List<ThanhVienModel>> searchKhachCocChon(@Field("khach") String khach);
    //
    @GET("admin/api/tien-coc/list_khach.php")
    Call<List<ThanhVienModel>> getKhachCocList();

    // Ưu đãi
    @GET ("admin/api/uu-dai/list.php")
    Call <List<UuDaiModel>> getUuDai();
    // Thêm ưu đãi
    @POST("admin/api/uu-dai/add.php")
    @FormUrlEncoded
    Call <UuDaiModel> addUuDai(@Field("idThanhVien") int idThanhVienQuanLy,
                               @Field("tenUuDai") String tenUuDaiText,
                               @Field("soNgayUuDai") int soNgayUuDai,
                               @Field("apDung") int apDung);
    // Chi tiet, sua uu dai
    @POST("admin/api/uu-dai/edit.php")
    @FormUrlEncoded
    Call <UuDaiModel> editUuDai(@Field("idThanhVien") int idThanhVienQuanLy,
                                @Field("idUuDai") int idUuDai,
                                @Field("tenUuDai") String tenUuDaiText,
                                @Field("soNgayUuDai") int soNgayUuDai,
                                @Field("apDung") int apDung);

    // Thu khác
    @GET("admin/api/thu-khac/list.php")
    Call <List<ThuKhacModel>> getThuKhac();
    // Chọn time lấy danh sách thu ngoài
    @POST("admin/api/thu-khac/choose_month.php")
    @FormUrlEncoded
    Call <List<ThuKhacModel>> getThuKhacMonth(@Field("thang") int thang, @Field("nam") int nam);
    // Thêm thu khác
    @POST("admin/api/thu-khac/add.php")
    @FormUrlEncoded
    Call <ThuKhacModel> addThuKhac(@Field("idThanhVien") int idThanhVien,
                                   @Field("lydo") String lyDo,
                                   @Field("tien") int tien,
                                   @Field("checktien") int checkTien,
                                   @Field("phong") int phong);
    // Trả phòng
    //thông tin
    @POST("admin/api/tra-phong/list.php")
    @FormUrlEncoded
    Call <TraPhongModel> getThongTinTraPhong(@Field("ngay") String ngay,
                                             @Field("hopdong") int hopDong);
    @POST("admin/api/tra-phong/list_dien.php")
    @FormUrlEncoded
    Call <List<DienTraPhongModel>> getLichSuTienDienCanDong(@Field("idHistory") String idHistory);

    @POST("admin/api/tra-phong/list_nuoc.php")
    @FormUrlEncoded
    Call <List<NuocTraPhongModel>> getLichSuTienNuocCanDong(@Field("idHistory") String idHistory);

    @POST("admin/api/tra-phong/list_tien_thanh_vien.php")
    @FormUrlEncoded
    Call <List<ThanhVienTraPhongModel>> getTienThanhVienCanDong(@Field("ngay") String time, @Field("idHistory") String idHistory);

    @POST("admin/api/tra-phong/list_tien_thiet_bi.php")
    @FormUrlEncoded
    Call <List<ThietBiTraPhongModel>> getThietBiCanDong(@Field("ngay") String time,@Field("idHistory") String idHistory);

    @POST("admin/api/tra-phong/list_tien_phong.php")
    @FormUrlEncoded
    Call <List<TienPhongTraModel>> getTienPhongCanDong(@Field("phong") int idPhong,@Field("ngay") String time,@Field("idHistory") String idHistory);

    @POST("admin/api/tra-phong/chi_tra_phong.php")
    @FormUrlEncoded
    Call <ChiTienTraPhongModel> taoPhieuChiTraPhong(@Field("chuphong") int chuPhong,
                                                    @Field("tenphong") String tenPhong,
                                                    @Field("hopdong") int hopDong,
                                                    @Field("ngay") String time,
                                                    @Field("trangthai") String trangthai,
                                                    @Field("tienphong") int tienphong,
                                                    @Field("tiencoc") int tiencoc,
                                                    @Field("phuongthucphong") int phuongthucphong,
                                                    @Field("phuongthuccoc") int phuongthuccoc);

    // Chuyển phòng
    @GET ("admin/api/doi-phong/phong_trong_check.php")
    Call <List<PhongModel>> listPhongTrong();

    @POST("admin/api/doi-phong/list.php")
    @FormUrlEncoded
    Call <ChuyenPhongModel> chuyenPhong(@Field("doiphong") String phong, @Field("hopdong") int hopdong);

    // Thay công tơ điện
    @POST("admin/api/thay-cong-to/cong-to-dien.php")
    @FormUrlEncoded
    Call <CongToDienModel> congToDien(@Field("phong") String phong, @Field("chiso") String chiso);

    // Thay công tơ nước
    @POST("admin/api/thay-cong-to/cong-to-nuoc.php")
    @FormUrlEncoded
    Call <CongToNuocModel> congToNuoc(@Field("phong") String phong, @Field("chiso") String chiso);
    // Phòng thiết bị hiển thị
    @POST("admin/api/quan-li-thiet-bi/chon-phong.php")
    @FormUrlEncoded
    Call <DoiThietBiModel> getPhongThietBi(@Field("phong") String phong);

    //Thêm thiết bị
    @POST("admin/api/quan-li-thiet-bi/them-thiet-bi.php")
    @FormUrlEncoded
    Call <DoiThietBiModel> themPhongThietBi(@Field("phong") String phong,@Field("thietbi") int thietbi);

    //Chuyển thiết bị
    @POST("admin/api/quan-li-thiet-bi/chuyen-thiet-bi.php")
    @FormUrlEncoded
    Call <ChuyenPhongThietBiModel> chuyenPhongThietBi(@Field("thietbi") int thietbi, @Field("chuyenden") String chuyenden, @Field("chuyendi") String chuyendi);

    // Đổi mật khẩu
    @POST("admin/api/auth/change_passd.php")
    @FormUrlEncoded
    Call <ThanhVienModel> changePassd(@Field("idThanhVien") int idThanhVienQuanLy,@Field("passdold") String passdOldText, @Field("passdnew") String passdNewText);
}
