package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CommentDaoComtroller;
import com.ithome.web.AdminDao.DepositDaoController;
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
import java.util.List;

@WebServlet("/Credits")
public class Credits extends HttpServlet {
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
    private String Currancy = null;

    private List<DropDowns> dropDownsList = new ArrayList<>();
    private List<DropDowns> dropDownsListWithCurrancy = new ArrayList<>();

    private List<Deposit> depositList = new ArrayList<>();
    private List<Banks> banksList = new ArrayList<>();


    private DropDownCurrancyHelper dropDownCurrancyHelper = new DropDownCurrancyHelper();
    private DropDownHelper dropDownHelper = new DropDownHelper();

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

    private int ProductCode = 0;
    private String FromPageName = null;
    private String Months = null;
    private String Range = null;
    private String rangeFiltered = null;
    private float percentage = 0.0f;

    List<Comments> commentsList = new ArrayList<>();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            credits(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            credits(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void credits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        //getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getParameters(request);
        getDepositListByProductCode(ProductCode);

        checkForCompareList();
        getPageRange();
        GetDropDownByCurrancy(dropDownsList, pageCurrancy);
        getCmments();
        createRequestes(request);
        gotoPage(request, response);
    }

    private void getCmments() throws SQLException {
        commentsList = new ArrayList<>();
        CommentDaoComtroller commentDaoComtroller = new CommentDaoComtroller();
        commentsList = commentDaoComtroller.getCommentByProdcutCode(ProductCode);
    }

   /* private void getDetailsFromPageNameAndId(String fromPageName, int productCode) throws SQLException {
        switch (fromPageName) {
            case "Deposit":

                break;

        }
    }*/

    private void getDepositListByProductCode(int productCode) throws SQLException {
        DepositDaoController depositDaoController = new DepositDaoController();
        depositList = depositDaoController.getDepositByCardCode(productCode);

        BanksDaoController banksDaoController = new BanksDaoController();
        int bankId = depositList.get(0).getBankId();
        banksList = banksDaoController.getBankInfoById(bankId);


    }

    private void createRequestes(HttpServletRequest request) {
        request.setAttribute("CommentsList", commentsList);

        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("PageName", pageName);
        request.setAttribute("City", city);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);


        request.setAttribute("Amountfiltered", amoutFiltered);
        //request.setAttribute("Amount", Amount);
        request.setAttribute("range", Amount);
        request.setAttribute("months", Months);
        // request.setAttribute("value_url", Range);
        request.setAttribute("value_url", rangeFiltered);

        request.setAttribute("minAmount", amoutFiltered);
        request.setAttribute("maxAmount", MaxAmount);
        request.setAttribute("MaxAmount", MaxAmount);
        request.setAttribute("WorningMessage", WorningMessage);
        request.setAttribute("DepositCompareSize", DepositCompareSize);
        request.setAttribute("name", "deposit");
        request.setAttribute("ProductCode", ProductCode);

        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);
        request.setAttribute("TheList", depositList);
        request.setAttribute("bankList", banksList);
        request.setAttribute("Per", percentage);
        request.setAttribute("Percentageing", percentage);
    }

    private void GetDropDownByCurrancy(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
    }

    private void getPageRange() throws SQLException {
        dropDownsList = dropDownHelper.getPageName(getPageNameArm(pageName));
    }

    private String getPageNameArm(String pageName) {
        return pageNameHelper.ArmPageName(pageName);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Credits.jsp").forward(request, response);
    }

    private void filterAmount(String amount) {
        if (amount.contains(",")) {
            amoutFiltered = amount.replace(",", "");
        } else {
            amoutFiltered = amount;
        }
        System.out.println("amoutFiltered " + amoutFiltered);
    }

    private void filterRange(String amount) {
        if (amount.contains(",")) {
            rangeFiltered = amount.replace(",", "");
        } else {
            rangeFiltered = amount;
        }
        System.out.println("amoutFiltered " + rangeFiltered);
    }


    private void getParameters(HttpServletRequest request) throws SQLException {
        if (request.getParameter("ProductCode") != null) {
            ProductCode = Integer.parseInt(request.getParameter("ProductCode"));
        } else if (request.getParameter("id") != null) {
            ProductCode = Integer.parseInt(request.getParameter("id"));
        }

        if (request.getParameter("months") != null) {
            Months = request.getParameter("months");
        }else{
            Months= "1";
        }
        if (Months.equals("30") || Months.equals("90") || Months.equals("180") || Months.equals("270") || Months.equals("360") || Months.equals("540") || Months.equals("720") || Months.equals("1080")) {
            int MonthsNotConverted = Integer.parseInt(Months);
            int finalMonth = MonthsNotConverted / 30;
            Months = (String.valueOf(finalMonth));
        }
        if(Months.equals("0")){
            List<Deposit> depositList = new ArrayList<>();
            DepositDaoController depositDaoController = new DepositDaoController();
            depositList= depositDaoController.getDepositByCardCode(ProductCode);
            int finalMonth = depositList.get(0).getTimeLine() / 30;
            Months = (String.valueOf(finalMonth));
        }

        getMaxAmount();

        if (request.getParameter("FromPageName") != null) {
            FromPageName = request.getParameter("FromPageName");
        } else {
            FromPageName = "Deposit";
        }

        String newcurrancy = request.getParameter("Currancy");
        if (newcurrancy.equals(Currancy) || Currancy == null) {
            Amount = "";
            if (request.getParameter("Amount") != null) {
                Amount = request.getParameter("Amount");
                filterAmount(Amount);
            } else if (request.getParameter("SpecialAMount") != null) {
                Amount = request.getParameter("SpecialAMount");
            } else if (request.getParameter("range") != null) {
                Amount = request.getParameter("range");
                filterAmount(Amount);

            } else if (request.getParameter("value") != null) {
                Amount = request.getParameter("value");
                filterAmount(Amount);
            } else {
                Amount = "";
            }
        } else {
            Amount = "";
            Amount = String.valueOf(dropDownsListWithCurrancy.get(0).getMinAmount());
            filterAmount(Amount);
        }


        if (newcurrancy.equals(Currancy) || Currancy == null) {
            if (request.getParameter("value_two") != null) {
                Range = request.getParameter("value_two");
                filterRange(Range);
            } else if (request.getParameter("range_two") != null) {
                Range = request.getParameter("range_two");
                filterRange(Range);
            } else {
                Range = "";
            }
        } else {
            Range = "0";
            filterRange(Range);
        }

        Currancy = request.getParameter("Currancy");


        if (request.getParameter("pageNameToDelete") != null) {
            PageNameToDelete = request.getParameter("pageNameToDelete");
            deleteList(PageNameToDelete);
        } else {
            PageNameToDelete = "";
        }


        if (request.getParameter("Percentage") != null) {
            percentage = Float.parseFloat(request.getParameter("Percentage"));
        } else {
            List<Deposit> depositForPercentage = new ArrayList<>();
            DepositDaoController de = new DepositDaoController();
            depositForPercentage = de.getDepositByCardCode(ProductCode);
            if (pageCurrancy.equals("AMD")) {
                switch (Integer.parseInt(Months)) {
                    case 1:
                        percentage = (float) depositForPercentage.get(0).getAmdMonth1();
                        break;
                    case 3:
                        percentage = (float) depositForPercentage.get(0).getAmdMonth3();
                        break;
                    case 6:
                        percentage = (float) depositForPercentage.get(0).getAmdMonth6();
                        break;
                    case 9:
                        percentage = (float) depositForPercentage.get(0).getAmdMonth9();
                        break;
                    case 12:
                        percentage = (float) depositForPercentage.get(0).getAmdMonth12();
                        break;
                    case 18:
                        percentage = (float) depositForPercentage.get(0).getAmdMonth18();
                        break;
                    case 24:
                        percentage = (float) depositForPercentage.get(0).getAmdMonth24();
                        break;
                    case 36:
                        percentage = (float) depositForPercentage.get(0).getAmdMonth36();
                        break;

                }
            }
            if (pageCurrancy.equals("USD")) {
                switch (Integer.parseInt(Months)) {
                    case 1:
                        percentage = (float) depositForPercentage.get(0).getUsdMonth1();
                        break;
                    case 3:
                        percentage = (float) depositForPercentage.get(0).getUsdMonth3();
                        break;
                    case 6:
                        percentage = (float) depositForPercentage.get(0).getUsdMonth6();
                        break;
                    case 9:
                        percentage = (float) depositForPercentage.get(0).getUsdMonth9();
                        break;
                    case 12:
                        percentage = (float) depositForPercentage.get(0).getUsdMonth12();
                        break;
                    case 18:
                        percentage = (float) depositForPercentage.get(0).getUsdMonth18();
                        break;
                    case 24:
                        percentage = (float) depositForPercentage.get(0).getUsdMonth24();
                        break;
                    case 36:
                        percentage = (float) depositForPercentage.get(0).getUsdMonth36();
                        break;

                }
            }
            if (pageCurrancy.equals("EUR")) {
                switch (Integer.parseInt(Months)) {
                    case 1:
                        percentage = (float) depositForPercentage.get(0).getEurMonth1();
                        break;
                    case 3:
                        percentage = (float) depositForPercentage.get(0).getEurMonth3();
                        break;
                    case 6:
                        percentage = (float) depositForPercentage.get(0).getEurMonth6();
                        break;
                    case 9:
                        percentage = (float) depositForPercentage.get(0).getEurMonth9();
                        break;
                    case 12:
                        percentage = (float) depositForPercentage.get(0).getEurMonth12();
                        break;
                    case 18:
                        percentage = (float) depositForPercentage.get(0).getEurMonth18();
                        break;
                    case 24:
                        percentage = (float) depositForPercentage.get(0).getEurMonth24();
                        break;
                    case 36:
                        percentage = (float) depositForPercentage.get(0).getEurMonth36();
                        break;

                }
            }
            if (pageCurrancy.equals("RUB")) {
                switch (Integer.parseInt(Months)) {
                    case 1:
                        percentage = (float) depositForPercentage.get(0).getRubMonth1();
                        break;
                    case 3:
                        percentage = (float) depositForPercentage.get(0).getRubMonth3();
                        break;
                    case 6:
                        percentage = (float) depositForPercentage.get(0).getRubMonth6();
                        break;
                    case 9:
                        percentage = (float) depositForPercentage.get(0).getRubMonth9();
                        break;
                    case 12:
                        percentage = (float) depositForPercentage.get(0).getRubMonth12();
                        break;
                    case 18:
                        percentage = (float) depositForPercentage.get(0).getRubMonth18();
                        break;
                    case 24:
                        percentage = (float) depositForPercentage.get(0).getRubMonth24();
                        break;
                    case 36:
                        percentage = (float) depositForPercentage.get(0).getRubMonth36();
                        break;

                }
            }
        }


    }

    private void getMaxAmount() throws SQLException {
        getPageRange();
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
        for (int i = 0; i < dropDownsListWithCurrancy.size(); i++) {
            MaxAmount = dropDownsListWithCurrancy.get(i).getMaxAmount();
        }
    }

    private void getPageLanguage(String language) {
        Pagelanguage = checkLanguageAndCurrency.checkLanguage(language);
        pageLanguageName = checkLanguageAndCurrency.checkLanguageName(language);
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

    private void getCityFromUser(HttpServletRequest request) throws IOException {
        if (request.getParameter("city") == null) {
            city = LookUpProgram.start(request);
        } else {
            city = request.getParameter("city");
        }
    }

    private void getPageName(HttpServletRequest request) {
        pageName = pageNameHelper.pageName(request);
        System.out.println("Page name : " + pageName);
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


}
