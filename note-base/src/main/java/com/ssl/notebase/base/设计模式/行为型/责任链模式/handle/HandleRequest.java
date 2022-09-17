package com.ssl.notebase.base.设计模式.行为型.责任链模式.handle;


import com.ssl.notebase.base.设计模式.行为型.责任链模式.Request;

/**
 * @author SongShengLin
 * @date 2022/7/26 00:11
 * @description
 */
public interface HandleRequest {

    void handleRequest(Request request);
}
