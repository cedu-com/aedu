package net.chinaedu.aedu.mvp;

import android.content.Context;

/**
 * Created by qinyun on 2017/3/20.
 */

public interface IAeduMvpPresenter<V extends IAeduMvpView, M extends IAeduMvpModel>{

    public void attachView(V view);

    public Context getContext();

    public V getView();

    public M getModel();

    public boolean isViewAttached();

    public void release();

    public void detachView();
}
