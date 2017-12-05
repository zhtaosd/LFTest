package com.longfor.example;

import com.longfor.core.activities.ProxyActivity;
import com.longfor.core.delegates.LongForDelegate;
/**
 * Created by zhanghaitao1 on 2017/12/5.
 */

public class ExampleActivity extends ProxyActivity {
    @Override
    public LongForDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
