package com.cashsystem.cmd.impl.entrance;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.EntranceCommand;
import com.cashsystem.cmd.impl.AbstracCommand;
import com.cashsystem.common.AccountStatus;
import com.cashsystem.entity.Account;

@CommandMeta(
        name="DL",
        desc="登陆",
        group = "入口命令"
)
@EntranceCommand
public class LoginCommand extends AbstracCommand {  //登陆命令

    @Override
    public void execute(Subject subject) {
        Account account=subject.getAccount();
        if (account!=null){
            System.out.println("你已经登录过");
        }
        System.out.println("请输入用户名:");
        String username = scanner.nextLine();
        System.out.println("请输入密码:");
        String passward = scanner.nextLine();

        account=this.accountService.Login(username,passward);


        if (account!=null&&account.getAccountStatus()== AccountStatus.UNLOCK){
            System.out.println("登录成功");
            subject.setAccount(account);
        }else {
            System.out.println("密码或用户名错误");
        }


    }
}

