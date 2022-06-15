package com.ithome.web.Helpers;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.Bean.Banks;


import java.sql.SQLException;
import java.util.List;

public class BankHelper {
    private BanksDaoController banksDaoController = new BanksDaoController();

    public List<Banks> bankFullList () throws SQLException {
        return banksDaoController.getAllBanksList();
    }
}
