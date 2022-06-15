package com.ithome.web.DropDownController;

import com.ithome.web.AdminDao.AgriculturalCreditDao;
import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CurrancyDao;
import com.ithome.web.AdminDao.DropDownController;
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

@WebServlet("/UpdateRangeController")
public class UpdateRangeController extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    private DropDownController dropDownController = new DropDownController();
    private List<DropDowns> dropDownsList = new ArrayList<>();

    private String minAmount = null;
    private String maxAmount = null;
    private String Steps = null;
    private int Id = 0;
    private String Position = null;
    private String currancy =null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateRangeController(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateRangeController(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateRangeController(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        getBankFullDetail();
        updateRange(CreateData(),request,response,Id);
    }

    private void updateRange(int createData, HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException, SQLException {
        if (createData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            getHomePageRangebyPosition();
            setRequestToPage(request);
            goToPage(request, response, message);
        } else {
            String message = "Հաջողությամբ ավելացրեց գյուղատնտեսական նոր վարկ";
            getHomePageRangebyPosition();
            setRequestToPage(request);
            goToPage(request,response,message);
        }
    }

    private void goToPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/PageRange.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("dropDownsList", dropDownsList);
        request.setAttribute("position", Position);
        request.setAttribute("banksList", banksList);
    }

    private void getHomePageRangebyPosition() throws SQLException {
        dropDownsList = dropDownController.getDropDownsByPosition(Position);
    }

    private int CreateData() throws SQLException {
        return dropDownController.UpdateDropDown(PrepaireForData(),Id);
    }

    private DropDowns PrepaireForData() {
        return new DropDowns(currancy,minAmount,maxAmount,Steps,Position);
    }

    private void getParameters(HttpServletRequest request) {
        minAmount = request.getParameter("minAmount");
        maxAmount = request.getParameter("maxAmount");
        Steps = request.getParameter("Steps");
        Id = Integer.parseInt(request.getParameter("Id"));
        Position = request.getParameter("Position");
        currancy = request.getParameter("currancy");
    }

    private void getBankFullDetail() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
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



