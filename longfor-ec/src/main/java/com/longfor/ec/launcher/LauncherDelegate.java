package com.longfor.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.longfor.core.app.AccountManager;
import com.longfor.core.app.IUserChecker;
import com.longfor.core.delegates.LongForDelegate;
import com.longfor.core.utils.storage.LongForPreference;
import com.longfor.core.utils.timer.BaseTimerTask;
import com.longfor.core.utils.timer.ITimeListener;
import com.longfor.ec.R;
import com.longfor.ec.R2;
import com.longfor.ui.launcher.ILauncherListener;
import com.longfor.ui.launcher.OnLauncherFinishTag;
import com.longfor.ui.launcher.ScrollLauncherTag;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhanghaitao1 on 2017/12/11.
 */

public class LauncherDelegate extends LongForDelegate implements ITimeListener {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
         mILauncherListener = (ILauncherListener) activity;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    //判断是否显示滑动页
    private void checkIsShowScroll(){
        if(!LongForPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            getSupportDelegate().start(new LauncherScrollDelegate(),SINGLETASK);
        }else {
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                }

                @Override
                public void onNotSignIn() {
                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                }
            });
        }
    }
}
