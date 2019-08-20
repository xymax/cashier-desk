package com.cashsystem.cmd.impl.goods;

import com.cashsystem.cmd.Subject;
import com.cashsystem.cmd.annotation.AdminCommand;
import com.cashsystem.cmd.annotation.CommandMeta;
import com.cashsystem.cmd.annotation.CustomerCommand;
import com.cashsystem.cmd.annotation.EntranceCommand;
import com.cashsystem.cmd.impl.AbstracCommand;
import com.cashsystem.entity.Goods;

import java.util.List;

@CommandMeta(
        name="LLSP",
        desc="浏览商品",
        group = "商品信息"
)
@AdminCommand
@CustomerCommand

public class GoodsBrowseCommand extends AbstracCommand { //货物浏览命令

    @Override
    public void execute(Subject subject) {
        System.out.println("*********************************浏览商品**********************************");
        //查询所有商品
        List<Goods> goodsList=this.goodsService.quarryAllGoods();
        if (goodsList.isEmpty()){
            System.out.println("对不起，暂时商品为空，请您稍后再来");
        }else {
            for(Goods goods:goodsList){
                System.out.println(goods);
            }
        }
        System.out.println("**************************************************************************");

    }
}

