package com.ithome.web.BlogController;

import com.ithome.web.AdminDao.UsefullArticlesDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.UsefulArticles;
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

@WebServlet("/UpdateRussianBlogToData")
public class UpdateRussianBlogToData extends HttpServlet {

    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private String BlogTitle =null;
    private String BlogRussianBody =null;
    private int UAId =0;
    private List<UsefulArticles> usefulArticlesList = new ArrayList<>();
    private UsefullArticlesDaoController usefullArticlesDaoController = new UsefullArticlesDaoController();
    private UsefulArticles usefulArticles = new UsefulArticles();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateRussianBlogToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateRussianBlogToData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateRussianBlogToData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getAdminInfo(request,response);
        getParameters(request);
        updateRussianBlogInData(createNewRussianBlogInData(),request,response,UAId);
    }

    private void updateRussianBlogInData(int newRussianBlogInData, HttpServletRequest request, HttpServletResponse response, int uaId) throws ServletException, IOException, SQLException {
        if (newRussianBlogInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            getRussianBlogDetailById(UAId);
            setRequestToRussianBlogUpdatePage(request);
            goBackToErrorPage(request, response, message);
        } else {
            String message = "Բլոգը հաջողությամբ թարմացվում է Ռուսերեն լեզվով";
            getRussianBlogDetailById(UAId);
            setRequestToRussianBlogUpdatePage(request);
            gotoNextPage(request,response,message);
        }
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void goBackToErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/UpdateRussianBlog.jsp").forward(request, response);
    }

    private void setRequestToRussianBlogUpdatePage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("usefulArticlesList", usefulArticlesList);

    }

    private void getRussianBlogDetailById(int uaId) throws SQLException {
        usefulArticlesList = usefullArticlesDaoController.getUsefulRussianArticleById(uaId);
    }

    private int createNewRussianBlogInData() throws SQLException {
        return usefullArticlesDaoController.UpdateUsefulArticlesRussian(BlogTitle,BlogRussianBody,UAId);
    }

    private void getParameters(HttpServletRequest request) {
        BlogTitle = request.getParameter("BlogTitle");
        BlogRussianBody = request.getParameter("BlogRussianBody");
        UAId = Integer.parseInt(request.getParameter("UAId"));
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



