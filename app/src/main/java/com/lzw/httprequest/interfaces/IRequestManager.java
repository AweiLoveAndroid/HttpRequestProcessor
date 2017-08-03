package com.lzw.httprequest.interfaces;

/**
 * 这是通过工厂模式完成有多种可替代解决方案的业务逻辑：
 * 解耦的答题思路如下：
 * 1.抽取它们的共性方法到接口中；
 * 2.使用自己选择的实现方案去实现；
 * 3.使用“工厂”类把具体实现转换成抽象接口，并返回抽象接口对象；
 * 4.Activity/Fragment/Presenter中，使用“工厂”中返回的接口进行操作，而不要依赖于其实现类。
 * 5.要更换方案的时候，只需要修改“工厂”中返回的实现类。
 *
 *
 * IRequestManager 此接口提供的就是http请求通用的方法，该接口可以用Volley来实现，也能用OkHttp等其它方式来实现
 * 接口说明：
 * 	get方法参数包含一个url，以及返回数据的接口
 * 	post/put/delete方法还需要提供一个请求体参数
 */
public interface IRequestManager {
    abstract void get(String url, IRequestCallback requestCallback);
    abstract void post(String url, String requestBodyJson, IRequestCallback requestCallback);
    abstract void put(String url, String requestBodyJson, IRequestCallback requestCallback);
    abstract void delete(String url, String requestBodyJson, IRequestCallback requestCallback);
}
