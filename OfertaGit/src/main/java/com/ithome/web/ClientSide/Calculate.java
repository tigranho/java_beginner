package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Comparision.DepositComDao;
import com.ithome.web.Comparision.DepositComparing;
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

@WebServlet("/Calculate")
public class Calculate extends HttpServlet {

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
    private List<Deposit> depositeAseList = new ArrayList<>();

    private MortgageSpecialOfferHelper mortgageSpecialOfferHelper = new MortgageSpecialOfferHelper();
    private ConsumerSpecialOfferHelper consumerSpecialOfferHelper = new ConsumerSpecialOfferHelper();
    private CarLoanSpecialOfferHelper carLoanSpecialOfferHelper = new CarLoanSpecialOfferHelper();
    private DepositSpecialOfferHelper depositSpecialOfferHelper = new DepositSpecialOfferHelper();
    private AgSpecialOfferHelper agSpecialOfferHelper = new AgSpecialOfferHelper();
    private CardsSpecialOfferHelper cardsSpecialOfferHelper = new CardsSpecialOfferHelper();
    private DropDownCurrancyHelper dropDownCurrancyHelper = new DropDownCurrancyHelper();
    private DropDownHelper dropDownHelper = new DropDownHelper();
    private DepositDaoController depositDaoController = new DepositDaoController();

    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private HitCounter hitCounter = new HitCounter();
    private LookUpProgram lookUpProgram = new LookUpProgram();
    private LanguageHelper languageHelper = new LanguageHelper();
    private PageNameHelper pageNameHelper = new PageNameHelper();
    private List<Deposit> depositAllInRage = new ArrayList<>();
    private List<Deposit> depositAllInSubRage = new ArrayList<>();

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

    List<Deposit> depositStartFilter = new ArrayList<>();
    List<Deposit> depositAmountFilter = new ArrayList<>();
    List<Deposit> depositCurrancyFilter = new ArrayList<>();
    List<Deposit> searchUpList = new ArrayList<>();

    String productName = null;
    int BankId = 0;
    int DEquippingPossibilitiesid = 0;
    int DEarlierWithdrawalAmountid = 0;
    int DAutoExtendPeriodid = 0;
    int DCapitalizationPercentid = 0;
    double percentage = 0;
    String BankLink = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            calculate(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            calculate(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void calculate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        //getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getParameters(request);

        filterAmount(Amount);
        //countHit();
        getPageRange();
        getApperance();
        // depositeFilterByTime();
        // depositeFilterSubbyTime();
        GetDropDownByCurrancy(dropDownsList, pageCurrancy);
        checkForCompareList();
        getDepositSpecialOffers();
        getProductnamesList();
        getMortgageSpecialOffers();
        getConsumerSpecialOffers();
        getCarLoanSpecialOffers();
        getCardsSpecialOffers();
        getAgSpecialOffers();
        createRequestes(request);
        gotoPageDepositing(request, response);
    }

    /* firstSection starts */
    private void depositeFilterByTime() throws SQLException {
        depositStartFilter = depositDaoController.getAllDepositeList();
        depositAmountFilter = new ArrayList<>();
        for (int i = 0; i < depositStartFilter.size(); i++) {
            int minAmount = depositStartFilter.get(i).getDMinAmount();
            int maxAmountFilter = depositStartFilter.get(i).getDMaxAmount();

            if (Integer.parseInt(Amount) >= minAmount  && Integer.parseInt(Amount) <= maxAmountFilter) {
                int id = Integer.parseInt(String.valueOf(depositStartFilter.get(i).getDId()));
                depositAmountFilter.addAll(FilteredList(id));
            }
        }
        FilterCurrancyList(depositAmountFilter);

    }

    private void FilterCurrancyList(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(Currancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterMonthsList(depositCurrancyFilter);
    }

    private void FilterMonthsList(List<Deposit> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int timeLineFilter = depositCurrancyFilter.get(i).getTimeLine();
            if (timeLineFilter < Integer.parseInt(montheToDays) && Integer.parseInt(montheToDays) != 0) {
                continue;
            } else if (Integer.parseInt(montheToDays) == 0) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getDId()));
                depositAllInRage.addAll(FilteredList(id));
            } else if(timeLineFilter == Integer.parseInt(montheToDays)){
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getDId()));
                depositAllInRage.addAll(FilteredList(id));
            }
        }
        arrow = 0;
        arrow2 = 0;
    }

    /* firstSection Ends */
    private List<Deposit> FilteredList(int id) throws SQLException {
        return depositDaoController.getDepositById(id);
    }

    private void convertToDays(String months) {
        switch (months) {
            case "0":
                montheToDays = "0";
                break;
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

    private void PercentageAsecSub() throws SQLException {
        depositAllInSubRage = depositDaoController.getDepositMaxMinPercentSubAsc(Integer.parseInt(amoutFiltered), pageCurrancy);
        Collections.sort(depositAllInSubRage, new DepositComarator());
        arrow = 1;
        arrow2 = 1;
    }

    /* start second  */
    private void PercentageAsec() throws SQLException {
        depositStartFilter = depositDaoController.getAllDepositeList();
        depositAmountFilter = new ArrayList<>();
        for (int i = 0; i < depositStartFilter.size(); i++) {
            int minAmount = depositStartFilter.get(i).getDMinAmount();
            int maxAmountFilter = depositStartFilter.get(i).getDMaxAmount();

            if (Integer.parseInt(Amount) >= minAmount  && Integer.parseInt(Amount) <= maxAmountFilter) {
                int id = Integer.parseInt(String.valueOf(depositStartFilter.get(i).getDId()));
                depositAmountFilter.addAll(FilteredList(id));
            }

        }
        FilterCurrancyListAsc(depositAmountFilter);
    }

    private void FilterCurrancyListAsc(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(Currancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterMonthsListAsc(depositCurrancyFilter);
    }

    private void FilterByRange1(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getDMinAmount();
            if (Integer.parseInt(Amount) >= minAmount || Integer.parseInt(Amount) <= minAmount && Integer.parseInt(MaxAmount) <= minAmount) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterMonthsListAsc(depositCurrancyFilter);
    }

    private void FilterMonthsListAsc(List<Deposit> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int timeLineFilter = depositCurrancyFilter.get(i).getTimeLine();
            if (timeLineFilter < Integer.parseInt(montheToDays) && Integer.parseInt(montheToDays) != 0) {
                continue;
            } else if (Integer.parseInt(montheToDays) == 0) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getDId()));
                depositAllInRage.addAll(FilteredList(id));
            } else if(timeLineFilter == Integer.parseInt(montheToDays)){
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getDId()));
                depositAllInRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComaratorAmount());
        arrow = 1;
        arrow2 = 1;

    }

    /* End second  */
    private void PercentageDescSub() throws SQLException {
        depositAllInSubRage = depositDaoController.getDepositMaxMinPercentSubDes(Integer.parseInt(amoutFiltered), pageCurrancy);

        arrow = 0;
        arrow2 = 0;
    }

    private void PercentageDesc() throws SQLException {
        // depositAllInSubRage = depositDaoController.getSemiDepositbyMaxMinAmount(Integer.parseInt(amoutFiltered), Integer.parseInt(MaxAmount),pageCurrancy);
        depositAllInRage = depositDaoController.getDepositMaxMinPercentSub(Integer.parseInt(amoutFiltered), pageCurrancy);

        arrow = 0;
        arrow2 = 0;
    }

    private void AmountAsecSubTime() throws SQLException {
        depositAllInSubRage = depositDaoController.getDepositMaxMinAmountSubByTime(Integer.parseInt(amoutFiltered), Integer.parseInt(months), pageCurrancy);
        Collections.sort(depositAllInSubRage, new DepositComaratorAmount());
        arrow = 1;
        arrow2 = 1;
    }

    private void AmountAsecTime() throws SQLException {
        depositStartFilter = depositDaoController.getAllDepositeList();
        depositAmountFilter = new ArrayList<>();
        for (int i = 0; i < depositStartFilter.size(); i++) {
            int minAmount = depositStartFilter.get(i).getDMinAmount();
            int maxAmountFilter = depositStartFilter.get(i).getDMaxAmount();

            if (Integer.parseInt(Amount) >= minAmount  && Integer.parseInt(Amount) <= maxAmountFilter) {
                int id = Integer.parseInt(String.valueOf(depositStartFilter.get(i).getDId()));
                depositAmountFilter.addAll(FilteredList(id));
            }

        }
        FilterCurrancyListAsc(depositAmountFilter);
    }

    private void FilterCurrancyList2Asc(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(Currancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterMonthsListAsc(depositCurrancyFilter);
    }

    private void FilterMonthsList2Asc(List<Deposit> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int timeLineFilter = depositCurrancyFilter.get(i).getTimeLine();
            if (timeLineFilter < Integer.parseInt(montheToDays) && Integer.parseInt(montheToDays) != 0) {
                continue;
            } else if (Integer.parseInt(montheToDays) == 0) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getDId()));
                depositAllInRage.addAll(FilteredList(id));
            } else {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getDId()));
                depositAllInRage.addAll(FilteredList(id));
            }
        }
        /* depositAllInRage = depositDaoController.getDepositbyMaxMinAmountAsecByTime(Integer.parseInt(amoutFiltered), Integer.parseInt(months), pageCurrancy);*/
        Collections.sort(depositAllInRage, new DepositComaratorAmount());
        arrow = 1;
        arrow2 = 1;
    }

    private void depositeFilterSubbyTime() throws SQLException {
        depositAllInSubRage = depositDaoController.getDepositMaxMinAmountByTime(Integer.parseInt(amoutFiltered), Integer.parseInt(months), pageCurrancy);

        arrow = 0;
        arrow2 = 0;
    }


    private void gotoPageDepositing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (montheToDays.equals("0")) {
            request.getRequestDispatcher("DepositClient").forward(request, response);
        } else {
            request.getRequestDispatcher("/CalcDeposit.jsp").forward(request, response);
        }
    }

    private void getPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName(getPageNameArm(pageName));
    }

    private String getPageNameArm(String pageName) {
        return pageNameHelper.ArmPageName(pageName);
    }

    private void getAgSpecialOffers() throws SQLException {
        agriculturalCreditListoffer = agSpecialOfferHelper.getStarted();
    }

    private void getCardsSpecialOffers() throws SQLException {
        cardsListOffer = cardsSpecialOfferHelper.getStarted();
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

    private void getApperance() throws SQLException {
        searchUpList = new ArrayList<>();
        depositeAseList = new ArrayList<>();
        searchUpList = searchDatabase();
        for (int i = 0; i < searchUpList.size(); i++) {
            int firstSearch = searchUpList.get(i).getDMinAmount();
            int firstSearchMonth = searchUpList.get(i).getTimeLine();
            String firstSearchCurrancy = searchUpList.get(i).getCurrancy();
            MaxAmount = null;
            getMaxAmount();
            if (Integer.parseInt(amoutFiltered) >= firstSearch || Integer.parseInt(amoutFiltered) <= firstSearch && Integer.parseInt(MaxAmount) <= firstSearch) {
                int filterdMonth = 0;
                filterdMonth = firstSearchMonth / 30;
                if (Integer.parseInt(months) == (filterdMonth)) {
                    if (firstSearchCurrancy.equals(pageCurrancy)) {
                        int id = Integer.parseInt(String.valueOf(searchUpList.get(i).getDId()));
                        depositeAseList.addAll(FilteredList(id));
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            } else {
                continue;
            }
            Collections.shuffle(depositeAseList);
        }

    }

    private List<Deposit> searchDatabase() throws SQLException {
        return depositSpecialOfferHelper.getAppearnace();
    }


    private void getProductnamesList() throws SQLException {
        ProductNameDaoController productNameDaoController = new ProductNameDaoController();
        productNameList = productNameDaoController.getAllProductNames();
    }

    private void GetDropDownByCurrancy(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }


    private void createRequestes(HttpServletRequest request) {
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("PageName", pageName);
        request.setAttribute("PageName", PageToGo);
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
        request.setAttribute("PageToGo", pageName);
        request.setAttribute("months", months);

        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);

    }

    private String getMaxAmount() throws SQLException {
        getPageRange();
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
        for (int i = 0; i < dropDownsListWithCurrancy.size(); i++) {
            MaxAmount = dropDownsListWithCurrancy.get(i).getMaxAmount();
        }
        return MaxAmount;
    }

    private void getParameters(HttpServletRequest request) throws SQLException {
        /*value = request.getParameter("value");*/


        MaxAmount = getMaxAmount();


        if (request.getParameter("range") != null) {
            Amount = request.getParameter("range");
            filterAmount(Amount);

        } else if (request.getParameter("Amount") != null) {
            Amount = request.getParameter("Amount");
            filterAmount(Amount);
        }
        Currancy = request.getParameter("Currancy");
        months = request.getParameter("months");
        convertToDays(months);
        city = request.getParameter("City");

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

    /*private void countHit() throws SQLException {
        hitCounter.countingHits(pageName, pageCurrancy, city, language, sessionId);
    }*/


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

    private void startCompare(int id, HttpServletRequest request) throws SQLException {

        List<Deposit> getDeposit = new ArrayList<>();
        boolean isSizeInRange = CompareHelper.DepositCheckOutOfBound();
        boolean isIdAlreadyHad = CompareHelper.CheckIfIdExsist(id);
        if (!isSizeInRange && !isIdAlreadyHad) {
            CompareHelper.AddDepositToArrayById(id);
            getDeposit = depositDaoController.getDepositByCardCode(id);
            for (int i = 0; i < getDeposit.size(); i++) {
                productName = getDeposit.get(i).getProductName();
                BankId = getDeposit.get(i).getBankId();
                DEquippingPossibilitiesid = getDeposit.get(i).getDEquippingPossibilitiesid();
                DEarlierWithdrawalAmountid = getDeposit.get(i).getDEarlierWithdrawalAmountid();
                DAutoExtendPeriodid = getDeposit.get(i).getDAutoExtendPeriodid();
                DCapitalizationPercentid = getDeposit.get(i).getDCapitalizationPercentid();
                percentage = getDeposit.get(i).getDPercentage();
            }

            DepositComDao depositComDao = new DepositComDao();
            int done = depositComDao.Add(CreateObjects());
            if (done == 0) {
                System.out.println("Something wrong");
            } else {
                System.out.println("success");
            }
            comparListDeposit = CompareHelper.getDepositList(sessionId);
            WorningMessage = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("WorningMessage", WorningMessage);
            String s = jsonObject.toString();
            request.getSession().setAttribute("jsonArray", s);
            DepositCompareSize = CompareHelper.GetSize();
        } else {
            if (DepositCompareSize > 3) {
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

        for (int i = 0; i < comparListDeposit.size(); i++) {
            System.out.println(comparListDeposit.get(i));
        }
    }

    private DepositComparing CreateObjects() {
        return new DepositComparing(ID, sessionId, Integer.parseInt(amoutFiltered), Integer.parseInt(montheToDays), BankId, productName, DEquippingPossibilitiesid, DEarlierWithdrawalAmountid, DAutoExtendPeriodid, DCapitalizationPercentid, percentage);
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

    private void SotringMachanizm(String sorting) throws SQLException {
        switch (sorting) {
            case "DescAmount":
                depositeFilterByTime();
                //depositeFilterSubbyTime();
                break;
            case "AsecAmount":
                PercentageAsec();
                // AmountAsecSubTime();
                break;
            case "DescPercent":
                depositeFilterByTime();
                //PercentageDescSub();
                break;
            case "AsecPercent":
                PercentageAsec();
                //PercentageAsecSub();
                break;
        }
    }

    static class DepositComarator implements Comparator<Deposit> {

        @Override
        public int compare(Deposit o1, Deposit o2) {
            return Double.compare(o2.getDPercentage(), o1.getDPercentage());
        }
    }

    static class DepositComaratorAmount implements Comparator<Deposit> {

        @Override
        public int compare(Deposit o1, Deposit o2) {
            return Double.compare(o1.getDPercentage(), o2.getDPercentage());
        }
    }
}

