package com.longfor.core.ui.camera;

import android.net.Uri;

/**
 * Created by zhanghaitao1 on 2018/1/24.
 */

public class CameraImageBean {
    private Uri mPath = null;
    private static final CameraImageBean INSTANCE = new CameraImageBean();
    public static CameraImageBean getInstance(){
        return INSTANCE;
    }
    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
