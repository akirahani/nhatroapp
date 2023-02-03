package com.example.nhatro2.thong_ke;

public class ThietBiChartModel {
    private int tulanh;
    private int maygiat;
    private int dieuhoa;
    private int xacdien;

    public ThietBiChartModel(int tulanh, int maygiat, int dieuhoa, int xacdien) {
        this.tulanh = tulanh;
        this.maygiat = maygiat;
        this.dieuhoa = dieuhoa;
        this.xacdien = xacdien;
    }

    public int getTulanh() {
        return tulanh;
    }

    public void setTulanh(int tulanh) {
        this.tulanh = tulanh;
    }

    public int getMaygiat() {
        return maygiat;
    }

    public void setMaygiat(int maygiat) {
        this.maygiat = maygiat;
    }

    public int getDieuhoa() {
        return dieuhoa;
    }

    public void setDieuhoa(int dieuhoa) {
        this.dieuhoa = dieuhoa;
    }

    public int getXacdien() {
        return xacdien;
    }

    public void setXacdien(int xacdien) {
        this.xacdien = xacdien;
    }
}
