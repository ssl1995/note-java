package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service.impl;


import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.entity.AwardInfo;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.entity.ResponseResult;
import com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L02_简单工厂.service.IFreeGoods;

/**
 * 优酷 会员服务
 * @author spikeCong
 * @date 2022/9/8
 **/
public class YouKuMemberFreeGoods implements IFreeGoods {

    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {

        String phone = awardInfo.getExtMap().get("phone");
        System.out.println("发放优酷会员成功,绑定手机号: " + phone);
        return new ResponseResult("200","优酷会员发放成功!");
    }
}
