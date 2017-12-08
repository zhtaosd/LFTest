package com.longfor.core.net.download;

import android.os.AsyncTask;

import com.longfor.core.net.RestCreator;
import com.longfor.core.net.callback.IError;
import com.longfor.core.net.callback.IFailure;
import com.longfor.core.net.callback.IRequest;
import com.longfor.core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhanghaitao1 on 2017/12/8.
 */

public class DownloadHandler {
    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String url,
                           IRequest request,
                           ISuccess success,
                           IFailure failure,
                           IError error,
                           String download_dir,
                           String extension,
                           String name) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public  final void handleDownload(){
        if(REQUEST!=null){
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL,PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    final ResponseBody body = response.body();
                    final SaveFileTask task = new SaveFileTask(REQUEST,SUCCESS);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,body,NAME);
                    if(task.isCancelled()){
                        if(REQUEST!=null){
                            REQUEST.onRequestEnd();
                        }
                    }
                }else{
                    if(ERROR!=null){
                        ERROR.onError(response.code(),response.message());
                    }
                }
                RestCreator.getParams().clear();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(FAILURE!=null){
                    FAILURE.onFailure();
                    RestCreator.getParams().clear();
                }
            }
        });
    }
}
