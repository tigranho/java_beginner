package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.AdminDao.MortgageDaoController;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Helpers.*;
import com.ithome.web.Localization.CheckLanguageAndCurrency;
import com.ithome.web.counterController.HitCounter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/MortgageBanks")
public class MortgageBanks extends HttpServlet {
    private CheckLanguageAndCurrency checkLanguageAndCurrency = new CheckLanguageAndCurrency();
    private String language = null;
    private String country = null;
    private String Pagelanguage = null;
    private String pageName = null;
    private String pageLanguageName = null;
    private static String remoteAddr = null;
    private NumberFormat numberFormat;
    private SessionChecker checker = new SessionChecker();
    private String sessionId = null;
    private LanguageHelper languageHelper = new LanguageHelper();
    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private String city = null;
    private LookUpProgram lookUpProgram = new LookUpProgram();
    private PageNameHelper pageNameHelper = new PageNameHelper();
    private HitCounter hitCounter = new HitCounter();
    private List<Banks> banksList = new ArrayList<>();
    private List<Mortgage> depositList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();

    private MortgageSpecialOfferHelper mortgageSpecialOfferHelper = new MortgageSpecialOfferHelper();
    private ConsumerSpecialOfferHelper consumerSpecialOfferHelper = new ConsumerSpecialOfferHelper();
    private CarLoanSpecialOfferHelper carLoanSpecialOfferHelper = new CarLoanSpecialOfferHelper();
    private DepositSpecialOfferHelper depositSpecialOfferHelper = new DepositSpecialOfferHelper();
    private AgSpecialOfferHelper agSpecialOfferHelper = new AgSpecialOfferHelper();
    private CardsSpecialOfferHelper cardsSpecialOfferHelper = new CardsSpecialOfferHelper();
    private DropDownHelper dropDownHelper = new DropDownHelper();

    private List<Deposit> depositListOffer = new ArrayList();
    private List<ProductName> productNameList = new ArrayList<>();
    private List<Mortgage> mortgageListOffer = new ArrayList();
    private List<ConsumerCredit> consumerCreditListoffer = new ArrayList();
    private List<CarLoans> carLoansListoffer = new ArrayList();
    private List<AgriculturalCredit> agriculturalCreditListoffer = new ArrayList();
    private List<Cards> cardsListOffer = new ArrayList();

    private int Bankid = 0;
    private String BankImageSmall = null;
    private String BankImageBig = null;
    private String BankName = null;
    private int bankID = 0;
    private List<Mortgage> deposiOposit = new ArrayList<>();
    private String Amount = null;
    private String amoutFiltered = null;
    private String MaxAmount = null;
    private String Sorting = null;

    private List<Integer> comparListDeposit = new ArrayList<>();
    private List<Integer> comparListMortgage = new ArrayList<>();
    private static List<Integer> comparListConsumer = new ArrayList<>();
    private static List<Integer> comparListCarLoan = new ArrayList<>();
    private static List<Integer> comparListMicro = new ArrayList<>();
    private static List<Integer> comparListAg = new ArrayList<>();
    private static List<Integer> comparListCard = new ArrayList<>();


    MortgageDaoController depositDaoController = new MortgageDaoController();

    private int  arrow = 0;
    private int arrow2 = 0;
    private int MinAmount = 0;
    private String PageNameToDelete=null;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            mortgageBanks(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            mortgageBanks(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void mortgageBanks(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
      //getCityFromUser(request);
        getParameters(request);
        getPageName(request);
        getPageLanguage(language);
        getPageLabguageName(language);
        countHit();

        getImageOfBank();
        getOtherBanks();
        checkForCompareList();
        setRequestes(request);
        gotoBanksPage(request, response);
    }


    private void gotoBanksPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/MBankingBank.jsp").forward(request, response);
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

    private void getOtherBanks() throws SQLException {
        List<Mortgage> depoposit = new ArrayList<>();
        deposiOposit = new ArrayList<>();
        depoposit = depositDaoController.getAllMortage();
        for (int i = 0; i < depoposit.size(); i++) {
            if (depoposit.get(i).getBankId() != Bankid) {
                deposiOposit.add(depoposit.get(i));
            } else {
                continue;
            }
        }
        for (int i = 0; i < deposiOposit.size(); i++) {
            System.out.println(deposiOposit.get(i).getBankId());
        }
    }

    private void setRequestes(HttpServletRequest request) {
        request.setAttribute("BankImageSmall", BankImageSmall);
        request.setAttribute("BankImageBig", BankImageBig);
        request.setAttribute("BankName", BankName);
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("PageName", pageName);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        // request.setAttribute("banksList", banksList);
        request.setAttribute("City", city );
        request.setAttribute("depositListOffer", depositListOffer);
        request.setAttribute("cardsListOffer", cardsListOffer);
        request.setAttribute("agriculturalCreditListoffer", agriculturalCreditListoffer);
        request.setAttribute("carLoansListoffer", carLoansListoffer);
        request.setAttribute("consumerCreditListoffer", consumerCreditListoffer);
        request.setAttribute("MortgageListOffer", mortgageListOffer);
        request.setAttribute("productNameList", productNameList);
        request.setAttribute("depositList", depositList);
        request.setAttribute("deposiOposit", deposiOposit);
        request.setAttribute("Bankid", Bankid );
        request.setAttribute("arrow", arrow );
        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);

    }


    private void getImageOfBank() throws SQLException {
        for (int i = 0; i < depositList.size(); i++) {
            bankID = depositList.get(i).getBankId();
        }
        BanksDaoController banksDaoController = new BanksDaoController();
        List<Banks> banksList = banksDaoController.getBankInfoById(bankID);
        for (int i = 0; i < banksList.size(); i++) {
            BankImageSmall = banksList.get(i).getBankSmallLogo();
            BankImageBig = banksList.get(i).getBankBigLogo();
            BankName = banksList.get(i).getBankName();
        }
    }


    private void countHit() throws SQLException {
        hitCounter.countingHits(pageName, pageCurrancy, city, language, sessionId);
    }


    private void getPageLanguage(String language) {
        Pagelanguage = checkLanguageAndCurrency.checkLanguage(language);

    }

    private void getPageLabguageName(String language) {
        pageLanguageName = checkLanguageAndCurrency.checkLanguageName(language);
    }

    private int getMinAmount() {
        for (int i = 0; i <depositList.size() ; i++) {
            MinAmount = depositList.get(i).getMMinAmount();
        }
        return MinAmount;
    }

    private void getPageName(HttpServletRequest request) {
        pageName = pageNameHelper.pageName(request);
        System.out.println(pageName);
    }

    private void depositeFilter() throws SQLException {
        depositList = depositDaoController.BankingAsc(Bankid);
        arrow = 0;
        arrow2 = 0;
    }

    private void AmountAsec() throws SQLException {
        depositList = depositDaoController.getDepositbyMaxMinAmountAsec(getMinAmount(),  pageCurrancy);
        Collections.sort(depositList, new DepositComaratorAmount());
        arrow = 1;
        arrow2 = 1;
    }

    private void PercentageDesc() throws SQLException {
      /*  // depositAllInSubRage = depositDaoController.getSemiDepositbyMaxMinAmount(Integer.parseInt(amoutFiltered), Integer.parseInt(MaxAmount),pageCurrancy);
        depositList = depositDaoController.getDepositMaxMinPercentSub(getMinAmount(), 5000000, pageCurrancy);*/
        depositList = depositDaoController.BankingAsc(Bankid);

        arrow = 0;
        arrow2 = 0;
    }

    private void PercentageAsec() throws SQLException {
        depositList = depositDaoController.BankingDesc(Bankid);
       // Collections.sort(depositList, new DepositComarator());
        arrow = 1;
        arrow2 = 1;
    }

    class DepositComarator implements Comparator<Mortgage> {

        @Override
        public int compare(Mortgage o1, Mortgage o2) {
            return Double.compare(o2.getMFatual(), o1.getMFatual());
        }
    }

    class DepositComaratorAmount implements Comparator<Mortgage> {

        @Override
        public int compare(Mortgage o1, Mortgage o2) {
            return Integer.compare(o2.getMMinAmount(), o1.getMMinAmount());
        }
    }

    private void SotringMachanizm(String sorting) throws SQLException {
        switch (sorting) {
            case "DescAmount":
                depositeFilter();

                break;
            case "AsecAmount":
                AmountAsec();

                break;
            case "DescPercent":
                PercentageDesc();

                break;
            case "AsecPercent":
                PercentageAsec();

                break;
        }
    }



    private void getParameters(HttpServletRequest request) throws SQLException {
        Bankid = Integer.parseInt(request.getParameter("bankId"));
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
    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        sessionId = session.getId();
        request.setAttribute("session",sessionId);
        session.setMaxInactiveInterval(86400);
        getUserSession(session, request, response);
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

    private void getUserSession(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        if (checker.checkSessionUser(request, response)) {
            sessionId = checker.requestSessionofUser(session);
        } else {
            sessionId = session.getId();
        }
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
}


