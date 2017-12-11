package com.longfor.example;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.longfor.core.delegates.LongForDelegate;
import com.longfor.core.net.RestClient;
import com.longfor.core.net.callback.IError;
import com.longfor.core.net.callback.IFailure;
import com.longfor.core.net.callback.ISuccess;

/**
 * Created by zhanghaitao1 on 2017/12/5.
 */

public class ExampleDelegate extends LongForDelegate {
    @Override
    public Object setLayout() {
//        testRestClient();
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .loader(getContext())
                .build()
                .get();
    }
}
