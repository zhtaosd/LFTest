package com.longfor.example;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.longfor.core.delegates.LongForDelegate;

/**
 * Created by zhanghaitao1 on 2017/12/5.
 */

public class ExampleDelegate extends LongForDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
