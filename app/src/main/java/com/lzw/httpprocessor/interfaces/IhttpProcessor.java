package com.lzw.httpprocessor.interfaces;

import java.util.Map;

public interface IhttpProcessor {
    //GET请求
    void get(String url, Map<String,Object> params,ICallBack callback);
    //POST请求
    void post(String url, Map<String,Object> params,ICallBack callback);
}
