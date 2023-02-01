package com.example.nhatro2.dong_tien;

public class ThanhToanModel {
    private String status;
    private String mess;
    private int last;

    public ThanhToanModel(String status, String mess, int last) {
        this.status = status;
        this.mess = mess;
        this.last = last;
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

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }
}
