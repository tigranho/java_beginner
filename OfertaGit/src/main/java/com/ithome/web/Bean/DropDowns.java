package com.ithome.web.Bean;

public class DropDowns {
    private int id;
    private String currancy;
    private String minAmount;
    private String maxAmount;
    private String Steps;
    private String position;

    public DropDowns(int id, String currancy, String minAmount, String maxAmount, String steps, String position) {
        this.id = id;
        this.currancy = currancy;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        Steps = steps;
        this.position = position;
    }

    public DropDowns(String currancy, String minAmount, String maxAmount, String steps, String position) {
        this.currancy = currancy;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        Steps = steps;
        this.position = position;
    }



    public DropDowns() {
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrancy() {
        return currancy;
    }

    public void setCurrancy(String currancy) {
        this.currancy = currancy;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getSteps() {
        return Steps;
    }

    public void setSteps(String steps) {
        Steps = steps;
    }
}
