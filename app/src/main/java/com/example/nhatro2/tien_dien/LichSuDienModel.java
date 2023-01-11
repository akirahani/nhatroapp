package com.example.nhatro2.tien_dien;

public class LichSuDienModel {
    private String phong;
    private int sodien;
    private String time;

    public LichSuDienModel(String phong, int sodien, String time) {
        this.phong = phong;
        this.sodien = sodien;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public int getSodien() {
        return sodien;
    }

    public void setSodien(int sodien) {
        this.sodien = sodien;
    }
}
