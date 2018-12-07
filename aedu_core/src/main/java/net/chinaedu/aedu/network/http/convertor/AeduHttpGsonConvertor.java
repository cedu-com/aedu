package net.chinaedu.aedu.network.http.convertor;

import net.chinaedu.aedu.network.http.AeduGson;
import net.chinaedu.aedu.network.http.IAeduHttpConvertor;

import java.lang.reflect.Type;

/**
 * Created by MartinKent on 2017/5/22.
 */

@SuppressWarnings("unchecked")
public class AeduHttpGsonConvertor implements IAeduHttpConvertor {

    public static AeduHttpGsonConvertor create() {
        return new AeduHttpGsonConvertor();
    }

    private AeduHttpGsonConvertor() {
        //no instance
    }

    @Override
    public <T> T convert(String response, Type clazz) {
        return AeduGson.fromJson(response, clazz);
    }

}
