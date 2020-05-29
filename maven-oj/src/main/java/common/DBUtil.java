package common;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 使用该DBUtil建立数据库连接，进一步操作数据库
 * @author zhaomin
 * @date 2020/5/29 23:42
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.0.0:3306/online_oj?characterEncoding=utf-8&&useSSL=true";
    private static final String USERNAME="root";
    private static final String PASSWORD = "";

    private static volatile DataSource dataSource=null;

    private DBUtil(){}

    public  static DataSource getDataSource(){
        if (dataSource==null) {
            synchronized (DBUtil.class) {
                if(dataSource==null){
                    dataSource=new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setURL(URL);
                    ((MysqlDataSource)dataSource).setUser(USERNAME);
                    ((MysqlDataSource)dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }
    public static Connection getConnection(){
        try {
            //内置了数据库连接池
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if(resultSet!=null){
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
