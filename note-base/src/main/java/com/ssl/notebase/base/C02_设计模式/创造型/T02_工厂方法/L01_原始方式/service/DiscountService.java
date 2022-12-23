package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L01_原始方式.service;


import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L01_原始方式.entity.DiscountResult;

/**
 * 打折券服务
 * @author spikeCong
 * @date 2022/9/8
 **/
public class DiscountService {

    public DiscountResult sendDiscount(String uid, String awardNumber){

        System.out.println("向用户发送一张打折券: " + uid +  " , " + awardNumber);
        return new DiscountResult("200","发放打折券成功!");
    }

}
