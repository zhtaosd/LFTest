package com.longfor.core.utils.timer;

import java.util.TimerTask;

/**
 * Created by zhanghaitao1 on 2017/12/11.
 */

public class BaseTimerTask extends TimerTask{
    private ITimeListener mITimeListener = null;

    public BaseTimerTask(ITimeListener mITimeListener) {
        this.mITimeListener = mITimeListener;
    }

    @Override
    public void run() {
        if(mITimeListener==null){
            mITimeListener.onTimer();
        }
    }
}
