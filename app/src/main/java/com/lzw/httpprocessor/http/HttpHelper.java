package com.lzw.httpprocessor.http;

import com.lzw.httpprocessor.interfaces.ICallBack;
import com.lzw.httpprocessor.interfaces.IhttpProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * 代理类
 */

public class HttpHelper implements IhttpProcessor {

    private static IhttpProcessor mIhttpProcessor;
    private static HttpHelper _instance;
	private Map<String,Object> mParams;
    
	private HttpHelper(){
		mParams = new HashMap<>();
    }

   

    public static HttpHelper obtain(){
		synchronized (HttpHelper.class){
			if(_instance == null){
				_instance = new HttpHelper();
			}
		}
        return _instance;
    }

	public static void init(IhttpProcessor httpProcessor){
        mIhttpProcessor = httpProcessor;

    }

    @Override
    public void get(String url, Map<String, Object> params, ICallBack callback) {
        final String finalUrl = appendParams(url,params);
		mIhttpProcessor.get(finalUrl,params,callback);
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callback) {
        final String finalUrl = appendParams(url,params);
		mIhttpProcessor.post(finalUrl,params,callback);
    }

	//拼接url
	private String appendParams(String url, Map<String, Object> params){

	}
}
