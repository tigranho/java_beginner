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

@WebServlet("/UpdateEURDetailsToData")

public class UpdateEURDetailsToData extends HttpServlet {
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
    private double  AMD1Month = 0.0;
    private double  AMD3Month = 0.0;
    private double  AMD6Month = 0.0;
    private double  AMD9Month = 0.0;
    private double  AMD12Month = 0.0;
    private double  AMD18Month = 0.0;
    private double  AMD24Month = 0.0;
    private double  AMD36Month = 0.0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateEURDetailsToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateEURDetailsToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateEURDetailsToData(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        getBankFullDetail();
        getCurrancyFullDetail();
        updateAMDInData(createNewAMDInData(), request, response, DId);

    }
    private void updateAMDInData(int newDepositInData, HttpServletRequest request, HttpServletResponse response, int dId) throws ServletException, IOException, SQLException {
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

    private void goBackToErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/UpdateEUR.jsp").forward(request, response);
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
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

    private int createNewAMDInData() throws SQLException {
        return depositDaoController.UpdateEURInData(prepaireDepositInfoForData(),DId);
    }

    private Deposit prepaireDepositInfoForData() {
        return new Deposit(AMD1Month,AMD3Month,AMD6Month,AMD9Month,AMD12Month,AMD18Month,AMD24Month, AMD36Month,true);
    }


    private void getParameters(HttpServletRequest request) {
        DId = Integer.parseInt(request.getParameter("DId"));
        AMD1Month = Double.parseDouble(request.getParameter("EUR1Month"));
        AMD3Month = Double.parseDouble(request.getParameter("EUR3Month"));
        AMD6Month = Double.parseDouble(request.getParameter("EUR6Month"));
        AMD9Month = Double.parseDouble(request.getParameter("EUR9Month"));
        AMD12Month = Double.parseDouble(request.getParameter("EUR12Month"));
        AMD18Month = Double.parseDouble(request.getParameter("EUR18Month"));
        AMD24Month = Double.parseDouble(request.getParameter("EUR24Month"));
        AMD36Month = Double.parseDouble(request.getParameter("EUR36Month"));
    }

    private void getCurrancyFullDetail() throws SQLException {
        currancyList = currancyDao.getAllCurrancyList();
    }

    private void getBankFullDetail() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
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




