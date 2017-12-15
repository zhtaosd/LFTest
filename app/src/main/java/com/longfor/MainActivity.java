package com.longfor;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.longfor.greendao.DaoMaster;
import com.longfor.greendao.DaoSession;
import com.longfor.greendao.User;
import com.longfor.greendao.UserDao;

public class MainActivity extends AppCompatActivity {


    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "recluse-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();

        User user = new User((long)1, "recluse");

        userDao.insert(user);
        userDao.update(user);
        userDao.deleteByKey((long)1);
        userDao.delete(user);
    }
}
