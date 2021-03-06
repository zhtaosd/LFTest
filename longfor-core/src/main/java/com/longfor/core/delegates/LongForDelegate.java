package com.longfor.core.delegates;

/**
 * Created by zhanghaitao1 on 2017/12/5.
 */

public abstract class LongForDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LongForDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
