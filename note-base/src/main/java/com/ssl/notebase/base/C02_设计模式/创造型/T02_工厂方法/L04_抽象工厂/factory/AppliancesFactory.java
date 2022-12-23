package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.factory;


import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.product.AbstractFreezer;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L04_抽象工厂.product.AbstractTV;

/**
 * 抽象工厂: 在一个抽象工厂中可以声明多个工厂方法,用于创建不同类型的产品
 * @author spikeCong
 * @date 2022/9/15
 **/
public interface AppliancesFactory {

    AbstractTV createTV();

    AbstractFreezer createFreezer();

}
