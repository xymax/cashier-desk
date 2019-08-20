package com.cashsystem.service;

import com.cashsystem.dao.GoodsDao;
import com.cashsystem.entity.Goods;

import java.util.List;

public class GoodsService {
    private GoodsDao goodsDao;

    public GoodsService() {
        this.goodsDao = new GoodsDao();
    }

    //找出所有商品
    public List<Goods> quarryAllGoods() {
        return this.goodsDao.quarryAllGoods();
    }

    //上架商品
    public boolean putAwayGoods(Goods goods) {
        return this.goodsDao.putAwayGoods(goods);
    }

    //获取商品
    public Goods getGoods(int goodsid) {
        return this.goodsDao.getGoods(goodsid);
    }

    //更新商品
    public boolean modifyGoods(Goods goods) {
        return this.goodsDao.modifyGoods(goods);
    }

    //下架商品
    public boolean soldOutGoods(int goodsId) {
        return this.goodsDao.deleteGoods(goodsId);
    }

    public boolean updateAfterPay(Goods goods, Integer buyGoodsNum) {

        return this.goodsDao.updateAfter(goods,buyGoodsNum);
    }
}