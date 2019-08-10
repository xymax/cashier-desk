package com.cashsystem.common;

public enum OrderStatus { //订单状态
    PLAYING(1,"待支付"),OK(2,"支付完成");
    private int flag;
    private String desc;
    OrderStatus(int flag,String desc) {
        this.flag = flag;
        this.desc = desc;
    }
    public static OrderStatus valueOf(int flag) {
        for (OrderStatus orderStatus : values()) {
            if(orderStatus.flag == flag) {
                return orderStatus;
            }
        }
        throw new RuntimeException("orderStatus flag"
                + flag + "not fount!");
    }

}
