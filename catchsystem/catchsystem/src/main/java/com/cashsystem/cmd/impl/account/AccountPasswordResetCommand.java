package com.cashsystem.cmd.impl.account;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstracCommand;
import com.cashsystem.entity.Account;

@CommandMeta(
        name = "CZMM",
        desc = "重置密码",
        group = "账号信息"
)
@AdminCommand
public class AccountPasswordResetCommand extends AbstracCommand { //密码重置命令

    @Override
    public void execute(Subject subject) {
        System.out.println("请输入您的原密码");
        String oldpassword = scanner.next();
        Account account = accountService.getAccountByPassword(oldpassword);
        if (account != null) {
            System.out.println("请输入新的的密码");
            String newpassword1 = scanner.next();
            if (newpassword1.equals(oldpassword)) {
                System.out.println("对不起，您输入的密码和原密码一致，请您重新输入密码");
                return;
            }

            System.out.println("请再次输入你的密码");
            String newpassword2 = scanner.next();
            if (newpassword1.equals(newpassword2)) {

                boolean effect = this.accountService.updatapassword(newpassword1, subject.getAccount());

                if (effect) {
                    subject.setAccount(null);
                    System.out.println("密码更改成功，请重新登录");
                } else {
                    System.out.println("密码更改失败，请联系管理员");
                }

            } else {
                System.out.println("您的两次密码输入有误，请您从新输入");
            }

        } else {
            System.out.println("对不起，您的密码输入有误");
        }

    }
}