package com.ithome.web.MarketingController;

import com.ithome.web.AdminDao.CardsDao;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Bean.Cards;
import com.ithome.web.Bean.ProductName;
import com.ithome.web.Helpers.AdminChecker;
import com.ithome.web.Helpers.GetBankFullInfo;
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

@WebServlet("/UpdateCardApperanceInData")
public class UpdateCardApperanceInData extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private List<Cards> cardsList = new ArrayList<>();
    private CardsDao cardsDao = new CardsDao();
    private int AppearanceNumber =0;
    private int CardId =0;
    private int productCode =0;
    private GetBankFullInfo bf = new GetBankFullInfo();
    private List<Banks> banksList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateCardApperanceInData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateCardApperanceInData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCardApperanceInData(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getBankFullInfo();
        getParameters(request);
        checkAppearance();
    }

    /**
     * get bank full info
     */
    private void getBankFullInfo() throws SQLException {
        banksList = bf.contactBankDetail();
    }

    /**
     * getting card appearance List
     * @return
     */
    private void getAllCardAppearanceListByProductCode() throws SQLException {
       cardsList =  cardsDao.getAllCardsList();
    }

    private void checkAppearance() throws SQLException {
        getAllCardAppearanceListByProductCode();
        for (int i = 0; i <cardsList.size() ; i++) {
            if(cardsList.get(i).getProductCode() == productCode) {
                int oldNumber = cardsList.get(i).getOrderOfAppearance();
                System.out.println("Old Number " + oldNumber);
            }
            getOrderOfAppearance();

        }
    }

    private void getOrderOfAppearance() {
        for (int i = 0; i < cardsList.size(); i++) {
            if (cardsList.get(i).getOrderOfAppearance() == AppearanceNumber) {
                System.out.println("Product Code " + cardsList.get(i).getProductCode());
            } else {
                System.out.println("Its new Code");
            }
        }
    }


    private void getParameters(HttpServletRequest request) {
        AppearanceNumber = Integer.parseInt(request.getParameter("AppearanceNumber"));
        CardId = Integer.parseInt(request.getParameter("CardId"));
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



