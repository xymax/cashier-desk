package com.cashsystem.cmd;

import com.cashsystem.cmd.impl.AbstracCommand;
import com.cashsystem.cmd.impl.Commands;
import com.cashsystem.entity.Account;

public class CheckStand extends AbstracCommand {  //检查状态
    public static void main(String[] args) {
        Subject subject = new Subject();
        new CheckStand().execute(subject);
    }

    @Override
    public void execute(Subject subject) {
        Commands.getCachedHelpCommands().execute(subject);
        while (true) {
            System.out.println(">>>");
            //dl
            String line = scanner.nextLine();
            String commandCode = line.trim().toUpperCase();
            Account account = subject.getAccount();
            if (account == null) {
                Commands.getEntranceCommand(commandCode).execute(subject);
                this.execute(subject);
            } else {
                switch (account.getAccountType()) {
                    case ADMIN:
                        Commands.getAdminCommand(commandCode).execute(subject);
                        break;
                    case CUSTOMER:
                        Commands.getCustomerCommand(commandCode).execute(subject);
                        break;
                    default:
                }
            }
        }
    }
}