package com.longfor.core.net;

import android.content.Context;

import com.longfor.core.net.callback.IError;
import com.longfor.core.net.callback.IFailure;
import com.longfor.core.net.callback.IRequest;
import com.longfor.core.net.callback.ISuccess;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * Created by zhanghaitao1 on 2017/12/5.
 */

public class RestClient {
    private static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    private final String URL;
    private final IRequest REQUEST;
    private final String DEWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody REQUESTBODY;
    private final File FILE;
    private final Context CONTEXT;

    RestClient(String url,
                      IRequest request,
                      String dewnload_dir,
                      String extension,
                      String name,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody requestBody,
                      File file,
                      Context context) {
        this.URL = url;
        this.REQUEST = request;
        this.DEWNLOAD_DIR = dewnload_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUESTBODY = requestBody;
        this.FILE = file;
        this.CONTEXT = context;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }
}
