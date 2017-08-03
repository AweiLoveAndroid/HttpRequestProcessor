# HttpRequestProcessor
> ## **网络请求隔离框架，有两种实现方式：一种是代理模式实现的，一种是工厂模式实现的。**

## 使用方式如下：

**先来说说代理模式的使用方法：**


> 一、**首先在application里面声明使用哪个框架**
    
    
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

> 二、**在代码里面具体使用**

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

-----------------------------------
是不是很简单？
