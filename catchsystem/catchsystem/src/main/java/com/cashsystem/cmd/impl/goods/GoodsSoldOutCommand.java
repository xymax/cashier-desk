package com.cashsystem.cmd.impl.goods;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstracCommand;
import com.cashsystem.entity.Goods;

import java.util.Scanner;

@CommandMeta(
        name = "XJSP",
        desc = "下架商品",
        group = "商品信息"
)
@AdminCommand
public class GoodsSoldOutCommand extends AbstracCommand { //货物下架命令

    @Override
    public void execute(Subject subject) {
        System.out.println("请输入下架商品的编号");
        int goodsId = scanner.nextInt();

        Goods goods = this.goodsService.getGoods(goodsId);
        if (goods == null) {
            System.out.println("对不起，没有找到您要下架的商品");
        } else {
            System.out.println("下架商品如下");
            System.out.println(goods);
            System.out.println("请您确认，是否下架给商品，如果是请输入'y'，否则请输入'n'");
            String confirm = scanner.next();
            if ("y".equalsIgnoreCase(confirm)) {
                boolean effect = this.goodsService.soldOutGoods(goodsId);
                if (effect) {
                    System.out.println("商品成功下架");
                } else {
                    System.out.println("商品下架失败，稍后重试");
                }
            }

        }
    }
}

