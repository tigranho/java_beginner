package com.ithome.web.Helpers;

import com.ithome.web.Bean.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader implements Runnable {

    private List<DropDowns> dropDownsList = new ArrayList<>();
    private List<DropDowns> dropDownsListWithCurrancy = new ArrayList<>();
    private List<Deposit> depositListOffer = new ArrayList();
    private List<ProductName> productNameList = new ArrayList<>();
    private List<Mortgage> mortgageListOffer = new ArrayList();
    private List<ConsumerCredit> consumerCreditListoffer = new ArrayList();
    private List<CarLoans> carLoansListoffer = new ArrayList();
    private List<AgriculturalCredit> agriculturalCreditListoffer = new ArrayList();
    private List<Cards> cardsListOffer = new ArrayList();


    private MortgageSpecialOfferHelper mortgageSpecialOfferHelper = new MortgageSpecialOfferHelper();
    private ConsumerSpecialOfferHelper consumerSpecialOfferHelper = new ConsumerSpecialOfferHelper();
    private CarLoanSpecialOfferHelper carLoanSpecialOfferHelper = new CarLoanSpecialOfferHelper();
    private DepositSpecialOfferHelper depositSpecialOfferHelper = new DepositSpecialOfferHelper();
    private AgSpecialOfferHelper agSpecialOfferHelper = new AgSpecialOfferHelper();
    private CardsSpecialOfferHelper cardsSpecialOfferHelper = new CardsSpecialOfferHelper();


    @Override
    public void run() {
        try {
            getData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getData() throws SQLException {
        cardsListOffer = cardsSpecialOfferHelper.getStarted();
        agriculturalCreditListoffer = agSpecialOfferHelper.getStarted();
        carLoansListoffer = carLoanSpecialOfferHelper.getStarted();
        consumerCreditListoffer = consumerSpecialOfferHelper.getStarted();
        mortgageListOffer = mortgageSpecialOfferHelper.getStarted();
        depositListOffer = depositSpecialOfferHelper.getStarted();

    }
}
