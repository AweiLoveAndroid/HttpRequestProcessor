package com.lzw.httprequest.manager;

import com.lzw.httprequest.interfaces.IRequestManager;

/**
 * 该类的作用是用于返回一个IRequestManager对象，这个IRequestManager的实现类
 * 可以是使用Volley实现的http请求对象，也可以是OkHttp实现的http请求对象
 * Activity/Fragment/Presenter中，只要调用getRequestManager()方法就能得到
 * http请求的操作接口，而不用关心具体是使用什么实现的。
 */
public class RequestFactory {
    public static IRequestManager getRequestManager() {
        return VolleyRequestManager.getInstance();
        //return OkHttpRequestManager.getInstance();
    }
}