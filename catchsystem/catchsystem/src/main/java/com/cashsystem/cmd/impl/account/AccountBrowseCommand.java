package com.cashsystem.cmd.impl.account;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstracCommand;
import com.cashsystem.entity.Account;

import java.util.List;

@CommandMeta(
        name="CKZH",
        desc="查看账户",
        group = "账号信息"
)
@AdminCommand
public class AccountBrowseCommand extends AbstracCommand { //账号浏览命令

    @Override
    public void execute(Subject subject) {
        System.out.println("查询所有的账户");
        List<Account> accountList=accountService.queryAllAccount();
        if (accountList.isEmpty()){
            System.out.println("对不起，您的账号为空");
        }else {
            for (Account account : accountList) {
                System.out.println(account);
            }

        }

    }
}
