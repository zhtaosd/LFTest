package com.longfor.core.net;

import android.content.Context;

import com.longfor.core.net.callback.HttpMethod;
import com.longfor.core.net.callback.IError;
import com.longfor.core.net.callback.IFailure;
import com.longfor.core.net.callback.IRequest;
import com.longfor.core.net.callback.ISuccess;
import com.longfor.core.net.callback.RequestCallbacks;
import com.longfor.core.ui.loader.LoaderStyle;
import com.longfor.core.ui.loader.LongforLoader;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by zhanghaitao1 on 2017/12/5.
 */

public class RestClient {
    private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
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
    private final LoaderStyle LOADERSTYLE;


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
               Context context,
               LoaderStyle loaderstyle) {
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
        this.LOADERSTYLE = loaderstyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOADERSTYLE != null) {
            LongforLoader.showLoading(CONTEXT, LOADERSTYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR, LOADERSTYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
