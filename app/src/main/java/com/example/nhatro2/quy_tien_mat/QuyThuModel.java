package com.example.nhatro2.quy_tien_mat;

public class QuyThuModel {
    private String time;
    private String tien;

    public QuyThuModel(String time, String tien) {
        this.time = time;
        this.tien = tien;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTien() {
        return tien;
    }

    public void setTien(String tien) {
        this.tien = tien;
    }
}
