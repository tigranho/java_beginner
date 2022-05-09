package com.ithome.web.AgricultureController;

import com.ithome.web.AdminDao.AgriculturalCreditDao;
import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CurrancyDao;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.AgriculturalCredit;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.Currancy;
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

@WebServlet("/UpdateAgDetailsToData")
public class UpdateAgDetailsToData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    List<Currancy> currancyList = new ArrayList<>();
    private CurrancyDao currancyDao = new CurrancyDao();
    private int agId = 0;
    private List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
    private AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();
    private int ACId =0;


    private int productcode = 0;
    private String BankName = null;
    private int Bankid = 0;
    private String bankWebSite = null;
    private String ProductName = null;
    private int ACMinAmount = 0;
    private int ACMaxAmount = 0;
    private double ACMinLoan = 0;
    private double ACMacLoan = 0;
    private double ACPerFactual = 0;
    private int ACMinPeriodMonth = 0;
    private int ACMaxPeriodMonth = 0;
    private int ACMinServiceFee = 0;
    private String currancy = null;
    private int ACMaxServiceFee =0;
    private int ACMinAge =0;
    private int ACMaxAge =0;
    private String bankImage =null;
    private String BankLink =null;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            updateAgDetailsToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateAgDetailsToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAgDetailsToData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getAdminInfo(request,response);
        getParameters(request);
        getBankDetailByBankName(BankName);
        getBankFullDetail();
        getCurrancyFullDetail();
        updateAgriculturCreditInData(createNewAGInData(),request,response,ACId);
    }

    private void getBankFullDetail() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
    }

    private void getCurrancyFullDetail() throws SQLException {
        currancyList = currancyDao.getAllCurrancyList();
    }

    private void updateAgriculturCreditInData(int newAGInData, HttpServletRequest request, HttpServletResponse response, int acId) throws ServletException, IOException, SQLException {
        if (newAGInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            getAgDetailById(ACId);
            setRequestToAgUpdatePage(request);
            goBackToErrorPage(request, response, message);
        } else {
            String message = "Հաջողությամբ ավելացրեց գյուղատնտեսական նոր վարկ";
            getAgDetailById(ACId);
            setRequestToAgUpdatePage(request);
            gotoNextPage(request,response,message);
        }
    }

    private void goBackToErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/UpdateAg.jsp").forward(request, response);
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void getAgDetailById(int acId) throws SQLException {
        agriculturalCreditList = agriculturalCreditDao.getAgriculturalCreditById(acId);
    }

    private void setRequestToAgUpdatePage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("BankFullList", banksList);
        request.setAttribute("CurrancyListFullList", currancyList);
        request.setAttribute("AgriculturalCreditList", agriculturalCreditList);
    }


    private void getBankDetailByBankName(String BankName) throws SQLException {
        banksList = banksDaoController.getBankFullInfoByName(BankName);
        for (int i = 0; i <banksList.size() ; i++) {
            bankWebSite= banksList.get(i).getBankWebSite();
            Bankid = banksList.get(i).getBankId();
        }
    }

    private int createNewAGInData() throws SQLException {
        return agriculturalCreditDao.updateAgInData(prepaireAgInfoForData(),ACId);
    }

    private AgriculturalCredit  prepaireAgInfoForData() {
        return new AgriculturalCredit(productcode,Bankid,BankName,bankWebSite,ACMinAge,ACMaxAge,ProductName,ACMinAmount,ACMaxAmount,ACMinLoan,ACMacLoan,ACPerFactual,ACMinPeriodMonth,ACMaxPeriodMonth,
                ACMinServiceFee,ACMaxServiceFee,currancy,BankLink );
    }

    private void getParameters(HttpServletRequest request) {
        productcode = Integer.parseInt(request.getParameter("ProductCode"));
        BankName = request.getParameter("BankName");
        ProductName = request.getParameter("ProductName");
        ACMinAmount = Integer.parseInt(request.getParameter("ACMinAmount"));
        ACMaxAmount = Integer.parseInt(request.getParameter("ACMaxAmount"));
        ACMinLoan = Double.parseDouble(request.getParameter("ACMinLoan"));
        ACMacLoan = Double.parseDouble(request.getParameter("ACMacLoan"));
        ACPerFactual = Double.parseDouble(request.getParameter("ACPerFactual"));
        ACMinPeriodMonth = Integer.parseInt(request.getParameter("ACMinPeriodMonth"));
        ACMaxPeriodMonth = Integer.parseInt(request.getParameter("ACMaxPeriodMonth"));
        ACMinServiceFee = Integer.parseInt(request.getParameter("ACMinServiceFee"));
        currancy = request.getParameter("currancy");
        ACMaxServiceFee = Integer.parseInt(request.getParameter("ACMaxServiceFee"));
        ACMinAge = Integer.parseInt(request.getParameter("ACMinAge"));
        ACMaxAge = Integer.parseInt(request.getParameter("ACMaxAge"));
        ACId = Integer.parseInt(request.getParameter("ACId"));
        BankLink = request.getParameter("BankLink");
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


