package com.lzw.httpprocessor.interfaces;

import java.util.Map;

/**
 * 作者：Created by lzw
 * 时间：Created on 2017/6/11 0011 08:44
 * 邮箱：lzw20099002@126.com
 */

public interface IhttpProcessor {
    //GET请求
    void get(String url, Map<String,Object> params,ICallBack callback);
    //POST请求
    void post(String url, Map<String,Object> params,ICallBack callback);
}
