package com.ithome.web.TermsController;

import com.ithome.web.AdminDao.TermsDao;
import com.ithome.web.Bean.AboutUs;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Terms;
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

@WebServlet("/UpdateTermsEnglishInData")
public class UpdateTermsEnglishInData extends HttpServlet {

    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private String termsText = null;
    private TermsDao termsDao = new TermsDao();
    private Terms terms = new Terms();
    private List<Terms> termsList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateTermsEnglishInData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateTermsEnglishInData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTermsEnglishInData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        UpdateTermsInDataEng(CreateNewTermsInData(1),request,response,1);
    }

    private void UpdateTermsInDataEng(int createNewTermsInData, HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException, SQLException {
        if(createNewTermsInData ==0){
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            getEnglishTerms();
            setRequestToTermsUpdatePage(request);
            goBackToErrorPage(request,response,message);
        }else{
            String message = "Տեքստը հաջողությամբ թարմացվում է";
            getEnglishTerms();
            setRequestToTermsUpdatePage(request);
            gotoNextPage(request,response,message);
        }
    }

    private void goBackToErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/TermsEnglish.jsp").forward(request, response);
    }

    private void setRequestToTermsUpdatePage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("termsList", termsList);
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void getEnglishTerms() throws SQLException {
        termsList = termsDao.GetTermsInArmenian();
    }

    private void getParameters(HttpServletRequest request) {
        termsText = request.getParameter("termsText");
    }

    private int CreateNewTermsInData(int id) throws SQLException {
        return termsDao.UpdateTermsinArm(termsText,id);
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



