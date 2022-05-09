package com.ithome.web.AgricultureController;

import com.ithome.web.AdminDao.AgriculturalCreditDao;
import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CommentDaoComtroller;
import com.ithome.web.AdminDao.ProductNameDaoController;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/AddNewAgricultureToData")
public class AddNewAgricultureToData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    private List<Banks> banksList = new ArrayList<>();
    private AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();
    private ProductNameDaoController productNameDaoController = new ProductNameDaoController();
    private ProductName productName = new ProductName();

    private int productcode = 0;
    private String BankName = null;
    private int Bankid = 0;
    private String bankWebSite = null;
    private String ProductName = null;
    private String ProductNameEn = null;
    private String ProductNameRu = null;
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
    private String bankImage=null;
    private int bankCode =0;
    private String bankLink =null;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewAgricultureToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewAgricultureToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewAgricultureToData(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        getBankInfoFromBankname(BankName);
        createProductCode();
        checkIfProductNameAdded(createProductNameInData(),request,response);
        checkIfAGAdded(createNewAGInData(), request, response);
    }

    private void createProductCode() {
        Random random = new Random();
        bankCode =getBankCode();
        productcode = Integer.parseInt("" + bankCode + (random.nextInt(10000)+1000));
    }

    private int getBankCode() {
        for (int i = 0; i <banksList.size() ; i++) {
            bankCode=  banksList.get(i).getBankCode();
        }
        return bankCode;
    }

    private void checkIfProductNameAdded(int productNameInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(productNameInData == 0){
            String message = "Ինչ-որ սխալ տեղի ունեցավ անվանման մեջ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToPage(request, response, message);
        }
    }

    private int createProductNameInData() throws SQLException {
        return productNameDaoController.AddNewProductName(PrepaireProductNames());
    }

    private ProductName PrepaireProductNames() {
        return new ProductName(productcode,ProductName,ProductNameEn,ProductNameRu);
    }

    private void checkIfAGAdded(int newAGInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (newAGInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToPage(request, response, message);
        } else {

            setRequestToPage(request);
            gotoNextPage(request,response);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/AddNewComments.jsp").forward(request, response);
    }

    private void goBackToPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddNewAgriculture.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("productCode",productcode);

    }

    private void getBankInfoFromBankname(String bankName) throws SQLException {
        banksList = banksDaoController.getBankFullInfoByName(bankName);
        for (int i = 0; i <banksList.size() ; i++) {
            Bankid = banksList.get(i).getBankId();
            bankWebSite = banksList.get(i).getBankWebSite();
        }
    }

    private int createNewAGInData() throws SQLException {
        return agriculturalCreditDao.AddNewAgriculturalCredit(createDaoToDatabase());
    }

    private AgriculturalCredit createDaoToDatabase() {
        return new AgriculturalCredit(productcode,Bankid,BankName,bankWebSite,ACMinAge,ACMaxAge,ProductName,ACMinAmount,ACMaxAmount,ACMinLoan,ACMacLoan,ACPerFactual,ACMinPeriodMonth,ACMaxPeriodMonth,
                ACMinServiceFee,ACMaxServiceFee,currancy,bankLink);
    }

    private void getParameters(HttpServletRequest request) {
      /*  productcode = Integer.parseInt(request.getParameter("ProductCode"));*/
        BankName = request.getParameter("BankName");
        ProductName = request.getParameter("ProductName");
        ProductNameEn = request.getParameter("ProductNameEng");
        ProductNameRu = request.getParameter("ProductNameRus");
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

        bankLink = request.getParameter("BankLink");

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
