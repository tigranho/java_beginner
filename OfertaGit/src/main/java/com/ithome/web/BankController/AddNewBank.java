package com.ithome.web.BankController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CurrancyDao;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.Currancy;
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

@WebServlet("/AddNewBank")
public class AddNewBank extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private List<Banks> banksList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    private List<Currancy> currancyList = new ArrayList<>();
    private CurrancyDao currancyDao = new CurrancyDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewBank(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewBank(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewBank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getAdminInfo(request,response);
        getFullBankDetail();
        getAllCurrancy();
        setRequestToPage(request);
        gotoPage(request,response);
    }

    private void getAllCurrancy() throws SQLException {
        currancyList = currancyDao.getAllCurrancyList();
    }

    private void getFullBankDetail() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
    }


    /**
     * prepaire requests to the page
     * @param request
     */
    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("BankFullList", banksList);
        request.setAttribute("CurrancyListFullList", currancyList);
    }


    /**
     * response of the servlet to the page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/AddNewBank.jsp").forward(request, response);
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
