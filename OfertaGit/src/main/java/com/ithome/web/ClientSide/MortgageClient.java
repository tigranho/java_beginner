package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.AdminDao.MortgageDaoController;
import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.AdminDao.UsefullArticlesDaoController;
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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/MortgageClient")
public class MortgageClient extends HttpServlet {
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

    private static List<Integer> comparListDeposit = new ArrayList<>();
    private static List<Integer> comparListMortgage = new ArrayList<>();
    private static List<Integer> comparListConsumer = new ArrayList<>();
    private static List<Integer> comparListCarLoan = new ArrayList<>();
    private static List<Integer> comparListMicro = new ArrayList<>();
    private static List<Integer> comparListAg = new ArrayList<>();
    private static List<Integer> comparListCard = new ArrayList<>();
    private String WorningMessage = null;
    private int MortgageCompareSize = 0;
    private String PageNameToDelete = null;
    private String valueTwo = null;
    private String rangeTwo = null;
    private String months = null;
    private String City = null;
    private String currancy = null;
    private String maxAmounr = null;

    List<Mortgage> depositStartFilter = new ArrayList<>();
    List<Mortgage> depositAmountFilter = new ArrayList<>();
    List<Mortgage> depositCurrancyFilter = new ArrayList<>();
    List<Mortgage> searchUpList = new ArrayList<>();

    List<Mortgage> subDepositStartFilter = new ArrayList<>();
    List<Mortgage> subDepositAmountFilter = new ArrayList<>();
    List<Mortgage> subDepositCurrancyFilter = new ArrayList<>();

    private int PercentageSecond =0;
    private float PercentageSecondFloat = 0;

    private int options = 0;
    private int RangeTwo = 0;

    String productName = null;
    int BankId = 0;
    int Service = 0;
    double percentage = 0;
    String BankLink = null;

    int getAgList=0;
    int getDpList = 0;
    int getMoList = 0;
    int getCoList = 0;
    int getCaList = 0;
    int getMiList = 0;
    String MinimunDropDownAmount = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            mortgageClient(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            mortgageClient(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mortgageClient(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        //getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getMaxAmount();
        getParameters(request);
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
        checkForCompareList();
        createRequestes(request);
        gotoPageMortgage(request, response);
    }

    private void checkForCompareList() {
        comparListDeposit = CompareHelper.getDepositList(sessionId);
        comparListMortgage = CompareHelper.getMortgageList(sessionId);
        comparListConsumer =  CompareHelper.getConsumerList(sessionId);
        comparListCarLoan =  CompareHelper.getCarLoanList(sessionId);
        comparListMicro =  CompareHelper.getMicroList(sessionId);
        comparListAg = CompareHelper.getAgList(sessionId);
      /*  getAgList = CompareHelper.getAgListSize();
        getDpList = CompareHelper.getDepositListSize();
        getMoList = CompareHelper.getMortgageListSize();
        getCoList = CompareHelper.getConsumerListSize();
        getCaList = CompareHelper.getCarLoanListSize();
        getMiList = CompareHelper.getMicroListSize();*/

        comparListCard =  CompareHelper.getCardList(sessionId);
    }
    private void filterAmount(String amount) {
        if (amount.contains(",")) {
            amoutFiltered = amount.replace(",", "");
        } else {
            amoutFiltered = amount;
        }
        System.out.println("amoutFiltered " + amoutFiltered);
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
            //  city = lookUpProgram.start(request);
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
        depositAllInRage = new ArrayList<>();
        depositAllInSubRage = new ArrayList<>();
        depositAllInRage = mortgageDaoController.getFilterdOrderMoreOne(Integer.parseInt(Amount),pageCurrancy);
        depositAllInSubRage= mortgageDaoController.getFilterdOrderSubMoreOne(Integer.parseInt(Amount),pageCurrancy);
        Collections.sort(depositAllInRage, new DepositComarator2());
        Collections.sort(depositAllInSubRage, new DepositComarator2());
        arrow = 0;
        arrow2 = 0;
    }

    private void PercentageAsec() throws SQLException {
        depositeAseList = new ArrayList<>();
        depositeAseList = mortgageDaoController.getFilterdOrderOne(Integer.parseInt(Amount),pageCurrancy);
        Collections.sort(depositeAseList, new DepositComarator2());
        arrow = 0;
        arrow2 = 0;
    }

    private void PercentageDescSub() throws SQLException {
        depositAllInRage = new ArrayList<>();
        depositAllInSubRage = new ArrayList<>();
        depositAllInRage = mortgageDaoController.getFilterdOrderMoreOne(Integer.parseInt(Amount),pageCurrancy);
        depositAllInSubRage= mortgageDaoController.getFilterdOrderSubMoreOne(Integer.parseInt(Amount),pageCurrancy);
        Collections.sort(depositAllInRage, new DepositComarator2());
        Collections.sort(depositAllInSubRage, new DepositComarator2());
        arrow = 1;
        arrow2 = 1;
    }

    private void PercentageDesc() throws SQLException {
        depositeAseList = new ArrayList<>();
        depositeAseList = mortgageDaoController.getFilterdOrderOne(Integer.parseInt(Amount),pageCurrancy);
        Collections.sort(depositeAseList, new DepositComarator2());
        arrow = 1;
        arrow2 = 1;
    }

    private void AmountAsecSub() throws SQLException {

        depositAllInRage = new ArrayList<>();
        depositAllInSubRage = new ArrayList<>();
        depositAllInRage = mortgageDaoController.getFilterdOrderMoreOne(Integer.parseInt(Amount), pageCurrancy);
        depositAllInSubRage = mortgageDaoController.getFilterdOrderSubMoreOne(Integer.parseInt(Amount), pageCurrancy);
        Collections.sort(depositAllInRage, new DepositComaratorAmount());
        Collections.sort(depositAllInSubRage, new DepositComaratorAmount());
        arrow = 0;
        arrow2 = 0;
    }

    private void AmountAsec() throws SQLException {
        depositeAseList = new ArrayList<>();
        depositeAseList = mortgageDaoController.getFilterdOrderOne(Integer.parseInt(Amount),pageCurrancy);
        Collections.sort(depositeAseList, new DepositComaratorAmount());
        arrow = 0;
        arrow2 = 0;
    }

    private void depositeFilterSub() throws SQLException {
        depositAllInRage = new ArrayList<>();
        depositAllInSubRage = new ArrayList<>();
        depositAllInRage = mortgageDaoController.getFilterdOrderMoreOne(Integer.parseInt(Amount), pageCurrancy);
        depositAllInSubRage = mortgageDaoController.getFilterdOrderSubMoreOne(Integer.parseInt(Amount), pageCurrancy);
        Collections.sort(depositAllInRage, new DepositComarator());
        Collections.sort(depositAllInSubRage, new DepositComarator());
        arrow = 1;
        arrow2 = 1;
    }

    private List<Mortgage> FilteredList(int id) throws SQLException {
        return mortgageDaoController.getMortagesById(id);
    }


    private void depositeFilter() throws SQLException {
        depositeAseList = new ArrayList<>();
        depositeAseList = mortgageDaoController.getFilterdOrderOne(Integer.parseInt(Amount),pageCurrancy);
        Collections.sort(depositeAseList, new DepositComarator());
        arrow = 1;
        arrow2 = 1;
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
        List<Mortgage> getDeposit = new ArrayList<>();
        boolean isSizeInRange = CompareHelper.DepositCheckOutOfBoundMortgage();
        boolean isIdAlreadyHad = CompareHelper.CheckIfIdExsistMortgage(id);
        if (!isSizeInRange && !isIdAlreadyHad) {
            CompareHelper.AddMortgageToArrayById(id);
            getDeposit = mortgageDaoController.getMortgageByProductCode(id);
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
            jsonObject.put("WorningMessage",WorningMessage);
            String s = jsonObject.toString();
            request.getSession().setAttribute("jsonArray",s);
            MortgageCompareSize = CompareHelper.GetSizeMortgage();
        } else {
            if (MortgageCompareSize > 3) {
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
    private MoComparing CreateObjects() {
        return new MoComparing(ID, sessionId, Integer.parseInt(amoutFiltered), Integer.parseInt(months), BankId, productName, percentage,Service,RangeTwo);
    }

    private void getApperance() throws SQLException {
        searchUpList = new ArrayList<>();
        depositeAseList = new ArrayList<>();

        searchUpList = searchDatabase();
        MaxAmount=null;
        getMaxAmount();
        for (int i = 0; i <searchUpList.size() ; i++) {
            int AmountMinimum = searchUpList.get(i).getMMinAmount();
            String firstSearchCurrancy = searchUpList.get(i).getCurrancy();
            if (Integer.parseInt(amoutFiltered) >= AmountMinimum || Integer.parseInt(amoutFiltered) <= AmountMinimum && Integer.parseInt(MaxAmount) <= AmountMinimum &&
                    firstSearchCurrancy.equals(pageCurrancy)) {
                int id = Integer.parseInt(String.valueOf(searchUpList.get(i).getMId()));
                depositeAseList.addAll(FilteredList(id));
            }
            Collections.shuffle(depositeAseList);
        }

    }

    private List<Mortgage> searchDatabase() throws SQLException {
        return mortgageSpecialOfferHelper.getAppearnace();
    }


    private void getPageRange() throws SQLException {
        dropDownsList  = new ArrayList<>();
        dropDownsList = dropDownHelper.getPageName(getPageNameArm(pageName));
    }

    private String getPageNameArm(String pageName) {
        return pageNameHelper.ArmPageName(pageName);
    }

    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        sessionId = session.getId();
        request.setAttribute("session",sessionId);
        session.setMaxInactiveInterval(86400);
        CompareHelper.sendSession(sessionId);
    }


    private void countHit() throws SQLException {
        hitCounter.countingHits(pageName, pageCurrancy, city, language, sessionId);
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
        dropDownsListWithCurrancy =new ArrayList<>();
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getCityFromUser(HttpServletRequest request) throws IOException {
        if (request.getParameter("city") == null) {
            city = lookUpProgram.start(request);
        } else {
            city = request.getParameter("city");
        }
    }

    private void getPageName(HttpServletRequest request) {
        if (pageName == null) {
            pageName = "ՀԻՓՈԹԵՔ";
        } else {
            pageName = pageNameHelper.pageName(request);
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
        request.setAttribute("maxAmounr", MaxAmount);
        request.setAttribute("arrow", arrow);
        request.setAttribute("arrow2", arrow2);
        request.setAttribute("WorningMessage", WorningMessage);
        request.setAttribute("DepositCompareSize", MortgageCompareSize);
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

    private void gotoPageMortgage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("Mortgage.jsp").forward(request, response);
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

    class DepositComaratorAmount2 implements Comparator<Mortgage> {

        @Override
        public int compare(Mortgage o1, Mortgage o2) {
            return Integer.compare(o1.getMMinAmount(), o2.getMMinAmount());
        }
    }

    class DepositComarator2 implements Comparator<Mortgage> {

        @Override
        public int compare(Mortgage o1, Mortgage o2) {
            return Double.compare(o1.getMFatual(), o2.getMFatual());
        }
    }

    class DepositComarator implements Comparator<Mortgage> {
        @Override
        public int compare(Mortgage o2, Mortgage o1) {
            return Double.compare(o2.getMFatual(), o1.getMFatual());
        }
    }

    class DepositComaratorAmount implements Comparator<Mortgage> {
        @Override
        public int compare(Mortgage o2, Mortgage o1) {
            return Integer.compare(o1.getMMinAmount(), o2.getMMinAmount());
        }
    }
}