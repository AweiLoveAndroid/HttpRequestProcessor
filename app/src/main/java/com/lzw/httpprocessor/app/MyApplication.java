package com.lzw.httpprocessor.app;

import android.app.Application;

import com.lzw.httpprocessor.http.HttpHelper;
import com.lzw.httpprocessor.processor.VolleyProcessor;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //这里只需要一行代码切换网络框架，6不6！！！

        //初始化Volley方式网络请求代理
        HttpHelper.init(new VolleyProcessor(this));

        //初始化Okhttp方式网络请求代理
        //HttpHelper.init(new OkHttpProcessor());
    }


}
