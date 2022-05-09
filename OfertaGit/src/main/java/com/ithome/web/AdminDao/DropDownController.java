package com.ithome.web.AdminDao;

import com.ithome.web.Bean.AgriculturalCredit;
import com.ithome.web.Bean.Cards;
import com.ithome.web.Bean.DropDowns;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DropDownController {
    /**
     * Show all getAllDropDowns
     */
    public List<DropDowns> getAllDropDowns() throws SQLException {
        DropDowns dropDowns = null;
        List<DropDowns> dropDownsList = new ArrayList<DropDowns>();
        Connection connection = null;
        Statement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `ddcontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            dropDownsList(dropDownsList, set);
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
        return dropDownsList;
    }

    /**
     * Get cards by id
     *
     * @param id
     * @return
     */
    public List<DropDowns> getDropDownsById(int id) throws SQLException {

        DropDowns dropDowns = null;
        List<DropDowns> dropDownsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `ddcontroller` WHERE ddid=" + id;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            dropDownsList(dropDownsList, set);

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
        return dropDownsList;
    }

    /**
     * Update ag in data
     *
     * @param prepaireCardInfoForData
     * @param id
     * @return
     */
    public int UpdateDropDown(DropDowns prepaireCardInfoForData, int id) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = connectToData();
            String sql = "UPDATE `ddcontroller`  " +
                    "SET currancy=?,minAmount=?,maxAmount=?,Steps=?,position=?  " +
                    "WHERE ddid=" + id;
            statment = connection.prepareStatement(sql);
            setStatmentFor(prepaireCardInfoForData, statment);

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

    public List<DropDowns> getDropDownsByPosition(String position) throws SQLException {

        DropDowns dropDowns = null;
        List<DropDowns> dropDownsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `ddcontroller` WHERE position like '%" + position + "%'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            dropDownsList(dropDownsList, set);

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
        return dropDownsList;
    }

    private void setStatmentFor(DropDowns prepaireCardInfoForData, PreparedStatement statement) throws SQLException {
        statement.setString(1, prepaireCardInfoForData.getCurrancy());
        statement.setString(2, prepaireCardInfoForData.getMinAmount());
        statement.setString(3, prepaireCardInfoForData.getMaxAmount());
        statement.setString(4, prepaireCardInfoForData.getSteps());
        statement.setString(5, prepaireCardInfoForData.getPosition());
    }


    private void dropDownsList(List<DropDowns> dropDownsList, ResultSet set) throws SQLException {
        DropDowns dropDowns = null;
        while (set.next()) {
            dropDowns = new DropDowns();
            dropDowns.setId(set.getInt("ddid"));
            dropDowns.setCurrancy(set.getString("currancy"));
            dropDowns.setMinAmount(set.getString("minAmount"));
            dropDowns.setMaxAmount(set.getString("maxAmount"));
            dropDowns.setSteps(set.getString("steps"));
            dropDowns.setPosition(set.getString("position"));

            dropDownsList.add(dropDowns);
        }
    }

    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }


}
