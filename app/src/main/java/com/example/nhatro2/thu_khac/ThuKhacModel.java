package com.example.nhatro2.thu_khac;

public class ThuKhacModel {
    private int id;
    private int tien;
    private String lydo;
    private String ngay;
    private String gio;
    private int phong;

    public ThuKhacModel(int id, int tien, String lydo, String ngay, String gio, int phong) {
        this.id = id;
        this.tien = tien;
        this.lydo = lydo;
        this.ngay = ngay;
        this.gio = gio;
        this.phong = phong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
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

    public int getPhong() {
        return phong;
    }

    public void setPhong(int phong) {
        this.phong = phong;
    }

}
