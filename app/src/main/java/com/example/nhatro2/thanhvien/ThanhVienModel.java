package com.example.nhatro2.thanhvien;

import java.util.Date;

public class ThanhVienModel {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private int nhom;
    private String dienthoai;
    private String diachi;
    private String cancuoc;
    private Date ngaycap;
    private String quoctich;
    private Date ngaysinh;

    public ThanhVienModel(int id, String username, String password, String fullname, int nhom, String dienthoai, String diachi, String cancuoc, Date ngaycap, String quoctich, Date ngaysinh) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.nhom = nhom;
        this.dienthoai = dienthoai;
        this.diachi = diachi;
        this.cancuoc = cancuoc;
        this.ngaycap = ngaycap;
        this.quoctich = quoctich;
        this.ngaysinh = ngaysinh;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getNhom() {
        return nhom;
    }

    public void setNhom(int nhom) {
        this.nhom = nhom;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getCancuoc() {
        return cancuoc;
    }

    public void setCancuoc(String cancuoc) {
        this.cancuoc = cancuoc;
    }

    public Date getNgaycap() {
        return ngaycap;
    }

    public void setNgaycap(Date ngaycap) {
        this.ngaycap = ngaycap;
    }

    public String getQuoctich() {
        return quoctich;
    }

    public void setQuoctich(String quoctich) {
        this.quoctich = quoctich;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }


}
