package net.chinaedu.aedu.network.http;

import java.lang.reflect.Type;

/**
 * Created by MartinKent on 2017/5/22.
 */

public interface IAeduHttpConvertor {
    <T> T convert(String response, Type clazz);
}
