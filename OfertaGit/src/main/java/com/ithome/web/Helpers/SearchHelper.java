package com.ithome.web.Helpers;

import com.ithome.web.AdminDao.*;
import com.ithome.web.Bean.*;

import java.sql.SQLException;
import java.util.List;

public class SearchHelper {

    private AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();
    private BanksDaoController banksDaoController = new BanksDaoController();
    private CardsDao cardsDao = new CardsDao();
    private CarLoanDao carLoanDao = new CarLoanDao();
    private ConsumerCreditDaoController consumerCreditDaoController = new ConsumerCreditDaoController();
    private DepositDaoController depositDaoController = new DepositDaoController();
    private MicroLoanDaoController microLoanDaoController = new MicroLoanDaoController();
    private MortgageDaoController mortgageDaoController = new MortgageDaoController();

    private SearchDeep deepSearch = new SearchDeep();

   /* public List<Banks> searchBank(String search) throws SQLException {
        return deepSearch.searchBankResult(banksDaoController.getAllBanksList(), search);
    }

    public List<AgriculturalCredit> searchAg(String search) throws SQLException {
        return deepSearch.searchAgResult(agriculturalCreditDao.getAllAgriculturalCreditList(), search);
    }

    public List<Cards> searchCards(String search) throws SQLException {
        return deepSearch.searchCardResult(cardsDao.getAllCardsList(), search);
    }

    public List<CarLoans> searchCarLoans(String search) throws SQLException {
        return deepSearch.searchCarLoanResult(carLoanDao.getAllCarLoans(), search);
    }


    public List<ConsumerCredit> searchCC(String search) throws SQLException {
        return deepSearch.searchCCResult(consumerCreditDaoController.getAllCardsList(), search);
    }

    public List<Deposit> searchDeposit(String search) throws SQLException {
        return deepSearch.searchD(depositDaoController.getAllDepositeList(), search);
    }

    public List<MicroLoans> serahcMicro(String search) throws SQLException {
        return deepSearch.searchMicroLoan(microLoanDaoController.getAllMicroLoans(), search);
    }

    public List<Mortgage> serahcMortgage(String search) throws SQLException {
        return deepSearch.searchMortgages(mortgageDaoController.getAllMortage(), search);
    }*/

}