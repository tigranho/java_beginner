package com.ithome.web.EmailController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CardsDao;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.Cards;
import com.ithome.web.Bean.DropDowns;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Helpers.*;
import com.ithome.web.Localization.CheckLanguageAndCurrency;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SendEmailForCards")
public class SendEmailForCards extends HttpServlet {
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
    private int productCode = 0;
    private int bankID = 0;
    private String bankEmail = null;
    private String emailBody = null;

    private String cardName = null;
    private String cardType = null;
    private String cardCurrancy = null;

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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            sendEmailForCards(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            sendEmailForCards(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void sendEmailForCards(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        //getCityFromUser(request);
        getPageLanguage(language);
        choosePageLanguageToPage();
        getParameters(request);
        getBankEmailAddress();
        getProductCode();
        getMainPageRange();
        checkForCompareList();
        GetDropDownByCurrancy(dropDownsList, pageCurrancy);
        checkEmail(sendEmailWithMessage(),response,request);
    }

    private void GetDropDownByCurrancy(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getMainPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName("ԳԼԽԱՎՈՐ");
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


    private void checkEmail(boolean sendEmailWithMessage,HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        if(sendEmailWithMessage){
            String message = "Ձեր էլեկտրոնային փոստը հաջողությամբ ուղարկվեց:";
            createRequestes(request);
            gotoToSuccessPage(request, response,message);
        }else{
            String message = "ինչ-որ բան չհաջողվեց, կրկին փորձեք:";
            createRequestes(request);
            gotoToErrorPage(request, response,message);
        }
    }

    private boolean sendEmailWithMessage() {
        emailBody = cardName + " " + cardType + " " +cardCurrancy;
        return SendMail.sendBank(bankEmail, emailBody);
    }

    private void gotoToSuccessPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("Success.jsp").forward(request, response);
    }

    private void gotoToErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("Success.jsp").forward(request, response);
    }

    private void getProductCode() throws SQLException {
        CardsDao cardsDao = new CardsDao();
        List<Cards> cardsList = cardsDao.getCardsByCardCode(productCode);
        for (int i = 0; i <cardsList.size() ; i++) {
            cardName = cardsList.get(i).getCardName();
            cardType = cardsList.get(i).getCardType();
            cardCurrancy = cardsList.get(i).getCurrancy();

        }
    }


    private void createRequestes(HttpServletRequest request) {
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("PageName", pageName);
        request.setAttribute("city", city);

        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);

        request.setAttribute("CounsumerCounter", CounsumerCounter);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);
    }

    private void getBankEmailAddress() throws SQLException {
        BanksDaoController banksDaoController = new BanksDaoController();
        List<Banks> banksList = banksDaoController.getBankInfoById(bankID);
        for (int i = 0; i <banksList.size() ; i++) {
            bankEmail = banksList.get(i).getBankEmailAddress();
        }
    }

    private void getParameters(HttpServletRequest request) {
        productCode = Integer.parseInt(request.getParameter("productCode"));
        bankID = Integer.parseInt(request.getParameter("BankId"));
    }

    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        getUserSession(session, request, response );
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

    private void choosePageLanguageToPage() {
        pageLanguageName = checkLanguageAndCurrency.checkLanguageName(language);
    }

    private void getCurancyFromPage(HttpServletRequest request) {
        if (request.getParameter("Currancy") == null) {
            pageCurrancy = checkLanguageAndCurrency.checkCurrency(pageCurrancyFromPage);
        } else {
            pageCurrancy = checkLanguageAndCurrency.checkCurrency(request.getParameter("Currancy"));
        }
    }


    private void getPageLanguage(String language) {
        Pagelanguage = checkLanguageAndCurrency.checkLanguage(language);
    }

    private String getLanguagesFromPage(HttpServletRequest request) {
        if (request.getParameter("Pagelanguage") == null) {
            language = languageHelper.Pagelanguage(request, Pagelanguage);
        } else {
            language = languageHelper.Pagelanguage(request, request.getParameter("Pagelanguage"));
        }
        return language;
    }
}
