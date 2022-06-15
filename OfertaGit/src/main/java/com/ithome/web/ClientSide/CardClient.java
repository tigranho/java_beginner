package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.CardsDao;
import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.AdminDao.ProductNameDaoController;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/CardClient")
public class CardClient extends HttpServlet {
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
    private List<Deposit> depositeAseList = new ArrayList<>();

    private MortgageSpecialOfferHelper mortgageSpecialOfferHelper = new MortgageSpecialOfferHelper();
    private ConsumerSpecialOfferHelper consumerSpecialOfferHelper = new ConsumerSpecialOfferHelper();
    private CarLoanSpecialOfferHelper carLoanSpecialOfferHelper = new CarLoanSpecialOfferHelper();
    private DepositSpecialOfferHelper depositSpecialOfferHelper = new DepositSpecialOfferHelper();
    private AgSpecialOfferHelper agSpecialOfferHelper = new AgSpecialOfferHelper();
    private CardsSpecialOfferHelper cardsSpecialOfferHelper = new CardsSpecialOfferHelper();

    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private HitCounter hitCounter = new HitCounter();
    private LookUpProgram lookUpProgram = new LookUpProgram();
    private LanguageHelper languageHelper = new LanguageHelper();
    private PageNameHelper pageNameHelper = new PageNameHelper();

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
    private DropDownCurrancyHelper dropDownCurrancyHelper = new DropDownCurrancyHelper();
    private String Ruble = null;
    private String Dollar = null;
    private String Euro = null;
    private String Dram = null;
    private String Credit = null;
    private String Cashback = null;
    private String Period = null;
    private String Free = null;
    private String Depit = null;
    private DropDownHelper dropDownHelper = new DropDownHelper();

    private List<Cards> cardsList = new ArrayList<>();
    private CardsDao cardsDao = new CardsDao();
    List<Cards> filterByCurancy = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            cardClient(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            cardClient(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void cardClient(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        //getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getParameters(request);
        getCards(request);
        getMainPageRange();
        checkForCompareList();
        GetDropDownByCurrancy(dropDownsList, pageCurrancy);
        getDepositSpecialOffers();
        getProductnamesList();
        getMortgageSpecialOffers();
        getConsumerSpecialOffers();
        getCarLoanSpecialOffers();
        getCardsSpecialOffers();
        getAgSpecialOffers();
        createRequestes(request);
        GOTOPage(request, response);
    }

    private void getMainPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName(pageName);
    }

    private void GetDropDownByCurrancy(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getCards(HttpServletRequest request) throws SQLException {
        int cardIdCheck = 0;
        int cashBackid =0;
        List<Cards> firstCardList = new ArrayList<>();
        firstCardList = cardsDao.getAllCardsList();
        filterByCurancy = new ArrayList<>();
        for (int i = 0; i < firstCardList.size(); i++) {
            String currancy = firstCardList.get(i).getCurrancy();
            String GracePeriod = firstCardList.get(i).getTimer();
            if (currancy.equals("AMD") && !Dram.equals("OFF") && Cashback.equals("OFF") && Period.equals("OFF") &&Free.equals("OFF")&&Depit.equals("OFF")&&Credit.equals("OFF")) {
                int cardId = Integer.parseInt(String.valueOf(firstCardList.get(i).getCardId()));
                cardIdCheck = cardId;
                filterByCurancy.addAll(cardsDao.getCardsById(cardId));
            }
            if (currancy.equals("EUR") && !Euro.equals("OFF")&& Cashback.equals("OFF") && Period.equals("OFF") &&Free.equals("OFF")&&Depit.equals("OFF")&&Credit.equals("OFF")) {
                int cardId = Integer.parseInt(String.valueOf(firstCardList.get(i).getCardId()));
                filterByCurancy.addAll(cardsDao.getCardsById(cardId));
            }
            if (currancy.equals("USD") && !Dollar.equals("OFF")&& Cashback.equals("OFF") && Period.equals("OFF") &&Free.equals("OFF")&&Depit.equals("OFF")&&Credit.equals("OFF")) {
                int cardId = Integer.parseInt(String.valueOf(firstCardList.get(i).getCardId()));
                filterByCurancy.addAll(cardsDao.getCardsById(cardId));
            }
            if (currancy.equals("RUB") && !Ruble.equals("OFF")&& Cashback.equals("OFF") && Period.equals("OFF") &&Free.equals("OFF")&&Depit.equals("OFF")&&Credit.equals("OFF")) {
                int cardId = Integer.parseInt(String.valueOf(firstCardList.get(i).getCardId()));
                filterByCurancy.addAll(cardsDao.getCardsById(cardId));
            }
            if (Cashback.equals("Cash") && !Cashback.equals("OFF") && currancy.equals("AMD") && !Dram.equals("OFF")) {
                int cardId = Integer.parseInt(String.valueOf(firstCardList.get(i).getCardId()));
                if(firstCardList.get(i).getCashback().equals("Cashback") && cardIdCheck!= cardId) {
                    filterByCurancy.addAll(cardsDao.getCardsById(cardId));
                }
            }
            if (Period.equals("Periods") && !Period.equals("OFF") && currancy.equals("AMD") && !Dram.equals("OFF")) {
                int cardId = Integer.parseInt(String.valueOf(firstCardList.get(i).getCardId()));
                if(firstCardList.get(i).getTimer().equals("Grace period") ) {
                    filterByCurancy.addAll(cardsDao.getCardsById(cardId));
                }
            }
            if (Free.equals("free") && !Free.equals("OFF")&&currancy.equals("AMD") && !Dram.equals("OFF")) {
                int cardId = Integer.parseInt(String.valueOf(firstCardList.get(i).getCardId()));
                if(firstCardList.get(i).getFree().equals("Free") && cardIdCheck!= cardId) {
                    filterByCurancy.addAll(cardsDao.getCardsById(cardId));
                }
            }
            if (Depit.equals("Depit") && !Depit.equals("OFF")&&currancy.equals("AMD") && !Dram.equals("OFF")) {
                int cardId = Integer.parseInt(String.valueOf(firstCardList.get(i).getCardId()));
                if(firstCardList.get(i).getDebit().equals("Debit") && cardIdCheck!= cardId) {
                    filterByCurancy.addAll(cardsDao.getCardsById(cardId));
                }
            }
            if (Credit.equals("credit") && !Credit.equals("OFF") || Credit.equals("Credit") && !Credit.equals("OFF") || Credit.equals("crediting") && !Credit.equals("OFF") && currancy.equals("AMD") && !Dram.equals("OFF")) {
                int cardId = Integer.parseInt(String.valueOf(firstCardList.get(i).getCardId()) );
                if(firstCardList.get(i).getCrediting().equals("Credit") && cardIdCheck!= cardId) {
                    filterByCurancy.addAll(cardsDao.getCardsById(cardId));
                }
            }

        } FilterCardForDublicated();

    }

    private void FilterCardForDublicated() {

        for (int j = 0; j < filterByCurancy.size(); j++) {
            for (int k = 0; k <filterByCurancy.size() ; k++) {
                if(filterByCurancy.get(j).getCardId() == filterByCurancy.get(k).getCardId() && j!=k){
                    System.out.println(filterByCurancy.get(j).getCardId());
                    System.out.println(filterByCurancy.get(k).getCardId());
                    filterByCurancy.remove(filterByCurancy.get(j));

                }else{
                    continue;
                }
            }
        }
    }

    private boolean IsEqualId(int cardIdCheck, int cardId) {
        return cardIdCheck != cardId;
    }


    private void getParameters(HttpServletRequest request) throws SQLException {
        Ruble = request.getParameter("Ruble");
        Dollar = request.getParameter("Dollar");
        Euro = request.getParameter("Euro");
        Dram = request.getParameter("Dram");
        Credit = request.getParameter("Credit");
        Cashback = request.getParameter("Cashback");
        Period = request.getParameter("Period");
        Free = request.getParameter("Free");
        Depit = request.getParameter("Depit");
        if (request.getParameter("pageNameToDelete") != null) {
            PageNameToDelete = request.getParameter("pageNameToDelete");
            deleteList(PageNameToDelete);
        } else {
            PageNameToDelete = "";
        }

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


    private void GOTOPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Cards.jsp").forward(request, response);
    }

    private void createRequestes(HttpServletRequest request) {
        request.setAttribute("Ruble", Ruble);
        request.setAttribute("Dollar", Dollar);
        request.setAttribute("Euro", Euro);
        request.setAttribute("Dram", Dram);

        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("PageName", pageName);
        request.setAttribute("city", city);
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
        request.setAttribute("minAmount", amoutFiltered);
        request.setAttribute("maxAmount", MaxAmount);

        request.setAttribute("WorningMessage", WorningMessage);
        request.setAttribute("DepositCompareSize", DepositCompareSize);
        request.setAttribute("name", "deposit");
        request.setAttribute("PageToGo", PageToGo);
        request.setAttribute("City", city );
        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);
        request.setAttribute("filterByCurancy", filterByCurancy);

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
            city = LookUpProgram.start(request);
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
