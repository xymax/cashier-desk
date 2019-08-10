package com.cashsystem.cmd.impl;


import com.cashsystem.cmd.Command;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.cmd.annotation.EntranceCommand;
import com.cashsystem.cmd.impl.account.AccountBrowseCommand;
import com.cashsystem.cmd.impl.account.AccountPasswordResetCommand;
import com.cashsystem.cmd.impl.account.AccountStatusSetCommand;
import com.cashsystem.cmd.impl.common.AboutCommand;
import com.cashsystem.cmd.impl.common.HelpCommand;
import com.cashsystem.cmd.impl.common.QuitCommand;
import com.cashsystem.cmd.impl.entrance.LoginCommand;
import com.cashsystem.cmd.impl.entrance.RegisterCommand;
import com.cashsystem.cmd.impl.goods.GoodsBrowseCommand;
import com.cashsystem.cmd.impl.goods.GoodsPutAwayCommand;
import com.cashsystem.cmd.impl.goods.GoodsSoldOutCommand;
import com.cashsystem.cmd.impl.goods.GoodsUpdateCommand;
import com.cashsystem.cmd.impl.order.OrderBrowseCommand;
import com.cashsystem.cmd.impl.order.OrderPayCommand;

import java.util.*;

public class Commands {  //命令 ，通过集合来获取
    public static Map<String, Command> ADMIN_COMANDS = new HashMap<>();
    public static Map<String, Command> CUSTOMER_COMANDS = new HashMap<>();
    public static Map<String, Command> ENTRANCE_COMANDS = new HashMap<>();

    private static final Set<Command> COMMANDS = new HashSet<>();
    private static final Command CACHED_HELP_COMMANDS;

    static {
        Collections.addAll(COMMANDS,
                new AccountBrowseCommand(),
                new AccountPasswordResetCommand(),
                new AccountStatusSetCommand(),
                new AboutCommand(),
                CACHED_HELP_COMMANDS = new HelpCommand(),
                new QuitCommand(),
                new LoginCommand(),
                new RegisterCommand(),
                new GoodsBrowseCommand(),
                new GoodsPutAwayCommand(),
                new GoodsSoldOutCommand(),
                new GoodsUpdateCommand(),
                new OrderBrowseCommand(),
                new OrderPayCommand()
        );
        for (Command command : COMMANDS) {
            //利用反射，将命令进行分类
            Class<?> cls = command.getClass();
            AdminCommand adminCommand = cls.getAnnotation(AdminCommand.class);
            CustomerCommand customerCommand = cls.getAnnotation(CustomerCommand.class);
            EntranceCommand entranceCommand = cls.getAnnotation(EntranceCommand.class);
            CommandMeta commandMeta = cls.getAnnotation(CommandMeta.class);

            String commandKey = commandMeta.name();

            if (adminCommand != null) {
                ADMIN_COMANDS.put(commandKey, command);
            }
            if (customerCommand != null) {
                CUSTOMER_COMANDS.put(commandKey, command);
            }
            if (entranceCommand != null) {
                ENTRANCE_COMANDS.put(commandKey, command);
            }

        }
    }

    public static Command getCachedHelpCommands() {
        return CACHED_HELP_COMMANDS;
    }

    public static Command getAdminCommand(String commandKey) {
        return getcommands(commandKey, ADMIN_COMANDS);

    }

    public static Command getCustomerCommand(String commandKey) {
        return getcommands(commandKey, CUSTOMER_COMANDS);

    }

    public static Command getEntranceCommand(String commandKey) {
        return getcommands(commandKey, ENTRANCE_COMANDS);
    }

    public static Command getcommands(String commandKey, Map<String, Command> commandMap) {
        //遍历相应的Map根据commandKey，得到相应的Value值
        return commandMap.getOrDefault(commandKey, CACHED_HELP_COMMANDS);


    }


}
