package com.ithome.web.BlogController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.AdminDao.CardTypeDao;
import com.ithome.web.AdminDao.CardsDao;
import com.ithome.web.AdminDao.UsefullArticlesDaoController;
import com.ithome.web.Bean.*;
import com.ithome.web.Helpers.AdminChecker;
import com.ithome.web.Helpers.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddNewBlogImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddNewBlogImage extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private int BlogId = 0;
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY_CARD = "uploadBlog";
    private UsefullArticlesDaoController usefullArticlesDaoController = new UsefullArticlesDaoController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            addNewBlogImage(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            addNewBlogImage(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addNewBlogImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        BlogImageUpload(request, response);
    }

    private void BlogImageUpload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        System.out.println("appPath +" + appPath);
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + UPLOAD_DIRECTORY_CARD;
        System.out.println("savePath +" + savePath);

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            System.out.println("fileName +" + fileName);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            String filepat = UPLOAD_DIRECTORY_CARD + "/" + fileName;
            System.out.println("filepat +" + filepat);
            part.write(savePath + File.separator + fileName);
            AddBlogImageToData(request, response, filepat, BlogId);
        }

    }

    private void AddBlogImageToData(HttpServletRequest request, HttpServletResponse response, String filepat, int blogId) throws ServletException, IOException, SQLException {
        String linkToSave =  "http://www.oferta.am/" +filepat;
        UsefulArticles usefulArticles = new UsefulArticles(linkToSave);
        int UpdateImageInData = usefullArticlesDaoController.AddNewUsefulArticleImage(usefulArticles,blogId);
        if (UpdateImageInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, կրկին փորձեք";
            setRequestToPage(request);
            goBackToSamePage(request, response, message);

        } else {
            String message = "Հաջողությամբ ավելացրեցիք նոր բլոկի տվյալները";
            setRequestToPage(request);
            goBackToResultPage(request, response, message);

        }
    }

    private void goBackToResultPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void goBackToSamePage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddNewBlogImage.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    private void getParameters(HttpServletRequest request) {
        BlogId = Integer.parseInt(request.getParameter("BlogId"));
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

