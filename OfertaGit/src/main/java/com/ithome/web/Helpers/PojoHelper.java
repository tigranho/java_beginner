package com.ithome.web.Helpers;

public abstract class PojoHelper {
    private int productCode;
    private int bankId;
    private String bankName;
    private String bankWebSite;
    private int MinAge;
    private int MaxAge;
    private int OrderOfAppearance;
    private int SpecialOffer;
    private int FirstSearchList;
    private int SendRequest;
    private String Currancy;
    private int GotoPage;

    public PojoHelper(int productCode, int bankId, String bankName, String bankWebSite, int minAge, int maxAge, int orderOfAppearance, int specialOffer, int firstSearchList, int sendRequest, String currancy, int gotoPage) {
        this.productCode = productCode;
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankWebSite = bankWebSite;
        MinAge = minAge;
        MaxAge = maxAge;
        OrderOfAppearance = orderOfAppearance;
        SpecialOffer = specialOffer;
        FirstSearchList = firstSearchList;
        SendRequest = sendRequest;
        Currancy = currancy;
        GotoPage = gotoPage;
    }

    public PojoHelper(int orderOfAppearance, int specialOffer, int firstSearchList, int sendRequest, int gotoPage) {
        OrderOfAppearance = orderOfAppearance;
        SpecialOffer = specialOffer;
        FirstSearchList = firstSearchList;
        SendRequest = sendRequest;
        GotoPage = gotoPage;
    }




    public int getGotoPage() {
        return GotoPage;
    }

    public void setGotoPage(int gotoPage) {
        GotoPage = gotoPage;
    }

    public PojoHelper() {
    }

    public PojoHelper(int productCode, int bankId, String bankName, String bankWebSite, int minAge, int maxAge, String currancy) {
        this.productCode = productCode;
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankWebSite = bankWebSite;
        MinAge = minAge;
        MaxAge = maxAge;
        Currancy = currancy;
    }



    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankWebSite() {
        return bankWebSite;
    }

    public void setBankWebSite(String bankWebSite) {
        this.bankWebSite = bankWebSite;
    }

    public int getMinAge() {
        return MinAge;
    }

    public void setMinAge(int minAge) {
        MinAge = minAge;
    }

    public int getMaxAge() {
        return MaxAge;
    }

    public void setMaxAge(int maxAge) {
        MaxAge = maxAge;
    }

    public int getOrderOfAppearance() {
        return OrderOfAppearance;
    }

    public void setOrderOfAppearance(int orderOfAppearance) {
        OrderOfAppearance = orderOfAppearance;
    }

    public int getSpecialOffer() {
        return SpecialOffer;
    }

    public void setSpecialOffer(int specialOffer) {
        SpecialOffer = specialOffer;
    }

    public int getFirstSearchList() {
        return FirstSearchList;
    }

    public void setFirstSearchList(int firstSearchList) {
        FirstSearchList = firstSearchList;
    }

    public int getSendRequest() {
        return SendRequest;
    }

    public void setSendRequest(int sendRequest) {
        SendRequest = sendRequest;
    }

    public String getCurrancy() {
        return Currancy;
    }

    public void setCurrancy(String currancy) {
        Currancy = currancy;
    }


}