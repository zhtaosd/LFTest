package com.longfor.core.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.longfor.core.delegates.web.WebDelegate;

/**
 * Created by zhanghaitao1 on 2018/1/15.
 */

public abstract class Event implements IEvent {
    private Context mContent = null;
    private String mAction = null;
    private WebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView mWebView = null;

    public Context getContent() {
        return mContent;
    }

    public void setContent(Context mContent) {
        this.mContent = mContent;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public WebDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public WebView getWebView() {
        return mDelegate.getWebView();
    }

    public void setWebView(WebView mWebView) {
        this.mWebView = mWebView;
    }
}
