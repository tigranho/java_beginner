package com.ithome.web.BankController;

import com.ithome.web.AdminDao.BanksDaoController;
import com.ithome.web.Bean.Admin;
import com.ithome.web.Bean.Banks;
import com.ithome.web.Helpers.AdminChecker;
import com.ithome.web.Helpers.SessionChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddNewBankSmallLogo")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)   // 50MB
public class AddNewBankSmallLogo extends HttpServlet {
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private int newBankId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "upload";
    private BanksDaoController banksDaoController = new BanksDaoController();
    private List<Banks> banksList = new ArrayList<>();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewBankSmallLogo(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addNewBankSmallLogo(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewBankSmallLogo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request,response);
        getAdminInfo(request,response);
        getParametersFromPage(request);
        UploadSmallImageToServer(request, response);
    }

    private void getParametersFromPage(HttpServletRequest request) {
        newBankId = Integer.parseInt(request.getParameter("bankId"));
    }

    private void UploadSmallImageToServer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + UPLOAD_DIRECTORY;

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            String filepat = UPLOAD_DIRECTORY + "/"+ fileName;
            part.write(savePath + File.separator + fileName);
            System.out.println(savePath + File.separator + fileName);
            AddSmallImageToData(request,response,filepat,newBankId);
        }

    }

    private void AddSmallImageToData(HttpServletRequest request, HttpServletResponse response, String filepat, int bankId) throws ServletException, IOException, SQLException {
        String linkToSave =  "http://www.oferta.am/" +filepat;
        int UpdateImageInData = banksDaoController.UpdateBankSmallImage(linkToSave, bankId);
        if (UpdateImageInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, կրկին փորձեք";
            setRequestToPage(request);
            goBackToSameSmallImagePage(request, response, message);

        } else {
            String message = "Հաջողությամբ ավելացրեցիք նոր բանկի տվյալները";
            setRequestToPage(request);
            goBackToSameSmallImagePage(request, response, message);


        }
    }

    private void goBackToSameSmallImagePage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddBankSecondStep.jsp").forward(request, response);

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

    private void setRequestToPage(HttpServletRequest request) {
        newBankId = Integer.parseInt(request.getParameter("bankId"));
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
