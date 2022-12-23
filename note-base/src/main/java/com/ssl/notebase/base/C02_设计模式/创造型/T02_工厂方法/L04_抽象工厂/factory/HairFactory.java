package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.factory;


import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.product.AbstractFreezer;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.product.AbstractTV;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.product.HairFreezer;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.product.HairTV;

/**
 * 具体工厂
 * @author spikeCong
 * @date 2022/9/15
 **/
public class HairFactory implements AppliancesFactory {

    @Override
    public AbstractTV createTV() {
        return new HairTV();
    }

    @Override
    public AbstractFreezer createFreezer() {
        return new HairFreezer();
    }
}
