package net.chinaedu.aedu.network.http.convertor;

import net.chinaedu.aedu.network.http.IAeduHttpConvertor;

import java.lang.reflect.Type;

/**
 * Created by MartinKent on 2017/5/22.
 */

@SuppressWarnings("unchecked")
public class AeduHttpStringConvertor implements IAeduHttpConvertor {

    public static AeduHttpStringConvertor create() {
        return new AeduHttpStringConvertor();
    }

    private AeduHttpStringConvertor() {
        //no instance
    }

    @Override
    public <T> T convert(String response, Type clazz) {
        return (T) response;
    }
}
