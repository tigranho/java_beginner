package com.ithome.web.MicroLoanController;

import com.ithome.web.AdminDao.AgriculturalCreditDao;
import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.MicroLoanDaoController;
import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.MicroLoans;
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

@WebServlet("/AddNewMicroToData")
public class AddNewMicroToData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private BanksDaoController banksDaoController = new BanksDaoController();
    private List<Banks> banksList = new ArrayList<>();
    private MicroLoanDaoController microLoanDaoController = new MicroLoanDaoController();
    private MicroLoans microLoana;
    private ProductNameDaoController productNameDaoController = new ProductNameDaoController();
    private ProductName productName = new ProductName();

    private int ProductCode =0;
    private String BankName = null;
    private String ProductName = null;
    private String ProductNameEn = null;
    private String ProductNameRu = null;
    private int MLMinAmount = 0;
    private int MLMaxAmount =0;
    private double MLMinLoan =0;
    private double MLMaxLoan =0;
    private double MLFatual=0;
    private String currancy=null;
    private int MMinPeriodDays =0;
    private int MMaxPeriodDays =0;
    private int MMinServiceFee =0;
    private int MMaxServiceFee =0;
    private int minAge=0;
    private int maxAge=0;
    private int Bankid =0;
    private String bankWebSite=null;
    private String BankLink=null;
    private int bankCode =0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewMicroToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewMicroToData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewMicroToData(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        getBankInfoFromBankname(BankName);
        createProductCode();
        checkIfProductNameAdded(createProductNameInData(),request,response);
        checkIfMicroLoanAdded(createNewMicroInData(), request, response);
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
        return new ProductName(ProductCode,ProductName,ProductNameEn,ProductNameRu);
    }

    private void checkIfMicroLoanAdded(int newMicroInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (newMicroInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToPage(request, response, message);
        } else {

            setRequestToPage(request);
            gotoNextPage(request,response);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/AddNewMLComments.jsp").forward(request, response);
    }

    private void goBackToPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddNewMicro.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("productcode", ProductCode);
    }

    private int createNewMicroInData() throws SQLException {
        return microLoanDaoController.AddNewMicroLoans(createMicroToDatabase());
    }

    private MicroLoans createMicroToDatabase() {
        return new MicroLoans(ProductCode,Bankid,BankName,bankWebSite,ProductName,MLMinAmount,MLMaxAmount,MLMinLoan,MLMaxLoan,
                MLFatual,currancy,MMinPeriodDays,MMaxPeriodDays,MMinServiceFee,MMaxServiceFee,minAge,maxAge,BankLink);
    }

    private void getBankInfoFromBankname(String bankName) throws SQLException {
        banksList = banksDaoController.getBankFullInfoByName(bankName);
        for (int i = 0; i <banksList.size() ; i++) {
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
        MLMinAmount = Integer.parseInt(request.getParameter("MLMinAmount"));
        MLMaxAmount = Integer.parseInt(request.getParameter("MLMinaxAmount"));
        MLMinLoan = Double.parseDouble(request.getParameter("MLMinLoan"));
        MLMaxLoan = Double.parseDouble(request.getParameter("MLMaxLoan"));
        MLFatual = Double.parseDouble(request.getParameter("MLFatual"));
        currancy = request.getParameter("currancy");
        MMinPeriodDays = Integer.parseInt(request.getParameter("MMinPeriodDays"));
        MMaxPeriodDays = Integer.parseInt(request.getParameter("MMaxPeriodDays"));
        MMinServiceFee = Integer.parseInt(request.getParameter("MMinServiceFee"));
        MMaxServiceFee = Integer.parseInt(request.getParameter("MMaxServiceFee"));
        minAge = Integer.parseInt(request.getParameter("minAge"));
        maxAge = Integer.parseInt(request.getParameter("maxAge"));
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

