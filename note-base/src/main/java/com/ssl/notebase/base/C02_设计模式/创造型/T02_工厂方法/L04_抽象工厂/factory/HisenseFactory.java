package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.factory;


import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.product.AbstractFreezer;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.product.AbstractTV;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.product.HisenseFreezer;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.product.HisenseTV;

/**
 * @author spikeCong
 * @date 2022/9/15
 **/
public class HisenseFactory implements AppliancesFactory {

    @Override
    public AbstractTV createTV() {
        return new HisenseTV();
    }

    @Override
    public AbstractFreezer createFreezer() {
        return new HisenseFreezer();
    }
}
