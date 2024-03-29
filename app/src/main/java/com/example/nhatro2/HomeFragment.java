package com.example.nhatro2;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.nhatro2.api.ApiQH;
import com.example.nhatro2.bat_bien.BatBien;
import com.example.nhatro2.chi_khac.ChiKhac;
import com.example.nhatro2.doi_thiet_bi.DoiThietBi;
import com.example.nhatro2.dong_tien.DongTien;
import com.example.nhatro2.hop_dong.HopDong;
import com.example.nhatro2.kha_bien.KhaBien;
import com.example.nhatro2.quanlychung.ChungAdapter;
import com.example.nhatro2.quanlychung.ChungModel;
import com.example.nhatro2.quy_tien_mat.QuyTienModel;
import com.example.nhatro2.tai_khoan.TaiKhoanModel;
import com.example.nhatro2.thanhvien.KhachTro;
import com.example.nhatro2.thay_cong_to.CongToDien;
import com.example.nhatro2.thay_cong_to.CongToNuoc;
import com.example.nhatro2.thong_ke.ThongKe;
import com.example.nhatro2.thu_khac.ThuKhac;
import com.example.nhatro2.tien_coc.TienCocAdd;
import com.example.nhatro2.tien_dien.TienDien;
import com.example.nhatro2.tien_nuoc.TienNuoc;
import com.example.nhatro2.uu_dai.UuDai;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    List<ChungModel> chung = new ArrayList<>();
    List<TaiKhoanModel> taiKhoan = new ArrayList<>();
    RecyclerView quanLyChung,quanLyThanhVien;
    TextView tenThanhVien,tenUser,xemThongKe;
    Toolbar header;
    ImageView thoat, menuDanhMuc;
    RelativeLayout rowWater, rowElectric, rowBatBien, rowKhaBien, rowThuKhac, rowChiKhac;
    SharedPreferences shp;
    SharedPreferences.Editor shpEdit;
    DrawerLayout mDrawerLayout;
    LinearLayout rightToolbar;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView doanhThuThangHienTai;
        // Tên người đăng nhập
        tenUser = view.findViewById(R.id.tenUser);
        shp = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String tenUserText =shp.getString("tenThanhVien","");
        tenUser.setText(tenUserText);
        // Nút thoát
        rightToolbar = view.findViewById(R.id.rightToolbar);
        rightToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(Html.fromHtml("<font color='#71a6d5'>Thông báo!</font>")).setMessage(Html.fromHtml("<font color='#71a6d5'>Bạn có thực sự muốn thoát ?</font>"));
                builder.setCancelable(true);
                builder.setIcon(R.drawable.alert_bottom);
                //check
                builder.setPositiveButton(Html.fromHtml("<font color='#71a6d5'>Có</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(),"Thoát", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        shp = view.getContext().getSharedPreferences("user", MODE_PRIVATE);
                        shp.edit().clear().commit();
//                        view.getContext();
                    }
                });
                // NO
                builder.setNegativeButton(Html.fromHtml("<font color='#71a6d5'>Không</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(),"Ở lại", Toast.LENGTH_SHORT).show();
                        //  Cancel
                        dialog.cancel();
                    }
                });
                // show alert
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        //QL chung
        quanLyChung = view.findViewById(R.id.quanLyChung);
        quanLyChung.setLayoutManager(new GridLayoutManager(getContext(), 4));
        quanLyChung.hasFixedSize();
        quanLyChung.setNestedScrollingEnabled(false);

        chung.add(new ChungModel(R.drawable.dichvu,"Dịch vụ", "dichvu"));
        chung.add(new ChungModel(R.drawable.khachtro,"TT khách trọ", "khachtro"));
        chung.add(new ChungModel(R.drawable.phongtro,"TT phòng trọ", "phong"));
        chung.add(new ChungModel(R.drawable.hopdong,"Hợp đồng", "hopdong"));
        chung.add(new ChungModel(R.drawable.quytien,"Quỹ tiền", "quytien"));
        chung.add(new ChungModel(R.drawable.tiencoc,"Tiền cọc", "tiencoc"));
        chung.add(new ChungModel(R.drawable.khoanthuchi,"Tiền phòng", "khoanthuchi"));
        chung.add(new ChungModel(R.drawable.coupons,"Ưu đãi", "uudai"));

        quanLyChung.setAdapter(new ChungAdapter(getContext(),chung));

        //QL Thanh vien
//        taiKhoan.add(new TaiKhoanModel(R.drawable.nhom,"Nhóm","nhom"));
//        taiKhoan.add(new TaiKhoanModel(R.drawable.thanhvien,"Thành viên","thanhvien"));
//        taiKhoan.add(new TaiKhoanModel(R.drawable.phanquyen,"Phân quyền","phanquyen"));
//        taiKhoan.add(new TaiKhoanModel(R.drawable.trang,"Trang","trang"));

//        quanLyThanhVien = view.findViewById(R.id.quanLyTaiKhoan);
//        quanLyThanhVien.setLayoutManager(new GridLayoutManager(getContext(),4));
//        quanLyThanhVien.hasFixedSize();
//        quanLyThanhVien.setNestedScrollingEnabled(false);
//        quanLyThanhVien.setAdapter(new TaiKhoanAdapter(getContext(),taiKhoan));

        rowWater = view.findViewById(R.id.rowWater);
        rowElectric = view.findViewById(R.id.rowElectric);
        rowThuKhac = view.findViewById(R.id.rowThuKhac);

        rowWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TienNuoc.class);
                startActivity(intent);
            }
        });

        rowElectric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TienDien.class);
                startActivity(intent);
            }
        });

        rowThuKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ThuKhac.class);
                startActivity(intent);
            }
        });

        // Khoản chi
        rowBatBien = view.findViewById(R.id.rowBatBien);
        rowKhaBien = view.findViewById(R.id.rowKhaBien);
          // Bất biến
            rowBatBien.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), BatBien.class);
                    startActivity(intent);
                }
            });
          // Khả biến
          rowKhaBien.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(getContext(), KhaBien.class);
                  startActivity(intent);
              }
          });
          // Chi khác
          rowChiKhac = view.findViewById(R.id.rowChiKhac);
          rowChiKhac.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(getContext(), ChiKhac.class);
                  startActivity(intent);
              }
          });


        xemThongKe = view.findViewById(R.id.xemThongKe);
        xemThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ThongKe.class);
                startActivity(intent);
            }
        });

        menuDanhMuc = view.findViewById(R.id.menuDanhMuc);
        mDrawerLayout = view.findViewById(R.id.drawer_layout_home_fragment);

        menuDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.khach_tro:
                        Intent khachTro = new Intent(view.getContext(), KhachTro.class);
                        startActivity(khachTro);
                        return true;
                    case R.id.dat_coc:
                        Intent datCoc = new Intent(view.getContext(), TienCocAdd.class);
                        startActivity(datCoc);
                        return true;
                    case R.id.thanh_toan:
                        Intent thanhToan = new Intent(view.getContext(), DongTien.class);
                        startActivity(thanhToan);
                        return true;
                    case R.id.hop_dong:
                        Intent hopDong = new Intent(view.getContext(), HopDong.class);
                        startActivity(hopDong);
                        return true;
                    case R.id.thay_cong_to_nuoc:
                        Intent thay_cong_to_nuoc = new Intent(view.getContext(), CongToNuoc.class);
                        startActivity(thay_cong_to_nuoc);
                        return true;
                    case R.id.thay_cong_to_dien:
                        Intent thay_cong_to_dien = new Intent(view.getContext(), CongToDien.class);
                        startActivity(thay_cong_to_dien);
                        return true;
                    case R.id.doi_thiet_bi:
                        Intent doi_thiet_bi = new Intent(view.getContext(), DoiThietBi.class);
                        startActivity(doi_thiet_bi);
                        return true;


                }
                return true;
            }
        });

        doanhThuThangHienTai = view.findViewById(R.id.doanhThuThangHienTai);
//        ApiQH.apiQH.getQuyThu().enqueue(new Callback<List<QuyThuModel>>() {
//            @Override
//            public void onResponse(Call<List<QuyThuModel>> call, Response<List<QuyThuModel>> response) {
//                List<QuyThuModel> listQuyThuFinal = response.body();
//
//                ArrayList<Integer> arrGiaTri = new ArrayList<>();
//                for(int i = listQuyThuFinal.size() -1; i >= 0 ;i--){
//                    arrGiaTri.add(i);
//                }
//                String doanhThuHienTaiText = listQuyThuFinal.get(arrGiaTri.get(0)).getTien();
//                doanhThuThangHienTai.setText(doanhThuHienTaiText+"đ");
//            }
//
//            @Override
//            public void onFailure(Call<List<QuyThuModel>> call, Throwable t) {
//
//            }
//        });

        ApiQH.apiQH.getQuyTien().enqueue(new Callback<QuyTienModel>() {
            @Override
            public void onResponse(Call<QuyTienModel> call, Response<QuyTienModel> response) {
                QuyTienModel listQuyTien = response.body();
                String doanhThuHienTaiText = listQuyTien.getDoanhthu();
                doanhThuThangHienTai.setText(doanhThuHienTaiText+"đ");
            }

            @Override
            public void onFailure(Call<QuyTienModel> call, Throwable t) {

            }
        });


        return view;
    }
}