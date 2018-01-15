package com.longfor.core.delegates.web;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.longfor.core.delegates.IPageLoadListener;
import com.longfor.core.delegates.WebViewInitalizer;
import com.longfor.core.delegates.web.chromeclient.WebChromeClientImpl;
import com.longfor.core.delegates.web.client.WebViewClientImpl;
import com.longfor.core.delegates.web.router.Router;
import com.longfor.core.delegates.web.router.RouterKeys;

/**
 * Created by zhanghaitao1 on 2018/1/15.
 */

public class WebDelegateImpl extends WebDelegate{
    private IPageLoadListener mIPageLoadListener = null;
    public static WebDelegateImpl creat(String url){
        final Bundle args = new Bundle();
        args.putString(RouterKeys.URL.name(),url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }
    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitalizer().creatWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setIPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }

    @Override
    public IWebViewInitializer setInitalizer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        if(getUrl()!=null){
            Router.getInstance().loadPage(this,getUrl());
        }
    }
}
