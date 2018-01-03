package com.longfor.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.longfor.core.app.LongFor;
import com.longfor.core.net.RestClient;
import com.longfor.core.net.callback.ISuccess;
import com.longfor.ui.recycler.DataConverter;
import com.longfor.ui.recycler.MultipleRecyclerAdapter;

import org.greenrobot.greendao.database.Database;

/**
 * Created by zhanghaitao1 on 2017/12/25.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    public RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT,
                          PagingBean BEAN,
                          RecyclerView RECYCLERVIEW,
                          DataConverter CONVERTER) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        this.BEAN = BEAN;
        this.RECYCLERVIEW = RECYCLERVIEW;
        this.CONVERTER = CONVERTER;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler creat(SwipeRefreshLayout swipeRefreshLayout,
                                       RecyclerView recyclerView,
                                       DataConverter converter) {
        return new RefreshHandler(swipeRefreshLayout, new PagingBean(), recyclerView, converter);
    }

    @Override
    public void onRefresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        LongFor.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total")).setPageSize(object.getInteger("page_size"));
                        //设置adapter
                        mAdapter = MultipleRecyclerAdapter.creat(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }

    private void paging(final String url) {
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();
        if (mAdapter.getData().size() < pageSize || currentCount > total) {
            mAdapter.loadMoreEnd(true);
        } else {
            LongFor.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestClient
                            .builder()
                            .url(url + index)
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    CONVERTER.clearData();
                                    mAdapter.addData(CONVERTER.setJsonData(response).convert());
                                    //累加数量
                                    BEAN.setCurrentCount(mAdapter.getData().size());
                                    mAdapter.loadMoreComplete();
                                    BEAN.addIndex();
                                }
                            })
                            .build()
                            .get();
                }
            }, 1000);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        paging("refresh.php?index=");
    }
}
