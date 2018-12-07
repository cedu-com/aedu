package net.chinaedu.aedu.network.http;

/**
 * Created by Qinyun on 2017/10/24.
 */

public abstract class IAeduHttpCall<T> {
    protected T mResult;

    public void setResult(T result){
        mResult = result;
    }

    public abstract void cancel();
}
