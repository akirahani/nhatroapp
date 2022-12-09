package com.example.nhatro2.quanlychung;

public class ChungModel {

    private int hinh;
    private String ten;
    private String page;

    public ChungModel(int hinh, String ten, String page) {
        this.hinh = hinh;
        this.ten = ten;
        this.page = page;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
