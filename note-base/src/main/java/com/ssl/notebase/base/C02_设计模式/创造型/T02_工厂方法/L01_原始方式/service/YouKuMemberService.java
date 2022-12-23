package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L01_原始方式.service;

/**
 * 优酷会员
 * @author spikeCong
 * @date 2022/9/8
 **/
public class YouKuMemberService {

    public void openMember(String bindMobile, String awardNumber){

        System.out.println("发放优酷会员: " + bindMobile + " , " + awardNumber);
    }

}
