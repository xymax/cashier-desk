package com.cashsystem.entity;

import com.cashsystem.common.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Order {//订单
    private String id;
    private Integer account_name;
    private LocalDateTime create_time;
    private LocalDateTime finish_time;
    private Integer actual_amount;
    private Integer total_money;
    private OrderStatus order_status;

}
