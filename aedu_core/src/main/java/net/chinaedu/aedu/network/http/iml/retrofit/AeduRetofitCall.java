package net.chinaedu.aedu.network.http.iml.retrofit;

import net.chinaedu.aedu.network.http.IAeduHttpCall;

import retrofit2.Call;

/**
 * Created by Qinyun on 2017/10/24.
 */

public class AeduRetofitCall extends IAeduHttpCall<Call> {
    @Override
    public void cancel() {
        ((Call)mResult).cancel();
    }
}
