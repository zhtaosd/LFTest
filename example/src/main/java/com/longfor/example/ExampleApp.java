package com.longfor.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.longfor.core.app.LongFor;
import com.longfor.core.net.interceptors.DebugInterceptor;


/**
 * Created by zhanghaitao1 on 2017/12/5.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LongFor.init(this)
                .withIcon(new FontAwesomeModule())
                .withLoaderDelayed(5000)
                .withApiHost("你的本地服务器地址")
                .withWeChatAppId("你的微信AppKey")
                .withWeChatAppSecret("你的微信AppSecret")
                .withJavascriptInterface("latte")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
