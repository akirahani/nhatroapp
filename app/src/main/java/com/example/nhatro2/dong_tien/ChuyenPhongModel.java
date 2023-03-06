package com.example.nhatro2.dong_tien;

public class ChuyenPhongModel {
    private String status;
    private String mess;
    private String phongcu;
    private String phongmoi;
    private int hopdong;

    public ChuyenPhongModel(String status, String mess, String phongcu, String phongmoi, int hopdong) {
        this.status = status;
        this.mess = mess;
        this.phongcu = phongcu;
        this.phongmoi = phongmoi;
        this.hopdong = hopdong;
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

    public String getPhongcu() {
        return phongcu;
    }

    public void setPhongcu(String phongcu) {
        this.phongcu = phongcu;
    }

    public String getPhongmoi() {
        return phongmoi;
    }

    public void setPhongmoi(String phongmoi) {
        this.phongmoi = phongmoi;
    }

    public int getHopdong() {
        return hopdong;
    }

    public void setHopdong(int hopdong) {
        this.hopdong = hopdong;
    }
}
