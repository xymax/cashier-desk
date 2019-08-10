package com.cashsystem.cmd.impl.account;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstracCommand;

@CommandMeta(
        name="CZMM",
        desc="重置密码",
        group = "账号信息"
)
@AdminCommand
public class AccountPasswordResetCommand extends AbstracCommand { //密码重置命令

    @Override
    public void execute(Subject subject) {

    }
}