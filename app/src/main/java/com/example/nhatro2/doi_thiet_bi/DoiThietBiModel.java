package com.example.nhatro2.doi_thiet_bi;

public class DoiThietBiModel {
    private String ten;
    private String thietbi;

    public DoiThietBiModel(String ten, String thietbi) {
        this.ten = ten;
        this.thietbi = thietbi;
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
