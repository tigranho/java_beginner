package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.MortgageDaoController;
import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Comparision.MoComparing;
import com.ithome.web.Comparision.MortgageComDao;
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
import javax.swing.text.html.Option;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/CalculateMort")
public class CalculateMort extends HttpServlet {

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
    private List<Mortgage> depositeAseList = new ArrayList<>();

    private MortgageSpecialOfferHelper mortgageSpecialOfferHelper = new MortgageSpecialOfferHelper();
    private ConsumerSpecialOfferHelper consumerSpecialOfferHelper = new ConsumerSpecialOfferHelper();
    private CarLoanSpecialOfferHelper carLoanSpecialOfferHelper = new CarLoanSpecialOfferHelper();
    private DepositSpecialOfferHelper depositSpecialOfferHelper = new DepositSpecialOfferHelper();
    private AgSpecialOfferHelper agSpecialOfferHelper = new AgSpecialOfferHelper();
    private CardsSpecialOfferHelper cardsSpecialOfferHelper = new CardsSpecialOfferHelper();
    private DropDownCurrancyHelper dropDownCurrancyHelper = new DropDownCurrancyHelper();
    private DropDownHelper dropDownHelper = new DropDownHelper();
    private MortgageDaoController mortgageDaoController = new MortgageDaoController();

    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private HitCounter hitCounter = new HitCounter();
    private LookUpProgram lookUpProgram = new LookUpProgram();
    private LanguageHelper languageHelper = new LanguageHelper();
    private PageNameHelper pageNameHelper = new PageNameHelper();
    private List<Mortgage> depositAllInRage = new ArrayList<>();
    private List<Mortgage> depositAllInSubRage = new ArrayList<>();

    private String Sorting = null;
    private int arrow = 0;
    private int arrow2 = 0;
    private int ID = 0;
    private int BankIdFromData = 0;

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
    private int RangeTwo = 0;

    private int MortgageCompareSize = 0;
    private String valueTwo = null;
    private String rangeTwo = null;
    private int PercentageSecond = 0;
    private float PercentageSecondFloat = 0;
    private int options = 0;

    private int oldAmount = 0;
    private float oldPercentage = 0;
    List<Mortgage> depositStartFilter = new ArrayList<>();
    List<Mortgage> depositAmountFilter = new ArrayList<>();
    List<Mortgage> depositCurrancyFilter = new ArrayList<>();
    List<Mortgage> searchUpList = new ArrayList<>();

    List<Mortgage> subDepositStartFilter = new ArrayList<>();
    List<Mortgage> subDepositAmountFilter = new ArrayList<>();
    List<Mortgage> subDepositCurrancyFilter = new ArrayList<>();
    List<Mortgage> depositOptionFilter = new ArrayList<>();
    List<Mortgage> depositOptiosInRage = new ArrayList<>();

    String productName = null;
    int BankId = 0;
    int Service = 0;
    double percentage = 0;
    String BankLink = null;

    int getAgList = 0;
    int getDpList = 0;
    int getMoList = 0;
    int getCoList = 0;
    int getCaList = 0;
    int getMiList = 0;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            calculateMort(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            calculateMort(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void calculateMort(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
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
        getPageRange();
        getApperance();
        GetDropDownByCurrancy(dropDownsList, pageCurrancy);
        //getDepositSpecialOffers();
        //getProductnamesList();
        getMortgageSpecialOffers();
        getConsumerSpecialOffers();
        getCarLoanSpecialOffers();
        getCardsSpecialOffers();
        getAgSpecialOffers();
        getDepositSpecialOffers();
        createRequestes(request);
        gotoPageMortgaging(request, response);
    }

    private void gotoPageMortgaging(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (PageToGo.equals("MortgageBank")) {
            request.setAttribute("Bankid", BankIdFromData);
            MortgageBanks mortgageBanks = new MortgageBanks();
            mortgageBanks.doGet(request, response);
        }/*if(months.equals("0")){
            request.getRequestDispatcher("/Mortgage.jsp").forward(request, response);
        }*/ else {
            request.getRequestDispatcher("/CalcMortgage.jsp").forward(request, response);
        }
    }

    private void getApperance() throws SQLException {
        searchUpList = new ArrayList<>();
        depositeAseList = new ArrayList<>();

        searchUpList = searchDatabase();
        for (int i = 0; i < searchUpList.size(); i++) {
            int firstSearch = searchUpList.get(i).getMMinAmount();
            String firstSearchCurrancy = searchUpList.get(i).getCurrancy();
            int monthsToCheck = searchUpList.get(i).getMMinPeriodMonth()/12;
            int monthsToCheckMax = searchUpList.get(i).getMMaxPeriodMonth()/12;
            if (Integer.parseInt(amoutFiltered) >= firstSearch && firstSearchCurrancy.equals(pageCurrancy) && Integer.parseInt(months) <= monthsToCheck  && Integer.parseInt(months) <= monthsToCheckMax) {
                int id = Integer.parseInt(String.valueOf(searchUpList.get(i).getMId()));
                depositeAseList.addAll(FilteredList(id));
            }
            Collections.shuffle(depositeAseList);
        }

    }

    private List<Mortgage> searchDatabase() throws SQLException {
        return mortgageSpecialOfferHelper.getAppearnace();
    }

    private void getParameters(HttpServletRequest request) throws SQLException {
        if (request.getParameter("options") != null) {
            options = Integer.parseInt(request.getParameter("options"));
        } else {
            options = 0;
        }
        if (request.getParameter("bankId") != null) {
            BankIdFromData = Integer.parseInt(request.getParameter("bankId"));
        } else {
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


        if (request.getParameter("range_two") != null && request.getParameter("fromMain") == null) {
            valueTwo = request.getParameter("range_two");
            if (oldAmount == 0) {
                oldAmount = Integer.parseInt(valueTwo);
                int getNewPercentage = (int) ((Float.parseFloat(valueTwo) / Float.parseFloat(Amount)) * 100);
                getPercentageValue(getNewPercentage, request);
            } else if (oldAmount != Integer.parseInt(valueTwo)) {
                CalculateALL(request);
                oldAmount = Integer.parseInt(valueTwo);
            } else {
                StringBuilder sb = new StringBuilder(request.getParameter("percentageSecond"));
                sb.deleteCharAt(sb.length() - 1);
                int percentage2 = (int) Float.parseFloat(String.valueOf(sb));
                getPercentageValue(percentage2, request);
            }

        } else if (request.getParameter("range_two") != null && request.getParameter("fromMain") != null) {
            valueTwo = request.getParameter("range_two");
            StringBuilder sb = new StringBuilder(request.getParameter("percentageSecond"));
            sb.deleteCharAt(sb.length() - 1);
            int percentage2 = (int) Float.parseFloat(String.valueOf(sb));
            if (oldAmount == 0) {
                if (percentage2 != 0 && percentage2 > 10) {
                    oldPercentage = percentage2;
                    getPercentageValue(percentage2, request);
                } else {
                    oldAmount = Integer.parseInt(valueTwo);
                    int getNewPercentage = (int) ((Float.parseFloat(valueTwo) / Float.parseFloat(Amount)) * 100);
                    getPercentageValue(getNewPercentage, request);
                }
            } else if (oldAmount != Integer.parseInt(valueTwo)) {
                CalculateALL(request);
                oldAmount = Integer.parseInt(valueTwo);
                RangeTwo = oldAmount;
            } else {
                sb = new StringBuilder(request.getParameter("percentageSecond"));
                sb.deleteCharAt(sb.length() - 1);
                percentage2 = (int) Float.parseFloat(String.valueOf(sb));
                getPercentageValue(percentage2, request);
            }
        } else {
            valueTwo = "";
        }


        Currancy = request.getParameter("Currancy");
        if (request.getParameter("months") != null) {
            months = request.getParameter("months");
        }
        if (months.equals("0")) {
            months = "1";
        }
        //convertToDays(months);
        if (request.getParameter("city") == null) {
            //  city = lookUpProgram.start(request);
        } else {
            city = request.getParameter("city");
        }

        if (request.getParameter("range") != null) {
            Amount = request.getParameter("range");
            filterAmount(Amount);
        } else if (request.getParameter("Amount") != null) {
            Amount = request.getParameter("Amount");
            filterAmount(Amount);
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
        if (request.getParameter("PageToGo") != null) {
            PageToGo = request.getParameter("PageToGo");
        } else {
            PageToGo = pageName;
        }

        if (request.getParameter("id") != null) {
            ID = Integer.parseInt(request.getParameter("id"));
            startCompare(ID, request);

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
        if (request.getParameter("value_two") != null) {
            valueTwo = request.getParameter("value_two");
        } else {
            valueTwo = "";
        }

        if (request.getParameter("range_two") != null) {
            valueTwo = request.getParameter("range_two");
        } else {
            valueTwo = "";
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
        } else {
            CalculateALL(request);
        }

    }


    private void startCompare(int id, HttpServletRequest request) throws SQLException {
        List<Mortgage> getDeposit = new ArrayList<>();
        boolean isSizeInRange = CompareHelper.DepositCheckOutOfBoundMortgage();
        boolean isIdAlreadyHad = CompareHelper.CheckIfIdExsistMortgage(id);
        if (!isSizeInRange && !isIdAlreadyHad) {
            CompareHelper.AddMortgageToArrayById(id);
            getDeposit = mortgageDaoController.getMortgageByProductCode(id);
            months = "0";
            for (int i = 0; i < getDeposit.size(); i++) {
                productName = getDeposit.get(i).getProductName();
                BankId = getDeposit.get(i).getBankId();
                percentage = getDeposit.get(i).getMFatual();
                Service = getDeposit.get(i).getMMaxServiceFee();

                    months = String.valueOf(getDeposit.get(i).getMMinPeriodMonth());

            }
            MortgageComDao mortgageComDao = new MortgageComDao();
            int done = mortgageComDao.Add(CreateObjects());
            if (done == 0) {
                System.out.println("Something wrong");
            } else {
                System.out.println("success");
            }
            comparListMortgage = CompareHelper.getMortgageList(sessionId);
            WorningMessage = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("WorningMessage", WorningMessage);
            String s = jsonObject.toString();
            request.getSession().setAttribute("jsonArray", s);
            MortgageCompareSize = CompareHelper.GetSizeMortgage();
        } else {
            if (MortgageCompareSize > 3) {
                WorningMessage = "";
                WorningMessage = "Դուք չեք կարող 4 ից ավել համեմատել։";
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("WorningMessage", WorningMessage);
                String s = jsonObject.toString();
                request.getSession().setAttribute("jsonArray", s);
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

    private void checkForCompareList() {
        comparListDeposit = CompareHelper.getDepositList(sessionId);
        comparListMortgage = CompareHelper.getMortgageList(sessionId);
        comparListConsumer = CompareHelper.getConsumerList(sessionId);
        comparListCarLoan = CompareHelper.getCarLoanList(sessionId);
        comparListMicro = CompareHelper.getMicroList(sessionId);
        comparListAg = CompareHelper.getAgList(sessionId);
   /*     getAgList = CompareHelper.getAgListSize();
        getDpList = CompareHelper.getDepositListSize();
        getMoList = CompareHelper.getMortgageListSize();
        getCoList = CompareHelper.getConsumerListSize();
        getCaList = CompareHelper.getCarLoanListSize();
        getMiList = CompareHelper.getMicroListSize();*/

        comparListCard = CompareHelper.getCardList(sessionId);
    }

    private MoComparing CreateObjects() {
        return new MoComparing(ID, sessionId, Integer.parseInt(amoutFiltered), Integer.parseInt(months), BankId, productName, percentage, Service, RangeTwo);
    }
    //Start from here

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
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }


    private String getPageNameArm(String pageName) {
        return pageNameHelper.ArmPageName(pageName);
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

    private void getPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName(getPageNameArm(pageName));
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

    private List<Mortgage> FilteredList(int id) throws SQLException {
        return mortgageDaoController.getMortagesById(id);
    }

    /* firstSection starts */
    private void PercentageAsecSorted() throws SQLException {
        depositStartFilter = mortgageDaoController.getAllMortage();
        depositAmountFilter = new ArrayList<>();
        for (int i = 0; i < depositStartFilter.size(); i++) {
            int minAamountFilter = depositStartFilter.get(i).getMMinAmount()/12;
            int maxnAamountFilter = depositStartFilter.get(i).getMMaxAmount()/12;
            if(Integer.parseInt(Amount) >= minAamountFilter ){
                int id = Integer.parseInt(String.valueOf(depositStartFilter.get(i).getMId()));
                depositAmountFilter.addAll(FilteredList(id));
            }

        }
        FilterCurrancyListSorted(depositAmountFilter);

    }

    private void FilterCurrancyListSorted(List<Mortgage> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(Currancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getMId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange2(depositCurrancyFilter);
    }

    private void FilterByRange2(List<Mortgage> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            double timeLineFilter = depositCurrancyFilter.get(i).getMFatual();
            int minMonthsFromData = depositAmountFilter.get(i).getMMinPeriodMonth()/12;
            int maxAmount = depositAmountFilter.get(i).getMMaxAmount()/12;
            if(Integer.parseInt(months)  >= minMonthsFromData ){
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getMId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }

        }
        FilterOptionsListSort(depositCurrancyFilter);
    }

    private void FilterOptionsListSort(List<Mortgage> depositOptionFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int OptionsFilter = depositCurrancyFilter.get(i).getMRepaymentId();
            if (OptionsFilter == options) {
                int id = (int) depositCurrancyFilter.get(i).getMId();
                depositOptiosInRage.addAll(FilteredList(id));
            }
        }
        if (depositOptiosInRage.size() == 0) {
            depositOptiosInRage.addAll(depositCurrancyFilter);
        }

        FilterMonthsListSorted(depositOptionFilter);
    }

    private void FilterMonthsListSorted(List<Mortgage> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            double timeLineFilter = depositCurrancyFilter.get(i).getMFatual();

            int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getMId()));
            depositAllInRage.addAll(FilteredList(id));

        }

        Collections.sort(depositAllInRage, new ComparePercentage2());
        arrow = 1;
        arrow2 = 1;
    }


    /* firstSection Ends */
    /* firstSection starts */
    private void PercentageAsec() throws SQLException {
        depositStartFilter = mortgageDaoController.getAllMortage();
        depositAmountFilter = new ArrayList<>();
        MaxAmount = null;
        getMaxAmount();
        for (int i = 0; i < depositStartFilter.size(); i++) {
            int minAamountFilter = depositStartFilter.get(i).getMMinAmount();
            int maxnAamountFilter = depositStartFilter.get(i).getMMaxAmount();
            if(Integer.parseInt(Amount) >= minAamountFilter ){
                int id = Integer.parseInt(String.valueOf(depositStartFilter.get(i).getMId()));
                depositAmountFilter.addAll(FilteredList(id));
            }

        }
        FilterCurrancyList(depositAmountFilter);

    }

    private void FilterCurrancyList(List<Mortgage> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(Currancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getMId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange(depositCurrancyFilter);
    }

    private void FilterByRange(List<Mortgage> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        int monthsOld = Integer.parseInt(months);
        if (monthsOld == 0) {
            monthsOld = 0;
        }
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            double timeLineFilter = depositAmountFilter.get(i).getMFatual();
            int minMonthsFromData = depositAmountFilter.get(i).getMMinPeriodMonth()/12;
            int maxMonthsFromData = depositStartFilter.get(i).getMMaxPeriodMonth()/12;
            if(Integer.parseInt(months) >= minMonthsFromData ) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getMId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOptionsList(depositCurrancyFilter);
    }

    private void FilterOptionsList(List<Mortgage> depositCurrancyFilter) throws SQLException {
        depositOptiosInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int OptionsFilter = depositCurrancyFilter.get(i).getMRepaymentId();
            if (OptionsFilter != 0) {
                if (OptionsFilter == options) {
                    int id = (int) depositCurrancyFilter.get(i).getMId();
                    depositOptiosInRage.addAll(FilteredList(id));
                }
            }
        }
        if (depositOptiosInRage.size() == 0) {
            for (int i = 0; i < depositCurrancyFilter.size(); i++) {
                int id = (int) depositCurrancyFilter.get(i).getMId();
                depositOptiosInRage.addAll(FilteredList(id));
            }
        }


        FilterMonthsList(depositOptiosInRage);
    }

    private void FilterMonthsList(List<Mortgage> depositOptiosInRage) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositOptiosInRage.size(); i++) {
            double timeLineFilter = depositOptiosInRage.get(i).getMFatual();

            int id = Integer.parseInt(String.valueOf(depositOptiosInRage.get(i).getMId()));
            depositAllInRage.addAll(FilteredList(id));

        }
        Collections.sort(depositAllInRage, new ComparePercentage());
        arrow = 0;
        arrow2 = 0;
    }


    /* firstSection Ends */


    private void getMaxAmount() throws SQLException {
        getPageRange();
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
        for (int i = 0; i < dropDownsListWithCurrancy.size(); i++) {
            MaxAmount = dropDownsListWithCurrancy.get(i).getMaxAmount();
        }
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

    static class ComparePercentage2 implements Comparator<Mortgage> {

        @Override
        public int compare(Mortgage o1, Mortgage o2) {
            return Double.compare(o2.getMFatual(), o1.getMFatual());
        }
    }

    static class ComparePercentage implements Comparator<Mortgage> {

        @Override
        public int compare(Mortgage o1, Mortgage o2) {
            return Double.compare(o1.getMFatual(), o2.getMFatual());
        }
    }

}
