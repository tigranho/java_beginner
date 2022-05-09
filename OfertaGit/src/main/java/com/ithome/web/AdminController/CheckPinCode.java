package com.ithome.web.AdminController;

import com.ithome.web.Bean.Admin;
import com.ithome.web.Helpers.AdminChecker;
import com.ithome.web.Helpers.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CheckPinCode")
public class CheckPinCode extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private int pinCode =0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkPinCode(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        checkPinCode(request,response);
    }

    private void checkPinCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        sessionControlling(request,response);
        getParameters(request);
        getAdminInfo(request,response);
        checkThePinCode(pinCode,request,response);
    }

    private void checkThePinCode(int pinCode,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(adminChecker.checkTheCode(pinCode,adminId)) {
            setRequestToPage(request);
            gotoPage(request,response);
        }else{
            setRequestToPage(request);
            String message = "Ձեր PIN կոդը սխալ է, խնդրում ենք կրկին փորձել\n";
            returnToSamePage(request,response,message);
        }
    }

    /**
     * return to same page
     * @param request
     * @param response
     * @param message
     */
    private void returnToSamePage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/updateAdmin.jsp").forward(request, response);
    }

    /**
     * send to page
     * @param request
     * @param response
     */
    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/updateAdminInfo.jsp").forward(request, response);
    }

    /**
     * prepaire requests to the page
     * @param request
     */
    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
    }

    /**
     * getparameters
     * @param request
     */
    private void getParameters(HttpServletRequest request) {
        pinCode = Integer.parseInt(request.getParameter("adminPinCode"));
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
     * Fill admin in list with the specific id
     * @param adminid
     */
    private void getFullAdminList(int adminid) {
        adminList = adminChecker.getAllInfoofAdmin(adminid);
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
