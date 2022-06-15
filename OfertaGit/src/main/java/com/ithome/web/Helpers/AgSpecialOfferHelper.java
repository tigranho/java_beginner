package com.ithome.web.Helpers;

import com.ithome.web.AdminDao.AgriculturalCreditDao;
import com.ithome.web.Bean.AgriculturalCredit;

import java.sql.SQLException;
import java.util.List;

public class AgSpecialOfferHelper implements SpecialOfferHellper<AgriculturalCredit> {
    private AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();

    public List<AgriculturalCredit> getStarted() throws SQLException {
        return agriculturalCreditDao.getAGWithSpecialOffer();
    }

    @Override
    public List<AgriculturalCredit> getAppearnace() throws SQLException {
        return agriculturalCreditDao.getAgWithAppearance();
    }




}
