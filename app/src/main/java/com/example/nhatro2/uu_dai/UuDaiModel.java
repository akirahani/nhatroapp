package com.example.nhatro2.uu_dai;

public class UuDaiModel {
    private int id;
    private String ten;
    private int so;
    private int apdung;

    public UuDaiModel(int id, String ten, int so, int apdung) {
        this.id = id;
        this.ten = ten;
        this.so = so;
        this.apdung = apdung;
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

    public int getSo() {
        return so;
    }

    public void setSo(int so) {
        this.so = so;
    }

    public int getApdung() {
        return apdung;
    }

    public void setApdung(int apdung) {
        this.apdung = apdung;
    }
}
