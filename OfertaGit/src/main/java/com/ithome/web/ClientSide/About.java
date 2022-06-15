package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.AboutUsDao;
import com.ithome.web.Bean.AboutUs;
import com.ithome.web.Bean.DropDowns;
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
import java.util.List;

@WebServlet("/About")
public class About extends HttpServlet {
    private CheckLanguageAndCurrency checkLanguageAndCurrency = new CheckLanguageAndCurrency();
    private String language = null;
    private String country = null;
    private String Pagelanguage = null;
    private String pageName = null;
    private String pageLanguageName = null;
    private SessionChecker checker = new SessionChecker();
    private String sessionId = null;
    private LanguageHelper languageHelper = new LanguageHelper();
    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private String city = null;
    private LookUpProgram lookUpProgram = new LookUpProgram();
    private PageNameHelper pageNameHelper = new PageNameHelper();
    private HitCounter hitCounter = new HitCounter();
    private AboutUsDao aboutUsDao = new AboutUsDao();
    private List<AboutUs> aboutUsList = new ArrayList<>();

    private List<Integer> comparListDeposit = new ArrayList<>();
    private List<Integer> comparListMortgage = new ArrayList<>();
    private static List<Integer> comparListConsumer =  new ArrayList<>();
    private static List<Integer> comparListCarLoan =  new ArrayList<>();
    private static List<Integer> comparListMicro =  new ArrayList<>();
    private static List<Integer> comparListAg =  new ArrayList<>();
    private static List<Integer> comparListCard =  new ArrayList<>();

    private int CounsumerCounter=0;
    private List<DropDowns> dropDownsListWithCurrancy = new ArrayList<>();
    private DropDownCurrancyHelper dropDownCurrancyHelper = new DropDownCurrancyHelper();
    private List<DropDowns> dropDownsList = new ArrayList<>();
    private DropDownHelper dropDownHelper = new DropDownHelper();

    private String PageNameToDelete=null;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            aboutUs(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            aboutUs(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void aboutUs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
       //getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getPageLabguageName(language);
        countHit();
        getMainPageRange();
        getparameters(request);
        checkForCompareList();
        GetDropDownByCurrancy(dropDownsList, pageCurrancy);
        getAboutUS();
        setRequestes(request);
        gotoAboutUsPage(request,response);
    }

    private void getparameters(HttpServletRequest request) throws SQLException {
        if (request.getParameter("pageNameToDelete") != null) {
            PageNameToDelete = request.getParameter("pageNameToDelete");
            deleteList(PageNameToDelete);
        } else {
            PageNameToDelete = "";
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

    private void GetDropDownByCurrancy(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getMainPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName(pageName);
    }

    private void setRequestes(HttpServletRequest request) {
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("PageName", pageName);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("aboutUsList", aboutUsList);

        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);

        request.setAttribute("CounsumerCounter", CounsumerCounter);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);
        request.setAttribute("City", city);

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


    private void getAboutUS() throws SQLException {
        aboutUsList = aboutUsDao.getAllAboutUs();
    }

    private void countHit() throws SQLException {
        hitCounter.countingHits(pageName,pageCurrancy,city,language,sessionId);
    }

    private void getPageLabguageName(String language) {
        pageLanguageName = checkLanguageAndCurrency.checkLanguageName(language);
    }

    private void getPageLanguage(String language) {
        Pagelanguage = checkLanguageAndCurrency.checkLanguage(language);

    }

    private void getPageName(HttpServletRequest request) {
        pageName = pageNameHelper.pageName(request);
        System.out.println(pageName);
    }

    private void gotoAboutUsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/About.jsp").forward(request, response);
    }

    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        sessionId = session.getId();
        request.setAttribute("session",sessionId);
        session.setMaxInactiveInterval(86400);
        getUserSession(session, request, response );
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
        if(checker.checkSessionUser(request, response)) {
            sessionId = checker.requestSessionofUser(session);
        }else{
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
