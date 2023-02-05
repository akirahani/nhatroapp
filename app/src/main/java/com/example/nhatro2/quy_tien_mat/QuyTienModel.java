package com.example.nhatro2.quy_tien_mat;

import java.util.ArrayList;
import java.util.List;

public class QuyTienModel {
    private String tienthu;
    private String tienchi;
    private String quy;


    public QuyTienModel(String tienthu, String tienchi, String quy) {
        this.tienthu = tienthu;
        this.tienchi = tienchi;
        this.quy = quy;

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

}
