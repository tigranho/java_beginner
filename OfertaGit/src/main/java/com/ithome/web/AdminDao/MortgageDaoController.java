package com.ithome.web.AdminDao;

import com.ithome.web.Bean.ConsumerCredit;
import com.ithome.web.Bean.Mortgage;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MortgageDaoController {
    /**
     * Show all Mortage
     */
    public List<Mortgage> getAllMortage() throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<Mortgage>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortageListSet(mortgageList, set);
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
        return mortgageList;
    }


    /**
     * Get cards by id
     *
     * @param mortageId
     * @return
     */
    public List<Mortgage> getMortagesById(int mortageId) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `mortagecontroller` WHERE MId=" + mortageId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            MortageListSet(mortgageList, set);

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
        return mortgageList;
    }

    /**
     * Get Mortgage list by Card code
     *
     * @param productCode
     * @return
     */
    public List<Mortgage> getMortgageByProductCode(int productCode) throws SQLException {

        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `mortagecontroller` WHERE productcode=" + productCode;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            MortageListSet(mortgageList, set);

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
        return mortgageList;
    }

    /**
     * Get mortgage by bank id
     *
     * @param bankId
     * @return
     */
    public List<Mortgage> getMortgageByBankId(int bankId) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `mortagecontroller` WHERE bankid=" + bankId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            MortageListSet(mortgageList, set);

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
        return mortgageList;
    }

    /**
     * Mortgage order by order on appeaarance
     *
     * @return
     */
    public List<Mortgage> MortgageOrderByOrderOnAppearance() {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` ORDER BY  orderonappearance DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                MortageListSet(mortgageList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return mortgageList;

    }

    /**
     * Mortgage order by card id
     *
     * @return
     */
    public List<Mortgage> MortgagesOrderByProductId() {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` ORDER BY  productCode DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                MortageListSet(mortgageList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return mortgageList;
    }

    /**
     * Mortgage order by card Name
     *
     * @return
     */
    public List<Mortgage> MortageOrderByProductName() {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` ORDER BY  productname DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                MortageListSet(mortgageList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return mortgageList;

    }

    /**
     * Deleting mortage by id
     *
     * @param MId
     * @return
     */
    public int DeleteMortgageById(int MId) {
        int rowsDeleted = 0;
        try {
            Connection connection = connectToData();

            String sql = "DELETE FROM `mortagecontroller` WHERE  MId=" + MId;
            PreparedStatement statment = connection.prepareStatement(sql);

            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A Message was deleted successfully!");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in CATEGORY DELETE  Section  : " + exception);
            exception.printStackTrace();
        }
        return rowsDeleted;
    }

    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }

    /**
     * Add new mortgage credit
     *
     * @param mortgage
     * @return
     * @throws SQLException
     */
    public int AddNewMortgage(Mortgage mortgage) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;

        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `mortagecontroller`" +
                    "(`MId`, `productcode`, `bankid`, `bankname`,`bankwebsite`,`productname`,`MMinAmount`,`MMaxAmount`,`MMinLoan`," +
                    "`MMaxLoan`,`MFatual`,`MMinDownPayment`, `MMaxDownPayment`,`currancy`," +
                    "`MMinPeriodMonth`,`MMaxPeriodMonth`,`MMinServiceFee`,`MMaxServiceFee`," +
                    "`minAge`,`maxAge`,`MRepaymentՕptionId`,`MRepaymentՕption`,`orderOfAppearance`,`specialOffer`,`firstSearchList`,`sendRequest`,`BankLink`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(mortgage, statment);
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

    public int updateMortgageInData(Mortgage prepaireMortgageForData, int clId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `mortagecontroller`  " +
                    "SET productcode=?,bankid=?,bankname=?,bankwebsite=?," +
                    "productname=?, " +
                    "MMinAmount=?,MMaxAmount=?,MMinLoan=?," +
                    "MMaxLoan=?,MFatual=?,MMinDownPayment=?,MMaxDownPayment=?,currancy=?," +
                    "MMinPeriodMonth=?,MMaxPeriodMonth=?,MMinServiceFee=?,MMaxServiceFee=?,minAge=?,maxAge=?,MRepaymentՕptionId=?," +
                    " MRepaymentՕption=?,BankLink=? " +
                    "WHERE MId=" + clId;
            statment = connection.prepareStatement(sql);
            setStatmentForNonMarketing(prepaireMortgageForData, statment);

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

    public List<Mortgage> getMortgagesByBankIdOrderByAppearance(int bankId) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `mortagecontroller` WHERE bankId=" + bankId + " ORDER BY `orderOfAppearance` ASC";
            statment = connection.prepareStatement(sql);
            ResultSet set = statment.executeQuery();
            while (set.next()) {
                mortgage = new Mortgage();
                mortgage.setMId(set.getLong("MId"));
                mortgage.setProductCode(set.getInt("productCode"));
                mortgage.setBankId(set.getInt("bankId"));
                mortgage.setBankName(set.getString("bankName"));
                mortgage.setBankWebSite(set.getString("bankWebSite"));
                mortgage.setProductName(set.getString("productName"));
                mortgage.setMMinAmount(set.getInt("MMinAmount"));
                mortgage.setMMaxAmount(set.getInt("MMaxAmount"));
                mortgage.setMMinLoan(set.getInt("MMinLoan"));
                mortgage.setMMaxLoan(set.getInt("MMaxLoan"));
                mortgage.setMFatual(set.getInt("MFatual"));
                mortgage.setMMinDownPayment(set.getInt("MMinDownPayment"));
                mortgage.setMMaxDownPayment(set.getInt("MMaxDownPayment"));
                mortgage.setCurrancy(set.getString("currancy"));
                mortgage.setMMinPeriodMonth(set.getInt("MMinPeriodMonth"));
                mortgage.setMMaxPeriodMonth(set.getInt("MMaxPeriodMonth"));
                mortgage.setMMinServiceFee(set.getInt("MMinServiceFee"));
                mortgage.setMMaxServiceFee(set.getInt("MMaxServiceFee"));
                mortgage.setMinAge(set.getInt("minAge"));
                mortgage.setMaxAge(set.getInt("maxAge"));
                mortgage.setMRepaymentId(set.getInt("MRepaymentՕptionId"));
                mortgage.setMRepayment(set.getString("MRepaymentՕption"));
                mortgage.setOrderOfAppearance(set.getInt("orderofappearance"));
                mortgage.setSpecialOffer(set.getInt("specialoffer"));
                mortgage.setFirstSearchList(set.getInt("firstsearchlist"));
                mortgage.setSendRequest(set.getInt("sendrequest"));
                mortgage.setBankLink(set.getString("BankLink"));
                mortgageList.add(mortgage);
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
        }
        return mortgageList;
    }


    private void setStatmentForNonMarketing(Mortgage mortgage, PreparedStatement statement) throws SQLException {
        StatmentNonMarketing(mortgage, statement);
    }

    private void StatmentNonMarketing(Mortgage mortgage, PreparedStatement statement) throws SQLException {
        statement.setInt(1, mortgage.getProductCode());
        statement.setInt(2, mortgage.getBankId());
        statement.setString(3, mortgage.getBankName());
        statement.setString(4, mortgage.getBankWebSite());
        statement.setString(5, mortgage.getProductName());
        statement.setDouble(6, mortgage.getMMinAmount());
        statement.setDouble(7, mortgage.getMMaxAmount());
        statement.setDouble(8, mortgage.getMMinLoan());
        statement.setDouble(9, mortgage.getMMaxLoan());
        statement.setDouble(10, mortgage.getMFatual());
        statement.setDouble(11, mortgage.getMMinDownPayment());
        statement.setDouble(12, mortgage.getMMaxDownPayment());
        statement.setString(13, mortgage.getCurrancy());
        statement.setInt(14, mortgage.getMMinPeriodMonth());
        statement.setInt(15, mortgage.getMMaxPeriodMonth());
        statement.setDouble(16, mortgage.getMMinServiceFee());
        statement.setDouble(17, mortgage.getMMaxServiceFee());
        statement.setInt(18, mortgage.getMinAge());
        statement.setInt(19, mortgage.getMaxAge());
        statement.setInt(20, mortgage.getMRepaymentId());
        statement.setString(21, mortgage.getMRepayment());
        statement.setString(22, mortgage.getBankLink());
    }

    /**
     * Statment for mortgage
     *
     * @param mortgage
     * @param statement
     */
    private void setStatment(Mortgage mortgage, PreparedStatement statement) throws SQLException {
        StatmentNonMarketing(mortgage, statement);
        statement.setInt(22, mortgage.getOrderOfAppearance());
        statement.setInt(23, mortgage.getSpecialOffer());
        statement.setInt(24, mortgage.getFirstSearchList());
        statement.setInt(25, mortgage.getSendRequest());
        statement.setString(26, mortgage.getBankLink());
    }


    /**
     * Mortage list set
     *
     * @param mortgageList
     * @param set
     */
    private void MortageListSet(List<Mortgage> mortgageList, ResultSet set) throws SQLException {
        Mortgage mortgage;
        while (set.next()) {
            mortgage = new Mortgage();
            mortgage.setMId(set.getLong("MId"));
            mortgage.setProductCode(set.getInt("productCode"));
            mortgage.setBankId(set.getInt("bankId"));
            mortgage.setBankName(set.getString("bankName"));
            mortgage.setBankWebSite(set.getString("bankWebSite"));
            mortgage.setProductName(set.getString("productName"));
            mortgage.setMMinAmount(set.getInt("MMinAmount"));
            mortgage.setMMaxAmount(set.getInt("MMaxAmount"));
            mortgage.setMMinLoan(set.getDouble("MMinLoan"));
            mortgage.setMMaxLoan(set.getDouble("MMaxLoan"));
            mortgage.setMFatual(set.getDouble("MFatual"));
            mortgage.setMMinDownPayment(set.getInt("MMinDownPayment"));
            mortgage.setMMaxDownPayment(set.getInt("MMaxDownPayment"));
            mortgage.setCurrancy(set.getString("currancy"));
            mortgage.setMMinPeriodMonth(set.getInt("MMinPeriodMonth"));
            mortgage.setMMaxPeriodMonth(set.getInt("MMaxPeriodMonth"));
            mortgage.setMMinServiceFee(set.getInt("MMinServiceFee"));
            mortgage.setMMaxServiceFee(set.getInt("MMaxServiceFee"));
            mortgage.setMinAge(set.getInt("minAge"));
            mortgage.setMaxAge(set.getInt("maxAge"));
            mortgage.setMRepaymentId(set.getInt("MRepaymentՕptionId"));
            mortgage.setMRepayment(set.getString("MRepaymentՕption"));
            mortgage.setOrderOfAppearance(set.getInt("orderofappearance"));
            mortgage.setSpecialOffer(set.getInt("specialoffer"));
            mortgage.setFirstSearchList(set.getInt("firstsearchlist"));
            mortgage.setSendRequest(set.getInt("sendrequest"));
            mortgage.setGotoPage(set.getInt("gotoPage"));
            mortgage.setLastlogic(set.getInt("lastlogic"));
            mortgage.setBankLink(set.getString("BankLink"));

            mortgage.setBanksList(mortgageList);
            mortgageList.add(mortgage);
        }
    }


    public int UpdateMortgageMarketingInData(Mortgage prepaireMicroLoanInfoForNewData, int getProductCode) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `mortagecontroller`  " +
                    "SET orderOfAppearance=?,specialOffer=?,firstSearchList=?," +
                    "sendRequest=?,GotoPage=?,LastLogic=? WHERE productCode=" + getProductCode;
            statement = connection.prepareStatement(sql);
            statement.setInt(1, prepaireMicroLoanInfoForNewData.getOrderOfAppearance());
            statement.setInt(2, prepaireMicroLoanInfoForNewData.getSpecialOffer());
            statement.setInt(3, prepaireMicroLoanInfoForNewData.getFirstSearchList());
            statement.setInt(4, prepaireMicroLoanInfoForNewData.getSendRequest());
            statement.setInt(5, prepaireMicroLoanInfoForNewData.getGotoPage());
            statement.setInt(6, prepaireMicroLoanInfoForNewData.getLastlogic());

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

    public List<Mortgage> getMortgageWithSpecialOffer() throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `specialOffer`=" + 1;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            MortageListSet(mortgageList, set);
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
        return mortgageList;
    }

    public List<Mortgage> getMortgageWithAppearance() throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `firstSearchList`=" + 1;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            MortageListSet(mortgageList, set);
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
        return mortgageList;
    }

    public List<Mortgage> getDepositbyMaxMinAmount(int minAmount,  String currancy) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `MMinAmount`<= " + minAmount + "  ORDER BY `MMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortageListSet(mortgageList, set);
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
        return mortgageList;
    }

    public List<Mortgage> getDepositMaxMinAmount(int minAmount, String currancy) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `currancy` like '%" + currancy + "%' AND `MMinAmount`>= " + minAmount + "  ORDER BY `MMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortageListSet(mortgageList, set);
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
        return mortgageList;
    }

    public List<Mortgage> getDepositbyMaxMinAmountAsec(int minAmount,  String currancy) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `MMinAmount`<= " + minAmount + " ORDER BY `MMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortageListSet(mortgageList, set);
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
        return mortgageList;
    }

    public List<Mortgage> getDepositMaxMinAmountSub(int minAmount, String currancy) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `currancy` like '%" + currancy + "%' AND `MMinAmount`>= " + minAmount + "  ORDER BY `MMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortageListSet(mortgageList, set);
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
        return mortgageList;
    }

    public List<Mortgage> getDepositMaxMinPercentSub(int minAmount, String currancy) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' ORDER BY `MFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortageListSet(mortgageList, set);
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
        return mortgageList;
    }

    public List<Mortgage> getDepositMaxMinPercentSubDes(int minAmount, String currancy) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `currancy` like '%" + currancy + "%' AND `MMinAmount`>= " + minAmount + "  ORDER BY `MFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortageListSet(mortgageList, set);
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
        return mortgageList;
    }

    public List<Mortgage> getDepositMaxMinPercentSubAcs(int minAmount,  String currancy) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `MMinAmount`<= " + minAmount + " ORDER BY `MFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortageListSet(mortgageList, set);
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
        return mortgageList;
    }

    public List<Mortgage> getDepositMaxMinPercentSubAsc(int minAmount, String currancy) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `currancy` like '%" + currancy + "%' AND `MMinAmount`>= " + minAmount + "  ORDER BY `MFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortageListSet(mortgageList, set);
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
        return mortgageList;
    }

    public List<Mortgage> getDepositbyMaxMinAmountById(int minAmount, String currancy, int BankId) throws SQLException {
        Mortgage deposit = null;
        List<Mortgage> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `currancy` like '%" + currancy + "%' AND `bankId`=" + BankId + " ORDER BY `MMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortageListSet(depositList, set);
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

    public List<Mortgage> getDepositMaxMinPercentByID(int minAmount, String currancy, int BankId) throws SQLException {
        Mortgage deposit = null;
        List<Mortgage> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `currancy` like '%" + currancy + "%' AND `bankId`=" + BankId + " ORDER BY `MFatual` ASC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortageListSet(depositList, set);
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

    public List<Mortgage> BankingAsc(int BankId) throws SQLException {
        Mortgage deposit = null;
        List<Mortgage> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `bankId`=" + BankId + " ORDER BY `MFatual` ASC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortageListSet(depositList, set);
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

    public List<Mortgage> BankingDesc(int BankId) throws SQLException {
        Mortgage deposit = null;
        List<Mortgage> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mortagecontroller` WHERE `bankId`=" + BankId + " ORDER BY `MFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortageListSet(depositList, set);
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

    public String getMortgageProductNameByProductCode(int productCode) throws SQLException {
        String productName = null;
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT `productName` FROM `mortagecontroller` WHERE `productCode`= " + productCode;
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

    public List<Mortgage> getFilterdOrderOne(int amount, String pageCurrency) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageArrayList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `mortagecontroller` " +
                " left join `bankscontroller`" +
                " on `bankscontroller`.`bankId` = `mortagecontroller`.`bankId`" +
                " left join `commentscontroller`" +
                " on `mortagecontroller`.`productCode` = `commentscontroller`.`productCode` WHERE `currancy` = '" + pageCurrency +
                "' AND `MMinLoan` <= " + amount +
                " AND `MMaxLoan` >= " + amount +
                " AND `firstSearchList` = 1";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortgageListJoin(mortgageArrayList, set);
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
        return mortgageArrayList;
    }

    private void MortgageListJoin(List<Mortgage> mortgageList, ResultSet set) throws SQLException {
        Mortgage mortgage;
        while (set.next()) {
            mortgage = new Mortgage();
            mortgage.setMId(set.getLong("MId"));
            mortgage.setProductCode(set.getInt("productCode"));
            mortgage.setBankId(set.getInt("bankId"));
            mortgage.setBankName(set.getString("bankName"));
            mortgage.setBankWebSite(set.getString("bankWebSite"));
            mortgage.setProductName(set.getString("productName"));
            mortgage.setMMinAmount(set.getInt("MMinAmount"));
            mortgage.setMMaxAmount(set.getInt("MMaxAmount"));
            mortgage.setMMinLoan(set.getDouble("MMinLoan"));
            mortgage.setMMaxLoan(set.getDouble("MMaxLoan"));
            mortgage.setMFatual(set.getDouble("MFatual"));
            mortgage.setMMinDownPayment(set.getInt("MMinDownPayment"));
            mortgage.setMMaxDownPayment(set.getInt("MMaxDownPayment"));
            mortgage.setCurrancy(set.getString("currancy"));
            mortgage.setMMinPeriodMonth(set.getInt("MMinPeriodMonth"));
            mortgage.setMMaxPeriodMonth(set.getInt("MMaxPeriodMonth"));
            mortgage.setMMinServiceFee(set.getInt("MMinServiceFee"));
            mortgage.setMMaxServiceFee(set.getInt("MMaxServiceFee"));
            mortgage.setMinAge(set.getInt("minAge"));
            mortgage.setMaxAge(set.getInt("maxAge"));
            mortgage.setMRepaymentId(set.getInt("MRepaymentՕptionId"));
            mortgage.setMRepayment(set.getString("MRepaymentՕption"));
            mortgage.setOrderOfAppearance(set.getInt("orderofappearance"));
            mortgage.setSpecialOffer(set.getInt("specialoffer"));
            mortgage.setFirstSearchList(set.getInt("firstsearchlist"));
            mortgage.setSendRequest(set.getInt("sendrequest"));
            mortgage.setGotoPage(set.getInt("gotoPage"));
            mortgage.setLastlogic(set.getInt("lastlogic"));
            mortgage.setBankLink(set.getString("BankLink"));
            mortgage.setComment1_Am(set.getString("Comment1_Am"));
            mortgage.setComment2_Am(set.getString("Comment2_Am"));
            mortgage.setComment3_Am(set.getString("Comment3_Am"));
            mortgage.setComment1_Ru(set.getString("Comment1_Ru"));
            mortgage.setComment2_Ru(set.getString("Comment2_Ru"));
            mortgage.setComment3_Ru(set.getString("Comment3_Ru"));
            mortgage.setBankBigLogo(set.getString("bankBigLogo"));
            mortgage.setBankSmallLogo(set.getString("bankSmallLogo"));

            mortgage.setBanksList(mortgageList);
            mortgageList.add(mortgage);
        }
    }

    public List<Mortgage> getFilterdOrderMoreOne(int amount, String pageCurrency) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageArrayList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `mortagecontroller` " +
                " left join `bankscontroller`" +
                " on `bankscontroller`.`bankId` = `mortagecontroller`.`bankId`" +
                " left join `commentscontroller`" +
                " on `mortagecontroller`.`productCode` = `commentscontroller`.`productCode` WHERE `currancy` = '" + pageCurrency +
                "' AND `MMinLoan` <= " + amount +
                " AND `MMaxLoan` >= " + amount +
                " AND `firstSearchList` = 1";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortgageListJoin(mortgageArrayList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in mortgageArrayList Section  : " + exception);
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
        return mortgageArrayList;
    }

    public List<Mortgage> getFilterdOrderSubMoreOne(int amount, String pageCurrency) throws SQLException {
        Mortgage mortgage = null;
        List<Mortgage> mortgageArrayList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `mortagecontroller` " +
                " left join `bankscontroller`" +
                " on `bankscontroller`.`bankId` = `mortagecontroller`.`bankId`" +
                " left join `commentscontroller`" +
                " on `mortagecontroller`.`productCode` = `commentscontroller`.`productCode` WHERE `currancy` = '" + pageCurrency +
                "' AND `MMinLoan` <= " + amount +
                " AND `MMaxLoan` >= " + amount +
                " AND `firstSearchList` > 1";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MortgageListJoin(mortgageArrayList, set);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in mortgageArrayList Section  : " + exception);
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
        return mortgageArrayList;
    }
}
