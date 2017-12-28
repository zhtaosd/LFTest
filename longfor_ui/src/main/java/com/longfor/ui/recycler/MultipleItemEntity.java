package com.longfor.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.util.LinkedHashMap;

/**
 * Created by zhanghaitao1 on 2017/12/28.
 */

public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUEUE = new ReferenceQueue<>();
    @Override
    public int getItemType() {
        return 0;
    }
}
