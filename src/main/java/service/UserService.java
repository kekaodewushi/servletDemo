package service;

import domain.Customers;
import org.apache.log4j.Logger;
import utils.JdbcUtil;

import java.sql.*;
import java.util.Date;


/**
 * Created by zangyaoyi on 2017/3/23.
 */
public class UserService {
    private static Logger logger = Logger.getLogger(UserService.class.getName());

    public static boolean validate(String userName, String passWord) {
        Connection con = JdbcUtil.getConn();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            String sql = "select * from customers where user_name=? and pass_word=?";
            logger.debug("sql=" + sql);
            logger.debug("userName=" + userName);
            logger.debug("password=" + passWord);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, passWord);

            rs = preparedStatement.executeQuery();
            result = rs.next();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(con, preparedStatement, rs);
        }
        return result;

    }

    public static int insert(Customers customers) {
        Connection conn = JdbcUtil.getConn();
        int i = 0;
        String sql = "insert into customers (user_name,pass_word,create_time,update_time) values(?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, customers.getUserName());
            ps.setString(2, customers.getPassWord());
            ps.setTimestamp(3, new Timestamp(new Date().getTime()));
            ps.setTimestamp(4, new Timestamp(customers.getUpdateTime().getTime()));
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn, ps, null);
        }
        return i;
    }
}
