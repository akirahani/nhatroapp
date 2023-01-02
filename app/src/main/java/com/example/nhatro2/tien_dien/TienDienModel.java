package com.example.nhatro2.tien_dien;

public class TienDienModel {
    private int soDau;
    private int soCuoi;
    private String ngayChot;
    private String ngayCapNhat;
    private int khach;
    private int dongia;
    private int tien;
    private int sodu;
    private int phong;

    public TienDienModel(int soDau, int soCuoi, String ngayChot, String ngayCapNhat, int khach, int dongia, int tien, int sodu, int phong) {
        this.soDau = soDau;
        this.soCuoi = soCuoi;
        this.ngayChot = ngayChot;
        this.ngayCapNhat = ngayCapNhat;
        this.khach = khach;
        this.dongia = dongia;
        this.tien = tien;
        this.sodu = sodu;
        this.phong = phong;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public int getSodu() {
        return sodu;
    }

    public void setSodu(int sodu) {
        this.sodu = sodu;
    }

    public int getSoDau() {
        return soDau;
    }

    public void setSoDau(int soDau) {
        this.soDau = soDau;
    }

    public int getSoCuoi() {
        return soCuoi;
    }

    public void setSoCuoi(int soCuoi) {
        this.soCuoi = soCuoi;
    }

    public String getNgayChot() {
        return ngayChot;
    }

    public void setNgayChot(String ngayChot) {
        this.ngayChot = ngayChot;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public int getKhach() {
        return khach;
    }

    public void setKhach(int khach) {
        this.khach = khach;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getPhong() {
        return phong;
    }

    public void setPhong(int phong) {
        this.phong = phong;
    }

}
