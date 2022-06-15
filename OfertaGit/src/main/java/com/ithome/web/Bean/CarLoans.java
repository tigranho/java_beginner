package com.ithome.web.Bean;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.Helpers.PojoHelper;

import java.sql.SQLException;
import java.util.List;

public class CarLoans extends PojoHelper {

    private Long CLId;
    private String productName;
    private int CLMinAmount;
    private int CLMaxAmount;
    private double CLMinLoan;
    private double CLMaxLoan;
    private double CLFatual;
    private int CLMinDownPayment;
    private int CLMaxDownPayment;
    private int CLMinPeriodMonths;
    private int CLMaxPeriodMonths;
    private int CLMinServiceFee;
    private int CLMaxServiceFee;
    private int lastLogic;
    private String bankLink;

    private BanksDaoController banksDaoController = new BanksDaoController();

    public CarLoans(int productCode, int bankId, String bankName, String bankWebSite,int minAge, int maxAge, int orderOfAppearance, int specialOffer,
                    int firstSearchList, int sendRequest, String currancy, int gotoPage, Long CLId, String productName, int CLMinAmount, int CLMaxAmount, double CLMinLoan, double CLMaxLoan, double CLFatual, int CLMinDownPayment, int CLMaxDownPayment, int CLMinPeriodMonths, int CLMaxPeriodMonths, int CLMinServiceFee, int CLMaxServiceFee,int lastlogic) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, orderOfAppearance, specialOffer, firstSearchList, sendRequest, currancy, gotoPage);
        this.CLId = CLId;
        this.productName = productName;
        this.CLMinAmount = CLMinAmount;
        this.CLMaxAmount = CLMaxAmount;
        this.CLMinLoan = CLMinLoan;
        this.CLMaxLoan = CLMaxLoan;
        this.CLFatual = CLFatual;
        this.CLMinDownPayment = CLMinDownPayment;
        this.CLMaxDownPayment = CLMaxDownPayment;
        this.CLMinPeriodMonths = CLMinPeriodMonths;
        this.CLMaxPeriodMonths = CLMaxPeriodMonths;
        this.CLMinServiceFee = CLMinServiceFee;
        this.CLMaxServiceFee = CLMaxServiceFee;
        this.lastLogic =lastlogic;
    }

    public CarLoans() {
    }

    public CarLoans(int orderOfAppearance, int specialOffer, int firstSearchList, int sendRequest, int gotoPage) {
        super(orderOfAppearance, specialOffer, firstSearchList, sendRequest, gotoPage);
    }


    public CarLoans(int orderOfAppearance, int specialOffer, int firstSearchList, int sendRequest, int gotoPage, int lastLogic) {
        super(orderOfAppearance, specialOffer, firstSearchList, sendRequest, gotoPage);
        this.lastLogic=lastLogic;
    }



    public CarLoans(int productCode, int bankId, String bankName, String bankWebSite, int clMinAge, int clMaxAge, String currancy, String productName,
                    int CLMinAmount, int CLMaxAmount, double CLMinLoan, double CLMaxLoan, double CLPerFactual, int CLMinDownPayment, int CLMaxDownPayment, int CLMinPeriodMonths, int CLMaxPeriodMonths,
                    int CLMinServiceFee, int CLMaxServiceFee,String BankLink) {
        super(productCode, bankId, bankName, bankWebSite, clMinAge, clMaxAge, currancy);
        this.productName = productName;
        this.CLMinAmount = CLMinAmount;
        this.CLMaxAmount = CLMaxAmount;
        this.CLMinLoan = CLMinLoan;
        this.CLMaxLoan = CLMaxLoan;
        this.CLFatual = CLPerFactual;
        this.CLMinDownPayment = CLMinDownPayment;
        this.CLMaxDownPayment = CLMaxDownPayment;
        this.CLMinPeriodMonths = CLMinPeriodMonths;
        this.CLMaxPeriodMonths = CLMaxPeriodMonths;
        this.CLMinServiceFee = CLMinServiceFee;
        this.CLMaxServiceFee = CLMaxServiceFee;
        this.bankLink = BankLink;
    }

    public CarLoans(String productName, String bankName, String bankWebSite, Long clId,int productCode, int bankId, int minAge, int maxAge,
                    int CLMinAmount, int CLMaxAmount, double clMinLoan, double clMaxLoan, int CLMinDownPayment,
                    int CLMaxDownPayment, int CLMinPeriodMonths, int CLMaxPeriodMonths, int CLMinServiceFee, int CLMaxServiceFee,String currancy) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, currancy);
        this.CLId =clId;
        this.productName = productName;
        this.CLMinAmount = CLMinAmount;
        this.CLMaxAmount = CLMaxAmount;
        this.CLMinLoan = clMinLoan;
        this.CLMaxLoan = clMaxLoan;
        this.CLMinDownPayment = CLMinDownPayment;
        this.CLMaxDownPayment = CLMaxDownPayment;
        this.CLMinPeriodMonths = CLMinPeriodMonths;
        this.CLMaxPeriodMonths = CLMaxPeriodMonths;
        this.CLMinServiceFee = CLMinServiceFee;
        this.CLMaxServiceFee = CLMaxServiceFee;
    }

    public String getBanksList() throws SQLException {
        return banksDaoController.getBankImageById(super.getBankId());

    }

    public String getBankLink() {
        return bankLink;
    }

    public void setBankLink(String bankLink) {
        this.bankLink = bankLink;
    }

    public int getLastLogic() {
        return lastLogic;
    }

    public void setLastLogic(int lastLogic) {
        this.lastLogic = lastLogic;
    }

    public int getLastlogic() {
        return lastLogic;
    }

    public void setLastlogic(int lastlogic) {
        this.lastLogic = lastlogic;
    }

    public String setBanksList(List<CarLoans> depositList) throws SQLException {
        String bankImage = null;
        int bankid = 0;
        for (int i = 0; i < depositList.size(); i++) {
            bankid = depositList.get(i).getBankId();
            bankImage = banksDaoController.getBankImageById(bankid);
        }
        return bankImage;
    }

    public Long getCLId() {
        return CLId;
    }

    public void setCLId(Long CLId) {
        this.CLId = CLId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCLMinAmount() {
        return CLMinAmount;
    }

    public void setCLMinAmount(int CLMinAmount) {
        this.CLMinAmount = CLMinAmount;
    }

    public int getCLMaxAmount() {
        return CLMaxAmount;
    }

    public void setCLMaxAmount(int CLMaxAmount) {
        this.CLMaxAmount = CLMaxAmount;
    }

    public double getCLMinLoan() {
        return CLMinLoan;
    }

    public void setCLMinLoan(double CLMinLoan) {
        this.CLMinLoan = CLMinLoan;
    }

    public double getCLMaxLoan() {
        return CLMaxLoan;
    }

    public void setCLMaxLoan(double CLMaxLoan) {
        this.CLMaxLoan = CLMaxLoan;
    }

    public double getCLFatual() {
        return CLFatual;
    }

    public void setCLFatual(double CLFatual) {
        this.CLFatual = CLFatual;
    }

    public int getCLMinDownPayment() {
        return CLMinDownPayment;
    }

    public void setCLMinDownPayment(int CLMinDownPayment) {
        this.CLMinDownPayment = CLMinDownPayment;
    }

    public int getCLMaxDownPayment() {
        return CLMaxDownPayment;
    }

    public void setCLMaxDownPayment(int CLMaxDownPayment) {
        this.CLMaxDownPayment = CLMaxDownPayment;
    }

    public int getCLMinPeriodMonths() {
        return CLMinPeriodMonths;
    }

    public void setCLMinPeriodMonths(int CLMinPeriodMonths) {
        this.CLMinPeriodMonths = CLMinPeriodMonths;
    }

    public int getCLMaxPeriodMonths() {
        return CLMaxPeriodMonths;
    }

    public void setCLMaxPeriodMonths(int CLMaxPeriodMonths) {
        this.CLMaxPeriodMonths = CLMaxPeriodMonths;
    }

    public int getCLMinServiceFee() {
        return CLMinServiceFee;
    }

    public void setCLMinServiceFee(int CLMinServiceFee) {
        this.CLMinServiceFee = CLMinServiceFee;
    }

    public int getCLMaxServiceFee() {
        return CLMaxServiceFee;
    }

    public void setCLMaxServiceFee(int CLMaxServiceFee) {
        this.CLMaxServiceFee = CLMaxServiceFee;
    }
}