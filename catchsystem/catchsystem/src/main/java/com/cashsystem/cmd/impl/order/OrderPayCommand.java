package com.cashsystem.cmd.impl.order;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.cmd.impl.AbstracCommand;
import com.cashsystem.common.OrderStatus;
import com.cashsystem.entity.Goods;
import com.cashsystem.entity.Order;
import com.cashsystem.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CommandMeta(
        name = "ZFDD",
        desc = "支付订单",
        group = "订单信息"
)
@CustomerCommand
public class OrderPayCommand extends AbstracCommand { //订单支付命令

    @Override
    public void execute(Subject subject) {
        System.out.println("请输入你要购买的货物id以及数量多个货物之间使用,隔开;(格式：1-8，3-5)");
        String string = scanner.nextLine();
        //[0]-->1-8 ,[1]-->2-5
        String[] strings = string.split(",");
        //把所有需要购买的商品存放至goodsList
        List<Goods> goodsList = new ArrayList<>();
        for (String goodsString : strings) {
            //[0] = "1" [1] = "8"
            String[] str = goodsString.split("-");
            Goods goods = this.goodsService.getGoods(Integer.parseInt(str[0]));
            goods.setBuyGoodsNum(Integer.parseInt(str[1]));
            goodsList.add(goods);
        }

        Order order = new Order();
        //获取系统当前时间，订单不会冲突
        order.setId(String.valueOf(System.currentTimeMillis()));
        order.setAccount_id(subject.getAccount().getId());
        order.setAccount_name(subject.getAccount().getUsername());
        order.setCreate_time(LocalDateTime.now());

        int totalMoney = 0;
        int actualMoney = 0;
        for (Goods goods : goodsList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setGoodsId(goods.getId());
            orderItem.setGoodsName(goods.getName());
            orderItem.setGoodsNum(goods.getBuyGoodsNum());
            orderItem.setGoodsIntroduce(goods.getIntroduce());
            orderItem.setGoodsUnit(goods.getUnit());
            orderItem.setGoodsPrice(goods.getPrice());
            orderItem.setGoodsDiscount(goods.getDiscount());
            order.orderItems.add(orderItem);


            //当前一个goods的金额
            int currentMoney = goods.getBuyGoodsNum() * goods.getPrice();
            //当前总金额
            totalMoney += currentMoney;
            //当前实际需要支付的money
            actualMoney += totalMoney * goods.getDiscount() / 100;
        }
        order.setActual_amount(actualMoney);
        order.setTotal_money(totalMoney);
        order.setOrder_status(OrderStatus.PLAYING);

        System.out.println(order);
        System.out.println("请输入是否支付以上订单：确认输入：“zf”");
        String confirm = scanner.next();
        if ("zf".equalsIgnoreCase(confirm)) {
            order.setFinish_time(LocalDateTime.now());
            order.setOrder_status(OrderStatus.OK);

            boolean effect = this.orderService.commitOrder(order);
            if (effect) {//插入订单和订单项成功
                for (Goods goods : goodsList) {
                    boolean isUpdate = this.goodsService.updateAfterPay(goods, goods.getBuyGoodsNum());
                    if (isUpdate) {
                        System.out.println("库存更新成功");
                    } else {
                        System.out.println("库存更新失败");
                    }
                }
                System.out.println(order);
            }

        } else {
            System.out.println("订单没有支付成功，请您重新下单");
        }

    }
}