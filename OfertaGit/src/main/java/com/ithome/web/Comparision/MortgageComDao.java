package com.ithome.web.Comparision;

import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MortgageComDao {

    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }

    public List<MoComparing> getAll() throws SQLException {
        MoComparing moComparing = null;
        List<MoComparing> moComparingList = new ArrayList<>();
        ResultSet set = null;
        Statement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `mocompare` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            Depo(moComparingList, set);
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
        return moComparingList;
    }

    public int Update(int Amount, int id, String SId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `mocompare` SET `Amount`=? WHERE `idCheck`=" + id + " AND `SID` like '%" + SId +"%'" ;
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

    public int UpdateOthers(int Months, int id, String SId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `mocompare` SET `months`=? WHERE `idCheck`=" + id + " AND `SID` like '%" + SId +"%'" ;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, Months);
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

    public int UpdatePrePayment(int Perpaiment, int id, String SId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `mocompare` SET `perpaiment`=? WHERE `idCheck`=" + id + " AND `SID` like '%" + SId +"%'" ;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, Perpaiment);
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

    public int Add(MoComparing moComparing) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `mocompare`" +
                    "(`id`,`idCheck`, `SID`,`Amount`,`months`,`bankId`,`productName`,`percentage`,`service`,`perpaiment`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(moComparing, statment);
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

    public List<MoComparing> getById(int id, String SId) throws SQLException {
        MoComparing moComparing = null;
        List<MoComparing> moComparingList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `mocompare` WHERE `idCheck`=" + id + " AND `SID` like '%" + SId + "%'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            Depo(moComparingList, set);
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
        return moComparingList;
    }

    public int Delete(int id, String SId) throws SQLException {
        int rowsDeleted = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();

            String sql = "DELETE FROM `mocompare` WHERE  idCheck=" + id + " AND `SID` like '%" + SId +"%'" ;
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

    public int DeleteBySession(String SId) throws SQLException {
        int rowsDeleted = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();

            String sql = "DELETE FROM `mocompare` WHERE  `SID` like '%" + SId +"%'" ;
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


    private void setStatment(MoComparing moComparing, PreparedStatement statment) throws SQLException {

        statment.setInt(1, moComparing.getIdCheck());
        statment.setString(2, moComparing.getSID());
        statment.setInt(3, moComparing.getAmount());
        statment.setInt(4, moComparing.getMonths());
        statment.setInt(5, moComparing.getBankId());
        statment.setString(6,moComparing.getProductName());
        statment.setDouble(7,moComparing.getPercentage());
        statment.setInt(8,moComparing.getService());
        statment.setInt(9,moComparing.getPerpaiment());


    }


    private void Depo(List<MoComparing> moComparingList, ResultSet set) throws SQLException {
        MoComparing moComparing;
        while (set.next()) {
            moComparing = new MoComparing();
            moComparing.setId(set.getInt("id"));
            moComparing.setIdCheck(set.getInt("idCheck"));
            moComparing.setSID(set.getString("SID"));
            moComparing.setAmount(set.getInt("Amount"));
            moComparing.setMonths(set.getInt("months"));
            moComparing.setBankId(set.getInt("bankId"));
            moComparing.setProductName(set.getString("productName"));
            moComparing.setPercentage(set.getDouble("percentage"));
            moComparing.setService(set.getInt("service"));
            moComparing.setPerpaiment(set.getInt("perpaiment"));
            moComparing.setBanksList(moComparingList);


            moComparingList.add(moComparing);
        }
    }


}
