package net.chinaedu.aedu.network.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MartinKent on 2017/5/23.
 */

public interface IAeduHttpInterceptor {
    Map<String, String> intercept(Map<String, String> in);
}
