package com.lzw.httpprocessor.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lzw.httpprocessor.R;
import com.lzw.httpprocessor.bean.ExpressBean;
import com.lzw.httpprocessor.http.HttpCallback;
import com.lzw.httpprocessor.http.HttpHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button button;
    //快递接口
    private String url2 = "http://www.kuaidi100.com/query?type=quanfengkuaidi&postid=300008026630";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            //访问网络
            HttpHelper.obtain().get(url2,
                null, new HttpCallback<ExpressBean>() {
                    @Override
                    public void onSuccess(ExpressBean expressBean) {
                        StringBuffer sb = new StringBuffer();
                        if(expressBean != null){
                            ArrayList<ExpressBean.DataBean> datas = expressBean.data;
                            for(ExpressBean.DataBean data : datas){
                                sb.append("时间：")
                                    .append(data.time+"\r\n")
                                    .append("地点和跟踪进度：")
                                    .append(data.context+"\r\n"+"\r\n");
                                textView.setText(sb.toString());
                            }

                        }
                    }

                    @Override
                    public void onFailed(String string) {
                        Toast.makeText(MainActivity.this,"请求失败了。。"+ string,Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }
}
