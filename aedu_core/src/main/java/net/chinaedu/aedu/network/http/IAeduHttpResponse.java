package net.chinaedu.aedu.network.http;

import java.util.List;
import java.util.Map;

/**
 * Created by MartinKent on 2017/5/23.
 */

public interface IAeduHttpResponse<T> {
    int code();

    T body();

    Map<String, List<String>> headers();
}
