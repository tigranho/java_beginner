package com.ithome.web.Comparision;

import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgComDao {

    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }

    public List<AgComparing> getAll() throws SQLException {
        AgComparing agComparing = null;
        List<AgComparing> agComparingList = new ArrayList<>();
        ResultSet set = null;
        Statement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agcompare` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            Depo(agComparingList, set);
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
        return agComparingList;
    }

    public int Update(int Amount, int id, String SId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `agcompare` SET `Amount`=? WHERE `idCheck`=" + id + " AND `SID` like '%" + SId +"%'" ;
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

            String sql = "UPDATE `agcompare` SET `months`=? WHERE `idCheck`=" + id + " AND `SID` like '%" + SId +"%'" ;
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

    public int Add(AgComparing agComparing) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `agcompare`" +
                    "(`id`,`idCheck`, `SID`,`Amount`,`months`,`bankId`,`productName`,`percentage`,`service`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(agComparing, statment);
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

    public List<AgComparing> getById(int id, String SId) throws SQLException {
        AgComparing agComparing = null;
        List<AgComparing> agComparingList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `agcompare` WHERE `idCheck`=" + id + " AND `SID` like '%" + SId + "%'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            Depo(agComparingList, set);
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
        return agComparingList;
    }

    public int Delete(int id, String SId) throws SQLException {
        int rowsDeleted = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();

            String sql = "DELETE FROM `agcompare` WHERE  idCheck=" + id + " AND `SID` like '%" + SId +"%'" ;
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

            String sql = "DELETE FROM `agcompare` WHERE  `SID` like '%" + SId +"%'" ;
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


    private void setStatment(AgComparing agComparing, PreparedStatement statment) throws SQLException {

        statment.setInt(1, agComparing.getIdCheck());
        statment.setString(2, agComparing.getSID());
        statment.setInt(3, agComparing.getAmount());
        statment.setInt(4, agComparing.getMonths());
        statment.setInt(5, agComparing.getBankId());
        statment.setString(6,agComparing.getProductName());
        statment.setDouble(7,agComparing.getPercentage());
        statment.setInt(8,agComparing.getService());


    }


    private void Depo(List<AgComparing> agComparingList, ResultSet set) throws SQLException {
        AgComparing agComparing;
        while (set.next()) {
            agComparing = new AgComparing();
            agComparing.setId(set.getInt("id"));
            agComparing.setIdCheck(set.getInt("idCheck"));
            agComparing.setSID(set.getString("SID"));
            agComparing.setAmount(set.getInt("Amount"));
            agComparing.setMonths(set.getInt("months"));
            agComparing.setBankId(set.getInt("bankId"));
            agComparing.setProductName(set.getString("productName"));
            agComparing.setPercentage(set.getDouble("percentage"));
            agComparing.setService(set.getInt("service"));
            agComparing.setBanksList(agComparingList);


            agComparingList.add(agComparing);
        }
    }


}
