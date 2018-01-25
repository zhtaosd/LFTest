package com.longfor.core.utils.callback;

import android.support.annotation.Nullable;

/**
 * Created by zhanghaitao1 on 2018/1/25.
 */

public interface IGlobalCallback<T> {
    void excuteCallback(@Nullable T args);
}
