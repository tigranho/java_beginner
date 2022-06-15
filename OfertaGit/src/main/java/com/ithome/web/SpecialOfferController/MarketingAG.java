package com.ithome.web.SpecialOfferController;

import com.ithome.web.AdminDao.AgriculturalCreditDao;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.AgriculturalCredit;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Helpers.AdminChecker;
import com.ithome.web.Helpers.GetBankFullInfo;
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

@WebServlet("/MarketingAG")
public class MarketingAG extends HttpServlet {

    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private int bankId =0;
    private AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();
    private List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
    private GetBankFullInfo bf = new GetBankFullInfo();
    private List<Banks> banksList = new ArrayList<>();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            getBankAgricultureLoanSpecialOffer(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            getBankAgricultureLoanSpecialOffer(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getBankAgricultureLoanSpecialOffer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getBankFullInfo();
        getAllAgDetail();
        setRequestToPage(request);
        gotoPage(request,response);
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.getRequestDispatcher("/WEB-INF/Marketing.jsp").forward(request,response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("ListMarketing", agriculturalCreditList);
        request.setAttribute("banksList", banksList);
        request.setAttribute("PageName","Agriculture");
    }
    private void getBankFullInfo() throws SQLException {
        banksList = bf.contactBankDetail();
    }


    private void getAllAgDetail() throws SQLException {
        agriculturalCreditList = agriculturalCreditDao.getAllAgriculturalCreditList();
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
