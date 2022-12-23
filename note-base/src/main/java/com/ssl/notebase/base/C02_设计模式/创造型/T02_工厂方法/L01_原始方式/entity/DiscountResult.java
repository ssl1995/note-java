package com.ssl.notebase.base.C02_设计模式.创造型.T02_工厂方法.L01_原始方式.entity;

/**
 * 打折券响应信息封装实体类
 * @author spikeCong
 * @date 2022/9/8
 **/
public class DiscountResult {

    private String status; //状态码

    private String message; //信息


    public DiscountResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
