package com.ithome.web.Bean;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.Helpers.PojoHelper;

import java.sql.SQLException;
import java.util.List;

public class MicroLoans extends PojoHelper {

    private Long MLId;
    private String productName;
    private int MLMinAmount;
    private int MLMaxAmount;
    private double MLMinLoan;
    private double MLMaxLoan;
    private double MLFatual;
    private int MMinPeriodDays;
    private int MMaxPeriodDays;
    private int MMinServiceFee;
    private int MMaxServiceFee;
    private int lastLogic;
    private String BankLink;
    private BanksDaoController banksDaoController = new BanksDaoController();

    public MicroLoans(int productCode, int bankId, String bankName, String bankWebSite,  int minAge, int maxAge, int orderOfAppearance, int specialOffer, int firstSearchList,
                      int sendRequest, String currancy, int gotoPage, Long MLId, String productName, int MLMinAmount, int MLMaxAmount, double MLMinLoan, double MLMaxLoan, double MLFatual, int MMinPeriodDays, int MMaxPeriodDays, int MMinServiceFee, int MMaxServiceFee, int lastlogic) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, orderOfAppearance, specialOffer, firstSearchList, sendRequest, currancy, gotoPage);
        this.MLId = MLId;
        this.productName = productName;
        this.MLMinAmount = MLMinAmount;
        this.MLMaxAmount = MLMaxAmount;
        this.MLMinLoan = MLMinLoan;
        this.MLMaxLoan = MLMaxLoan;
        this.MLFatual = MLFatual;
        this.MMinPeriodDays = MMinPeriodDays;
        this.MMaxPeriodDays = MMaxPeriodDays;
        this.MMinServiceFee = MMinServiceFee;
        this.MMaxServiceFee = MMaxServiceFee;
        this.lastLogic=lastlogic;
    }

    public MicroLoans() {
    }

    public MicroLoans(int productCode, int bankId, String bankName, String bankWebSite, String productName, int MLMinAmount,
                      int MLMaxAmount, double MLMinLoan, double MLMaxLoan, double MLFatual, String currancy, int MMinPeriodDays,
                      int MMaxPeriodDays, int MMinServiceFee, int MMaxServiceFee, int minAge, int maxAge,String BankLink) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, currancy);
        this.productName = productName;
        this.MLMinAmount = MLMinAmount;
        this.MLMaxAmount = MLMaxAmount;
        this.MLMinLoan = MLMinLoan;
        this.MLMaxLoan = MLMaxLoan;
        this.MLFatual = MLFatual;
        this.MMinPeriodDays = MMinPeriodDays;
        this.MMaxPeriodDays = MMaxPeriodDays;
        this.MMinServiceFee = MMinServiceFee;
        this.MMaxServiceFee = MMaxServiceFee;
        this.BankLink = BankLink;
    }

    public MicroLoans(int orderOfAppearance, int specialOffer, int firstSearchList, int sendRequest, int gotoPage) {
        super(orderOfAppearance, specialOffer, firstSearchList, sendRequest, gotoPage);
    }

    public MicroLoans(int orderOfAppearance, int specialOffer, int firstSearchList, int sendRequest, int gotoPage,int lastlogic) {
        super(orderOfAppearance, specialOffer, firstSearchList, sendRequest, gotoPage);
        this.lastLogic=lastlogic;
    }

    public MicroLoans(String productName, String bankName, String bankWebSite, Long mlId,
                      int bankId, int productCode, int MLMinAmount, int
                              MLMaxAmount, int minAge, int maxAge, double MLMinLoan, double MLMaxLoan, int MMinPeriodDays, int MMaxPeriodDays,
                      int MMinServiceFee, int MMaxServiceFee,String currancy) {
        super(productCode, bankId, bankName, bankWebSite, minAge, maxAge, currancy);
       this.MLId = mlId;
       this.productName = productName;
        this.MLMinAmount = MLMinAmount;
        this.MLMaxAmount = MLMaxAmount;
        this.MLMinLoan = MLMinLoan;
        this.MLMaxLoan = MLMaxLoan;
        this.MMinPeriodDays = MMinPeriodDays;
        this.MMaxPeriodDays = MMaxPeriodDays;
        this.MMinServiceFee = MMinServiceFee;
        this.MMaxServiceFee = MMaxServiceFee;

    }

    public String getBanksList() throws SQLException {
        return banksDaoController.getBankImageById(super.getBankId());

    }

    public String setBanksList(List<MicroLoans> depositList) throws SQLException {
        String bankImage = null;
        int bankid = 0;
        for (int i = 0; i < depositList.size(); i++) {
            bankid = depositList.get(i).getBankId();
            bankImage = banksDaoController.getBankBigImageById(bankid);
        }
        return bankImage;
    }

    public int getLastLogic() {
        return lastLogic;
    }

    public void setLastLogic(int lastLogic) {
        this.lastLogic = lastLogic;
    }

    public String getBankLink() {
        return BankLink;
    }

    public void setBankLink(String bankLink) {
        BankLink = bankLink;
    }

    public int getLastlogic() {
        return lastLogic;
    }

    public void setLastlogic(int lastlogic) {
        this.lastLogic = lastlogic;
    }

    public Long getMLId() {
        return MLId;
    }

    public void setMLId(Long MLId) {
        this.MLId = MLId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getMLMinAmount() {
        return MLMinAmount;
    }

    public void setMLMinAmount(int MLMinAmount) {
        this.MLMinAmount = MLMinAmount;
    }

    public int getMLMaxAmount() {
        return MLMaxAmount;
    }

    public void setMLMaxAmount(int MLMaxAmount) {
        this.MLMaxAmount = MLMaxAmount;
    }

    public double getMLMinLoan() {
        return MLMinLoan;
    }

    public void setMLMinLoan(double MLMinLoan) {
        this.MLMinLoan = MLMinLoan;
    }

    public double getMLMaxLoan() {
        return MLMaxLoan;
    }

    public void setMLMaxLoan(double MLMaxLoan) {
        this.MLMaxLoan = MLMaxLoan;
    }

    public double getMLFatual() {
        return MLFatual;
    }

    public void setMLFatual(double MLFatual) {
        this.MLFatual = MLFatual;
    }

    public int getMMinPeriodDays() {
        return MMinPeriodDays;
    }

    public void setMMinPeriodDays(int MMinPeriodDays) {
        this.MMinPeriodDays = MMinPeriodDays;
    }

    public int getMMaxPeriodDays() {
        return MMaxPeriodDays;
    }

    public void setMMaxPeriodDays(int MMaxPeriodDays) {
        this.MMaxPeriodDays = MMaxPeriodDays;
    }

    public int getMMinServiceFee() {
        return MMinServiceFee;
    }

    public void setMMinServiceFee(int MMinServiceFee) {
        this.MMinServiceFee = MMinServiceFee;
    }

    public int getMMaxServiceFee() {
        return MMaxServiceFee;
    }

    public void setMMaxServiceFee(int MMaxServiceFee) {
        this.MMaxServiceFee = MMaxServiceFee;
    }

}