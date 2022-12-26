package com.example.nhatro2.phong;

public class PhongModel {
    private int id;
    private String ten;
    private int trangthai;
    private int vitri;
    private String dichvu;
    private int datcoc;
    private int khach;
    private String day;
    private int tang;
    private String daidien;
    private String dienthoai;
    private int gia;

    public String getDaidien() {
        return daidien;
    }

    public void setDaidien(String daidien) {
        this.daidien = daidien;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTang() {
        return tang;
    }

    public void setTang(int tang) {
        this.tang = tang;
    }

    //contrucstor
    public PhongModel(int id, String ten, int trangthai, int vitri, String dichvu, int datcoc, int khach, String day, int tang, String daidien, String dienthoai, int gia) {
        this.id = id;
        this.ten = ten;
        this.trangthai = trangthai;
        this.vitri = vitri;
        this.dichvu = dichvu;
        this.datcoc = datcoc;
        this.khach = khach;
        this.day = day;
        this.tang = tang;
        this.daidien = daidien;
        this.dienthoai = dienthoai;
        this.gia = gia;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setTrangthai(int trangtai) {
        this.trangthai = trangthai;
    }

    public void setVitri(int vitri) {
        this.vitri = vitri;
    }

    public void setDichvu(String dichvu) {
        this.dichvu = dichvu;
    }

    public void setDatcoc(int datcoc) {
        this.datcoc = datcoc;
    }

    public void setKhach(int khach) {
        this.khach = khach;
    }

    //    getter
    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public int getVitri() {
        return vitri;
    }

    public String getDichvu() {
        return dichvu;
    }

    public int getDatcoc() {
        return datcoc;
    }

    public int getKhach() {
        return khach;
    }
}
