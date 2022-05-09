package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.ConsumerCreditDaoController;
import com.ithome.web.AdminDao.MicroLoanDaoController;
import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Comparision.MicroComDao;
import com.ithome.web.Comparision.MicroComparing;
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

@WebServlet("/CalculatMocri")
public class CalculatMocri extends HttpServlet {
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
    private List<MicroLoans> depositeAseList = new ArrayList<>();

    private MortgageSpecialOfferHelper mortgageSpecialOfferHelper = new MortgageSpecialOfferHelper();
    private ConsumerSpecialOfferHelper consumerSpecialOfferHelper = new ConsumerSpecialOfferHelper();
    private CarLoanSpecialOfferHelper carLoanSpecialOfferHelper = new CarLoanSpecialOfferHelper();
    private DepositSpecialOfferHelper depositSpecialOfferHelper = new DepositSpecialOfferHelper();
    private AgSpecialOfferHelper agSpecialOfferHelper = new AgSpecialOfferHelper();
    private CardsSpecialOfferHelper cardsSpecialOfferHelper = new CardsSpecialOfferHelper();
    private DropDownCurrancyHelper dropDownCurrancyHelper = new DropDownCurrancyHelper();
    private MicroLoanSpecialOfferHelper microLoanSpecialOfferHelper = new MicroLoanSpecialOfferHelper();
    private DropDownHelper dropDownHelper = new DropDownHelper();
    private MicroLoanDaoController microLoanDaoController = new MicroLoanDaoController();

    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private HitCounter hitCounter = new HitCounter();
    private LookUpProgram lookUpProgram = new LookUpProgram();
    private LanguageHelper languageHelper = new LanguageHelper();
    private PageNameHelper pageNameHelper = new PageNameHelper();
    private List<MicroLoans> depositAllInRage = new ArrayList<>();
    private List<MicroLoans> depositAllInSubRage = new ArrayList<>();

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


    List<MicroLoans> depositStartFilter = new ArrayList<>();
    List<MicroLoans> depositAmountFilter = new ArrayList<>();
    List<MicroLoans> depositCurrancyFilter = new ArrayList<>();
    List<MicroLoans> searchUpList = new ArrayList<>();

    List<MicroLoans> subDepositStartFilter = new ArrayList<>();
    List<MicroLoans> subDepositAmountFilter = new ArrayList<>();
    List<MicroLoans> subDepositCurrancyFilter = new ArrayList<>();

    String productName = null;
    int BankId = 0;
    int BankIdFromData = 0;
    int Service = 0;
    double percentage = 0;
    String BankLink = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            clculatMocri(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            clculatMocri(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clculatMocri(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
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
        checkForCompareList();
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

    private void SotringMachanizm(String sorting) throws SQLException {
        switch (sorting) {
            case "DescAmount":
                depositeFilter();
                // depositeFilterSub();
                break;
            case "AsecAmount":
                AmountAsec();
                //AmountAsecSub();
                break;
            case "DescPercent":
                depositeFilter();
                //PercentageDescSub();
                break;
            case "AsecPercent":
                AmountAsec();
                //PercentageAsecSub();
                break;
        }
    }

    private List<MicroLoans> FilteredList(int id) throws SQLException {
        return microLoanDaoController.getMicroLoanssById(id);
    }

    class DepositComaratorAmount2 implements Comparator<MicroLoans> {

        @Override
        public int compare(MicroLoans o1, MicroLoans o2) {
            return Integer.compare(o1.getMLMinAmount(), o2.getMLMinAmount());
        }
    }


    class DepositComarator2 implements Comparator<MicroLoans> {

        @Override
        public int compare(MicroLoans o1, MicroLoans o2) {
            return Double.compare(o1.getMLFatual(), o2.getMLFatual());
        }
    }
    /* Forth section sub Starts */
    private void PercentageAsecSub() throws SQLException {
        subDepositStartFilter = microLoanDaoController.getAllMicroLoans();
        subDepositAmountFilter = new ArrayList<>();
        subDepositAmountFilter = microLoanDaoController.getAllMicroLoans();

        FilterCurrancyListSub3(subDepositAmountFilter);
    }

    private void FilterCurrancyListSub3(List<MicroLoans> subDepositAmountFilter) throws SQLException {
        subDepositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < subDepositAmountFilter.size(); i++) {
            String CurrancyFilter = subDepositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(subDepositAmountFilter.get(i).getMLId()));
                subDepositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceListSub3(subDepositCurrancyFilter);
    }

    private void FilterOrderApperanceListSub3(List<MicroLoans> subDepositCurrancyFilter) throws SQLException {
        depositAllInSubRage = new ArrayList<>();
        for (int i = 0; i < subDepositCurrancyFilter.size(); i++) {
            int orderOfAppearance = subDepositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance > 1) {
                int id = Integer.parseInt(String.valueOf(subDepositCurrancyFilter.get(i).getMLId()));
                depositAllInSubRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInSubRage, new DepositComarator());
        arrow = 0;
        arrow2 = 0;
    }

    /* Forth section  Ends */
    /* Third section sub Starts */
    private void PercentageDescSub() throws SQLException {
        subDepositStartFilter = microLoanDaoController.getAllMicroLoans();
        subDepositAmountFilter = new ArrayList<>();
        subDepositAmountFilter = microLoanDaoController.getAllMicroLoans();

        FilterCurrancyListSub6(subDepositAmountFilter);
    }

    private void FilterCurrancyListSub6(List<MicroLoans> subDepositAmountFilter) throws SQLException {
        subDepositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < subDepositAmountFilter.size(); i++) {
            String CurrancyFilter = subDepositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(subDepositAmountFilter.get(i).getMLId()));
                subDepositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceListSub6(subDepositCurrancyFilter);
    }

    private void FilterOrderApperanceListSub6(List<MicroLoans> subDepositCurrancyFilter) throws SQLException {
        depositAllInSubRage = new ArrayList<>();
        for (int i = 0; i < subDepositCurrancyFilter.size(); i++) {
            int orderOfAppearance = subDepositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance > 1) {
                int id = Integer.parseInt(String.valueOf(subDepositCurrancyFilter.get(i).getMLId()));
                depositAllInSubRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInSubRage, new DepositComarator2());
        arrow = 1;
        arrow2 = 1;
    }

    /* Third section Ends */

    /* Second Section sub Starts */
    private void AmountAsecSub() throws SQLException {
        subDepositStartFilter = microLoanDaoController.getAllMicroLoans();
        subDepositAmountFilter = new ArrayList<>();
        subDepositAmountFilter = microLoanDaoController.getAllMicroLoans();

        FilterCurrancyListSub4(subDepositAmountFilter);
    }

    private void FilterCurrancyListSub4(List<MicroLoans> subDepositAmountFilter) throws SQLException {
        subDepositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < subDepositAmountFilter.size(); i++) {
            String CurrancyFilter = subDepositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(subDepositAmountFilter.get(i).getMLId()));
                subDepositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceListSub4(subDepositCurrancyFilter);
    }

    private void FilterOrderApperanceListSub4(List<MicroLoans> subDepositCurrancyFilter) throws SQLException {
        depositAllInSubRage = new ArrayList<>();
        for (int i = 0; i < subDepositCurrancyFilter.size(); i++) {
            int orderOfAppearance = subDepositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance > 1) {
                int id = Integer.parseInt(String.valueOf(subDepositCurrancyFilter.get(i).getMLId()));
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

        depositStartFilter = microLoanDaoController.getAllMicroLoans();
        depositAmountFilter = new ArrayList<>();
        depositAmountFilter = microLoanDaoController.getAllMicroLoans();
        FilterCurrancyList3(depositAmountFilter);

    }

    private void FilterCurrancyList3(List<MicroLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getMLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange2(depositCurrancyFilter);
    }
    private void FilterByRange2(List<MicroLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        MaxAmount=null;
        getMaxAmount();

        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getMLMinAmount();
            int maxAmount = depositAmountFilter.get(i).getMLMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount){
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getMLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }else{
                continue;
            }
        }
        FilterByMonths2(depositCurrancyFilter);
    }

    private void FilterByMonths2(List<MicroLoans> depositMonthFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        if(Integer.parseInt(months) <= 1){
            FilterOrderApperanceList3(depositMonthFilter);
        }else {
            for (int i = 0; i < depositAmountFilter.size(); i++) {
                int minMonthsFromData = depositAmountFilter.get(i).getMMinPeriodDays();
                int manMonths = depositAmountFilter.get(i).getMMaxPeriodDays();
                if(Integer.parseInt(months) >= minMonthsFromData ) {
                    int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getMLId()));
                    depositCurrancyFilter.addAll(FilteredList(id));
                }
            }
        }
        FilterOrderApperanceList3(depositCurrancyFilter);
    }
    private void FilterOrderApperanceList3(List<MicroLoans> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int orderOfAppearance = depositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance == 1) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getMLId()));
                depositAllInRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComaratorAmount());
        arrow = 0;
        arrow2 = 0;
    }

    /* second section Ends  */

    /* FirstSection Start */
    private void depositeFilter() throws SQLException {
        depositStartFilter = microLoanDaoController.getAllMicroLoans();
        depositAmountFilter = new ArrayList<>();
        depositAmountFilter = microLoanDaoController.getAllMicroLoans();
        FilterCurrancyList(depositAmountFilter);

    }

    private void FilterCurrancyList(List<MicroLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getMLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByMonths(depositCurrancyFilter);
    }

    private void FilterByMonths(List<MicroLoans> depositMonthFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        if(Integer.parseInt(months) <= 1){
            FilterByRange(depositMonthFilter);
        }else {
            for (int i = 0; i < depositAmountFilter.size(); i++) {
                int minMonthsFromData = depositAmountFilter.get(i).getMMinPeriodDays();
                int manMonths = depositAmountFilter.get(i).getMMaxPeriodDays();
                if(Integer.parseInt(months) >= minMonthsFromData) {
                    int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getMLId()));
                    depositCurrancyFilter.addAll(FilteredList(id));
                }
            }
        }
        FilterByRange(depositCurrancyFilter);
    }

    private void FilterByRange(List<MicroLoans> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        MaxAmount=null;
        getMaxAmount();

        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getMLMinAmount();
            int maxAmount = depositAmountFilter.get(i).getMLMaxAmount();
            if(Integer.parseInt(Amount) >= minAmount ){
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getMLId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }else{
                continue;
            }
        }
        FilterOrderApperanceList(depositCurrancyFilter);
    }

    private void FilterOrderApperanceList(List<MicroLoans> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int orderOfAppearance = depositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance == 1) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getMLId()));
                depositAllInRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComaratorAmount2());
        arrow = 1;
        arrow2 = 1;
    }
    /* FirstSection Ends */
    /* first Section sub Starts */
    private void depositeFilterSub() throws SQLException {
        subDepositStartFilter = microLoanDaoController.getAllMicroLoans();
        subDepositAmountFilter = new ArrayList<>();
        subDepositAmountFilter = microLoanDaoController.getAllMicroLoans();

        FilterCurrancyListSub(subDepositAmountFilter);
    }

    private void FilterCurrancyListSub(List<MicroLoans> subDepositAmountFilter) throws SQLException {
        subDepositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < subDepositAmountFilter.size(); i++) {
            String CurrancyFilter = subDepositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(subDepositAmountFilter.get(i).getMLId()));
                subDepositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceListSub(subDepositCurrancyFilter);
    }

    private void FilterOrderApperanceListSub(List<MicroLoans> subDepositCurrancyFilter) throws SQLException {
        depositAllInSubRage = new ArrayList<>();
        for (int i = 0; i < subDepositCurrancyFilter.size(); i++) {
            int orderOfAppearance = subDepositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance > 1) {
                int id = Integer.parseInt(String.valueOf(subDepositCurrancyFilter.get(i).getMLId()));
                depositAllInSubRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComaratorAmount2());
        arrow = 1;
        arrow2 = 1;
    }
    /* first Section sub Ends */

    private void GoPageToGo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(PageToGo.equals("MicroBank")){
            request.setAttribute("Bankid",BankIdFromData);
            MicroBanks microBanks = new MicroBanks();
            microBanks.doGet(request,response);
        }else {
            request.getRequestDispatcher("/CalculateMicro.jsp").forward(request, response);
        }
    }

    class DepositComarator implements Comparator<MicroLoans> {
        @Override
        public int compare(MicroLoans o1, MicroLoans o2) {
            return Double.compare(o1.getMLFatual(), o2.getMLFatual());
        }
    }

    class DepositComaratorAmount implements Comparator<MicroLoans> {
        @Override
        public int compare(MicroLoans o1, MicroLoans o2) {
            return Integer.compare(o2.getMMinPeriodDays(), o1.getMMinPeriodDays());
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


    private void filterAmount(String amount) {
        if(amount.contains(",")) {
            amoutFiltered = amount.replace(",", "");
        }else{
            amoutFiltered = amount;
        }
        System.out.println("amoutFiltered " + amoutFiltered);
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
            int firstSearch = searchUpList.get(i).getMLMinAmount();
            String firstSearchCurrancy = searchUpList.get(i).getCurrancy();
            int minMonthsFromData = searchUpList.get(i).getMMinPeriodDays();
            int maxMonthsFromData = searchUpList.get(i).getMMaxPeriodDays();
            if (Integer.parseInt(amoutFiltered) >= firstSearch || Integer.parseInt(amoutFiltered) <= firstSearch && Integer.parseInt(months) >= minMonthsFromData  && Integer.parseInt(months) <= maxMonthsFromData) {
                int id = Integer.parseInt(String.valueOf(searchUpList.get(i).getMLId()));
                depositeAseList.addAll(FilteredList(id));
            }
        }

    }


    private List<MicroLoans> searchDatabase() throws SQLException {
        return microLoanSpecialOfferHelper.getAppearnace();
    }

    private void getPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName("Սպարողական");
    }

    private String getPageNameArm(String pageName) {
        return pageNameHelper.ArmPageName(pageName);
    }

    private void getMaxAmount() throws SQLException {
        getPageRange();
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
        for (int i = 0; i <dropDownsListWithCurrancy.size() ; i++) {
            MaxAmount = dropDownsListWithCurrancy.get(i).getMaxAmount();
        }
    }

    private void
    getParameters(HttpServletRequest request) throws SQLException {
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

        if(request.getParameter("months") !=null){
            months = request.getParameter("months");
        }else{
            months ="";
        }
        //convertToDays(months);
        city = request.getParameter("City");

        if(request.getParameter("PageToGo")!= null) {
            PageToGo = request.getParameter("PageToGo");
        }else{
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
        if(request.getParameter("pageNameToDelete")!= null){
            PageNameToDelete = request.getParameter("pageNameToDelete");
            deleteList(PageNameToDelete);
        }else{
            PageNameToDelete ="";
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



    private void startCompare(int id,HttpServletRequest request) throws SQLException {
        List<MicroLoans> getDeposit = new ArrayList<>();
        boolean isSizeInRange = CompareHelper.MicroCheckOutOfBound();
        boolean isIdAlreadyHad = CompareHelper.CheckIfIdExsistMicro(id);
        if (!isSizeInRange && !isIdAlreadyHad) {
            CompareHelper.AddMicroToArrayById(id);
            getDeposit = microLoanDaoController.getMicroLoansByProductCode(id);
            for (int i = 0; i < getDeposit.size(); i++) {
                productName = getDeposit.get(i).getProductName();
                BankId = getDeposit.get(i).getBankId();
                percentage = getDeposit.get(i).getMLFatual();
                Service = getDeposit.get(i).getMMaxServiceFee();
            }
            MicroComDao microComDao = new MicroComDao();
            int done = microComDao.Add(CreateObjects());
            if (done == 0) {
                System.out.println("Something wrong");
            } else {
                System.out.println("success");
            }
            comparListMicro = CompareHelper.getMicroList(sessionId);
            WorningMessage = "";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("WorningMessage",WorningMessage);
            String s = jsonObject.toString();
            request.getSession().setAttribute("jsonArray",s);
            DepositCompareSize = CompareHelper.GetSizeMicro();
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
    private void checkForCompareList() {
        comparListDeposit = CompareHelper.getDepositList(sessionId);
        comparListMortgage = CompareHelper.getMortgageList(sessionId);
        comparListConsumer =  CompareHelper.getConsumerList(sessionId);
        comparListCarLoan =  CompareHelper.getCarLoanList(sessionId);
        comparListMicro =  CompareHelper.getMicroList(sessionId);
        comparListAg = CompareHelper.getAgList(sessionId);
        comparListCard =  CompareHelper.getCardList(sessionId);
    }

    private MicroComparing CreateObjects() {
        return new MicroComparing(ID, sessionId, Integer.parseInt(amoutFiltered), Integer.parseInt(months), BankId, productName, percentage, Service);
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
