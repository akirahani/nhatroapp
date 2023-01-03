package com.example.nhatro2.tien_nuoc;

public class TienNuocModel {
    private int sodau;
    private int socuoi;
    private String ngaychot;
    private int khach;
    private int dongia;
    private int tien;
    private int sodu;
    private String phong;

    public TienNuocModel(int sodau, int socuoi, String ngaychot, int khach, int dongia, int tien, int sodu, String phong) {
        this.sodau = sodau;
        this.socuoi = socuoi;
        this.ngaychot = ngaychot;
        this.khach = khach;
        this.dongia = dongia;
        this.tien = tien;
        this.sodu = sodu;
        this.phong = phong;
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

    public String getNgaychot() {
        return ngaychot;
    }

    public void setNgaychot(String ngaychot) {
        this.ngaychot = ngaychot;
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

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }


}
