package com.longfor.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.longfor.core.activities.ProxyActivity;
import com.longfor.core.delegates.LongForDelegate;
import com.longfor.ec.launcher.LauncherDelegate;

/**
 * Created by zhanghaitao1 on 2017/12/5.
 */

public class ExampleActivity extends ProxyActivity {
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
}
