package com.cashsystem.cmd.impl.account;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstracCommand;

@CommandMeta(
        name="CKZH",
        desc="查看账户",
        group = "账号信息"
)
@AdminCommand
public class AccountBrowseCommand extends AbstracCommand { //账号浏览命令

    @Override
    public void execute(Subject subject) {
        System.out.println("浏览商品");
        //


    }
}
