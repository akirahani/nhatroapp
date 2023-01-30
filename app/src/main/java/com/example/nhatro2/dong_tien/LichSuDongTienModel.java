package com.example.nhatro2.dong_tien;

public class LichSuDongTienModel {
    private String khachtratien;
    private String ngay;
    private String gio;

    public LichSuDongTienModel(String khachtratien, String ngay, String gio) {
        this.khachtratien = khachtratien;
        this.ngay = ngay;
        this.gio = gio;
    }

    public String getKhachtratien() {
        return khachtratien;
    }

    public void setKhachtratien(String khachtratien) {
        this.khachtratien = khachtratien;
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

}
