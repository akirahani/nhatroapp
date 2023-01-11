package com.example.nhatro2.tien_dien;

public class TienDienModel {
    private String phong;
    private int sodau;
    private int socuoi;
    private String ngaychot;
    private String tenkhach;

    private int khach;
    private int dongia;
    private int tien;
    private int sodien;
    private int sodu;

    public TienDienModel(String phong, int sodau, int socuoi, String ngaychot, String tenkhach, int khach, int dongia, int tien, int sodien, int sodu) {
        this.phong = phong;
        this.sodau = sodau;
        this.socuoi = socuoi;
        this.ngaychot = ngaychot;
        this.tenkhach = tenkhach;
        this.khach = khach;
        this.dongia = dongia;
        this.tien = tien;
        this.sodien = sodien;
        this.sodu = sodu;
    }

    public String getTenkhach() {
        return tenkhach;
    }

    public void setTenkhach(String tenkhach) {
        this.tenkhach = tenkhach;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
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

    public int getSodien() {
        return sodien;
    }

    public void setSodien(int sodien) {
        this.sodien = sodien;
    }

    public int getSodu() {
        return sodu;
    }

    public void setSodu(int sodu) {
        this.sodu = sodu;
    }

}
