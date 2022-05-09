package com.ithome.web.AdminDao;

import com.ithome.web.Bean.AgriculturalCredit;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgriculturalCreditDao {
    /**
     * Show all cards
     */
    public List<AgriculturalCredit> getAllAgriculturalCreditList() throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<AgriculturalCredit>();
        Connection connection = null;
        ResultSet set = null;
        Statement statment = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    /**
     * Get cards by id
     *
     * @param agriculturalCreditId
     * @return
     */
    public List<AgriculturalCredit> getAgriculturalCreditById(int agriculturalCreditId) throws SQLException {

        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> AgriculturalCreditList = new ArrayList<>();
        PreparedStatement statment = null;
        Connection connection = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `agriculturalcreditcontroller` WHERE ACId=" + agriculturalCreditId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCards(AgriculturalCreditList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
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
        return AgriculturalCreditList;
    }


    /**
     * Get Card list by Card code
     *
     * @param productCode
     * @return
     */
    public List<AgriculturalCredit> getAgriculturalCreditByProductCode(int productCode) throws SQLException {
        PreparedStatement statment = null;
        Connection connection = null;
        ResultSet set = null;
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `agriculturalcreditcontroller` WHERE productcode=" + productCode;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCards(agriculturalCreditList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
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
        return agriculturalCreditList;
    }

    /**
     * Get card by bank id
     *
     * @param bankId
     * @return
     */
    public List<AgriculturalCredit> getAgriculturalCreditBybankId(int bankId) throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        PreparedStatement statment = null;
        Connection connection = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `agriculturalcreditcontroller` WHERE bankid=" + bankId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCards(agriculturalCreditList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
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
        return agriculturalCreditList;
    }

    /**
     * get card by card Min Service Fee
     *
     * @param ACMinServiceFee
     * @return
     */
    public List<AgriculturalCredit> getAgriculturalCreditByMinServiceFee(int ACMinServiceFee) {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `agriculturalcreditcontroller` WHERE acminservicefee=" + ACMinServiceFee;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(agriculturalCreditList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return agriculturalCreditList;
    }

    /**
     * get card by card max Service Fee
     *
     * @param ACMaxServiceFee
     * @return
     */
    public List<AgriculturalCredit> getAgriculturalCreditByMaxServiceFee(int ACMaxServiceFee) {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `agriculturalcreditcontroller` WHERE acmaxservicefee=" + ACMaxServiceFee;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(agriculturalCreditList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return agriculturalCreditList;
    }

    /**
     * gert card by Card per factual
     *
     * @param cardFactual
     * @return
     */
    public List<AgriculturalCredit> getAgriculturalCreditByFactual(double cardFactual) {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT *  FROM  `agriculturalcreditcontroller` WHERE acfactual=" + cardFactual;
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(agriculturalCreditList, set);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return agriculturalCreditList;
    }

    /**
     * load cards with bank name
     *
     * @param bankName
     * @return
     */
    public List<AgriculturalCredit> LoadAgriculturalCreditBybankName(String bankName) {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` where `bankname` like '%" + bankName + "%'";

            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(agriculturalCreditList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return agriculturalCreditList;
    }

    /**
     * load card by bank web site
     *
     * @param bankWebSite
     * @return
     */
    public List<AgriculturalCredit> LoadAgriculturalCreditBybankWebSite(String bankWebSite) {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` where `bankwebsite` like '%" + bankWebSite + "%'";

            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(agriculturalCreditList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return agriculturalCreditList;
    }

    /**
     * load card by card currency
     *
     * @param Currency
     * @return
     */
    public List<AgriculturalCredit> LoadAgriculturalCreditByCurrency(String Currency) {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` where `currency` like '%" + Currency + "%'";
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCards(agriculturalCreditList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return agriculturalCreditList;
    }

    /**
     * cards order by order on appeaarance
     *
     * @return
     */
    public List<AgriculturalCredit> AgriculturalCreditOrderByOrderOnAppearance() {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` ORDER BY  orderonappearance DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(agriculturalCreditList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return agriculturalCreditList;

    }

    /**
     * Cards order by card id
     *
     * @return
     */
    public List<AgriculturalCredit> AgriculturalCreditOrderByProductId() {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` ORDER BY  acid DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(agriculturalCreditList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return agriculturalCreditList;

    }

    /**
     * Card order by bank name
     *
     * @return
     */
    public List<AgriculturalCredit> AgriculturalCreditOrderByBankName() {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` ORDER BY  bankname DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(agriculturalCreditList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return agriculturalCreditList;

    }

    /**
     * card order by card Name
     *
     * @return
     */
    public List<AgriculturalCredit> AgriculturalCreditOrderByProductName() {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` ORDER BY  productname DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(agriculturalCreditList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return agriculturalCreditList;

    }

    /**
     * card order by cardMinServiceFee
     *
     * @return
     */
    public List<AgriculturalCredit> AgriculturalCreditOrderByMinServiceFee() {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` ORDER BY  minservicefee DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(agriculturalCreditList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return agriculturalCreditList;

    }

    /**
     * card order by cardMaxServiceFee
     *
     * @return
     */
    public List<AgriculturalCredit> AgriculturalCreditOrderByMaxServiceFee() {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` ORDER BY  maxservicefee DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(agriculturalCreditList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return agriculturalCreditList;

    }

    /**
     * Add new agriculture credit
     *
     * @param agriculturalCredit
     * @return
     * @throws SQLException
     */
    public int AddNewAgriculturalCredit(AgriculturalCredit agriculturalCredit) throws SQLException {
        int rowsAffected = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `agriculturalcreditcontroller`" +
                    "(`acid`, `productcode`, `bankid`, `bankname`,`bankwebsite`,`minage`,`maxage`,`productname`,`ACminamount`," +
                    "`ACmaxamount`,`ACminloan`, `ACmaxloan`, `ACfactual`,`ACminperiodmonth`," +
                    "`ACmaxperiodmonth`,`ACminservicefee`,`ACmaxservicefee`, `orderofappearance`,`specialoffer`," +
                    "`firstsearchlist`,`sendrequest`,`currancy`,`GotoPage`,`LastLogic`,`BankLink`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(agriculturalCredit, statment);
            rowsAffected = statment.executeUpdate();
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
        }

        return rowsAffected;

    }

    /**
     * card order by cardPerFactual
     *
     * @return
     */
    public List<AgriculturalCredit> AgriculturalCreditOrderByFactual() throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        ResultSet set = null;
        Statement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` ORDER BY  factual DESC";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;

    }

    /**
     * card order by cardCurrency
     *
     * @return
     */
    public List<AgriculturalCredit> AgriculturalCreditOrderByCurrency() {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` ORDER BY  currency DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(agriculturalCreditList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return agriculturalCreditList;

    }

    /**
     * card order by cardSpecialOfferId
     *
     * @return
     */
    public List<AgriculturalCredit> AgriculturalCreditOrderBySpecialOfferId() {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` ORDER BY  specialofferid DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(agriculturalCreditList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return agriculturalCreditList;

    }

    /**
     * card order by cardSearchPositionId
     *
     * @return
     */
    public List<AgriculturalCredit> AgriculturalCreditOrderBySearchPositionId() {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` ORDER BY  searchpositionid DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(agriculturalCreditList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return agriculturalCreditList;

    }

    /**
     * card order by cardSendRequest
     *
     * @return
     */
    public List<AgriculturalCredit> CardsOrderByCardSendRequest() {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` ORDER BY  sendrequest DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCards(agriculturalCreditList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return agriculturalCreditList;

    }

    /**
     * delete Agriculture by id
     *
     * @param agId
     * @return
     */
    public int DeleteAfById(int agId) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        try {
            Connection connection = connectToData();

            String sql = "DELETE FROM `agriculturalcreditcontroller` WHERE  ACId=" + agId;
            statment = connection.prepareStatement(sql);

            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A Message was deleted successfully!");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in CATEGORY DELETE  Section  : " + exception);
            exception.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
        }
        return rowsDeleted;
    }

    /**
     * Update ag in data
     *
     * @param prepaireCardInfoForData
     * @param ACId
     * @return
     */
    public int updateAgInData(AgriculturalCredit prepaireCardInfoForData, int ACId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `agriculturalcreditcontroller`  " +
                    "SET productcode=?,bankid=?,bankname=?,bankwebsite=?,minage=?,maxage=?,productname=?,ACminamount=?,ACmaxamount=?,ACminloan=?,ACmaxloan=?,ACfactual=?,ACminperiodmonth=?,ACmaxperiodmonth=?,ACminservicefee=?,ACmaxservicefee=?,currancy=?,BankLink=?  " +
                    "WHERE ACId=" + ACId;
            statment = connection.prepareStatement(sql);
            setStatmentForNonMarketing(prepaireCardInfoForData, statment);

            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

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
        }
        return rowsUpdated;

    }

    public List<AgriculturalCredit> getAgricultureByBankIdOrderByAppearance(int bankId) throws SQLException {
        AgriculturalCredit AC = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        ResultSet set = null;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `agriculturalcreditcontroller` WHERE bankId=" + bankId + " ORDER BY `orderofappearance` ASC";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                AC = new AgriculturalCredit();
                AC.setProductCode(set.getInt("productCode"));
                AC.setBankId(set.getInt("bankId"));
                AC.setBankName(set.getString("bankName"));
                AC.setBankWebSite(set.getString("bankWebSite"));
                AC.setMinAge(set.getInt("minAge"));
                AC.setMaxAge(set.getInt("maxAge"));
                AC.setOrderOfAppearance(set.getInt("orderofappearance"));
                AC.setSpecialOffer(set.getInt("specialoffer"));
                AC.setFirstSearchList(set.getInt("firstsearchlist"));
                AC.setSendRequest(set.getInt("sendrequest"));
                AC.setCurrancy(set.getString("currancy"));
                AC.setProductName(set.getString("productname"));
                AC.setACId(set.getLong("ACId"));
                AC.setACMinAmount(set.getInt("ACMinAmount"));
                AC.setACMaxAmount(set.getInt("ACMaxAmount"));
                AC.setACMinLoan(set.getInt("ACMinLoan"));
                AC.setACMaxLoan(set.getInt("ACMaxLoan"));
                AC.setACFactual(set.getInt("ACFactual"));
                AC.setACMinPeriodMonth(set.getInt("ACMinPeriodMonth"));
                AC.setACMaxPeriodMonth(set.getInt("ACMaxPeriodMonth"));
                AC.setACMinServiceFee(set.getInt("ACMinServiceFee"));
                AC.setACMaxAmount(set.getInt("ACMaxAmount"));
                AC.setLastLogic(set.getInt("LastLogic"));

                agriculturalCreditList.add(AC);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
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
        return agriculturalCreditList;
    }

    /**
     * Setting statments for agriculture credits
     *
     * @param credit
     * @param statement
     * @throws SQLException
     */
    private void setStatmentForNonMarketing(AgriculturalCredit credit, PreparedStatement statement) throws SQLException {
        statement.setLong(1, credit.getProductCode());
        statement.setInt(2, credit.getBankId());
        statement.setString(3, credit.getBankName());
        statement.setString(4, credit.getBankWebSite());
        statement.setInt(5, credit.getMinAge());
        statement.setInt(6, credit.getMaxAge());
        statement.setString(7, credit.getProductName());
        statement.setDouble(8, credit.getACMinAmount());
        statement.setDouble(9, credit.getACMaxAmount());
        statement.setDouble(10, credit.getACMinLoan());
        statement.setDouble(11, credit.getACMaxLoan());
        statement.setDouble(12, credit.getACFactual());
        statement.setInt(13, credit.getACMinPeriodMonth());
        statement.setInt(14, credit.getACMaxPeriodMonth());
        statement.setInt(15, credit.getACMinServiceFee());
        statement.setInt(16, credit.getACMaxServiceFee());
        statement.setString(17, credit.getCurrancy());
        statement.setString(18, credit.getBankLink());

    }

    /**
     * Setting statments for agriculture credits
     *
     * @param credit
     * @param statement
     * @throws SQLException
     */
    private void setStatment(AgriculturalCredit credit, PreparedStatement statement) throws SQLException {
        statement.setLong(1, credit.getProductCode());
        statement.setInt(2, credit.getBankId());
        statement.setString(3, credit.getBankName());
        statement.setString(4, credit.getBankWebSite());
        statement.setInt(5, credit.getMinAge());
        statement.setInt(6, credit.getMaxAge());
        statement.setString(7, credit.getProductName());
        statement.setDouble(8, credit.getACMinAmount());
        statement.setDouble(9, credit.getACMaxAmount());
        statement.setDouble(10, credit.getACMinLoan());
        statement.setDouble(11, credit.getACMaxLoan());
        statement.setDouble(12, credit.getACFactual());
        statement.setInt(13, credit.getACMinPeriodMonth());
        statement.setInt(14, credit.getACMaxPeriodMonth());
        statement.setInt(15, credit.getACMinServiceFee());
        statement.setInt(16, credit.getACMaxServiceFee());
        statement.setInt(17, credit.getOrderOfAppearance());
        statement.setInt(18, credit.getSpecialOffer());
        statement.setInt(19, credit.getFirstSearchList());
        statement.setInt(20, credit.getSendRequest());
        statement.setString(21, credit.getCurrancy());
        statement.setInt(22, credit.getGotoPage());
        statement.setInt(23, credit.getLastLogic());
        statement.setString(24, credit.getBankLink());


    }

    /**
     * List cards in Set
     *
     * @param creditList
     * @param set
     */
    private void ListOfCards(List<AgriculturalCredit> creditList, ResultSet set) throws SQLException {
        AgriculturalCredit AC;
        while (set.next()) {
            AC = new AgriculturalCredit();
            AC.setProductCode(set.getInt("productCode"));
            AC.setBankId(set.getInt("bankId"));
            AC.setBankName(set.getString("bankName"));
            AC.setBankWebSite(set.getString("bankWebSite"));
            AC.setMinAge(set.getInt("minAge"));
            AC.setMaxAge(set.getInt("maxAge"));
            AC.setOrderOfAppearance(set.getInt("orderofappearance"));
            AC.setSpecialOffer(set.getInt("specialoffer"));
            AC.setFirstSearchList(set.getInt("firstsearchlist"));
            AC.setSendRequest(set.getInt("sendrequest"));
            AC.setCurrancy(set.getString("currancy"));
            AC.setGotoPage(set.getInt("gotoPage"));
            AC.setProductName(set.getString("productname"));
            AC.setACId(set.getLong("ACId"));
            AC.setACMinAmount(set.getInt("ACMinAmount"));
            AC.setACMaxAmount(set.getInt("ACMaxAmount"));
            AC.setACMinLoan(set.getDouble("ACMinLoan"));
            AC.setACMaxLoan(set.getDouble("ACMaxLoan"));
            AC.setACFactual(set.getDouble("ACFactual"));
            AC.setACMinPeriodMonth(set.getInt("ACMinPeriodMonth"));
            AC.setACMaxPeriodMonth(set.getInt("ACMaxPeriodMonth"));
            AC.setACMinServiceFee(set.getInt("ACMinServiceFee"));
            AC.setACMaxServiceFee(set.getInt("ACMaxServiceFee"));
            AC.setACMaxAmount(set.getInt("ACMaxAmount"));
            AC.setLastLogic(set.getInt("lastlogic"));
            AC.setBankLink(set.getString("bankLink"));
            AC.setBanksList(creditList);
            creditList.add(AC);
        }
    }

    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }


    public int UpdateAGMarketingInData(AgriculturalCredit prepaireMortgageInfoForDataFS, int getProductCode) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `agriculturalcreditcontroller`  " +
                    "SET orderofappearance=?,specialoffer=?,firstsearchlist=?," +
                    "sendrequest=?,GotoPage=?,LastLogic=? WHERE productcode=" + getProductCode;
            statement = connection.prepareStatement(sql);
            statement.setInt(1, prepaireMortgageInfoForDataFS.getOrderOfAppearance());
            statement.setInt(2, prepaireMortgageInfoForDataFS.getSpecialOffer());
            statement.setInt(3, prepaireMortgageInfoForDataFS.getFirstSearchList());
            statement.setInt(4, prepaireMortgageInfoForDataFS.getSendRequest());
            statement.setInt(5, prepaireMortgageInfoForDataFS.getGotoPage());
            statement.setInt(6, prepaireMortgageInfoForDataFS.getLastLogic());

            rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return rowsUpdated;
    }

    /**
     * @return
     */
    public List<AgriculturalCredit> getAGWithSpecialOffer() throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `specialoffer`=" + 1;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getAgWithAppearance() throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `firstsearchlist`=" + 1;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getDepositbyMaxMinAmount(int minAmount, int maxAmount, String currancy) throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `orderofappearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `ACminamount`<= " + minAmount + " AND `ACmaxamount`>=" + maxAmount + " ORDER BY `ACminamount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getDepositMaxMinAmount(int minAmount, String currancy) throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `currancy` like '%" + currancy + "%' AND `ACminamount`>= " + minAmount + "  ORDER BY `ACminamount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getDepositbyMaxMinAmountAsec(int maxAmount, int minAmount, String currancy) throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `orderofappearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `ACminamount`<= " + minAmount + " AND `ACmaxamount`>=" + maxAmount + " ORDER BY `ACminamount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getDepositMaxMinAmountSub(int minAmount, String currancy) throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `currancy` like '%" + currancy + "%' AND `ACminamount`>= " + minAmount + "  ORDER BY `ACminamount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getDepositMaxMinPercentSub(int minAmount, int maxAmount, String currancy) throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `orderofappearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `ACminamount`<= " + minAmount + " AND `ACmaxamount`>=" + maxAmount + " ORDER BY `ACfactual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getDepositMaxMinPercentSubDes(int minAmount, String currancy) throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `currancy` like '%" + currancy + "%' AND `ACminamount`>= " + minAmount + "  ORDER BY `ACfactual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getDepositMaxMinPercentSubAcs(int minAmount, int maxAmount, String currancy) throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `orderofappearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `ACminamount`<= " + minAmount + " AND `ACmaxamount`>=" + maxAmount + " ORDER BY `ACfactual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getDepositMaxMinPercentSubAsc(int minAmount, String currancy) throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `currancy` like '%" + currancy + "%' AND `ACminamount`>= " + minAmount + "  ORDER BY `ACfactual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getDepositbyMaxMinAmountByTime(int minAmount, int timeline, String currancy) throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `orderofappearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `ACminamount`<= " + minAmount + " AND `ACminperiodmonth`=" + timeline + "  ORDER BY `ACminperiodmonth` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getDepositMaxMinAmountByTime(int minAmount, int timeline, String currancy) throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `currancy` like '%" + currancy + "%' AND `ACminamount`>= " + minAmount + " AND `ACminperiodmonth`= " + timeline + " ORDER BY `ACminperiodmonth` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getDepositbyMaxMinAmountAsecByTime(int minAmount, int timeline, String currancy) throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `orderofappearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `ACminperiodmonth`<= " + minAmount + " AND `ACminperiodmonth`=" + timeline + " ORDER BY `ACminperiodmonth` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getDepositMaxMinAmountSubByTime(int minAmount, int timeline, String currancy) throws SQLException {
        AgriculturalCredit agriculturalCredit = null;
        List<AgriculturalCredit> agriculturalCreditList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `currancy` like '%" + currancy + "%' AND `ACminamount`>= " + minAmount + " AND `ACminperiodmonth`= "+timeline+"  ORDER BY `ACminperiodmonth` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(agriculturalCreditList, set);
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
        return agriculturalCreditList;
    }

    public List<AgriculturalCredit> getDepositbyMaxMinAmountById(int minAmount, String currancy, int BankId) throws SQLException {
        AgriculturalCredit deposit = null;
        List<AgriculturalCredit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `currancy` like '%" + currancy + "%' AND `bankid`=" + BankId + " ORDER BY `ACminamount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(depositList, set);
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

    public List<AgriculturalCredit> getDepositMaxMinPercentByID(int minAmount, String currancy, int BankId) throws SQLException {
        AgriculturalCredit deposit = null;
        List<AgriculturalCredit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `currancy` like '%" + currancy + "%' AND `bankid`=" + BankId + " ORDER BY `ACfactual` ASC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(depositList, set);
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

    public List<AgriculturalCredit> BankingDesc(int bankid) throws SQLException {
        AgriculturalCredit deposit = null;
        List<AgriculturalCredit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `bankid`=" + bankid + " ORDER BY `ACfactual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(depositList, set);
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
    public List<AgriculturalCredit> BankingAsc(int bankid) throws SQLException {
        AgriculturalCredit deposit = null;
        List<AgriculturalCredit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` WHERE `bankid`=" + bankid + " ORDER BY `ACfactual` ASC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCards(depositList, set);
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

    public String getAGProductNameByProductCode(int productCode) throws SQLException {
        String productName = null;
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT `productname` FROM `agriculturalcreditcontroller` WHERE `productcode`= " + productCode;
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