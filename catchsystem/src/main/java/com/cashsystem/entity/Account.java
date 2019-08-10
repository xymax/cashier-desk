package com.cashsystem.entity;

import com.cashsystem.common.AccountStatus;
import com.cashsystem.common.AccountType;
import lombok.Data;

@Data
public class Account {  //账户
    private Integer id;
    private String username;
    private String password;
    private String name;
    private AccountType accountType;
    private AccountStatus accountStatus;
}
