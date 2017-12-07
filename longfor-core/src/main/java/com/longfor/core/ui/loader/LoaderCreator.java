package com.longfor.core.ui.loader;

import android.content.Context;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;
import java.util.WeakHashMap;

/**
 * Created by zhanghaitao1 on 2017/12/7.
 */

public class LoaderCreator {
    private static final WeakHashMap<String,Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView creat(String type, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if(LOADING_MAP.get(type)==null){
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if(name == null||name.isEmpty()){
            return null;
        }
        final StringBuilder drawbleClassName = new StringBuilder();
        if(!name.contains(".")){
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawbleClassName.append(defaultPackageName).append(".indicators").append(".");
        }
        drawbleClassName.append(name);
        final Class<?> drawableClass;
        try {
            drawableClass = Class.forName(drawbleClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
