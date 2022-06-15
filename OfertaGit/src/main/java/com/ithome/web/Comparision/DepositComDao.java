package com.ithome.web.Comparision;

import com.ithome.web.Bean.Deposit;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepositComDao {

    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }

    public List<DepositComparing> getAll() throws SQLException {
        DepositComparing depositComparing = null;
        List<DepositComparing> depositComparingList = new ArrayList<>();
        ResultSet set = null;
        Statement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `deposicompare` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            Depo(depositComparingList, set);
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
        return depositComparingList;
    }

    public int Update(int Amount, int id, String SId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `deposicompare` SET `Amount`=? WHERE `idCheck`=" + id + " AND `SID` like '%" + SId +"%'" ;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, Amount);
            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in admin UPDATE Section  : " + exception);
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


    public int Add(DepositComparing depositComparing) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `deposicompare`" +
                    "(`id`,`idCheck`, `SID`,`Amount`,`months`,`bankId`,`productName`,`DEid`,`DEWlid`,`DAid`,`DCid`,`percentage`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(depositComparing, statment);
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

    public int Delete(int id, String SId) throws SQLException {
        int rowsDeleted = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();

            String sql = "DELETE FROM `deposicompare` WHERE  idCheck=" + id + " AND `SID` like '%" + SId +"%'" ;
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

    public int DeletebySession(String SId) throws SQLException {
        int rowsDeleted = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();

            String sql = "DELETE FROM `deposicompare` WHERE  `SID` like '%" + SId +"%'" ;
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
    public List<DepositComparing> getById(int id, String SId) throws SQLException {
        DepositComparing depositComparing = null;
        List<DepositComparing> depositComparingList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `deposicompare` WHERE `idCheck`=" + id + " AND `SID` like '%" + SId + "%'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            Depo(depositComparingList, set);
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
        return depositComparingList;
    }


    private void setStatment(DepositComparing depositComparing, PreparedStatement statment) throws SQLException {

        statment.setInt(1, depositComparing.getIdCheck());
        statment.setString(2, depositComparing.getSID());
        statment.setInt(3, depositComparing.getAmount());
        statment.setInt(4, depositComparing.getMonths());
        statment.setInt(5, depositComparing.getBankId());
        statment.setString(6,depositComparing.getProductName());
        statment.setInt(7,depositComparing.getDEquippingPossibilitiesid());
        statment.setInt(8,depositComparing.getDEarlierWithdrawalAmountid());
        statment.setInt(9,depositComparing.getDAutoExtendPeriodid());
        statment.setInt(10,depositComparing.getDCapitalizationPercentid());
        statment.setDouble(11,depositComparing.getPercentage());


    }

    private void Depo(List<DepositComparing> depositComparingList, ResultSet set) throws SQLException {
        DepositComparing depositComparing;
        while (set.next()) {
            depositComparing = new DepositComparing();
            depositComparing.setId(set.getInt("id"));
            depositComparing.setIdCheck(set.getInt("idCheck"));
            depositComparing.setSID(set.getString("SID"));
            depositComparing.setAmount(set.getInt("Amount"));
            depositComparing.setMonths(set.getInt("months"));
            depositComparing.setBankId(set.getInt("bankId"));
            depositComparing.setProductName(set.getString("productName"));
            depositComparing.setDEquippingPossibilitiesid(set.getInt("DEid"));
            depositComparing.setDEarlierWithdrawalAmountid(set.getInt("DEWlid"));
            depositComparing.setDAutoExtendPeriodid(set.getInt("DAid"));
            depositComparing.setDCapitalizationPercentid(set.getInt("DCid"));
            depositComparing.setPercentage(set.getDouble("percentage"));
            depositComparing.setBanksList(depositComparingList);


            depositComparingList.add(depositComparing);
        }
    }


}
