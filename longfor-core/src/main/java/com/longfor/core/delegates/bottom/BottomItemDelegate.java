package com.longfor.core.delegates.bottom;

import android.widget.Toast;

import com.longfor.core.delegates.LongForDelegate;

/**
 * Created by zhanghaitao1 on 2017/12/22.
 */

public abstract class BottomItemDelegate extends LongForDelegate {
    //连续点击两次退出程序时间设置
    private static final long WATE_TIME = 2000L;
    private long touch_time = 0;

    @Override
    public boolean onBackPressedSupport() {
        if(System.currentTimeMillis() - touch_time < WATE_TIME){
            _mActivity.finish();
        }else{
            touch_time = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
