package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.factory;


import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service.IFreeGoods;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service.impl.DiscountFreeGoods;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service.impl.SmallGiftFreeGoods;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service.impl.YouKuMemberFreeGoods;

/**
 * 具体工厂: 生成免费商品
 * @author spikeCong
 * @date 2022/9/9
 **/
public class FreeGoodsFactory {

    public static IFreeGoods getInstance(Integer awardType){

        IFreeGoods iFreeGoods = null;

        if(awardType == 1){  //打折券

            iFreeGoods = new DiscountFreeGoods();
        }else if(awardType == 2){ //优酷会员

            iFreeGoods = new YouKuMemberFreeGoods();
        }else if(awardType == 3){ //小礼品

            iFreeGoods = new SmallGiftFreeGoods();
        }

        return iFreeGoods;
    }
}
