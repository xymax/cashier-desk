package com.cashsystem.cmd.impl.goods;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstracCommand;
import com.cashsystem.entity.Goods;

@CommandMeta(
        name = "GXSP",
        desc = "更新商品",
        group = "商品信息"
)
@AdminCommand
public class GoodsUpdateCommand extends AbstracCommand { //货物更新命令

    @Override
    public void execute(Subject subject) {
        System.out.println("更新商品");
        System.out.println("请输入更新商品的编号");
        int goodsId = scanner.nextInt();
        Goods goods = this.goodsService.getGoods(goodsId);
        if (goods == null) {
            System.out.println("此编号商品不存在");
            return;
        } else {
            System.out.println("商品原信息如下");
            System.out.println(goods);
            System.out.println("请输入商品新简介：");
            String introduce = scanner.next();
            System.out.println("请输入商品新库存（数量）：");
            int stock = scanner.nextInt();
            System.out.println("请输入商品新单价（单位：元）");
            double priceDouble = scanner.nextDouble();
            int price = new Double(100 * priceDouble).intValue();
            System.out.println("请输入商品新折扣（范围：[0,100] 比如：75表示75折）: ");
            int discount = scanner.nextInt();
            System.out.println("确认是否更新（y/n): ");
            String confirm = scanner.next();
            if ("y".equalsIgnoreCase(confirm)) {
                goods.setIntroduce(introduce);
                goods.setStock(stock);
                goods.setPrice(price);
                goods.setDiscount(discount);
                boolean effect = this.goodsService.modifyGoods(goods);
                if (effect) {
                    System.out.println("商品成功更新");
                } else {
                    System.out.println("商品更新失败，稍后重试");
                }
            }else {
                System.out.println("您选择了不更新商品");
            }
        }
    }
}

