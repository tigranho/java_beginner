package com.ithome.web.MortgageController;

import com.ithome.web.AdminDao.*;
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
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UpdateMortgage")
public class UpdateMortgage extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    List<Currancy> currancyList = new ArrayList<>();
    private CurrancyDao currancyDao = new CurrancyDao();
    private List<Mortgage> mortgageList = new ArrayList<>();
    private MortgageDaoController mortgageDaoController = new MortgageDaoController();
    private List<RePaymentOptions> rePaymentOptionsList = new ArrayList<>();
    private PrepaimentOptionDao prepaimentOptionDao = new PrepaimentOptionDao();
    private int MId =0;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateMortgage(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateMortgage(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateMortgage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getAdminInfo(request,response);
        getParameters(request);
        getBankFullDetails();
        getCurrancyFullDetail();
        getRePaimentOption();
        getMortgageDetailById(MId);
        setRequestToPage(request);
        gotoUpdateMortgagePage(request,response);
    }

    private void getRePaimentOption() throws SQLException {
        rePaymentOptionsList = prepaimentOptionDao.GetAllRePaymentOptionArmenian();

    }

    private void gotoUpdateMortgagePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/UpdateMortgage.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("BankFullList", banksList);
        request.setAttribute("CurrancyListFullList", currancyList);
        request.setAttribute("MortgageList", mortgageList);
        request.setAttribute("RePaymentOptionsList", rePaymentOptionsList);
    }


    private void getMortgageDetailById(int clId) throws SQLException {
        mortgageList = mortgageDaoController.getMortagesById(MId);
    }

    private void getBankFullDetails() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
    }
    private void getCurrancyFullDetail() throws SQLException {
        currancyList = currancyDao.getAllCurrancyList();
    }


    private void getParameters(HttpServletRequest request) {
        MId = Integer.parseInt(request.getParameter("MId"));
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


