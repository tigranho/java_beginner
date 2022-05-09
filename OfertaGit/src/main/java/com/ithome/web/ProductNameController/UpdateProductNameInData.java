package com.ithome.web.ProductNameController;

import com.ithome.web.AdminDao.ProductNameDaoController;
import com.ithome.web.Bean.Admin;
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

@WebServlet("/UpdateProductNameInData")
public class UpdateProductNameInData extends HttpServlet {

    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private String productName = null;
    private String prodeuctNameEng = null;
    private String productNameRus = null;
    private int productCode = 0;
    private ProductNameDaoController productNameDaoController = new ProductNameDaoController();
    private List<ProductName> productNameList = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateProductNameInData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateProductNameInData(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateProductNameInData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        updateProductNamesInData(CreateNewProductNameInData(), request, response, productCode);
    }

    private void updateProductNamesInData(int createNewProductNameInData, HttpServletRequest request, HttpServletResponse response, int productCode) throws ServletException, IOException, SQLException {
        if (createNewProductNameInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
           getProductNameByProductCode(productCode);
            setRequestToProductName(request);
            goBackToErrorPage(request, response, message);
        }else{
            String message = "ձեր թարմացումը հաջող է";
            setRequestToProductName(request);
            gotoNextPage(request,response,message);
        }
    }

    private void goBackToErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/UpdateProductNames.jsp").forward(request, response);
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void setRequestToProductName(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("productNameList", productNameList);
    }

    private void getProductNameByProductCode(int productCode) throws SQLException {
        productNameList = productNameDaoController.getProductNameByProdcutCode(productCode);
    }

    private int CreateNewProductNameInData() throws SQLException {
        return productNameDaoController.updateAgInData(PrePaireProductNameForData(), productCode);
    }

    private ProductName PrePaireProductNameForData() {
        return new ProductName(productCode, productName, prodeuctNameEng, productNameRus);
    }

    private void getParameters(HttpServletRequest request) {
        productName = request.getParameter("productName");
        prodeuctNameEng = request.getParameter("prodeuctNameEng");
        productNameRus = request.getParameter("productNameRus");
        productCode = Integer.parseInt(request.getParameter("productCode"));
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



