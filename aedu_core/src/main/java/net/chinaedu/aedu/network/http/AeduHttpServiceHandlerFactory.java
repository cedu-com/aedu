package net.chinaedu.aedu.network.http;

import net.chinaedu.aedu.network.http.iml.retrofit.AeduRetrofitServiceHandler;

/**
 * Created by MartinKent on 2017/5/22.
 */

class AeduHttpServiceHandlerFactory {
    public static AeduBaseHttpServiceHandler create(AeduHttpServiceBuilder builder) {
        return new AeduRetrofitServiceHandler(builder);
    }
}
