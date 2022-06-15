package com.ithome.web.AdminDao;

import com.ithome.web.Bean.CarLoans;
import com.ithome.web.Bean.MicroLoans;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MicroLoanDaoController {

    /**
     * Show all cards
     */
    public List<MicroLoans> getAllMicroLoans() throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<MicroLoans>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    /**
     * Get cards by id
     *
     * @param microLoanId
     * @return
     */
    public List<MicroLoans> getMicroLoanssById(int microLoanId) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `microloancontroller` WHERE MLId=" + microLoanId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            MicroLoanListSet(microLoansList, set);

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
        return microLoansList;
    }

    /**
     * Get MicroLoans list by Card code
     *
     * @param productCode
     * @return
     */
    public List<MicroLoans> getMicroLoansByProductCode(int productCode) throws SQLException {

        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `microloancontroller` WHERE productcode=" + productCode;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            MicroLoanListSet(microLoansList, set);

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
        return microLoansList;
    }

    /**
     * Get MicroLoans by bank id
     *
     * @param bankId
     * @return
     */
    public List<MicroLoans> getMicroLoansByBankId(int bankId) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `microloancontroller` WHERE bankid=" + bankId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            MicroLoanListSet(microLoansList, set);

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
        return microLoansList;
    }

    /**
     * MicroLoans order by order on appeaarance
     *
     * @return
     */
    public List<MicroLoans> MicroLoansOrderByOrderOnAppearance() throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` ORDER BY  orderonappearance DESC";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            while (set.next()) {
                MicroLoanListSet(microLoansList, set);
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
        return microLoansList;

    }

    /**
     * MicroLoans order by card id
     *
     * @return
     */
    public List<MicroLoans> MicroLoansOrderByProductCode() {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` ORDER BY  productCode DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                MicroLoanListSet(microLoansList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return microLoansList;

    }

    /**
     * MicroLoans order by card Name
     *
     * @return
     */
    public List<MicroLoans> MicroLoansOrderByProductName() {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        try {
            Connection connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` ORDER BY  productname DESC";
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {
                MicroLoanListSet(microLoansList, set);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return microLoansList;

    }

    /**
     * get car loans by bank id and order of appearance
     *
     * @param bankId
     * @return
     */
    public List<MicroLoans> getMicroLoansByBankIdOrderByAppearance(int bankId) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `microloancontroller` WHERE bankId=" + bankId + " ORDER BY `orderOfAppearance` ASC";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                microLoans = new MicroLoans();
                microLoans.setMLId(set.getLong("MLId"));
                microLoans.setProductCode(set.getInt("productCode"));
                microLoans.setBankId(set.getInt("bankId"));
                microLoans.setBankName(set.getString("bankName"));
                microLoans.setBankWebSite(set.getString("bankWebSite"));
                microLoans.setProductName(set.getString("productName"));
                microLoans.setMLMinAmount(set.getInt("MLMinAmount"));
                microLoans.setMLMaxAmount(set.getInt("MLMaxAmount"));
                microLoans.setMLMinLoan(set.getInt("MLMinLoan"));
                microLoans.setMLMaxLoan(set.getInt("MLMaxLoan"));
                microLoans.setMLFatual(set.getInt("MLFatual"));
                microLoans.setCurrancy(set.getString("currancy"));
                microLoans.setMMinPeriodDays(set.getInt("MMinPeriodDays"));
                microLoans.setMMaxPeriodDays(set.getInt("MMaxPeriodDays"));
                microLoans.setMMinServiceFee(set.getInt("MMinServiceFee"));
                microLoans.setMMaxServiceFee(set.getInt("MMaxServiceFee"));
                microLoans.setMinAge(set.getInt("minAge"));
                microLoans.setMaxAge(set.getInt("maxAge"));
                microLoans.setOrderOfAppearance(set.getInt("orderofappearance"));
                microLoans.setSpecialOffer(set.getInt("specialoffer"));
                microLoans.setFirstSearchList(set.getInt("firstsearchlist"));
                microLoans.setSendRequest(set.getInt("sendrequest"));
                microLoans.setLastlogic(set.getInt("LastLogic"));

                microLoansList.add(microLoans);
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
        return microLoansList;
    }


    /**
     * get car loans by bank id and order of appearance
     *
     * @param bankId
     * @return
     */
    public List<MicroLoans> getMicroLoansByBankIdOrderBySpecialOffer(int bankId) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `microloancontroller` WHERE bankId=" + bankId + " ORDER BY `specialOffer` ASC";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                microLoans = new MicroLoans();
                microLoans.setMLId(set.getLong("MLId"));
                microLoans.setProductCode(set.getInt("productCode"));
                microLoans.setBankId(set.getInt("bankId"));
                microLoans.setBankName(set.getString("bankName"));
                microLoans.setBankWebSite(set.getString("bankWebSite"));
                microLoans.setProductName(set.getString("productName"));
                microLoans.setMLMinAmount(set.getInt("MLMinAmount"));
                microLoans.setMLMaxAmount(set.getInt("MLMaxAmount"));
                microLoans.setMLMinLoan(set.getInt("MLMinLoan"));
                microLoans.setMLMaxLoan(set.getInt("MLMaxLoan"));
                microLoans.setMLFatual(set.getInt("MLFatual"));
                microLoans.setCurrancy(set.getString("currancy"));
                microLoans.setMMinPeriodDays(set.getInt("MMinPeriodDays"));
                microLoans.setMMaxPeriodDays(set.getInt("MMaxPeriodDays"));
                microLoans.setMMinServiceFee(set.getInt("MMinServiceFee"));
                microLoans.setMMaxServiceFee(set.getInt("MMaxServiceFee"));
                microLoans.setMinAge(set.getInt("minAge"));
                microLoans.setMaxAge(set.getInt("maxAge"));
                microLoans.setOrderOfAppearance(set.getInt("orderofappearance"));
                microLoans.setSpecialOffer(set.getInt("specialoffer"));
                microLoans.setFirstSearchList(set.getInt("firstsearchlist"));
                microLoans.setSendRequest(set.getInt("sendrequest"));

                microLoansList.add(microLoans);
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
        return microLoansList;
    }


    /**
     * Add new MicroLoans
     *
     * @param microLoans
     * @return
     * @throws SQLException
     */
    public int AddNewMicroLoans(MicroLoans microLoans) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;

        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `microloancontroller`" +
                    "(`MLId`, `productcode`, `bankid`, `bankname`,`bankwebsite`,`productname`," +
                    "`MLMinAmount`,`MLMaxAmount`,`MLMinLoan`," +
                    "`MLMaxLoan`,`MLFatual`,`currancy`, `MMinPeriodDays`,`MMaxPeriodDays`," +
                    "`MMinServiceFee`,`MMaxServiceFee`,`minAge`,`maxAge`,`orderOfAppearance`,`specialOffer`,`firstSearchList`,`sendRequest`,`LastLogic`,`BankLink`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(microLoans, statment);
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


    public int UpdateMicroInData(MicroLoans prepaireMicroInfoForData, int mlId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `microloancontroller`  " +
                    "SET productcode=?,bankid=?," +
                    "bankname=?,bankwebsite=?,productname=?,MLMinAmount=?,MLMaxAmount=?," +
                    "MLMinLoan=?,MLMaxLoan=?,MLFatual=?,currancy=?,MMinPeriodDays=?," +
                    "MMaxPeriodDays=?,MMinServiceFee=?,MMaxServiceFee=?,minAge=?,maxAge=?,BankLink=?  " +
                    "WHERE MLId=" + mlId;
            statment = connection.prepareStatement(sql);
            setStatmentForNonMarketing(prepaireMicroInfoForData, statment);

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

    private void setStatmentForNonMarketing(MicroLoans microLoans, PreparedStatement statement) throws SQLException {
        statement.setInt(1, microLoans.getProductCode());
        statement.setInt(2, microLoans.getBankId());
        statement.setString(3, microLoans.getBankName());
        statement.setString(4, microLoans.getBankWebSite());
        statement.setString(5, microLoans.getProductName());
        statement.setDouble(6, microLoans.getMLMinAmount());
        statement.setDouble(7, microLoans.getMLMaxAmount());
        statement.setDouble(8, microLoans.getMLMinLoan());
        statement.setDouble(9, microLoans.getMLMaxLoan());
        statement.setDouble(10, microLoans.getMLFatual());
        statement.setString(11, microLoans.getCurrancy());
        statement.setInt(12, microLoans.getMMinPeriodDays());
        statement.setInt(13, microLoans.getMMaxPeriodDays());
        statement.setDouble(14, microLoans.getMMinServiceFee());
        statement.setDouble(15, microLoans.getMMaxServiceFee());
        statement.setInt(16, microLoans.getMinAge());
        statement.setInt(17, microLoans.getMaxAge());
        statement.setString(18, microLoans.getBankLink());

    }

    /**
     * Deleting micro
     *
     * @param mlId
     * @return
     */
    public int DeleteMicro(int mlId) throws SQLException {
        int rowsDeleted = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();

            String sql = "DELETE FROM `microloancontroller` WHERE  MLId=" + mlId;
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
     * Setting statment for micro loans
     *
     * @param microLoans
     * @param statement
     * @throws SQLException
     */
    private void setStatment(MicroLoans microLoans, PreparedStatement statement) throws SQLException {
        statement.setInt(1, microLoans.getProductCode());
        statement.setInt(2, microLoans.getBankId());
        statement.setString(3, microLoans.getBankName());
        statement.setString(4, microLoans.getBankWebSite());
        statement.setString(5, microLoans.getProductName());
        statement.setDouble(6, microLoans.getMLMinAmount());
        statement.setDouble(7, microLoans.getMLMaxAmount());
        statement.setDouble(8, microLoans.getMLMinLoan());
        statement.setDouble(9, microLoans.getMLMaxLoan());
        statement.setDouble(10, microLoans.getMLFatual());
        statement.setString(11, microLoans.getCurrancy());
        statement.setInt(12, microLoans.getMMinPeriodDays());
        statement.setInt(13, microLoans.getMMaxPeriodDays());
        statement.setDouble(14, microLoans.getMMinServiceFee());
        statement.setDouble(15, microLoans.getMMaxServiceFee());
        statement.setInt(16, microLoans.getMinAge());
        statement.setInt(17, microLoans.getMaxAge());
        statement.setInt(18, microLoans.getOrderOfAppearance());
        statement.setInt(19, microLoans.getSpecialOffer());
        statement.setInt(20, microLoans.getFirstSearchList());
        statement.setInt(21, microLoans.getSendRequest());
        statement.setInt(22, microLoans.getLastlogic());
        statement.setString(23, microLoans.getBankLink());
    }


    /**
     * Micro loan set
     *
     * @param microLoansList
     * @param set
     * @throws SQLException
     */
    private void MicroLoanListSet(List<MicroLoans> microLoansList, ResultSet set) throws SQLException {
        MicroLoans microLoans;
        while (set.next()) {
            microLoans = new MicroLoans();
            microLoans.setMLId(set.getLong("MLId"));
            microLoans.setProductCode(set.getInt("productCode"));
            microLoans.setBankId(set.getInt("bankId"));
            microLoans.setBankName(set.getString("bankName"));
            microLoans.setBankWebSite(set.getString("bankWebSite"));
            microLoans.setProductName(set.getString("productName"));
            microLoans.setMLMinAmount(set.getInt("MLMinAmount"));
            microLoans.setMLMaxAmount(set.getInt("MLMaxAmount"));
            microLoans.setMLMinLoan(set.getDouble("MLMinLoan"));
            microLoans.setMLMaxLoan(set.getDouble("MLMaxLoan"));
            microLoans.setMLFatual(set.getDouble("MLFatual"));
            microLoans.setCurrancy(set.getString("currancy"));
            microLoans.setMMinPeriodDays(set.getInt("MMinPeriodDays"));
            microLoans.setMMaxPeriodDays(set.getInt("MMaxPeriodDays"));
            microLoans.setMMinServiceFee(set.getInt("MMinServiceFee"));
            microLoans.setMMaxServiceFee(set.getInt("MMaxServiceFee"));
            microLoans.setMinAge(set.getInt("minAge"));
            microLoans.setMaxAge(set.getInt("maxAge"));
            microLoans.setOrderOfAppearance(set.getInt("orderofappearance"));
            microLoans.setSpecialOffer(set.getInt("specialoffer"));
            microLoans.setFirstSearchList(set.getInt("firstsearchlist"));
            microLoans.setSendRequest(set.getInt("sendrequest"));
            microLoans.setGotoPage(set.getInt("gotoPage"));
            microLoans.setLastlogic(set.getInt("lastlogic"));
            microLoans.setBankLink(set.getString("BankLink"));
            microLoans.setBanksList(microLoansList);
            microLoansList.add(microLoans);
        }
    }

    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }


    public int UpdateMicroLoanMarketingInData(MicroLoans prepaireCarLoanInfoForData, int getProductCode) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `microloancontroller`  " +
                    "SET orderOfAppearance=?,specialOffer=?,firstSearchList=?," +
                    "sendRequest=?,GotoPage=?,LastLogic=? WHERE productCode=" + getProductCode;
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

    public List<MicroLoans> getMicroWithSpecialOffer() throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `specialOffer`=" + 1;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositWithAppearance() throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `firstSearchList`=" + 1;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositMaxMinAmount(int minAmount, String currancy) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `MLMinAmount`>= " + minAmount + "  ORDER BY `MLMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositbyMaxMinAmount(int minAmount, int maxAmount, String currancy) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `MLMinAmount`<= " + minAmount + " AND `MLMaxAmount`>=" + maxAmount + " ORDER BY `MLMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositbyMaxMinAmountAsec(int minAmount, int maxAmount, String currancy) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `MLMinAmount`<= " + minAmount + " AND `MLMaxAmount`>=" + maxAmount + " ORDER BY `MLMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositMaxMinAmountSub(int minAmount, String currancy) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `MLMinAmount`>= " + minAmount + "  ORDER BY `MLMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositMaxMinPercentSub(int minAmount, int maxAmount, String currancy) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `MLMinAmount`<= " + minAmount + " AND `MLMaxAmount`>=" + maxAmount + " ORDER BY `MLFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositMaxMinPercentSubDes(int minAmount, String currancy) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `MLMinAmount`>= " + minAmount + "  ORDER BY `MLFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositMaxMinPercentSubAcs(int minAmount, int maxAmount, String currancy) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `MLMinAmount`<= " + minAmount + " AND `MLMaxAmount`>=" + maxAmount + " ORDER BY `MLFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositMaxMinPercentSubAsc(int minAmount, String currancy) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `MLMinAmount`>= " + minAmount + "  ORDER BY `MLFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositbyMaxMinAmountByTime(int minAmount, int timeline, String currancy) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `MLMinAmount`<= " + minAmount + " AND `MMinPeriodDays`=" + timeline + "  ORDER BY `MMinPeriodDays` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositMaxMinAmountByTime(int minAmount, int timeline, String currancy) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `MLMinAmount`>= " + minAmount + " AND `MMinPeriodDays`= " + timeline + " ORDER BY `MMinPeriodDays` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositbyMaxMinAmountAsecByTime(int minAmount, int timeline, String currancy) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `orderOfAppearance`= " + 1 + " AND `currancy` like '%" + currancy + "%' AND `MLMinAmount`<= " + minAmount + " AND `MMinPeriodDays`=" + timeline + " ORDER BY `MMinPeriodDays` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositMaxMinAmountSubByTime(int minAmount, int timeline, String currancy) throws SQLException {
        MicroLoans microLoans = null;
        List<MicroLoans> microLoansList = new ArrayList<>();
        Statement statment = null;
        Connection connection = null;
        ResultSet set = null;

        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `MLMinAmount`>= " + minAmount + " AND `MMinPeriodDays`= "+timeline+"  ORDER BY `MMinPeriodDays` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(microLoansList, set);
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
        return microLoansList;
    }

    public List<MicroLoans> getDepositbyMaxMinAmountById(int minAmount, String currancy, int BankId) throws SQLException {
        MicroLoans deposit = null;
        List<MicroLoans> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `bankId`=" + BankId + " ORDER BY `MLMinAmount` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(depositList, set);
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

    public List<MicroLoans> getDepositMaxMinPercentByID(int minAmount, String currancy, int BankId) throws SQLException {
        MicroLoans deposit = null;
        List<MicroLoans> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `currancy` like '%" + currancy + "%' AND `bankId`=" + BankId + " ORDER BY `MLFatual` ASC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(depositList, set);
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

    public List<MicroLoans> BankingDesc(int BankId) throws SQLException {
        MicroLoans deposit = null;
        List<MicroLoans> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `bankId`=" + BankId + " ORDER BY `MLFatual` DESC ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(depositList, set);
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

    public List<MicroLoans> BankingAsc(int BankId) throws SQLException {
        MicroLoans deposit = null;
        List<MicroLoans> depositList = new ArrayList<>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microloancontroller` WHERE `bankId`=" + BankId + " ORDER BY `MLFatual` Asc ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            MicroLoanListSet(depositList, set);
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
}