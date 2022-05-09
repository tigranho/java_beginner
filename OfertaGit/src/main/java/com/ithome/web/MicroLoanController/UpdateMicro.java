package com.ithome.web.MicroLoanController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CurrancyDao;
import com.ithome.web.AdminDao.MicroLoanDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.Currancy;
import com.ithome.web.Bean.MicroLoans;
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

@WebServlet("/UpdateMicro")
public class UpdateMicro extends HttpServlet {

    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    List<Currancy> currancyList = new ArrayList<>();
    private CurrancyDao currancyDao = new CurrancyDao();
    private int MLId = 0;
    private List<MicroLoans> microLoansList = new ArrayList<>();
    private MicroLoanDaoController microLoanDaoController = new MicroLoanDaoController();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateMicro(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateMicro(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateMicro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getAdminInfo(request,response);
        getParameters(request);
        getBankFullDetails();
        getCurrancyFullDetail();
        getMicroDetailById(MLId);
        setRequestToPage(request);
        gotoUpdateMicroPage(request,response);
    }

    private void gotoUpdateMicroPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/UpdateMicro.jsp").forward(request, response);
    }


    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("BankFullList", banksList);
        request.setAttribute("CurrancyListFullList", currancyList);
        request.setAttribute("microLoansList", microLoansList);
    }


    private void getMicroDetailById(int mlId) throws SQLException {
        microLoansList = microLoanDaoController.getMicroLoanssById(mlId);
    }

    private void getParameters(HttpServletRequest request) {
        MLId = Integer.parseInt(request.getParameter("MLId"));
    }

    private void getBankFullDetails() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
    }

    private void getCurrancyFullDetail() throws SQLException {
        currancyList = currancyDao.getAllCurrancyList();
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


