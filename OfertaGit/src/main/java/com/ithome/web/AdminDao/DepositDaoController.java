package com.ithome.web.AdminDao;

import com.ithome.web.Bean.Deposit;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepositDaoController {
    /**
     * Show all cards
     */
    public List<Deposit> getAllDepositeList() throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<Deposit>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDeposite(depositList, set);
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
        return depositList;
    }

    /**
     * Get cards by id
     *
     * @param depositId
     * @return
     */
    public List<Deposit> getDepositById(int depositId) throws SQLException {

        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `depositecontroller` WHERE DId=" + depositId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfDeposite(depositList, set);

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
        return depositList;
    }

    /**
     * Get Card list by Card code
     *
     * @param productcode
     * @return
     */
    public List<Deposit> getDepositByCardCode(int productcode) throws SQLException {

        Deposit cards = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `depositecontroller` WHERE productcode=" + productcode;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            specialDeposit(depositList,set);
            ListOfDeposite(depositList, set);

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
        return depositList;
    }

    private void specialDeposit(List<Deposit> depositList, ResultSet set) throws SQLException {
        Deposit deposit;
        while (set.next()) {
            deposit = new Deposit();
            deposit.setDId(set.getLong("DId"));
            deposit.setProductCode(set.getInt("productCode"));
            deposit.setBankId(set.getInt("bankId"));
            deposit.setBankName(set.getString("bankName"));
            deposit.setBankWebSite(set.getString("bankWebSite"));
            deposit.setProductName(set.getString("productName"));
            deposit.setDMinAmount(set.getInt("DMinAmount"));
            deposit.setDMaxAmount(set.getInt("DMaxAmount"));
            deposit.setDPercentage(set.getDouble("DPercentage"));
            deposit.setCurrancy(set.getString("currancy"));
            deposit.setMinAge(set.getInt("minAge"));
            deposit.setMaxAge(set.getInt("maxAge"));
            deposit.setDEquippingPossibilitiesid(set.getInt("DEquippingPossibilitiesid"));
            deposit.setDEquippingPossibilities(set.getString("DEquippingPossibilities"));
            deposit.setDEarlierWithdrawalAmountid(set.getInt("DEarlierWithdrawalAmountid"));
            deposit.setDEarlierWithdrawalAmount(set.getString("DEarlierWithdrawalAmount"));
            deposit.setDAutoExtendPeriodid(set.getInt("DAutoExtendPeriodid"));
            deposit.setDAutoExtendPeriod(set.getString("DAutoExtendPeriod"));
            deposit.setDCapitalizationPercentid(set.getInt("DCapitalizationPercentid"));
            deposit.setDCapitalizationPercent(set.getString("DCapitalizationPercent"));
            deposit.setOrderOfAppearance(set.getInt("orderOfAppearance"));
            deposit.setGotoPage(set.getInt("gotoPage"));
            deposit.setSendRequest(set.getInt("sendRequest"));
            deposit.setFirstSearchList(set.getInt("firstSearchList"));
            deposit.setSpecialOffer(set.getInt("specialOffer"));
            deposit.setLastLogic(set.getInt("lastLogic"));
            deposit.setTimeLine(set.getInt("timeline"));

            deposit.setAmdMonth1(set.getDouble("amd1"));
            deposit.setAmdMonth3(set.getDouble("amd3"));
            deposit.setAmdMonth6(set.getDouble("amd6"));
            deposit.setAmdMonth9(set.getDouble("amd9"));
            deposit.setAmdMonth12(set.getDouble("amd12"));
            deposit.setAmdMonth18(set.getDouble("amd18"));
            deposit.setAmdMonth24(set.getDouble("amd24"));
            deposit.setAmdMonth36(set.getDouble("amd36"));


            deposit.setUsdMonth1(set.getDouble("usd1"));
            deposit.setUsdMonth3(set.getDouble("usd3"));
            deposit.setUsdMonth6(set.getDouble("usd6"));
            deposit.setUsdMonth9(set.getDouble("usd9"));
            deposit.setUsdMonth12(set.getDouble("usd12"));
            deposit.setUsdMonth18(set.getDouble("usd18"));
            deposit.setUsdMonth24(set.getDouble("usd24"));
            deposit.setUsdMonth36(set.getDouble("usd36"));

            deposit.setEurMonth1(set.getDouble("eur1"));
            deposit.setEurMonth3(set.getDouble("eur3"));
            deposit.setEurMonth6(set.getDouble("eur6"));
            deposit.setEurMonth9(set.getDouble("eur9"));
            deposit.setEurMonth12(set.getDouble("eur12"));
            deposit.setEurMonth18(set.getDouble("eur18"));
            deposit.setEurMonth24(set.getDouble("eur24"));
            deposit.setEurMonth36(set.getDouble("eur36"));

            deposit.setRubMonth1(set.getDouble("rub1"));
            deposit.setRubMonth3(set.getDouble("rub3"));
            deposit.setRubMonth6(set.getDouble("rub6"));
            deposit.setRubMonth9(set.getDouble("rub9"));
            deposit.setRubMonth12(set.getDouble("rub12"));
            deposit.setRubMonth18(set.getDouble("rub18"));
            deposit.setRubMonth24(set.getDouble("rub24"));
            deposit.setRubMonth36(set.getDouble("rub36"));

            deposit.setBankLink(set.getString("BankLink"));
            depositList.add(deposit);


         /*   deposit.setUsdMonth1(set.getDouble("usd1"));
            deposit.setUsdMonth3(set.getDouble("usd3"));
            deposit.setUsdMonth6(set.getDouble("usd6"));
            deposit.setUsdMonth9(set.getDouble("usd9"));
            deposit.setUsdMonth12(set.getDouble("usd12"));
            deposit.setUsdMonth18(set.getDouble("usd18"));
            deposit.setUsdMonth24(set.getDouble("usd24"));
            deposit.setUsdMonth36(set.getDouble("usd36"));*/
        }

    }
    /**
     * Get card by bank id
     *
     * @param bankId
     * @return
     */
    public List<Deposit> getDepositBybankId(int bankId) throws SQLException {

        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `depositecontroller` WHERE bankId=" + bankId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfDeposite(depositList, set);

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
        return depositList;
    }

    /**
     * @return
     */
    public List<Deposit> getDepositWithSpecialOffer() throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `specialOffer`=" + 1;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfDepositeWithList(depositList, set);
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
        return depositList;
    }

    public List<Deposit> getDepositWithAppearance() throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `firstSearchList`=" + 1;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfDepositeWithList(depositList, set);
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
        return depositList;
    }


    /**
     * @param depositList
     * @param set
     * @throws SQLException
     */
    private void ListOfDepositeWithList(List<Deposit> depositList, ResultSet set) throws SQLException {
        Deposit deposit;

        while (set.next()) {
            deposit = new Deposit();
            deposit.setDId(set.getLong("DId"));
            deposit.setProductCode(set.getInt("productCode"));
            deposit.setBankId(set.getInt("bankId"));
            deposit.setBankName(set.getString("bankName"));
            deposit.setBankWebSite(set.getString("bankWebSite"));
            deposit.setProductName(set.getString("productName"));
            deposit.setDMinAmount(set.getInt("DMinAmount"));
            deposit.setDMaxAmount(set.getInt("DMaxAmount"));
            deposit.setDPercentage(set.getDouble("DPercentage"));
            deposit.setCurrancy(set.getString("currancy"));
            deposit.setMinAge(set.getInt("minAge"));
            deposit.setMaxAge(set.getInt("maxAge"));
            deposit.setDEquippingPossibilitiesid(set.getInt("DEquippingPossibilitiesid"));
            deposit.setDEquippingPossibilities(set.getString("DEquippingPossibilities"));
            deposit.setDEarlierWithdrawalAmountid(set.getInt("DEarlierWithdrawalAmountid"));
            deposit.setDEarlierWithdrawalAmount(set.getString("DEarlierWithdrawalAmount"));
            deposit.setDAutoExtendPeriodid(set.getInt("DAutoExtendPeriodid"));
            deposit.setDAutoExtendPeriod(set.getString("DAutoExtendPeriod"));
            deposit.setDCapitalizationPercentid(set.getInt("DCapitalizationPercentid"));
            deposit.setDCapitalizationPercent(set.getString("DCapitalizationPercent"));
            deposit.setOrderOfAppearance(set.getInt("orderOfAppearance"));
            deposit.setGotoPage(set.getInt("gotoPage"));
            deposit.setSendRequest(set.getInt("sendRequest"));
            deposit.setFirstSearchList(set.getInt("firstSearchList"));
            deposit.setSpecialOffer(set.getInt("specialOffer"));
            deposit.setLastLogic(set.getInt("lastLogic"));
            deposit.setTimeLine(set.getInt("timeLine"));

            deposit.setBanksList(depositList);
            depositList.add(deposit);
        }
    }

    /**
     * load cards with bank name
     *
     * @param bankName
     * @return
     */
    public List<Deposit> LoadDepositBybankName(String bankName) {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` where `bankname` like '%" + bankName + "%'";

            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfDeposite(depositList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return depositList;
    }


    /**
     * load Deposit by card name
     *
     * @param productName
     * @return
     */
    public List<Deposit> LoadDepositByproductName(String productName) {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` where `productName` like '%" + productName + "%'";

            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfDeposite(depositList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return depositList;
    }

    /**
     * load Deposit by Deposit currency
     *
     * @param Currency
     * @return
     */
    public List<Deposit> LoadDepositByCurrency(String Currency) {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + Currency + "%' ORDER BY 'bankId'";
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            ListOfDepositeWithList(depositList, set);
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in Admin Section  : " + exception);
            exception.printStackTrace();
        }
        return depositList;
    }


    /**
     * Deposit order by order on appeaarance
     *
     * @return
     */
    public List<Deposit> DepositOrderByOrderOnAppearance() {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` ORDER BY  orderOfAppearance DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfDepositeWithList(depositList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return depositList;

    }

    /**
     * Deposit order by bank name
     *
     * @return
     */
    public List<Deposit> DepositOrderByBankName() {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` ORDER BY  bankname DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfDeposite(depositList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return depositList;

    }

    /**
     * Deposit order by product name
     *
     * @return
     */
    public List<Deposit> DepositOrderByProductName() {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` ORDER BY  productName DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                ListOfDeposite(depositList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return depositList;

    }

    /**
     * Adding new deposit
     *
     * @param deposit
     * @return
     * @throws SQLException
     */
    public int AddNewDeposit(Deposit deposit) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `depositecontroller`" +
                    "(`DId`, `productcode`, `bankid`, `bankname`,`bankwebsite`,`productName`,`DMinAmount`,`DMaxAmount`," +
                    "`DPercentage`,`currancy`,`timeline`, `minAge`, `maxAge`,`DEquippingPossibilitiesid`," +
                    "`DEquippingPossibilities`,`DEarlierWithdrawalAmountid`,`DEarlierWithdrawalAmount`, `DAutoExtendPeriodid`,`DAutoExtendPeriod`," +
                    "`DCapitalizationPercentid`,`DCapitalizationPercent`,`OrderOfAppearance`, `SpecialOffer`,`FirstSearchList`,`SendRequest`,`BankLink`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(deposit, statment);
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
     * Deleting cards
     *
     * @param depositId
     * @return
     */
    public int DeleteDeposit(int depositId) throws SQLException {

        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "DELETE FROM `depositecontroller` WHERE DId=" + depositId;
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

    public int getMinAmountByCurrancy(String pageCurrancy) {
        int minAmount = 0;
        try {
            Connection connection = connectToData();

            String sql = "SELECT * FROM `depositecontroller` WHERE currancy like '%" + pageCurrancy + "%'";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                minAmount = set.getInt("DMinAmount");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in CATEGORY DELETE  Section  : " + exception);
            exception.printStackTrace();
        }
        return minAmount;
    }

    public int getMaxAmountByCurrancy(String pageCurrancy) {
        int maxAmount = 0;
        try {
            Connection connection = connectToData();

            String sql = "SELECT * FROM `depositecontroller` WHERE currancy like '%" + pageCurrancy + "%'";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                maxAmount = set.getInt("DMaxAmount");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in CATEGORY DELETE  Section  : " + exception);
            exception.printStackTrace();
        }
        return maxAmount;
    }

    public List<Deposit> getDepositMaxMinAmount(int minAmount, String currancy) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `currancy` like '%" + currancy + "%' AND `DMinAmount`<= " + minAmount + "  ORDER BY `DMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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
        return depositList;
    }

    public List<Deposit> getDepositMaxMinAmountByTime(int minAmount,int timeline, String currancy) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `currancy` like '%" + currancy + "%' AND `DMinAmount`<= " + minAmount + " AND `timeline`= " + timeline + " ORDER BY `timeline` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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
        return depositList;
    }

    public List<Deposit> getDepositMaxMinAmountSub(int minAmount, String currancy) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `currancy` like '%" + currancy + "%' AND `DMinAmount`<= " + minAmount + "  ORDER BY `DMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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
        return depositList;
    }

    public List<Deposit> getDepositMaxMinAmountSubByTime(int minAmount,int timeline, String currancy) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `currancy` like '%" + currancy + "%' AND `DMinAmount`<= " + minAmount + " AND `timeline`= "+timeline+"  ORDER BY `timeline` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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
        return depositList;
    }


    public List<Deposit> getDepositMaxMinPercentSubAsc(int minAmount, String currancy) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `currancy` like '%" + currancy + "%' AND `DMinAmount`<= " + minAmount + " ORDER BY `DPercentage` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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
        return depositList;
    }


    public List<Deposit> getDepositMaxMinPercentSubDes(int minAmount, String currancy) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `currancy` like '%" + currancy + "%' AND `DMinAmount`<= " + minAmount + "  ORDER BY `DPercentage` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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
        return depositList;
    }


    public List<Deposit> getDepositbyMaxMinAmount(int minAmount, String currancy) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `DMinAmount`<= " + minAmount + " ORDER BY `DMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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

    public List<Deposit> getDepositbyMaxMinAmountByTime(int minAmount, int timeline, String currancy) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `DMinAmount`<= " + minAmount + " AND `timeline`=" + timeline + "  ORDER BY `timeline` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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

    public List<Deposit> getDepositbyMaxMinAmountAsec(int minAmount, String currancy) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `DMinAmount`<= " + minAmount + " ORDER BY `DMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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

    public List<Deposit> getDepositbyMaxMinAmountAsecByTime(int minAmount, int timeline, String currancy) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `minAmount`<= " + minAmount + " AND `timeline`=" + timeline + " ORDER BY `timeline` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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

    public List<Deposit> getDepositMaxMinPercentSub(int minAmount,  String currancy) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `DMinAmount`<= " + minAmount + "  ORDER BY `DPercentage` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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


    public List<Deposit> getDepositMaxMinPercentSubAcs(int minAmount, int maxAmount, String currancy) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `DMinAmount`<= " + minAmount + " ORDER BY `DPercentage` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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

    public List<Deposit> BankingDesc(int BankId) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `bankId`=" + BankId + "  ORDER BY `DPercentage` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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
    public List<Deposit> BankingAsc(int BankId) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `bankId`=" + BankId + "  ORDER BY `DPercentage` ASC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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

    public List<Deposit> getSemiDepositbyMaxMinAmount(int minAmount, int maxAmount, String currancy) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `currancy` like '%" + currancy + "%' AND `DMinAmount`<= " + minAmount + " ORDER BY `DMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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

    /**
     * Update Deposit
     *
     * @param prepaireDepositInfoForData
     * @param dId
     * @return
     */
    public int UpdateDepositeInData(Deposit prepaireDepositInfoForData, int dId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `depositecontroller`  " +
                    "SET productcode=?,bankid=?,bankname=?,bankwebsite=?,productName=?,DMinAmount=?,DMaxAmount=?," +
                    "DPercentage=?,currancy=?, timeline=?,minAge=?,maxAge=?,DEquippingPossibilitiesid=?,DEquippingPossibilities=?," +
                    "DEarlierWithdrawalAmountid=?,DEarlierWithdrawalAmount=?,DAutoExtendPeriodid=?,DAutoExtendPeriod=?,DCapitalizationPercentid=?,DCapitalizationPercent=?,BankLink=?  " +
                    "WHERE DId=" + dId;
            statment = connection.prepareStatement(sql);
            setStatmentForNonMarketing(prepaireDepositInfoForData, statment);

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


    public int UpdateAMDInData(Deposit prepaireDepositInfoForData, int dId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `depositecontroller`  " +
                    "SET amd1=?,amd3=?,amd6=?,amd9=?,amd12=?,amd18=?," +
                    "amd24=?,amd36=? WHERE productCode=" + dId;
            statment = connection.prepareStatement(sql);
            setStatmentForAMD(prepaireDepositInfoForData, statment);

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


    public int UpdateUSDInData(Deposit prepaireDepositInfoForData, int dId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `depositecontroller`  " +
                    "SET usd1=?,usd3=?,usd6=?,usd9=?,usd12=?,usd18=?," +
                    "usd24=?,usd36=? WHERE productCode=" + dId;
            statment = connection.prepareStatement(sql);
            setStatmentForUSD(prepaireDepositInfoForData, statment);

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

    public int UpdateEURInData(Deposit prepaireDepositInfoForData, int dId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `depositecontroller`  " +
                    "SET eur1=?,eur3=?,eur6=?,eur9=?,eur12=?,eur18=?," +
                    "eur24=?,eur36=? WHERE productCode=" + dId;
            statment = connection.prepareStatement(sql);
            setStatmentForEUR(prepaireDepositInfoForData, statment);

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

    public int UpdateRUBInData(Deposit prepaireDepositInfoForData, int dId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `depositecontroller`  " +
                    "SET rub1=?,rub3=?,rub6=?,rub9=?,rub12=?,rub18=?," +
                    "rub24=?,rub36=? WHERE productCode=" + dId;
            statment = connection.prepareStatement(sql);
            setStatmentForRUB(prepaireDepositInfoForData, statment);

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

    private void setStatmentForAMD(Deposit prepaireDepositInfoForData, PreparedStatement statement) throws SQLException {
        statement.setDouble(1, prepaireDepositInfoForData.getAmdMonth1());
        statement.setDouble(2, prepaireDepositInfoForData.getAmdMonth3());
        statement.setDouble(3, prepaireDepositInfoForData.getAmdMonth6());
        statement.setDouble(4, prepaireDepositInfoForData.getAmdMonth9());
        statement.setDouble(5, prepaireDepositInfoForData.getAmdMonth12());
        statement.setDouble(6, prepaireDepositInfoForData.getAmdMonth18());
        statement.setDouble(7, prepaireDepositInfoForData.getAmdMonth24());
        statement.setDouble(8, prepaireDepositInfoForData.getAmdMonth36());

    }

    private void setStatmentForUSD(Deposit prepaireDepositInfoForData2, PreparedStatement statement) throws SQLException {
        statement.setDouble(1, prepaireDepositInfoForData2.getUsdMonth1());
        statement.setDouble(2, prepaireDepositInfoForData2.getUsdMonth3());
        statement.setDouble(3, prepaireDepositInfoForData2.getUsdMonth6());
        statement.setDouble(4, prepaireDepositInfoForData2.getUsdMonth9());
        statement.setDouble(5, prepaireDepositInfoForData2.getUsdMonth12());
        statement.setDouble(6, prepaireDepositInfoForData2.getUsdMonth18());
        statement.setDouble(7, prepaireDepositInfoForData2.getUsdMonth24());
        statement.setDouble(8, prepaireDepositInfoForData2.getUsdMonth36());

    }

    private void setStatmentForEUR(Deposit prepaireDepositInfoForData, PreparedStatement statement) throws SQLException {
        statement.setDouble(1, prepaireDepositInfoForData.getEurMonth1());
        statement.setDouble(2, prepaireDepositInfoForData.getEurMonth3());
        statement.setDouble(3, prepaireDepositInfoForData.getEurMonth6());
        statement.setDouble(4, prepaireDepositInfoForData.getEurMonth9());
        statement.setDouble(5, prepaireDepositInfoForData.getEurMonth12());
        statement.setDouble(6, prepaireDepositInfoForData.getEurMonth18());
        statement.setDouble(7, prepaireDepositInfoForData.getEurMonth24());
        statement.setDouble(8, prepaireDepositInfoForData.getEurMonth36());

    }

    private void setStatmentForRUB(Deposit prepaireDepositInfoForData, PreparedStatement statement) throws SQLException {
        statement.setDouble(1, prepaireDepositInfoForData.getRubMonth1());
        statement.setDouble(2, prepaireDepositInfoForData.getRubMonth3());
        statement.setDouble(3, prepaireDepositInfoForData.getRubMonth6());
        statement.setDouble(4, prepaireDepositInfoForData.getRubMonth9());
        statement.setDouble(5, prepaireDepositInfoForData.getRubMonth12());
        statement.setDouble(6, prepaireDepositInfoForData.getRubMonth18());
        statement.setDouble(7, prepaireDepositInfoForData.getRubMonth24());
        statement.setDouble(8, prepaireDepositInfoForData.getRubMonth36());

    }

    public List<Deposit> getDepositeByBankIdOrderByAppearance(int bankId) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        PreparedStatement statment = null;
        Connection connection = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `depositecontroller` WHERE bankId=" + bankId + " ORDER BY `orderOfAppearance` ASC";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                deposit = new Deposit();
                deposit.setDId(set.getLong("DId"));
                deposit.setProductCode(set.getInt("productCode"));
                deposit.setBankId(set.getInt("bankId"));
                deposit.setBankName(set.getString("bankName"));
                deposit.setBankWebSite(set.getString("bankWebSite"));
                deposit.setProductName(set.getString("productName"));
                deposit.setDMinAmount(set.getInt("DMinAmount"));
                deposit.setDMaxAmount(set.getInt("DMaxAmount"));
                deposit.setDPercentage(set.getInt("DPercentage"));
                deposit.setCurrancy(set.getString("currancy"));
                deposit.setMinAge(set.getInt("minAge"));
                deposit.setMaxAge(set.getInt("maxAge"));
                deposit.setDEquippingPossibilitiesid(set.getInt("DEquippingPossibilitiesid"));
                deposit.setDEquippingPossibilities(set.getString("DEquippingPossibilities"));
                deposit.setDEarlierWithdrawalAmountid(set.getInt("DEarlierWithdrawalAmountid"));
                deposit.setDEarlierWithdrawalAmount(set.getString("DEarlierWithdrawalAmount"));
                deposit.setDAutoExtendPeriodid(set.getInt("DAutoExtendPeriodid"));
                deposit.setDAutoExtendPeriod(set.getString("DAutoExtendPeriod"));
                deposit.setDCapitalizationPercentid(set.getInt("DCapitalizationPercentid"));
                deposit.setDCapitalizationPercent(set.getString("DCapitalizationPercent"));
                deposit.setOrderOfAppearance(set.getInt("orderOfAppearance"));
                deposit.setTimeLine(set.getInt("timeline"));
                depositList.add(deposit);
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
        return depositList;
    }


    private void setStatmentForNonMarketing(Deposit deposit, PreparedStatement statement) throws SQLException {
        statement.setLong(1, deposit.getProductCode());
        statement.setInt(2, deposit.getBankId());
        statement.setString(3, deposit.getBankName());
        statement.setString(4, deposit.getBankWebSite());
        statement.setString(5, deposit.getProductName());
        statement.setInt(6, deposit.getDMinAmount());
        statement.setInt(7, deposit.getDMaxAmount());
        statement.setDouble(8, deposit.getDPercentage());
        statement.setString(9, deposit.getCurrancy());
        statement.setInt(10, deposit.getTimeLine());
        statement.setInt(11, deposit.getMinAge());
        statement.setInt(12, deposit.getMaxAge());
        statement.setInt(13, deposit.getDEquippingPossibilitiesid());
        statement.setString(14, deposit.getDEquippingPossibilities());
        statement.setInt(15, deposit.getDEarlierWithdrawalAmountid());
        statement.setString(16, deposit.getDEarlierWithdrawalAmount());
        statement.setInt(17, deposit.getDAutoExtendPeriodid());
        statement.setString(18, deposit.getDAutoExtendPeriod());
        statement.setInt(19, deposit.getDCapitalizationPercentid());
        statement.setString(20, deposit.getDCapitalizationPercent());
        statement.setString(21, deposit.getBankLink());

    }

    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }


    private void setStatment(Deposit deposit, PreparedStatement statement) throws SQLException {
        statement.setLong(1, deposit.getProductCode());
        statement.setInt(2, deposit.getBankId());
        statement.setString(3, deposit.getBankName());
        statement.setString(4, deposit.getBankWebSite());
        statement.setString(5, deposit.getProductName());
        statement.setInt(6, deposit.getDMinAmount());
        statement.setInt(7, deposit.getDMaxAmount());
        statement.setDouble(8, deposit.getDPercentage());
        statement.setString(9, deposit.getCurrancy());
        statement.setInt(10, deposit.getTimeLine());
        statement.setInt(11, deposit.getMinAge());
        statement.setInt(12, deposit.getMaxAge());
        statement.setInt(13, deposit.getDEquippingPossibilitiesid());
        statement.setString(14, deposit.getDEquippingPossibilities());
        statement.setInt(15, deposit.getDEarlierWithdrawalAmountid());
        statement.setString(16, deposit.getDEarlierWithdrawalAmount());
        statement.setInt(17, deposit.getDAutoExtendPeriodid());
        statement.setString(18, deposit.getDAutoExtendPeriod());
        statement.setInt(19, deposit.getDCapitalizationPercentid());
        statement.setString(20, deposit.getDCapitalizationPercent());
        statement.setInt(21, deposit.getOrderOfAppearance());
        statement.setInt(22, deposit.getSpecialOffer());
        statement.setInt(23, deposit.getFirstSearchList());
        statement.setInt(24, deposit.getSendRequest());
        statement.setString(25, deposit.getBankLink());
    }


    /**
     * List of deposite
     *
     * @param depositList
     * @param set
     * @throws SQLException
     */
    private void ListOfDeposite(List<Deposit> depositList, ResultSet set) throws SQLException {
        Deposit deposit;
        while (set.next()) {
            deposit = new Deposit();
            deposit.setDId(set.getLong("DId"));
            deposit.setProductCode(set.getInt("productCode"));
            deposit.setBankId(set.getInt("bankId"));
            deposit.setBankName(set.getString("bankName"));
            deposit.setBankWebSite(set.getString("bankWebSite"));
            deposit.setProductName(set.getString("productName"));
            deposit.setDMinAmount(set.getInt("DMinAmount"));
            deposit.setDMaxAmount(set.getInt("DMaxAmount"));
            deposit.setDPercentage(set.getDouble("DPercentage"));
            deposit.setCurrancy(set.getString("currancy"));
            deposit.setMinAge(set.getInt("minAge"));
            deposit.setMaxAge(set.getInt("maxAge"));
            deposit.setDEquippingPossibilitiesid(set.getInt("DEquippingPossibilitiesid"));
            deposit.setDEquippingPossibilities(set.getString("DEquippingPossibilities"));
            deposit.setDEarlierWithdrawalAmountid(set.getInt("DEarlierWithdrawalAmountid"));
            deposit.setDEarlierWithdrawalAmount(set.getString("DEarlierWithdrawalAmount"));
            deposit.setDAutoExtendPeriodid(set.getInt("DAutoExtendPeriodid"));
            deposit.setDAutoExtendPeriod(set.getString("DAutoExtendPeriod"));
            deposit.setDCapitalizationPercentid(set.getInt("DCapitalizationPercentid"));
            deposit.setDCapitalizationPercent(set.getString("DCapitalizationPercent"));
            deposit.setOrderOfAppearance(set.getInt("orderOfAppearance"));
            deposit.setGotoPage(set.getInt("gotoPage"));
            deposit.setSendRequest(set.getInt("sendRequest"));
            deposit.setFirstSearchList(set.getInt("firstSearchList"));
            deposit.setSpecialOffer(set.getInt("specialOffer"));
            deposit.setLastLogic(set.getInt("lastLogic"));
            deposit.setTimeLine(set.getInt("timeline"));
            deposit.setAmdMonth1(set.getDouble("amd1"));
            deposit.setAmdMonth3(set.getDouble("amd3"));
            deposit.setAmdMonth6(set.getDouble("amd6"));
            deposit.setAmdMonth9(set.getDouble("amd9"));
            deposit.setAmdMonth12(set.getDouble("amd12"));
            deposit.setAmdMonth18(set.getDouble("amd18"));
            deposit.setAmdMonth24(set.getDouble("amd24"));
            deposit.setAmdMonth36(set.getDouble("amd36"));

            deposit.setUsdMonth1(set.getDouble("usd1"));
            deposit.setUsdMonth3(set.getDouble("usd3"));
            deposit.setUsdMonth6(set.getDouble("usd6"));
            deposit.setUsdMonth9(set.getDouble("usd9"));

            deposit.setUsdMonth12(set.getDouble("usd12"));
            deposit.setUsdMonth18(set.getDouble("usd18"));
            deposit.setUsdMonth24(set.getDouble("usd24"));
            deposit.setUsdMonth36(set.getDouble("usd36"));

            deposit.setEurMonth1(set.getDouble("eur1"));
            deposit.setEurMonth3(set.getDouble("eur3"));
            deposit.setEurMonth6(set.getDouble("eur6"));
            deposit.setEurMonth9(set.getDouble("eur9"));
            deposit.setEurMonth12(set.getDouble("eur12"));
            deposit.setEurMonth18(set.getDouble("eur18"));
            deposit.setEurMonth24(set.getDouble("eur24"));
            deposit.setEurMonth36(set.getDouble("eur36"));

            deposit.setRubMonth1(set.getDouble("rub1"));
            deposit.setRubMonth3(set.getDouble("rub3"));
            deposit.setRubMonth6(set.getDouble("rub6"));
            deposit.setRubMonth9(set.getDouble("rub9"));
            deposit.setRubMonth12(set.getDouble("rub12"));
            deposit.setRubMonth18(set.getDouble("rub18"));
            deposit.setRubMonth24(set.getDouble("rub24"));
            deposit.setRubMonth36(set.getDouble("rub36"));



            deposit.setBankLink(set.getString("BankLink"));

            depositList.add(deposit);
        }
    }


    public int updateDepositeInData(Deposit prepaireDepositeInfoForDataFS, int getProductCode) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `depositecontroller`  " +
                    "SET orderOfAppearance=?,specialOffer=?,firstSearchList=?," +
                    "sendRequest=?,GotoPage=?,LastLogic=? WHERE productCode=" + getProductCode;
            statement = connection.prepareStatement(sql);
            statement.setInt(1, prepaireDepositeInfoForDataFS.getOrderOfAppearance());
            statement.setInt(2, prepaireDepositeInfoForDataFS.getSpecialOffer());
            statement.setInt(3, prepaireDepositeInfoForDataFS.getFirstSearchList());
            statement.setInt(4, prepaireDepositeInfoForDataFS.getSendRequest());
            statement.setInt(5, prepaireDepositeInfoForDataFS.getGotoPage());
            statement.setInt(6, prepaireDepositeInfoForDataFS.getLastLogic());

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
    public List<Deposit> getDepositbyMaxMinAmountById(int minAmount, String currancy,int BankId) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `currancy` like '%" + currancy + "%' AND `bankId`=" + BankId + " ORDER BY `DMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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

    public List<Deposit> getDepositMaxMinPercentByID(int minAmount, String currancy, int BankId) throws SQLException {
        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `depositecontroller` WHERE `currancy` like '%" + currancy + "%' AND `bankId`=" + BankId + " ORDER BY `DPercentage` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfDepositeWithList(depositList, set);
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

    public int AddAmdMonths(Deposit commentDaoToData, int productCode) throws SQLException{
        int rowsUpdated = 0;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `depositecontroller`  " +
                    "SET amd1=?,amd3=?,amd6=?,amd9=?,amd12=?,amd18=?,amd24=?," +
                    "amd36=?  " +
                    "WHERE productCode=" + productCode;
            statement = connection.prepareStatement(sql);
            statement.setDouble(1,commentDaoToData.getAmdMonth1());
            statement.setDouble(2,commentDaoToData.getAmdMonth3());
            statement.setDouble(3,commentDaoToData.getAmdMonth6());
            statement.setDouble(4,commentDaoToData.getAmdMonth9());
            statement.setDouble(5,commentDaoToData.getAmdMonth12());
            statement.setDouble(6,commentDaoToData.getAmdMonth18());
            statement.setDouble(7,commentDaoToData.getAmdMonth24());
            statement.setDouble(8,commentDaoToData.getAmdMonth36());

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

    public int AddUsdMonths(Deposit commentDaoToData, int productCode) throws SQLException{
        int rowsUpdated = 0;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `depositecontroller`  " +
                    "SET usd1=?,usd3=?,usd6=?,usd9=?,usd12=?,usd18=?,usd24=?," +
                    "usd36=?  " +
                    "WHERE productCode=" + productCode;
            statement = connection.prepareStatement(sql);
            statement.setDouble(1,commentDaoToData.getUsdMonth1());
            statement.setDouble(2,commentDaoToData.getUsdMonth3());
            statement.setDouble(3,commentDaoToData.getUsdMonth6());
            statement.setDouble(4,commentDaoToData.getUsdMonth9());
            statement.setDouble(5,commentDaoToData.getUsdMonth12());
            statement.setDouble(6,commentDaoToData.getUsdMonth18());
            statement.setDouble(7,commentDaoToData.getUsdMonth24());
            statement.setDouble(8,commentDaoToData.getUsdMonth36());

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

    public int AddRubMonths(Deposit commentDaoToData, int productCode) throws SQLException{
        int rowsUpdated = 0;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `depositecontroller`  " +
                    "SET rub1=?,rub3=?,rub6=?,rub9=?,rub12=?,rub18=?,rub24=?," +
                    "rub36=?  " +
                    "WHERE productCode=" + productCode;
            statement = connection.prepareStatement(sql);
            statement.setDouble(1,commentDaoToData.getRubMonth1());
            statement.setDouble(2,commentDaoToData.getRubMonth3());
            statement.setDouble(3,commentDaoToData.getRubMonth6());
            statement.setDouble(4,commentDaoToData.getRubMonth9());
            statement.setDouble(5,commentDaoToData.getRubMonth12());
            statement.setDouble(6,commentDaoToData.getRubMonth18());
            statement.setDouble(7,commentDaoToData.getRubMonth24());
            statement.setDouble(8,commentDaoToData.getRubMonth36());

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

    public int AddEurMonths(Deposit commentDaoToData, int productCode) throws SQLException{
        int rowsUpdated = 0;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `depositecontroller`  " +
                    "SET eur1=?,eur3=?,eur6=?,eur9=?,eur12=?,eur18=?,eur24=?,eur36=?  " +
                    "WHERE productCode=" + productCode;
            statement = connection.prepareStatement(sql);
            statement.setDouble(1,commentDaoToData.getEurMonth1());
            statement.setDouble(2,commentDaoToData.getEurMonth3());
            statement.setDouble(3,commentDaoToData.getEurMonth6());
            statement.setDouble(4,commentDaoToData.getEurMonth9());
            statement.setDouble(5,commentDaoToData.getEurMonth12());
            statement.setDouble(6,commentDaoToData.getEurMonth18());
            statement.setDouble(7,commentDaoToData.getEurMonth24());
            statement.setDouble(8,commentDaoToData.getEurMonth36());

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

    /*public List<Deposit> getDepositBybankIdOthers(int bankid) {

        Deposit deposit = null;
        List<Deposit> depositList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `depositecontroller` WHERE bankId !=" + bankId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfDeposite(depositList, set);

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
        return depositList;
    }*/
}
