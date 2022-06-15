


package com.ithome.web.ClientSide;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Comparision.DepositComDao;
import com.ithome.web.Comparision.DepositComparing;
import com.ithome.web.Helpers.*;
import com.ithome.web.Localization.CheckLanguageAndCurrency;
import com.ithome.web.counterController.HitCounter;
import com.ithome.web.starter.App;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.time.Month;
import java.util.*;

@WebServlet("/DepositClient")
public class DepositClient extends HttpServlet {
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
    private int Months = 0;
    private int ProductCode = 0;

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

    List<Deposit> subDepositStartFilter = new ArrayList<>();
    List<Deposit> subDepositAmountFilter = new ArrayList<>();
    List<Deposit> subDepositCurrancyFilter = new ArrayList<>();

    String productName = null;
    int BankId = 0;
    int BankIdFromData = 0;
    int DEquippingPossibilitiesid = 0;
    int DEarlierWithdrawalAmountid = 0;
    int DAutoExtendPeriodid = 0;
    int DCapitalizationPercentid = 0;
    double percentage = 0;
    String BankLink = null;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            depositClient(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            depositClient(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void depositClient(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        //getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getParameters(request, response);
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

    private void getApperance() throws SQLException {
        searchUpList = new ArrayList<>();
        depositeAseList = new ArrayList<>();
        MaxAmount=null;
        getMaxAmount();
        if(Amount == null){
            filterAmount(getMinAmount());
        }
        searchUpList = searchDatabase();
        for (int i = 0; i < searchUpList.size(); i++) {
            int firstSearch = searchUpList.get(i).getDMinAmount();
            //int firstSearchMonth = searchUpList.get(i).getTimeLine();
            String firstSearchCurrancy = searchUpList.get(i).getCurrancy();
            if (Integer.parseInt(amoutFiltered) >= firstSearch || Integer.parseInt(amoutFiltered) <= firstSearch && Integer.parseInt(MaxAmount) <= firstSearch && firstSearchCurrancy.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(searchUpList.get(i).getDId()));
                depositeAseList.addAll(FilteredList(id));
            }
            Collections.shuffle(depositeAseList);
        }

    }

    private List<Deposit> FilteredList(int id) throws SQLException {
        return depositDaoController.getDepositById(id);
    }

    /* first Section sub Starts */
    private void depositeFilterSub() throws SQLException {
        subDepositStartFilter = depositDaoController.getAllDepositeList();
        subDepositAmountFilter = new ArrayList<>();
        subDepositAmountFilter = depositDaoController.getAllDepositeList();

        FilterCurrancyListSub(subDepositAmountFilter);
    }

    private void FilterCurrancyListSub(List<Deposit> subDepositAmountFilter) throws SQLException {
        subDepositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < subDepositAmountFilter.size(); i++) {
            String CurrancyFilter = subDepositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(subDepositAmountFilter.get(i).getDId()));
                subDepositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceListSub(subDepositCurrancyFilter);
    }

    private void FilterOrderApperanceListSub(List<Deposit> subDepositCurrancyFilter) throws SQLException {
        depositAllInSubRage = new ArrayList<>();
        for (int i = 0; i < subDepositCurrancyFilter.size(); i++) {
            int orderOfAppearance = subDepositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance > 1) {
                int id = Integer.parseInt(String.valueOf(subDepositCurrancyFilter.get(i).getDId()));
                depositAllInSubRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComaratorAmount2());
        arrow = 1;
        arrow2 = 1;
    }

    /* first Section sub Ends */
    /* Second Section sub Starts */
    private void AmountAsecSub() throws SQLException {
        subDepositStartFilter = depositDaoController.getAllDepositeList();
        subDepositAmountFilter = new ArrayList<>();
        subDepositAmountFilter = depositDaoController.getAllDepositeList();

        FilterCurrancyListSub4(subDepositAmountFilter);
    }

    private void FilterCurrancyListSub4(List<Deposit> subDepositAmountFilter) throws SQLException {
        subDepositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < subDepositAmountFilter.size(); i++) {
            String CurrancyFilter = subDepositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(subDepositAmountFilter.get(i).getDId()));
                subDepositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange6(subDepositCurrancyFilter);
    }
    private void FilterByRange6(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getDMinAmount();
            int maxAmount = depositAmountFilter.get(i).getDMaxAmount();
            if (Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount ) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceListSub4(depositCurrancyFilter);
    }


    private void FilterOrderApperanceListSub4(List<Deposit> subDepositCurrancyFilter) throws SQLException {
        depositAllInSubRage = new ArrayList<>();
        for (int i = 0; i < subDepositCurrancyFilter.size(); i++) {
            int orderOfAppearance = subDepositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance > 1) {
                int id = Integer.parseInt(String.valueOf(subDepositCurrancyFilter.get(i).getDId()));
                depositAllInSubRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInSubRage, new DepositComaratorAmount());
        arrow = 0;
        arrow2 = 0;
    }

    /* Third section sub Starts */
    private void PercentageDescSub() throws SQLException {
        subDepositStartFilter = depositDaoController.getAllDepositeList();
        subDepositAmountFilter = new ArrayList<>();
        subDepositAmountFilter = depositDaoController.getAllDepositeList();

        FilterCurrancyListSub6(subDepositAmountFilter);
    }

    private void FilterCurrancyListSub6(List<Deposit> subDepositAmountFilter) throws SQLException {
        subDepositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < subDepositAmountFilter.size(); i++) {
            String CurrancyFilter = subDepositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(subDepositAmountFilter.get(i).getDId()));
                subDepositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange2(subDepositCurrancyFilter);
    }
    private void FilterByRange2(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getDMinAmount();
            int maxAmount = depositAmountFilter.get(i).getDMaxAmount();
            if (Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount ) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceListSub6(depositCurrancyFilter);
    }

    private void FilterOrderApperanceListSub6(List<Deposit> subDepositCurrancyFilter) throws SQLException {
        depositAllInSubRage = new ArrayList<>();
        for (int i = 0; i < subDepositCurrancyFilter.size(); i++) {
            int orderOfAppearance = subDepositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance > 1) {
                int id = Integer.parseInt(String.valueOf(subDepositCurrancyFilter.get(i).getDId()));
                depositAllInSubRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInSubRage, new DepositComarator2());
        arrow = 1;
        arrow2 = 1;
    }

    /* Third section sub Ends */

    /* Forth section sub Starts */
    private void PercentageAsecSub() throws SQLException {
        subDepositStartFilter = depositDaoController.getAllDepositeList();
        subDepositAmountFilter = new ArrayList<>();
        subDepositAmountFilter = depositDaoController.getAllDepositeList();

        FilterCurrancyListSub3(subDepositAmountFilter);
    }

    private void FilterCurrancyListSub3(List<Deposit> subDepositAmountFilter) throws SQLException {
        subDepositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < subDepositAmountFilter.size(); i++) {
            String CurrancyFilter = subDepositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(subDepositAmountFilter.get(i).getDId()));
                subDepositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange4(subDepositCurrancyFilter);
    }

    private void FilterByRange4(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getDMinAmount();
            int maxAmount = depositAmountFilter.get(i).getDMaxAmount();
            if (Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount ) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceListSub3(depositCurrancyFilter);
    }

    private void FilterOrderApperanceListSub3(List<Deposit> subDepositCurrancyFilter) throws SQLException {
        depositAllInSubRage = new ArrayList<>();
        for (int i = 0; i < subDepositCurrancyFilter.size(); i++) {
            int orderOfAppearance = subDepositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance > 1) {
                int id = Integer.parseInt(String.valueOf(subDepositCurrancyFilter.get(i).getDId()));
                depositAllInSubRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInSubRage, new DepositComarator());
        arrow = 0;
        arrow2 = 0;
    }
    /* Forth section sub Starts */

    /* FirstSection Start */
    private void depositeFilter() throws SQLException {
        depositStartFilter = depositDaoController.getAllDepositeList();
        depositAmountFilter = new ArrayList<>();
        depositAmountFilter = depositDaoController.getAllDepositeList();
        FilterCurrancyList(depositAmountFilter);

    }

    private void FilterCurrancyList(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceList(depositCurrancyFilter);
    }


    private void FilterOrderApperanceList(List<Deposit> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int orderOfAppearance = depositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance == 1) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getDId()));
                depositAllInRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComaratorAmount2());
        arrow = 1;
        arrow2 = 1;
    }

    /* FirstSection Ends */

    /* second section Starts  */

    private void AmountAsec() throws SQLException {

        depositStartFilter = depositDaoController.getAllDepositeList();
        depositAmountFilter = new ArrayList<>();
        depositAmountFilter = depositDaoController.getAllDepositeList();
        FilterCurrancyList3(depositAmountFilter);

    }

    private void FilterCurrancyList3(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange5(depositCurrancyFilter);
    }
    private void FilterByRange5(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        MaxAmount=null;
        getMaxAmount();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getDMinAmount();

            int maxAmount = depositAmountFilter.get(i).getDMaxAmount();
            if (Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount ) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceList3(depositCurrancyFilter);
    }
    private void FilterOrderApperanceList3(List<Deposit> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int orderOfAppearance = depositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance == 1) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getDId()));
                depositAllInRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComaratorAmount());
        arrow = 0;
        arrow2 = 0;
    }

    /* second section Ends  */

    /* Third section Starts */
    private void PercentageDesc() throws SQLException {
        depositStartFilter = depositDaoController.getAllDepositeList();
        depositAmountFilter = new ArrayList<>();
        depositAmountFilter = depositDaoController.getAllDepositeList();
        FilterCurrancyList5(depositAmountFilter);

    }

    private void FilterCurrancyList5(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange(depositCurrancyFilter);
    }

    private void FilterByRange(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        if(Amount == null ){
            filterAmount(getMinAmount());
        }
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getDMinAmount();
            int maxAmount = depositAmountFilter.get(i).getDMaxAmount();
            if (Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount ) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }else{
                continue;
            }
        }
        FilterOrderApperanceList5(depositCurrancyFilter);
    }

    private void FilterOrderApperanceList5(List<Deposit> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int orderOfAppearance = depositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance == 1) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getDId()));
                depositAllInRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComarator2());
        arrow = 1;
        arrow2 = 1;
    }


    /* Third section Ends */

    /* Forth section  Starts */
    private void PercentageAsec() throws SQLException {
        depositStartFilter = depositDaoController.getAllDepositeList();
        depositAmountFilter = new ArrayList<>();
        depositAmountFilter = depositDaoController.getAllDepositeList();
        FilterCurrancyList2(depositAmountFilter);
    }

    private void FilterCurrancyList2(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            String CurrancyFilter = depositAmountFilter.get(i).getCurrancy();
            if (CurrancyFilter.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterByRange3(depositCurrancyFilter);
    }

    private void FilterByRange3(List<Deposit> depositAmountFilter) throws SQLException {
        depositCurrancyFilter = new ArrayList<>();
        System.out.println(pageCurrancy);
        for (int i = 0; i < depositAmountFilter.size(); i++) {
            int minAmount = depositAmountFilter.get(i).getDMinAmount();
            int maxAmount = depositAmountFilter.get(i).getDMaxAmount();
            if (Integer.parseInt(Amount) >= minAmount && Integer.parseInt(Amount) <= maxAmount ) {
                int id = Integer.parseInt(String.valueOf(depositAmountFilter.get(i).getDId()));
                depositCurrancyFilter.addAll(FilteredList(id));
            }
        }
        FilterOrderApperanceList2(depositCurrancyFilter);
    }

    private void FilterOrderApperanceList2(List<Deposit> depositCurrancyFilter) throws SQLException {
        depositAllInRage = new ArrayList<>();
        for (int i = 0; i < depositCurrancyFilter.size(); i++) {
            int orderOfAppearance = depositCurrancyFilter.get(i).getOrderOfAppearance();
            if (orderOfAppearance == 1) {
                int id = Integer.parseInt(String.valueOf(depositCurrancyFilter.get(i).getDId()));
                depositAllInRage.addAll(FilteredList(id));
            }
        }
        Collections.sort(depositAllInRage, new DepositComarator());
        arrow = 0;
        arrow2 = 0;
    }

    /* Forth section  Ends */

    private void filterAmount(String amount) {
        if (amount.contains(",")) {
            amoutFiltered = amount.replace(",", "");
        } else {
            amoutFiltered = amount;
        }
        System.out.println("amoutFiltered " + amoutFiltered);
    }

    private void switchToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (PageToGo) {
            case "Deposits":
                gotoPageDeposit(request, response);
                break;
            case "Calculate":
                gotoPageDeposit(request, response);
                break;
            case "Calculateer":
                gotoPageCalculate(request, response);
                break;
            case "DepositClient":
                gotoPageDeposit(request, response);
                break;
            case "Credits":
                gotoPageCredits(request, response);
                break;
            case "MortgageClient":
                gotoPageMortgage(request, response);
                break;
            case "CreditSendMortgage":
                gotoPageCreditSendMortgage(request, response);
                break;
            case "CalculateMort":
                gotoPageCalculateMortgage(request, response);
                break;
            case "ConsumerClient":
                gotoPageConsumerClient(request, response);
                break;
            case "ConsumerCalculate":
                gotoPageConsumerCalculate(request, response);
                break;
            case "AutoClient":
                gotoPageAutoClient(request, response);
                break;
            case "AutoCalculate":
                gotoPageAutoCalculate(request, response);
                break;
            case "MicroClient":
                gotoPageMicroClient(request, response);
                break;
            case "MicroCalculate":
                gotoPageMicroCalculate(request, response);
                break;
            case "AClient":
                gotoPageAClient(request, response);
                break;
            case "ACaluclate":
                gotoPageACalculate(request, response);
                break;
            case "App":
                gotoPageApp(request, response);
                break;
            case "Cards":
                gotoPageCads(request, response);
                break;
            case "BankingDepo":
                gotoBankingDeposit(request, response);
                break;

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

    private void gotoPageApp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        App app = new App();
        app.doGet(request, response);
    }

    private void gotoPageCads(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CardClient cardClient = new CardClient();
        cardClient.doGet(request, response);
    }

    private void gotoBankingDeposit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("Bankid",BankIdFromData);
        DepositBanks depositBanks = new DepositBanks();
        depositBanks.doGet(request, response);
    }

    private void gotoPageMicroClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MicroClient microClient = new MicroClient();
        microClient.doGet(request, response);
    }

    private void gotoPageMicroCalculate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CalculatMocri calculatMocri = new CalculatMocri();
        calculatMocri.doGet(request, response);
    }


    private void gotoPageAClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AClient aClient = new AClient();
        aClient.doGet(request, response);
    }

    private void gotoPageACalculate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AgCalculate agCalculate = new AgCalculate();
        agCalculate.doGet(request, response);
    }

    private void gotoPageAutoClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AutoClient autoClient = new AutoClient();
        autoClient.doGet(request, response);
    }
    private void gotoPageAutoCalculate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("PageToGo","AutoCalculate");
        AutoCalulate autoCalulate = new AutoCalulate();
        autoCalulate.doGet(request, response);
    }
    private void gotoPageConsumerClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConsumerClient consumerClient = new ConsumerClient();
        consumerClient.doGet(request, response);
    }
    private void gotoPageConsumerCalculate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConsumerCalculate consumerCalculate = new ConsumerCalculate();
        consumerCalculate.doGet(request, response);
    }

    private void gotoPageMortgage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MortgageClient mortgageClient = new MortgageClient();
        mortgageClient.doGet(request, response);
    }

    private void gotoPageCalculateMortgage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CalculateMort calculateMort = new CalculateMort();
        calculateMort.doGet(request, response);
    }

    private List<Deposit> searchDatabase() throws SQLException {
        return depositSpecialOfferHelper.getAppearnace();
    }

    private void getPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName(getPageNameArm(pageName));
    }

    private String getPageNameArm(String pageName) {
        return pageNameHelper.ArmPageName(pageName);
    }

    private void GetDropDownByCurrancy(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void gotoPageDeposit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Deposit3.jsp").forward(request, response);
    }

    private void gotoPageCalculate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/CalcDeposit.jsp").forward(request, response);
    }

    private void gotoPageCreditSendMortgage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("CreditSend").forward(request, response);
    }

    private void gotoPageCredits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Credits").forward(request, response);
    }

    private String getMaxAmount() throws SQLException {
        getPageRange();
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
        for (int i = 0; i < dropDownsListWithCurrancy.size(); i++) {
            MaxAmount = dropDownsListWithCurrancy.get(i).getMaxAmount();
        }
        return MaxAmount;
    }

    private void getParameters(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (request.getParameter("range") != null) {
            Amount = request.getParameter("range");
            filterAmount(Amount);
        } else if (request.getParameter("Amount") != null) {
            Amount = request.getParameter("Amount");
            filterAmount(Amount);
        } else if (request.getParameter("value") != null) {
            Amount = request.getParameter("value");
            filterAmount(Amount);
        } else {
            Amount = getMinAmount();
            filterAmount(Amount);
        }

        if(request.getParameter("bankId")!=null){
            BankIdFromData = Integer.parseInt(request.getParameter("bankId"));
        }else{
            BankIdFromData = 0;
        }

        MaxAmount = getMaxAmount();

        // PageToGo = "DepositClient";
        if (request.getParameter("PageToGo") != null) {
            PageToGo = request.getParameter("PageToGo");
        } else if (request.getAttribute("PageToGo") != null) {
            PageToGo = String.valueOf(request.getAttribute("PageToGo"));
        }

        if (request.getParameter("id") != null) {
            ID = Integer.parseInt(request.getParameter("id"));

            try {
                startCompare(ID, request);
            } catch (IOException e) {
                e.printStackTrace();
            }


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

       /* if (request.getParameter("ProductCode") != null) {
            ProductCode = Integer.parseInt(request.getParameter("ProductCode"));
        } else if (request.getParameter("id") != null) {
            ProductCode = Integer.parseInt(request.getParameter("id"));
        }else{
            ProductCode=-1;
        }

        if(Months == null || Months.equals("0")){
            if(ProductCode != -1) {
                List<Deposit> depositList = new ArrayList<>();
                DepositDaoController depositDaoController = new DepositDaoController();
                depositList = depositDaoController.getDepositByCardCode(ProductCode);
                int finalMonth = depositList.get(0).getTimeLine() / 30;
                Months = (String.valueOf(finalMonth));
            }else{
                Months="0";
            }
        }*/

        if (request.getParameter("sorting") != null) {
            Sorting = request.getParameter("sorting");
            try {
                SotringMachanizm(Sorting);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        } else {
            Sorting = "DescPercent";
            try {
                SotringMachanizm(Sorting);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
    }

    private String getMinAmount() throws SQLException {
        getPageRange();
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
        for (int i = 0; i < dropDownsListWithCurrancy.size(); i++) {
            Amount = dropDownsListWithCurrancy.get(i).getMinAmount();
        }
        return Amount;
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

    private void startCompare(int id, HttpServletRequest request) throws SQLException, IOException {
        List<Deposit> getDeposit = new ArrayList<>();
        boolean isSizeInRange = CompareHelper.DepositCheckOutOfBound();
        boolean isIdAlreadyHad = CompareHelper.CheckIfIdExsist(id);
        if (!isSizeInRange && !isIdAlreadyHad) {
            CompareHelper.AddDepositToArrayById(id);
            //getting all info from the deposit databse
            getDeposit = depositDaoController.getDepositByCardCode(id);
            for (int i = 0; i < getDeposit.size(); i++) {
                productName = getDeposit.get(i).getProductName();
                BankId = getDeposit.get(i).getBankId();
                DEquippingPossibilitiesid = getDeposit.get(i).getDEquippingPossibilitiesid();
                DEarlierWithdrawalAmountid = getDeposit.get(i).getDEarlierWithdrawalAmountid();
                DAutoExtendPeriodid = getDeposit.get(i).getDAutoExtendPeriodid();
                DCapitalizationPercentid = getDeposit.get(i).getDCapitalizationPercentid();
                percentage = getDeposit.get(i).getDPercentage();
                Months = getDeposit.get(i).getTimeLine();
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
        return new DepositComparing(ID, sessionId, Integer.parseInt(amoutFiltered), Months, BankId, productName, DEquippingPossibilitiesid, DEarlierWithdrawalAmountid, DAutoExtendPeriodid, DCapitalizationPercentid, percentage);
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

    private void countHit() throws SQLException {
        hitCounter.countingHits(pageName, pageCurrancy, city, language, sessionId);
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

    class DepositComarator implements Comparator<Deposit> {

        @Override
        public int compare(Deposit o1, Deposit o2) {
            return Double.compare(o2.getDPercentage(), o1.getDPercentage());
        }
    }

    class DepositComarator2 implements Comparator<Deposit> {

        @Override
        public int compare(Deposit o1, Deposit o2) {
            return Double.compare(o1.getDPercentage(), o2.getDPercentage());
        }
    }

    class DepositComaratorAmount implements Comparator<Deposit> {

        @Override
        public int compare(Deposit o1, Deposit o2) {
            return Integer.compare(o2.getDMinAmount(), o1.getDMinAmount());
        }
    }

    class DepositComaratorAmount2 implements Comparator<Deposit> {

        @Override
        public int compare(Deposit o1, Deposit o2) {
            return Integer.compare(o1.getDMinAmount(), o2.getDMinAmount());
        }
    }
}
