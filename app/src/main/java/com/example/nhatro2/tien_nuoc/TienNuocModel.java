package com.example.nhatro2.tien_nuoc;

public class TienNuocModel {
    private String phong;
    private int khach;
    private String tenkhach;
    private int sodau;
    private int socuoi;
    private String ngay;
    private String gio;
    private int dongia;
    private int tien;
    private int sonuoc;

    public TienNuocModel(String phong, int khach, String tenkhach, int sodau, int socuoi, String ngay, String gio, int dongia, int tien, int sonuoc) {
        this.phong = phong;
        this.khach = khach;
        this.tenkhach = tenkhach;
        this.sodau = sodau;
        this.socuoi = socuoi;
        this.ngay = ngay;
        this.gio = gio;
        this.dongia = dongia;
        this.tien = tien;
        this.sonuoc = sonuoc;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public int getKhach() {
        return khach;
    }

    public void setKhach(int khach) {
        this.khach = khach;
    }

    public String getTenkhach() {
        return tenkhach;
    }

    public void setTenkhach(String tenkhach) {
        this.tenkhach = tenkhach;
    }

    public int getSodau() {
        return sodau;
    }

    public void setSodau(int sodau) {
        this.sodau = sodau;
    }

    public int getSocuoi() {
        return socuoi;
    }

    public void setSocuoi(int socuoi) {
        this.socuoi = socuoi;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public int getSonuoc() {
        return sonuoc;
    }

    public void setSonuoc(int sonuoc) {
        this.sonuoc = sonuoc;
    }
}
