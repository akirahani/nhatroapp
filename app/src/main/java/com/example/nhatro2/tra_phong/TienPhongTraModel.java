package com.example.nhatro2.tra_phong;

public class TienPhongTraModel {
    private String thoigian;
    private String tien;

    public TienPhongTraModel(String thoigian, String tien) {
        this.thoigian = thoigian;
        this.tien = tien;
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
}
