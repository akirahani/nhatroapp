package com.example.nhatro2.thong_ke;

public class TongQuanChartModel {
    private int phongtrong;
    private int phongthue;
    private int thanhvien;

    public TongQuanChartModel(int phongtrong, int phongthue, int thanhvien) {
        this.phongtrong = phongtrong;
        this.phongthue = phongthue;
        this.thanhvien = thanhvien;
    }

    public int getPhongtrong() {
        return phongtrong;
    }

    public void setPhongtrong(int phongtrong) {
        this.phongtrong = phongtrong;
    }

    public int getPhongthue() {
        return phongthue;
    }

    public void setPhongthue(int phongthue) {
        this.phongthue = phongthue;
    }

    public int getThanhvien() {
        return thanhvien;
    }

    public void setThanhvien(int thanhvien) {
        this.thanhvien = thanhvien;
    }
}
