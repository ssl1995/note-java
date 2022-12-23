package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L03_工厂模式.factory;


import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service.IFreeGoods;

/**
 * 抽象工厂
 * @author spikeCong
 * @date 2022/9/9
 **/
public interface FreeGoodsFactory {

    IFreeGoods getInstance();
}
