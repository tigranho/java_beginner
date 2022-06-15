package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.*;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Helpers.*;
import com.ithome.web.Localization.CheckLanguageAndCurrency;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/SearchWeb")
public class Search extends HttpServlet {
    private CheckLanguageAndCurrency checkLanguageAndCurrency = new CheckLanguageAndCurrency();
    private String language = null;
    private String country = null;
    private String Pagelanguage = null;
    private String pageName = null;
    private String pageLanguageName = null;
    private SessionChecker checker = new SessionChecker();
    private String sessionId = null;
    private LanguageHelper languageHelper = new LanguageHelper();
    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private String city = null;
    private LookUpProgram lookUpProgram = new LookUpProgram();
    private PageNameHelper pageNameHelper = new PageNameHelper();
    private String Search =null;
    private SearchHelper searchHelper = new SearchHelper();
    private List<Banks> BSearchResult = new ArrayList<>();
    private List<AgriculturalCredit> AGSearchResult = new ArrayList<>();
    private List<Cards> CardsSearchResult = new ArrayList<>();
    private List<CarLoans> carLoansList = new ArrayList<>();
    private List<ConsumerCredit> consumerCreditList = new ArrayList<>();
    private List<Deposit> depositList = new ArrayList<>();
    private List<MicroLoans> microLoansList = new ArrayList<>();
    private List<Mortgage>mortgageList = new ArrayList<>();



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        searchWeb(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        searchWeb(request, response);
    }

    private void searchWeb(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        //getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getPageLabguageName(language);
        getParameter(request);
        setRequest(request);
        gotoPage(request,response);
    }


    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/SearchDetail.jsp").forward(request, response);
    }

    private void setRequest(HttpServletRequest request) {
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("PageName", pageName);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("BSearchResult", BSearchResult);
        request.setAttribute("AGSearchResult", AGSearchResult);
        request.setAttribute("CardsSearchResult", CardsSearchResult);
        request.setAttribute("carLoansList", carLoansList);
        request.setAttribute("consumerCreditList", consumerCreditList);
        request.setAttribute("depositList", depositList);
        request.setAttribute("microLoansList", microLoansList);
        request.setAttribute("mortgageList", mortgageList);
        request.setAttribute("City", city );
    }


    private void StartSearch(String search) throws SQLException {
        DepositDaoController depositDaoController = new DepositDaoController();
        depositList = depositDaoController.getAllDepositeList();

        BanksDaoController banksDaoController = new BanksDaoController();
        BSearchResult = banksDaoController.getAllBanksList();

        CarLoanDao carLoanDao = new CarLoanDao();
        carLoansList = carLoanDao.getAllCarLoans();

        AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();
        AGSearchResult = agriculturalCreditDao.getAllAgriculturalCreditList();

        CardsDao cardsDao = new CardsDao();
        CardsSearchResult = cardsDao.getAllCardsList();

        ConsumerCreditDaoController  consumerCreditDaoController = new ConsumerCreditDaoController();
        consumerCreditList = consumerCreditDaoController.getAllCardsList();

        MicroLoanDaoController microLoanDaoController = new MicroLoanDaoController();
        microLoansList  = microLoanDaoController.getAllMicroLoans();

        MortgageDaoController mortgageDaoController = new MortgageDaoController();
        mortgageList = mortgageDaoController.getAllMortage();
        //BSearchResult = searchHelper.searchBank(search);
        //carLoansList = searchHelper.searchCarLoans(search);
        //AGSearchResult = searchHelper.searchAg(search);
        //CardsSearchResult = searchHelper.searchCards(search);
        //consumerCreditList = searchHelper.searchCC(search);
        //depositList = searchHelper.searchDeposit(search);
        //microLoansList = searchHelper.serahcMicro(search);
        //mortgageList = searchHelper.serahcMortgage(search);
    }

    private void getParameter(HttpServletRequest request) {
        String search[] = request.getParameter("Search").split(" ");
        System.out.println("output string: " + Arrays.toString(search));
        for (int i = 0; i <search.length ; i++) {
            try {
                StartSearch(search[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void getPageLabguageName(String language) {
        pageLanguageName = checkLanguageAndCurrency.checkLanguageName(language);
    }

    private void getPageLanguage(String language) {
        Pagelanguage = checkLanguageAndCurrency.checkLanguage(language);

    }

    private void getPageName(HttpServletRequest request) {
        pageName = pageNameHelper.pageName(request);
    }


    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        sessionId = session.getId();
        request.setAttribute("session",sessionId);
        session.setMaxInactiveInterval(86400);
        getUserSession(session, request, response);
        CompareHelper.sendSession(sessionId);
    }

    private String getLanguagesFromPage(HttpServletRequest request) {
        if (request.getParameter("Pagelanguage") == null) {
            language = languageHelper.Pagelanguage(request, Pagelanguage);
        } else {
            language = languageHelper.Pagelanguage(request, request.getParameter("Pagelanguage"));
        }
        return language;
    }

    private void getUserSession(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        if (checker.checkSessionUser(request, response)) {
            sessionId = checker.requestSessionofUser(session);
        } else {
            sessionId = session.getId();
        }
    }

    private void getCityFromUser(HttpServletRequest request) throws IOException {
        if (request.getParameter("city") == null) {
            city = LookUpProgram.start(request);
        } else {
            city = request.getParameter("city");
        }
    }

    private void getCurancyFromPage(HttpServletRequest request) {
        if (request.getParameter("Currancy") == null) {
            pageCurrancy = checkLanguageAndCurrency.checkCurrency(pageCurrancyFromPage);
        } else {
            pageCurrancy = checkLanguageAndCurrency.checkCurrency(request.getParameter("Currancy"));
        }
    }
}
