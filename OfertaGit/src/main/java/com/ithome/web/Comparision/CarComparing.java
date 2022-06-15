package com.ithome.web.Comparision;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.Helpers.PojoHelper;

import java.sql.SQLException;
import java.util.List;

public class CarComparing extends PojoHelper {
    private int id;
    private int idCheck;
    private String SID;
    private int Amount;
    private int months;
    private int bankId;
    private String productName;
    private double percentage;
    private int Service;
    private int perpaiment;
    private BanksDaoController banksDaoController = new BanksDaoController();


    public CarComparing() {
    }

    public CarComparing(int idCheck, String SID, int amount, int months, int bankId, String productName, double percentage, int Service, int prepaiment) {
        this.idCheck = idCheck;
        this.SID = SID;
        Amount = amount;
        this.months = months;
        this.bankId = bankId;
        this.productName = productName;
        this.percentage = percentage;
        this.Service = Service;
        this.perpaiment=prepaiment;
    }


    public int getPerpaiment() {
        return perpaiment;
    }

    public void setPerpaiment(int perpaiment) {
        this.perpaiment = perpaiment;
    }

    public int getService() {
        return Service;
    }

    public void setService(int service) {
        Service = service;
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

    @Override
    public int getBankId() {
        return bankId;
    }

    @Override
    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }


    public String getBanksList() throws SQLException {
        return banksDaoController.getBankImageById(super.getBankId());

    }

    public String setBanksList(List<CarComparing> depositList) throws SQLException {
        String bankImage = null;
        int bankid = 0;
        for (int i = 0; i < depositList.size(); i++) {
            bankid = depositList.get(i).getBankId();
            bankImage = banksDaoController.getBankBigImageById(bankid);
        }
        return bankImage;
    }
}
