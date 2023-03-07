package com.example.nhatro2.doi_thiet_bi;

public class ChuyenPhongThietBiModel
{
    private String status;
    private String mess;
    private String cate;

    public ChuyenPhongThietBiModel(String status, String mess, String cate) {
        this.status = status;
        this.mess = mess;
        this.cate = cate;
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

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }




}
