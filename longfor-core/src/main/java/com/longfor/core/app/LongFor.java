package com.longfor.core.app;

import android.content.Context;
import android.os.Handler;


/**
 * Created by zhanghaitao1 on 2017/12/4.
 */

public class LongFor {
    public static Configrator init(Context context){
        Configrator.getInstance().getLongforConfigs().put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configrator.getInstance();
    }

    public static Configrator getConfigrator(){
        return Configrator.getInstance();
    }
    public static <T> T getConfiguration(Object key){
        return getConfigrator().getConfiguration(key);
    }
    public static Context getApplicationContext(){
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
    public static Handler getHandler(){
        return getConfiguration(ConfigKeys.HANDLER);
    }
}
