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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/AddNewArmenianBlogToDataStepOne")
public class AddNewArmenianBlogToDataStepOne extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private String BlogTitle = null;
    private String BlogArmenianBody = null;
    private List<UsefulArticles> usefulArticlesList = new ArrayList<>();
    private UsefulArticles usefulArticles = new UsefulArticles();
    private UsefullArticlesDaoController usefullArticlesDaoController = new UsefullArticlesDaoController();
    private int blogId =0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewArmenianBlogToDataStepOne(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewArmenianBlogToDataStepOne(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewArmenianBlogToDataStepOne(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        checkIfBlogAdded(createNewArmenianBlogInData(), request, response);
    }

    private void checkIfBlogAdded(int newArmenianBlogInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (newArmenianBlogInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            setRequestToPage(request);
            goBackToPage(request, response, message);
        } else {
            getNewBlogId();
            setRequestToPage(request);
            gotoNextPage(request, response);
        }
    }

    private void getNewBlogId() throws SQLException {
        usefulArticlesList = usefullArticlesDaoController.getAllUsefullArticles();
        blogId = usefulArticlesList.get(usefulArticlesList.size()-1).getUAId();
    }


    private void goBackToPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddNewBlog.jsp").forward(request, response);
    }

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/AddNewBlogImage.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("blogId", blogId);
    }


    private int createNewArmenianBlogInData() throws SQLException {
        return usefullArticlesDaoController.AddNewUsefulArticle(prepaireArticlesForDatat());
    }

    private UsefulArticles prepaireArticlesForDatat() {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        return new UsefulArticles(BlogTitle, BlogTitle, BlogTitle, BlogArmenianBody, BlogArmenianBody, BlogArmenianBody,date);
    }

    private void getParameters(HttpServletRequest request) {
        BlogArmenianBody = request.getParameter("BlogArmenianBody");
        BlogTitle = request.getParameter("BlogTitle");
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

