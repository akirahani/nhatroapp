package com.example.nhatro2.thanhvien;

public class ThanhVienModel {
    private String status;
    private int id;
    private String username;
    private String password;
    private String fullname;
    private int nhom;
    private String dienthoai;
    private String diachi;
    private String cancuoc;
    private String quoctich;
    private String ngaycap;
    private String ngaysinh;
    private String noicap;
    private int gioitinh;
    private int nhomtuoi;

    public ThanhVienModel(String status, int id, String username, String password, String fullname, int nhom, String dienthoai, String diachi, String cancuoc, String quoctich, String ngaycap, String ngaysinh, String noicap, int gioitinh, int nhomtuoi) {
        this.status = status;
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.nhom = nhom;
        this.dienthoai = dienthoai;
        this.diachi = diachi;
        this.cancuoc = cancuoc;
        this.quoctich = quoctich;
        this.ngaycap = ngaycap;
        this.ngaysinh = ngaysinh;
        this.noicap = noicap;
        this.gioitinh = gioitinh;
        this.nhomtuoi = nhomtuoi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoicap() {
        return noicap;
    }

    public void setNoicap(String noicap) {
        this.noicap = noicap;
    }

    public String getNgaycap() {
        return ngaycap;
    }

    public void setNgaycap(String ngaycap) {
        this.ngaycap = ngaycap;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }


    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getNhomtuoi() {
        return nhomtuoi;
    }

    public void setNhomtuoi(int nhomtuoi) {
        this.nhomtuoi = nhomtuoi;
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

    public String getQuoctich() {
        return quoctich;
    }

    public void setQuoctich(String quoctich) {
        this.quoctich = quoctich;
    }




}
