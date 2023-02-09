package com.example.nhatro2.quy_tien_mat;

import java.util.ArrayList;
import java.util.List;

public class QuyTienModel {
    private String tienthu;
    private String tienchi;
    private String quy;
    private String doanhthu;
    private String tongthucacthang;

    public QuyTienModel(String tienthu, String tienchi, String quy, String doanhthu, String tongthucacthang) {
        this.tienthu = tienthu;
        this.tienchi = tienchi;
        this.quy = quy;
        this.doanhthu = doanhthu;
        this.tongthucacthang = tongthucacthang;
    }

    public String getTienthu() {
        return tienthu;
    }

    public void setTienthu(String tienthu) {
        this.tienthu = tienthu;
    }

    public String getTienchi() {
        return tienchi;
    }

    public void setTienchi(String tienchi) {
        this.tienchi = tienchi;
    }

    public String getQuy() {
        return quy;
    }

    public void setQuy(String quy) {
        this.quy = quy;
    }

    public String getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(String doanhthu) {
        this.doanhthu = doanhthu;
    }

    public String getTongthucacthang() {
        return tongthucacthang;
    }

    public void setTongthucacthang(String tongthucacthang) {
        this.tongthucacthang = tongthucacthang;
    }

}
