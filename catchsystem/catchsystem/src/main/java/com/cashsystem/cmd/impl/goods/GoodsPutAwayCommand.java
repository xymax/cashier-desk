package com.cashsystem.cmd.impl.goods;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.impl.AbstracCommand;
import com.cashsystem.entity.Goods;

@CommandMeta(
        name = "SJSP",
        desc = "上架商品",
        group = "商品信息"
)
@AdminCommand
public class GoodsPutAwayCommand extends AbstracCommand { //货物上架命令

    @Override
    public void execute(Subject subject) {
        System.out.println("*********上架商品**********");
        System.out.println("请输入商品名称");
        String name=scanner.nextLine();
        System.out.println("请输入商品简介：");
        String introduce=scanner.nextLine();
        System.out.println("请输入商品库存：");
        int stock= Integer.parseInt(scanner.nextLine());
        System.out.println("请输入商品单位：个，打，包，盒，袋，瓶，本，台，千克，箱……）：");
        String unit=scanner.nextLine();
        System.out.println("请输入商品价格：单位（元）");
        int price=new Double(100*scanner.nextInt()).intValue();
        System.out.println("请输入商品折扣：");
        int discount=scanner.nextInt();

        Goods goods=new Goods();
        goods.setUnit(unit);
        goods.setPrice(price);
        goods.setName(name);
        goods.setStock(stock);
        goods.setIntroduce(introduce);
        goods.setDiscount(discount);
        boolean effect= goodsService.putAwayGoods(goods);
        if (effect){
            System.out.println("添加商品"+goods.getName()+"成功");
        }else {
            System.out.println("添加失败");
        }


    }
}

