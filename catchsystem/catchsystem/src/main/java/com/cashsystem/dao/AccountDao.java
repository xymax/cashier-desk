package com.cashsystem.dao;

import com.cashsystem.common.AccountStatus;
import com.cashsystem.common.AccountType;
import com.cashsystem.entity.Account;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

public class AccountDao extends BaseDao {


    //操作数据库
    /*
     * 登录数据库
     *
     * */
    public Account login(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account account = null;
        try {
            connection = this.getConnection(true);
            String sql = "select id,username,password,name,account_type," +
                    "account_status from account where username=? " +
                    "and password=?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, DigestUtils.md5Hex(password));
            resultSet = preparedStatement.executeQuery();
            //返回结果集到resultSet
            if (resultSet.next()) {
                //解析resultSet得到account
                account = this.extractAccount(resultSet);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return account;
    }


    /*
     *
     * 提取账户
     * */
    private Account extractAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setUsername(resultSet.getString("username"));
        account.setPassword(resultSet.getString("password"));
        account.setName(resultSet.getString("name"));
        account.setAccountType(AccountType.valueOf(resultSet.getInt("account_type")));
        account.setAccountStatus(AccountStatus.valueOf(resultSet.getInt("account_status")));

        return account;

    }

    /*
     * 检查用户名
     *
     * */
    public boolean checkUserName(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.getConnection(true);
            String sql = "select * from account where username=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, statement, connection);
        }
        return false;

    }

    /*
     *
     * 注册
     *
     * */
    public boolean register(Account account) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean effect = false;
        try {
            connection = this.getConnection(true);
            String sql = "insert into account (username, password, name, account_type, account_status) values (?,?,?,?,?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, account.getUsername());
            statement.setString(2, DigestUtils.md5Hex(account.getPassword()));
            statement.setString(3, account.getName());
            statement.setInt(4, account.getAccountType().getFlag());
            statement.setInt(5, account.getAccountStatus().getFlag());
            effect = (statement.executeUpdate() == 1);
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                account.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, statement, connection);
        }
        return effect;
    }
    /*
     * 查询所有账户
     *
     *
     * */

    public List<Account> queryAllAccount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Account> accounts = new ArrayList<>();
        try {
            connection = this.getConnection(true);
            String sql = "select * from account";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Account account = this.extractAccount(resultSet);
                accounts.add(account);
            }
            return accounts;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            this.closeResource(resultSet, statement, connection);
        }


    }


    //得到原账户的密码

    public Account getAccountByPassword(String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Account account = null;
        try {
            connection = this.getConnection(true);
            String sql = "select * from Account where password=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, DigestUtils.md5Hex(password));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                account = this.extractAccount(resultSet);
            }
            return account;

        } catch (SQLException e) {
            e.printStackTrace();
            return account;
        } finally {
            this.closeResource(resultSet, statement, connection);

        }
    }

    //更新密码
    public boolean updatapassword(String newPassword,Account account) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection=this.getConnection(true);
            String sql="update account set password= ? where id=?";
            statement=connection.prepareStatement(sql);
            statement.setString(1,DigestUtils.md5Hex(newPassword));
            statement.setInt(2,account.getId());
            return statement.executeUpdate()==1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


}
