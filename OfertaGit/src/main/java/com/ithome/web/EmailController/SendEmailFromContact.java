package com.ithome.web.EmailController;

import com.ithome.web.Helpers.LanguageHelper;
import com.ithome.web.Helpers.LookUpProgram;
import com.ithome.web.Helpers.PageNameHelper;
import com.ithome.web.Helpers.SessionChecker;
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
import java.text.NumberFormat;

@WebServlet("/SendEmailFromContact")
public class SendEmailFromContact extends HttpServlet {
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
    private String message = null;
    private String sendName = null;
    private String senderPhoneNumber = null;
    private String senderEmailAddress = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendEmailFromContact(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendEmailFromContact(request, response);
    }

    private void sendEmailFromContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        ////getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        choosePageLanguageToPage();
        getParameters(request);
        checkEmail(sendEmailWithMessage(),response,request);



    }
    private void getPageName(HttpServletRequest request) {
        pageName = pageNameHelper.pageName(request);
    }

    private void checkEmail(boolean sendEmailWithMessage,HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        if(sendEmailWithMessage){
            String message = "success";
            createRequestes(request);
            gotoToContactUsPage(request, response,message);
        }else{
            String message = "Error";
            createRequestes(request);
            gotoToContactUsPageError(request, response,message);

        }
    }


    private void gotoToContactUsPage(HttpServletRequest request, HttpServletResponse response,String message) throws ServletException, IOException {
        request.setAttribute("messageSuccess",  message);
        System.out.println("messageError " + message);
        request.getRequestDispatcher("/ContactUs.jsp").forward(request, response);
    }

    private void gotoToContactUsPageError(HttpServletRequest request, HttpServletResponse response,String message) throws ServletException, IOException {
        request.setAttribute("messageError",  message);
        System.out.println("messageError " + message);
        request.getRequestDispatcher("/ContactUs.jsp").forward(request, response);
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


    private void getParameters(HttpServletRequest request) {
        sendName = request.getParameter("to").trim();
        senderPhoneNumber = request.getParameter("senderPhoneNumber").trim();
        senderEmailAddress = request.getParameter("senderEmailAddress").trim();
        message = request.getParameter("senderMessage");
    }

    private boolean sendEmailWithMessage() {
        return SendMail.send(sendName, senderPhoneNumber, senderEmailAddress, message);
    }

    private void createRequestes(HttpServletRequest request) {
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("PageName", pageName);
        request.setAttribute("city", city);
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
