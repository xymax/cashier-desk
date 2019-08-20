package com.cashsystem.dao;

import com.cashsystem.entity.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao extends BaseDao {
    public List<Goods> quarryAllGoods() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Goods> list = new ArrayList<>();
        try {
            connection = this.getConnection(true);
            String sql = "select * from goods";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Goods goods = extractGoods(resultSet);
                if (goods != null) {
                    list.add(goods);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Goods extractGoods(ResultSet resultSet) throws SQLException {
        Goods goods = new Goods();
        goods.setId(resultSet.getInt("id"));
        goods.setName(resultSet.getString("name"));
        goods.setIntroduce(resultSet.getString("introduce"));
        goods.setStock(resultSet.getInt("stock"));
        goods.setUnit(resultSet.getString("unit"));
        goods.setPrice(resultSet.getInt("price"));
        goods.setDiscount(resultSet.getInt("discount"));
        return goods;
    }

    public boolean putAwayGoods(Goods goods) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean effect = false;
        try {
            connection = this.getConnection(true);
            String sql = "insert into goods(name,introduce,stock,unit,price,discount) values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, goods.getName());
            preparedStatement.setString(2, goods.getIntroduce());
            preparedStatement.setInt(3, goods.getStock());
            preparedStatement.setString(4, goods.getUnit());
            preparedStatement.setInt(5, goods.getPrice());
            preparedStatement.setInt(6, goods.getDiscount());
            effect = (preparedStatement.executeUpdate() == 1);
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                goods.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, preparedStatement, connection);
        }
        return effect;
    }

    public Goods getGoods(int goodsid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Goods goods = null;
        try {
            connection = this.getConnection(true);
            String sql = "select * from goods where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodsid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                goods = this.extractGoods(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public boolean modifyGoods(Goods goods) {
        //名称，简介，价格，折扣，库存
        Connection connection = null;
        PreparedStatement statement = null;
//        ResultSet resultSet = null;
        try {
            connection = this.getConnection(true);
            String sql = "update goods set name=? , introduce =?,  stock=? , price=?, discount =? where  id= ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, goods.getName());
            statement.setString(2, goods.getIntroduce());
            statement.setInt(3, goods.getStock());
            statement.setInt(4, goods.getPrice());
            statement.setInt(5, goods.getDiscount());
            statement.setInt(6, goods.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(null,statement, connection);
        }
        return false;
    }

    //下架商品
    public boolean deleteGoods(int goodsId) {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;

        try {
            connection=this.getConnection(true);
            String sql = "delete  from goods where  id=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,goodsId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource(resultSet, statement, connection);
        }
        return false;

    }

    public boolean updateAfter(Goods goods, int buyGoodsNum) {
        Connection connection=null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        boolean effect=false;

        try {
            connection=this.getConnection(true);
            String sql="update goods set stock=? where id=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,goods.getStock()-buyGoodsNum);
            preparedStatement.setInt(2,goods.getId());

            if (preparedStatement.executeUpdate()==1){
                effect=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return effect;
    }
}