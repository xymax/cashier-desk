package com.cashsystem.service;

import com.cashsystem.dao.AccountDao;
import com.cashsystem.entity.Account;

public class AccountService { //账户服务
    private AccountDao accountDao;
    public AccountService(){
        this.accountDao=new AccountDao();
    }

    public Account Login(String username,String password){
        return this.accountDao.login(username,password);
    }
    public boolean checkDuplicateUserName(String username) {
        return this.accountDao.checkUserName(username);
    }

    public boolean register(Account account) {
        return this.accountDao.register(account);
    }
}
