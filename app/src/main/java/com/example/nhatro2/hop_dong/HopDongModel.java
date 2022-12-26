package com.example.nhatro2.hop_dong;

import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.phong.PhongModel;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import java.util.List;

public class HopDongModel {
    private int id;
    private List<PhongModel> phong;
    private  List<ThanhVienModel> khach;
    private  List<DichVuModel> thietbi;
    private String ngayKetThuc;

    public HopDongModel(int id, List<PhongModel> phong, List<ThanhVienModel> khach, List<DichVuModel> thietbi, String ngayKetThuc) {
        this.id = id;
        this.phong = phong;
        this.khach = khach;
        this.thietbi = thietbi;
        this.ngayKetThuc = ngayKetThuc;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PhongModel> getPhong() {
        return phong;
    }

    public void setPhong(List<PhongModel> phong) {
        this.phong = phong;
    }

    public List<ThanhVienModel> getKhach() {
        return khach;
    }

    public void setKhach(List<ThanhVienModel> khach) {
        this.khach = khach;
    }

    public List<DichVuModel> getThietbi() {
        return thietbi;
    }

    public void setThietbi(List<DichVuModel> thietbi) {
        this.thietbi = thietbi;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

}
