package com.lzw.httprequest.interfaces;

/**
 * Created by chenjianwei on 2016/12/11.
 * 请求返回成功/失败，成功时，把服务器返回的结果回调出去，失败时回调异常信息
 * onSuccess中的参数类型，当然也可以为JSONObject，这里只是举个栗子，可按照实际需求变通
 */
public interface IRequestCallback {
    abstract void onSuccess(String response);
    abstract void onFailure(Throwable throwable);

}
