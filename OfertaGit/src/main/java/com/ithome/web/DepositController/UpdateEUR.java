package com.ithome.web.DepositController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CurrancyDao;
import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.AdminDao.TimeLineDao;
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

@WebServlet("/UpdateEUR")
public class UpdateEUR extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    List<Currancy> currancyList = new ArrayList<>();
    private CurrancyDao currancyDao = new CurrancyDao();
    private int DId=0;
    private List<Deposit> depositList = new ArrayList<>();
    private List<TimeLine> timeLineList = new ArrayList<>();
    private TimeLineDao timeLineDao = new TimeLineDao();
    private DepositDaoController depositDaoController = new DepositDaoController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateEUR(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateEUR(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateEUR(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getAdminInfo(request,response);
        getParameters(request);
        getBankFullDetails();
        getCurrancyFullDetail();
        getTimeLineFullDetail();
        getDepositDetailById(DId);
        setRequestToPage(request);
        gotoUpdateDepositPage(request,response);

    }

    private void getDepositDetailById(int dId) throws SQLException {
        depositList = depositDaoController.getDepositById(dId);
    }

    private void gotoUpdateDepositPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/UpdateEUR.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("BankFullList", banksList);
        request.setAttribute("CurrancyListFullList", currancyList);
        request.setAttribute("DepositList", depositList);
        request.setAttribute("timeLineList", timeLineList);
    }


    private void getTimeLineFullDetail() throws SQLException {
        timeLineList = timeLineDao.getAllTimeLine();
    }


    private void getBankFullDetails() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
    }

    private void getCurrancyFullDetail() throws SQLException {
        currancyList = currancyDao.getAllCurrancyList();
    }


    private void getParameters(HttpServletRequest request) {
        DId = Integer.parseInt(request.getParameter("DId"));
    }


    /**
     * Controlling the session for admin using helper classes
     * @param request
     * @param response
     * @throws IOException
     */
    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        getSession(session, request, response );
    }

    /**
     * Fill admin in list with the specific id
     * @param adminid
     */
    private void getFullAdminList(int adminid) {
        adminList = adminChecker.getAllInfoofAdmin(adminid);
    }

    /**
     * get admin admin id by username from session
     * @param request
     * @param response
     */
    private void getAdminInfo(HttpServletRequest request, HttpServletResponse response) {
        adminId = adminChecker.getAdminId(username);
        getFullAdminList(adminId);
    }

    /**
     * Session of uadmin if no session true send to login page else get the seesion key
     * @param session
     * @param request
     * @param response
     * @throws IOException
     */
    private void getSession(HttpSession session , HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(checker.checkSession(request, response)){
            username = checker.requestSessionofAdmin(session);
        }else{
            response.sendRedirect("/admin/SignIn.jsp");
        }
    }
}
