package com.cashsystem.cmd.impl.entrance;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.EntranceCommand;
import com.cashsystem.cmd.impl.AbstracCommand;
import com.cashsystem.common.AccountStatus;
import com.cashsystem.common.AccountType;
import com.cashsystem.entity.Account;

@CommandMeta(
        name = "ZC",
        desc = "注册",
        group = "入口命令"
)
@EntranceCommand
public class RegisterCommand extends AbstracCommand { //注册命令

    @Override
    public void execute(Subject subject) {

        System.out.println("请输入你的用户名");
        String username=scanner.nextLine();
        System.out.println("请输入你的密码");
        String password1=scanner.nextLine();
        System.out.println("请再次输入你的密码");
        String password2=scanner.nextLine();
        if (!password1.equals(password2)){
            System.out.println("两次密码不一致");
            return;
        }

        System.out.println("请输入姓名");
        String name=scanner.nextLine();

        System.out.println("请输入账户的类型  1  管理员   2   普通用户");
        int accountType=scanner.nextInt();
        AccountType accountType1=AccountType.valueOf(accountType);
        System.out.println("请输入用户的状态：1.启用  2.启停 ");
        int accountStatus1=scanner.nextInt();
        AccountStatus accountStatus=AccountStatus.valueOf(accountStatus1);



        Account account=new Account();
        account.setUsername(username);
        account.setPassword(password1);
        account.setName(name);
        account.setAccountStatus(accountStatus);
        account.setAccountType(accountType1);
        boolean effect =this.accountService.register(account);
        if (effect){
            System.out.println("注册成功");
        }else {
            System.out.println("注册失败");
        }


    }
}

