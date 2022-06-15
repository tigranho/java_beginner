package com.ithome.web.SearchController;

import com.google.gson.Gson;
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
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchPageApp")
public class SearchPageApp extends HttpServlet {
    private CheckLanguageAndCurrency checkLanguageAndCurrency = new CheckLanguageAndCurrency();
    private LookUpProgram lookUpProgram = new LookUpProgram();
    private LanguageHelper languageHelper = new LanguageHelper();

    private String PageToGo = null;
    private String IdToCheck = null;
    private String language = null;
    private String Pagelanguage = null;
    private String pageName = null;
    private String city = null;
    private String pageLanguageName = null;
    private String sessionId = null;
    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private float Percentage = 0;
    private int valueTwo = 0;
    private PageNameHelper pageNameHelper = new PageNameHelper();
    private int minAmount = 0;
    private int depositMonths = 0;
    private float perc = 0;
    private int months = 0;
    private String maxAmount = null;
    private List<DropDowns> dropDownsListWithCurrancy = new ArrayList<>();
    private DropDownCurrancyHelper dropDownCurrancyHelper = new DropDownCurrancyHelper();
    private List<DropDowns> dropDownsList = new ArrayList<>();
    private DropDownHelper dropDownHelper = new DropDownHelper();

    private List<Banks> banksList = new ArrayList<>();
    private List<Deposit> depositList = new ArrayList<>();
    private List<MicroLoans> microLoansList = new ArrayList<>();
    private List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
    private List<ConsumerCredit> consumerCreditList = new ArrayList<>();
    private List<Cards> cardsList = new ArrayList<>();
    private List<CarLoans> carLoansList = new ArrayList<>();
    private List<Mortgage> mortgageList = new ArrayList<>();

    private List<ProductName> productNameList = new ArrayList<>();

    private List<Integer> comparListDeposit = new ArrayList<>();
    private List<Integer> comparListMortgage = new ArrayList<>();
    private static List<Integer> comparListConsumer = new ArrayList<>();
    private static List<Integer> comparListCarLoan = new ArrayList<>();
    private static List<Integer> comparListMicro = new ArrayList<>();
    private static List<Integer> comparListAg = new ArrayList<>();
    private static List<Integer> comparListCard = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            searchPageApp(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            searchPageApp(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchPageApp(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        ////getCityFromUser(request);
        getPageLanguage(language);
        getParameters(request,response);

        checkForCompareList();

        getDataByIdANdPage(PageToGo, IdToCheck, request, response);

    }

    private void GetDropDownByCurrancyDeposit(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getMainPageRangeDeposit() throws SQLException {
        dropDownsList = dropDownHelper.getPageName("ԱՎԱՆԴՆԵՐ");
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


    private void RequestesDeposit(HttpServletRequest request) {
        request.setAttribute("fromPage", "Search");
        request.setAttribute("TheList", depositList);
        request.setAttribute("range", minAmount);

        request.setAttribute("FromPageName", "Deposit");
        // request.setAttribute("Amount",minAmount);
        request.setAttribute("months", depositMonths);
        request.setAttribute("maxAmount", maxAmount);
        request.setAttribute("MaxAmount", maxAmount);
        request.setAttribute("ProductCode", IdToCheck);
        request.setAttribute("value_two", "0");
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("city", city);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);
        //request.setAttribute("productNameList", productNameList);
        request.setAttribute("Per", perc);

    }

    private void RequestesBank(HttpServletRequest request) {
        request.setAttribute("banksList", banksList);
        request.setAttribute("fromPage", "Search");
        request.setAttribute("TheList", depositList);
        request.setAttribute("range", minAmount);
        request.setAttribute("FromPageName", "Banks");
        // request.setAttribute("Amount",minAmount);
        request.setAttribute("months", depositMonths);

        request.setAttribute("ProductCode", IdToCheck);
        request.setAttribute("value_two", "0");
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("city", city);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);

    }

    private void RequestesMicro(HttpServletRequest request) {
        request.setAttribute("TheListMicro", microLoansList);
        request.setAttribute("TheList", depositList);
        request.setAttribute("range", minAmount);
        request.setAttribute("Amount", minAmount);
        request.setAttribute("FromPageName", "Micro");
        // request.setAttribute("Amount",minAmount);
        request.setAttribute("months", depositMonths);
        request.setAttribute("PageToGo", "Micro");
        request.setAttribute("value_two", 1000000);
        request.setAttribute("range_two", 30);


        request.setAttribute("ProductCode", IdToCheck);
        request.setAttribute("value_two", "0");
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("city", city);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);
        request.setAttribute("Per", Percentage);
    }

    private void RequestesAG(HttpServletRequest request) {
        request.setAttribute("TheListAg", agriculturalCreditList);
        request.setAttribute("TheList", depositList);
        request.setAttribute("range", minAmount);
        request.setAttribute("Amount", minAmount);
        request.setAttribute("FromPageName", "AG");
        // request.setAttribute("Amount",minAmount);
        request.setAttribute("months", depositMonths);
        request.setAttribute("PageToGo", "AG");
        request.setAttribute("value_two", 1000000);
        request.setAttribute("range_two", 30);
        request.setAttribute("ProductCode", IdToCheck);
        request.setAttribute("value_two", "0");
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("city", city);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);
        request.setAttribute("Per", Percentage);
    }

    private void RequestesConsumer(HttpServletRequest request) {
        request.setAttribute("TheListCC", consumerCreditList);
        request.setAttribute("TheList", depositList);
        request.setAttribute("range", minAmount);
        request.setAttribute("Amount", minAmount);
        request.setAttribute("FromPageName", "Consumer");
        // request.setAttribute("Amount",minAmount);
        request.setAttribute("months", depositMonths);
        request.setAttribute("PageToGo", "Micro");
        request.setAttribute("value_two", 1000000);
        request.setAttribute("range_two", 30);
        request.setAttribute("ProductCode", IdToCheck);
        request.setAttribute("value_two", "0");
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("city", city);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);
        request.setAttribute("Per", Percentage);
    }

    private void RequestesCards(HttpServletRequest request) {
        request.setAttribute("CardList", cardsList);
        request.setAttribute("range", minAmount);
        request.setAttribute("Amount", minAmount);
        request.setAttribute("FromPageName", "Cards");
        // request.setAttribute("Amount",minAmount);
        request.setAttribute("months", depositMonths);
        request.setAttribute("PageToGo", "Micro");
        request.setAttribute("value_two", 1000000);
        request.setAttribute("range_two", 30);
        request.setAttribute("ProductCode", IdToCheck);
        request.setAttribute("value_two", "0");
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("city", city);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);
        request.setAttribute("Per", Percentage);
    }

    private void RequestesCars(HttpServletRequest request) {
        request.setAttribute("CarList", carLoansList);
        request.setAttribute("range", minAmount);
        request.setAttribute("Amount", minAmount);
        request.setAttribute("FromPageName", "Cars");
        // request.setAttribute("Amount",minAmount);
        request.setAttribute("months", depositMonths);
        request.setAttribute("PageToGo", "Cars");
        request.setAttribute("value_two", minAmount);
        request.setAttribute("Amountfiltered", minAmount);
        request.setAttribute("range_two", 10);
        request.setAttribute("ProductCode", IdToCheck);
        request.setAttribute("value_two", "0");
        request.setAttribute("valueTwo", valueTwo);
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("city", city);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);
        request.setAttribute("Per", Percentage);
        request.setAttribute("percentageSecond", 10);
        request.setAttribute("PercentageSecond", 10);
    }

    private void RequestesMortgage(HttpServletRequest request) {
        request.setAttribute("TheListM", mortgageList);
        request.setAttribute("range", minAmount);
        request.setAttribute("Amount", minAmount);
        request.setAttribute("Amountfiltered", minAmount);
        request.setAttribute("FromPageName", "Mortgage");
        // request.setAttribute("Amount",minAmount);
        request.setAttribute("months", depositMonths);
        request.setAttribute("PageToGo", "Cars");
        request.setAttribute("value_two", minAmount);
        request.setAttribute("range_two", 10);
        request.setAttribute("ProductCode", IdToCheck);
        request.setAttribute("value_two", "0");
        request.setAttribute("valueTwo", valueTwo);
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("city", city);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);
        request.setAttribute("Per", Percentage);
        request.setAttribute("percentageSecond", 10);
        request.setAttribute("PercentageSecond", 10);
    }
    private void getProductnamesList() throws SQLException {
        productNameList=new ArrayList<>();
        ProductNameDaoController productNameDaoController = new ProductNameDaoController();
        productNameList = productNameDaoController.getAllProductNames();
    }

    private void getDataByIdANdPage(String pageToGo, String idToCheck, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        switch (pageToGo) {
            case "Բանկեր-ում":
                getBanksDetailById(idToCheck);
                RequestesBank(request);
                GoToBankPage(request, response);
                break;
            case "Ավանդներ-ում":
                getDepositDetailByProductCode(idToCheck);
                minAmount = getDepositMinAmount();
                maxAmount = getDepositMaxAmount();
                depositMonths = getDepositmonths();
                perc = (float) (depositList.get(0).getDPercentage());
                months =depositList.get(0).getTimeLine();
                getMainPageRangeDeposit();
                GetDropDownByCurrancyDeposit(dropDownsList, pageCurrancy);
                getProductnamesList();
                RequestesDeposit(request);
                GoToDepositPage(request, response);

                break;
            case "Միկրովարկ-ում":
                getMicroDetailByProductCode(idToCheck);
                minAmount = getMicroMinAmount();
                maxAmount = getMicroMaxAmount();
                depositMonths = getMicromonths();
                Percentage = getMicroPercentage();
                getMainPageRangeMicro();
                GetDropDownByCurrancyMicro(dropDownsList, pageCurrancy);
                RequestesMicro(request);
                GoToMicroPage(request, response);
                break;
            case "Գյուղատնտեսական-ում":
                getAGDetailByProductCode(idToCheck);
                minAmount = getAGMinAmount();
                maxAmount = getAGMaxAmount();
                depositMonths = getAGmonths();
                Percentage = getAGPercentage();
                getMainPageRangeAG();
                GetDropDownByCurrancyAG(dropDownsList, pageCurrancy);
                RequestesAG(request);
                GoToAGPage(request, response);
                break;
            case "Սպարողական-ում":
                getConsumerDetailByProductCode(idToCheck);
                minAmount = getConsumerMinAmount();
                maxAmount = getConsumerMaxAmount();
                depositMonths = getConsumermonths();
                Percentage = getConsumerPercentage();
                getMainPageRangeConsumer();
                GetDropDownByCurrancyConsumer(dropDownsList, pageCurrancy);
                RequestesConsumer(request);
                GoToConsumerPage(request, response);
                break;
            case "Քարտեր-ում":
                getCardDetailByProductCode(idToCheck);
                RequestesCards(request);
                GoToCardsPage(request, response);
                break;
            case "Ավտովարկ-ում":
                getCarDetailByProductCode(idToCheck);
                minAmount = getCarMinAmount();
                maxAmount = getCarMaxAmount();
                depositMonths = getCarmonths();
                Percentage = getCarPercentage();
                valueTwo = calculateTenPercent(maxAmount);
                getMainPageRangeCars();
                GetDropDownByCurrancyCar(dropDownsList, pageCurrancy);
                RequestesCars(request);
                GoToCarsPage(request, response);
                break;
            case "Հիփոթեք-ում":
                getMortgageDetailByProductCode(idToCheck);
                minAmount = getMortgageMinAmount();
                maxAmount = getMortgageMaxAmount();
                depositMonths = getMortgagemonths();
                Percentage = getMortgagePercentage();
                valueTwo = calculateTenPercent(maxAmount);
                getMainPageRangeMortgage();
                GetDropDownByCurrancyMortgage(dropDownsList, pageCurrancy);
                RequestesMortgage(request);
                GoToMortgagePage(request, response);
                break;
            default:
                GoToMainPage(request, response);
                break;
        }
    }

    private void GoToMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void GoToMortgagePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("CreditsSendM.jsp").forward(request, response);
    }


    private void GetDropDownByCurrancyMortgage(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getMainPageRangeMortgage() throws SQLException {
        dropDownsList = dropDownHelper.getPageName("ՀԻՓՈԹԵՔ");
    }

    private float getMortgagePercentage() {
        return (float) mortgageList.get(0).getMFatual();
    }

    private int getMortgagemonths() {
        return mortgageList.get(0).getMMaxPeriodMonth();
    }

    private String getMortgageMaxAmount() {
        return String.valueOf(mortgageList.get(0).getMMaxAmount());
    }

    private int getMortgageMinAmount() {
        return mortgageList.get(0).getMMinAmount();
    }

    private void getMortgageDetailByProductCode(String idToCheck) throws SQLException {
        mortgageList = new ArrayList<>();
        MortgageDaoController mortgageDaoController = new MortgageDaoController();
        mortgageList = mortgageDaoController.getMortgageByProductCode(Integer.parseInt(idToCheck));
    }

    private int calculateTenPercent(String maxAmount) {
        return Integer.parseInt(maxAmount) * 10 / 100;
    }

    private void GoToCarsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("CreditsSendCar.jsp").forward(request, response);
    }


    private void GetDropDownByCurrancyCar(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getMainPageRangeCars() throws SQLException {
        dropDownsList = dropDownHelper.getPageName("ԱՎՏՈՎԱՐԿ");
    }

    private float getCarPercentage() {
        return (float) carLoansList.get(0).getCLFatual();
    }

    private int getCarmonths() {
        return carLoansList.get(0).getCLMaxPeriodMonths();
    }

    private String getCarMaxAmount() {
        return String.valueOf(carLoansList.get(0).getCLMaxAmount());
    }

    private int getCarMinAmount() {
        return carLoansList.get(0).getCLMinAmount();
    }

    private void getCarDetailByProductCode(String idToCheck) throws SQLException {
        carLoansList = new ArrayList<>();
        CarLoanDao carLoanDao = new CarLoanDao();
        carLoansList = carLoanDao.getCarLoansByProductCode(Integer.parseInt(idToCheck));
    }

    private void GoToCardsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("CreditsSendCa.jsp").forward(request, response);
    }


    private void GoToConsumerPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("CreditsSendC.jsp").forward(request, response);
    }


    private void GetDropDownByCurrancyConsumer(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getMainPageRangeConsumer() throws SQLException {
        dropDownsList = dropDownHelper.getPageName("Սպարողական");
    }

    private float getConsumerPercentage() {
        return (float) consumerCreditList.get(0).getCCFactual();
    }

    private int getConsumermonths() {
        return consumerCreditList.get(0).getCCMinPeriodMonth();
    }

    private String getConsumerMaxAmount() {
        return String.valueOf(consumerCreditList.get(0).getCCMaxAmount());
    }

    private int getConsumerMinAmount() {
        return consumerCreditList.get(0).getCCMinAmount();
    }

    private void getCardDetailByProductCode(String idToCheck) throws SQLException {
        cardsList = new ArrayList<>();
        CardsDao cardsDao = new CardsDao();
        cardsList = cardsDao.getCardsByCardCode(Integer.parseInt(idToCheck));
    }

    private void getConsumerDetailByProductCode(String idToCheck) throws SQLException {
        consumerCreditList = new ArrayList<>();
        ConsumerCreditDaoController consumerCreditDaoController = new ConsumerCreditDaoController();
        consumerCreditList = consumerCreditDaoController.getConsumerCreditByCardCode(Integer.parseInt(idToCheck));
    }

    private void GoToAGPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("CreditsSendAG.jsp").forward(request, response);
    }

    private void GetDropDownByCurrancyAG(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getMainPageRangeAG() throws SQLException {
        dropDownsList = dropDownHelper.getPageName("ԳՅՈՒՂԱՏՆՏԵՍԱԿԱՆ");
    }

    private int getAGMinAmount() {
        return agriculturalCreditList.get(0).getACMinAmount();
    }

    private String getAGMaxAmount() {
        return String.valueOf(agriculturalCreditList.get(0).getACMaxAmount());
    }

    private int getAGmonths() {
        return agriculturalCreditList.get(0).getACMaxPeriodMonth();
    }

    private float getAGPercentage() {
        return (float) agriculturalCreditList.get(0).getACFactual();
    }


    private void getAGDetailByProductCode(String idToCheck) throws SQLException {
        agriculturalCreditList = new ArrayList<>();
        AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();
        agriculturalCreditList = agriculturalCreditDao.getAgriculturalCreditByProductCode(Integer.parseInt(idToCheck));
    }

    private void GoToMicroPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("CreditsSendMi.jsp").forward(request, response);
    }

    private void GetDropDownByCurrancyMicro(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getMainPageRangeMicro() throws SQLException {
        dropDownsList = dropDownHelper.getPageName("ՄԻԿՐՈՎԱՐԿ");
    }


    private float getMicroPercentage() {
        return (float) microLoansList.get(0).getMLFatual();
    }

    private String getMicroMaxAmount() {
        return String.valueOf(microLoansList.get(0).getMLMaxAmount());
    }


    private int getMicroMinAmount() {
        return microLoansList.get(0).getMLMinAmount();
    }

    private int getMicromonths() {
        return microLoansList.get(0).getMMaxPeriodDays();

    }


    private String getDepositMaxAmount() {
        return String.valueOf(depositList.get(0).getDMaxAmount());
    }


    private int getDepositmonths() {
        return depositList.get(0).getTimeLine() / 30;
    }

    private int getDepositMinAmount() {
        return depositList.get(0).getDMinAmount();

    }

    private void GoToDepositPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Credits.jsp").forward(request, response);
    }


    private void GoToBankPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("AboutBank.jsp").forward(request, response);
    }

    private void getMicroDetailByProductCode(String idToCheck) throws SQLException {
        microLoansList = new ArrayList<>();
        MicroLoanDaoController microLoanDaoController = new MicroLoanDaoController();
        microLoansList = microLoanDaoController.getMicroLoansByProductCode(Integer.parseInt(idToCheck));
    }

    private void getBanksDetailById(String idToCheck) throws SQLException {
        banksList = new ArrayList<>();
        BanksDaoController banksDaoController = new BanksDaoController();
        banksList = banksDaoController.getBankInfoById(Integer.parseInt(idToCheck));
    }

    private void getDepositDetailByProductCode(String idToCheck) throws SQLException {
        depositList = new ArrayList<>();
        DepositDaoController depositDaoController = new DepositDaoController();
        depositList = depositDaoController.getDepositByCardCode(Integer.parseInt(idToCheck));

    }

    private void getParameters(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        String param = request.getParameter("searchText");
        String[] paramArray = param.split(" ");
        if(param.length() < 5 || !param.contains("ID") || !param.contains("-ում")){
            ProductModel productModel = null;
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            out.print(gson.toJson("Please enter full search"));
        }else {
            byte[] PageToGoChar = paramArray[paramArray.length - 3].getBytes(charset);
            PageToGo = new String(PageToGoChar, charset);
            IdToCheck = new String(paramArray[paramArray.length - 1].getBytes(charset));
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
            language = languageHelper.Pagelanguage(request, "hy_AM");
        }
        return language;
    }

    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        sessionId = session.getId();
        CompareHelper.sendSession(sessionId);
    }


}
