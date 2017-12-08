package com.longfor.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.longfor.core.app.LongFor;
import com.longfor.core.net.callback.IRequest;
import com.longfor.core.net.callback.ISuccess;
import com.longfor.core.utils.file.FileUtil;

import java.io.File;
import java.io.InputStream;
import okhttp3.ResponseBody;

/**
 * Created by zhanghaitao1 on 2017/12/8.
 */

public class SaveFileTask extends AsyncTask<Object,Void,File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downLoadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = body.byteStream();
        if(downLoadDir==null||downLoadDir.equals("")){
            downLoadDir = "down_loads";
        }
        if(extension==null||extension.equals("")){
            extension = "";
        }
        if(name == null){
            return FileUtil.writeToDisk(is,downLoadDir,extension.toUpperCase(),extension);
        }else{
            return FileUtil.writeToDisk(is,downLoadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if(SUCCESS!=null){
            SUCCESS.onSuccess(file.getPath());
        }
        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if(FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            LongFor.getApplicationContext().startActivity(install);
        }
    }

}
