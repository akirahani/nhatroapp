package com.example.nhatro2.hop_dong;

import com.example.nhatro2.dich_vu.DichVuModel;
import com.example.nhatro2.phong.PhongModel;
import com.example.nhatro2.thanhvien.ThanhVienModel;

import java.util.List;

public class HopDongModel {
    private int id;
    private  String chuphong;
    private String phong;
    private  int tienphong;
    private  int phuongthuctienphong;
    private  int tiencoc;
    private  int phuongthuctiencoc;
    private List<String> khach;
    private List<String> thietbi;
    private String ngayketthuc;
    private String ngaybatdau;
    private String ghichu;
    private int trangthai;

    public HopDongModel(int id, String chuphong, String phong, int tienphong, int phuongthuctienphong, int tiencoc, int phuongthuctiencoc, List<String> khach, List<String> thietbi, String ngayketthuc, String ngaybatdau, String ghichu, int trangthai) {
        this.id = id;
        this.chuphong = chuphong;
        this.phong = phong;
        this.tienphong = tienphong;
        this.phuongthuctienphong = phuongthuctienphong;
        this.tiencoc = tiencoc;
        this.phuongthuctiencoc = phuongthuctiencoc;
        this.khach = khach;
        this.thietbi = thietbi;
        this.ngayketthuc = ngayketthuc;
        this.ngaybatdau = ngaybatdau;
        this.ghichu = ghichu;
        this.trangthai = trangthai;
    }

    public List<String> getKhach() {
        return khach;
    }

    public void setKhach(List<String> khach) {
        this.khach = khach;
    }

    public List<String> getThietbi() {
        return thietbi;
    }

    public void setThietbi(List<String> thietbi) {
        this.thietbi = thietbi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getChuphong() {
        return chuphong;
    }

    public void setChuphong(String chuphong) {
        this.chuphong = chuphong;
    }

    public int getTienphong() {
        return tienphong;
    }

    public void setTienphong(int tienphong) {
        this.tienphong = tienphong;
    }

    public int getPhuongthuctienphong() {
        return phuongthuctienphong;
    }

    public void setPhuongthuctienphong(int phuongthuctienphong) {
        this.phuongthuctienphong = phuongthuctienphong;
    }

    public int getTiencoc() {
        return tiencoc;
    }

    public void setTiencoc(int tiencoc) {
        this.tiencoc = tiencoc;
    }

    public int getPhuongthuctiencoc() {
        return phuongthuctiencoc;
    }

    public void setPhuongthuctiencoc(int phuongthuctiencoc) {
        this.phuongthuctiencoc = phuongthuctiencoc;
    }


    public String getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(String ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

}
