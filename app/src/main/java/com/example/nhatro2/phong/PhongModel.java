package com.example.nhatro2.phong;

public class PhongModel {
    private int id;
    private String ten;
    private int trangthai;
    private String khu;
    private int tang;
    private int thietbi;
    private int chuphong;
    private String tenkhach;
    private int giaphong;
    private String giaphongformat;
    private String dienthoai;

    public PhongModel(int id, String ten, int trangthai, String khu, int tang, int thietbi, int chuphong, String tenkhach, int giaphong, String giaphongformat, String dienthoai) {
        this.id = id;
        this.ten = ten;
        this.trangthai = trangthai;
        this.khu = khu;
        this.tang = tang;
        this.thietbi = thietbi;
        this.chuphong = chuphong;
        this.tenkhach = tenkhach;
        this.giaphong = giaphong;
        this.giaphongformat = giaphongformat;
        this.dienthoai = dienthoai;
    }

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

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getKhu() {
        return khu;
    }

    public void setKhu(String khu) {
        this.khu = khu;
    }

    public int getTang() {
        return tang;
    }

    public void setTang(int tang) {
        this.tang = tang;
    }

    public int getThietbi() {
        return thietbi;
    }

    public void setThietbi(int thietbi) {
        this.thietbi = thietbi;
    }

    public int getChuphong() {
        return chuphong;
    }

    public void setChuphong(int chuphong) {
        this.chuphong = chuphong;
    }

    public String getTenkhach() {
        return tenkhach;
    }

    public void setTenkhach(String tenkhach) {
        this.tenkhach = tenkhach;
    }

    public int getGiaphong() {
        return giaphong;
    }

    public void setGiaphong(int giaphong) {
        this.giaphong = giaphong;
    }

    public String getGiaphongformat() {
        return giaphongformat;
    }

    public void setGiaphongformat(String giaphongformat) {
        this.giaphongformat = giaphongformat;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }
}
