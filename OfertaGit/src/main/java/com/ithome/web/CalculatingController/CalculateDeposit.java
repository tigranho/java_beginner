package com.ithome.web.CalculatingController;

import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.Bean.*;
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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CalculateDeposit")
public class CalculateDeposit extends HttpServlet {
    private CheckLanguageAndCurrency checkLanguageAndCurrency = new CheckLanguageAndCurrency();
    private String language = null;
    private String Pagelanguage = null;
    private String pageName = null;
    private String city = null;
    private String pageLanguageName = null;
    private String sessionId = null;
    private String Amount = null;
    private String PageToGo = null;
    private String amoutFiltered =null;
    private String MaxAmount=null;

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

    private List<Deposit>depositAllInRage = new ArrayList<>();

    private String range =null;
    private String rangeFiltered = null;
    private String Month =null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        calculateDeposit(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        calculateDeposit(request,response);
    }

    private void calculateDeposit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        //getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getParameters(request);
        filterAmount(Amount);
        setRequestToPage(request);
        gotoPage(request,response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/tester.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("pageCurrancy", pageCurrancy);
        request.setAttribute("pageName", pageName);
        request.setAttribute("city", city);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("amoutFiltered", amoutFiltered);
        request.setAttribute("Month", "1");
        request.setAttribute("percentage", "12");


    }

    private void getPageLanguage(String language) {
        Pagelanguage = checkLanguageAndCurrency.checkLanguage(language);
        pageLanguageName = checkLanguageAndCurrency.checkLanguageName(language);
    }

    private void filterAmount(String amount) {
        if(amount != null) {
            amoutFiltered = amount.replace(",", "");
            System.out.println("amoutFiltered " + amoutFiltered);
        }else{
            filterRnge(range);
        }
    }

    private void filterRnge(String range) {
        if(range != null) {
            rangeFiltered = range.replace(",", "");
            System.out.println("amoutFiltered " + amoutFiltered);
        }
    }


    private void getParameters(HttpServletRequest request) {
        if(request.getParameter("Amount") != null){
            Amount = request.getParameter("Amount");
        }else{
            range = request.getParameter("range");
        }


        MaxAmount = request.getParameter("MaxAmounr");
        PageToGo =request.getParameter("PageToGo");
    }

    private void getPageName(HttpServletRequest request) {
        pageName = pageNameHelper.pageName(request);
        System.out.println("Page name : " + pageName);
    }

    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        sessionId = session.getId();
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

    private void getCityFromUser(HttpServletRequest request) throws IOException {
        if (request.getParameter("city") == null) {
            city = LookUpProgram.start(request);
        } else {
            city = request.getParameter("city");
        }
    }
}

