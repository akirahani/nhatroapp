package com.example.nhatro2.thong_ke;

public class TienNuocChartModel {
    private int phongnuocdatra;
    private int phongnuocchuatra;

    public TienNuocChartModel(int phongnuocdatra, int phongnuocchuatra) {
        this.phongnuocdatra = phongnuocdatra;
        this.phongnuocchuatra = phongnuocchuatra;
    }

    public int getPhongnuocdatra() {
        return phongnuocdatra;
    }

    public void setPhongnuocdatra(int phongnuocdatra) {
        this.phongnuocdatra = phongnuocdatra;
    }

    public int getPhongnuocchuatra() {
        return phongnuocchuatra;
    }

    public void setPhongnuocchuatra(int phongnuocchuatra) {
        this.phongnuocchuatra = phongnuocchuatra;
    }

}
