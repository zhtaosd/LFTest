package com.longfor.core.delegates.web.router;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.longfor.core.delegates.LongForDelegate;
import com.longfor.core.delegates.web.WebDelegate;
import com.longfor.core.delegates.web.WebDelegateImpl;

/**
 * Created by zhanghaitao1 on 2018/1/15.
 */

public class Router {
    private Router() {
    }
    private static class Holder{
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance(){
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate delegate,String url){
        if(url.contains("tel:")){
            callPhone(delegate.getContext(),url);
            return true;
        }
        final LongForDelegate topDelegate = delegate.getTopDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.creat(url);
        topDelegate.getSupportDelegate().start(webDelegate);
        return true;
    }

    private void loadWebPage(WebView webView,String url){
        if(webView!=null){
            webView.loadUrl(url);
        }else{
            throw new NullPointerException("WebView is null!");
        }
    }

    private void loadLocalpage(WebView webView,String url){
        loadWebPage(webView,"file:///android_asset/"+url);
    }

    private void loadPage(WebView webView,String url){
        if(URLUtil.isNetworkUrl(url)||URLUtil.isAssetUrl(url)){
            loadWebPage(webView,url);
        }else{
            loadLocalpage(webView,url);
        }
    }

    public final void loadPage(WebDelegate delegate,String url){
        loadPage(delegate.getWebView(),url);
    }

    private void callPhone(Context context,String uri){
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context,intent,null);
    }
}
