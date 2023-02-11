package com.example.nhatro2.thu_khac;

public class ThuKhacModel {
    private int id;
    private String lydo;
    private String ngay;
    private String gio;
    private int tien;
    private String tienformat;
    private String phong;


    public ThuKhacModel(int id, String lydo, String ngay, String gio, int tien, String tienformat, String phong) {
        this.id = id;
        this.lydo = lydo;
        this.ngay = ngay;
        this.gio = gio;
        this.tien = tien;
        this.tienformat = tienformat;
        this.phong = phong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public String getTienformat() {
        return tienformat;
    }

    public void setTienformat(String tienformat) {
        this.tienformat = tienformat;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }
}
