package net.chinaedu.aedu.activity;

import android.support.v4.app.FragmentActivity;

/**
 * Created by qinyun on 2017/2/27.
 * Activity基类
 */

public class AeduBaseActivity extends FragmentActivity {
    /**
     * 是否销毁标志
     */
    private volatile boolean mIsDestory;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIsDestory = true;
    }

    /**
     * 是否销毁
     * @return
     */
    protected boolean isDestory(){
        return mIsDestory;
    }
}
