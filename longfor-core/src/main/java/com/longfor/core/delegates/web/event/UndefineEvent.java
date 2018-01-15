package com.longfor.core.delegates.web.event;


import android.util.Log;


/**
 * Created by 傅令杰
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        Log.e("UndefineEvent", params);
        return null;
    }
}
