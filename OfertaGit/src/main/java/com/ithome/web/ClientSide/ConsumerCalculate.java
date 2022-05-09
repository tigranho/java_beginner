package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.ConsumerCreditDaoController;
import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Comparision.ConsumerComDao;
import com.ithome.web.Comparision.ConsumerComparing;
import com.ithome.web.Helpers.*;
import com.ithome.web.Localization.CheckLanguageAndCurrency;
import com.ithome.web.counterController.HitCounter;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/ConsumerCalculate")
public class ConsumerCalculate extends HttpServlet {
    private String value = null;
    private String range = null;
    private String Amount = null;
    private String Currancy = null;
    private String months = null;


    private CheckLanguageAndCurrency checkLanguageAndCurrency = new CheckLanguageAndCurrency();
    private String language = null;
    private String Pagelanguage = null;
    private String pageName = null;
    private String city = null;
    private String pageLanguageName = null;
    private String sessionId = null;
    private String PageToGo = null;
    private String amoutFiltered = null;
    private String MaxAmount = null;
    private String montheToDays = null;

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
    private List<ConsumerCredit> depositeAseList = new ArrayList<>();

    private MortgageSpecialOfferHelper mortgageSpecialOfferHelper = new MortgageSpecialOfferHelper();
    private ConsumerSpecialOfferHelper consumerSpecialOfferHelper = new ConsumerSpecialOfferHelper();
    private CarLoanSpecialOfferHelper carLoanSpecialOfferHelper = new CarLoanSpecialOfferHelper();
    private DepositSpecialOfferHelper depositSpecialOfferHelper = new DepositSpecialOfferHelper();
    private AgSpecialOfferHelper agSpecialOfferHelper = new AgSpecialOfferHelper();
    private CardsSpecialOfferHelper cardsSpecialOfferHelper = new CardsSpecialOfferHelper();
    private DropDownCurrancyHelper dropDownCurrancyHelper = new DropDownCurrancyHelper();
    private DropDownHelper dropDownHelper = new DropDownHelper();
    private ConsumerCreditDaoController consumerCreditDaoController = new ConsumerCreditDaoController();

    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private HitCounter hitCounter = new HitCounter();
    private LookUpProgram lookUpProgram = new LookUpProgram();
    private LanguageHelper languageHelper = new LanguageHelper();
    private PageNameHelper pageNameHelper = new PageNameHelper();
    private List<ConsumerCredit> depositAllInRage = new ArrayList<>();
    private List<ConsumerCredit> depositAllInSubRage = new ArrayList<>();

    private String Sorting = null;
    private int arrow = 0;
    private int arrow2 = 0;
    private int ID = 0;

    private List<Integer> comparListDeposit = new ArrayList<>();
    private List<Integer> comparListMortgage = new ArrayList<>();
    private static List<Integer> comparListConsumer = new ArrayList<>();
    private static List<Integer> comparListCarLoan = new ArrayList<>();
    private static List<Integer> comparListMicro = new ArrayList<>();
    private static List<Integer> comparListAg = new ArrayList<>();
    private static List<Integer> comparListCard = new ArrayList<>();
    private String WorningMessage = null;
    private int DepositCompareSize = 0;
    private String PageNameToDelete = null;

    List<ConsumerCredit> depositStartFilter = new ArrayList<>();
    List<ConsumerCredit> depositAmountFilter = new ArrayList<>();
    List<ConsumerCredit> depositCurrancyFilter = new ArrayList<>();
    List<ConsumerCredit> searchUpList = new ArrayList<>();

    List<ConsumerCredit> subDepositStartFilter = new ArrayList<>();
    List<ConsumerCredit> subDepositAmountFilter = new ArrayList<>();
    List<ConsumerCredit> subDepositCurrancyFilter = new ArrayList<>();

    String productName = null;
    int BankId = 0;
    int Service = 0;
    double percentage = 0;
    String BankLink = null;
    int BankIdFromData = 0;
    private int CounsumerCounter=0;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            consumerCalculate(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            consumerCalculate(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void consumerCalculate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        ////getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getParameters(request);

        filterAmount(Amount);
        //countHit();
        getPageRange();
        checkForCompareList();
        getApperance();
        //depositeFilterByTime();
        //depositeFilterSubbyTime();
        GetDropDownByCurrancy(dropDownsList, pageCurrancy);
        getDepositSpecialOffers();
        getProductnamesList();
        getMortgageSpecialOffers();
        getConsumerSpecialOffers();
        getCarLoanSpecialOffers();
        getCardsSpecialOffers();
        getAgSpecialOffers();
        createRequestes(request);
        GoPageToGo(request, response);
    }

    private void GoPageToGo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(PageToGo.equals("ConsumerBank")){
            request.setAttribute("Bankid",BankIdFromData);
            ConsumerBanks consumerBanks = new ConsumerBanks();
            consumerBanks.doGet(request,response);
        }else  if(months.equals("0")){
            int amount = Integer.parseInt(request.getParameter("range"));
            request.setAttribute("range",amount);
            ConsumerClient consumerClient = new ConsumerClient();
            consumerClient.doGet(request,response);
        }else {
            request.getRequestDispatcher("/CalculateConsumer.jsp").forward(request, response);
        }
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
        request.setAttribute("BanksList", banksList);
        request.setAttribute("usefulArticlesList", usefulArticlesList);

        request.setAttribute("Amountfiltered", amoutFiltered);
        request.setAttribute("Amount", Amount);
        request.setAttribute("range", Amount);

        request.setAttribute("depositeAseList", depositeAseList);
        request.setAttribute("depositAllInRage", depositAllInRage);
        request.setAttribute("depositAllInSubRage", depositAllInSubRage);
        request.setAttribute("minAmount", amoutFiltered);
        request.setAttribute("maxAmount", MaxAmount);
        request.setAttribute("arrow", arrow);
        request.setAttribute("arrow2", arrow2);
        request.setAttribute("WorningMessage", WorningMessage);
        request.setAttribute("DepositCompareSize", DepositCompareSize);
        request.setAttribute("name", "deposit");
        request.setAttribute("PageToGo", PageToGo);

        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);
        request.setAttribute("CounsumerCounter", CounsumerCounter);
        request.setAttribute("percentageMain", percentage);
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

    private void GetDropDownByCurrancy(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getApperance() throws SQLException {
        searchUpList = new ArrayList<>();
        depositeAseList = new ArrayList<>();

        searchUpList = searchDatabase();
        for (int i = 0; i <searchUpList.size() ; i++) {
            int firstSearch = searchUpList.get(i).getCCMinAmount();
            String firstSearchCurrancy = searchUpList.get(i).getCurrancy();
            int minMonthsFromData = searchUpList.get(i).getCCMinPeriodMonth();
            int maxMonthsFromData = searchUpList.get(i).getCCMaxPeriodMonth();
            if (Integer.parseInt(amoutFiltered) >= firstSearch && firstSearchCurrancy.equals(pageCurrancy) && Integer.parseInt(months) >= minMonthsFromData && Integer.parseInt(months) <= maxMonthsFromData ) {
                int id = Integer.parseInt(String.valueOf(searchUpList.get(i).getCLId()));
                depositeAseList.addAll(FilteredList(id));
            }
        }

    }

    private List<ConsumerCredit> searchDatabase() throws SQLException {
        return consumerSpecialOfferHelper.getAppearnace();
    }

    private void getPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName(getPageNameArm(pageName));
    }

    private String getPageNameArm(String pageName) {
        return pageNameHelper.ArmPageName(pageName);
    }

    private void SotringMachanizm(String sorting) throws SQLException {
        switch (sorting) {
            case "DescPercent":
                PercentageAsec();
                break;
            case "AsecAmount":
                PercentageAsecSorted();
                break;
            case "DescAmount":
                PercentageAsec();
                break;
            case "AsecPercent":
                PercentageAsecSorted();
                break;
        }
    }

    private void PercentageAsecSorted() throws SQLException {
        depositStartFilter = new ArrayList<>();
        depositAllInRage = consumerCreditDaoController.getFilterdOrderMoreOneMonths(Integer.parseInt(Amount),
            pageCurrancy,
            Integer.parseInt(months));
        Collections.sort(depositAllInRage, new ConsumerCalculate.ComparePercentage());
        arrow = 1;
        arrow2 = 1;
    }

    static class ComparePercentage implements Comparator<ConsumerCredit> {

        @Override
        public int compare(ConsumerCredit o1, ConsumerCredit o2) {
            return Double.compare(o1.getCCFactual(), o2.getCCFactual());
        }
    }

    private void PercentageAsec() throws SQLException {
        depositStartFilter = new ArrayList<>();
        depositAllInRage=consumerCreditDaoController.getFilterdOrderOneWithMonths(Integer.parseInt(Amount),
            pageCurrancy,
            Integer.parseInt(months));
        System.out.println();
        Collections.sort(depositAllInRage, new ConsumerCalculate.ComparePercentage2());
        arrow = 0;
        arrow2 = 0;
    }

    static class ComparePercentage2 implements Comparator<ConsumerCredit> {

        @Override
        public int compare(ConsumerCredit o1, ConsumerCredit o2) {
            return Double.compare(o2.getCCFactual(), o1.getCCFactual());
        }
    }


    private List<ConsumerCredit> FilteredList(int id) throws SQLException {
        return consumerCreditDaoController.getConsumerCreditById(id);
    }


    private void convertToDays(String months) {
        switch (months) {
            case "1":
                montheToDays = "30";
                break;
            case "3":
                montheToDays = "90";
                break;
            case "6":
                montheToDays = "180";
                break;
            case "9":
                montheToDays = "270";
                break;
            case "12":
                montheToDays = "360";
                break;
            case "18":
                montheToDays = "540";
                break;
            case "24":
                montheToDays = "720";
                break;
            case "36":
                montheToDays = "1080";
                break;
            default:
                montheToDays = "0";
                break;
        }
    }

    private void startCompare(int id,HttpServletRequest request) throws SQLException {
        List<ConsumerCredit> getDeposit = new ArrayList<>();
        boolean isSizeInRange = CompareHelper.ConsumerCheckOutOfBound();
        boolean isIdAlreadyHad = CompareHelper.CheckIfIdExsistConsumer(id);
        if (!isSizeInRange && !isIdAlreadyHad) {
            CompareHelper.AddConsumerToArrayById(id);
            getDeposit = consumerCreditDaoController.getByproductId(id);
            for (int i = 0; i < getDeposit.size(); i++) {
                productName = getDeposit.get(i).getProductName();
                BankId = getDeposit.get(i).getBankId();
                percentage = getDeposit.get(i).getCCFactual();
                Service = getDeposit.get(i).getCCMaxServiceFee();
            }
            ConsumerComDao consumerComDao = new ConsumerComDao();
            int done = consumerComDao.Add(CreateObjects());
            if (done == 0) {
                System.out.println("Something wrong");
            } else {
                System.out.println("success");
            }
            comparListConsumer = CompareHelper.getConsumerList(sessionId);
            WorningMessage = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("WorningMessage",WorningMessage);
            String s = jsonObject.toString();
            request.getSession().setAttribute("jsonArray",s);
            DepositCompareSize = CompareHelper.GetSizeConsumer();
        } else {
            if (DepositCompareSize > 3) {
                WorningMessage="";
                WorningMessage = "Դուք չեք կարող 4 ից ավել համեմատել։";
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("WorningMessage",WorningMessage);
                String s = jsonObject.toString();
                request.getSession().setAttribute("jsonArray",s);
            } else {
                WorningMessage = "";
                WorningMessage = "Դուք արդեն ունեք նույն ID-ին";
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("WorningMessage", WorningMessage);
                String s = jsonObject.toString();
                request.getSession().setAttribute("jsonArray", s);
            }
        }
    }

    private ConsumerComparing CreateObjects() {
        return new ConsumerComparing(ID, sessionId, Integer.parseInt(amoutFiltered), Integer.parseInt(months), BankId, productName, percentage, Service);
    }

    private void getMaxAmount() throws SQLException {
        getPageRange();
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
        for (int i = 0; i < dropDownsListWithCurrancy.size(); i++) {
            MaxAmount = dropDownsListWithCurrancy.get(i).getMaxAmount();
        }
    }

    static class DepositComarator implements Comparator<ConsumerCredit> {

        @Override
        public int compare(ConsumerCredit o1, ConsumerCredit o2) {
            return Double.compare(o2.getCCFactual(), o1.getCCFactual());
        }
    }

    static class DepositComaratorAmount implements Comparator<ConsumerCredit> {

        @Override
        public int compare(ConsumerCredit o1, ConsumerCredit o2) {
            return Integer.compare(o2.getCCMinAmount(), o1.getCCMinAmount());
        }
    }

    private void getParameters(HttpServletRequest request) throws SQLException {

        if (request.getParameter("MaxAmount") != null) {
            MaxAmount = request.getParameter("MaxAmount");
        } else if (request.getParameter("maxAmount") != null) {
            MaxAmount = request.getParameter("maxAmount");
        } else if (request.getParameter("MaxAmounr") != null) {
            MaxAmount = request.getParameter("MaxAmounr");
        } else {
            getMaxAmount();
        }
        if(request.getParameter("bankId")!=null){
            BankIdFromData = Integer.parseInt(request.getParameter("bankId"));
        }else{
            BankIdFromData = 0;
        }
        if (request.getParameter("range") != null) {
            Amount = request.getParameter("range");
            filterAmount(Amount);

        } else if (request.getParameter("Amount") != null) {
            Amount = request.getParameter("Amount");
            filterAmount(Amount);
        }
        Currancy = request.getParameter("Currancy");
        if (request.getParameter("months") != null) {
            months = request.getParameter("months");
        } else {
            months = "1";
        }
        //convertToDays(months);
        city = request.getParameter("City");

        if (request.getParameter("PageToGo") != null) {
            PageToGo = request.getParameter("PageToGo");
        } else {
            PageToGo = pageName;
        }

        if (request.getParameter("id") != null) {
            ID = Integer.parseInt(request.getParameter("id"));
            startCompare(ID,request);

        } else {
            WorningMessage = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("WorningMessage",WorningMessage);
            String s = jsonObject.toString();
            request.getSession().setAttribute("jsonArray",s);
            ID = 0;
        }
        if (request.getParameter("pageNameToDelete") != null) {
            PageNameToDelete = request.getParameter("pageNameToDelete");
            deleteList(PageNameToDelete);
        } else {
            PageNameToDelete = "";
        }

        if (request.getParameter("sorting") != null) {
            Sorting = request.getParameter("sorting");
            try {
                SotringMachanizm(Sorting);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        } else {
            Sorting = "DescAmount";
            try {
                SotringMachanizm(Sorting);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }

    }

    private void deleteList(String pageNameToDelete) throws SQLException {
        switch (pageNameToDelete) {
            case "Ավանդ":
                CompareHelper.DeleteDepositList(sessionId);
                break;
            case "Հիփոթեք":
                CompareHelper.DeleteMortgag(sessionId);
                break;
            case "Ավտովարկ":
                CompareHelper.DeleteCarLoan(sessionId);
                break;
            case "ՄԻԿՐՈՎԱՐԿ":
                CompareHelper.DeleteMicro(sessionId);
                break;
            case "Գյուղատնտեսական":
                CompareHelper.DeleteAg(sessionId);
                break;
            case "Սպարողական":
                CompareHelper.DeleteConsumer(sessionId);
                break;
            case "Քարտեր":
                CompareHelper.DeleteCard();
                break;
        }
    }

    private void filterAmount(String amount) {
        if (amount.contains(",")) {
            amoutFiltered = amount.replace(",", "");
        } else {
            amoutFiltered = amount;
        }
        System.out.println("amoutFiltered " + amoutFiltered);
    }

    private void getPageLanguage(String language) {
        Pagelanguage = checkLanguageAndCurrency.checkLanguage(language);
        pageLanguageName = checkLanguageAndCurrency.checkLanguageName(language);
    }

    private void getPageName(HttpServletRequest request) {
        pageName = pageNameHelper.pageName(request);
        System.out.println("Page name : " + pageName);
    }

    private void getCityFromUser(HttpServletRequest request) throws IOException {
        if (request.getParameter("city") == null) {
            city = lookUpProgram.start(request);
        } else {
            city = request.getParameter("city");
        }
    }

    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        sessionId = session.getId();
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

    private void getCurancyFromPage(HttpServletRequest request) {
        if (request.getParameter("Currancy") == null) {
            pageCurrancy = checkLanguageAndCurrency.checkCurrency(pageCurrancyFromPage);
        } else {
            pageCurrancy = checkLanguageAndCurrency.checkCurrency(request.getParameter("Currancy"));
        }
    }
}

