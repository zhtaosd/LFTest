package com.longfor.core.net;

import com.longfor.core.app.ConfigKeys;
import com.longfor.core.app.LongFor;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zhanghaitao1 on 2017/12/5.
 */

public final class RestCreator {
    /**
     * 参数容器
     */
    private static final class ParamsHolder{
        private static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }
    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    /**
     * okhttp容器
     */
    private static final class OkHttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = LongFor.getConfiguration(ConfigKeys.INTERCEPTOR);
        private static OkHttpClient.Builder addInterceptor(){
            if(INTERCEPTORS!=null&&!INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor().
                connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    /**
     * 构建全局retrofit客户端
     */
    private static final class RetrofitHolder{
        private static final String BASE_URL = LongFor.getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder().
                baseUrl("https://www.baidu.com/").
                client(OkHttpHolder.OK_HTTP_CLIENT).
                addConverterFactory(ScalarsConverterFactory.create()).
                build();
    }
    /**
     * service接口
     */
    private static final class RestServiceHolder{
        private static final RestService RESTSERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService(){
        String a = "str";
        OkHttpClient okHttpClient = OkHttpHolder.OK_HTTP_CLIENT;
        Retrofit retrofit = RetrofitHolder.RETROFIT_CLIENT;
        RestService service = RestServiceHolder.RESTSERVICE;
        return RestServiceHolder.RESTSERVICE;
    }
}
