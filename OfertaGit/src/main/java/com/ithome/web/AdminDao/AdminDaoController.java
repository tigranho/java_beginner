package com.ithome.web.AdminDao;

import com.ithome.web.Bean.Admin;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoController {
    /**
     * Method will check if Admin exist or not
     *
     * @param username
     * @param password
     * @return
     */
    public boolean validateAdminUsers(String username, String password) {
        return validatePass(username, password);
    }

    private boolean validatePass(String username, String password) {
        boolean isValidAdminUser = false;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();
            System.out.println("Connection " + connection);
            System.out.println(username + " " + password);
            String sql = "SELECT *  FROM  `admincontroller` WHERE adminusername=? AND  adminpassword=?";
            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setString(1, username);
            statment.setString(2, password);
            ResultSet set = statment.executeQuery();
            while (set.next()) {
                isValidAdminUser = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return isValidAdminUser;
    }

    /**
     * Method will return AdminId using Username
     *
     * @param username
     * @return
     */
    public int getAdminIdByUserName(String username) {
        int adminId = 0;
        try {
            // get the connection for the database
            Connection connection = DBConnection.getConnectionToDatabase();
            // System.out.println("Connection Admin");
            // write the insert query
            // String sql = "select * from adminsection where admin_user_name=? and
            // admin_password =?";
            String sql = "SELECT *  FROM `admincontroller` WHERE adminusername=?";
            //  System.out.println("Connection SQL :  " + sql);
            // set parameters with PreparedStatement
            PreparedStatement statment = connection.prepareStatement(sql);
            //  System.out.println("Connection statement :  " + statment);

            statment.setString(1, username);
            //   System.out.println("Connection username :  " + username);

            // execute the statement and check whether user exists
            ResultSet set = statment.executeQuery();
            while (set.next()) {
                adminId = set.getInt("adminId");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return adminId;
    }

    /**
     * get all admin info by adminId
     *
     * @param adminId
     * @return
     */
    public List<Admin> getAllAdminInfo(int adminId) {
        Admin admin = null;
        List<Admin> adminList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnectionToDatabase();
            String sql = "SELECT * FROM `admincontroller` where adminid =" + adminId;
            Statement statment = connection.createStatement();
            ResultSet set = statment.executeQuery(sql);
            while (set.next()) {

                admin = new Admin();
                admin.setAdminid(set.getInt("adminid"));
                admin.setUsername(set.getString("adminusername"));
                admin.setPassword(set.getString("adminpassword"));
                admin.setAdminpincode(set.getInt("adminpincode"));

                adminList.add(admin);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("sqlException in Application in Admin Section  : " + exception);
        }
        return adminList;
    }

    /**
     * get Admin pin code
     * @param adminId
     * @return
     */
    public int getAdminpinCode(int adminId) {
        int pinCode = 0;
        try {
            // get the connection for the database
            Connection connection = DBConnection.getConnectionToDatabase();
            // System.out.println("Connection Admin");
            // write the insert query
            // String sql = "select * from adminsection where admin_user_name=? and
            // admin_password =?";
            String sql = "SELECT *  FROM  `admincontroller` WHERE adminid=" + adminId;
            //  System.out.println("Connection SQL :  " + sql);
            // set parameters with PreparedStatement
            PreparedStatement statment = connection.prepareStatement(sql);
            // execute the statement and check whether user exists
            ResultSet set = statment.executeQuery();
            while (set.next()) {
                pinCode = set.getInt("adminpincode");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return pinCode;
    }

    /**
     * Update Admin info
     * @param prepaireAdminInfoForData
     * @param adminId
     * @return
     */
    public int updateAdminInfo(Admin prepaireAdminInfoForData, int adminId) {
        int rowsUpdated = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "UPDATE `admincontroller`  SET adminusername=?,adminpassword=?,adminpincode=? WHERE adminid=" + adminId;

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, prepaireAdminInfoForData.getUsername());
            statement.setString(2, prepaireAdminInfoForData.getPassword());
            statement.setInt(3, prepaireAdminInfoForData.getAdminpincode());

            rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("An existing CATEGORY NAME was updated successfully!");
            }
        } catch (SQLException exception) {
            System.out.println("sqlException in Application in CATEGORY NAME UPDATE Section  : " + exception);
            exception.printStackTrace();
        }
        return rowsUpdated;
    }
}
