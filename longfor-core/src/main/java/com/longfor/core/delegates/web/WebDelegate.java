package com.longfor.core.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.longfor.core.app.ConfigKeys;
import com.longfor.core.app.LongFor;
import com.longfor.core.delegates.LongForDelegate;
import com.longfor.core.delegates.web.router.RouterKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by zhanghaitao1 on 2018/1/15.
 */

public abstract class WebDelegate extends LongForDelegate implements IWebViewInitializer{
    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;
    private LongForDelegate mTopDelegate = null;

    public WebDelegate() {

    }

    public abstract  IWebViewInitializer setInitalizer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouterKeys.URL.name());
        initWebView();
    }

    protected void initWebView(){
        if(mWebView!=null){
            mWebView.removeAllViews();
            mWebView.destroy();
        }else{
            final IWebViewInitializer initializer = setInitalizer();
            if(initializer !=null){
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<WebView>(new WebView(getContext()),WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                final String name = LongFor.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE);
//                mWebView.addJavascriptInterface();
            }
        }
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView IS NULL!");
        }
        return mIsWebViewAvailable ? mWebView : null;
    }
}
