package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.CarLoanDao;
import com.ithome.web.AdminDao.CardsDao;
import com.ithome.web.AdminDao.ConsumerCreditDaoController;
import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Comparision.CarComDao;
import com.ithome.web.Comparision.CarComparing;
import com.ithome.web.Helpers.*;
import com.ithome.web.Localization.CheckLanguageAndCurrency;
import com.ithome.web.SearchController.Product;
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

@WebServlet("/AutoCalulate")
public class AutoCalulate extends HttpServlet {
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
    private List<CarLoans> depositeAseList = new ArrayList<>();

    private MortgageSpecialOfferHelper mortgageSpecialOfferHelper = new MortgageSpecialOfferHelper();
    private ConsumerSpecialOfferHelper consumerSpecialOfferHelper = new ConsumerSpecialOfferHelper();
    private CarLoanSpecialOfferHelper carLoanSpecialOfferHelper = new CarLoanSpecialOfferHelper();
    private DepositSpecialOfferHelper depositSpecialOfferHelper = new DepositSpecialOfferHelper();
    private AgSpecialOfferHelper agSpecialOfferHelper = new AgSpecialOfferHelper();
    private CardsSpecialOfferHelper cardsSpecialOfferHelper = new CardsSpecialOfferHelper();
    private DropDownCurrancyHelper dropDownCurrancyHelper = new DropDownCurrancyHelper();
    private DropDownHelper dropDownHelper = new DropDownHelper();
    private CarLoanDao carLoanDao = new CarLoanDao();

    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private HitCounter hitCounter = new HitCounter();
    private LookUpProgram lookUpProgram = new LookUpProgram();
    private LanguageHelper languageHelper = new LanguageHelper();
    private PageNameHelper pageNameHelper = new PageNameHelper();
    private List<CarLoans> depositAllInRage = new ArrayList<>();
    private List<CarLoans> depositAllInSubRage = new ArrayList<>();

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
    private double percentageMainData = 0;

    List<CarLoans> depositStartFilter = new ArrayList<>();
    List<CarLoans> depositAmountFilter = new ArrayList<>();
    List<CarLoans> depositCurrancyFilter = new ArrayList<>();
    List<CarLoans> searchUpList = new ArrayList<>();

    List<CarLoans> subDepositStartFilter = new ArrayList<>();
    List<CarLoans> subDepositAmountFilter = new ArrayList<>();
    List<CarLoans> subDepositCurrancyFilter = new ArrayList<>();
    List<CarLoans> depositOptionFilter = new ArrayList<>();
    List<CarLoans> depositOptiosInRage = new ArrayList<>();

    private int MortgageCompareSize = 0;
    private String valueTwo = null;
    private String rangeTwo = null;
    private int PercentageSecond = 0;
    private float PercentageSecondFloat = 0;
    private int options = 0;

    private int oldAmount = 0;
    String productName = null;
    int BankId = 0;
    int Service = 0;
    double percentage = 0;
    String BankLink = null;
    private int RangeTwo = 0;

    int getAgList=0;
    int getDpList = 0;
    int getMoList = 0;
    int getCoList = 0;
    int getCaList = 0;
    int getMiList = 0;
    private int BankIdFromData = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            autoCalulate(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            autoCalulate(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void autoCalulate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        //getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getParameters(request);

        filterAmount(Amount);
        getPageRange();
        getApperance();
        checkForCompareList();
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


    private List<CarLoans> FilteredList(int id) throws SQLException {
        return carLoanDao.getCarLoansById(id);
    }

    /* firstSection starts */
    private void PercentageAsecSorted() throws SQLException {
        depositStartFilter = carLoanDao.getAllCarLoans();
        depositAmountFilter = new ArrayList<>();
        MaxAmount=null;
        getMaxAmount();
        for (int i = 0; i < depositStartFilter.size(); i++) {
            int minAmount = depositStartFilter.get(i).getCLMinAmount();
            int maxAmount = depositStartFilter.get(i).getCLMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount ){
                int id = Integer.parseInt(String.valueOf(depositStartFilter.get(i).getCLId()));
                depositAmountFilter.addAll(FilteredList(id));
            }

        }
        FilterCurrancyListSorted(depositAmountFilter);

    }

    private void FilterCurrancyListSorted(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(Currancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterMonthsListSorted(depositCurrancyFilter);
    }


    private void FilterMonthsListSorted(List<CarLoans> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            double timeLineFilter = depositCurrancyFilter.get(i).getCLFatual();
            int minMonthsFromData = depositStartFilter.get(i).getCLMinPeriodMonths();
            int maxMonthsFromData = depositStartFilter.get(i).getCLMaxPeriodMonths();
            if(Integer.parseInt(months) >= minMonthsFromData) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getCLId()));
                depositAllInRage.addAll(FilteredList(id));
            }

        }

        Collections.sort(depositAllInRage, new ComparePercentage());
        arrow = 1;
        arrow2 = 1;
    }

    static class ComparePercentage implements Comparator<CarLoans> {

        @Override
        public int compare(CarLoans o1, CarLoans o2) {
            return Double.compare(o2.getCLFatual(), o1.getCLFatual());
        }
    }

    /* firstSection Ends */

    /* firstSection starts */
    private void PercentageAsec() throws SQLException {
        depositStartFilter = carLoanDao.getAllCarLoans();
        depositAmountFilter = new ArrayList<>();
        MaxAmount = null;
        getMaxAmount();
        for (int i = 0; i < depositStartFilter.size(); i++) {
            int minAmount = depositStartFilter.get(i).getCLMinAmount();
            int maxAmount = depositStartFilter.get(i).getCLMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount){

                int id = Integer.parseInt(String.valueOf(depositStartFilter.get(i).getCLId()));
                depositAmountFilter.addAll(FilteredList(id));
            }

        }
        FilterCurrancyList(depositAmountFilter);

    }


    private void FilterCurrancyList(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(Currancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterMonthsList(depositCurrancyFilter);
    }


    private void FilterMonthsList(List<CarLoans> depositOptiosInRage) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositOptiosInRage.size(); i++) {
            double timeLineFilter = depositOptiosInRage.get(i).getCLFatual();
            int minMonthsFromData = depositStartFilter.get(i).getCLMinPeriodMonths();
            int maxMonthsFromData = depositStartFilter.get(i).getCLMaxPeriodMonths();
            if(Integer.parseInt(months) >= minMonthsFromData) {
                int id = Integer.parseInt(String.valueOf(depositOptiosInRage.get(i).getCLId()));
                depositAllInRage.addAll(FilteredList(id));
            }

        }
        Collections.sort(depositAllInRage, new ComparePercentage2());
        arrow = 0;
        arrow2 = 0;
    }

    static class ComparePercentage2 implements Comparator<CarLoans> {

        @Override
        public int compare(CarLoans o1, CarLoans o2) {
            return Double.compare(o1.getCLFatual(), o2.getCLFatual());
        }
    }
    /* firstSection Ends */


    class DepositComarator implements Comparator<CarLoans> {
        @Override
        public int compare(CarLoans o1, CarLoans o2) {
            return Double.compare(o1.getCLFatual(), o2.getCLFatual());
        }
    }

    class DepositComaratorAmount implements Comparator<CarLoans> {
        @Override
        public int compare(CarLoans o1, CarLoans o2) {
            return Integer.compare(o2.getCLMinPeriodMonths(), o1.getCLMinPeriodMonths());
        }
    }


    private void GoPageToGo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(PageToGo.equals("Cars")){
            request.setAttribute("Bankid",BankIdFromData);
            AutoBanks autoBanks = new AutoBanks();
            autoBanks.doGet(request,response);
        }else {
            request.getRequestDispatcher("/CalculateAuto.jsp").forward(request, response);}
    }

    private void createRequestes(HttpServletRequest request) {
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("PageName", pageName);
        request.setAttribute("City", city);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);
        request.setAttribute("percentageMainData", percentageMainData);

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
        request.setAttribute("MaxAmounr", MaxAmount);
        request.setAttribute("maxAmounr", MaxAmount);

        request.setAttribute("minAmount", amoutFiltered);
        request.setAttribute("months", months);
        request.setAttribute("PageToGo", PageToGo);
        request.setAttribute("arrow", arrow);
        request.setAttribute("arrow2", arrow2);

        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);

        request.setAttribute("RangeTwo", RangeTwo);
        request.setAttribute("valueTwo", valueTwo);
        request.setAttribute("percentageSecond", PercentageSecond);
        request.setAttribute("options", options);

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
        getMaxAmount();
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);

    }

    private void getMaxAmount() throws SQLException {
        getPageRange();
        String minAmount = null;
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
        for (int i = 0; i <dropDownsListWithCurrancy.size() ; i++) {
            MaxAmount = dropDownsListWithCurrancy.get(i).getMaxAmount();
            minAmount = dropDownsListWithCurrancy.get(i).getMinAmount();
            System.out.println("MaxAmount " + MaxAmount + " minAmount " + minAmount);
        }

    }

    private void getApperance() throws SQLException {
        searchUpList = new ArrayList<>();
        depositeAseList = new ArrayList<>();

        searchUpList = searchDatabase();
        for (int i = 0; i <searchUpList.size() ; i++) {
            int firstSearch = searchUpList.get(i).getCLMinAmount();
            String firstSearchCurrancy = searchUpList.get(i).getCurrancy();
            int minMonthsFromData = searchUpList.get(i).getCLMinPeriodMonths();
            int maxMonthsFromData = searchUpList.get(i).getCLMaxPeriodMonths();
            if (Integer.parseInt(amoutFiltered) >= firstSearch && firstSearchCurrancy.equals(pageCurrancy) && Integer.parseInt(months) >= minMonthsFromData  && Integer.parseInt(months) <= maxMonthsFromData ) {
                int id = Integer.parseInt(String.valueOf(searchUpList.get(i).getCLId()));
                depositeAseList.addAll(FilteredList(id));
            }
        }
    }

    private List<CarLoans> searchDatabase() throws SQLException {
        return carLoanSpecialOfferHelper.getAppearnace();
    }

    private void getPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName(getPageNameArm(pageName));

    }

    private String getPageNameArm(String pageName) {
        return pageNameHelper.ArmPageName(pageName);
    }


    private void getParameters(HttpServletRequest request) throws SQLException, IOException {
        if (request.getParameter("options") != null) {
            options = Integer.parseInt(request.getParameter("options"));
        } else {
            options = 0;
        }

        if(request.getParameter("bankId")!=null){
            BankIdFromData = Integer.parseInt(request.getParameter("bankId"));
        }else{
            BankIdFromData = 0;
        }

        if (request.getParameter("range_two") != null) {
            RangeTwo = Integer.parseInt(request.getParameter("range_two"));
        } else if (request.getParameter("value_two") != null) {
            RangeTwo = Integer.parseInt(request.getParameter("value_two"));
        }
        if (request.getParameter("value_two") != null) {
            valueTwo = request.getParameter("value_two");
        } else {
            valueTwo = "";
        }


        if (request.getParameter("range") != null) {
            Amount = request.getParameter("range");
            filterAmount(Amount);

        } else if (request.getParameter("Amount") != null) {
            Amount = request.getParameter("Amount");
            filterAmount(Amount);
        } else if (request.getParameter("value") != null) {
            Amount = request.getParameter("value");
            filterAmount(Amount);
        }


        if (request.getParameter("range_two") != null) {
            valueTwo = request.getParameter("range_two");
            if(oldAmount == 0){
                //CalculateALL(request);
                oldAmount = Integer.parseInt(valueTwo);
                int getNewPercentage= (int) ((Float.parseFloat(valueTwo)/ Float.parseFloat(Amount))*100);
             /*   StringBuilder sb = new StringBuilder(request.getParameter("percentageSecond"));
                sb.deleteCharAt(sb.length() - 1);
                int percentage2 = (int) Float.parseFloat(String.valueOf(sb));*/
                getPercentageValue(getNewPercentage, request);
            }
            else if (oldAmount != Integer.parseInt(valueTwo)) {
                CalculateALL(request);
                oldAmount = Integer.parseInt(valueTwo);
            } else {
                StringBuilder sb = new StringBuilder(request.getParameter("percentageSecond"));
                sb.deleteCharAt(sb.length() - 1);
                int percentage2 = (int) Float.parseFloat(String.valueOf(sb));
                getPercentageValue(percentage2, request);
            }
        } else {
            valueTwo = "";
        }


        Currancy = request.getParameter("Currancy");
        months = request.getParameter("months");
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
            jsonObject.put("WorningMessage", WorningMessage);
            String s = jsonObject.toString();
            request.getSession().setAttribute("jsonArray", s);
            ID = 0;
        }
        if (request.getParameter("pageNameToDelete") != null) {
            PageNameToDelete = request.getParameter("pageNameToDelete");
            deleteList(PageNameToDelete);
        } else {
            PageNameToDelete = "";
        }
        if (request.getParameter("MaxAmount") != null) {
            MaxAmount = request.getParameter("MaxAmount");
        } else if (request.getParameter("maxAmount") != null) {
            MaxAmount = request.getParameter("maxAmount");
        } else if (request.getParameter("MaxAmounr") != null) {
            MaxAmount = request.getParameter("MaxAmounr");
        } else {
            getMaxAmount();
        }

        //percentageMainData = getFactual();


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

    private double getFactual() throws SQLException {
        CarLoanDao carLoanDao = new CarLoanDao();
        List<CarLoans> carLoansFactual2 =  new ArrayList<>();
        carLoansFactual2 = carLoanDao.getCarLoansByProductCode(ID);
        return carLoansFactual2.get(0).getCLFatual();
    }

    private void CalculateALL(HttpServletRequest request) {
        PercentageSecondFloat = ((Float.parseFloat(valueTwo) / Float.parseFloat(Amount)) * 100);
        if (PercentageSecond < 10 || PercentageSecondFloat < 10) {
            PercentageSecond = 10;
        } else if (PercentageSecond > 80 || PercentageSecondFloat > 80) {
            PercentageSecond = 80;
        } else {
            PercentageSecond = (int) PercentageSecondFloat;
            valueTwo = request.getParameter("range_two");
        }
    }

    private void getPercentageValue(int percentage2, HttpServletRequest request) {

        if (percentage2 >= 10 && percentage2 <= 80) {
            PercentageSecond = percentage2;
            valueTwo = String.valueOf(((Float.parseFloat(valueTwo) / percentage2 * 100)));

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

    private void startCompare(int id,HttpServletRequest request) throws SQLException {
        List<CarLoans> getDeposit = new ArrayList<>();
        boolean isSizeInRange = CompareHelper.AutoCheckOutOfBound();
        boolean isIdAlreadyHad = CompareHelper.CheckIfIdExsistAuto(id);
        if (!isSizeInRange && !isIdAlreadyHad) {
            CompareHelper.AddAutoToArrayById(id);
            getDeposit = carLoanDao.getCarLoansByProductCode(id);
            for (int i = 0; i < getDeposit.size(); i++) {
                productName = getDeposit.get(i).getProductName();
                BankId = getDeposit.get(i).getBankId();
                percentage = getDeposit.get(i).getCLFatual();
                Service = getDeposit.get(i).getCLMaxServiceFee();
            }
            CarComDao carComDao = new CarComDao();
            int done = carComDao.Add(CreateObjects());
            if (done == 0) {
                System.out.println("Something wrong");
            } else {
                System.out.println("success");
            }
            comparListCarLoan = CompareHelper.getCarLoanList(sessionId);
            WorningMessage = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("WorningMessage",WorningMessage);
            String s = jsonObject.toString();
            request.getSession().setAttribute("jsonArray",s);
            DepositCompareSize = CompareHelper.GetSizeAuto();
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
    private CarComparing CreateObjects() {
        return new CarComparing(ID, sessionId, Integer.parseInt(amoutFiltered), Integer.parseInt(months), BankId, productName, percentage, Service,RangeTwo);
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
        if (pageName == null) {
            pageName = "Ավտովարկ";
        } else {
            pageName = pageNameHelper.pageName(request);
        }
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

    private void checkForCompareList() {
        comparListDeposit = CompareHelper.getDepositList(sessionId);
        comparListMortgage = CompareHelper.getMortgageList(sessionId);
        comparListConsumer =  CompareHelper.getConsumerList(sessionId);
        comparListCarLoan =  CompareHelper.getCarLoanList(sessionId);
        comparListMicro =  CompareHelper.getMicroList(sessionId);
        comparListAg = CompareHelper.getAgList(sessionId);
        comparListCard =  CompareHelper.getCardList(sessionId);
    }
}
