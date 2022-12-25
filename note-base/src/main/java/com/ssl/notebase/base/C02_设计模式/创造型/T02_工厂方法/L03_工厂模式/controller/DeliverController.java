package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L03_工厂模式.controller;


import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L03_工厂模式.factory.FreeGoodsFactory;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L03_工厂模式.factory.FreeGoodsFactoryMap;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.entity.AwardInfo;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.entity.ResponseResult;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service.IFreeGoods;

/**
 * 工厂模式：抽象工厂 + 具体工厂 + 抽象产品 + 具体产品
 * 优点：
 * 1.用户只需要知道具体工厂的名称就可得到所要的产品，无须知道产品的具体创建过程
 * 2.在系统增加新的产品时只需要添加具体产品类和对应的具体工厂类，无须对原工厂进行任何修改，满足开闭原则；
 * 缺点：
 * 1.每增加一个产品就要增加一个具体产品类和一个对应的具体工厂类，这增加了系统的复杂度
 **/
public class DeliverController {

    //发放奖品
    public ResponseResult awardToUser(AwardInfo awardInfo) {

        //根据类型获取具体工厂
        FreeGoodsFactory goodsFactory = FreeGoodsFactoryMap.getParserFactory(awardInfo.getAwardTypes());

        //从工厂类中获取对应实例
        IFreeGoods iFreeGoods = goodsFactory.getInstance();

        System.out.println("==========工厂方法模式=============");
        ResponseResult responseResult = iFreeGoods.sendFreeGoods(awardInfo);
        return responseResult;
    }
}
