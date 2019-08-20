package com.cashsystem.entity;

import lombok.Data;

@Data
public class OrderItem { //订单项
    private Integer id;
    private String orderId;
    private Integer goodsId;
    private String goodsName;
    private String goodsIntroduce;
    private Integer goodsNum;
    private String goodsUnit;
    private Integer goodsPrice;
    private Integer goodsDiscount;
}
