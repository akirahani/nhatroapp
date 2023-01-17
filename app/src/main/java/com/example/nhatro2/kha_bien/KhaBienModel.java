package com.example.nhatro2.kha_bien;

public class KhaBienModel {
    private int id;
    private int tien;
    private String lydo;
    private int loai;
    private int hinhthuc;
    private String ngay;
    private String gio;

    public KhaBienModel(int id, int tien, String lydo, int loai, int hinhthuc, String ngay, String gio) {
        this.id = id;
        this.tien = tien;
        this.lydo = lydo;
        this.loai = loai;
        this.hinhthuc = hinhthuc;
        this.ngay = ngay;
        this.gio = gio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public int getHinhthuc() {
        return hinhthuc;
    }

    public void setHinhthuc(int hinhthuc) {
        this.hinhthuc = hinhthuc;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

}
