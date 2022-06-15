package com.ithome.web.AdminDao;

import com.ithome.web.Bean.Comments;
import com.ithome.web.Connection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoComtroller {


    /**
     * Add new agriculture credit
     *
     * @param comments
     * @return
     * @throws SQLException
     */
    public int AddNewComment(Comments comments) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;

        try {
            connection = connectToData();
            String insertQuery = "INSERT INTO `commentscontroller`" +
                "(`Commentid`, `productCode`, `Comment1_Am`, `Comment2_Am`," +
                "`Comment3_Am`,`Comment1_En`,`Comment2_En`,`Comment3_En`,`Comment1_Ru`," +
                "`Comment2_Ru`,`Comment3_Ru`) VALUES (Default,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentForNonMarketing(comments, statment);
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

    public List<Comments> getAllComments() throws SQLException {
        Comments comments = null;
        List<Comments> commentsList = new ArrayList<>();
        Connection connection = null;
        ResultSet set = null;
        Statement statment = null;
        try {
            connection = connectToData();
            String sql = "SELECT * FROM `agriculturalcreditcontroller` ";
            statment = connection.createStatement();
            set = statment.executeQuery(sql);
            ListOfComments(commentsList, set);
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
        return commentsList;
    }

    /**
     * get card by card Min Service Fee
     *
     * @param ProductCode
     * @return
     */
    public List<Comments> getCommentByProdcutCode(int ProductCode) throws SQLException {
        Comments comments = null;
        List<Comments> commentsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = connectToData();
            String sql = "SELECT *  FROM  `commentscontroller` WHERE `productCode` =" + ProductCode;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfComments(commentsList, set);

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
        return commentsList;
    }

    private void ListOfComments(List<Comments> commentsList, ResultSet set) throws SQLException {
        Comments co;
        while (set.next()) {
            co = new Comments();
            co.setProductCode(set.getInt("ProductCode"));
            co.setComment1_Am(set.getString("Comment1_Am"));
            co.setComment2_Am(set.getString("Comment2_Am"));
            co.setComment3_Am(set.getString("Comment3_Am"));
            co.setComment1_En(set.getString("Comment1_En"));
            co.setComment2_En(set.getString("Comment2_En"));
            co.setComment3_En(set.getString("Comment3_En"));
            co.setComment1_Ru(set.getString("Comment1_Ru"));
            co.setComment2_Ru(set.getString("Comment2_Ru"));
            co.setComment3_Ru(set.getString("Comment3_Ru"));
            commentsList.add(co);
        }
    }

    public int updateAgInData(Comments comments, int productCode) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;

        try {
            connection = connectToData();
            String sql = "UPDATE `commentscontroller`  " +
                "SET `Comment1_Am` = ?,`Comment2_Am` = ?,`Comment3_Am` = ? " +
                "WHERE `productCode` = " + productCode;
            statment = connection.prepareStatement(sql);
            setStatment(comments, statment);

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


    private void setStatmentForNonMarketing(Comments comments, PreparedStatement statement) throws SQLException {
        statement.setInt(1, comments.getProductCode());
        statement.setString(2, comments.getComment1_Am());
        statement.setString(3, comments.getComment2_Am());
        statement.setString(4, comments.getComment3_Am());
        statement.setString(5, comments.getComment1_En());
        statement.setString(6, comments.getComment2_En());
        statement.setString(7, comments.getComment3_En());
        statement.setString(8, comments.getComment1_Ru());
        statement.setString(9, comments.getComment2_Ru());
        statement.setString(10, comments.getComment3_Ru());
    }


    private void setStatment(Comments comments, PreparedStatement statement) throws SQLException {

        statement.setString(1, comments.getComment1_Am());
        statement.setString(2, comments.getComment2_Am());
        statement.setString(3, comments.getComment3_Am());
    }

    private Connection connectToData() {
        return DBConnection.getConnectionToDatabase();
    }


}
