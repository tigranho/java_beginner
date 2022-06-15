package com.ithome.web.util;

import com.ithome.web.Bean.*;
import com.ithome.web.Helpers.*;

import java.sql.SQLException;
import java.util.List;

public class Offers extends Thread {
    private CardsSpecialOfferHelper cardsSpecialOfferHelper = new CardsSpecialOfferHelper();
    private AgSpecialOfferHelper agSpecialOfferHelper = new AgSpecialOfferHelper();
    private CarLoanSpecialOfferHelper carLoanSpecialOfferHelper = new CarLoanSpecialOfferHelper();
    private ConsumerSpecialOfferHelper consumerSpecialOfferHelper = new ConsumerSpecialOfferHelper();
    private MortgageSpecialOfferHelper mortgageSpecialOfferHelper = new MortgageSpecialOfferHelper();
    private DepositSpecialOfferHelper depositSpecialOfferHelper = new DepositSpecialOfferHelper();

    @Override
    public void run() {
        try {
            getCardsSpecialOffers();
            getAgSpecialOffers();
            getCarLoanSpecialOffers();
            getConsumerSpecialOffers();
            getMortgageSpecialOffers();
            getDepositSpecialOffers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Cards> getCardsSpecialOffers() throws SQLException {
        return cardsSpecialOfferHelper.getStarted();
    }

    public List<AgriculturalCredit> getAgSpecialOffers() throws SQLException {
        return agSpecialOfferHelper.getStarted();
    }

    public List<CarLoans> getCarLoanSpecialOffers() throws SQLException {
        return carLoanSpecialOfferHelper.getStarted();
    }

    public List<ConsumerCredit> getConsumerSpecialOffers() throws SQLException {
        return consumerSpecialOfferHelper.getStarted();
    }

    public List<Mortgage> getMortgageSpecialOffers() throws SQLException {
        return mortgageSpecialOfferHelper.getStarted();
    }

    public List<Deposit> getDepositSpecialOffers() throws SQLException {
        return depositSpecialOfferHelper.getStarted();
    }
}
