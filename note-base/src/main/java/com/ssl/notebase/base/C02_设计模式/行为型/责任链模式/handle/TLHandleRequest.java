package com.ssl.notebase.base.C02_设计模式.行为型.责任链模式.handle;


import com.ssl.notebase.base.C02_设计模式.行为型.责任链模式.LeaveRequest;
import com.ssl.notebase.base.C02_设计模式.行为型.责任链模式.Request;

/**
 * @author SongShengLin
 * @date 2022/7/26 00:28
 * @description
 */
public class TLHandleRequest implements HandleRequest {

    private HandleRequest handleRequest;

    public TLHandleRequest(HandleRequest handleRequest) {
        this.handleRequest = handleRequest;
    }


    @Override
    public void handleRequest(Request request) {
        if (request instanceof LeaveRequest) {
            System.out.println("要请假,项目组长审批！");
        } else {
            handleRequest.handleRequest(request);
        }
    }
}
