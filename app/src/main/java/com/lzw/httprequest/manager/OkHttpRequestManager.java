package com.lzw.httprequest.manager;

import android.os.Handler;

import com.lzw.httprequest.interfaces.IRequestCallback;
import com.lzw.httprequest.interfaces.IRequestManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class OkHttpRequestManager implements IRequestManager {

    public static final MediaType TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient okHttpClient;
    private Handler handler;

    public static OkHttpRequestManager getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private static class SingletonHolder {
        private static final OkHttpRequestManager INSTANCE = new OkHttpRequestManager();
    }


    public OkHttpRequestManager() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        //在哪个线程创建该对象，则最后的请求结果将在该线程回调
        handler = new Handler();
    }

    @Override
    public void get(String url, IRequestCallback requestCallback) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        addCallBack(requestCallback, request);
    }

    @Override
    public void post(String url, String requestBodyJson, IRequestCallback requestCallback) {
        RequestBody body = RequestBody.create(TYPE_JSON, requestBodyJson);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        addCallBack(requestCallback, request);
    }

    @Override
    public void put(String url, String requestBodyJson, IRequestCallback requestCallback) {
        RequestBody body = RequestBody.create(TYPE_JSON, requestBodyJson);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        addCallBack(requestCallback, request);
    }

    @Override
    public void delete(String url, String requestBodyJson, IRequestCallback requestCallback) {
        RequestBody body = RequestBody.create(TYPE_JSON, requestBodyJson);
        Request request = new Request.Builder()
                .url(url)
                .delete(body)
                .build();
        addCallBack(requestCallback, request);
    }

    private void addCallBack(final IRequestCallback requestCallback, Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                e.printStackTrace();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        requestCallback.onFailure(e);
                    }
                });

            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String json = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallback.onSuccess(json);
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallback.onFailure(new IOException(response.message() + ",url=" + call.request().url().toString()));
                        }
                    });
                }
            }
        });
    }
}