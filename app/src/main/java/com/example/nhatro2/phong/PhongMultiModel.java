package com.example.nhatro2.phong;

import java.util.List;

public class PhongMultiModel {
    private String phong;
    private String ten;
    private String dienthoai;
    private int tiencoc;

    public PhongMultiModel(String phong, String ten, String dienthoai, int tiencoc) {
        this.phong = phong;
        this.ten = ten;
        this.dienthoai = dienthoai;
        this.tiencoc = tiencoc;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public int getTiencoc() {
        return tiencoc;
    }

    public void setTiencoc(int tiencoc) {
        this.tiencoc = tiencoc;
    }


}
