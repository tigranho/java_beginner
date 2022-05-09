package com.ithome.web.ConsumerCreditController;

import com.ithome.web.AdminDao.*;
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
import java.util.Random;

@WebServlet("/AddNewConsumerCreditToData")
public class AddNewConsumerCreditToData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    private ConsumerCredit consumerCredit = new ConsumerCredit();
    private ConsumerCreditDaoController consumerCreditDaoController = new ConsumerCreditDaoController();
    private ProductNameDaoController productNameDaoController = new ProductNameDaoController();
    private ProductName productName = new ProductName();

    private int ProductCode = 0;
    private String BankName = null;
    private String ProductName = null;
    private String ProductNameEn = null;
    private String ProductNameRu = null;
    private int CoCMinAmount = 0;
    private int CoCMaxAmount = 0;
    private double CoCMinCredit = 0;
    private double CoCMaxCredit = 0;
    private double CoCPerFactual = 0;
    private String currancy = null;
    private int CoCMinPeriodMonths = 0;
    private int CoCMaxPeriodMonth = 0;
    private int CoCMinServiceFee = 0;
    private int CoCMaxServiceFee = 0;
    private int CoCMinAge = 0;
    private int CoCMaxAge = 0;
    private int Bankid= 0;
    private String bankWebSite =null;
    private String BankLink =null;
    private int bankCode =0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewConsumerCreditToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewConsumerCreditToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewConsumerCreditToData(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        getBankInfoFromBankname(BankName);
        createProductCode();
        checkIfProductNameAdded(createProductNameInData(),request,response);
        checkIfConsumerCreditAdded(newConsumerCreditInData(),request,response);
    }

    private void checkIfProductNameAdded(int productNameInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(productNameInData == 0){
            String message = "Ինչ-որ սխալ տեղի ունեցավ անվանման մեջ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToAddCreditConsumerPage(request, response, message);
        }
    }

    private void createProductCode() {
        Random random = new Random();
        bankCode =getBankCode();
        ProductCode = Integer.parseInt("" + bankCode + (random.nextInt(10000)+1000));
    }

    private int getBankCode() {
        for (int i = 0; i <banksList.size() ; i++) {
            bankCode=  banksList.get(i).getBankCode();
        }
        return bankCode;
    }

    private int createProductNameInData() throws SQLException {
        return productNameDaoController.AddNewProductName(PrepaireProductNames());
    }

    private com.ithome.web.Bean.ProductName PrepaireProductNames() {
        return new ProductName(ProductCode,ProductName,ProductNameEn,ProductNameRu);
    }


    private int newConsumerCreditInData() throws SQLException {
        return consumerCreditDaoController.AddNewConsumerCredit(createConsumerCreditData());
    }

    private ConsumerCredit createConsumerCreditData() {
        return new ConsumerCredit(ProductCode,Bankid,BankName,bankWebSite,CoCMinAge,CoCMaxAge,currancy,ProductName,
                CoCMinAmount,CoCMaxAmount,CoCMinCredit,CoCMaxCredit,CoCPerFactual,CoCMinPeriodMonths,CoCMaxPeriodMonth,CoCMaxServiceFee,CoCMinServiceFee,BankLink);

    }

    private void checkIfConsumerCreditAdded(int newConsumerCreditInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (newConsumerCreditInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToAddCreditConsumerPage(request, response, message);
        } else {

            setRequestToPage(request);
            gotoNextPage(request,response);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/AddNewCCComments.jsp").forward(request, response);
    }

    private void goBackToAddCreditConsumerPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddNewConsumerCredit.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("productcode", ProductCode);
    }

    private void getBankInfoFromBankname(String bankName) throws SQLException {
        banksList = banksDaoController.getBankFullInfoByName(bankName);
        for (int i = 0; i < banksList.size(); i++) {
            Bankid = banksList.get(i).getBankId();
            bankWebSite = banksList.get(i).getBankWebSite();
        }
    }


    private void getParameters(HttpServletRequest request) {
       /* ProductCode = Integer.parseInt(request.getParameter("ProductCode"));*/
        BankName = request.getParameter("BankName");
        ProductName = request.getParameter("ProductName");
        ProductNameEn = request.getParameter("ProductNameEng");
        ProductNameRu = request.getParameter("ProductNameRus");
        CoCMinAmount = Integer.parseInt(request.getParameter("CoCMinAmount"));
        CoCMaxAmount = Integer.parseInt(request.getParameter("CoCMaxAmount"));
        CoCMinCredit = Double.parseDouble(request.getParameter("CCMinLoan"));
        CoCMaxCredit = Double.parseDouble(request.getParameter("CCMaxLoan"));
        CoCPerFactual = Double.parseDouble(request.getParameter("CCFactual"));
        currancy = request.getParameter("currancy");
        CoCMinPeriodMonths = Integer.parseInt(request.getParameter("CoCMinPeriodMonths"));
        CoCMaxPeriodMonth = Integer.parseInt(request.getParameter("CoCMaxPeriodMonth"));
        CoCMinServiceFee = Integer.parseInt(request.getParameter("CoCMinServiceFee"));
        CoCMaxServiceFee = Integer.parseInt(request.getParameter("CoCMaxServiceFee"));
        CoCMinAge = Integer.parseInt(request.getParameter("CoCMinAge"));
        CoCMaxAge = Integer.parseInt(request.getParameter("CoCMaxAge"));
        BankLink = request.getParameter("BankLink");
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

