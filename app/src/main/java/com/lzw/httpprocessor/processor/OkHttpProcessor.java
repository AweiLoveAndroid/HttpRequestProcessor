package com.lzw.httpprocessor.processor;


import android.os.Handler;

import com.lzw.httpprocessor.interfaces.ICallBack;
import com.lzw.httpprocessor.interfaces.IhttpProcessor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkHttpProcessor implements IhttpProcessor {

    public static final String TAG ="OkHttpProcessor";

    private static OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public OkHttpProcessor(){
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler();
    }


    @Override
    public void get(String url, Map<String, Object> params, final ICallBack callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailed(e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                /*if(response.isSuccessful()){
                    final String result = response.body().toString();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });

                }else{
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailed(response.message().toString());
                        }
                    });
                }*/
                boolean isSuccessful = response.isSuccessful();
                postParams(callback,isSuccessful,response);
            }
        });
    }


    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callback) {

        RequestBody requestBody = appendBody(params);

        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailed(e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
               /* if(response.isSuccessful()){
                    final String result = response.body().toString();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });

                }else{
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailed(response.message().toString());
                        }
                    });
                }*/
                boolean isSuccessful = response.isSuccessful();
                postParams(callback,isSuccessful,response);
            }
        });
    }

    //传入参数，返回添加头信息
    private RequestBody appendBody( Map<String, Object> params){
        FormBody.Builder body = new FormBody.Builder();
        if(params == null || params.isEmpty()){
            return body.build();
        }
        for(Map.Entry<String, Object> entry : params.entrySet()){
                body.add(entry.getKey(),entry.getValue().toString());
        }
        return body.build();
    }


    private void postParams(final ICallBack callback, final boolean isSuccess, final Response response){
        final String[] result = {""};
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(isSuccess == true){
                    result[0] = response.body().toString();
                    callback.onSuccess(result[0]);
                }else{
                    result[0] = response.message().toString();
                    callback.onFailed(result[0]);
                }
            }
        });
    }
}
