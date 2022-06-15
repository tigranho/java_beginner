package com.ithome.web.Helpers;

import com.ithome.web.AdminDao.MicroLoanDaoController;
import com.ithome.web.Bean.MicroLoans;

import java.sql.SQLException;
import java.util.List;

public class MicroLoanSpecialOfferHelper implements SpecialOfferHellper<MicroLoans> {
    private MicroLoanDaoController microLoanDaoController = new MicroLoanDaoController();
    @Override
    public List<MicroLoans> getStarted() throws SQLException {
        return microLoanDaoController.getMicroWithSpecialOffer();
    }

    @Override
    public List<MicroLoans> getAppearnace() throws SQLException {
        return microLoanDaoController.getDepositWithAppearance();
    }



}
