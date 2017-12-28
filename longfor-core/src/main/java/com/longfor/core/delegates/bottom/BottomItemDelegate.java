package com.longfor.core.delegates.bottom;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.longfor.core.delegates.LongForDelegate;

/**
 * Created by zhanghaitao1 on 2017/12/22.
 */

public abstract class BottomItemDelegate extends LongForDelegate implements View.OnKeyListener{
    //连续点击两次退出程序时间设置
    private static final long WATE_TIME = 2000L;
    private long touch_time = 0;
    private BackHandledInterface mBackHandledInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(getActivity() instanceof BackHandledInterface)) {
            throw new ClassCastException(
                    "Hosting Activity must implement BackHandledInterface");
        } else {
            this.mBackHandledInterface = (BackHandledInterface) getActivity();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if(rootView!=null){
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public void onStart() {
        mBackHandledInterface.setSelectedFragment(this);
        super.onStart();
    }


    public boolean onBackPressed() {
        if(System.currentTimeMillis() - touch_time < WATE_TIME){
            _mActivity.finish();
            if(touch_time!=0){
                touch_time = 0;
            }
            return true;
        }else{
            touch_time = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(System.currentTimeMillis() - touch_time < WATE_TIME){
            _mActivity.finish();
            if(touch_time!=0){
                touch_time = 0;
            }
            return true;
        }else{
            touch_time = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public boolean onBackPressedSupport() {
        if(System.currentTimeMillis() - touch_time < WATE_TIME){
            _mActivity.finish();
        }else{
            touch_time = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
