package com.example.nhatro2.tien_coc;

public class TienCocModel {
    private int id;
    private int chuphong;
    private String tenchuphong;
    private String status;
    private String mes;
    private String tienformat;
    private int last;
    private int tien;
    private String ngay;
    private String gio;
    private String ghichu;
    private int phuongthuc;
    private int daxuly;

    public TienCocModel(int id, int chuphong, String tenchuphong, String status, String mes, String tienformat, int last, int tien, String ngay, String gio, String ghichu, int phuongthuc, int daxuly) {
        this.id = id;
        this.chuphong = chuphong;
        this.tenchuphong = tenchuphong;
        this.status = status;
        this.mes = mes;
        this.tienformat = tienformat;
        this.last = last;
        this.tien = tien;
        this.ngay = ngay;
        this.gio = gio;
        this.ghichu = ghichu;
        this.phuongthuc = phuongthuc;
        this.daxuly = daxuly;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenchuphong() {
        return tenchuphong;
    }

    public void setTenchuphong(String tenchuphong) {
        this.tenchuphong = tenchuphong;
    }

    public int getChuphong() {
        return chuphong;
    }

    public void setChuphong(int chuphong) {
        this.chuphong = chuphong;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getTienformat() {
        return tienformat;
    }

    public void setTienformat(String tienformat) {
        this.tienformat = tienformat;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
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

    public int getPhuongthuc() {
        return phuongthuc;
    }

    public void setPhuongthuc(int phuongthuc) {
        this.phuongthuc = phuongthuc;
    }

    public int getDaxuly() {
        return daxuly;
    }

    public void setDaxuly(int daxuly) {
        this.daxuly = daxuly;
    }
}
