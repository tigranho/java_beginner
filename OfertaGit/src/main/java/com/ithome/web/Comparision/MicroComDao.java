package com.ithome.web.Comparision;

import com.ithome.web.Bean.MicroLoans;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MicroComDao {

    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }

    public List<MicroComparing> getAll() throws SQLException {
        MicroComparing microComparing = null;
        List<MicroComparing> microComparingList = new ArrayList<>();
        ResultSet set = null;
        Statement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `microcompare` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            Depo(microComparingList, set);
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
        return microComparingList;
    }

    public int Update(int Amount, int id, String SId) throws SQLException {
        int rowsUpdated = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = connectToData();

            String sql = "UPDATE `microcompare` SET `Amount`=? WHERE `idCheck`=" + id + " AND `SID` like '%" + SId +"%'" ;
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

            String sql = "UPDATE `microcompare` SET `months`=? WHERE `idCheck`=" + id + " AND `SID` like '%" + SId +"%'" ;
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

    public int Add(MicroComparing microComparing) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `microcompare`" +
                    "(`id`,`idCheck`, `SID`,`Amount`,`months`,`bankId`,`productName`,`percentage`,`service`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatment(microComparing, statment);
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

    public List<MicroComparing> getBySession(String SId) throws SQLException {
        MicroComparing microComparing = null;
        List<MicroComparing> microComparingList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `microcompare` WHERE  `SID` like '%" + SId + "%'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            Depo(microComparingList, set);
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
        return microComparingList;
    }

    public List<MicroComparing> getById(int id, String SId) throws SQLException {
        MicroComparing microComparing = null;
        List<MicroComparing> microComparingList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `microcompare` WHERE `idCheck`=" + id + " AND `SID` like '%" + SId + "%'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            Depo(microComparingList, set);
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
        return microComparingList;
    }

    public int Delete(int id, String SId) throws SQLException {
        int rowsDeleted = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();

            String sql = "DELETE FROM `microcompare` WHERE  idCheck=" + id + " AND `SID` like '%" + SId +"%'" ;
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

            String sql = "DELETE FROM `microcompare` WHERE `SID` like '%" + SId +"%'" ;
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


    private void setStatment(MicroComparing microComparing, PreparedStatement statment) throws SQLException {

        statment.setInt(1, microComparing.getIdCheck());
        statment.setString(2, microComparing.getSID());
        statment.setInt(3, microComparing.getAmount());
        statment.setInt(4, microComparing.getMonths());
        statment.setInt(5, microComparing.getBankId());
        statment.setString(6,microComparing.getProductName());
        statment.setDouble(7,microComparing.getPercentage());
        statment.setInt(8,microComparing.getService());

    }


    private void Depo(List<MicroComparing> microComparingList, ResultSet set) throws SQLException {
        MicroComparing microComparing;
        while (set.next()) {
            microComparing = new MicroComparing();
            microComparing.setId(set.getInt("id"));
            microComparing.setIdCheck(set.getInt("idCheck"));
            microComparing.setSID(set.getString("SID"));
            microComparing.setAmount(set.getInt("Amount"));
            microComparing.setMonths(set.getInt("months"));
            microComparing.setBankId(set.getInt("bankId"));
            microComparing.setProductName(set.getString("productName"));
            microComparing.setPercentage(set.getDouble("percentage"));
            microComparing.setService(set.getInt("service"));
            microComparing.setBanksList(microComparingList);


            microComparingList.add(microComparing);
        }
    }


}
