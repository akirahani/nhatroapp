package com.example.nhatro2.dich_vu;

public class    DichVuModel {
    private int id;
    private String ten;
    private int loai;
    private int gia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public DichVuModel(int id, String ten, int loai, int gia) {
        this.id = id;
        this.ten = ten;
        this.loai = loai;
        this.gia = gia;
    }


}
