package com.cashsystem.cmd.impl.account;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstracCommand;

@CommandMeta(
        name = "ZHGL",
        desc = "账号管理",
        group = "账号信息"
)
@AdminCommand
public class AccountStatusSetCommand extends AbstracCommand {//账号状态命令

    @Override
    public void execute(Subject subject) {

    }
}
