package com.cashsystem.dao;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {  //连接数据库
    private static volatile DataSource dataSource;

    //采用基本实现
    private DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DataSource.class) {
                if (dataSource == null) {
                    dataSource = (DataSource) new MysqlDataSource();//MySql数据源
                    String host = "127.0.0.1";
                    String port = "3306";
                    ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://" + (host + ":" + port)+ "/check_stand?useSSL=false" );
                    ((MysqlDataSource) dataSource).setUser("root");
                    ((MysqlDataSource) dataSource).setPassword("123456");
                }
            }
        }
        return dataSource;
    }

    protected Connection getConnection(boolean autoCommit) throws SQLException {
        //获取连接
        Connection connection = this.getDataSource().getConnection();
        //如果true  每写一条语句 自动进行提交
        connection.setAutoCommit(autoCommit);
        return connection;
    }

    protected void closeResource(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        //结果 -> 命令 -> 连接
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
