package com.ithome.web.starter;


import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.AdminDao.UsefullArticlesDaoController;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Helpers.*;
import com.ithome.web.Localization.CheckLanguageAndCurrency;
import com.ithome.web.SearchController.AjaxServlet;
import com.ithome.web.counterController.HitCounter;

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


@WebServlet("/App")
public class App extends HttpServlet {
    private CheckLanguageAndCurrency checkLanguageAndCurrency = new CheckLanguageAndCurrency();
    private String language = null;
    private String Pagelanguage = null;
    private String pageName = null;
    private String city = null;

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

    private LookUpProgram lookUpProgram = new LookUpProgram();
    private DropDownHelper dropDownHelper = new DropDownHelper();
    private LanguageHelper languageHelper = new LanguageHelper();
    private PageNameHelper pageNameHelper = new PageNameHelper();
    private MortgageSpecialOfferHelper mortgageSpecialOfferHelper = new MortgageSpecialOfferHelper();
    private ConsumerSpecialOfferHelper consumerSpecialOfferHelper = new ConsumerSpecialOfferHelper();
    private CarLoanSpecialOfferHelper carLoanSpecialOfferHelper = new CarLoanSpecialOfferHelper();
    private DepositSpecialOfferHelper depositSpecialOfferHelper = new DepositSpecialOfferHelper();
    private DropDownCurrancyHelper dropDownCurrancyHelper = new DropDownCurrancyHelper();
    private AgSpecialOfferHelper agSpecialOfferHelper = new AgSpecialOfferHelper();
    private CardsSpecialOfferHelper cardsSpecialOfferHelper = new CardsSpecialOfferHelper();
    private BankHelper bankHelper = new BankHelper();
    private UsefullArticlesDaoController usefullArticlesDaoController = new UsefullArticlesDaoController();
    private String sessionId = null;
    private String pageLanguageName = null;
    private getCurrancyRates getCurrancyRates = new getCurrancyRates();
    private double usd = 0.0;
    private double rub = 0.0;
    private double eur = 0.0;
    private double gpb = 0.0;
    private double usdPosition = 0.0;
    private double rubPosition = 0.0;
    private double eurPosition = 0.0;
    private String eurPositionString = null;
    private double gpbPosition = 0.0;
    private int index = 0;


    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private HitCounter hitCounter = new HitCounter();
    private List<Integer> comparListDeposit = new ArrayList<>();
    private List<Integer> comparListMortgage = new ArrayList<>();
    private static List<Integer> comparListConsumer = new ArrayList<>();
    private static List<Integer> comparListCarLoan = new ArrayList<>();
    private static List<Integer> comparListMicro = new ArrayList<>();
    private static List<Integer> comparListAg = new ArrayList<>();
    private static List<Integer> comparListCard = new ArrayList<>();

    private int CounsumerCounter = 0;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            StartApp(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            StartApp(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private void StartApp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        ////getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getMainPageRange();
        //getRates();
        countHit();
        checkForCompareList();
        GetDropDownByCurrancy(dropDownsList, pageCurrancy);
        getDepositSpecialOffers();
        getProductnamesList();
        getMortgageSpecialOffers();
        getConsumerSpecialOffers();
        getCarLoanSpecialOffers();
        getCardsSpecialOffers();
        getAgSpecialOffers();
        getPartners();
        getBlog();

        createRequestes(request);
        gotoPage(request, response);
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


    private void getRates() {
        final String[] ratesArray = getCurrancyRates.getReadExChange();
        if(ratesArray.length > 0) {
            getUsdValues(ratesArray, getIndexIndex("USD", ratesArray));
            getGbpValues(ratesArray, getIndexIndex("GBP", ratesArray));
            getEurValues(ratesArray, getIndexIndex("EUR", ratesArray));
            getRubValues(ratesArray, getIndexIndex("RUB", ratesArray));
        }

    }

    private int getIndexIndex(String string, String[] myArray) {
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i].equals(string)) {
                return i;
            }
        }
        return 0;
    }

    private void getUsdValues(String[] ratesArray, int index) {

        usd = Double.parseDouble(ratesArray[index + 2]);
        boolean t = checkIfNumber(ratesArray[index + 2]);
        if (t) {
            usdPosition = getPositionResult(Double.parseDouble(ratesArray[index + 3]));

        } else {
            usdPosition = 0.0;
        }

    }

    private void getGbpValues(String[] ratesArray, int index) {

        gpb = Double.parseDouble(ratesArray[index + 1]);
        boolean t = checkIfNumber(ratesArray[index + 2]);
        if (t) {
            gpbPosition = getPositionResult(Double.parseDouble(ratesArray[index + 2]));
        } else {
            gpbPosition = 0.0;
        }

    }

    private void getEurValues(String[] ratesArray, int index) {

        eur = Double.parseDouble(ratesArray[index + 1]);
        boolean t = checkIfNumber(ratesArray[index + 2]);
        if (t) {
            eurPosition = getPositionResult(Double.parseDouble(ratesArray[index + 2]));
        } else {
            eurPosition = 0.0;
        }


    }

    private void getRubValues(String[] ratesArray, int index) {
        rub = Double.parseDouble(ratesArray[index + 1]);
        boolean t = checkIfNumber(ratesArray[index + 2]);
        if (t) {
            rubPosition = getPositionResult(Double.parseDouble(ratesArray[index + 2]));
        } else {
            rubPosition = 0.0;
        }

    }

    private boolean checkIfNumber(final String s) {
        try {
            double v = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException nfe) {
        }
        return false;
    }

    private double getPositionResult(double usdPosition) {
        return usdPosition > 0.0 ? 1.0 : usdPosition < 0.0 ? -1.0 : 0.0;
    }

    private void getBlog() throws SQLException {
        usefulArticlesList = usefullArticlesDaoController.getAllUsefullArticlesAsc();
        if (usefulArticlesList == null) usefulArticlesList = new ArrayList<>();
    }

    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        sessionId = session.getId();
        request.setAttribute("session",sessionId);
        session.setMaxInactiveInterval(86400);
    }


    private void countHit() throws SQLException {
        hitCounter.countingHits(pageName, pageCurrancy, city, language, sessionId);
    }

    private void getPartners() throws SQLException {
        banksList = bankHelper.bankFullList();
    }

    private void getProductnamesList() throws SQLException {
        ProductNameDaoController productNameDaoController = new ProductNameDaoController();
        productNameList = productNameDaoController.getAllProductNames();
    }

    private void getCardsSpecialOffers() throws SQLException {
        cardsListOffer = cardsSpecialOfferHelper.getStarted();
    }

    private void getAgSpecialOffers() throws SQLException {
        agriculturalCreditListoffer = agSpecialOfferHelper.getStarted();
    }

    private void getCarLoanSpecialOffers() throws SQLException {
        carLoansListoffer = carLoanSpecialOfferHelper.getStarted();
    }

    private void getConsumerSpecialOffers() throws SQLException {
        consumerCreditListoffer = consumerSpecialOfferHelper.getStarted();
    }

    private void getMortgageSpecialOffers() throws SQLException {
        mortgageListOffer = mortgageSpecialOfferHelper.getStarted();
    }

    private void getDepositSpecialOffers() throws SQLException {
        depositListOffer = depositSpecialOfferHelper.getStarted();
    }

    private void getMainPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName(pageName);
    }

    private void GetDropDownByCurrancy(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getCityFromUser(HttpServletRequest request) throws IOException {

        if (request.getParameter("city") == null) {
            city = LookUpProgram.start(request);
        } else {
            city = request.getParameter("city");
        }
    }

    private void getPageName(HttpServletRequest request) {
        pageName = pageNameHelper.pageName(request);
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
        request.setAttribute("usefulArticlesList", usefulArticlesList);

        request.setAttribute("usd", usd);
        request.setAttribute("rub", rub);
        request.setAttribute("eur", eur);
        request.setAttribute("gpb", gpb);
        request.setAttribute("usdPosition", usdPosition);
        request.setAttribute("rubPosition", rubPosition);
        request.setAttribute("eurPosition", eurPosition);
        request.setAttribute("gpbPosition", gpbPosition);

        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);

        request.setAttribute("CounsumerCounter", CounsumerCounter);

    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void getPageLanguage(String language) {
        Pagelanguage = checkLanguageAndCurrency.checkLanguage(language);
        pageLanguageName = checkLanguageAndCurrency.checkLanguageName(language);
    }

    private String getLanguagesFromPage(HttpServletRequest request) {
        if (request.getParameter("Pagelanguage") == null) {
            language = languageHelper.Pagelanguage(request, Pagelanguage);
        } else {
            language = languageHelper.Pagelanguage(request, request.getParameter("Pagelanguage"));
        }
        return language;
    }

    private void getCurancyFromPage(HttpServletRequest request) {
        if (request.getParameter("Currancy") == null) {
            pageCurrancy = checkLanguageAndCurrency.checkCurrency(pageCurrancyFromPage);
        } else {
            pageCurrancy = checkLanguageAndCurrency.checkCurrency(request.getParameter("Currancy"));
        }
    }


}