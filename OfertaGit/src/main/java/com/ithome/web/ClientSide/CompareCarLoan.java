package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CarLoanDao;
import com.ithome.web.AdminDao.CardsDao;
import com.ithome.web.AdminDao.ConsumerCreditDaoController;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Comparision.CarComDao;
import com.ithome.web.Comparision.CarComparing;
import com.ithome.web.Comparision.ConsumerComparing;
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

@WebServlet("/CompareCarLoan")
public class CompareCarLoan extends HttpServlet {
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
    List<CarLoans> CarLoanCompareList = new ArrayList<>();

    CarLoanDao carLoanDao = new CarLoanDao();

    private String NewAmount = null;
    private String idNew = null;
    private int iDCheck = 0;
    private String Months = null;
    private String montheToDays = null;

    List<CarComparing> consumerCreditsCompareList = new ArrayList<>();
    List<CarLoans> ConsumerMainList = new ArrayList<>();
    List<Banks> BanksList = new ArrayList<>();

    private int CounsumerCounter=0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            compareCarLoan(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            compareCarLoan(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void compareCarLoan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        //getCityFromUser(request);
        getPageLanguage(language);
        getParameters(request);
        getMainPageRange();
        checkForCompareList();
        GetDropDownByCurrancy(dropDownsList, pageCurrancy);
        getCompareMortgageList(request);

        createRequestes(request);
        GOTOPage(request, response);
    }
    private void checkForCompareList() {
        comparListDeposit = CompareHelper.getDepositList(sessionId);
        comparListMortgage = CompareHelper.getMortgageList(sessionId);
        comparListConsumer =  CompareHelper.getConsumerList(sessionId);
        comparListCarLoan =  CompareHelper.getCarLoanList(sessionId);
        comparListMicro =  CompareHelper.getMicroList(sessionId);
        comparListAg = CompareHelper.getAgList(sessionId);
        comparListCard =  CompareHelper.getCardList(sessionId);
    }

    private void GetDropDownByCurrancy(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getMainPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName("ԳԼԽԱՎՈՐ");
    }

    private void GOTOPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/CCarLoan.jsp").forward(request, response);
    }

    private void RemoveId(HttpServletRequest request, int IDRemove, String session) throws SQLException {
        CompareHelper.DeleteAutoById(IDRemove);
        CarComDao carComDao = new CarComDao();
        int deleteCheck = carComDao.Delete(IDRemove, session);
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
        if (request.getParameter("Pagelanguage") == null) {
            language = languageHelper.Pagelanguage(request, Pagelanguage);
        } else {
            language = languageHelper.Pagelanguage(request, request.getParameter("Pagelanguage"));
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

    private void createRequestes(HttpServletRequest request) {
        request.setAttribute("Pagelanguage", Pagelanguage);
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
        request.setAttribute("BanksList", BanksList);

        request.setAttribute("WorningMessage", WorningMessage);
        request.setAttribute("DepositCompareSize", DepositCompareSize);
        request.setAttribute("name", "deposit");
        request.setAttribute("PageToGo", PageToGo);

        request.setAttribute("consumerCreditsCompareList", consumerCreditsCompareList);

        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);

        request.setAttribute("CounsumerCounter", CounsumerCounter);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);

    }

    private void getCompareMortgageList(HttpServletRequest request) throws SQLException {
        comparListConsumer = CompareHelper.getCarLoanList(sessionId);
        CarComDao carComDao = new CarComDao();
        consumerCreditsCompareList = new ArrayList<>();
        for (int i = 0; i < comparListConsumer.size(); i++) {
            int id = comparListConsumer.get(i);
            consumerCreditsCompareList.addAll(carComDao.getById(id, sessionId));
        }
        CarLoanDao carLoanDao = new CarLoanDao();
        ConsumerMainList = carLoanDao.getAllCarLoans();
        BanksDaoController banksDaoController = new BanksDaoController();
        BanksList=banksDaoController.getAllBanksList();
    }

    private void filterAmount(String amount) {
        if (amount.contains(",")) {
            amoutFiltered = amount.replace(",", "");
            NewAmount = amoutFiltered;
        } else {
            amoutFiltered = amount;
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

        if (request.getParameter("clientCode") != null) {
            sessionId = request.getParameter("clientCode");
        }

        if (request.getParameter("NewAmount") != null) {
            String amountRequest = request.getParameter("NewAmount");
            filterAmount(amountRequest);
        }
        if (request.getParameter("idNew") != null) {
            idNew = request.getParameter("idNew");
            UpdateDetails(NewAmount, idNew, sessionId);
        } else {
            NewAmount = "";
            idNew = "";
        }
        if (request.getParameter("months") != null) {
            Months = request.getParameter("months");
            //convertToDays(Months);
        }
        if (request.getParameter("idCheck1") != null) {
            iDCheck = Integer.parseInt(request.getParameter("idCheck1"));
            Months = request.getParameter("months1");
            // convertToDays(Months);
            UpdateOtherDetails(Integer.parseInt(Months), iDCheck, sessionId);
        }
        if (request.getParameter("idCheck2") != null) {
            iDCheck = Integer.parseInt(request.getParameter("idCheck2"));
            Months = request.getParameter("months2");
            // convertToDays(Months);
            UpdateOtherDetails(Integer.parseInt(Months), iDCheck, sessionId);
        }
        if (request.getParameter("idCheck3") != null) {
            iDCheck = Integer.parseInt(request.getParameter("idCheck3"));
            Months = request.getParameter("months3");
            //convertToDays(Months);
            UpdateOtherDetails(Integer.parseInt(Months), iDCheck, sessionId);
        }
        if (request.getParameter("idCheck4") != null) {
            iDCheck = Integer.parseInt(request.getParameter("idCheck4"));
            Months = request.getParameter("months4");
            // convertToDays(Months);
            UpdateOtherDetails(Integer.parseInt(Months), iDCheck, sessionId);
        } else {
            Months = "";
            iDCheck = 0;
        }
    }


    private void UpdateOtherDetails(int months, int iDCheck, String sessionId) throws SQLException {
        CarComDao carComDao = new CarComDao();
        int updateCheck = carComDao.UpdateOthers(months, iDCheck, sessionId);
        if (updateCheck == 0) {
            System.out.println("failed");
        } else {
            System.out.println("success");
        }
    }

    private void UpdateDetails(String newAmount, String idNew, String sessionId) throws SQLException {
        CarComDao carComDao = new CarComDao();
        int updateCheck = carComDao.Update(Integer.parseInt(newAmount), Integer.parseInt(idNew), sessionId);
        if (updateCheck == 0) {
            System.out.println("failed");
        } else {
            System.out.println("success");
        }
    }
}
