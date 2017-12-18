package com.longfor.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.ec.database.DatabaseManager;
import com.longfor.ec.database.UserProfile;

/**
 * Created by zhanghaitao1 on 2017/12/18.
 */

public class SignHandler {
    public static void onSignIn(String response){
        final JSONObject userProfileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = userProfileJson.getLong("userId");
        final String name = userProfileJson.getString("name");
        final String avatar = userProfileJson.getString("avatar");
        final String gender = userProfileJson.getString("gender");
        final String address = userProfileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(userProfile);
    }

    public static void onSignUp(String response){
        final JSONObject userProfileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = userProfileJson.getLong("userId");
        final String name = userProfileJson.getString("name");
        final String avatar = userProfileJson.getString("avatar");
        final String gender = userProfileJson.getString("gender");
        final String address = userProfileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(userProfile);
    }
}
