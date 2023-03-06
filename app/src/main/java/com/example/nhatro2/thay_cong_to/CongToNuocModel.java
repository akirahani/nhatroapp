package com.example.nhatro2.thay_cong_to;

public class CongToNuocModel {
    private String status;
    private String mess;
    private String phong;
    private int chiso;

    public CongToNuocModel(String status, String mess, String phong, int chiso) {
        this.status = status;
        this.mess = mess;
        this.phong = phong;
        this.chiso = chiso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public int getChiso() {
        return chiso;
    }

    public void setChiso(int chiso) {
        this.chiso = chiso;
    }
}
