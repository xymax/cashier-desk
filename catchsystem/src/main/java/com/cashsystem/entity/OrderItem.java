package com.cashsystem.entity;

import lombok.Data;

@Data
public class OrderItem { //订单项
    private Integer id;
    private String orderId;
    private Integer goodsId;
    private String goodName;
    private String goodsIntroduce;
    private Integer goodsNum;
    private String foodUnit;
    private Integer goodsPrice;
    private Integer goodDiscount;


}
