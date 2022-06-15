package com.ithome.web.Helpers;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.Bean.Banks;

import java.sql.SQLException;
import java.util.List;


public class GetBankFullInfo {
    private BanksDaoController banksDaoController = new BanksDaoController();
    private List<Banks> getBankDetail() throws SQLException {
        return banksDaoController.getAllBanksList();
    }

    public List<Banks> contactBankDetail() throws SQLException {
        return getBankDetail();
    }
}
