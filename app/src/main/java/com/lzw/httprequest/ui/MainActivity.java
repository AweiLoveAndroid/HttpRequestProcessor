package com.lzw.httprequest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lzw.httpprocessor.R;
import com.lzw.httprequest.interfaces.IRequestCallback;
import com.lzw.httprequest.interfaces.IRequestManager;
import com.lzw.httprequest.manager.RequestFactory;

/**
 * 作者：Created by lzw
 * 时间：Created on 2017/6/17 0017 22:48
 * 邮箱：lzw20099002@126.com
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //测试请求
        String url = "https://api.douban.com/v2/movie/top250";
        //这里发起请求依赖的是IRequestManager接口
        IRequestManager requestManager = RequestFactory.getRequestManager();
        requestManager.get(url, new IRequestCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d(TAG, "onSuccess: " + response);

            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();

            }
        });

    }
}