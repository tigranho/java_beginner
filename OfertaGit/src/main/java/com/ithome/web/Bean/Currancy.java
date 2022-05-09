package com.ithome.web.Bean;

public class Currancy {

    private int currancyId;
    private String currancy;

    public Currancy(int currancyId, String currancy) {
        this.currancyId = currancyId;
        this.currancy = currancy;
    }

    public Currancy() {
    }

    public int getCurrancyId() {
        return currancyId;
    }

    public void setCurrancyId(int currancyId) {
        this.currancyId = currancyId;
    }

    public String getCurrancy() {
        return currancy;
    }

    public void setCurrancy(String currancy) {
        this.currancy = currancy;
    }
}
