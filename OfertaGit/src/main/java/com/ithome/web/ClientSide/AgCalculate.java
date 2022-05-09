package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.AgriculturalCreditDao;
import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Comparision.AgComDao;
import com.ithome.web.Comparision.AgComparing;
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

@WebServlet("/AgCalculate")
public class AgCalculate extends HttpServlet {
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
    private List<AgriculturalCredit> depositeAseList = new ArrayList<>();

    private MortgageSpecialOfferHelper mortgageSpecialOfferHelper = new MortgageSpecialOfferHelper();
    private ConsumerSpecialOfferHelper consumerSpecialOfferHelper = new ConsumerSpecialOfferHelper();
    private CarLoanSpecialOfferHelper carLoanSpecialOfferHelper = new CarLoanSpecialOfferHelper();
    private DepositSpecialOfferHelper depositSpecialOfferHelper = new DepositSpecialOfferHelper();
    private AgSpecialOfferHelper agSpecialOfferHelper = new AgSpecialOfferHelper();
    private CardsSpecialOfferHelper cardsSpecialOfferHelper = new CardsSpecialOfferHelper();
    private DropDownCurrancyHelper dropDownCurrancyHelper = new DropDownCurrancyHelper();
    private DropDownHelper dropDownHelper = new DropDownHelper();
    private AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();

    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private HitCounter hitCounter = new HitCounter();
    private LookUpProgram lookUpProgram = new LookUpProgram();
    private LanguageHelper languageHelper = new LanguageHelper();
    private PageNameHelper pageNameHelper = new PageNameHelper();
    private List<AgriculturalCredit> depositAllInRage = new ArrayList<>();
    private List<AgriculturalCredit> depositAllInSubRage = new ArrayList<>();

    private String Sorting = null;
    private int arrow = 0;
    private int arrow2 = 0;
    private int ID = 0;

    private List<Integer> comparListDeposit = new ArrayList<>();
    private List<Integer> comparListMortgage = new ArrayList<>();
    private static List<Integer> comparListConsumer =  new ArrayList<>();
    private static List<Integer> comparListCarLoan =  new ArrayList<>();
    private static List<Integer> comparListMicro =  new ArrayList<>();
    private static List<Integer> comparListAg =  new ArrayList<>();
    private static List<Integer> comparListCard =  new ArrayList<>();
    private String WorningMessage = null;
    private int DepositCompareSize = 0;
    private String PageNameToDelete =null;

    String productName = null;
    int BankId = 0;
    int BankIdFromData = 0;
    int Service = 0;
    double percentage = 0;
    String BankLink = null;


    List<AgriculturalCredit> depositStartFilter = new ArrayList<>();
    List<AgriculturalCredit> depositAmountFilter = new ArrayList<>();
    List<AgriculturalCredit> depositCurrancyFilter = new ArrayList<>();
    List<AgriculturalCredit> searchUpList = new ArrayList<>();

    List<AgriculturalCredit> subDepositStartFilter = new ArrayList<>();
    List<AgriculturalCredit> subDepositAmountFilter = new ArrayList<>();
    List<AgriculturalCredit> subDepositCurrancyFilter = new ArrayList<>();

    int getAgList=0;
    int getDpList = 0;
    int getMoList = 0;
    int getCoList = 0;
    int getCaList = 0;
    int getMiList = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            agCalculate(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            agCalculate(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void agCalculate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
         //getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getParameters(request);
        checkForCompareList();
        filterAmount(Amount);
        //countHit();
        getPageRange();
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
        GoPageToGo(request,response);
    }
    private void checkForCompareList() {
        comparListDeposit = CompareHelper.getDepositList(sessionId);
        comparListMortgage = CompareHelper.getMortgageList(sessionId);
        comparListConsumer =  CompareHelper.getConsumerList(sessionId);
        comparListCarLoan =  CompareHelper.getCarLoanList(sessionId);
        comparListMicro =  CompareHelper.getMicroList(sessionId);
        comparListAg = CompareHelper.getAgList(sessionId);
        getAgList = CompareHelper.getAgListSize();
        getDpList = CompareHelper.getDepositListSize();
        getMoList = CompareHelper.getMortgageListSize();
        getCoList = CompareHelper.getConsumerListSize();
        getCaList = CompareHelper.getCarLoanListSize();
        getMiList = CompareHelper.getMicroListSize();

        comparListCard =  CompareHelper.getCardList(sessionId);
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

    /* firstSection starts */
    private void PercentageAsecSorted() throws SQLException {
        depositStartFilter = agriculturalCreditDao.getAllAgriculturalCreditList();
        depositAmountFilter = new ArrayList<>();
        MaxAmount=null;
        getMaxAmount();
        for (int i = 0; i < depositStartFilter.size(); i++) {
            int minAmount = depositStartFilter.get(i).getACMinAmount();
            int maxAmount = depositStartFilter.get(i).getACMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount ){
                int id = Integer.parseInt(String.valueOf(depositStartFilter.get(i).getACId()));
                depositAmountFilter.addAll(FilteredList(id));
            }

        }
        FilterCurrancyListSorted(depositAmountFilter);

    }

    private void FilterCurrancyListSorted(List<AgriculturalCredit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(Currancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getACId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterMonthsListSorted(depositCurrancyFilter);
    }

    private void FilterMonthsListSorted(List<AgriculturalCredit> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            double timeLineFilter = depositCurrancyFilter.get(i).getACFactual();
            int minMonthsFromData = depositStartFilter.get(i).getACMinPeriodMonth();
            int maxMonthsFromData = depositStartFilter.get(i).getACMaxPeriodMonth();
            if (Integer.parseInt(months) >= minMonthsFromData ) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getACId()));
                depositAllInRage.addAll(FilteredList(id));
            }

        }

        Collections.sort(depositAllInRage, new ComparePercentage());
        arrow = 1;
        arrow2 = 1;
    }
    /* firstSection Ends */

    static class ComparePercentage implements Comparator<AgriculturalCredit> {

        @Override
        public int compare(AgriculturalCredit o1, AgriculturalCredit o2) {
            return Double.compare(o1.getACFactual(), o2.getACFactual());
        }
    }

    /* firstSection starts */
    private void PercentageAsec() throws SQLException {
        depositStartFilter = agriculturalCreditDao.getAllAgriculturalCreditList();
        depositAmountFilter = new ArrayList<>();
        MaxAmount=null;
        getMaxAmount();
        for (int i = 0; i < depositStartFilter.size(); i++) {
            int minAmount = depositStartFilter.get(i).getACMinAmount();
            int maxAmount = depositStartFilter.get(i).getACMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount ){
                int id = Integer.parseInt(String.valueOf(depositStartFilter.get(i).getACId()));
                depositAmountFilter.addAll(FilteredList(id));
            }

        }
        FilterCurrancyList(depositAmountFilter);

    }

    private void FilterCurrancyList(List<AgriculturalCredit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(Currancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getACId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterMonthsList(depositCurrancyFilter);
    }

    private void FilterMonthsList(List<AgriculturalCredit> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int minMonthsFromData = depositStartFilter.get(i).getACMinPeriodMonth();
            int maxMonthsFromData = depositStartFilter.get(i).getACMaxPeriodMonth();
            if (Integer.parseInt(months) >= minMonthsFromData ) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getACId()));
                depositAllInRage.addAll(FilteredList(id));
            }

        }
        Collections.sort(depositAllInRage, new ComparePercentage2());
        arrow = 0;
        arrow2 = 0;
    }
    /* firstSection Ends */
    static class ComparePercentage2 implements Comparator<AgriculturalCredit> {

        @Override
        public int compare(AgriculturalCredit o1, AgriculturalCredit o2) {
            return Double.compare(o2.getACFactual(), o1.getACFactual());
        }
    }

    private List<AgriculturalCredit> FilteredList(int id) throws SQLException {
        return agriculturalCreditDao.getAgriculturalCreditById(id);
    }

    private void GoPageToGo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(PageToGo.equals("AGBanks")){
            request.setAttribute("Bankid",BankIdFromData);
            AgBanks  agBanks = new AgBanks();
            agBanks.doGet(request,response);
        }else {
            request.getRequestDispatcher("/CalculateAg.jsp").forward(request, response);
        }
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
        request.setAttribute("months", months);
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
        request.setAttribute("depositeAseList", depositeAseList);

    }

    private void getProductnamesList() throws SQLException {
        ProductNameDaoController productNameDaoController = new ProductNameDaoController();
        productNameList = productNameDaoController.getAllProductNames();
    }

    private void getCardsSpecialOffers()  throws SQLException{
        cardsListOffer = cardsSpecialOfferHelper.getStarted();
    }

    private void getAgSpecialOffers() throws SQLException {
        agriculturalCreditListoffer = agSpecialOfferHelper.getStarted();
    }

    private void getCarLoanSpecialOffers()  throws SQLException{
        carLoansListoffer = carLoanSpecialOfferHelper.getStarted();
    }

    private void getConsumerSpecialOffers()  throws SQLException {
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
        MaxAmount=null;
        getMaxAmount();
        searchUpList = searchDatabase();
        for (int i = 0; i <searchUpList.size() ; i++) {
            int firstSearch = searchUpList.get(i).getACMinAmount();
            String firstSearchCurrancy = searchUpList.get(i).getCurrancy();
            int minMonthsFromData = searchUpList.get(i).getACMinPeriodMonth()/12;
            int maxMonthsFromData = searchUpList.get(i).getACMaxPeriodMonth()/12;
            if (Integer.parseInt(amoutFiltered) >= firstSearch || Integer.parseInt(amoutFiltered) <= firstSearch && Integer.parseInt(months) <= minMonthsFromData  && Integer.parseInt(months) <= maxMonthsFromData) {
                int id = Integer.parseInt(String.valueOf(searchUpList.get(i).getACId()));
                depositeAseList.addAll(FilteredList(id));
            }
        }

    }

    private List<AgriculturalCredit> searchDatabase() throws SQLException {
        return agSpecialOfferHelper.getAppearnace();
    }

    private void getPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName(getPageNameArm(pageName));
    }

    private String getPageNameArm(String pageName) {
        return pageNameHelper.ArmPageName(pageName);
    }

    private void getMaxAmount() throws SQLException {
        getPageRange();
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
        MaxAmount = dropDownsListWithCurrancy.get(0).getMaxAmount();
        for (int i = 0; i <dropDownsListWithCurrancy.size() ; i++) {
            System.out.println(dropDownsListWithCurrancy.get(0).getMaxAmount());
            System.out.println(dropDownsListWithCurrancy.get(0).getMinAmount());
        }
    }

    private void getParameters(HttpServletRequest request) throws SQLException {
        /*value = request.getParameter("value");*/

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
        if(request.getParameter("months")!= null ) {
            months = request.getParameter("months");
        }
        if (months.equals("0")) {
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
        switch (pageNameToDelete){
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

    private void startCompare(int id,HttpServletRequest request) throws SQLException {
        List<AgriculturalCredit> getDeposit = new ArrayList<>();
        boolean isSizeInRange = CompareHelper.AGCheckOutOfBound();
        boolean isIdAlreadyHad = CompareHelper.CheckIfIdExsistAG(id);
        if (!isSizeInRange && !isIdAlreadyHad) {
            CompareHelper.AddAgToArrayById(id);
            getDeposit = agriculturalCreditDao.getAgriculturalCreditByProductCode(id);
            for (int i = 0; i < getDeposit.size(); i++) {
                productName = getDeposit.get(i).getProductName();
                BankId = getDeposit.get(i).getBankId();
                percentage = getDeposit.get(i).getACFactual();
                Service = getDeposit.get(i).getACMaxServiceFee();
            }
            AgComDao agComDao = new AgComDao();
            int done = agComDao.Add(CreateObjects());
            if (done == 0) {
                System.out.println("Something wrong");
            } else {
                System.out.println("success");
            }
            comparListAg = CompareHelper.getAGList();
            WorningMessage = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("WorningMessage",WorningMessage);
            String s = jsonObject.toString();
            request.getSession().setAttribute("jsonArray",s);
            DepositCompareSize = CompareHelper.GetSizeAG();
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

    private AgComparing CreateObjects() {
        return new AgComparing(ID, sessionId, Integer.parseInt(amoutFiltered), (Integer.parseInt(months)), BankId, productName, percentage, Service);
    }

    private void filterAmount(String amount) {
        if(amount.contains(",")) {
            amoutFiltered = amount.replace(",", "");
        }else{
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
        HttpSession session = request.getSession();
        sessionId = session.getId();
        request.setAttribute("session",sessionId);
        session.setMaxInactiveInterval(86400);
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
