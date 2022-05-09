package com.ithome.web.CarLoanController;

import com.ithome.web.AdminDao.AgriculturalCreditDao;
import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CarLoanDao;
import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.CarLoans;
import com.ithome.web.Bean.ProductName;
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

@WebServlet("/AddNewCarLoanToData")
public class AddNewCarLoanToData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    private CarLoanDao carLoanDao = new CarLoanDao();
    private CarLoans carLoans = new CarLoans();

    private int ProductCode = 0;
    private String ProductName = null;
    private String ProductNameEn = null;
    private String ProductNameRu = null;
    private int CLMinAmount = 0;
    private int CLMaxAmount = 0;
    private double CLMinLoan = 0;
    private double CLMacLoan = 0;
    private double CLPerFactual = 0;
    private int CLMinDownPayment = 0;
    private int CLMaxDownPayment = 0;
    private int CLMinPeriodMonths = 0;
    private int CLMaxPeriodMonth = 0;
    private int CLMinServiceFee = 0;
    private int ClMaxServiceFee = 0;
    private int ClMinAge = 0;
    private int ClMaxAge = 0;
    private String BankName = null;
    private String currancy = null;
    private int Bankid = 0;
    private String bankWebSite = null;
    private String BankLink = null;

    private ProductNameDaoController productNameDaoController = new ProductNameDaoController();
   private ProductName productName;
    private int bankCode =0;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewCarLoanToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewCarLoanToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewCarLoanToData(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        getBankInfoFromBankname(BankName);
        createProductCode();
        checkIfProductNameAdded(createProductNameInData(),request,response);
        checkIfCarLoanAdded(createNewCarLoanInData(), request, response);
    }

    private void checkIfProductNameAdded(int productNameInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(productNameInData == 0){
            String message = "Ինչ-որ սխալ տեղի ունեցավ անվանման մեջ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToAddCarLoanPage(request, response, message);
        }
    }

    private int createProductNameInData() throws SQLException {
        return productNameDaoController.AddNewProductName(PrepaireProductNames());
    }

    private ProductName PrepaireProductNames() {
        return new ProductName(ProductCode,ProductName,ProductNameEn,ProductNameRu);
    }

    private void checkIfCarLoanAdded(int newCarLoanInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (newCarLoanInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToAddCarLoanPage(request, response, message);
        } else {
            String message = "Տվյալները հաջողությամբ ավելացան";
            setRequestToPage(request);
            gotoNextPage(request,response);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/AddNewCarLoanComments.jsp").forward(request, response);
    }

    private void goBackToAddCarLoanPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddNewCarLoan.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("productcode", ProductCode);

    }

    private int createNewCarLoanInData() throws SQLException {
        return carLoanDao.AddNewCarsLoans(createDataToCarLoan());
    }

    private CarLoans createDataToCarLoan() {
        return new CarLoans(ProductCode,Bankid,BankName,bankWebSite,ClMinAge,ClMaxAge,currancy,ProductName,CLMinAmount,CLMaxAmount,CLMinLoan,CLMacLoan,CLPerFactual,CLMinDownPayment,CLMaxDownPayment,CLMinPeriodMonths,CLMaxPeriodMonth,CLMinServiceFee,ClMaxServiceFee,BankLink);
    }

    private void getBankInfoFromBankname(String bankName) throws SQLException {
        banksList = banksDaoController.getBankFullInfoByName(bankName);
        for (int i = 0; i < banksList.size(); i++) {
            Bankid = banksList.get(i).getBankId();
            bankWebSite = banksList.get(i).getBankWebSite();
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

    private void getParameters(HttpServletRequest request) {
       /* ProductCode = Integer.parseInt(request.getParameter("ProductCode"));*/
        BankName = request.getParameter("BankName");
        ProductName = request.getParameter("ProductName");
        ProductNameEn = request.getParameter("ProductNameEng");
        ProductNameRu = request.getParameter("ProductNameRus");
        CLMinAmount = Integer.parseInt(request.getParameter("CLMinAmount"));
        CLMaxAmount = Integer.parseInt(request.getParameter("CLMaxAmount"));

        CLMinLoan = Double.parseDouble(request.getParameter("CLMinLoan"));
        CLMacLoan = Double.parseDouble(request.getParameter("CLMacLoan"));
        CLPerFactual = Double.parseDouble(request.getParameter("CLPerFactual"));

        CLMinDownPayment = Integer.parseInt(request.getParameter("CLMinDownPayment"));
        CLMaxDownPayment = Integer.parseInt(request.getParameter("CLMaxDownPayment"));
        currancy = request.getParameter("currancy");
        CLMinPeriodMonths = Integer.parseInt(request.getParameter("CLMinPeriodMonths"));
        CLMaxPeriodMonth = Integer.parseInt(request.getParameter("CLMaxPeriodMonth"));
        CLMinServiceFee = Integer.parseInt(request.getParameter("CLMinServiceFee"));
        ClMaxServiceFee = Integer.parseInt(request.getParameter("ClMaxServiceFee"));
        ClMinAge = Integer.parseInt(request.getParameter("ClMinAge"));
        ClMaxAge = Integer.parseInt(request.getParameter("ClMaxAge"));
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
