package com.ithome.web.AdminDao;

import com.ithome.web.Bean.ConsumerCredit;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumerCreditDaoController {
    /**
     * Show all cards
     */
    public List<ConsumerCredit> getAllCardsList() throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<ConsumerCredit>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `cccontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(consumerCreditList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in Admin Section  : " + exception);
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
        return consumerCreditList;
    }


    /**
     * Deleting cards
     *
     * @param CCId
     * @return
     */
    public int DeleteCC(int CCId) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "DELETE FROM `cccontroller` WHERE CLId=" + CCId;
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
     * Get cards by id
     *
     * @param ConsumerCreditid
     * @return
     */
    public List<ConsumerCredit> getConsumerCreditById(int ConsumerCreditid) throws SQLException {

        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `cccontroller` WHERE CLId=" + ConsumerCreditid;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCC(consumerCreditList, set);

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
        return consumerCreditList;
    }

    /**
     * Get Card list by productcode
     *
     * @param productcode
     * @return
     */
    public List<ConsumerCredit> getConsumerCreditByCardCode(int productcode) throws SQLException {

        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `cccontroller` WHERE productcode=" + productcode;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCC(consumerCreditList, set);

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
        return consumerCreditList;
    }

    /**
     * Get card by bank id
     *
     * @param bankId
     * @return
     */
    public List<ConsumerCredit> getConsumerCreditBybankId(int bankId) throws SQLException {

        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `cccontroller` WHERE bankid=" + bankId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCC(consumerCreditList, set);

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
        return consumerCreditList;
    }

    /**
     * load cards with bank name
     *
     * @param bankName
     * @return
     */
    public List<ConsumerCredit> LoadConsumerCreditBybankName(String bankName) {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` where `bankname` like '%" + bankName + "%'";

            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCC(consumerCreditList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return consumerCreditList;
    }

    /**
     * load ConsumerCredit by card name
     *
     * @param productName
     * @return
     */
    public List<ConsumerCredit> LoadConsumerCreditByproductName(String productName) {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` where `productName` like '%" + productName + "%'";

            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCC(consumerCreditList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return consumerCreditList;
    }

    public List<ConsumerCredit> getByproductId(int productcode) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `cccontroller` WHERE productCode=" + productcode;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCC(consumerCreditList, set);

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
        return consumerCreditList;
    }

    /**
     * load ConsumerCredit by ConsumerCredit currency
     *
     * @param Currency
     * @return
     */
    public List<ConsumerCredit> LoadConsumerCreditByCurrency(String Currency) {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` where `currency` like '%" + Currency + "%'";
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfCC(consumerCreditList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return consumerCreditList;
    }

    /**
     * ConsumerCredit order by order on appeaarance
     *
     * @return
     */
    public List<ConsumerCredit> ConsumerCreditOrderByOrderOnAppearance() {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` ORDER BY  orderonappearance DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCC(consumerCreditList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return consumerCreditList;

    }

    /**
     * ConsumerCredit order by bank name
     *
     * @return
     */
    public List<ConsumerCredit> ConsumerCreditOrderByBankName() {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` ORDER BY  bankname DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCC(consumerCreditList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return consumerCreditList;

    }

    /**
     * ConsumerCredit order by product name
     *
     * @return
     */
    public List<ConsumerCredit> ConsumerCreditOrderByProductName() {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` ORDER BY  productName DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfCC(consumerCreditList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return consumerCreditList;

    }

    /**
     * Update Consumer Credit
     *
     * @param prepaireConsumerCreditDetailInData
     * @param ccId
     * @return
     */
    public int updateConsumerCredit(ConsumerCredit prepaireConsumerCreditDetailInData, int ccId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `cccontroller`  " +
                "SET productcode=?," +
                "bankid=?,bankname=?,bankwebsite=?,productname=?,CCMinAmount=?,CCMaxAmount=?," +
                "CCMinLoan=?,CCMaxLoan=?,CCFatual=?,currancy=?," +
                "CCMinPeriodMonths=?,CCMaxPeriodMonths=?,CCMinServiceFee=?,CCMaxServiceFee=?,minAge=?,maxAge=? WHERE CLId=" + ccId;
            statment = connection.prepareStatement(sql);
            setStatmentForNonMarketing(prepaireConsumerCreditDetailInData, statment);

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

    private void setStatmentForNonMarketing(ConsumerCredit consumerCredit, PreparedStatement statement) throws SQLException {
        SetMaster(consumerCredit, statement);
    }


    public List<ConsumerCredit> getConsumerCreditByBankIdOrderByAppearance(int bankId) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `cccontroller` WHERE bankId=" + bankId + " ORDER BY `orderOfAppearance` ASC";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                consumerCredit = new ConsumerCredit();
                consumerCredit = new ConsumerCredit();
                consumerCredit.setCLId(set.getLong("CLId"));
                consumerCredit.setProductCode(set.getInt("productCode"));
                consumerCredit.setBankId(set.getInt("bankId"));
                consumerCredit.setBankName(set.getString("bankName"));
                consumerCredit.setBankWebSite(set.getString("bankWebSite"));
                consumerCredit.setProductName(set.getString("productName"));
                consumerCredit.setCCMinAmount(set.getInt("CCMinAmount"));
                consumerCredit.setCCMaxAmount(set.getInt("CCMaxAmount"));
                consumerCredit.setCCMinLoan(set.getInt("CCMinLoan"));
                consumerCredit.setCCMaxLoan(set.getInt("CCMaxLoan"));
                consumerCredit.setCCFactual(set.getInt("CCFatual"));
                consumerCredit.setCurrancy(set.getString("currancy"));
                consumerCredit.setCCMinPeriodMonth(set.getInt("CCMinPeriodMonths"));
                consumerCredit.setCCMaxPeriodMonth(set.getInt("CCMaxPeriodMonths"));
                consumerCredit.setCCMinServiceFee(set.getInt("CCMinServiceFee"));
                consumerCredit.setCCMaxServiceFee(set.getInt("CCMaxServiceFee"));
                consumerCredit.setMinAge(set.getInt("minAge"));
                consumerCredit.setMaxAge(set.getInt("maxAge"));
                consumerCredit.setOrderOfAppearance(set.getInt("orderOfAppearance"));
                consumerCredit.setSpecialOffer(set.getInt("specialOffer"));
                consumerCredit.setFirstSearchList(set.getInt("firstSearchList"));
                consumerCredit.setSendRequest(set.getInt("sendRequest"));
                consumerCredit.setLastlogic(set.getInt("LastLogic"));
                consumerCreditList.add(consumerCredit);
            }

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
        return consumerCreditList;
    }


    private void SetMaster(ConsumerCredit consumerCredit, PreparedStatement statement) throws SQLException {
        statement.setLong(1, consumerCredit.getProductCode());
        statement.setInt(2, consumerCredit.getBankId());
        statement.setString(3, consumerCredit.getBankName());
        statement.setString(4, consumerCredit.getBankWebSite());
        statement.setString(5, consumerCredit.getProductName());
        statement.setInt(6, consumerCredit.getCCMinAmount());
        statement.setInt(7, consumerCredit.getCCMaxAmount());
        statement.setDouble(8, consumerCredit.getCCMinLoan());
        statement.setDouble(9, consumerCredit.getCCMaxLoan());
        statement.setDouble(10, consumerCredit.getCCFactual());
        statement.setString(11, consumerCredit.getCurrancy());
        statement.setInt(12, consumerCredit.getCCMinPeriodMonth());
        statement.setInt(13, consumerCredit.getCCMaxPeriodMonth());
        statement.setInt(14, consumerCredit.getCCMinServiceFee());
        statement.setInt(15, consumerCredit.getCCMaxServiceFee());
        statement.setInt(16, consumerCredit.getMinAge());
        statement.setInt(17, consumerCredit.getMaxAge());

    }

    /**
     * Adding new ConsumerCredit
     *
     * @param consumerCredit
     * @return
     * @throws SQLException
     */
    public int AddNewConsumerCredit(ConsumerCredit consumerCredit) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `cccontroller`" +
                "(`CLId`, `productcode`, `bankid`, `bankname`,`bankwebsite`,`productName`,`CCMinAmount`,`CCMaxAmount`," +
                "`CCMinLoan`,`CCMaxLoan`, `CCFatual`, `currancy`,`CCMinPeriodMonths`," +
                "`CCMaxPeriodMonths`,`CCMinServiceFee`,`CCMaxServiceFee`, `minAge`,`maxAge`," +
                "`orderOfAppearance`,`specialOffer`,`firstSearchList`,`sendRequest`,`LastLogic`,`BankLink`) "
                + "VALUES (Default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(consumerCredit, statment);
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
     * Statment for ConsumerCredit
     *
     * @param consumerCredit
     * @param statement
     */
    private void setStatment(ConsumerCredit consumerCredit, PreparedStatement statement) throws SQLException {

        statement.setLong(1, consumerCredit.getProductCode());
        statement.setInt(2, consumerCredit.getBankId());
        statement.setString(3, consumerCredit.getBankName());
        statement.setString(4, consumerCredit.getBankWebSite());
        statement.setString(5, consumerCredit.getProductName());
        statement.setInt(6, consumerCredit.getCCMinAmount());
        statement.setInt(7, consumerCredit.getCCMaxAmount());
        statement.setDouble(8, consumerCredit.getCCMinLoan());
        statement.setDouble(9, consumerCredit.getCCMaxLoan());
        statement.setDouble(10, consumerCredit.getCCFactual());
        statement.setString(11, consumerCredit.getCurrancy());
        statement.setInt(12, consumerCredit.getCCMinPeriodMonth());
        statement.setInt(13, consumerCredit.getCCMaxPeriodMonth());
        statement.setInt(14, consumerCredit.getCCMinServiceFee());
        statement.setInt(15, consumerCredit.getCCMaxServiceFee());
        statement.setInt(16, consumerCredit.getMinAge());
        statement.setInt(17, consumerCredit.getMaxAge());
        statement.setInt(18, consumerCredit.getOrderOfAppearance());
        statement.setInt(19, consumerCredit.getSpecialOffer());
        statement.setInt(20, consumerCredit.getFirstSearchList());
        statement.setInt(21, consumerCredit.getSendRequest());
        statement.setInt(22, consumerCredit.getLastlogic());
        statement.setString(23, consumerCredit.getBankLink());

    }


    /**
     * Connect to database
     *
     * @return
     */
    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }

    /**
     * List of Consumer credit
     *
     * @param consumerCreditList
     * @param set
     */
    private void ListOfCC(List<ConsumerCredit> consumerCreditList, ResultSet set) throws SQLException {
        ConsumerCredit consumerCredit;
        while (set.next()) {
            consumerCredit = new ConsumerCredit();
            consumerCredit.setCLId(set.getLong("CLId"));
            consumerCredit.setProductCode(set.getInt("productCode"));
            consumerCredit.setBankId(set.getInt("bankId"));
            consumerCredit.setBankName(set.getString("bankName"));
            consumerCredit.setBankWebSite(set.getString("bankWebSite"));
            consumerCredit.setProductName(set.getString("productName"));
            consumerCredit.setCCMinAmount(set.getInt("CCMinAmount"));
            consumerCredit.setCCMaxAmount(set.getInt("CCMaxAmount"));
            consumerCredit.setCCMinLoan(set.getDouble("CCMinLoan"));
            consumerCredit.setCCMaxLoan(set.getDouble("CCMaxLoan"));
            consumerCredit.setCCFactual(set.getDouble("CCFatual"));
            consumerCredit.setCurrancy(set.getString("currancy"));
            consumerCredit.setCCMinPeriodMonth(set.getInt("CCMinPeriodMonths"));
            consumerCredit.setCCMaxPeriodMonth(set.getInt("CCMaxPeriodMonths"));
            consumerCredit.setCCMinServiceFee(set.getInt("CCMinServiceFee"));
            consumerCredit.setCCMaxServiceFee(set.getInt("CCMaxServiceFee"));
            consumerCredit.setMinAge(set.getInt("minAge"));
            consumerCredit.setMaxAge(set.getInt("maxAge"));
            consumerCredit.setOrderOfAppearance(set.getInt("orderOfAppearance"));
            consumerCredit.setSpecialOffer(set.getInt("specialOffer"));
            consumerCredit.setFirstSearchList(set.getInt("firstSearchList"));
            consumerCredit.setSendRequest(set.getInt("sendRequest"));
            consumerCredit.setGotoPage(set.getInt("gotoPage"));
            consumerCredit.setLastlogic(set.getInt("lastlogic"));
            consumerCredit.setBankLink(set.getString("bankLink"));
            consumerCredit.setBanksList(consumerCreditList);
            consumerCreditList.add(consumerCredit);
        }

    }


    public int UpdateConsumerMarketingInData(ConsumerCredit prepaireMortgageInfoForDataFS, int getProductCode) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `cccontroller`  " +
                "SET orderOfAppearance=?,specialOffer=?,firstSearchList=?," +
                "sendRequest=?,GotoPage=?,LastLogic=? WHERE productCode=" + getProductCode;
            statement = connection.prepareStatement(sql);
            statement.setInt(1, prepaireMortgageInfoForDataFS.getOrderOfAppearance());
            statement.setInt(2, prepaireMortgageInfoForDataFS.getSpecialOffer());
            statement.setInt(3, prepaireMortgageInfoForDataFS.getFirstSearchList());
            statement.setInt(4, prepaireMortgageInfoForDataFS.getSendRequest());
            statement.setInt(5, prepaireMortgageInfoForDataFS.getGotoPage());
            statement.setInt(6, prepaireMortgageInfoForDataFS.getLastlogic());

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

    public List<ConsumerCredit> getConsumerWithSpecialOffer() throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `specialOffer`=" + 1;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCC(consumerCreditList, set);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getConsumerCreditAppearance() throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `firstSearchList`=" + 1;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfCC(consumerCreditList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getDepositbyMaxMinAmount(int minAmount, int maxAmount, String pageCurrancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + pageCurrancy + "%' AND `CCMinAmount`<= " + minAmount + " AND `CCMaxAmount`>=" + maxAmount + " ORDER BY `CCMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(consumerCreditList, set);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getDepositMaxMinAmount(int minAmount, String currancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `currancy` like '%" + currancy + "%' AND `CCMinAmount`>= " + minAmount + "  ORDER BY `CCMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(consumerCreditList, set);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getDepositMaxMinAmountSub(int minAmount, String currancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `currancy` like '%" + currancy + "%' AND `CCMinAmount`>= " + minAmount + "  ORDER BY `CCMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(consumerCreditList, set);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getDepositbyMaxMinAmountAsec(int minAmount, int maxAmount, String currancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `CCMinAmount`<= " + minAmount + " AND `CCMaxAmount`>=" + maxAmount + " ORDER BY `CCMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(consumerCreditList, set);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getDepositMaxMinPercentSub(int minAmount, int maxAmount, String currancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `CCMinAmount`<= " + minAmount + " AND `CCMaxAmount`>=" + maxAmount + " ORDER BY `CCFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(consumerCreditList, set);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getDepositMaxMinPercentSubDes(int minAmount, String currancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `currancy` like '%" + currancy + "%' AND `CCMinAmount`>= " + minAmount + "  ORDER BY `CCFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(consumerCreditList, set);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getDepositMaxMinPercentSubAcs(int minAmount, int maxAmount, String currancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `CCMinAmount`<= " + minAmount + " AND `CCMaxAmount`>=" + maxAmount + " ORDER BY `CCFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(consumerCreditList, set);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getDepositMaxMinPercentSubAsc(int minAmount, String currancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `currancy` like '%" + currancy + "%' AND `CCMinAmount`>= " + minAmount + "  ORDER BY `CCFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(consumerCreditList, set);
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
        return consumerCreditList;
    }


    public List<ConsumerCredit> getDepositbyMaxMinAmountByTime(int minAmount, int timeline, String currancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `CCMinAmount`<= " + minAmount + " AND `CCMinPeriodMonths`=" + timeline + "  ORDER BY `CCMaxPeriodMonths` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(consumerCreditList, set);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getDepositMaxMinAmountByTime(int minAmount, int timeline, String currancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `currancy` like '%" + currancy + "%' AND `CCMinAmount`>= " + minAmount + " AND `CCMinPeriodMonths`= " + timeline + " ORDER BY `CCMinPeriodMonths` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(consumerCreditList, set);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getDepositbyMaxMinAmountAsecByTime(int minAmount, int timeline, String currancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `CCMinAmount`<= " + minAmount + " AND `CCMinPeriodMonths`=" + timeline + " ORDER BY `CCMinPeriodMonths` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(consumerCreditList, set);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getDepositMaxMinAmountSubByTime(int minAmount, int timeline, String currancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `currancy` like '%" + currancy + "%' AND `CCMinAmount`>= " + minAmount + " AND `CCMinPeriodMonths`= " + timeline + "  ORDER BY `CCMinPeriodMonths` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(consumerCreditList, set);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getDepositbyMaxMinAmountById(int minAmount, String currancy, int BankId) throws SQLException {
        ConsumerCredit deposit = null;
        List<ConsumerCredit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `currancy` like '%" + currancy + "%' AND `bankId`=" + BankId + " ORDER BY `CCMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(depositList, set);
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

    public List<ConsumerCredit> getDepositMaxMinPercentByID(int minAmount, String currancy, int BankId) throws SQLException {
        ConsumerCredit deposit = null;
        List<ConsumerCredit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `currancy` like '%" + currancy + "%' AND `bankId`=" + BankId + " ORDER BY `CCFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(depositList, set);
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

    public List<ConsumerCredit> BankingAsc(int BankId) throws SQLException {
        ConsumerCredit deposit = null;
        List<ConsumerCredit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `bankId`=" + BankId + " ORDER BY `CCFatual` ASC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(depositList, set);
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

    public List<ConsumerCredit> BankingDesc(int BankId) throws SQLException {
        ConsumerCredit deposit = null;
        List<ConsumerCredit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `cccontroller` WHERE `bankId`=" + BankId + " ORDER BY `CCFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCC(depositList, set);
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

    public String getConsumerProductNameByProductCode(int productCode) throws SQLException {
        String productName = null;
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT `productName` FROM `cccontroller` WHERE `productCode`= " + productCode;
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

    public List<ConsumerCredit> getFilterdOrderOne(int amount, String pageCurrancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<ConsumerCredit>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `cccontroller` " +
                " left join `bankscontroller`" +
                " on `bankscontroller`.`bankId` = `cccontroller`.`bankId`" +
                " left join `commentscontroller`" +
                " on `cccontroller`.`productCode` = `commentscontroller`.`productCode` WHERE `currancy` = '" + pageCurrancy +
                "' AND `CCMinAmount` <= " + amount +
                " AND `CCMaxAmount` >= " + amount +
                " AND `firstSearchList` = 1";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCCComments(consumerCreditList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in getFilterdOrderOne Section  : " + exception);
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
        return consumerCreditList;

    }

    private void ListOfCCComments(List<ConsumerCredit> consumerCreditList, ResultSet set) throws SQLException {
        ConsumerCredit consumerCredit;
        while (set.next()) {
            consumerCredit = new ConsumerCredit();
            consumerCredit.setCLId(set.getLong("CLId"));
            consumerCredit.setProductCode(set.getInt("productCode"));
            consumerCredit.setBankId(set.getInt("bankId"));
            consumerCredit.setBankName(set.getString("bankName"));
            consumerCredit.setBankWebSite(set.getString("bankWebSite"));
            consumerCredit.setProductName(set.getString("productName"));
            consumerCredit.setCCMinAmount(set.getInt("CCMinAmount"));
            consumerCredit.setCCMaxAmount(set.getInt("CCMaxAmount"));
            consumerCredit.setCCMinLoan(set.getDouble("CCMinLoan"));
            consumerCredit.setCCMaxLoan(set.getDouble("CCMaxLoan"));
            consumerCredit.setCCFactual(set.getDouble("CCFatual"));
            consumerCredit.setCurrancy(set.getString("currancy"));
            consumerCredit.setCCMinPeriodMonth(set.getInt("CCMinPeriodMonths"));
            consumerCredit.setCCMaxPeriodMonth(set.getInt("CCMaxPeriodMonths"));
            consumerCredit.setCCMinServiceFee(set.getInt("CCMinServiceFee"));
            consumerCredit.setCCMaxServiceFee(set.getInt("CCMaxServiceFee"));
            consumerCredit.setMinAge(set.getInt("minAge"));
            consumerCredit.setMaxAge(set.getInt("maxAge"));
            consumerCredit.setOrderOfAppearance(set.getInt("orderOfAppearance"));
            consumerCredit.setSpecialOffer(set.getInt("specialOffer"));
            consumerCredit.setFirstSearchList(set.getInt("firstSearchList"));
            consumerCredit.setSendRequest(set.getInt("sendRequest"));
            consumerCredit.setGotoPage(set.getInt("gotoPage"));
            consumerCredit.setLastlogic(set.getInt("lastlogic"));
            consumerCredit.setBankLink(set.getString("bankLink"));
            consumerCredit.setComment1_Am(set.getString("Comment1_Am"));
            consumerCredit.setComment2_Am(set.getString("Comment2_Am"));
            consumerCredit.setComment3_Am(set.getString("Comment3_Am"));
            consumerCredit.setComment1_Ru(set.getString("Comment1_Ru"));
            consumerCredit.setComment2_Ru(set.getString("Comment2_Ru"));
            consumerCredit.setComment3_Ru(set.getString("Comment3_Ru"));
            consumerCredit.setBankBigLogo(set.getString("bankBigLogo"));
            consumerCredit.setBankSmallLogo(set.getString("bankSmallLogo"));
            consumerCredit.setBanksList(consumerCreditList);
            consumerCreditList.add(consumerCredit);
        }
    }

    public List<ConsumerCredit> getFilterdOrderMoreOne(int amount, String pageCurrancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<ConsumerCredit>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `cccontroller` " +
                " left join `bankscontroller`" +
                " on `bankscontroller`.`bankId` = `cccontroller`.`bankId`" +
                " left join `commentscontroller`" +
                " on `cccontroller`.`productCode` = `commentscontroller`.`productCode` WHERE `currancy` = '" + pageCurrancy +
                "' AND `CCMinAmount` <= " + amount +
                " AND `CCMaxAmount` >= " + amount +
                " AND `orderOfAppearance` = 1";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCCComments(consumerCreditList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in getFilterdOrderMoreOne Section  : " + exception);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getFilterdOrderSubMoreOne(int amount, String pageCurrancy) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<ConsumerCredit>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `cccontroller` " +
                " left join `bankscontroller`" +
                " on `bankscontroller`.`bankId` = `cccontroller`.`bankId`" +
                " left join `commentscontroller`" +
                " on `cccontroller`.`productCode` = `commentscontroller`.`productCode` WHERE `currancy` = '" + pageCurrancy +
                "' AND `CCMinAmount` <= " + amount +
                " AND `CCMaxAmount` >= " + amount +
                " AND `orderOfAppearance` > 1";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCCComments(consumerCreditList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in getFilterdOrderMoreOne Section  : " + exception);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getFilterdOrderOneWithMonths(int amount, String pageCurrancy, int months) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<ConsumerCredit>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `cccontroller` " +
                " left join `bankscontroller`" +
                " on `bankscontroller`.`bankId` = `cccontroller`.`bankId`" +
                " left join `commentscontroller`" +
                " on `cccontroller`.`productCode` = `commentscontroller`.`productCode` WHERE `currancy` = '" + pageCurrancy +
                "' AND `CCMinAmount` <= " + amount +
                " AND `CCMaxAmount` >= " + amount +
                " AND `CCMinPeriodMonths` <= " + months +
                " AND `CCMaxPeriodMonths` >= " + months;
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCCComments(consumerCreditList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in getFilterdOrderMoreOne Section  : " + exception);
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
        return consumerCreditList;
    }

    public List<ConsumerCredit> getFilterdOrderMoreOneMonths(int amount, String pageCurrancy, int months) throws SQLException {
        ConsumerCredit consumerCredit = null;
        List<ConsumerCredit> consumerCreditList = new ArrayList<ConsumerCredit>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `cccontroller` " +
                " left join `bankscontroller`" +
                " on `bankscontroller`.`bankId` = `cccontroller`.`bankId`" +
                " left join `commentscontroller`" +
                " on `cccontroller`.`productCode` = `commentscontroller`.`productCode` WHERE `currancy` = '" + pageCurrancy +
                "' AND `CCMinAmount` <= " + amount +
                " AND `CCMaxAmount` >= " + amount +
                " AND `CCMinPeriodMonths` <= " + months +
                " AND `CCMaxPeriodMonths` >= " + months;
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfCCComments(consumerCreditList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in getFilterdOrderMoreOne Section  : " + exception);
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
        return consumerCreditList;
    }
}
