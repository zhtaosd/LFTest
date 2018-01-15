package com.longfor.core.delegates.web;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.longfor.core.delegates.web.event.Event;
import com.longfor.core.delegates.web.event.EventManager;

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
        final Event event = EventManager.getInstance().creatEvent(action);
        Log.e("WEB_EVENT",params);
        if(event!=null){
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContent(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
        }
        return null;
    }
}
