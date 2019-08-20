package com.cashsystem.cmd.impl.common;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.cmd.annotation.EntranceCommand;
import com.cashsystem.cmd.impl.AbstracCommand;

@CommandMeta(
        name = "GUXT",
        desc = "关于系统",
        group = "公共命令"
)
@AdminCommand
@CustomerCommand
@EntranceCommand
public class AboutCommand extends AbstracCommand {  //关于系统命令

    @Override
    public void execute(Subject subject) {
        System.out.println("**************字符界面收银平台*****************");
        System.out.println("*****************作者：向阳*******************");
        System.out.println("****************2019-08-04*******************");
        System.out.println("*********************************************");
    }
}

