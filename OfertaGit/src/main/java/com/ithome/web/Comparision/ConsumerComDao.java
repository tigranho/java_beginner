package com.ithome.web.Comparision;

import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumerComDao {

    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }

    public List<ConsumerComparing> getAll() throws SQLException {
        ConsumerComparing consumerComparing = null;
        List<ConsumerComparing> consumerComparingList = new ArrayList<>();
        ResultSet set = null;
        Statement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `consumercompare` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            Depo(consumerComparingList, set);
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
        return consumerComparingList;
    }

    public int Update(int Amount, int id, String SId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `consumercompare` SET `Amount`=? WHERE `idCheck`=" + id + " AND `SID` like '%" + SId +"%'" ;
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

            String sql = "UPDATE `consumercompare` SET `months`=? WHERE `idCheck`=" + id + " AND `SID` like '%" + SId +"%'" ;
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

    public int Add(ConsumerComparing consumerComparing) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `consumercompare`" +
                    "(`id`,`idCheck`, `SID`,`Amount`,`months`,`bankId`,`productName`,`percentage`,`service`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(consumerComparing, statment);
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

            String sql = "DELETE FROM `consumercompare` WHERE  idCheck=" + id + " AND `SID` like '%" + SId +"%'" ;
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

            String sql = "DELETE FROM `consumercompare` WHERE `SID` like '%" + SId +"%'" ;
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

    public List<ConsumerComparing> getById(int id, String SId) throws SQLException {
        ConsumerComparing consumerComparing = null;
        List<ConsumerComparing> consumerComparingList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `consumercompare` WHERE `idCheck`=" + id + " AND `SID` like '%" + SId + "%'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            Depo(consumerComparingList, set);
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
        return consumerComparingList;
    }


    private void setStatment(ConsumerComparing consumerComparing, PreparedStatement statment) throws SQLException {

        statment.setInt(1, consumerComparing.getIdCheck());
        statment.setString(2, consumerComparing.getSID());
        statment.setInt(3, consumerComparing.getAmount());
        statment.setInt(4, consumerComparing.getMonths());
        statment.setInt(5, consumerComparing.getBankId());
        statment.setString(6,consumerComparing.getProductName());
        statment.setDouble(7,consumerComparing.getPercentage());
        statment.setInt(8,consumerComparing.getService());


    }

    private void Depo(List<ConsumerComparing> consumerComparingList, ResultSet set) throws SQLException {
        ConsumerComparing consumerComparing;
        while (set.next()) {
            consumerComparing = new ConsumerComparing();
            consumerComparing.setId(set.getInt("id"));
            consumerComparing.setIdCheck(set.getInt("idCheck"));
            consumerComparing.setSID(set.getString("SID"));
            consumerComparing.setAmount(set.getInt("Amount"));
            consumerComparing.setMonths(set.getInt("months"));
            consumerComparing.setBankId(set.getInt("bankId"));
            consumerComparing.setProductName(set.getString("productName"));
            consumerComparing.setPercentage(set.getDouble("percentage"));
            consumerComparing.setService(set.getInt("service"));
            consumerComparing.setBanksList(consumerComparingList);


            consumerComparingList.add(consumerComparing);
        }
    }

}
