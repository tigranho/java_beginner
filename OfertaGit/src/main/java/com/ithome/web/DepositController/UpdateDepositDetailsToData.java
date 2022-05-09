package com.ithome.web.DepositController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CurrancyDao;
import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.Currancy;
import com.ithome.web.Bean.Deposit;
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

@WebServlet("/UpdateDepositDetailsToData")
public class UpdateDepositDetailsToData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    List<Currancy> currancyList = new ArrayList<>();
    private CurrancyDao currancyDao = new CurrancyDao();
    private List<Deposit> depositList = new ArrayList<>();
    private DepositDaoController depositDaoController = new DepositDaoController();
    private Deposit deposit = new Deposit();

    private int DId = 0;
    private String ProductName = null;
    private int ProductCode = 0;
    private String bankName= null;
    private int DMinAmount= 0;
    private int DMaxAmount= 0;
    private String currancy = null;
    private double DPercentage= 0;
    private int minAge= 0;
    private int maxAge= 0;
    private String DEquippingPossibilities= null;
    private int DEquippingPossibilitiesid= 0;
    private String DEarlierWithdrawalAmount= null;
    private int DEarlierWithdrawalAmountid= 0;
    private String DAutoExtendPeriod= null;
    private int DAutoExtendPeriodid= 0;
    private String DCapitalizationPercent= null;
    private int DCapitalizationPercentid= 0;
    private String bankWebSite = null;
    private String BankLink = null;
    private int Bankid = 0;
    private int timeline = 0;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateDepositDetailsToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateDepositDetailsToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateDepositDetailsToData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        getBankDetailByBankName(bankName);
        getBankFullDetail();
        getCurrancyFullDetail();
        updateDepositInData(createNewDepositInData(), request, response, DId);
    }

    private void updateDepositInData(int newDepositInData, HttpServletRequest request, HttpServletResponse response, int dId) throws ServletException, IOException, SQLException {
        if (newDepositInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            getDepositDetailById(DId);
            setRequestToDepositUpdatePage(request);
            goBackToErrorPage(request, response, message);
        } else {
            String message = "Հաջողությամբ Հաջողությամբ թարմացրել եէ Ավանդը";
            getDepositDetailById(DId);
            setRequestToDepositUpdatePage(request);
            gotoNextPage(request,response,message);
        }

    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void goBackToErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/UpdateDeposit.jsp").forward(request, response);
    }

    private void setRequestToDepositUpdatePage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("BankFullList", banksList);
        request.setAttribute("CurrancyListFullList", currancyList);
        request.setAttribute("DepositList", depositList);
    }

    private void getDepositDetailById(int DId) throws SQLException {
        depositList = depositDaoController.getDepositById(DId);
    }


    private int createNewDepositInData() throws SQLException {
        return depositDaoController.UpdateDepositeInData(prepaireDepositInfoForData(),DId);
    }

    private Deposit prepaireDepositInfoForData() {
        return new Deposit(ProductCode,Bankid,bankName,bankWebSite,minAge,maxAge,currancy,timeline, ProductName,DMinAmount,DMaxAmount,DPercentage,DEquippingPossibilitiesid,DEquippingPossibilities,DEarlierWithdrawalAmountid
        ,DEarlierWithdrawalAmount,DAutoExtendPeriodid,DAutoExtendPeriod,DCapitalizationPercentid,DCapitalizationPercent,BankLink);
    }

    private void getCurrancyFullDetail() throws SQLException {
        currancyList = currancyDao.getAllCurrancyList();
    }

    private void getBankFullDetail() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
    }

    private void getBankDetailByBankName(String BankName) throws SQLException {
        banksList = banksDaoController.getBankFullInfoByName(BankName);
        for (int i = 0; i <banksList.size() ; i++) {
            bankWebSite= banksList.get(i).getBankWebSite();
            Bankid = banksList.get(i).getBankId();
        }
    }


    private void getParameters(HttpServletRequest request) {
        ProductName = request.getParameter("ProductName");
        ProductCode = Integer.parseInt(request.getParameter("ProductCode"));
        bankName = request.getParameter("BankName");
        DMinAmount = Integer.parseInt(request.getParameter("DMinAmount"));
        DMaxAmount = Integer.parseInt(request.getParameter("DMaxAmount"));
        currancy = request.getParameter("currancy");
        DPercentage = Double.parseDouble(request.getParameter("DPercentage"));
        minAge = Integer.parseInt(request.getParameter("minAge"));
        maxAge = Integer.parseInt(request.getParameter("maxAge"));
        DEquippingPossibilities = request.getParameter("DEquippingPossibilities");
        DEquippingPossibilitiesid = Integer.parseInt(request.getParameter("DEquippingPossibilitiesid"));
        DEarlierWithdrawalAmount = request.getParameter("DEarlierWithdrawalAmount");
        DEarlierWithdrawalAmountid = Integer.parseInt(request.getParameter("DEarlierWithdrawalAmountid"));
        DAutoExtendPeriod = request.getParameter("DAutoExtendPeriod");
        DAutoExtendPeriodid = Integer.parseInt(request.getParameter("DAutoExtendPeriodid"));
        DCapitalizationPercent = request.getParameter("DCapitalizationPercent");
        DCapitalizationPercentid = Integer.parseInt(request.getParameter("DCapitalizationPercentid"));
        timeline = Integer.parseInt(request.getParameter("timeline"));
        DId = Integer.parseInt(request.getParameter("DId"));
        BankLink = request.getParameter("BankLink");
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



