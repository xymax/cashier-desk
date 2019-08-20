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


    @Override
    public String toString() {
        System.out.println("----------------  帐号列表信息  ----------------------");
        System.out.println("|    编号    |    姓名   |    账号   |    类型      |    状态    |");
        StringBuilder sb = new StringBuilder();
        sb.append("|"+"   "+this.getId() + "       "+"|")
                .append("    "+this.getName() + "   "+"|")
                .append("    "+this.getUsername() + "     "+"|")
                .append("    "+this.getAccountType() + "  "+"|")
                .append("    "+this.getAccountStatus().getDesc()+"|"+"\n");
        sb.append("======================================================");
        return sb.toString();

    }
}
