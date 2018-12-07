package net.chinaedu.aedu.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MartinKent on 2017/4/25.
 */
@SuppressWarnings("unchecked")
public abstract class AeduBaseFragment<V extends IAeduMvpView, P extends IAeduMvpPresenter> extends Fragment {
    private View mRootView;
    private ViewGroup mContainer;
    protected AeduMvpBaseActivity mActivity;

    private V mView;
    private P mPresenter;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mContainer = container;
        this.mRootView = onCreateView(inflater, savedInstanceState);
        return mRootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof AeduMvpBaseActivity)) {
            throw new RuntimeException("Activity for AeduBaseFragment must be instance of AeduMvpBaseActivity");
        }
        mActivity = (AeduMvpBaseActivity) activity;
        ensureViewAndPresenter();
    }

    private synchronized void ensureViewAndPresenter() {
        if (null == mPresenter || null == mView) {
            mView = createView();
            mPresenter = createPresenter();
            if(mPresenter != null){
                mPresenter.attachView(mView);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        if (null != mPresenter) {
            mPresenter.detachView();
        }
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public View getRootView() {
        return mRootView;
    }

    public ViewGroup getContainer() {
        return mContainer;
    }

    public P getPresenter() {
        ensureViewAndPresenter();
        return mPresenter;
    }

    @NonNull
    protected abstract V createView();

    @NonNull
    protected abstract P createPresenter();

    @NonNull
    abstract protected View onCreateView(LayoutInflater inflater, @Nullable Bundle savedInstanceState);
}
