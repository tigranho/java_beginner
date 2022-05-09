package com.ithome.web.Helpers;

import com.ithome.web.AdminDao.MortgageDaoController;
import com.ithome.web.Bean.Mortgage;

import java.sql.SQLException;
import java.util.List;

public class MortgageSpecialOfferHelper implements SpecialOfferHellper<Mortgage> {

    private MortgageDaoController mortgageDaoController = new MortgageDaoController();
    @Override
    public List<Mortgage> getStarted() throws SQLException {
        return mortgageDaoController.getMortgageWithSpecialOffer();
    }

    @Override
    public List<Mortgage> getAppearnace() throws SQLException {
        return mortgageDaoController.getMortgageWithAppearance();
    }


}
