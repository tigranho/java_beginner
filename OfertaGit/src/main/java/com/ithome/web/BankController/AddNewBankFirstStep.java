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

@WebServlet("/AddNewBankFirstStep")
public class AddNewBankFirstStep extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private int pinCode = 0;
    private Banks banks;
    private BanksDaoController banksDaoController = new BanksDaoController();
    private String bankName = null;
    private int bankCode = 0;
    private String bankAddress = null;
    private String bankPhoneNumber = null;
    private String bankType = null;
    private String bankHQ = null;
    private int bankBranches = 0;
    private String bankSwiftCode = null;
    private String bankWebSite = null;
    private List<Banks> banksList = new ArrayList<>();
    private int newBankId = 0;
    private String bankFaxNumber = null;
    private int bankAtmQuantity = 0;
    private String bankEmailAddress = null;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewBankFirstStep(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewBankFirstStep(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewBankFirstStep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        checkIfBankAdded(createNewbankInData(), request, response);

    }

    private void checkIfBankAdded(int newbankInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (newbankInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToPage(request, response, message);
        } else {
            getNewBankFullInfo();
            getnewBankId();
            setRequestToPage(request);
            gotoNextStepPage(request, response);
        }
    }

    private void getnewBankId() {
        int[] BankArray = new int[banksList.size()];
        for (int i = 0; i < banksList.size(); i++) {
            BankArray[i] = banksList.get(i).getBankId();
        }
        newBankId = BankArray[BankArray.length - 1];
    }

    private void getNewBankFullInfo() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
    }

    private void gotoNextStepPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/AddBankSecondStep.jsp").forward(request, response);
    }

    private int createNewbankInData() throws SQLException {
        return banksDaoController.AddBank(getNewBankDetails());
    }

    private Banks getNewBankDetails() {
        return new Banks(bankName,bankCode,bankAddress,bankPhoneNumber,bankFaxNumber,bankHQ,bankBranches,bankAtmQuantity,bankWebSite,bankEmailAddress,bankSwiftCode);
    }

    /**
     * getting parameters from web
     *
     * @param request
     */

    private void getParameters(HttpServletRequest request) {
        bankName = request.getParameter("bankName");
        bankAddress = request.getParameter("bankAddress");
        bankPhoneNumber = request.getParameter("bankPhoneNumber");
        bankType = request.getParameter("bankType");
        bankHQ = request.getParameter("bankHQ");
        bankBranches = Integer.parseInt(request.getParameter("bankBranches"));
        bankSwiftCode = request.getParameter("bankSwiftCode");
        bankWebSite = request.getParameter("bankWebSite");
        bankFaxNumber = request.getParameter("bankFaxNumber");
        bankAtmQuantity = Integer.parseInt(request.getParameter("bankAtmQuantity"));
        bankEmailAddress = request.getParameter("bankEmailAddress");
        bankCode = Integer.parseInt(request.getParameter("bankCode"));

    }


    /**
     * prepaire requests to the page
     *
     * @param request
     */
    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("newBankId", newBankId);
    }


    /**
     * response of the servlet to the page
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void goBackToPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddNewBank.jsp").forward(request, response);
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
