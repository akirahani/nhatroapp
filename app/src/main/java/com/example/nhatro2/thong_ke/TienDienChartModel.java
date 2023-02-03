package com.example.nhatro2.thong_ke;

public class TienDienChartModel {
    private int phongdiendatra;
    private int phongdienchuatra;

    public int getPhongdiendatra() {
        return phongdiendatra;
    }

    public void setPhongdiendatra(int phongdiendatra) {
        this.phongdiendatra = phongdiendatra;
    }

    public int getPhongdienchuatra() {
        return phongdienchuatra;
    }

    public void setPhongdienchuatra(int phongdienchuatra) {
        this.phongdienchuatra = phongdienchuatra;
    }

    public TienDienChartModel(int phongdiendatra, int phongdienchuatra) {
        this.phongdiendatra = phongdiendatra;
        this.phongdienchuatra = phongdienchuatra;
    }
}
