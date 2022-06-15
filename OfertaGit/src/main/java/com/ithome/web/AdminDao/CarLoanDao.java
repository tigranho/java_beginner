package com.ithome.web.AdminDao;

import com.ithome.web.Bean.CarLoans;
import com.ithome.web.Bean.Cards;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarLoanDao {

    /**
     * Show all cards
     */
    public List<CarLoans> getAllCarLoans() throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<CarLoans>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in Admin Section  : " + exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
            if (statment != null) {
                statment.close();
            }
        }
        return carLoansList;
    }


    /**
     * Get cards by id
     *
     * @param CarLoanId
     * @return
     */
    public List<CarLoans> getCarLoansById(int CarLoanId) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `carloancontroller` WHERE CLId=" + CarLoanId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            CarLoanListSet(carLoansList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    /**
     * Get car loans list by Card code
     *
     * @param productCode
     * @return
     */
    public List<CarLoans> getCarLoansByProductCode(int productCode) throws SQLException {

        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `carloancontroller` WHERE ProductCode=" + productCode;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            CarLoanListSet(carLoansList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    /**
     * Get carLoans by bank id
     *
     * @param bankId
     * @return
     */
    public List<CarLoans> getCarLoanByBankId(int bankId) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `carloancontroller` WHERE bankid=" + bankId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            CarLoanListSet(carLoansList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    /**
     * Car loans order by order on appeaarance
     *
     * @return
     */
    public List<CarLoans> CarLoansOrderByOrderOnAppearance() {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` ORDER BY  orderonappearance DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                CarLoanListSet(carLoansList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return carLoansList;

    }

    /**
     * CarLoans order by card id
     *
     * @return
     */
    public List<CarLoans> CarLoansOrderByProductCode() {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` ORDER BY  productCode DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                CarLoanListSet(carLoansList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return carLoansList;

    }

    /**
     * Car loan order by card Name
     *
     * @return
     */
    public List<CarLoans> CarLoanOrderByProductName() {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` ORDER BY  productname DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                CarLoanListSet(carLoansList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return carLoansList;

    }

    /**
     * Add new agriculture credit
     *
     * @param carLoans
     * @return
     * @throws SQLException
     */
    public int AddNewCarsLoans(CarLoans carLoans) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `carloancontroller`" +
                    "(`CLId`, `productcode`, `bankid`, `bankname`,`bankwebsite`,`productname`,`CLMinAmount`,`CLMaxAmount`,`CLMinLoan`," +
                    "`CLMaxLoan`,`CLFatual`,`CLMinDownPayment`, `CLMaxDownPayment`,`currancy`," +
                    "`CLMinPeriodMonths`,`CLMaxPeriodMonths`,`CLMinServiceFee`,`CLMaxServiceFee`,`minAge`,`maxAge`,`orderOfAppearance`,`specialOffer`,`firstSearchList`,`sendRequest`,`LastLogic`,`BankLink`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(carLoans, statment);
            rowsAffected = statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
        }

        return rowsAffected;

    }

    /**
     * Connecting data
     *
     * @return
     */
    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }

    /**
     * Deleting card loan
     *
     * @param clId
     * @return
     */
    public int DeleteClById(int clId) throws SQLException {
        int rowsDeleted = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();

            String sql = "DELETE FROM `carloancontroller` WHERE  CLId=" + clId;
            statment = connection.prepareStatement(sql);

            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A Message was deleted successfully!");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in CATEGORY DELETE  Section  : " + exception);
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
        }
        return rowsDeleted;
    }


    /**
     * set statments of car loans
     *
     * @param carLoans
     * @param statement
     */
    private void setStatment(CarLoans carLoans, PreparedStatement statement) throws SQLException {
        SetMaster(carLoans, statement);
        statement.setInt(20, carLoans.getOrderOfAppearance());
        statement.setInt(21, carLoans.getSpecialOffer());
        statement.setInt(22, carLoans.getFirstSearchList());
        statement.setInt(23, carLoans.getSendRequest());
        statement.setInt(24, carLoans.getLastlogic());
        statement.setString(25, carLoans.getBankLink()); SetMaster(carLoans, statement);
        statement.setInt(20, carLoans.getOrderOfAppearance());
        statement.setInt(21, carLoans.getSpecialOffer());
        statement.setInt(22, carLoans.getFirstSearchList());
        statement.setInt(23, carLoans.getSendRequest());
        statement.setInt(24, carLoans.getLastlogic());
        statement.setString(25, carLoans.getBankLink());
    }


    /**
     * Car loan List set
     *
     * @param carLoansList
     * @param set
     * @throws SQLException
     */
    private void CarLoanListSet(List<CarLoans> carLoansList, ResultSet set) throws SQLException {

        CarLoans carLoans;
        while (set.next()) {
            carLoans = new CarLoans();
            carLoans.setCLId(set.getLong("CLId"));
            carLoans.setProductCode(set.getInt("productCode"));
            carLoans.setBankId(set.getInt("bankId"));
            carLoans.setBankName(set.getString("bankName"));
            carLoans.setBankWebSite(set.getString("bankWebSite"));
            carLoans.setMinAge(set.getInt("minAge"));
            carLoans.setMaxAge(set.getInt("maxAge"));
            carLoans.setOrderOfAppearance(set.getInt("orderofappearance"));
            carLoans.setSpecialOffer(set.getInt("specialoffer"));
            carLoans.setFirstSearchList(set.getInt("firstsearchlist"));
            carLoans.setSendRequest(set.getInt("sendrequest"));
            carLoans.setCurrancy(set.getString("currancy"));
            carLoans.setProductName(set.getString("productName"));
            carLoans.setCLMinAmount(set.getInt("CLMinAmount"));
            carLoans.setCLMaxAmount(set.getInt("CLMaxAmount"));
            carLoans.setCLMinLoan(set.getDouble("CLMinLoan"));
            carLoans.setCLMaxLoan(set.getDouble("CLMaxLoan"));
            carLoans.setCLFatual(set.getDouble("CLFatual"));
            carLoans.setCLMinDownPayment(set.getInt("CLMinDownPayment"));
            carLoans.setCLMaxDownPayment(set.getInt("CLMaxDownPayment"));
            carLoans.setCLMinPeriodMonths(set.getInt("CLMinPeriodMonths"));
            carLoans.setCLMaxPeriodMonths(set.getInt("CLMaxPeriodMonths"));
            carLoans.setCLMinServiceFee(set.getInt("CLMinServiceFee"));
            carLoans.setCLMaxServiceFee(set.getInt("CLMaxServiceFee"));
            carLoans.setGotoPage(set.getInt("gotoPage"));
            carLoans.setLastlogic(set.getInt("LastLogic"));
            carLoans.setBankLink(set.getString("BankLink"));
            carLoans.setBanksList(carLoansList);
            carLoansList.add(carLoans);
        }
    }


    /**
     * Updateing data of car loan
     *
     * @param prepairCarLoanInfoInData
     * @param clId
     * @return
     */
    public int updateClInData(CarLoans prepairCarLoanInfoInData, int clId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection =null;
        PreparedStatement statment =null;
        try {
            connection = connectToData();
            String sql = "UPDATE `carloancontroller`  " +
                    "SET productcode=?,bankid=?,bankname=?," +
                    "bankwebsite=?,productname=?,CLMinAmount=?,CLMaxAmount=?,CLMinLoan=?,CLMaxLoan=?,CLFatual=?," +
                    "CLMinDownPayment=?,CLMaxDownPayment=?,currancy=?,CLMinPeriodMonths=?,CLMaxPeriodMonths=?,CLMinServiceFee=?,CLMaxServiceFee=?,minAge=?,maxAge=?,BankLink=?  " +
                    "WHERE CLId=" + clId;
            statment = connection.prepareStatement(sql);
            setStatmentForNonMarketing(prepairCarLoanInfoInData, statment);

            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            if(connection != null){
                connection.close();
            }if(statment != null){
                statment.close();
            }
        }
        return rowsUpdated;
    }

    /**
     * set statments of car loans
     *
     * @param carLoans
     * @param statement
     */
    private void setStatmentForNonMarketing(CarLoans carLoans, PreparedStatement statement) throws SQLException {
        SetMaster(carLoans, statement);
    }

    private void SetMaster(CarLoans carLoans, PreparedStatement statement) throws SQLException {
        statement.setInt(1, carLoans.getProductCode());
        statement.setInt(2, carLoans.getBankId());
        statement.setString(3, carLoans.getBankName());
        statement.setString(4, carLoans.getBankWebSite());
        statement.setString(5, carLoans.getProductName());
        statement.setInt(6, carLoans.getCLMinAmount());
        statement.setInt(7, carLoans.getCLMaxAmount());
        statement.setDouble(8, carLoans.getCLMinLoan());
        statement.setDouble(9, carLoans.getCLMaxLoan());
        statement.setDouble(10, carLoans.getCLFatual());
        statement.setInt(11, carLoans.getCLMinDownPayment());
        statement.setInt(12, carLoans.getCLMaxDownPayment());
        statement.setString(13, carLoans.getCurrancy());
        statement.setInt(14, carLoans.getCLMinPeriodMonths());
        statement.setInt(15, carLoans.getCLMaxPeriodMonths());
        statement.setInt(16, carLoans.getCLMinServiceFee());
        statement.setInt(17, carLoans.getCLMaxServiceFee());
        statement.setInt(18, carLoans.getMinAge());
        statement.setInt(19, carLoans.getMaxAge());
        statement.setString(20, carLoans.getBankLink());
    }


    /**
     * get car loans by bank id and order of appearance
     *
     * @param bankId
     * @return
     */
    public List<CarLoans> getCarLoansByBankIdOrderByAppearance(int bankId) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Connection connection =null;
        PreparedStatement statment =null;
        ResultSet set =null;
        try {
             connection = connectToData();
            String sql = "SELECT *  FROM  `carloancontroller` WHERE BankId=" + bankId + " ORDER BY `OrderOfAppearance` ASC";
             statment = connection.prepareStatement(sql);
             set = statment.executeQuery();
            while (set.next()) {
                carLoans = new CarLoans();
                carLoans.setCLId(set.getLong("CLId"));
                carLoans.setProductCode(set.getInt("productCode"));
                carLoans.setBankId(set.getInt("bankId"));
                carLoans.setBankName(set.getString("bankName"));
                carLoans.setBankWebSite(set.getString("bankWebSite"));
                carLoans.setMinAge(set.getInt("minAge"));
                carLoans.setMaxAge(set.getInt("maxAge"));
                carLoans.setOrderOfAppearance(set.getInt("orderofappearance"));
                carLoans.setSpecialOffer(set.getInt("specialoffer"));
                carLoans.setFirstSearchList(set.getInt("firstsearchlist"));
                carLoans.setSendRequest(set.getInt("sendrequest"));
                carLoans.setCurrancy(set.getString("currancy"));
                carLoans.setProductName(set.getString("productName"));
                carLoans.setCLMinAmount(set.getInt("CLMinAmount"));
                carLoans.setCLMaxAmount(set.getInt("CLMaxAmount"));
                carLoans.setCLMinLoan(set.getInt("CLMinLoan"));
                carLoans.setCLMaxLoan(set.getInt("CLMaxLoan"));
                carLoans.setCLFatual(set.getInt("CLFatual"));
                carLoans.setCLMinDownPayment(set.getInt("CLMinDownPayment"));
                carLoans.setCLMaxDownPayment(set.getInt("CLMaxDownPayment"));
                carLoans.setCLMinPeriodMonths(set.getInt("CLMinPeriodMonths"));
                carLoans.setCLMaxPeriodMonths(set.getInt("CLMaxPeriodMonths"));
                carLoans.setCLMinServiceFee(set.getInt("CLMinServiceFee"));
                carLoans.setCLMaxServiceFee(set.getInt("CLMaxServiceFee"));
                carLoans.setLastlogic(set.getInt("LastLogic"));
                carLoansList.add(carLoans);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }finally {
            if(connection != null){
                connection.close();
            }if(statment != null){
                statment.close();
            }if(set != null){
                set.close();
            }
        }
        return carLoansList;
    }

    /**
     * get car loans by bank id and order of appearance
     *
     * @param bankId
     * @return
     */
    public List<CarLoans> getCarLoansByBankIdOrderBySpecialOffer(int bankId) {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `carloancontroller` WHERE BankId=" + bankId + " ORDER BY `SpecialOffer` ASC";
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            while (set.next()) {
                carLoans = new CarLoans();
                carLoans.setCLId(set.getLong("CLId"));
                carLoans.setProductCode(set.getInt("productCode"));
                carLoans.setBankId(set.getInt("bankId"));
                carLoans.setBankName(set.getString("bankName"));
                carLoans.setBankWebSite(set.getString("bankWebSite"));
                carLoans.setMinAge(set.getInt("minAge"));
                carLoans.setMaxAge(set.getInt("maxAge"));
                carLoans.setOrderOfAppearance(set.getInt("orderofappearance"));
                carLoans.setSpecialOffer(set.getInt("specialoffer"));
                carLoans.setFirstSearchList(set.getInt("firstsearchlist"));
                carLoans.setSendRequest(set.getInt("sendrequest"));
                carLoans.setCurrancy(set.getString("currancy"));
                carLoans.setProductName(set.getString("productName"));
                carLoans.setCLMinAmount(set.getInt("CLMinAmount"));
                carLoans.setCLMaxAmount(set.getInt("CLMaxAmount"));
                carLoans.setCLMinLoan(set.getInt("CLMinLoan"));
                carLoans.setCLMaxLoan(set.getInt("CLMaxLoan"));
                carLoans.setCLFatual(set.getInt("CLFatual"));
                carLoans.setCLMinDownPayment(set.getInt("CLMinDownPayment"));
                carLoans.setCLMaxDownPayment(set.getInt("CLMaxDownPayment"));
                carLoans.setCLMinPeriodMonths(set.getInt("CLMinPeriodMonths"));
                carLoans.setCLMaxPeriodMonths(set.getInt("CLMaxPeriodMonths"));
                carLoans.setCLMinServiceFee(set.getInt("CLMinServiceFee"));
                carLoans.setCLMaxServiceFee(set.getInt("CLMaxServiceFee"));
                carLoansList.add(carLoans);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return carLoansList;
    }

    /**
     * Update marketing
     *
     * @param prepaireCarLoanInfoForData
     * @param getProductCode
     * @return
     */
    public int updateCarLoanMarketingInData(CarLoans prepaireCarLoanInfoForData, int getProductCode) throws SQLException {
        int rowsUpdated = 0;
        Connection connection =null;
        PreparedStatement statement =null;
        try {
             connection = connectToData();
            String sql = "UPDATE `carloancontroller`  " +
                    "SET OrderOfAppearance=?,SpecialOffer=?,FirstSearchList=?," +
                    "SendRequest=?,gotopage=?,LastLogic=? WHERE ProductCode=" + getProductCode;
             statement = connection.prepareStatement(sql);
            statement.setInt(1, prepaireCarLoanInfoForData.getOrderOfAppearance());
            statement.setInt(2, prepaireCarLoanInfoForData.getSpecialOffer());
            statement.setInt(3, prepaireCarLoanInfoForData.getFirstSearchList());
            statement.setInt(4, prepaireCarLoanInfoForData.getSendRequest());
            statement.setInt(5, prepaireCarLoanInfoForData.getGotoPage());
            statement.setInt(6, prepaireCarLoanInfoForData.getLastlogic());

            rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            if(connection != null){
                connection.close();
            }if(statement != null){
                statement.close();
            }
        }
        return rowsUpdated;
    }


    public List<CarLoans> getCarLoanWithSpecialOffer() throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Connection connection=null;
        PreparedStatement statment =null;
        ResultSet set =null;
        try {
             connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `SpecialOffer`=" + 1;
             statment = connection.prepareStatement(sql);
             set = statment.executeQuery();
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }finally {
            if(connection != null){
                connection.close();
            }if(statment != null){
                statment.close();
            }if(set != null){
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositWithAppearance() throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `FirstSearchList`=" + 1;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositbyMaxMinAmount(int minAmount, int maxAmount, String currancy) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `OrderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `CLMinAmount`<= " + minAmount + " AND `CLMaxAmount`>=" + maxAmount + " ORDER BY `CLMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositMaxMinAmount(int minAmount, String currancy) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `CLMinAmount`>= " + minAmount + "  ORDER BY `CLMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositbyMaxMinAmountAsec(int minAmount, int maxAmount, String currancy) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `OrderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `CLMinAmount`<= " + minAmount + " AND `CLMaxAmount`>=" + maxAmount + " ORDER BY `CLMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositMaxMinAmountSub(int minAmount, String currancy) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `CLMinAmount`>= " + minAmount + "  ORDER BY `CLMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositMaxMinPercentSub(int minAmount, int maxAmount, String currancy) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `OrderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `CLMinAmount`<= " + minAmount + " AND `CLMaxAmount`>=" + maxAmount + " ORDER BY `CLFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositMaxMinPercentSubDes(int minAmount, String currancy) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `CLMinAmount`>= " + minAmount + "  ORDER BY `CLFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositMaxMinPercentSubAcs(int minAmount, int maxAmount, String currancy) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `OrderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `CLMinAmount`<= " + minAmount + " AND `CLMaxAmount`>=" + maxAmount + " ORDER BY `CLFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositMaxMinPercentSubAsc(int minAmount, String currancy) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `CLMinAmount`>= " + minAmount + "  ORDER BY `CLFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositbyMaxMinAmountByTime(int minAmount, int timeline, String currancy) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `OrderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `CLMinAmount`<= " + minAmount + " AND `CLMinPeriodMonths`=" + timeline + "  ORDER BY `CLMinPeriodMonths` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositMaxMinAmountByTime(int minAmount, int timeline, String currancy) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `CLMinAmount`>= " + minAmount + " AND `CLMinPeriodMonths`= " + timeline + " ORDER BY `CLMinPeriodMonths` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositbyMaxMinAmountAsecByTime(int minAmount, int timeline, String currancy) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `OrderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `CLMinAmount`<= " + minAmount + " AND `CLMinPeriodMonths`=" + timeline + " ORDER BY `CLMinPeriodMonths` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositMaxMinAmountSubByTime(int minAmount, int timeline, String currancy) throws SQLException {
        CarLoans carLoans = null;
        List<CarLoans> carLoansList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `CLMinAmount`>= " + minAmount + " AND `CLMinPeriodMonths`= "+timeline+"  ORDER BY `CLMinPeriodMonths` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(carLoansList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return carLoansList;
    }

    public List<CarLoans> getDepositbyMaxMinAmountById(int minAmount, String currancy, int BankId) throws SQLException {
        CarLoans deposit = null;
        List<CarLoans> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `BankId`=" + BankId + " ORDER BY `CLMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(depositList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return depositList;
    }

    public List<CarLoans> getDepositMaxMinPercentByID(int minAmount, String currancy, int BankId) throws SQLException {
        CarLoans deposit = null;
        List<CarLoans> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `BankId`=" + BankId + " ORDER BY `CLFatual` ASC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(depositList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return depositList;
    }

    public List<CarLoans> BankingDesc(int BankId) throws SQLException {
        CarLoans deposit = null;
        List<CarLoans> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `BankId`=" + BankId + " ORDER BY `CLFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(depositList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return depositList;
    }

    public List<CarLoans> BankingAsc(int BankId) throws SQLException {
        CarLoans deposit = null;
        List<CarLoans> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `carloancontroller` WHERE `BankId`=" + BankId + " ORDER BY `CLFatual` ASC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            CarLoanListSet(depositList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return depositList;
    }

    public String getCarProductNameByProductCode(int productCode) throws SQLException {
        String productName = null;
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT `ProductName` FROM `carloancontroller` WHERE `ProductCode`= " + productCode;
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            while (set.next()) {
                productName = set.getString("productName");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return productName;
    }
}

