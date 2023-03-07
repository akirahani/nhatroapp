package com.example.nhatro2.doi_thiet_bi;

public class DoiThietBiModel {
    private String ten;
    private String thietbi;
    private String khongdung;

    public DoiThietBiModel(String ten, String thietbi, String khongdung) {
        this.ten = ten;
        this.thietbi = thietbi;
        this.khongdung = khongdung;
    }

    public String getKhongdung() {
        return khongdung;
    }

    public void setKhongdung(String khongdung) {
        this.khongdung = khongdung;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getThietbi() {
        return thietbi;
    }

    public void setThietbi(String thietbi) {
        this.thietbi = thietbi;
    }
}
