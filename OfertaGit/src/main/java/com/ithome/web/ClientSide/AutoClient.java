package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.CarLoanDao;
import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Comparision.CarComDao;
import com.ithome.web.Comparision.CarComparing;
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
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/AutoClient")
public class AutoClient extends HttpServlet {
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
    private String valueTwo = null;
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
    private static List<Integer> comparListConsumer =  new ArrayList<>();
    private static List<Integer> comparListCarLoan =  new ArrayList<>();
    private static List<Integer> comparListMicro =  new ArrayList<>();
    private static List<Integer> comparListAg =  new ArrayList<>();
    private static List<Integer> comparListCard =  new ArrayList<>();
    private String WorningMessage = null;
    private int DepositCompareSize = 0;
    private String PageNameToDelete =null;

    private int PercentageSecond =0;
    private float PercentageSecondFloat = 0;

    List<CarLoans> depositStartFilter = new ArrayList<>();
    List<CarLoans> depositAmountFilter = new ArrayList<>();
    List<CarLoans> depositCurrancyFilter = new ArrayList<>();
    List<CarLoans> searchUpList = new ArrayList<>();

    List<CarLoans> subDepositStartFilter = new ArrayList<>();
    List<CarLoans> subDepositAmountFilter = new ArrayList<>();
    List<CarLoans> subDepositCurrancyFilter = new ArrayList<>();

    private int options = 0;
    private int RangeTwo = 0;
    private String rangeTwo = null;
    private String months = null;
    private String City = null;
    private String currancy = null;
    private String maxAmounr = null;

    String productName = null;
    int BankId = 0;
    int Service = 0;
    double percentage = 0;
    String BankLink = null;
    String MinimunDropDownAmount ="";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            autoClient(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            autoClient(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void autoClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        //getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getMaxAmount();
        getParameters(request);
        checkForCompareList();
        countHit();
        getPageRange();
        getApperance();

        GetDropDownByCurrancy(dropDownsList, pageCurrancy);
        getDepositSpecialOffers();
        getProductnamesList();
        getMortgageSpecialOffers();
        getConsumerSpecialOffers();
        getCarLoanSpecialOffers();
        getCardsSpecialOffers();
        getAgSpecialOffers();
        createRequestes(request);
        switchToPage(request, response);
    }

    private void switchToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/CarLoan.jsp").forward(request, response);
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
        request.setAttribute("maxAmounr", MaxAmount);
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
        request.setAttribute("percentageSecond", PercentageSecond);

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
        MaxAmount=null;
        getMaxAmount();
        for (int i = 0; i <searchUpList.size() ; i++) {
            int firstSearch = searchUpList.get(i).getCLMinAmount();
            String firstSearchCurrancy = searchUpList.get(i).getCurrancy();

            if (firstSearch >=  Integer.parseInt(amoutFiltered)  && firstSearch <=Integer.parseInt(MaxAmount) && firstSearchCurrancy.equals(pageCurrancy) ) {
                int id = Integer.parseInt(String.valueOf(searchUpList.get(i).getCLId()));
                depositeAseList.addAll(FilteredList(id));
            }
            Collections.shuffle(depositeAseList);
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

    private void countHit() throws SQLException {
        hitCounter.countingHits(pageName, pageCurrancy, city, language, sessionId);
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

    private void getMaxAmount() throws SQLException {
        getPageRange();
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
        for (int i = 0; i <dropDownsListWithCurrancy.size() ; i++) {
            MaxAmount = dropDownsListWithCurrancy.get(i).getMaxAmount();
            MinimunDropDownAmount = dropDownsListWithCurrancy.get(i).getMinAmount();
        }
    }

    private void getParameters(HttpServletRequest request) throws SQLException, IOException {
        if (request.getParameter("options") != null) {
            options = Integer.parseInt(request.getParameter("options"));
        } else {
            options = 0;
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

        if(request.getParameter("percentageSecond")!= null){
            PercentageSecondFloat = Float.parseFloat(request.getParameter("percentageSecond"));
            PercentageSecond = (int) PercentageSecondFloat;
        }else{
            PercentageSecond =10;
        }

        months = request.getParameter("months");
        if (request.getParameter("city") == null) {
            // city = lookUpProgram.start(request);
        } else {
            city = request.getParameter("city");
        }
        currancy = request.getParameter("Currancy");

        if (request.getParameter("range") != null) {
            Amount = request.getParameter("range");
            filterAmount(Amount);
        } else if (request.getParameter("Amount") != null) {
            Amount = request.getParameter("Amount");
            filterAmount(Amount);
        }
        if(!MinimunDropDownAmount.equals(Amount)){
            Amount = MinimunDropDownAmount;
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

    private void SotringMachanizm(String sorting) throws SQLException {
        switch (sorting) {
            case "DescAmount":
                depositeFilter();
                depositeFilterSub();
                break;
            case "AsecAmount":
                AmountAsec();
                AmountAsecSub();
                break;
            case "DescPercent":
                PercentageDesc();
                PercentageDescSub();
                break;
            case "AsecPercent":
                PercentageAsec();
                PercentageAsecSub();
                break;

        }
    }

    /* Forth section sub Starts */
    private void PercentageAsecSub() throws SQLException {
        subDepositStartFilter = carLoanDao.getAllCarLoans();
        subDepositAmountFilter = new ArrayList<>();
        subDepositAmountFilter = carLoanDao.getAllCarLoans();

        FilterCurrancyListSub3(subDepositAmountFilter);
    }

    private void FilterCurrancyListSub3(List<CarLoans> subDepositAmountFilter) throws SQLException {
        subDepositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < subDepositAmountFilter.size(); i++) {
            String CurrancyFilter = subDepositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(subDepositAmountFilter.get(i).getCLId()));
                subDepositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange(subDepositCurrancyFilter);
    }
    private void FilterByRange(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        MaxAmount=null;
        getMaxAmount();

        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getCLMinAmount();
            int maxAmount = depositAmountFilter.get(i).getCLMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount){

                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }else{
                continue;
            }
        }
        FilterOrderApperanceListSub3(depositCurrancyFilter);
    }

    private void FilterOrderApperanceListSub3(List<CarLoans> subDepositCurrancyFilter) throws SQLException {
        depositAllInSubRage = new ArrayList<>();
        for (int i = 0; i < subDepositCurrancyFilter.size(); i++) {
            int orderOfAppearance = subDepositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance > 1) {
                int id = Integer.parseInt(String.valueOf(subDepositCurrancyFilter.get(i).getCLId()));
                depositAllInSubRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInSubRage, new DepositComarator());
        arrow = 0;
        arrow2 = 0;
    }


    /* Forth section sub Starts */

    /* Forth section  Starts */
    private void PercentageAsec() throws SQLException {
        depositStartFilter = carLoanDao.getAllCarLoans();
        depositAmountFilter = new ArrayList<>();
        depositAmountFilter = carLoanDao.getAllCarLoans();
        FilterCurrancyList2(depositAmountFilter);
    }

    private void FilterCurrancyList2(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange2(depositCurrancyFilter);
    }
    private void FilterByRange2(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        MaxAmount=null;
        getMaxAmount();

        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getCLMinAmount();
            int maxAmount = depositAmountFilter.get(i).getCLMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount){

                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }else{
                continue;
            }
        }
        FilterOrderApperanceList2(depositCurrancyFilter);
    }

    private void FilterOrderApperanceList2(List<CarLoans> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int orderOfAppearance = depositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance == 1) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getCLId()));
                depositAllInRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComarator());
        arrow = 0;
        arrow2 = 0;
    }

    /* Forth section  Ends */
    /* Third section sub Starts */
    private void PercentageDescSub() throws SQLException {
        subDepositStartFilter = carLoanDao.getAllCarLoans();
        subDepositAmountFilter = new ArrayList<>();
        subDepositAmountFilter = carLoanDao.getAllCarLoans();

        FilterCurrancyListSub6(subDepositAmountFilter);
    }

    private void FilterCurrancyListSub6(List<CarLoans> subDepositAmountFilter) throws SQLException {
        subDepositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < subDepositAmountFilter.size(); i++) {
            String CurrancyFilter = subDepositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(subDepositAmountFilter.get(i).getCLId()));
                subDepositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange3(subDepositCurrancyFilter);
    }
    private void FilterByRange3(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        MaxAmount=null;
        getMaxAmount();

        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getCLMinAmount();
            int maxAmount = depositAmountFilter.get(i).getCLMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount){

                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }else{
                continue;
            }
        }
        FilterOrderApperanceListSub6(depositCurrancyFilter);
    }
    private void FilterOrderApperanceListSub6(List<CarLoans> subDepositCurrancyFilter) throws SQLException {
        depositAllInSubRage = new ArrayList<>();
        for (int i = 0; i < subDepositCurrancyFilter.size(); i++) {
            int orderOfAppearance = subDepositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance > 1) {
                int id = Integer.parseInt(String.valueOf(subDepositCurrancyFilter.get(i).getCLId()));
                depositAllInSubRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInSubRage, new DepositComarator2());
        arrow = 1;
        arrow2 = 1;
    }

    /* Third section sub Ends */
    /* Third section Starts */
    private void PercentageDesc() throws SQLException {
        depositStartFilter = carLoanDao.getAllCarLoans();
        depositAmountFilter = new ArrayList<>();
        depositAmountFilter = carLoanDao.getAllCarLoans();
        FilterCurrancyList5(depositAmountFilter);

    }

    private void FilterCurrancyList5(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange4(depositCurrancyFilter);
    }
    private void FilterByRange4(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        MaxAmount=null;
        getMaxAmount();

        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getCLMinAmount();
            int maxAmount = depositAmountFilter.get(i).getCLMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount){

                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }else{
                continue;
            }
        }
        FilterOrderApperanceList5(depositCurrancyFilter);
    }
    private void FilterOrderApperanceList5(List<CarLoans> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int orderOfAppearance = depositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance == 1) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getCLId()));
                depositAllInRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComarator2());
        arrow = 1;
        arrow2 = 1;
    }


    /* Third section Ends */
    /* Second Section sub Starts */
    private void AmountAsecSub() throws SQLException {
        subDepositStartFilter = carLoanDao.getAllCarLoans();
        subDepositAmountFilter = new ArrayList<>();
        subDepositAmountFilter = carLoanDao.getAllCarLoans();

        FilterCurrancyListSub4(subDepositAmountFilter);
    }

    private void FilterCurrancyListSub4(List<CarLoans> subDepositAmountFilter) throws SQLException {
        subDepositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < subDepositAmountFilter.size(); i++) {
            String CurrancyFilter = subDepositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(subDepositAmountFilter.get(i).getCLId()));
                subDepositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceListSub4(subDepositCurrancyFilter);
    }
    private void FilterByRange5(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        MaxAmount=null;
        getMaxAmount();

        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getCLMinAmount();
            int maxAmount = depositAmountFilter.get(i).getCLMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount){

                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }else{
                continue;
            }
        }
        FilterOrderApperanceListSub4(depositCurrancyFilter);
    }
    private void FilterOrderApperanceListSub4(List<CarLoans> subDepositCurrancyFilter) throws SQLException {
        depositAllInSubRage = new ArrayList<>();
        for (int i = 0; i < subDepositCurrancyFilter.size(); i++) {
            int orderOfAppearance = subDepositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance > 1) {
                int id = Integer.parseInt(String.valueOf(subDepositCurrancyFilter.get(i).getCLId()));
                depositAllInSubRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInSubRage, new DepositComaratorAmount());
        arrow = 0;
        arrow2 = 0;
    }

    /* Second Section sub Starts  */


    /* second section Starts  */

    private void AmountAsec() throws SQLException {

        depositStartFilter = carLoanDao.getAllCarLoans();
        depositAmountFilter = new ArrayList<>();
        depositAmountFilter = carLoanDao.getAllCarLoans();
        FilterCurrancyList3(depositAmountFilter);

    }

    private void FilterCurrancyList3(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceList3(depositCurrancyFilter);
    }
    private void FilterByRange6(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        MaxAmount=null;
        getMaxAmount();

        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getCLMinAmount();
            int maxAmount = depositAmountFilter.get(i).getCLMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount){

                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }else{
                continue;
            }
        }
        FilterOrderApperanceList3(depositCurrancyFilter);
    }
    private void FilterOrderApperanceList3(List<CarLoans> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int orderOfAppearance = depositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance == 1) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getCLId()));
                depositAllInRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComaratorAmount());
        arrow = 0;
        arrow2 = 0;
    }

    /* second section Ends  */

    /* first Section sub Starts */
    private void depositeFilterSub() throws SQLException {
        subDepositStartFilter = carLoanDao.getAllCarLoans();
        subDepositAmountFilter = new ArrayList<>();
        subDepositAmountFilter = carLoanDao.getAllCarLoans();

        FilterCurrancyListSub(subDepositAmountFilter);
    }

    private void FilterCurrancyListSub(List<CarLoans> subDepositAmountFilter) throws SQLException {
        subDepositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < subDepositAmountFilter.size(); i++) {
            String CurrancyFilter = subDepositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(subDepositAmountFilter.get(i).getCLId()));
                subDepositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceListSub(subDepositCurrancyFilter);
    }
    private void FilterByRange7(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        MaxAmount=null;
        getMaxAmount();

        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getCLMinAmount();
            int maxAmount = depositAmountFilter.get(i).getCLMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount){
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }else{
                continue;
            }
        }
        FilterOrderApperanceListSub(depositCurrancyFilter);
    }
    private void FilterOrderApperanceListSub(List<CarLoans> subDepositCurrancyFilter) throws SQLException {
        depositAllInSubRage = new ArrayList<>();
        for (int i = 0; i < subDepositCurrancyFilter.size(); i++) {
            int orderOfAppearance = subDepositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance > 1) {
                int id = Integer.parseInt(String.valueOf(subDepositCurrancyFilter.get(i).getCLId()));
                depositAllInSubRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComaratorAmount2());
        arrow = 1;
        arrow2 = 1;
    }

    /* FirstSection Start */
    private void depositeFilter() throws SQLException {
        depositStartFilter = carLoanDao.getAllCarLoans();
        depositAmountFilter = new ArrayList<>();
        depositAmountFilter = carLoanDao.getAllCarLoans();
        FilterCurrancyList(depositAmountFilter);

    }
    private List<CarLoans> FilteredList(int id) throws SQLException {
        return carLoanDao.getCarLoansById(id);
    }
    private void FilterCurrancyList(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange8(depositCurrancyFilter);
    }
    private void FilterByRange8(List<CarLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        MaxAmount=null;
        getMaxAmount();

        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getCLMinAmount();
            int maxAmount = depositAmountFilter.get(i).getCLMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount){
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getCLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }else{
                continue;
            }
        }
        FilterOrderApperanceList(depositCurrancyFilter);
    }
    private void FilterOrderApperanceList(List<CarLoans> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int orderOfAppearance = depositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance == 1) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getCLId()));
                depositAllInRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComaratorAmount2());
        arrow = 1;
        arrow2 = 1;
    }

    /* FirstSection Ends  */

    class DepositComaratorAmount implements Comparator<CarLoans> {

        @Override
        public int compare(CarLoans o1, CarLoans o2) {
            return Integer.compare(o1.getCLMinAmount(), o2.getCLMinAmount());
        }
    }

    class DepositComarator implements Comparator<CarLoans> {

        @Override
        public int compare(CarLoans o1, CarLoans o2) {
            return Double.compare(o1.getCLFatual(), o2.getCLFatual());
        }
    }

    class DepositComaratorAmount2 implements Comparator<CarLoans> {

        @Override
        public int compare(CarLoans o2, CarLoans o1) {
            return Integer.compare(o1.getCLMinAmount(), o2.getCLMinAmount());
        }
    }

    class DepositComarator2 implements Comparator<CarLoans> {

        @Override
        public int compare(CarLoans o2, CarLoans o1) {
            return Double.compare(o1.getCLFatual(), o2.getCLFatual());
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
                months = String.valueOf(getDeposit.get(i).getCLMinPeriodMonths());
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
        return new CarComparing(ID, sessionId, Integer.parseInt(amoutFiltered), Integer.parseInt(months), BankId, productName, percentage,Service,RangeTwo);
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
        if(pageName == null){
            pageName = "Ավտովարկ";
        }else {
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
}
