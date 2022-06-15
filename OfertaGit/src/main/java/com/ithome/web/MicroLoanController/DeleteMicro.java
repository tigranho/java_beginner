package com.ithome.web.MicroLoanController;

import com.ithome.web.AdminDao.AgriculturalCreditDao;
import com.ithome.web.AdminDao.MicroLoanDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.AgriculturalCredit;
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

@WebServlet("/DeleteMicro")
public class DeleteMicro extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private int MLId = 0;
    private int deleteMicro =0;
    private MicroLoanDaoController microLoanDaoController = new MicroLoanDaoController();
    private List<MicroLoans> microLoansList = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteMicro(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteMicro(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteMicro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getAdminInfo(request,response);
        getParameters(request);
        DeleteMicroById(MLId,request,response);
    }

    private void DeleteMicroById(int mlId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        deleteMicro = microLoanDaoController.DeleteMicro(mlId);
        if(deleteMicro > 0) {
            getAllMicroDetail();
            setRequestToPage(request);
            gotoSamePage(request,response);
        }else {
            getAllMicroDetail();
            setRequestToPage(request);
            gotoSamePage(request,response);
        }
    }

    private void gotoSamePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ShowAllMicro.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("microLoansList", microLoansList);
    }

    private void getAllMicroDetail() throws SQLException {
        microLoansList = microLoanDaoController.getAllMicroLoans();
    }

    private void getParameters(HttpServletRequest request) {
        MLId = Integer.parseInt(request.getParameter("MLId"));
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


