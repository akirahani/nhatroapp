package com.example.nhatro2.hop_dong;

import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.phong.PhongModel;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import java.util.List;

public class HopDongModel {
    private int id;
    private int phong;
    private List<ThanhVienModel> khach;
    private List<DichVuModel> thietbi;
    private String ngayKetThuc;

    public HopDongModel(int id, int phong, List<ThanhVienModel> khach, List<DichVuModel> thietbi, String ngayKetThuc) {
        this.id = id;
        this.phong = phong;
        this.khach = khach;
        this.thietbi = thietbi;
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getPhong() {
        return phong;
    }

    public void setPhong(int phong) {
        this.phong = phong;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
