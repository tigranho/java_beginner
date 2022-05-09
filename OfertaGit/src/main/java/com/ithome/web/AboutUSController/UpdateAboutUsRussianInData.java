package com.ithome.web.AboutUSController;

import com.ithome.web.AdminDao.AboutUsDao;
import com.ithome.web.Bean.AboutUs;
import com.ithome.web.Bean.Admin;
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

@WebServlet("/UpdateAboutUsRussianInData")
public class UpdateAboutUsRussianInData extends HttpServlet {

    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private List<AboutUs> aboutUsList = new ArrayList<>();
    private String fullText = null;
    private AboutUsDao aboutUsDao = new AboutUsDao();
    private AboutUs aboutUs = new AboutUs();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateAboutUsRuussianInData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateAboutUsRuussianInData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAboutUsRuussianInData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        UpdateTextInDataRus(CreateNewTextInData(1),request,response,1);
    }

    private void UpdateTextInDataRus(int AddedToDta, HttpServletRequest request, HttpServletResponse response, int Id) throws ServletException, IOException, SQLException {
        if(AddedToDta ==0){
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            getRussianText();
            setRequestToAboutUsUpdatePage(request);
            goBackToErrorPage(request,response,message);
        }else{
            String message = "Տեքստը հաջողությամբ թարմացվում է";
            getRussianText();
            setRequestToAboutUsUpdatePage(request);
            gotoNextPage(request,response,message);
        }
    }

    private void setRequestToAboutUsUpdatePage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("aboutUsList", aboutUsList);
    }

    private void goBackToErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AboutUsRussian.jsp").forward(request, response);
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void getRussianText() throws SQLException {
        aboutUsList = aboutUsDao.getAboutUsInRussian();
    }

    private int CreateNewTextInData(int id) throws SQLException {
        return aboutUsDao.UpdateAboutusArm(fullText,id);
    }

    private void getParameters(HttpServletRequest request) {
        fullText  = request.getParameter("AboutUsText");
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


