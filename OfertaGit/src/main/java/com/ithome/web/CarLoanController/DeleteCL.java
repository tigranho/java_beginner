package com.ithome.web.CarLoanController;

import com.ithome.web.AdminDao.AgriculturalCreditDao;
import com.ithome.web.AdminDao.CarLoanDao;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.AgriculturalCredit;
import com.ithome.web.Bean.CarLoans;
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

@WebServlet("/DeleteCL")
public class DeleteCL extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private int agId = 0;
    private List<CarLoans> carLoansList = new ArrayList<>();
    private CarLoanDao carLoanDao = new CarLoanDao();
    private int deleteCl =0;
    private int CLId = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteCL(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteCL(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCL(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getAdminInfo(request,response);
        getParameters(request);
        DeleteAgById(agId,request,response);
    }

    private void DeleteAgById(int agId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        deleteCl = carLoanDao.DeleteClById(CLId);
        if(deleteCl > 0) {
            getAllCarLoanDetails();
            setRequestToPage(request);
            gotoSamePage(request,response);
        }else {
            getAllCarLoanDetails();
            setRequestToPage(request);
            gotoSamePage(request,response);
        }
    }

    private void gotoSamePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ShowAllCL.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("carLoansList", carLoansList);
    }

    private void getAllCarLoanDetails() throws SQLException {
        carLoansList = carLoanDao.getAllCarLoans();
    }

    private void getParameters(HttpServletRequest request) {
        CLId = Integer.parseInt(request.getParameter("CLId"));
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


