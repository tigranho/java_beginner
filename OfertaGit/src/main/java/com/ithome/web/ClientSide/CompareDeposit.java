package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.CardsDao;
import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.AdminDao.MortgageDaoController;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Comparision.DepositComDao;
import com.ithome.web.Comparision.DepositComparing;
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
import java.util.List;

@WebServlet("/CompareDeposit")
public class CompareDeposit extends HttpServlet {
    private CheckLanguageAndCurrency checkLanguageAndCurrency = new CheckLanguageAndCurrency();
    private String language = null;
    private String Pagelanguage = null;
    private String pageName = null;
    private String city = null;
    private String pageLanguageName = null;
    private String sessionId = null;
    private String Amount = null;
    private String PageToGo = null;
    private String amoutFiltered = null;
    private String MaxAmount = null;

    private List<DropDowns> dropDownsList = new ArrayList<>();
    private List<DropDowns> dropDownsListWithCurrancy = new ArrayList<>();
    private List<Deposit> depositListOffer = new ArrayList();
    private List<ProductName> productNameList = new ArrayList<>();
    private List<Mortgage> mortgageListOffer = new ArrayList();
    private List<ConsumerCredit> consumerCreditListoffer = new ArrayList();
    private List<CarLoans> carLoansListoffer = new ArrayList();
    private List<AgriculturalCredit> agriculturalCreditListoffer = new ArrayList();
    private List<Cards> cardsListOffer = new ArrayList();
    private List<Banks> banksList = new ArrayList<>();
    private List<UsefulArticles> usefulArticlesList = new ArrayList<>();

    private List<Cards> cardsList = new ArrayList<>();

    private MortgageSpecialOfferHelper mortgageSpecialOfferHelper = new MortgageSpecialOfferHelper();
    private ConsumerSpecialOfferHelper consumerSpecialOfferHelper = new ConsumerSpecialOfferHelper();
    private CarLoanSpecialOfferHelper carLoanSpecialOfferHelper = new CarLoanSpecialOfferHelper();
    private DepositSpecialOfferHelper depositSpecialOfferHelper = new DepositSpecialOfferHelper();
    private AgSpecialOfferHelper agSpecialOfferHelper = new AgSpecialOfferHelper();
    private CardsSpecialOfferHelper cardsSpecialOfferHelper = new CardsSpecialOfferHelper();
    private DropDownCurrancyHelper dropDownCurrancyHelper = new DropDownCurrancyHelper();
    private DropDownHelper dropDownHelper = new DropDownHelper();
    private CardsDao cardsDao = new CardsDao();

    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private LookUpProgram lookUpProgram = new LookUpProgram();
    private LanguageHelper languageHelper = new LanguageHelper();
    private PageNameHelper pageNameHelper = new PageNameHelper();


    private List<Integer> comparListDeposit = new ArrayList<>();
    private List<Integer> comparListMortgage = new ArrayList<>();
    private static List<Integer> comparListConsumer = new ArrayList<>();
    private static List<Integer> comparListCarLoan = new ArrayList<>();
    private static List<Integer> comparListMicro = new ArrayList<>();
    private static List<Integer> comparListAg = new ArrayList<>();
    private static List<Integer> comparListCard = new ArrayList<>();
    private String WorningMessage = null;
    private int DepositCompareSize = 0;
    private int CardId = 0;
    private int IDRemove = 0;
    private String Currancy = null;
    private String PageNameToDelete = null;
    private String NewAmount = null;
    private String idNew = null;
    DepositDaoController depositDaoController = new DepositDaoController();
    List<DepositComparing> DepositComapreList = new ArrayList<>();
    List<Deposit> DepositDataList = new ArrayList<>();

    private int idCheck = 0;
    private int AmountCompare = 0;
    private int MonthsCompare = 0;
    private int SID = 0;

    private int CounsumerCounter = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            compareDeposit(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            compareDeposit(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void compareDeposit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        getPageLanguage(language);
        getParameters(request);
        getMainPageRange();
        GetDropDownByCurrancy(dropDownsList, pageCurrancy);
        getCompareMortgageList(request, response);
        createRequestes(request);
        GOTOPage(request, response);
    }


    private void GetDropDownByCurrancy(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getMainPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName("ԳԼԽԱՎՈՐ");
    }

    private void GOTOPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/CDeposit.jsp").forward(request, response);
    }

    private void createRequestes(HttpServletRequest request) {
        request.setAttribute("PageLanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("PageName", pageName);
        request.setAttribute("City", city);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);

        request.setAttribute("depositListOffer", depositListOffer);
        request.setAttribute("cardsListOffer", cardsListOffer);
        request.setAttribute("agriculturalCreditListoffer", agriculturalCreditListoffer);
        request.setAttribute("carLoansListoffer", carLoansListoffer);
        request.setAttribute("consumerCreditListoffer", consumerCreditListoffer);
        request.setAttribute("MortgageListOffer", mortgageListOffer);
        request.setAttribute("productNameList", productNameList);
        request.setAttribute("BanksList", banksList);

        request.setAttribute("WorningMessage", WorningMessage);
        request.setAttribute("DepositCompareSize", DepositCompareSize);
        request.setAttribute("name", "deposit");
        request.setAttribute("PageToGo", PageToGo);

        request.setAttribute("DepositComapreList", DepositComapreList);
        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("DepositDataList", DepositDataList);
        request.setAttribute("NewAmount", NewAmount);
        request.setAttribute("NEW", idNew);

    }


    private void checkForCompareList() {
        comparListDeposit = CompareHelper.getDepositList(sessionId);
        comparListMortgage = CompareHelper.getMortgageList(sessionId);
        comparListConsumer = CompareHelper.getConsumerList(sessionId);
        comparListCarLoan = CompareHelper.getCarLoanList(sessionId);
        comparListMicro = CompareHelper.getMicroList(sessionId);
        comparListAg = CompareHelper.getAgList(sessionId);
        comparListCard = CompareHelper.getCardList(sessionId);
    }


    private void getCompareMortgageList(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        comparListDeposit = CompareHelper.getDepositList(sessionId);
        DepositComDao depositComDao = new DepositComDao();
        DepositComapreList = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < comparListDeposit.size(); i++) {
            int id = comparListDeposit.get(i);
            DepositComapreList.addAll(depositComDao.getById(id, sessionId));
        }
    }

    /* private void getData(int id) throws SQLException {

         DepositComapreList = ;
         System.out.println(DepositComapreList.size());
         *//*for (int i = 0; i <DepositComapreList.size() ; i++) {
            System.out.println(DepositComapreList.get(i).getId());
        }*//*
    }*/
    private void filterAmount(String amoutFiltered) {
        NewAmount = "";
        if (amoutFiltered.contains(",")) {
            NewAmount = amoutFiltered.replace(",", "");
        } else {
            NewAmount = amoutFiltered;
        }
        System.out.println("amoutFiltered " + amoutFiltered);
    }

    private void getParameters(HttpServletRequest request) throws SQLException {
        Currancy = request.getParameter("Currancy");
        if (request.getParameter("IDToRemove") != null) {
            IDRemove = Integer.parseInt(request.getParameter("IDToRemove"));
            RemoveId(request, IDRemove, sessionId);
        } else {
            IDRemove = 0;
        }

        if (request.getParameter("NewAmount") != null) {
            NewAmount = request.getParameter("NewAmount");
            filterAmount(NewAmount);
        }
        if (request.getParameter("idNew") != null) {
            idNew = request.getParameter("idNew");
            UpdateDetails(NewAmount, idNew, sessionId);
        } else {
            NewAmount = "";
            idNew = "";
        }


    }


    private void UpdateDetails(String newAmount, String idNew, String sessionId) throws SQLException {
        DepositComDao depositComDao = new DepositComDao();
        int updateCheck = depositComDao.Update(Integer.parseInt(newAmount), Integer.parseInt(idNew), sessionId);
        if (updateCheck == 0) {
            System.out.println("failed");
        } else {
            System.out.println("success");
        }
    }

    private void RemoveId(HttpServletRequest request, int IDRemove, String session) throws SQLException {
        CompareHelper.DeleteDepositeById(IDRemove);
        DepositComDao depositComDao = new DepositComDao();
        int deleteCheck = depositComDao.Delete(IDRemove, session);
        if (deleteCheck == 0) {
            System.out.println("failed");
        } else {
            System.out.println("success");
        }
    }

    private void getPageLanguage(String language) {
        Pagelanguage = checkLanguageAndCurrency.checkLanguage(language);
        pageLanguageName = checkLanguageAndCurrency.checkLanguageName(language);
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

    private String getLanguagesFromPage(HttpServletRequest request) {
        if (request.getParameter("PageLanguage") == null) {
            language = languageHelper.Pagelanguage(request, Pagelanguage);
        } else {
            language = languageHelper.Pagelanguage(request, request.getParameter("PageLanguage"));
        }
        return language;
    }

    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        sessionId = session.getId();
        request.setAttribute("session",sessionId);
        session.setMaxInactiveInterval(86400);
        CompareHelper.sendSession(sessionId);
    }
}

