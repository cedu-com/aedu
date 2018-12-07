package net.chinaedu.aedu.network.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MartinKent on 2017/5/23.
 */

class AeduHttpDefaultInterceptor implements IAeduHttpInterceptor {
    @Override
    public Map<String, String> intercept(Map<String, String> in) {
        if (null == in) {
            return new HashMap<>();
        }
        return in;
    }
}
