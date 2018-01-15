package com.longfor.core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.longfor.core.app.ConfigKeys;
import com.longfor.core.app.LongFor;
import com.longfor.core.delegates.IPageLoadListener;
import com.longfor.core.delegates.web.WebDelegate;
import com.longfor.core.delegates.web.router.Router;
import com.longfor.core.ui.loader.LongforLoader;

import retrofit2.http.DELETE;

/**
 * Created by zhanghaitao1 on 2018/1/15.
 */

public class WebViewClientImpl extends WebViewClient{
    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static  final Handler HANDLER = LongFor.getHandler();

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public void setIPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return Router.getInstance().handleWebUrl(DELEGATE,url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if(mIPageLoadListener!=null){
            mIPageLoadListener.onLoadStart();
        }
        LongforLoader.showLoading(view.getContext());
    }

    //获取浏览器cookie
    private void syncCookie(){

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if(mIPageLoadListener!=null){
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LongforLoader.stopLoading();
            }
        },1000);
    }
}
