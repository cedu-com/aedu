package net.chinaedu.aedu.mvp;

/**
 * Created by qinyun on 2017/3/20.
 */

public abstract class AeduMvpViewActivity<P extends IAeduMvpPresenter> extends AeduMvpBaseActivity<AeduMvpViewActivity, P> implements IAeduMvpView {
    @Override
    protected AeduMvpViewActivity createView() {
        return this;
    }
}
