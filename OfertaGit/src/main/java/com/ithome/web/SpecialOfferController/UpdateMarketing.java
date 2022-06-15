package com.ithome.web.SpecialOfferController;

import com.ithome.web.AdminDao.*;
import com.ithome.web.Bean.*;
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

@WebServlet("/UpdateMarketing")
public class UpdateMarketing extends HttpServlet {

    private SessionChecker checker = new SessionChecker();
    private String username = null;
    private AdminChecker adminChecker = new AdminChecker();
    private int adminId = 0;
    private List<Admin> adminList = new ArrayList<>();
    private List<CarLoans> carLoansList = new ArrayList<>();
    private List<Deposit> depositList = new ArrayList<>();
    private List<AgriculturalCredit> AGList = new ArrayList<>();
    private List<ConsumerCredit> consumerCreditList = new ArrayList<>();
    private List<MicroLoans> microLoansList = new ArrayList<>();
    private List<Mortgage> mortgageList = new ArrayList<>();
    private List<Banks> banksList = new ArrayList<>();


    private CarLoanDao carLoanDao = new CarLoanDao();
    private ConsumerCreditDaoController consumerCreditDaoController = new ConsumerCreditDaoController();
    private DepositDaoController depositDaoController = new DepositDaoController();
    private AgriculturalCreditDao AGDaoController = new AgriculturalCreditDao();
    private MicroLoanDaoController microLoanDaoController = new MicroLoanDaoController();
    private MortgageDaoController mortgageDaoController = new MortgageDaoController();
    private BanksDaoController banksDaoController = new BanksDaoController();

    private int productCode = 0;
    private String SpecialOffer = null;
    private int SpecialOfferInteger = 0;
    private String SearchList = null;
    private int SearchListInteger = 0;
    private String SendRequest = null;
    private int SendRequestInteger = 0;
    private String GotoPage = null;
    private int GotoPageInteger = 0;
    private String PageName = null;
    private int OrderOfAppearance = 0;
    private String LastLogic = null;
    private int LastLogicIntegers = 0;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateMarketing(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateMarketing(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateMarketing(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getAdminInfo(request, response);
        getParameters(request);
        getBankList();
        convertToDecimals();
        SwitchDatabAses(PageName, request, response);
    }

    private void getBankList() throws SQLException {
        banksList = banksDaoController.getAllBanksList();
    }

    private void convertToDecimals() {
        SpecialOfferInteger = convertSpecialOffers(SpecialOffer);
        SearchListInteger = convertSearch(SearchList);
        SendRequestInteger = convertSendRequest(SendRequest);
        GotoPageInteger = convertGoto(GotoPage);
        LastLogicIntegers = covertLastLogic(LastLogic);

    }

    private int covertLastLogic(String LastLogic) {
        return  LastLogic==null || !LastLogic.equals("on")? 0 : 1;
    }

    private int convertSpecialOffers(String specialOffer) {
        return  specialOffer==null || !specialOffer.equals("on")? 0 : 1;
    }

    private int convertSearch(String SearchList) {
        return  SearchList==null || !SearchList.equals("on")? 0 : 1;
    }

    private int convertSendRequest(String SendRequest) {
        return  SendRequest==null || !SendRequest.equals("on")? 0 : 1;
    }

    private int convertGoto(String GotoPage) {
        return  GotoPage==null || !GotoPage.equals("on")? 0 : 1;
    }


    private void SwitchDatabAses(String pageName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        switch (pageName) {
            case "CarLoan":
                updateCarLoanInData(UpdateCarLoanInData(), request, response);
                break;
            case "Deposit":
                updateDepositInData(UpdateDepositInData(), request, response);
                break;
            case "Agriculture":
                UpdateAGInData(UpdateAGInData(),request,response);
                break;
            case "Consummer":
                updateConsumerInData(UpdateConsumerInData(),request,response);

                break;
            case "Micro":
                updateMicroInData(UpdateMicroInData(),request,response);
                break;
            case "Mortgage":
                updateMortgageInData(UpdateMortgageInData(),request,response);
                break;
            default:
                errorMessage(request,response);
                break;
        }
    }

    private void errorMessage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String messMortgagee = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
        setRequestToPage(request);
        gotoNextPage(request,response,messMortgagee);
    }

    ////////////////////////////////////////////////////////Mortgage Start
    private int UpdateMortgageInData() throws SQLException {
        OrderOfAppearance = getMortgageAppearanceByProductCode(productCode);
        return mortgageDaoController.UpdateMortgageMarketingInData(prepaireMortgageInfoForData(),productCode);
    }
    private int getMortgageAppearanceByProductCode(int productCode) throws SQLException {
        mortgageList = getListOfMortgageByProductCode(productCode);
        return getMortgageOrderOfAppearance(mortgageList);
    }

    private int getMortgageOrderOfAppearance(List<Mortgage> mortgageList) {
        return mortgageList.get(0).getOrderOfAppearance();
    }

    private List<Mortgage> getListOfMortgageByProductCode(int productCode) throws SQLException {
        return mortgageDaoController.getMortgageByProductCode(productCode);

    }

    private Mortgage prepaireMortgageInfoForData() {
        return new Mortgage(OrderOfAppearance, SpecialOfferInteger, SearchListInteger, SendRequestInteger, GotoPageInteger,LastLogicIntegers);
    }

    private void updateMortgageInData(int updateInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FinalStep(updateInData, request, response);
    }
    /////////////////////////////////////////////////////////Mortgage End
    ////////////////////////////////////////////////////////Micro Start
    private int UpdateMicroInData() throws SQLException {
        OrderOfAppearance = getMicroAppearanceByProductCode(productCode);
        return microLoanDaoController.UpdateMicroLoanMarketingInData(prepaireMicroInfoForData(),productCode);
    }
    private int getMicroAppearanceByProductCode(int productCode) throws SQLException {
        microLoansList = getListOfMicroByProductCode(productCode);
        return getMicroOrderOfAppearance(microLoansList);
    }

    private int getMicroOrderOfAppearance(List<MicroLoans> microLoansList) {
        return microLoansList.get(0).getOrderOfAppearance();
    }

    private List<MicroLoans> getListOfMicroByProductCode(int productCode) throws SQLException {
        return microLoanDaoController.getMicroLoansByProductCode(productCode);

    }

    private MicroLoans prepaireMicroInfoForData() {
        return new MicroLoans(OrderOfAppearance, SpecialOfferInteger, SearchListInteger, SendRequestInteger, GotoPageInteger,LastLogicIntegers);
    }

    private void updateMicroInData(int updateInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FinalStep(updateInData, request, response);
    }
    /////////////////////////////////////////////////////////Micro End
    ////////////////////////////////////////////////////////Consumer Start
    private int UpdateConsumerInData() throws SQLException {
        OrderOfAppearance = getConsumerAppearanceByProductCode(productCode);
        return consumerCreditDaoController.UpdateConsumerMarketingInData(prepaireConsumerInfoForData(),productCode);
    }
    private int getConsumerAppearanceByProductCode(int productCode) throws SQLException {
        consumerCreditList = getListOfConsumerByProductCode(productCode);
        return getConsumerOrderOfAppearance(consumerCreditList);
    }

    private int getConsumerOrderOfAppearance(List<ConsumerCredit> consumerCreditList) {
        return consumerCreditList.get(0).getOrderOfAppearance();
    }

    private List<ConsumerCredit> getListOfConsumerByProductCode(int productCode) throws SQLException {
        return consumerCreditDaoController.getConsumerCreditByCardCode(productCode);

    }

    private ConsumerCredit prepaireConsumerInfoForData() {
        return new ConsumerCredit(OrderOfAppearance, SpecialOfferInteger, SearchListInteger, SendRequestInteger, GotoPageInteger,LastLogicIntegers);
    }

    private void updateConsumerInData(int updateInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FinalStep(updateInData, request, response);
    }
    /////////////////////////////////////////////////////////Consumer End


    ////////////////////////////////////////////////////////AG Start
    private int UpdateAGInData() throws SQLException {
        OrderOfAppearance = getAGAppearanceByProductCode(productCode);
        return AGDaoController.UpdateAGMarketingInData(prepaireAGInfoForData(),productCode);
    }
    private int getAGAppearanceByProductCode(int productCode) throws SQLException {
        AGList = getListOfAGByProductCode(productCode);
        return getAGOrderOfAppearance(AGList);
    }

    private int getAGOrderOfAppearance(List<AgriculturalCredit> AGList) {
        return AGList.get(0).getOrderOfAppearance();
    }

    private List<AgriculturalCredit> getListOfAGByProductCode(int productCode) throws SQLException {
        return AGDaoController.getAgriculturalCreditByProductCode(productCode);

    }

    private AgriculturalCredit prepaireAGInfoForData() {
        return new AgriculturalCredit(OrderOfAppearance, SpecialOfferInteger, SearchListInteger, SendRequestInteger, GotoPageInteger,LastLogicIntegers);
    }

    private void UpdateAGInData(int updateInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FinalStep(updateInData, request, response);
    }
    /////////////////////////////////////////////////////////AG End

    ////////////////////////////////////////////////////////Deposit Start
    private int UpdateDepositInData() throws SQLException {
        OrderOfAppearance = getDepositAppearanceByProductCode(productCode);
        return depositDaoController.updateDepositeInData(prepaireDepositInfoForData(),productCode);
    }
    private int getDepositAppearanceByProductCode(int productCode) throws SQLException {
        depositList = getListOfDepositByProductCode(productCode);
        return getDepositOrderOfAppearance(depositList);
    }

    private int getDepositOrderOfAppearance(List<Deposit> depositList) {
        return depositList.get(0).getOrderOfAppearance();
    }

    private List<Deposit> getListOfDepositByProductCode(int productCode) throws SQLException {
        return depositDaoController.getDepositByCardCode(productCode);

    }

    private Deposit prepaireDepositInfoForData() {
        return new Deposit(OrderOfAppearance, SpecialOfferInteger, SearchListInteger, SendRequestInteger, GotoPageInteger,LastLogicIntegers);
    }
    /////////////////////////////////////////////////////////Deposit End
    private void updateDepositInData(int updateInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinalStep(updateInData, request, response);
    }

    private void FinalStep(int updateInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(updateInData==0){
            String message = "Ինչ-որ սխալ տեղի ունեցավ, փորձեք կրկին";
            setRequestToPage(request);
            gotoNextPage(request,response,message);
        }else{
            String message = "Հաջողությամբ կատարեցիք փոփոխություները";
            setRequestToPage(request);
            gotoNextPage(request,response,message);
        }
    }


/////////////////////////////////////////////////////////Car loan Start
    private int UpdateCarLoanInData() throws SQLException {
        OrderOfAppearance = getAppearanceByProductCode(productCode);
        return carLoanDao.updateCarLoanMarketingInData(prepaireCarLoanInfoForData(), productCode);
    }

    private int getAppearanceByProductCode(int productCode) throws SQLException {
        carLoansList = getListOfCarByProductCode(productCode);
        return getOrderOfAppearance(carLoansList);
    }

    private int getOrderOfAppearance(List<CarLoans> carLoansList) {
        return carLoansList.get(0).getOrderOfAppearance();
    }

    private List<CarLoans> getListOfCarByProductCode(int productCode) throws SQLException {
        return carLoanDao.getCarLoansByProductCode(productCode);
    }

    private CarLoans prepaireCarLoanInfoForData() {
        return new CarLoans(OrderOfAppearance, SpecialOfferInteger, SearchListInteger, SendRequestInteger, GotoPageInteger,LastLogicIntegers);
    }

    private void updateCarLoanInData(int updateInData, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FinalStep(updateInData, request, response);
    }
    /////////////////////////////////////////////////////////Car loan End

    private void gotoNextPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/Results.jsp").forward(request, response);
    }

    private void setRequestToPage(HttpServletRequest request) {
        request.setAttribute("username", username);
        request.setAttribute("adminId", adminId);
        request.setAttribute("adminFullInfo", adminList);
        request.setAttribute("banksList", banksList);
    }

    private void getParameters(HttpServletRequest request) {
        productCode = Integer.parseInt(request.getParameter("productCode"));
        SpecialOffer = request.getParameter("SpecialOffer");
        SearchList = request.getParameter("SearchList");
        SendRequest = request.getParameter("SendRequest");
        GotoPage = request.getParameter("GotoPage");
        PageName = request.getParameter("PageName");
        LastLogic = request.getParameter("LastLogic");
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
