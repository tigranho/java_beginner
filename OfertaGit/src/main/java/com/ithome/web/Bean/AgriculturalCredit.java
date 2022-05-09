package com.ithome.web.Bean;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.Helpers.PojoHelper;

import java.sql.SQLException;
import java.util.List;

public class AgriculturalCredit extends PojoHelper {

    private Long ACId;
    private String ProductName;
    private int ACMinAmount;
    private int ACMaxAmount;
    private double ACMinLoan;
    private double ACMaxLoan;
    private double ACFactual;
    private int ACMinPeriodMonth;
    private int ACMaxPeriodMonth;
    private int ACMinServiceFee;
    private int ACMaxServiceFee;
    private int lastLogic;
    private String bankLink;
    private BanksDaoController banksDaoController = new BanksDaoController();

    public AgriculturalCredit(int productCode, int bankId, String bankName, String bankWebSite,int minAge, int maxAge,
                              int orderOfAppearance, int specialOffer, int firstSearchList, int sendRequest, String currancy,
                              int gotoPage, Long ACId, String productName, int ACMinAmount, int ACMaxAmount, double ACMinLoan, double ACMaxLoan,
                              double ACFactual, int ACMinPeriodMonth, int ACMaxPeriodMonth, int ACMinServiceFee, int ACMaxServiceFee,int lastLogic) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, orderOfAppearance, specialOffer, firstSearchList, sendRequest, currancy, gotoPage);
        this.ACId = ACId;
        ProductName = productName;
        this.ACMinAmount = ACMinAmount;
        this.ACMaxAmount = ACMaxAmount;
        this.ACMinLoan = ACMinLoan;
        this.ACMaxLoan = ACMaxLoan;
        this.ACFactual = ACFactual;
        this.ACMinPeriodMonth = ACMinPeriodMonth;
        this.ACMaxPeriodMonth = ACMaxPeriodMonth;
        this.ACMinServiceFee = ACMinServiceFee;
        this.ACMaxServiceFee = ACMaxServiceFee;
        this.lastLogic = lastLogic;

    }

    public AgriculturalCredit(int productcode, int bankId, String bankName, String bankWebSite, int minAge, int maxAge,
                              String productName, int ACMinAmount, int ACMaxAmount, double ACMinLoan,
                              double ACMacLoan, double ACPerFactual, int ACMinPeriodMonth, int ACMaxPeriodMonth, int ACMinServiceFee, int ACMaxServiceFee, String currancy,String bankLink) {
        super(productcode, bankId, bankName, bankWebSite, minAge, maxAge,currancy);
        ProductName = productName;
        this.ACMinAmount = ACMinAmount;
        this.ACMaxAmount = ACMaxAmount;
        this.ACMinLoan = ACMinLoan;
        this.ACMaxLoan = ACMacLoan;
        this.ACFactual = ACPerFactual;
        this.ACMinPeriodMonth = ACMinPeriodMonth;
        this.ACMaxPeriodMonth = ACMaxPeriodMonth;
        this.ACMinServiceFee = ACMinServiceFee;
        this.ACMaxServiceFee = ACMaxServiceFee;
        this.bankLink = bankLink;

    }
    public AgriculturalCredit(int orderOfAppearance, int specialOffer, int firstSearchList, int sendRequest, int gotoPage) {
        super(orderOfAppearance, specialOffer, firstSearchList, sendRequest, gotoPage);
    }


    public AgriculturalCredit(int orderOfAppearance, int specialOffer, int firstSearchList, int sendRequest, int gotoPage, int lastLogic) {
        super(orderOfAppearance, specialOffer, firstSearchList, sendRequest, gotoPage);
        this.lastLogic=lastLogic;
    }

    public AgriculturalCredit() {
    }

    public AgriculturalCredit(Long acId, String bankName, int bankId, String bankWebSite, String productName, int productcode,
                              int acMaxPeriodMonth, int acMinPeriodMonth, int acMaxAmount, int acMinAmount, int minAge, int maxAge,
                              double acFactual, int acMinServiceFee, int acMaxServiceFee,double ACMinLoan,double ACMacLoan, String currancy) {
        super(productcode, bankId, bankName, bankWebSite, minAge, maxAge,currancy);
        this.ACId = acId;
        this.ProductName = productName;
        this.ACMinAmount = acMinAmount;
        this.ACMaxAmount = acMaxAmount;
        this.ACMinLoan = ACMinLoan;
        this.ACMaxLoan = ACMacLoan;
        this.ACFactual = acFactual;
        this.ACMinPeriodMonth = acMinPeriodMonth;
        this.ACMaxPeriodMonth = acMaxPeriodMonth;
        this.ACMinServiceFee = acMinServiceFee;
        this.ACMaxServiceFee = acMaxServiceFee;

    }


    public String getBanksList() throws SQLException {
        return banksDaoController.getBankImageById(super.getBankId());

    }

    public int getLastLogic() {
        return lastLogic;
    }

    public void setLastLogic(int lastLogic) {
        this.lastLogic = lastLogic;
    }

    public String setBanksList(List<AgriculturalCredit> depositList) throws SQLException {
        String bankImage = null;
        int bankid = 0;
        for (int i = 0; i < depositList.size(); i++) {
            bankid = depositList.get(i).getBankId();
            bankImage = banksDaoController.getBankImageById(bankid);
        }
        return bankImage;
    }

    public String getBankLink() {
        return bankLink;
    }

    public void setBankLink(String bankLink) {
        this.bankLink = bankLink;
    }

    public Long getACId() {
        return ACId;
    }

    public void setACId(Long ACId) {
        this.ACId = ACId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        this.ProductName = productName;
    }

    public int getACMinAmount() {
        return ACMinAmount;
    }

    public void setACMinAmount(int ACMinAmount) {
        this.ACMinAmount = ACMinAmount;
    }

    public int getACMaxAmount() {
        return ACMaxAmount;
    }

    public void setACMaxAmount(int ACMaxAmount) {
        this.ACMaxAmount = ACMaxAmount;
    }

    public double getACMinLoan() {
        return ACMinLoan;
    }

    public void setACMinLoan(double ACMinLoan) {
        this.ACMinLoan = ACMinLoan;
    }

    public double getACMaxLoan() {
        return ACMaxLoan;
    }

    public void setACMaxLoan(double ACMaxLoan) {
        this.ACMaxLoan = ACMaxLoan;
    }

    public double getACFactual() {
        return ACFactual;
    }

    public void setACFactual(double ACFactual) {
        this.ACFactual = ACFactual;
    }

    public int getACMinPeriodMonth() {
        return ACMinPeriodMonth;
    }

    public void setACMinPeriodMonth(int ACMinPeriodMonth) {
        this.ACMinPeriodMonth = ACMinPeriodMonth;
    }

    public int getACMaxPeriodMonth() {
        return ACMaxPeriodMonth;
    }

    public void setACMaxPeriodMonth(int ACMaxPeriodMonth) {
        this.ACMaxPeriodMonth = ACMaxPeriodMonth;
    }

    public int getACMinServiceFee() {
        return ACMinServiceFee;
    }

    public void setACMinServiceFee(int ACMinServiceFee) {
        this.ACMinServiceFee = ACMinServiceFee;
    }

    public int getACMaxServiceFee() {
        return ACMaxServiceFee;
    }

    public void setACMaxServiceFee(int ACMaxServiceFee) {
        this.ACMaxServiceFee = ACMaxServiceFee;
    }
}