package com.ithome.web.Helpers;

import com.ithome.web.AdminDao.CardsDao;
import com.ithome.web.Bean.Cards;

import java.sql.SQLException;
import java.util.List;

public final class  CardsSpecialOfferHelper implements SpecialOfferHellper<Cards> {
    private final CardsDao cardsDao = new CardsDao();
    @Override
    public final List<Cards> getStarted() throws SQLException {
        return cardsDao.getCardsWithSpecialOffer();
    }

    @Override
    public List<Cards> getAppearnace() {
        return null;
    }



}
