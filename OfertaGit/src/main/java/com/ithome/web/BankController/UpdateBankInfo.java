package com.ithome.web.BankController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
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

@WebServlet("/UpdateBankInfo")
public class UpdateBankInfo extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private int pinCode = 0;
    private List<Banks> bankList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    private int bankId = 0;
    private String updateBankName = null;
    private String bankAddress = null;
    private String bankPhoneNumber = null;
    private String bankType = null;
    private String bankHQ = null;
    private int  bankBranches = 0;
    private String bankSwiftCode = null;
    private String bankWebSite = null;
    private Banks banks;
    private String bankImage =null;
    private int newBankId = 0;
    private String bankFaxNumber = null;
    private int bankAtmQuantity = 0;
    private String bankEmailAddress = null;
    private int bankCode = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateBankInfo(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateBankInfo(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateBankInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        updateAdminInfoInData(prepaireBankInfoForData(),request,response,bankId);

    }

    private void updateAdminInfoInData(Banks prepaireBankInfoForData, HttpServletRequest request, HttpServletResponse response, int bankId) throws ServletException, IOException, SQLException {
        int row = banksDaoController.UpdateBank(prepaireBankInfoForData,bankId);
        if(row ==0){
            String message = "Ինչ-որ սխալ տեղի ունեցավ, կրկին փորձեք: ";
            getBankInfoById(bankId);
            setRequestToPage(request);
            gotoPage(request,response, message);

        }else{
            String message = "Դուք հաջողությամբ թարմացնում եք կառավարիչի տվյալները: ";
            getBankInfoById(bankId);
            setRequestToPage(request);
            gotoPage(request,response, message);
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);

        request.getRequestDispatcher("/WEB-INF/UpdateBanks.jsp").forward(request, response);
    }

    private void getBankInfoById(int bankId) throws SQLException {
        bankList = banksDaoController.getBankInfoById(bankId);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("BankFullList", bankList);
        System.out.println();

    }

    private Banks prepaireBankInfoForData() {
        return new Banks(updateBankName,bankCode,bankAddress,bankPhoneNumber,bankFaxNumber,bankHQ,bankBranches,bankAtmQuantity,bankWebSite,bankEmailAddress,bankSwiftCode);
    }

    private void getParameters(HttpServletRequest request) {
        bankId = Integer.parseInt(request.getParameter("bankId"));
        bankCode = Integer.parseInt(request.getParameter("bankCode"));
        updateBankName = request.getParameter("bankName");
        bankAddress = request.getParameter("bankAddress");
        bankPhoneNumber = request.getParameter("bankPhoneNumber");
        bankHQ = request.getParameter("bankHQ");
        bankBranches = Integer.parseInt(request.getParameter("bankBranches"));
        bankSwiftCode = request.getParameter("bankSwiftCode");
        bankWebSite = request.getParameter("bankWebSite");
        bankFaxNumber = request.getParameter("bankFaxNumber");
        bankAtmQuantity = Integer.parseInt(request.getParameter("bankAtmQuantity"));
        bankEmailAddress = request.getParameter("bankEmailAddress");
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
     * Session of uadmin if no session true send to login page else get the session key
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


