package net.chinaedu.aedu.mvp;

import android.content.Context;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by qinyun on 2017/2/28.
 * V View
 * M Model
 */

public abstract class AeduBasePresenter<V extends IAeduMvpView, M extends IAeduMvpModel> implements IAeduMvpPresenter<V, M> {
    Reference<Context> mContext;
    Reference<V> mViewRef;
    M mModel;

    public AeduBasePresenter(Context context, V view) {
        mContext = new WeakReference<>(context);
        attachView(view);
        mModel = createModel();
    }

    @Override
    public Context getContext() {
        return null == mContext ? null : mContext.get();
    }

    @Override
    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    @Override
    public V getView() {
        return null == mViewRef ? null : mViewRef.get();
    }

    @Override
    public M getModel() {
        return mModel;
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    @Override
    public void release() {
        detachView();
        mModel = null;
        if(mContext != null){
            mContext.clear();
            mContext = null;
        }
    }

    public abstract M createModel();
}
