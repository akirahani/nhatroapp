package com.example.nhatro2.dong_tien;

public class ApDungUuDaiModel {
    private String status;
    private int uudai;
    private int idphong;
    private int chuphong;
    private String mess;

    public ApDungUuDaiModel(String status, int uudai, int idphong, int chuphong, String mess) {
        this.status = status;
        this.uudai = uudai;
        this.idphong = idphong;
        this.chuphong = chuphong;
        this.mess = mess;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUudai() {
        return uudai;
    }

    public void setUudai(int uudai) {
        this.uudai = uudai;
    }

    public int getIdphong() {
        return idphong;
    }

    public void setIdphong(int idphong) {
        this.idphong = idphong;
    }

    public int getChuphong() {
        return chuphong;
    }

    public void setChuphong(int chuphong) {
        this.chuphong = chuphong;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
