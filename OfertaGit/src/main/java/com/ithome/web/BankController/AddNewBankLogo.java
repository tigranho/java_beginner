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


@WebServlet("/AddNewBankLogo")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)   // 50MB
public class AddNewBankLogo extends HttpServlet {

    private String bankdId = null;
    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private Banks banks;
    private BanksDaoController banksDaoController = new BanksDaoController();
    List<Banks> banksList = new ArrayList<>();
    private int newBankId = 0;
    private String filePath = null;
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "upload";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            addNewBankLogo(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            addNewBankLogo(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void addNewBankLogo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo();
        getParameters(request);
        UploadImageToServer(request, response);


    }

    private void UploadImageToServer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        System.out.println("appPath +" + appPath);
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + UPLOAD_DIRECTORY;
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
            String filepat =  UPLOAD_DIRECTORY + "/" + fileName;
            System.out.println("filepat +" + filepat);
            part.write(savePath + File.separator + fileName);

            AddToData(request,response,filepat,newBankId);
        }

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
        newBankId = Integer.parseInt(request.getParameter("bankId"));
    }


    /**
     * update image in Database with bank updated id
     *
     * @param request
     * @param response
     * @param filePath
     * @param bankId
     * @throws ServletException
     * @throws IOException
     */
    private void AddToData(HttpServletRequest request, HttpServletResponse response, String filePath, int bankId) throws ServletException, IOException, SQLException {
        String linkToSave =  "http://www.oferta.am/" +filePath;
        Banks banks = new Banks(linkToSave);
        int UpdateImageInData = banksDaoController.UpdateBankImage(banks, bankId);
        if (UpdateImageInData == 0) {
            String message = "Ինչ-որ սխալ տեղի ունեցավ, կրկին փորձեք";
            setRequestToPage(request);
            goBackToSamePage(request, response, message);

        } else {
            String message = "Հաջողությամբ ավելացրեցիք նոր բանկի տվյալները";
            setRequestToPage(request);
            goBackToSamePage(request, response, message);


        }
    }


    private void goBackToSamePage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/AddBankSecondStep.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);

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


    /**
     * get admin admin id by username from session
     */
    private void getAdminInfo() {
        adminId = adminChecker.getAdminId(username);
        getFullAdminList(adminId);
    }

    /**
     * Fill admin in list with the specific id
     *
     * @param adminid
     */
    private void getFullAdminList(int adminid) {
        adminList = adminChecker.getAllInfoofAdmin(adminid);
    }


}
