package com.ithome.web.CardController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CardTypeDao;
import com.ithome.web.AdminDao.CardsDao;
import com.ithome.web.AdminDao.CurrancyDao;
import com.ithome.web.Bean.*;
import com.ithome.web.Helpers.AdminChecker;
import com.ithome.web.Helpers.SessionChecker;

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

@WebServlet("/UpdateCardDetailsToData")
public class UpdateCardDetailsToData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private CardsDao cardsDao = new CardsDao();
    private Cards cards = new Cards();


    private int ProductCode = 0;
    private String cardName = null;
    private int cardMinServiceFee = 0;
    private int cardMaxServiceFee = 0;
    private double cardPerMinCashBack = 0.0;
    private double cardPerMaxCashBack = 0.0;
    private double cardPerMinDiscount = 0.0;
    private double cardPerMaxDiscount = 0.0;
    private int cardMinCreditLimit = 0;
    private int cardMaxCreditLimit = 0;
    private double cardPerCreditLimit = 0.0;
    private double cardPerFactual = 0.0;
    private int minAge = 0;
    private int maxAge = 0;
    private int cardGracePeriod = 0;
    private double cardPerOnCreditStanding = 0.0;
    private String currancy = null;
    private String cardType = null;
    private String BankName = null;
    private int BankId = 0;
    private String BankWebAddress = null;
    private int typeId = 0;
    private int newCardId = 0;
    private int cardId =0;
    private double minCashBack = 0;
    private double maxCashBack = 0;
    private String BankLink = null;
    private String Detail = null;

    private List<Banks> banksList = new ArrayList<>();
    private List<CardTypes> cardTypesList = new ArrayList<>();
    private List<Currancy> currancyList = new ArrayList<>();
    private List<Cards> cardsList = new ArrayList<>();

    private BanksDaoController banksDaoController = new BanksDaoController();
    private CurrancyDao currancyDao = new CurrancyDao();
    private CardTypeDao cardTypeDao = new CardTypeDao();

    private String imageAddress =null;

    private String Cashback = null;
    private String Timer = null;
    private String Free = null;
    private String Debit = null;
    private String Credit = null;
    private String CardInfo = null;
    private String LastLink = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateCardDetailsToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateCardDetailsToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCardDetailsToData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        getBankDetailsByBankName(BankName);
        getBanlFullDetail();
        getCurrancyFullDetail();
        getImageAddress();
        getCardDetailById(cardId);
        updateCardInfoInData(prepaireCardInfoForData(),request,response,cardId);


    }

    private void updateCardInfoInData(Cards prepaireCardInfoForData, HttpServletRequest request, HttpServletResponse response, int cardId) throws ServletException, IOException, SQLException {
        int newCardInData =cardsDao.UpdateCardDetailsinData(prepaireCardInfoForData,cardId);
        if (newCardInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            getCardTypeFullDetail();
            setRequestToPage(request);
            goBackToPage(request, response, message);
        } else {
            String message = "Քարտը հաջողությամբ թարմացվել է";
            getCardTypeFullDetail();
            setRequestToPage(request);
            gotoNextPage(request,response,message);
        }
    }

    private void getImageAddress() throws SQLException {
        cardsList = cardsDao.getCardsById(cardId);
        for (int i = 0; i <cardsList.size() ; i++) {
            imageAddress   = cardsList.get(i).getCardImage();
        }
    }


    private void getBankDetailsByBankName(String bankName) throws SQLException {
        banksList = banksDaoController.getBankFullInfoByName(bankName);
        BankId = getBankId(banksList);
        BankWebAddress = getBankWebAddress(banksList);
        System.out.println("BankId " + BankId + " BankWebAddress " + BankWebAddress);
    }

    private String getBankWebAddress(List<Banks> banksList) {
        return banksList.get(0).getBankWebSite();
    }

    private int getBankId(List<Banks> banksList) {
        return banksList.get(0).getBankId();
    }

    private void getCardDetailById(int cardId) throws SQLException {
        cardsList = cardsDao.getCardsById(cardId);
    }

    private void getCardTypeFullDetail() throws SQLException {
        cardTypesList = cardTypeDao.getAllCardsList();
    }

    private void getCurrancyFullDetail() throws SQLException {
        currancyList = currancyDao.getAllCurrancyList();
    }

    private void getBanlFullDetail() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response,String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void goBackToPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/UpdateCards.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("BankFullList", banksList);
        request.setAttribute("CurrancyListFullList", currancyList);
        request.setAttribute("CardTypeFullList", cardTypesList);
        request.setAttribute("CardFullListById", cardsList);

    }



    private Cards prepaireCardInfoForData() {
        return new Cards(ProductCode, cardName, BankId, BankName, BankWebAddress, typeId, cardType,imageAddress, cardMinServiceFee, cardMaxServiceFee, cardPerMinCashBack, cardPerMaxCashBack, cardPerMaxDiscount, cardPerMaxDiscount
                , cardMinCreditLimit, cardMaxCreditLimit, cardPerCreditLimit, cardPerFactual, cardGracePeriod, currancy, minAge, maxAge, cardPerOnCreditStanding,minCashBack,maxCashBack,BankLink,Detail,Cashback,Timer,Free,Debit,Credit,CardInfo,LastLink);
    }

    private void getParameters(HttpServletRequest request) {
        cardId = Integer.parseInt(request.getParameter("cardId"));
       /* cardType = request.getParameter("cardType");*/
        currancy = request.getParameter("currancy");
        ProductCode = Integer.parseInt(request.getParameter("ProductCode"));
        BankName = request.getParameter("BankName");
        cardName = request.getParameter("cardName");
        cardMinServiceFee = Integer.parseInt(request.getParameter("cardMinServiceFee"));
        cardMaxServiceFee = Integer.parseInt(request.getParameter("cardMaxServiceFee"));
        cardPerMinCashBack = Double.parseDouble(request.getParameter("cardPerMinCashBack"));
        cardPerMaxCashBack = Double.parseDouble(request.getParameter("cardPerMaxCashBack"));
        cardPerMinDiscount = Double.parseDouble(request.getParameter("cardPerMinDiscount"));
        cardPerMaxDiscount = Double.parseDouble(request.getParameter("cardPerMaxDiscount"));
        cardMinCreditLimit = Integer.parseInt(request.getParameter("cardMinCreditLimit"));
        cardMaxCreditLimit = Integer.parseInt(request.getParameter("cardMaxCreditLimit"));
        cardPerCreditLimit = Double.parseDouble(request.getParameter("cardPerCreditLimit"));
        cardPerFactual = Double.parseDouble(request.getParameter("cardPerFactual"));
        cardGracePeriod = Integer.parseInt(request.getParameter("cardGracePeriod"));
        minAge = Integer.parseInt(request.getParameter("minAge"));
        maxAge = Integer.parseInt(request.getParameter("maxAge"));
        cardPerOnCreditStanding = Double.parseDouble(request.getParameter("cardPerOnCreditStanding"));
        minCashBack = Double.parseDouble(request.getParameter("MinCashBack"));
        maxCashBack = Double.parseDouble(request.getParameter("MaxCashBack"));
        Detail = request.getParameter("cardDetails");
        BankLink = request.getParameter("BankLink");
        Cashback = request.getParameter("Cashback");
        Timer = request.getParameter("Timer");
        Free = request.getParameter("Free");
        Debit = request.getParameter("Debit");
        Credit = request.getParameter("Credit");
        CardInfo = request.getParameter("cardInfo");
        LastLink = request.getParameter("lastLink");
    }

    /**
     * Controlling the session for admin using helper classes
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        getSession(session, request, response);

    }
    /**
     * Fill admin in list with the specific id
     *
     * @param adminid
     */
    private void getFullAdminList(int adminid) {
        adminList = adminChecker.getAllInfoofAdmin(adminid);
    }

    /**
     * get admin admin id by username from session
     *
     * @param request
     * @param response
     */
    private void getAdminInfo(HttpServletRequest request, HttpServletResponse response) {
        adminId = adminChecker.getAdminId(username);
        getFullAdminList(adminId);
    }

    /**
     * Session of uadmin if no session true send to login page else get the seesion key
     *
     * @param session
     * @param request
     * @param response
     * @throws IOException
     */
    private void getSession(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (checker.checkSession(request, response)) {
            username = checker.requestSessionofAdmin(session);
        } else {
            response.sendRedirect("/admin/SignIn.jsp");
        }
    }
}
