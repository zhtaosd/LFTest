package com.longfor.ui.recycler;

import java.util.ArrayList;

/**
 * Created by zhanghaitao1 on 2017/12/28.
 */

public abstract class DataConverter {
    protected  final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json){
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData(){
        if(mJsonData==null||mJsonData.isEmpty()){
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }

    public void clearData(){
        ENTITIES.clear();
    }

}
