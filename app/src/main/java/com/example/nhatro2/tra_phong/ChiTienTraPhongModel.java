package com.example.nhatro2.tra_phong;

public class ChiTienTraPhongModel {
    private String status;
    private String noidung;
    private String message;

    public ChiTienTraPhongModel(String status, String noidung, String message) {
        this.status = status;
        this.noidung = noidung;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
