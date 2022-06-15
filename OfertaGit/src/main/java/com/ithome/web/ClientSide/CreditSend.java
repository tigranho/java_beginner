package com.ithome.web.ClientSide;

import com.ithome.web.AdminDao.*;
import com.ithome.web.Bean.*;
import com.ithome.web.CompareController.CompareHelper;
import com.ithome.web.Constances.Constance;
import com.ithome.web.*;
import com.ithome.web.Helpers.*;
import com.ithome.web.Localization.CheckLanguageAndCurrency;
import com.ithome.web.counterController.HitCounter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import java.util.Properties;

@WebServlet("/CreditSend")
public class CreditSend extends HttpServlet {
    private CheckLanguageAndCurrency checkLanguageAndCurrency = new CheckLanguageAndCurrency();
    private String language = null;
    private String Pagelanguage = null;
    private String pageName = null;
    private String city = null;
    private String pageLanguageName = null;
    private String sessionId = null;
    private String Amount = null;
    private String PageToGo = null;
    private String amoutFiltered = null;
    private String MaxAmount = null;

    private List<DropDowns> dropDownsList = new ArrayList<>();
    private List<DropDowns> dropDownsListWithCurrancy = new ArrayList<>();

    private List<Mortgage> mortgageList = new ArrayList<>();
    private List<ConsumerCredit> consumerCreditList = new ArrayList<>();
    private List<MicroLoans> MicroCreditList = new ArrayList<>();
    private List<AgriculturalCredit> AgList = new ArrayList<>();
    private List<Cards> CardList = new ArrayList<>();
    private List<CarLoans> CarList = new ArrayList<>();
    private List<Deposit> DepositsList = new ArrayList<>();
    private List<Banks> banksList = new ArrayList<>();
    private List<Integer> hashiv = new ArrayList<>();


    private DropDownCurrancyHelper dropDownCurrancyHelper = new DropDownCurrancyHelper();
    private DropDownHelper dropDownHelper = new DropDownHelper();

    private String pageCurrancy = null;
    private String pageCurrancyFromPage = null;
    private HitCounter hitCounter = new HitCounter();
    private LookUpProgram lookUpProgram = new LookUpProgram();
    private LanguageHelper languageHelper = new LanguageHelper();
    private PageNameHelper pageNameHelper = new PageNameHelper();


    private List<Integer> comparListDeposit = new ArrayList<>();
    private List<Integer> comparListMortgage = new ArrayList<>();
    private static List<Integer> comparListConsumer = new ArrayList<>();
    private static List<Integer> comparListCarLoan = new ArrayList<>();
    private static List<Integer> comparListMicro = new ArrayList<>();
    private static List<Integer> comparListAg = new ArrayList<>();
    private static List<Integer> comparListCard = new ArrayList<>();
    private String WorningMessage = null;
    private int DepositCompareSize = 0;
    private int PercentageSecond = 0;
    private String PageNameToDelete = null;

    private int ProductCode = 0;
    private String FromPageName = null;
    private String Months = null;

    private String rangeTwo = null;
    private String valueTwo = null;
    private String productCode = "";

    private float PercentageSecondFloat = 0;

    private int oldAmount = 0;
    private int options = 0;
    private int oldformerrCheck = 0;


    private String UserName = "";
    private String SerName = "";
    private String Phone = "";
    private String Email = "";
    private String Body = "";
    private String EmailProductName = "";
    private String PageToGoFromPage = "";
    private int BankID = 0;
    private String BankEmail = "";
    private float perc = 0;
    List<Mortgage> forEmail = new ArrayList<>();
    List<FirstReplayment> longList = new ArrayList<>();

    private long testAmount = 0;
    List<Repayment> repaymentList = new ArrayList<>();

    long first = 0;
    long secound = 0;
    long third = 0;
    long four = 0;

    long firstFirst = 0;
    long secondSecond = 0;
    long thirdThird = 0;
    long forthForth = 0;
    int valueChange = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            creditSend(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            creditSend(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void creditSend(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        request.setCharacterEncoding("UTF-8");
        sessionControlling(request, response);
        getLanguagesFromPage(request);
        getCurancyFromPage(request);
        //getCityFromUser(request);
        getPageName(request);
        getPageLanguage(language);
        getParameters(request);
        getDepositListByProductCode(ProductCode, request);

        checkForCompareList();
        getPageRange();
        GetDropDownByCurrancy(dropDownsList, pageCurrancy);
        createRequestes(request);
        gotoPage(request, response);
    }

    private void getDepositListByProductCode(int id, HttpServletRequest request) throws SQLException {
        if (FromPageName.equals("Mortgage") || FromPageName.equals("CreditSendMortgage")) {
            mortgageList = new ArrayList<>();
            MortgageDaoController mortgageDaoController = new MortgageDaoController();
            mortgageList = mortgageDaoController.getMortgageByProductCode(id);
            BanksDaoController banksDaoController = new BanksDaoController();
            int bankId = mortgageList.get(0).getBankId();
            calculatloan(request);

            banksList = banksDaoController.getBankInfoById(bankId);
        } else if (FromPageName.equals("Consumer") || FromPageName.equals("ՍՊԱՌՈՂԱԿԱՆ") || FromPageName.equals("ConsumerClient") || FromPageName.equals("ConsumerCalculate")) {
            consumerCreditList = new ArrayList<>();
            ConsumerCreditDaoController consumerCreditDaoController = new ConsumerCreditDaoController();
            consumerCreditList = consumerCreditDaoController.getConsumerCreditByCardCode(id);
            BanksDaoController banksDaoController = new BanksDaoController();
            int bankId = consumerCreditList.get(0).getBankId();
            calculatloanConsumer(request);
            banksList = banksDaoController.getBankInfoById(bankId);
        } else if (FromPageName.equals("Micro")) {
            MicroCreditList = new ArrayList<>();
            MicroLoanDaoController microLoanDaoController = new MicroLoanDaoController();
            MicroCreditList = microLoanDaoController.getMicroLoansByProductCode(id);
            BanksDaoController banksDaoController = new BanksDaoController();
            int bankId = MicroCreditList.get(0).getBankId();
            banksList = banksDaoController.getBankInfoById(bankId);
        } else if (FromPageName.equals("AG") || FromPageName.equals("AgCalculate")) {
            AgList = new ArrayList<>();
            AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();
            AgList = agriculturalCreditDao.getAgriculturalCreditByProductCode(id);
            BanksDaoController banksDaoController = new BanksDaoController();
            int bankId = AgList.get(0).getBankId();
            calculatloanAg(request);
            banksList = banksDaoController.getBankInfoById(bankId);
        } else if (FromPageName.equals("Cars") || FromPageName.equals("Car")) {
            CarList = new ArrayList<>();
            CarLoanDao carLoanDao = new CarLoanDao();
            CarList = carLoanDao.getCarLoansByProductCode(id);
            BanksDaoController banksDaoController = new BanksDaoController();
            int bankId = CarList.get(0).getBankId();
            calculatloanCar(request);
            banksList = banksDaoController.getBankInfoById(bankId);
        } else if (FromPageName.equals("Cards")) {
            CardList = new ArrayList<>();
            CardsDao cardsDao = new CardsDao();
            CardList = cardsDao.getCardsByProductCode(id);
            BanksDaoController banksDaoController = new BanksDaoController();
            int bankId = CardList.get(0).getBankId();
            banksList = banksDaoController.getBankInfoById(bankId);

        } else if (FromPageName.equals("Deposit")) {
            DepositsList = new ArrayList<>();
            DepositDaoController depositDaoController = new DepositDaoController();
            DepositsList = depositDaoController.getDepositByCardCode(id);
            BanksDaoController banksDaoController = new BanksDaoController();
            int bankId = DepositsList.get(0).getBankId();
            banksList = banksDaoController.getBankInfoById(bankId);
        }
    }

    private void calculatloanCar(HttpServletRequest request) {
        testAmount = 0;
        valueChange = 0;
        repaymentList = new ArrayList<>();
        float fatual = (float) CarList.get(0).getCLFatual();
        if (Integer.parseInt(Months) == 0) {
            Months = String.valueOf(CarList.get(0).getCLMinPeriodMonths());
        }

        longList = new ArrayList<>();
        if (Integer.parseInt(Amount) < 0) {
            testAmount = Long.parseLong(Amount) + Long.parseLong(valueTwo);
        } else {
            if (valueTwo.equals("")) {
                valueChange = (int) (Long.parseLong(Amount) * 0.10);
                valueTwo = String.valueOf(valueChange);
                testAmount = Long.parseLong(Amount) - Integer.parseInt(valueTwo);
                HashivCar hashivCar = new HashivCar();
                longList = hashivCar.hashivCar(testAmount, fatual, Integer.parseInt(Months));
                MortgageCar mortgageSecond = new MortgageCar();
                repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));

                if (request.getParameter("value_two") != null) {
                    valueTwo = request.getParameter("value_two");
                } else {
                    valueTwo = "";
                }

                if (request.getParameter("range_two") != null) {
                    valueTwo = request.getParameter("range_two");
                } else {
                    valueTwo = "";
                }
            } else {
                if (Integer.parseInt(Amount) < Integer.parseInt(valueTwo)) {
                    if (PercentageSecond < 10) {
                        valueChange = (int) (Long.parseLong(Amount) * 0.10);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                        HashivCar hashivCar = new HashivCar();
                        longList = hashivCar.hashivCar(testAmount, fatual, Integer.parseInt(Months));
                        MortgageCar mortgageSecond = new MortgageCar();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    } else {
                        valueChange = (int) (Long.parseLong(Amount) * PercentageSecond / 100);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                        HashivCar hashivCar = new HashivCar();
                        longList = hashivCar.hashivCar(testAmount, fatual, Integer.parseInt(Months));
                        MortgageCar mortgageSecond = new MortgageCar();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    }

                } else if (Integer.parseInt(Amount) == Integer.parseInt(valueTwo)) {
                    if (PercentageSecond < 10) {
                        valueChange = (int) (Long.parseLong(Amount) * 0.10);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                        HashivCar hashivCar = new HashivCar();
                        longList = hashivCar.hashivCar(testAmount, fatual, Integer.parseInt(Months));
                        MortgageCar mortgageSecond = new MortgageCar();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    } else {
                        valueChange = (int) (Long.parseLong(Amount) * PercentageSecond / 100);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                        HashivCar hashivCar = new HashivCar();
                        longList = hashivCar.hashivCar(testAmount, fatual, Integer.parseInt(Months));
                        MortgageCar mortgageSecond = new MortgageCar();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    }

                } else if (Integer.parseInt(Amount) > Integer.parseInt(valueTwo)) {
                    if (PercentageSecond < 10) {
                        valueChange = (int) (Long.parseLong(Amount) * 0.10);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                        HashivCar hashivCar = new HashivCar();
                        longList = hashivCar.hashivCar(testAmount, fatual, Integer.parseInt(Months));
                        MortgageCar mortgageSecond = new MortgageCar();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    } else {
                        valueChange = (int) (Long.parseLong(Amount) * PercentageSecond / 100);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                        HashivCar hashivCar = new HashivCar();
                        longList = hashivCar.hashivCar(testAmount, fatual, Integer.parseInt(Months));
                        MortgageCar mortgageSecond = new MortgageCar();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    }

                } else {
                    testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                    HashivCar hashivCar = new HashivCar();
                    longList = hashivCar.hashivCar(testAmount, fatual, Integer.parseInt(Months));
                    MortgageCar mortgageSecond = new MortgageCar();
                    repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));

                }
            }
            firstFirst = 0;
            secondSecond = 0;
            thirdThird = 0;
            forthForth = 0;
            for (int i = 0; i < longList.size(); i++) {
                firstFirst += longList.get(i).getFirst();
                secondSecond += longList.get(i).getSecond();
                thirdThird += longList.get(i).getThird();
                forthForth += longList.get(i).getForth();
            }
        }


        first = 0;
        secound = 0;
        third = 0;
        four = 0;
        for (int i = 0; i < repaymentList.size(); i++) {
            first += repaymentList.get(i).getCreditBalance();
            System.out.println(first);
            secound += repaymentList.get(i).getPercentageOfAmount();
            System.out.println(secound);
            third += repaymentList.get(i).getLoanRepayment();
            System.out.println(third);
            four += repaymentList.get(i).getTotalPayment();
            System.out.println(four);
        }
    }

    private void calculatloanConsumer(HttpServletRequest request) {
        testAmount = 0;
        valueChange = 0;
        repaymentList = new ArrayList<>();
        float fatual = (float) consumerCreditList.get(0).getCCFactual();
        if (Integer.parseInt(Months) == 0) {
            Months = String.valueOf(consumerCreditList.get(0).getCCMinPeriodMonth());
        }

        longList = new ArrayList<>();
        if (Integer.parseInt(Amount) < 0) {
            testAmount = Long.parseLong(Amount);
        } else {
            valueChange = (int) (Long.parseLong(Amount) * 0.10);
            valueTwo = String.valueOf(valueChange);
            testAmount = Integer.parseInt(Amount) ;
            longList = HashivCon.hashiv(testAmount, fatual, Integer.parseInt(Months));
            MortgageConsumer mortgageSecond = new MortgageConsumer();
            repaymentList = mortgageSecond.startConsumer(fatual, testAmount, Long.parseLong(Months));

        }
        firstFirst = 0;
        secondSecond = 0;
        thirdThird = 0;
        forthForth = 0;
        for (int i = 0; i < longList.size(); i++) {
            firstFirst += longList.get(i).getFirst();
            secondSecond += longList.get(i).getSecond();
            thirdThird += longList.get(i).getThird();
            forthForth += longList.get(i).getForth();
        }
        first = 0;
        secound = 0;
        third = 0;
        four = 0;
        for (int i = 0; i < repaymentList.size(); i++) {
            first += repaymentList.get(i).getCreditBalance();
            System.out.println(first);
            secound += repaymentList.get(i).getPercentageOfAmount();
            System.out.println(secound);
            third += repaymentList.get(i).getLoanRepayment();
            System.out.println(third);
            four += repaymentList.get(i).getTotalPayment();
            System.out.println(four);
        }

    }

    private void calculatloan(HttpServletRequest request) {
        testAmount = 0;
        valueChange = 0;
        repaymentList = new ArrayList<>();
        float fatual = (float) mortgageList.get(0).getMFatual();
        if (Integer.parseInt(Months) == 0) {
            Months = String.valueOf(mortgageList.get(0).getMMinPeriodMonth());
        }

        longList = new ArrayList<>();
        if (Integer.parseInt(Amount) < 0) {
            testAmount = Long.parseLong(Amount) + Long.parseLong(valueTwo);
        } else {
            if (valueTwo.equals("")) {
                valueChange = (int) (Long.parseLong(Amount) * 0.10);
                valueTwo = String.valueOf(valueChange);
                testAmount = Long.parseLong(Amount) - Integer.parseInt(valueTwo);
                Hashiv hashiv = new Hashiv();
                longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                MortgageSecond mortgageSecond = new MortgageSecond();
                repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));

                if (request.getParameter("value_two") != null) {
                    valueTwo = request.getParameter("value_two");
                } else {
                    valueTwo = "";
                }

                if (request.getParameter("range_two") != null) {
                    valueTwo = request.getParameter("range_two");
                } else {
                    valueTwo = "";
                }
            } else {
                if (Integer.parseInt(Amount) < Integer.parseInt(valueTwo)) {
                    if (PercentageSecond < 10) {
                        valueChange = (int) (Long.parseLong(Amount) * 0.10);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                        Hashiv hashiv = new Hashiv();
                        longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                        MortgageSecond mortgageSecond = new MortgageSecond();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    } else {
                        valueChange = (int) (Long.parseLong(Amount) * PercentageSecond / 100);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                        Hashiv hashiv = new Hashiv();
                        longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                        MortgageSecond mortgageSecond = new MortgageSecond();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    }

                } else if (Integer.parseInt(Amount) == Integer.parseInt(valueTwo)) {
                    if (PercentageSecond < 10) {
                        valueChange = (int) (Long.parseLong(Amount) * 0.10);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                        Hashiv hashiv = new Hashiv();
                        longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                        MortgageSecond mortgageSecond = new MortgageSecond();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    } else {
                        valueChange = (int) (Long.parseLong(Amount) * PercentageSecond / 100);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                        Hashiv hashiv = new Hashiv();
                        longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                        MortgageSecond mortgageSecond = new MortgageSecond();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    }

                } else if (Integer.parseInt(Amount) > Integer.parseInt(valueTwo)) {
                    if (PercentageSecond < 10) {
                        valueChange = (int) (Long.parseLong(Amount) * 0.10);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                        Hashiv hashiv = new Hashiv();
                        longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                        MortgageSecond mortgageSecond = new MortgageSecond();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    } else {
                        valueChange = (int) (Long.parseLong(Amount) * PercentageSecond / 100);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                        Hashiv hashiv = new Hashiv();
                        longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                        MortgageSecond mortgageSecond = new MortgageSecond();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    }

                } else {
                    testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                    Hashiv hashiv = new Hashiv();
                    longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                    MortgageSecond mortgageSecond = new MortgageSecond();
                    repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));

                }
            }
            firstFirst = 0;
            secondSecond = 0;
            thirdThird = 0;
            forthForth = 0;
            for (int i = 0; i < longList.size(); i++) {
                firstFirst += longList.get(i).getFirst();
                secondSecond += longList.get(i).getSecond();
                thirdThird += longList.get(i).getThird();
                forthForth += longList.get(i).getForth();
            }
        }


        first = 0;
        secound = 0;
        third = 0;
        four = 0;
        for (int i = 0; i < repaymentList.size(); i++) {
            first += repaymentList.get(i).getCreditBalance();
            System.out.println(first);
            secound += repaymentList.get(i).getPercentageOfAmount();
            System.out.println(secound);
            third += repaymentList.get(i).getLoanRepayment();
            System.out.println(third);
            four += repaymentList.get(i).getTotalPayment();
            System.out.println(four);
        }
    }

    private void calculatloanAg(HttpServletRequest request) {
        testAmount = 0;
        valueChange = 0;
        repaymentList = new ArrayList<>();
        float fatual = (float) AgList.get(0).getACFactual();
        if (Integer.parseInt(Months) == 0) {
            Months = String.valueOf(AgList.get(0).getACMinPeriodMonth());
        }

        longList = new ArrayList<>();
        if (Integer.parseInt(Amount) < 0) {
            testAmount = Long.parseLong(Amount);
        } else {
            if (valueTwo.equals("")) {
                valueChange = (int) (Long.parseLong(Amount) * 0.10);
                valueTwo = String.valueOf(valueChange);
                testAmount = Long.parseLong(Amount);
                Hashiv hashiv = new Hashiv();
                longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                MortgageSecond mortgageSecond = new MortgageSecond();
                repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));

                if (request.getParameter("value_two") != null) {
                    valueTwo = request.getParameter("value_two");
                } else {
                    valueTwo = "";
                }

                if (request.getParameter("range_two") != null) {
                    valueTwo = request.getParameter("range_two");
                } else {
                    valueTwo = "";
                }
            } else {
                if (Integer.parseInt(Amount) < Integer.parseInt(valueTwo)) {
                    if (PercentageSecond < 10) {
                        valueChange = (int) (Long.parseLong(Amount) * 0.10);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount);
                        Hashiv hashiv = new Hashiv();
                        longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                        MortgageSecond mortgageSecond = new MortgageSecond();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    } else {
                        valueChange = (int) (Long.parseLong(Amount) * PercentageSecond / 100);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount);
                        Hashiv hashiv = new Hashiv();
                        longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                        MortgageSecond mortgageSecond = new MortgageSecond();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    }

                } else if (Integer.parseInt(Amount) == Integer.parseInt(valueTwo)) {
                    if (PercentageSecond < 10) {
                        valueChange = (int) (Long.parseLong(Amount) * 0.10);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) ;
                        Hashiv hashiv = new Hashiv();
                        longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                        MortgageSecond mortgageSecond = new MortgageSecond();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    } else {
                        valueChange = (int) (Long.parseLong(Amount) * PercentageSecond / 100);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) ;
                        Hashiv hashiv = new Hashiv();
                        longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                        MortgageSecond mortgageSecond = new MortgageSecond();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    }

                } else if (Integer.parseInt(Amount) > Integer.parseInt(valueTwo)) {
                    if (PercentageSecond < 10) {
                        valueChange = (int) (Long.parseLong(Amount) * 0.10);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) ;
                        Hashiv hashiv = new Hashiv();
                        longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                        MortgageSecond mortgageSecond = new MortgageSecond();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    } else {
                        valueChange = (int) (Long.parseLong(Amount) * PercentageSecond / 100);
                        valueTwo = String.valueOf(valueChange);
                        testAmount = Integer.parseInt(Amount) ;
                        Hashiv hashiv = new Hashiv();
                        longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                        MortgageSecond mortgageSecond = new MortgageSecond();
                        repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));
                    }

                } else {
                    testAmount = Integer.parseInt(Amount) - Integer.parseInt(valueTwo);
                    Hashiv hashiv = new Hashiv();
                    longList = hashiv.hashiv(testAmount, fatual, Integer.parseInt(Months));
                    MortgageSecond mortgageSecond = new MortgageSecond();
                    repaymentList = mortgageSecond.startMortgage(fatual, testAmount, Long.parseLong(Months));

                }
            }
            firstFirst = 0;
            secondSecond = 0;
            thirdThird = 0;
            forthForth = 0;
            for (int i = 0; i < longList.size(); i++) {
                firstFirst += longList.get(i).getFirst();
                secondSecond += longList.get(i).getSecond();
                thirdThird += longList.get(i).getThird();
                forthForth += longList.get(i).getForth();
            }
            first = 0;
            secound = 0;
            third = 0;
            four = 0;
            for (int i = 0; i < repaymentList.size(); i++) {
                first += repaymentList.get(i).getCreditBalance();
                System.out.println(first);
                secound += repaymentList.get(i).getPercentageOfAmount();
                System.out.println(secound);
                third += repaymentList.get(i).getLoanRepayment();
                System.out.println(third);
                four += repaymentList.get(i).getTotalPayment();
                System.out.println(four);
            }
        }

    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (FromPageName.equals("Consumer") || FromPageName.equals("ՍՊԱՌՈՂԱԿԱՆ") || FromPageName.equals("ConsumerClient") || FromPageName.equals("ConsumerCalculate")) {
            if (checkMMonths(request)) {
                List<ConsumerCredit> consumerCredits = new ArrayList<>();
                ConsumerCreditDaoController consumerCreditDaoController = new ConsumerCreditDaoController();
                consumerCredits = consumerCreditDaoController.getConsumerCreditByCardCode(ProductCode);
                Months = String.valueOf(consumerCredits.get(0).getCCMinPeriodMonth());
            }
            request.setAttribute("months", Months);
            request.setAttribute("First", first);
            request.setAttribute("Secound", secound);
            request.setAttribute("Third", third);
            request.setAttribute("Four", four);
            request.setAttribute("FirstFirst", firstFirst);
            request.setAttribute("SecondSecond", secondSecond);
            request.setAttribute("ThirdThird", thirdThird);
            request.setAttribute("ForthForth", forthForth);

            request.setAttribute("RepaymentList", repaymentList);
            request.setAttribute("LongList", longList);
            request.getRequestDispatcher("/CreditsSendC.jsp").forward(request, response);
        } else if (FromPageName.equals("Micro")) {

            request.getRequestDispatcher("/CreditsSendMi.jsp").forward(request, response);
        } else if (FromPageName.equals("Mortgage") || FromPageName.equals("CreditSendMortgage")) {
            if (checkMMonths(request)) {

                List<Mortgage> mortgageListMonths = new ArrayList<>();
                MortgageDaoController mortgageDaoController = new MortgageDaoController();
                mortgageListMonths = mortgageDaoController.getMortgageByProductCode(ProductCode);
                Months = String.valueOf(mortgageListMonths.get(0).getMMinPeriodMonth());
            }
            request.setAttribute("months", Months);
            request.setAttribute("First", first);
            request.setAttribute("Secound", secound);
            request.setAttribute("Third", third);
            request.setAttribute("Four", four);
            request.setAttribute("FirstFirst", firstFirst);
            request.setAttribute("SecondSecond", secondSecond);
            request.setAttribute("ThirdThird", thirdThird);
            request.setAttribute("ForthForth", forthForth);

            request.setAttribute("RepaymentList", repaymentList);
            request.setAttribute("LongList", longList);
            request.getRequestDispatcher("/CreditsSendM.jsp").forward(request, response);
        } else if (FromPageName.equals("AG") || FromPageName.equals("AgCalculate")) {
            if (checkMMonths(request)) {
                List<AgriculturalCredit> agricultureMonths = new ArrayList<>();
                AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();
                agricultureMonths = agriculturalCreditDao.getAgriculturalCreditByProductCode(ProductCode);
                Months = String.valueOf(agricultureMonths.get(0).getACMinPeriodMonth());
                perc = (float) agricultureMonths.get(0).getACFactual();
                request.setAttribute("percent", perc);
            }
            List<AgriculturalCredit> agricultureMonths = new ArrayList<>();
            AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();
            agricultureMonths = agriculturalCreditDao.getAgriculturalCreditByProductCode(ProductCode);
            perc = (float) agricultureMonths.get(0).getACFactual();
            request.setAttribute("percent", perc);
            request.setAttribute("months", Months);
            request.setAttribute("First", first);
            request.setAttribute("Secound", secound);
            request.setAttribute("Third", third);
            request.setAttribute("Four", four);
            request.setAttribute("FirstFirst", firstFirst);
            request.setAttribute("SecondSecond", secondSecond);
            request.setAttribute("ThirdThird", thirdThird);
            request.setAttribute("ForthForth", forthForth);

            request.setAttribute("RepaymentList", repaymentList);
            request.setAttribute("LongList", longList);

            request.getRequestDispatcher("/CreditsSendAG.jsp").forward(request, response);
        } else if (FromPageName.equals("Cars") || FromPageName.equals("Car")) {
            if (checkMMonths(request)) {
                List<CarLoans> carMonths = new ArrayList<>();
                CarLoanDao carLoanDao = new CarLoanDao();
                carMonths = carLoanDao.getCarLoansByProductCode(ProductCode);
                Months = String.valueOf(carMonths.get(0).getCLMinPeriodMonths());
                perc = (float) carMonths.get(0).getCLFatual();
                request.setAttribute("MFatual", perc);
            }
            request.setAttribute("months", Months);
            request.setAttribute("First", first);
            request.setAttribute("Secound", secound);
            request.setAttribute("Third", third);
            request.setAttribute("Four", four);
            request.setAttribute("FirstFirst", firstFirst);
            request.setAttribute("SecondSecond", secondSecond);
            request.setAttribute("ThirdThird", thirdThird);
            request.setAttribute("ForthForth", forthForth);

            request.setAttribute("RepaymentList", repaymentList);
            request.setAttribute("LongList", longList);

            request.getRequestDispatcher("/CreditsSendCar.jsp").forward(request, response);
        } else if (FromPageName.equals("Cards")) {
            if (checkMMonths(request)) {
                List<Cards> cardMonths = new ArrayList<>();
                CardsDao cardsDao = new CardsDao();
                cardMonths = cardsDao.getCardsByCardCode(ProductCode);
                Months = String.valueOf(cardMonths.get(0).getCardGracePeriod());
            }
            request.setAttribute("months", Months);
            request.getRequestDispatcher("/CreditsSendCa.jsp").forward(request, response);
        } else if (FromPageName.equals("Deposit")) {

            request.getRequestDispatcher("/Credits.jsp").forward(request, response);
        }
    }

    private boolean checkMMonths(HttpServletRequest request) {
        return request.getParameter("months") != null && request.getParameter("months").equals("0");
    }


    private void GetDropDownByCurrancy(List<DropDowns> dropDownsList, String pageCurrancy) throws SQLException {
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
        System.out.println(dropDownsListWithCurrancy.get(0).getMaxAmount());
        System.out.println(dropDownsListWithCurrancy.get(0).getMinAmount());
    }

    private void createRequestes(HttpServletRequest request) {
        request.setAttribute("Pagelanguage", Pagelanguage);
        request.setAttribute("sessionId", sessionId);
        request.setAttribute("PageCurrancy", pageCurrancy);
        request.setAttribute("PageName", pageName);
        request.setAttribute("City", city);
        request.setAttribute("pageLanguageName", pageLanguageName);
        request.setAttribute("dropDownsListWithCurrancy", dropDownsListWithCurrancy);


        request.setAttribute("Amountfiltered", amoutFiltered);
        request.setAttribute("Amount", Amount);
        request.setAttribute("Amount", amoutFiltered);
        request.setAttribute("range", Amount);
        request.setAttribute("months", Months);

        request.setAttribute("minAmount", amoutFiltered);
        request.setAttribute("maxAmount", MaxAmount);
        request.setAttribute("MaxAmount", MaxAmount);
        request.setAttribute("WorningMessage", WorningMessage);
        request.setAttribute("DepositCompareSize", DepositCompareSize);
        request.setAttribute("name", "deposit");
        request.setAttribute("ProductCode", ProductCode);

        request.setAttribute("comparListDeposit", comparListDeposit);
        request.setAttribute("comparListMortgage", comparListMortgage);
        request.setAttribute("comparListConsumer", comparListConsumer);
        request.setAttribute("comparListCarLoan", comparListCarLoan);
        request.setAttribute("comparListMicro", comparListMicro);
        request.setAttribute("comparListAg", comparListAg);
        request.setAttribute("comparListCard", comparListCard);
        request.setAttribute("TheListM", mortgageList);
        request.setAttribute("TheListCC", consumerCreditList);
        request.setAttribute("TheListMicro", MicroCreditList);
        request.setAttribute("TheListAg", AgList);
        request.setAttribute("CardList", CardList);
        request.setAttribute("CarList", CarList);
        request.setAttribute("TheList", DepositsList);
        request.setAttribute("rangeTwo", rangeTwo);
        request.setAttribute("rangeTwo", valueChange);
        request.setAttribute("valueTwo", valueTwo);
        request.setAttribute("valueTwo", valueChange);
        request.setAttribute("PercentageSecond", PercentageSecond);
        request.setAttribute("Per", perc);
        request.setAttribute("MFatual", perc);
        request.setAttribute("bankList", banksList);
        request.setAttribute("percent", perc);
    }

    private boolean sendEmail(String emailOfSender, String userName, String serName, String PhoneNumber, String body, float mfatualpage, String EmailProductName) {
        String subject = "Հայտ oferta.am -ից ";
        Properties props = new Properties();

        System.out.println("TLSEmail Start");
        // Get the session object

        // Get system properties
        Properties properties = System.getProperties();


        props.setProperty("mail.smtp.host", Constance.HOST);
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        //props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.ssl.enable", "true");
        //props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty(Constance.SOCKET_FACTORY, Constance.PORT);
        props.put("mail.smtp.auth", "true");
        System.out.println(Constance.PORT);
       /* Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Constance.USERNAME, Constance.PASSWORD);
                    }
                });
        session = Session.getInstance(props, this);*/
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Constance.USERNAME, Constance.PASSWORD);
                    }
                });


        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Constance.USERNAME));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(BankEmail));
            message.setSubject(subject, "UTF-8");
            String messageContent = userName + " " + SerName + " հեռախոսահամար " + PhoneNumber + " Հասցէ " + emailOfSender + "\n\n" +
                    " Հետաքրքվում է \n\n վարկի անվանումը ։" + EmailProductName + "  \n\n " +
                    " վարկի կոդը ։" + ProductCode + "  \n\n " +
                    " վարկի գումարը ։" + amoutFiltered + "  \n\n " +
                    " արժույթը ։" + pageCurrancy + "  \n\n " +
                    " տոկոսադրույքը ։" + mfatualpage + "  \n\n հաճախորդի մէկնաբանությունը \n\n"  + body;
            message.setText(messageContent, "UTF-8");
            System.out.println("email messageContent " + messageContent);
            //3rd step)send message
            Transport.send(message);
            System.out.println("email sent");
            return true;
        } catch (MessagingException mex) {
            System.out.println("email not " + mex);
            mex.printStackTrace();
        }
        return false;

    }

    private void getBankEmail(HttpServletRequest request) throws SQLException {
        if (request.getParameter("Bankid") != null) {
            BankID = Integer.parseInt(request.getParameter("Bankid"));
            BanksDaoController banksDaoController = new BanksDaoController();
            List<Banks> banksList = banksDaoController.getBankInfoById(BankID);
            for (int i = 0; i < banksList.size(); i++) {
                BankEmail = banksList.get(i).getBankEmailAddress();
            }
        } else {
            BankID = 0;
        }
    }


    private void getParameters(HttpServletRequest request) throws SQLException {

        if (request.getParameter("Email") != null) {
            getBankEmail(request);
            UserName = request.getParameter("userName");
            SerName = request.getParameter("serName");
            Phone = request.getParameter("phone");
            Email = request.getParameter("email");
            Body = request.getParameter("body");
            EmailProductName = "";
            float mfatualpage = Float.parseFloat(request.getParameter("mfatualpage"));
            String EmailProductName = getProductNameById(ProductCode, FromPageName);


            if (sendEmail(Email, UserName, SerName, Phone, Body,mfatualpage, EmailProductName)) {
                request.setAttribute("message", "Ձեր էլեկտրոնային փոստը հաջողությամբ ուղարկվեց:");
            } else {
                request.setAttribute("message", "ինչ-որ բան չհաջողվեց, կրկին փորձեք:");
            }

        } else {
            UserName = "";
            SerName = "";
            Phone = "";
            Email = "";
            Body = "";
        }

        if (request.getParameter("percentageSecond") != null) {
            String percentagetoCover = "";
            percentagetoCover = request.getParameter("percentageSecond");
            if (percentagetoCover.contains("%")) {
                StringBuilder sb = new StringBuilder(request.getParameter("percentageSecond"));
                sb.deleteCharAt(sb.length() - 1);
                int percentage2 = 0;
                percentage2 = (int) Float.parseFloat(String.valueOf(sb));
                PercentageSecond = checkPercentageRange(percentage2);
            }
        } else if (request.getAttribute("percentageSecond") != null) {
            String percentagetoCover = "";
            percentagetoCover = String.valueOf(request.getAttribute("percentageSecond"));
            if (percentagetoCover.contains("%")) {
                StringBuilder sb = new StringBuilder(request.getParameter("percentageSecond"));
                sb.deleteCharAt(sb.length() - 1);
                int percentage2 = (int) Float.parseFloat(String.valueOf(sb));
                PercentageSecond = checkPercentageRange(percentage2);
            }
        } else {
            PercentageSecond = 10;
        }


        if (request.getParameter("percentage") != null) {
            perc = 0;
            perc = Float.parseFloat(String.valueOf(request.getParameter("percentage")));
        } else {
            perc = 0;
        }
        if (request.getParameter("options") != null) {
            options = Integer.parseInt(request.getParameter("options"));
        } else {
            options = 0;
        }


        if (request.getParameter("value_two") != null) {
            valueTwo = request.getParameter("value_two");
        } else {
            valueTwo = "";
        }

        if (request.getParameter("range_two") != null) {
            valueTwo = request.getParameter("range_two");
        } else {
            valueTwo = "";
        }


        if (request.getParameter("FromPageName") != null) {
            FromPageName = "";
            FromPageName = request.getParameter("FromPageName");
        } else if (request.getParameter("PageToGo") != null) {
            FromPageName = "";
            FromPageName = request.getParameter("PageToGo");
        } else if (request.getParameter("PageToGo") != null) {
            FromPageName = "";
            FromPageName = request.getParameter("PageName");
        } else {
            FromPageName = "";
        }

        if (request.getParameter("months") != null) {
            Months = request.getParameter("months");
            if (FromPageName.equals("Deposit")) {
                int MonthConverted = Integer.parseInt(Months) / 30;
                Months = "";
                Months = String.valueOf(MonthConverted);
            }
        } else {
            Months = "1";
        }




    /* if (request.getParameter("MaxAmount") != null) {
         MaxAmount = request.getParameter("MaxAmount");
     } else if (request.getParameter("maxAmount") != null) {
         MaxAmount = request.getParameter("maxAmount");
     } else if (request.getParameter("MaxAmounr") != null) {
         MaxAmount = request.getParameter("MaxAmounr");
     } else {
         getMaxAmount();
     }*/
        getMaxAmount();


        if (request.getParameter("range") != null) {
            Amount = "";
            Amount = request.getParameter("range");
            filterAmount(Amount);
        } else if (request.getParameter("Amount") != null) {
            Amount = "";
            Amount = request.getParameter("Amount");
            filterAmount(Amount);
        }

        if (request.getParameter("ProductCode") != null) {
            ProductCode = 0;
            ProductCode = Integer.parseInt(request.getParameter("ProductCode"));
        }
        if (request.getParameter("id") != null) {
            ProductCode = 0;
            ProductCode = Integer.parseInt(request.getParameter("id"));
        }
        if (request.getParameter("range_two") != null) {
            valueTwo = request.getParameter("range_two");
            if (oldAmount != Integer.parseInt(valueTwo)) {
                CalculateALL(request);
                oldAmount = Integer.parseInt(valueTwo);
            } else {
                if (request.getParameter("percentageSecond") != null) {
                    StringBuilder sb = new StringBuilder(request.getParameter("percentageSecond"));
                    sb.deleteCharAt(sb.length() - 1);
                    int percentage2 = (int) Float.parseFloat(String.valueOf(sb));
                    getPercentageValue(percentage2, request);
                }
            }
        } else {
            valueTwo = "";
        }

        if (request.getParameter("pageNameToDelete") != null) {
            PageNameToDelete = request.getParameter("pageNameToDelete");
            deleteList(PageNameToDelete);
        } else {
            PageNameToDelete = "";
        }


    }

    private String getProductNameById(int productCode, String fromPageName) throws SQLException {
        switch (fromPageName) {
            case "Mortgage":
                MortgageDaoController mortgageDaoController = new MortgageDaoController();
                return mortgageDaoController.getMortgageProductNameByProductCode(productCode);
            case "Consumer":
                ConsumerCreditDaoController consumerCreditDaoController = new ConsumerCreditDaoController();
                return consumerCreditDaoController.getConsumerProductNameByProductCode(productCode);
            case "Cars":
                CarLoanDao carLoanDao = new CarLoanDao();
                return carLoanDao.getCarProductNameByProductCode(productCode);
            case "AgCalculate":
                AgriculturalCreditDao agriculturalCreditDao = new AgriculturalCreditDao();
                return agriculturalCreditDao.getAGProductNameByProductCode(productCode);
        }
        return null;
    }


    private int checkPercentageRange(int percentage2) {
        if (percentage2 <= 10) {
            return 10;
        } else if (percentage2 >= 80) {
            return 80;
        }
        return percentage2;
    }


    private void CalculateALL(HttpServletRequest request) {
        if (Amount != null) {
            PercentageSecondFloat = ((Float.parseFloat(valueTwo) / Float.parseFloat(Amount)) * 100);
        }

        if (PercentageSecond < 10 || PercentageSecondFloat < 10) {
            PercentageSecond = 10;
        } else if (PercentageSecond > 80 || PercentageSecondFloat > 80) {
            PercentageSecond = 80;
        } else {
            PercentageSecond = (int) PercentageSecondFloat;
            valueTwo = request.getParameter("range_two");
        }
    }

    private void getPercentageValue(int percentage2, HttpServletRequest request) {

        if (percentage2 >= 10 && percentage2 <= 80) {
            PercentageSecond = percentage2;
            Long test = (Long.parseLong(valueTwo) / percentage2 * 100);
            System.out.println(test);
            valueTwo = String.valueOf(test);
        }

    }

    private void filterAmount(String amount) {
        if (amount.contains(",")) {
            amoutFiltered = amount.replace(",", "");
        } else {
            amoutFiltered = amount;
        }
        System.out.println("amoutFiltered " + amoutFiltered);
    }


    private String getPageNameArm(String pageName) {
        return pageNameHelper.ArmPageName(pageName);
    }

    private void getPageRange() throws SQLException {
        if (FromPageName.equals("Consumer") || FromPageName.equals("ՍՊԱՌՈՂԱԿԱՆ") || FromPageName.equals("ConsumerClient") || FromPageName.equals("ConsumerCalculate")) {
            dropDownsList = dropDownHelper.getPageName("ՍՊԱՌՈՂԱԿԱՆ");
        } else if (FromPageName.equals("Mortgage") || FromPageName.equals("CreditSendMortgage")) {
            dropDownsList = dropDownHelper.getPageName("ՀԻՓՈԹԵՔ");
        } else if (FromPageName.equals("Micro")) {
            dropDownsList = dropDownHelper.getPageName("ՄԻԿՐՈՎԱՐԿ");
        } else if (FromPageName.equals("AG") || FromPageName.equals("AgCalculate")) {
            dropDownsList = dropDownHelper.getPageName("ԳՅՈՒՂԱՏՆՏԵՍԱԿԱՆ");
        } else if (FromPageName.equals("Cars") || FromPageName.equals("Car")) {
            dropDownsList = dropDownHelper.getPageName("ԱՎՏՈՎԱՐԿ");
        } else if (FromPageName.equals("Cards")) {
            dropDownsList = dropDownHelper.getPageName("ԳԼԽԱՎՈՐ");
        } else if (FromPageName.equals("Deposit")) {
            dropDownsList = dropDownHelper.getPageName("ԱՎԱՆԴՆԵՐ");
        }
    }

    private void getMaxAmount() throws SQLException {
        getPageRange();
        dropDownsListWithCurrancy = dropDownCurrancyHelper.getDropDownWithCurrancy(dropDownsList, pageCurrancy);
        for (int i = 0; i < dropDownsListWithCurrancy.size(); i++) {
            MaxAmount = dropDownsListWithCurrancy.get(i).getMaxAmount();
            System.out.println(dropDownsListWithCurrancy.get(i).getMinAmount());
        }
    }

    private void getPageLanguage(String language) {
        Pagelanguage = checkLanguageAndCurrency.checkLanguage(language);
        pageLanguageName = checkLanguageAndCurrency.checkLanguageName(language);
    }

    private void sessionControlling(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        sessionId = session.getId();
        request.setAttribute("session",sessionId);
        session.setMaxInactiveInterval(86400);
        CompareHelper.sendSession(sessionId);
    }

    private String getLanguagesFromPage(HttpServletRequest request) {
        if (request.getParameter("Pagelanguage") == null) {
            language = languageHelper.Pagelanguage(request, Pagelanguage);
        } else {
            language = languageHelper.Pagelanguage(request, request.getParameter("Pagelanguage"));
        }
        return language;
    }

    private void getCurancyFromPage(HttpServletRequest request) {
        if (request.getParameter("Currancy") == null) {
            pageCurrancy = checkLanguageAndCurrency.checkCurrency(pageCurrancyFromPage);
        } else {
            pageCurrancy = checkLanguageAndCurrency.checkCurrency(request.getParameter("Currancy"));
        }
    }

    private void getCityFromUser(HttpServletRequest request) throws IOException {
        if (request.getParameter("city") == null) {
            city = lookUpProgram.start(request);
        } else {
            city = request.getParameter("city");
        }
    }

    private void getPageName(HttpServletRequest request) {
        pageName = pageNameHelper.pageName(request);
        System.out.println("Page name : " + pageName);
    }

    private void checkForCompareList() {
        comparListDeposit = CompareHelper.getDepositList(sessionId);
        comparListMortgage = CompareHelper.getMortgageList(sessionId);
        comparListConsumer = CompareHelper.getConsumerList(sessionId);
        comparListCarLoan = CompareHelper.getCarLoanList(sessionId);
        comparListMicro = CompareHelper.getMicroList(sessionId);
        comparListAg = CompareHelper.getAgList(sessionId);
        comparListCard = CompareHelper.getCardList(sessionId);
    }

    private void deleteList(String pageNameToDelete) throws SQLException {
        switch (pageNameToDelete) {
            case "Ավանդ":
                CompareHelper.DeleteDepositList(sessionId);

                break;
            case "Հիփոթեք":
                CompareHelper.DeleteMortgag(sessionId);
                break;
            case "Ավտովարկ":
                CompareHelper.DeleteCarLoan(sessionId);
                break;
            case "ՄԻԿՐՈՎԱՐԿ":
                CompareHelper.DeleteMicro(sessionId);
                break;
            case "Գյուղատնտեսական":
                CompareHelper.DeleteAg(sessionId);
                break;
            case "Սպարողական":
                CompareHelper.DeleteConsumer(sessionId);
                break;
            case "Քարտեր":
                CompareHelper.DeleteCard();
                break;
        }
    }


}

