package com.cashsystem.cmd.impl;

import com.cashsystem.cmd.Command;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.common.OrderStatus;
import com.cashsystem.service.AccountService;
import com.cashsystem.service.GoodsService;
import com.cashsystem.service.OrderService;

@CustomerCommand
public abstract class AbstracCommand implements Command { //抽象命令
    public AccountService accountService;
    public GoodsService goodsService;
    public OrderService orderService;

    public AbstracCommand() {
        this.accountService = new AccountService();
        this.goodsService = new GoodsService();
        this.orderService = new OrderService();


    }
}
