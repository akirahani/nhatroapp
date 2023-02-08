package com.example.nhatro2.dong_tien;

public class LichSuDongTienModel {
    private String tien;
    private String ngay;
    private String gio;

    public LichSuDongTienModel(String tien, String ngay, String gio) {
        this.tien = tien;
        this.ngay = ngay;
        this.gio = gio;
    }



    public String getTien() {
        return tien;
    }
    public void setTien(String tien) {
        this.tien = tien;
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
