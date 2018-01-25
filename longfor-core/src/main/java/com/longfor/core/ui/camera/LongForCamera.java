package com.longfor.core.ui.camera;

import android.net.Uri;

import com.longfor.core.delegates.PermissionCheckerDelegate;
import com.longfor.core.utils.file.FileUtil;

/**
 * Created by zhanghaitao1 on 2018/1/24.
 */

public class LongForCamera {
    public static Uri createCropFile(){
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG","jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate){
        new Camerahandler(delegate).beginCameraDialog();
    }
}
