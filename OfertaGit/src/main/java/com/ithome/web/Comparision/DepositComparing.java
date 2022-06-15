package com.ithome.web.Comparision;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.Bean.Deposit;
import com.ithome.web.Helpers.PojoHelper;

import java.sql.SQLException;
import java.util.List;

public class DepositComparing extends PojoHelper {
    private int id;
    private int idCheck;
    private String SID;
    private int Amount;
    private int months;
    private int bankId;
    private String productName;
    private int DEquippingPossibilitiesid;
    private int DEarlierWithdrawalAmountid;
    private int DAutoExtendPeriodid;
    private int DCapitalizationPercentid;
    private double percentage;
    private BanksDaoController banksDaoController = new BanksDaoController();



    public DepositComparing(int id, String sessionId, int parseInt, int i, int bankId, String productName, int dEquippingPossibilitiesid, int dEarlierWithdrawalAmountid, int dAutoExtendPeriodid, int dCapitalizationPercentid,double percentage) {
        this.idCheck = id;
        this.SID = sessionId;
        this.Amount = parseInt;
        this.months = i;
        this.bankId = bankId;
        this.productName = productName;
        this.DEquippingPossibilitiesid = dEquippingPossibilitiesid;
        this.DEarlierWithdrawalAmountid = dEarlierWithdrawalAmountid;
        this.DAutoExtendPeriodid = dAutoExtendPeriodid;
        this.DCapitalizationPercentid = dCapitalizationPercentid;
        this.percentage = percentage;
    }

    @Override
    public int getBankId() {
        return bankId;
    }

    @Override
    public void setBankId(int bankId) {
        this.bankId = bankId;
    }


    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public DepositComparing() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCheck() {
        return idCheck;
    }

    public void setIdCheck(int idCheck) {
        this.idCheck = idCheck;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getDEquippingPossibilitiesid() {
        return DEquippingPossibilitiesid;
    }

    public void setDEquippingPossibilitiesid(int DEquippingPossibilitiesid) {
        this.DEquippingPossibilitiesid = DEquippingPossibilitiesid;
    }

    public int getDEarlierWithdrawalAmountid() {
        return DEarlierWithdrawalAmountid;
    }

    public void setDEarlierWithdrawalAmountid(int DEarlierWithdrawalAmountid) {
        this.DEarlierWithdrawalAmountid = DEarlierWithdrawalAmountid;
    }


    public int getDAutoExtendPeriodid() {
        return DAutoExtendPeriodid;
    }

    public void setDAutoExtendPeriodid(int DAutoExtendPeriodid) {
        this.DAutoExtendPeriodid = DAutoExtendPeriodid;
    }


    public int getDCapitalizationPercentid() {
        return DCapitalizationPercentid;
    }

    public void setDCapitalizationPercentid(int DCapitalizationPercentid) {
        this.DCapitalizationPercentid = DCapitalizationPercentid;
    }


    public String getBanksList() throws SQLException {
        return banksDaoController.getBankImageById(super.getBankId());

    }

    public String setBanksList(List<DepositComparing> depositList) throws SQLException {
        String bankImage = null;
        int bankid = 0;
        for (int i = 0; i < depositList.size(); i++) {
            bankid = depositList.get(i).getBankId();
            bankImage = banksDaoController.getBankBigImageById(bankid);
        }
        return bankImage;
    }
}