package net.chinaedu.aedu.mvp;

import android.os.Bundle;

import net.chinaedu.aedu.activity.AeduBaseActivity;

/**
 * Created by qinyun on 2017/2/28.
 * V View
 * P presenter
 */
public abstract class AeduMvpBaseActivity<V extends IAeduMvpView, P extends IAeduMvpPresenter> extends AeduBaseActivity {
    P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.release();
            mPresenter = null;
        }
    }

    public P getPresenter() {
        return mPresenter;
    }

    protected abstract P createPresenter();
    protected abstract V createView();
}
