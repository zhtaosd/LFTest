package com.longfor.core.app;

import com.longfor.core.utils.storage.LongForPreference;

/**
 * Created by zhanghaitao1 on 2017/12/22.
 */

public class AccountManager {
    private enum SingTag{
        SING_TAG
    }
    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state){
        LongForPreference.setAppFlag(SingTag.SING_TAG.name(),state);
    }

    private static boolean isSignIn(){
        return LongForPreference.getAppFlag(SingTag.SING_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if(isSignIn()){
            checker.onSignIn();
        }else{
            checker.onNotSignIn();
        }
    }
}
