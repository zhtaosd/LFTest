package com.longfor.example;

import com.longfor.core.activities.ProxyActivity;
import com.longfor.core.delegates.LongForDelegate;
import com.longfor.ec.launcher.LauncherDelegate;
import com.longfor.ec.launcher.LauncherScrollDelegate;

/**
 * Created by zhanghaitao1 on 2017/12/5.
 */

public class ExampleActivity extends ProxyActivity {
    @Override
    public LongForDelegate setRootDelegate() {
        return new LauncherScrollDelegate();
    }
}
