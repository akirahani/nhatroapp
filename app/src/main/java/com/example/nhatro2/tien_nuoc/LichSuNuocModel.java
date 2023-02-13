package com.example.nhatro2.tien_nuoc;

public class LichSuNuocModel {
    private String phong;
    private int sonuoc;
    private String time;

    public LichSuNuocModel(String phong, int sonuoc, String time) {
        this.phong = phong;
        this.sonuoc = sonuoc;
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

    public int getSonuoc() {
        return sonuoc;
    }

    public void setSonuoc(int sonuoc) {
        this.sonuoc = sonuoc;
    }
}
