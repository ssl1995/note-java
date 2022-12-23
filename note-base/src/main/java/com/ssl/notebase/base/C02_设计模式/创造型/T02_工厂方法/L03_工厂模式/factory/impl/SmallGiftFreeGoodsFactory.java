package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L03_工厂模式.factory.impl;


import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L03_工厂模式.factory.FreeGoodsFactory;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service.IFreeGoods;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service.impl.SmallGiftFreeGoods;

/**
 * 生产小礼品发放接口-具体工厂
 *
 * @author spikeCong
 * @date 2022/9/9
 **/
public class SmallGiftFreeGoodsFactory implements FreeGoodsFactory {

    @Override
    public IFreeGoods getInstance() {
        return new SmallGiftFreeGoods();
    }
}
