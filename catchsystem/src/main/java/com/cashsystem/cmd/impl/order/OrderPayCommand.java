package com.cashsystem.cmd.impl.order;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.cmd.impl.AbstracCommand;

@CommandMeta(
        name="ZFDD",
        desc="支付订单",
        group = "订单信息"
)
@CustomerCommand
public class OrderPayCommand extends AbstracCommand { //订单支付命令

    @Override
    public void execute(Subject subject) {

    }
}
