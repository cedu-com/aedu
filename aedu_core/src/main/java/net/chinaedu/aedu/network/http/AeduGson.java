package net.chinaedu.aedu.network.http;

import java.lang.reflect.Type;

/**
 * Created by MartinKent on 2017/5/22.
 */

public class AeduGson {
    private static com.google.gson.Gson gson = new com.google.gson.Gson();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }
}
