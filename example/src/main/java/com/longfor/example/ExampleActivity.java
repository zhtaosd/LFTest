package com.longfor.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.longfor.core.activities.ProxyActivity;
import com.longfor.core.delegates.LongForDelegate;
import com.longfor.ec.launcher.LauncherDelegate;
import com.longfor.ec.sign.ISignListener;
import com.longfor.ec.sign.SignInDelegate;
import com.longfor.ui.launcher.ILauncherListener;
import com.longfor.ui.launcher.OnLauncherFinishTag;

/**
 * Created by zhanghaitao1 on 2017/12/5.
 */

public class ExampleActivity extends ProxyActivity implements ISignListener,ILauncherListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       ActionBar actionBar = getSupportActionBar();
       actionBar.hide();
    }

    @Override
    public LongForDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:

                break;
            case NOT_SIGNED:
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
        }
    }
}
