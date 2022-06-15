package com.ithome.web.MortgageController;

import com.ithome.web.AdminDao.AgriculturalCreditDao;
import com.ithome.web.AdminDao.MortgageDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.AgriculturalCredit;
import com.ithome.web.Bean.Mortgage;
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

@WebServlet("/DeleteMortgage")
public class DeleteMortgage extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private List<Mortgage> mortgageList = new ArrayList<>();
    private MortgageDaoController mortgageDaoController = new MortgageDaoController();
    private Mortgage mortgage = new Mortgage();
    private int MId =0;
    private int deleteMortgage =0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteMortgage(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteMortgage(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteMortgage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        DeleteMortgageById(MId, request, response);
    }

    private void DeleteMortgageById(int clId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        deleteMortgage = mortgageDaoController.DeleteMortgageById(clId);
        if(deleteMortgage > 0) {
            getAllMortgageDetail();
            setRequestToPage(request);
            gotoSamePage(request,response);
        }else {
            getAllMortgageDetail();
            setRequestToPage(request);
            gotoSamePage(request,response);
        }
    }

    private void gotoSamePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ShowAllMortgage.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("MortgageList", mortgageList);
    }

    private void getAllMortgageDetail() throws SQLException {
        mortgageList = mortgageDaoController.getAllMortage();
    }

    private void getParameters(HttpServletRequest request)
    {
        MId = Integer.parseInt(request.getParameter("MId"));
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


