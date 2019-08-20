package com.cashsystem.dao;

import com.cashsystem.common.OrderStatus;
import com.cashsystem.entity.Order;
import com.cashsystem.entity.OrderItem;

import javax.management.relation.RelationNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends BaseDao {



    public boolean commitOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        // boolean effect = false;
        try {
            connection = this.getConnection(false);
            String insertOrderSql = "insert into `order`" +
                    "(id, account_id, create_time, finish_time, " +
                    "actual_amount, total_money, order_status, " +
                    "account_name) values (?,?,now(),now(),?,?,?,?)";
            String insertOrderItemSql = "insert into order_item(order_id, goods_id, goods_name," +
                    "goods_introduce, goods_num, goods_unit, goods_price, goods_discount) values (?,?,?,?,?,?,?,?)";

            //进行预编译
            preparedStatement = connection.prepareStatement(insertOrderSql);
            preparedStatement.setString(1, order.getId());
            preparedStatement.setInt(2, order.getAccount_id());
            preparedStatement.setInt(3, order.getActual_amount());
            preparedStatement.setInt(4, order.getTotal_money());
            preparedStatement.setInt(5, order.getOrder_status().getFlag());
            preparedStatement.setString(6, order.getAccount_name());


            if (preparedStatement.executeUpdate() == 0) {
                throw new RuntimeException("插入订单失败");

            }
            preparedStatement = connection.prepareStatement(insertOrderItemSql);
            for (OrderItem orderItem : order.orderItems) {
                preparedStatement.setString(1, orderItem.getOrderId());
                preparedStatement.setInt(2, orderItem.getGoodsId());
                preparedStatement.setString(3, orderItem.getGoodsName());
                preparedStatement.setString(4, orderItem.getGoodsIntroduce());
                preparedStatement.setInt(5, orderItem.getGoodsNum());
                preparedStatement.setString(6, orderItem.getGoodsUnit());
                preparedStatement.setInt(7, orderItem.getGoodsPrice());
                preparedStatement.setInt(8, orderItem.getGoodsDiscount());

                //将每一句pstatment缓存好
                preparedStatement.addBatch();
            }

            //批量操作数据库
            int[] effects = preparedStatement.executeBatch();

            for (int i : effects) {
                if (i == 0) {
                    throw new RelationNotFoundException("插入订单明细失败");
                }
            }

            connection.commit();
        } catch (SQLException | RelationNotFoundException e) {
            e.printStackTrace();
        }
        if (connection != null) {
            try {
                //回滚
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.closeResource(null, preparedStatement, connection);
            }
        }
        return true;
    }

    //查询订单
    public List<Order> queryOrderByAccount(Integer accountId) {
        List<Order> orderList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.getConnection(false);
            String sql = this.getSql("@query_order_by_account");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);

            resultSet = preparedStatement.executeQuery();

            Order order = null;


            while (resultSet.next()) {
                if (order == null) {
                    order = new Order();
                    this.extractOrder(order, resultSet);
                    orderList.add(order);
                }
                //拿到了当前的Order的id。
                String orderId = resultSet.getString("order_id");
                //只有当订单信息不同的时候我们才会生成一个订单，订单信息只有一个，因为其中包含了很多的4
                //订单信息，如果每个订单信息都生成一个订单是不合理的。
                if (!orderId.equals(order.getId())) {
                    order = new Order();
                    this.extractOrder(order, resultSet);
                    orderList.add(order);
                }
                //往当前订单内添加具体的订单项
                OrderItem orderItem = this.extractOrderItem(resultSet);
                order.getOrderItems().add(orderItem);


            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return orderList;

    }

    private OrderItem extractOrderItem(ResultSet resultSet) throws SQLException {

        OrderItem orderItem = new OrderItem();
        orderItem.setId(resultSet.getInt("item_id"));
        orderItem.setGoodsId(resultSet.getInt("goods_id"));
        orderItem.setGoodsName(resultSet.getString("goods_name"));
        orderItem.setGoodsIntroduce(resultSet.getString("goods_introduce"));
        orderItem.setGoodsNum(resultSet.getInt("goods_num"));
        orderItem.setGoodsUnit(resultSet.getString("goods_unit"));
        orderItem.setGoodsPrice(resultSet.getInt("goods_price"));
        orderItem.setGoodsDiscount(resultSet.getInt("goods_discount"));
        return orderItem;



    }

    public void extractOrder(Order order,ResultSet resultSet) throws SQLException {
        order.setId(resultSet.getString("order_id"));
        order.setAccount_id(resultSet.getInt("account_id"));
        order.setAccount_name(resultSet.getString("account_name"));
        order.setCreate_time(resultSet.getTimestamp("create_time").toLocalDateTime());
        Timestamp finishTime = resultSet.getTimestamp("finish_time");
        if (finishTime != null) {
            order.setFinish_time(finishTime.toLocalDateTime());
        }
        order.setActual_amount(resultSet.getInt("actual_amount"));
        order.setTotal_money(resultSet.getInt("total_money"));
        order.setOrder_status(OrderStatus.valueOf(resultSet.getInt("order_status")));
    }
    

}

