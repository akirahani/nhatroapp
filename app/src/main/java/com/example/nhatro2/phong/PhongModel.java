package com.example.nhatro2.phong;

public class PhongModel {
    private int id;
    private String ten;
    private int trangthai;
    private String khu;
    private int tang;
    private int thietbi;
    private int datcoc;
    private int sodu;
    private int chuphong;
    private String tenkhach;
    private int giaphong;
    private String dienthoai;
    //    private String daidien;

    public PhongModel(int id, String ten, int trangthai, String khu, int tang, int thietbi, int datcoc, int sodu, int chuphong, String tenkhach, int giaphong, String dienthoai) {
        this.id = id;
        this.ten = ten;
        this.trangthai = trangthai;
        this.khu = khu;
        this.tang = tang;
        this.thietbi = thietbi;
        this.datcoc = datcoc;
        this.sodu = sodu;
        this.chuphong = chuphong;
        this.tenkhach = tenkhach;
        this.giaphong = giaphong;
        this.dienthoai = dienthoai;
    }
    public String getTenkhach() {
        return tenkhach;
    }

    public void setTenkhach(String tenkhach) {
        this.tenkhach = tenkhach;
    }


    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public int getGiaphong() {
        return giaphong;
    }

    public void setGiaphong(int giaphong) {
        this.giaphong = giaphong;
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

    public int getDatcoc() {
        return datcoc;
    }

    public void setDatcoc(int datcoc) {
        this.datcoc = datcoc;
    }

    public int getSodu() {
        return sodu;
    }

    public void setSodu(int sodu) {
        this.sodu = sodu;
    }

    public int getChuphong() {
        return chuphong;
    }

    public void setChuphong(int chuphong) {
        this.chuphong = chuphong;
    }

}
