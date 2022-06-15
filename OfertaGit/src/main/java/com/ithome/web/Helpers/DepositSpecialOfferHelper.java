package com.ithome.web.Helpers;

import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.Bean.Cards;
import com.ithome.web.Bean.Deposit;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class DepositSpecialOfferHelper implements SpecialOfferHellper<Deposit> {
    private DepositDaoController depositDaoController = new DepositDaoController();
    @Override
    public List<Deposit> getStarted() throws SQLException {
        return depositDaoController.getDepositWithSpecialOffer();
    }

    @Override
    public List<Deposit> getAppearnace() throws SQLException {
        return depositDaoController.getDepositWithAppearance();
    }


}
