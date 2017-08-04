package com.lzw.httpprocessor.bean;


import java.util.ArrayList;

/**
 * 快递查询的bjavaean
 */
public class ExpressBean {

    public String message;//状态 OK
    public String nu;//快递单号 300008026630
    public String ischeck;//1
    public String condition;//F00
    public String com;//快递名称 quanfengkuaidi
    public String status;//状态码 200
    public String state;//3
    public ArrayList<DataBean> data;

    @Override
    public String toString() {
        return "ExpressBean{" +
                "message='" + message + '\'' +
                ", nu='" + nu + '\'' +
                ", ischeck='" + ischeck + '\'' +
                ", condition='" + condition + '\'' +
                ", com='" + com + '\'' +
                ", status='" + status + '\'' +
                ", state='" + state + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        public String time;//2013-05-27 13:48:37
        public String ftime;//2013-05-27 13:48:37
        public String context;//派件已【签收】,签收人是【秦胜杰】签收网点是【北京人大分部】
        public String location;//

        @Override
        public String toString() {
            return "DataBean{" +
                    "time='" + time + '\'' +
                    ", ftime='" + ftime + '\'' +
                    ", context='" + context + '\'' +
                    ", location='" + location + '\'' +
                    '}';
        }
    }
}
