package com.example.nhatro2.tra_phong;

public class DienTraPhongModel {
    private String thoigian;
    private String tien;
    private int sodau;
    private int socuoi;

    public DienTraPhongModel(String thoigian, String tien, int sodau, int socuoi) {
        this.thoigian = thoigian;
        this.tien = tien;
        this.sodau = sodau;
        this.socuoi = socuoi;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getTien() {
        return tien;
    }

    public void setTien(String tien) {
        this.tien = tien;
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
}
