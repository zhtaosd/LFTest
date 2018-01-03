package com.longfor.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by zhanghaitao1 on 2018/1/3.
 */

public class BaseDecoration extends DividerItemDecoration{
    public BaseDecoration(@ColorInt int color,int size) {
        setDividerLookup(new DividerLookupImpl(color,size));
    }

    public static BaseDecoration creat(@ColorInt int color,int size){
        return new BaseDecoration(color,size);
    }
}
