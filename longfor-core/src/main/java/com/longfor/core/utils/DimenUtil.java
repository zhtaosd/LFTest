package com.longfor.core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.longfor.core.app.LongFor;

/**
 * Created by zhanghaitao1 on 2017/12/7.
 */

public class DimenUtil {
    public static  int getScreenWidth(){
        final Resources resources = LongFor.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static  int getScreenHeight(){
        final Resources resources = LongFor.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
