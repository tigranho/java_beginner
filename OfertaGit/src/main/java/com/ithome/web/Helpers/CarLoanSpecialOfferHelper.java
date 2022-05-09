package com.ithome.web.Helpers;

import com.ithome.web.AdminDao.CarLoanDao;
import com.ithome.web.Bean.CarLoans;

import java.sql.SQLException;
import java.util.List;

public class CarLoanSpecialOfferHelper implements SpecialOfferHellper<CarLoans> {
    private CarLoanDao carLoanDao = new CarLoanDao();

    @Override
    public List<CarLoans> getStarted() throws SQLException {
        return carLoanDao.getCarLoanWithSpecialOffer();
    }

    @Override
    public List<CarLoans> getAppearnace() throws SQLException {
        return carLoanDao.getDepositWithAppearance();
    }



}
