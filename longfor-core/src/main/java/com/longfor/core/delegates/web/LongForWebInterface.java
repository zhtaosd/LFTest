package com.longfor.core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;

/**
 * Created by zhanghaitao1 on 2018/1/15.
 */

public class LongForWebInterface {
    private final WebDelegate DELEGATE;

    private  LongForWebInterface(WebDelegate delegate) {
        DELEGATE = delegate;
    }
    static LongForWebInterface creat(WebDelegate delegate){
        return new LongForWebInterface(delegate);
    }
    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
