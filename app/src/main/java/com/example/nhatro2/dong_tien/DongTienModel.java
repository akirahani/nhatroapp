package com.example.nhatro2.dong_tien;

public class DongTienModel {
    private int chuphong;
    private String phong;
    private int tien;
    private int sodu;
    private int khachtratien;
    private int phuongthuc;
    private String ngay;
    private String gio;
    private String ghichu;
    private int tienphongthang;

    public DongTienModel(int chuphong, String phong, int tien, int sodu, int khachtratien, int phuongthuc, String ngay, String gio, String ghichu, int tienphongthang) {
        this.chuphong = chuphong;
        this.phong = phong;
        this.tien = tien;
        this.sodu = sodu;
        this.khachtratien = khachtratien;
        this.phuongthuc = phuongthuc;
        this.ngay = ngay;
        this.gio = gio;
        this.ghichu = ghichu;
        this.tienphongthang = tienphongthang;
    }


    public int getChuphong() {
        return chuphong;
    }

    public void setChuphong(int chuphong) {
        this.chuphong = chuphong;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public int getSodu() {
        return sodu;
    }

    public void setSodu(int sodu) {
        this.sodu = sodu;
    }

    public int getKhachtratien() {
        return khachtratien;
    }

    public void setKhachtratien(int khachtratien) {
        this.khachtratien = khachtratien;
    }

    public int getPhuongthuc() {
        return phuongthuc;
    }

    public void setPhuongthuc(int phuongthuc) {
        this.phuongthuc = phuongthuc;
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

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getTienphongthang() {
        return tienphongthang;
    }

    public void setTienphongthang(int tienphongthang) {
        this.tienphongthang = tienphongthang;
    }

}
