package com.example.nhatro2.tai_khoan;

public class TaiKhoanModel {
    private int anh;
    private String ten;
    private String page;

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
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

    public TaiKhoanModel(int anh, String ten, String page) {
        this.anh = anh;
        this.ten = ten;
        this.page = page;
    }



}
