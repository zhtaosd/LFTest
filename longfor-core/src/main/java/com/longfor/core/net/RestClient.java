package com.longfor.core.net;

import android.content.Context;

import com.longfor.core.net.callback.HttpMethod;
import com.longfor.core.net.callback.IError;
import com.longfor.core.net.callback.IFailure;
import com.longfor.core.net.callback.IRequest;
import com.longfor.core.net.callback.ISuccess;
import com.longfor.core.net.callback.RequestCallbacks;
import com.longfor.core.net.download.DownloadHandler;
import com.longfor.core.ui.loader.LoaderStyle;
import com.longfor.core.ui.loader.LongforLoader;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
    private final String DOWNLOAD_DIR;
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
               String download_dir,
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
        this.DOWNLOAD_DIR = download_dir;
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
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
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
        if (REQUESTBODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (REQUESTBODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, SUCCESS, FAILURE, ERROR, DOWNLOAD_DIR, EXTENSION, NAME).handleDownload();
    }
}
