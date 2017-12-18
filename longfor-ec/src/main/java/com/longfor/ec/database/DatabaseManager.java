package com.longfor.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by zhanghaitao1 on 2017/12/18.
 */

public class DatabaseManager {
    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private  DatabaseManager() {
    }
    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context,"channel_ec.db");
        final Database  db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao(){
        return mDao;
    }

    public static final class Holder{
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }
}
