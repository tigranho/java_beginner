package com.ithome.web.DepositController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CommentDaoComtroller;
import com.ithome.web.AdminDao.DepositDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.Comments;
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
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddAmdMonths")
public class AddAmdMonths extends HttpServlet {

    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private DepositDaoController depositDaoController = new DepositDaoController();

    private int productcode = 0;
    private double AMD1Month = 0.0;
    private double AMD3Month = 0.0;
    private double AMD6Month = 0.0;
    private double AMD9Month = 0.0;
    private double AMD12Month = 0.0;
    private double AMD18Month = 0.0;
    private double AMD24Month = 0.0;
    private double AMD36Month = 0.0;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addAmdMonths(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addAmdMonths(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addAmdMonths(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);

        getAdminInfo(request, response);
        getParameters(request);
        checkIfAmdMonthsAdded(creatNewCommentInData(), request, response);
    }

    private int creatNewCommentInData() throws SQLException {
        return depositDaoController.AddAmdMonths(createCommentDaoToData(), productcode);
    }

    private Deposit createCommentDaoToData() {
        return new Deposit(AMD1Month, AMD3Month, AMD6Month, AMD9Month, AMD12Month, AMD18Month, AMD24Month, AMD36Month);
    }

    private void checkIfAmdMonthsAdded(int creatNewCommentInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (creatNewCommentInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToPage(request, response, message);
        } else {
            String message = "";
            setRequestToPage(request);
            gotoNextPage(request, response, message);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddDepositMonths2.jsp").forward(request, response);
    }

    private void goBackToPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddDepositMonths.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("productcode", productcode);

    }

    private void getParameters(HttpServletRequest request) {
        productcode = Integer.parseInt(request.getParameter("productcode"));

        String one = request.getParameter("AMD1Month");
        if(one.equals("0.0")){
            AMD1Month=0.0;
        }else {
            AMD1Month = Double.parseDouble(String.valueOf(one));
        }
        String two = request.getParameter("AMD3Month");
        if(two.equals("0.0")){
            AMD3Month=0.0;
        }else {
            AMD3Month = Double.parseDouble(String.valueOf(two));
        }

        String three = request.getParameter("AMD6Month");
        if(three.equals("0.0")){
            AMD6Month=0.0;
        }else {
            AMD6Month = Double.parseDouble(String.valueOf(three));
        }

        String forth = request.getParameter("AMD9Month");
        if(forth.equals("0.0")){
            AMD9Month=0.0;
        }else {
            AMD9Month = Double.parseDouble(String.valueOf(forth));
        }


        String fifth = request.getParameter("AMD12Month");
        if(fifth.equals("0.0")){
            AMD12Month=0.0;
        }else {
            AMD12Month = Double.parseDouble(String.valueOf(fifth));
        }

        String six = request.getParameter("AMD18Month");
        if(six.equals("0.0")){
            AMD18Month=0.0;
        }else {
            AMD18Month = Double.parseDouble(String.valueOf(six));
        }

        String seven = request.getParameter("AMD24Month");
        if(seven.equals("0.0")){
            AMD24Month=0.0;
        }else {
            AMD24Month = Double.parseDouble(String.valueOf(seven));
        }

        String eigth = request.getParameter("AMD36Month");
        if(eigth.equals("0.0")){
            AMD36Month=0.0;
        }else {
            AMD36Month = Double.parseDouble(String.valueOf(eigth));
        }

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
