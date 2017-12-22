package com.longfor.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.core.app.AccountManager;
import com.longfor.ec.database.DatabaseManager;
import com.longfor.ec.database.UserProfile;

/**
 * Created by zhanghaitao1 on 2017/12/18.
 */

public class SignHandler {
    public static void onSignIn(String response,ISignListener signListener){
        final JSONObject userProfileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = userProfileJson.getLong("userId");
        final String name = userProfileJson.getString("name");
        final String avatar = userProfileJson.getString("avatar");
        final String gender = userProfileJson.getString("gender");
        final String address = userProfileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(userProfile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

    public static void onSignUp(String response,ISignListener signListener){
        final JSONObject userProfileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = userProfileJson.getLong("userId");
        final String name = userProfileJson.getString("name");
        final String avatar = userProfileJson.getString("avatar");
        final String gender = userProfileJson.getString("gender");
        final String address = userProfileJson.getString("address");

        final UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(userProfile);

        //已经注册并成功登录了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
